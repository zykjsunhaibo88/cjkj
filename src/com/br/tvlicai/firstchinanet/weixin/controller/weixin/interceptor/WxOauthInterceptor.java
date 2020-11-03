package com.br.tvlicai.firstchinanet.weixin.controller.weixin.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.tvlicai.firstchinanet.weixin.controller.weixin.util.CookieUtility;
import com.br.tvlicai.firstchinanet.weixin.controller.weixin.ws.WeixinWSImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WxOauthInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 微信认证服务
	 */
	@Autowired
	private WeixinWSImpl weixinWSImpl;
	/**
	 * controller处理前拦截。
	 *
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @param handler
	 *            handler
	 * @return 是否重定向
	 * @throws
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String queryString = request.getQueryString();
		// 取得浏览器信息
		String userAgent = request.getHeader("user-agent").toLowerCase();
		boolean isSettingPage = request.getRequestURI().contains("/users/setting");
		// 如果是微信浏览器
		if (userAgent.indexOf("micromessenger") != -1) {
			// 取得请求类型
			String requestType = request.getHeader("x-requested-with");
			// 不是ajax请求
			if (requestType == null || !requestType.equalsIgnoreCase("XMLHttpRequest")) {
				//取得cookie中的用户信息
				String wxOpenid = CookieUtility.getCookieValue(request, "openid", null);
				//取得cookie中的用户主键
				String userIndexNo = CookieUtility.getCookieValue(request, "userIndexNo", null);
				// cookie中没有用户信息
				if (StringUtils.isBlank(wxOpenid) || StringUtils.isBlank(userIndexNo) || isSettingPage) {//如果是调置画面（要比较用户信息是否有变化）
					// 取得微信code
					String code = request.getParameter("code");
					// code不为空
					if (StringUtils.isNotBlank(code)) {
						// 取得微信用户信息
						Map<String, String> tmpData = weixinWSImpl.getWeixinUser(request, response);
						//Map<String, String>
						//jsonData-01:{"errcode":40029,"errmsg":"invalid code, hints: [ req_id: 2F_7Ca0798ns53 ]"}
						//if(!CheckUtility.checkValue(tmpData.get("openid"))){//如果没有openid
                        if(StringUtils.isBlank(tmpData.get("openid"))){
							redirectOauthPage(request, response, true);
							// 重定向
							return false;
						}
					} else {
						redirectOauthPage(request, response, false);
						// 重定向
						return false;
					}
					// 取得signature
					weixinWSImpl.getSignature(request);
				}
			}
		}
		// 不重定向
		return true;
	}

	private void redirectOauthPage(HttpServletRequest request, HttpServletResponse response, boolean isClearParam)
			throws UnsupportedEncodingException, IOException {
		isClearParam = false;
		// 请求的路径
		String url = request.getRequestURL().toString();
		// 请求的工程名
		String contextPath = request.getContextPath();
		// 请求的参数
		String queryString = request.getQueryString();
		// url中包含工程名
		if (StringUtils.isNotBlank(contextPath)) {
			// 去掉工程名
			url = url.replaceFirst(contextPath + "/", "/");
		}
		// url中包含参数
		if (StringUtils.isNotBlank(queryString) && !isClearParam) {
			// 将参数加到url中
			url = url + "?" + queryString;
		}
        url = URLEncoder.encode(url, "UTF-8");
        //
		String redUrl = "http://weixin.wanfangyun.cn:80/" + "wxOauth/oauth2/?id=1&scope=snsapi_userinfo&state=1&url="
				+ url;
		// SSLog.debug(redUrl);
		//WXLog.info("<<WxOauthInterceptor>>-redUrl:" + redUrl);
		// 重定向到用户认证功能
		response.sendRedirect(redUrl);
	}

}
