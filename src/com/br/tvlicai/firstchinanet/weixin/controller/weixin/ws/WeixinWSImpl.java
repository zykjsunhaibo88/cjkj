/*
 * ==================================================
 * 系统开发地 : 中国辽宁沈阳
 * 系统名称: 超级组讯平台（SS）
 * 文件名: WeixinWSImpl.java
 * ==================================================
 *  开发环境: JDK1.7+
 * ==================================================
 * -变更履历  作者   YYYY/MM/DD REV. 备注
 * -制作          李鑫    2016年4月11日 1.00 新建文件
 * ==================================================
 * (C) Copyright Shenyang BR Tech. 2016 All Rights Reserved.
 */
package com.br.tvlicai.firstchinanet.weixin.controller.weixin.ws;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.br.tvlicai.firstchinanet.weixin.controller.weixin.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author James
 *
 */
@Service
public class WeixinWSImpl implements WeixinWS {

	@Override
	public void oauth2(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
	}
//	// TODO
//	private final static String Token = "123456789abcdef";
//	/**
//	 * #wechat_redirect:无论直接打开还是做页面302重定向时候，必须带此参数
//	 */
//	public final static String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
//
//	@Autowired
//	private WxConfBusInterface wxConfBusInterface;
//	@Autowired
//	private WxUserBusInterface wxUserBusInterface;
//	/**
//	 * 核心处理3：用户授权访问，weixin oauth2.0 接入授权后重定向
//	 * 
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@Override
//	public void oauth2(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// parseXml(request);
//		// 1、以snsapi_base为scope发起的网页授权，是用来获取进入页面的用户的openid的，并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（往往是业务页面）
//		// 2、以snsapi_userinfo为scope发起的网页授权，是用来获取用户的基本信息的。但这种授权需要用户手动同意，并且由于用户同意过，所以无须关注，就可在授权后获取该用户的基本信息。
//		// 3、用户管理类接口中的“获取用户基本信息接口”，是在用户和公众号产生消息交互或关注后事件推送后，才能根据用户OpenID来获取用户基本信息。这个接口，包括其他微信接口，都是需要该用户（即openid）关注了公众号后，才能调用成功的。
//		String appId = "";
//		String redirectUrl = "";
//		String scope = "snsapi_base";
//		String id = request.getParameter("id");//StringUtility.null2String(request.getParameter("id"));
//		if (StringUtils.isBlank(request.getParameter("scope"))) {
//			scope = request.getParameter("scope");
//		}
//		WxConfBean wxConfBean = new WxConfBean();
//		wxConfBean.setMallId(request.getParameter("url").split("str=")[1].split(",")[1]);
//		wxConfBean.setPlatformId(request.getParameter("url").split("str=")[1].split(",")[0]);
//		WxConfBean wxConfBean1 =  wxConfBusInterface.getWxConf(wxConfBean);
//		appId =wxConfBean1.getAppid();
//		//appId="wxad842248cbb9d157";
//
//		// 重定向url
//		if (StringUtils.isNotBlank(request.getParameter("url"))) {
//			redirectUrl = request.getParameter("url");
//			// 如果开头不是以http或https开头，使用我们服务器的域命进行拼接
//			if (!redirectUrl.toLowerCase().startsWith("http://") && !redirectUrl.toLowerCase().startsWith("https://")) {
//				redirectUrl = ("http://weixin.wanfangyun.cn" + redirectUrl);
//			}
//			if (StringUtils.isBlank(redirectUrl)) {
//				redirectUrl = urlEnodeUTF8(redirectUrl);
//			}
//		}
//		// （弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地
//		String tmpurl = oauth2Url;
//		if (StringUtils.isNotBlank(tmpurl)) {
//			tmpurl = tmpurl.replace("APPID", appId);
//			tmpurl = tmpurl.replace("REDIRECT_URI", redirectUrl);
//			tmpurl = tmpurl.replace("SCOPE", scope);
//			tmpurl = tmpurl.replace("STATE", id);// rename mstBelongPk->id
//		}
//		response.sendRedirect(tmpurl);
//	}
//
//	/**
//	 * 取得微信用户与JJSDK用相关信息(首次通过授权画面过来的时候)
//	 * 
//	 * <pre>
//	 * 通过调用后，自动将用户信息存入如下KEY的请求对象中.
//	 * request.setAttribute(WeiXinConst.REQ_PATH, tokenMap);
//	 * 参数值对应表：http://mp.weixin.qq.com/wiki/4/9ac2e7b1f1d22e9e57260f6553822520.html
//	 * ---- 授权cookie start ----
//	 * map.get("appId"):appId
//	 * map.get("openid")//用户的唯一标识 
//	 * map.get("nickname")//用户昵称 
//	 * map.get("sex")//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 
//	 * map.get("province")//用户个人资料填写的省份 
//	 * map.get("city")//普通用户个人资料填写的城市 
//	 * map.get("headimgurl")//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 
//	 * map.get("privilege")//用户特权信息，json 数组，如微信沃卡用户为（chinaunicom） 
//	 * map.get("country")//国家，如中国为CN 
//	 * map.get("language")//语言:zh_CN
//	 * ---- 授权cookie end ----
//	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
//	 * map.get("unionid")//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
//	 * map.get("signature")//页面调用wx的用的签名
//	 * map.get("timestamp")//时间戳
//	 * map.get("nonceStr")//随机字符串
//	 * map.get("jsapi_ticket")//令牌
//	 * map.get("url")//跳转路径
//	 * 
//	 * 
//	 * Test(因为配置域名的反向代理，会引起签名错误，注意):
//	 * http://ss.cymain.com/wx/oauth2/?id=1&scope=snsapi_userinfo&state=1&url=http%3a%2f%2fss.cymain.com%2fwx%2ftest%2f
//	 * http://ss.cymain.com/wx/oauth2/?id=1&scope=snsapi_base&state=1&url=http%3a%2f%2fss.cymain.com%2fwx%2ftest%2f
//	 * </pre>
//	 * 
//	 * @return the weixinUser
//	 */
//	public Map<String, String> getWeixinUser(HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//
//
//		Map<String, String> map = null;
//		try {
//			String code = request.getParameter("code");
//			if(StringUtils.isNotBlank(code)){
//				WxConfBean wxConfBean = getAppId(request);
//				// 1、以snsapi_base为scope发起的网页授权，是用来获取进入页面的用户的openid的，并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（往往是业务页面）
//				// 2、以snsapi_userinfo为scope发起的网页授权，是用来获取用户的基本信息的。但这种授权需要用户手动同意，并且由于用户同意过，所以无须关注，就可在授权后获取该用户的基本信息。
//				// 3、用户管理类接口中的“获取用户基本信息接口”，是在用户和公众号产生消息交互或关注后事件推送后，才能根据用户OpenID来获取用户基本信息。这个接口，包括其他微信接口，都是需要该用户（即openid）关注了公众号后，才能调用成功的。
//				String appId = wxConfBean.getAppid();
//				String secret = wxConfBean.getAppsecret();
////				String appId = "wxad842248cbb9d157";
////				String secret = "1c5a7ebc962e74c1d9993f9d932299e5";
//
//				map = new HashMap<String, String>();
//				// 如果有CODE需要取出个人信息放入MAP
//				String tmpAccessTokenUrl = WeiXinConst.ACCESS_TOKEN_URL;
//				tmpAccessTokenUrl = tmpAccessTokenUrl.replace("APPID", appId);
//				tmpAccessTokenUrl = tmpAccessTokenUrl.replace("SECRET", secret);
//				tmpAccessTokenUrl = tmpAccessTokenUrl.replace("CODE", code);
//				String jsonData = JsoupUtil.getJson(tmpAccessTokenUrl);
//				map = JSonUtility.toMap(jsonData);
//				// list:http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html
//				if (!map.containsKey("errcode") || "0".equals(map.get("errcode"))) {// 请求成功
//					String tmpUserInfoUrl = WeiXinConst.USER_INFO_URL;
//					tmpUserInfoUrl = tmpUserInfoUrl.replace("ACCESS_TOKEN", map.get("access_token"));
//					tmpUserInfoUrl = tmpUserInfoUrl.replace("OPENID", map.get("openid"));
//					jsonData = JsoupUtil.getJson(tmpUserInfoUrl);
//					map.putAll(JSonUtility.toMap(jsonData));
//					// 如果用户不存在
//					map.put("userIndexNo", "");
//					int i = wxUserBusInterface.selectCountByOpenid(map.get("openid"));
//					if(i==0){
//						WxUserBean wxUserBean = new WxUserBean();
//						wxUserBean.setOpenId(map.get("openid"));
//						wxUserBusInterface.insertWxUser(wxUserBean);
//					}
//				}
//
//				CookieUtility.setCookieValue(response, "openid", map.get("openid"));
//				CookieUtility.setCookieValue(response, "nickname", map.get("nickname"));
//				CookieUtility.setCookieValue(response, "sex", map.get("sex"));
//				CookieUtility.setCookieValue(response, "province", map.get("province"));
//				CookieUtility.setCookieValue(response, "city", map.get("city"));
//				CookieUtility.setCookieValue(response, "headimgurl", map.get("headimgurl"));
//				CookieUtility.setCookieValue(response, "privilege", map.get("privilege"));
//				CookieUtility.setCookieValue(response, "unionid", map.get("unionid"));
//				CookieUtility.setCookieValue(response, "country", map.get("country"));
//				CookieUtility.setCookieValue(response, "language", map.get("language"));
//				CookieUtility.setCookieValue(response, "userIndexNo", map.get("userIndexNo"));
//				CookieUtility.setCookieValue(response, "real_flg", map.get("real_flg"));
//				//首次没有回画面，COOKIE数据没有
//				request.setAttribute("openid", map.get("openid"));
//				request.setAttribute("nickname", map.get("nickname"));
//				request.setAttribute("sex", map.get("sex"));
//				request.setAttribute("province", map.get("province"));
//				request.setAttribute("city", map.get("city"));
//				request.setAttribute("headimgurl", map.get("headimgurl"));
//				request.setAttribute("privilege", map.get("privilege"));
//				request.setAttribute("unionid", map.get("unionid"));
//				request.setAttribute("country", map.get("country"));
//				request.setAttribute("language", map.get("language"));
//				request.setAttribute("userIndexNo", map.get("userIndexNo"));
//				request.setAttribute("real_flg", map.get("real_flg"));
//			}
//		} catch (Exception e) {
//			//SSLog.error(e.getMessage(), e);
//		}
//		return map;
//	}
//
//	/**
//	 * 取得signature(首次通过授权画面过来的时候)
//	 *
//	 * <pre>
//	 * 通过调用后，自动将用户信息存入如下KEY的请求对象中.
//	 * request.setAttribute(WeiXinConst.REQ_PATH, tokenMap);
//	 * 参数值对应表：http://mp.weixin.qq.com/wiki/4/9ac2e7b1f1d22e9e57260f6553822520.html
//	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
//	 * map.get("signature")//页面调用wx的用的签名
//	 * map.get("timestamp")//时间戳
//	 * map.get("nonceStr")//随机字符串
//	 * map.get("jsapi_ticket")//令牌
//	 * map.get("url")//跳转路径
//	 *
//	 *
//	 * Test(因为配置域名的反向代理，会引起签名错误，注意):
//	 * http://ss.cymain.com/wx/oauth2/?id=1&scope=snsapi_userinfo&state=1&url=http%3a%2f%2fss.cymain.com%2fwx%2ftest%2f
//	 * http://ss.cymain.com/wx/oauth2/?id=1&scope=snsapi_base&state=1&url=http%3a%2f%2fss.cymain.com%2fwx%2ftest%2f
//	 * </pre>
//	 *
//	 * @return the weixinUser
//	 */
//	public Map<String, String> getSignature(HttpServletRequest request) throws Exception {
//
//		Map<String, String> map = null;
//		try {
//			WxConfBean wxConfBean = getAppId(request);
//			// 1、以snsapi_base为scope发起的网页授权，是用来获取进入页面的用户的openid的，并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（往往是业务页面）
//			// 2、以snsapi_userinfo为scope发起的网页授权，是用来获取用户的基本信息的。但这种授权需要用户手动同意，并且由于用户同意过，所以无须关注，就可在授权后获取该用户的基本信息。
//			// 3、用户管理类接口中的“获取用户基本信息接口”，是在用户和公众号产生消息交互或关注后事件推送后，才能根据用户OpenID来获取用户基本信息。这个接口，包括其他微信接口，都是需要该用户（即openid）关注了公众号后，才能调用成功的。
//			String appId = wxConfBean.getAppid();
//			String secret = wxConfBean.getAppsecret();
//			String mallId = wxConfBean.getMallId();
//			String platformId = wxConfBean.getPlatformId();
////			String appId = "wxad842248cbb9d157";
////			String secret = "1c5a7ebc962e74c1d9993f9d932299e5";
//			map = new HashMap<String, String>();
//			// -----------weixin jjsdk start
//			// 微信本机调试
//			// http://mp.weixin.qq.com/wiki/10/e5f772f4521da17fa0d7304f68b97d7e.html#.E6.A8.A1.E6.8B.9FJSSDK.E6.9D.83.E9.99.90.E6.A0.A1.E9.AA.8C
//			// 时间戳(秒)
//			String timeStamp = System.currentTimeMillis() / 1000 + "";
//			String noncestr = RandomStringUtils.random(16, "1234567890abcdefghijklmnopqrstuvwxyz"); // 32位随机串=字符串
//			Map<String, String> ticketMap = new HashMap<String, String>();
//
//			map.put("appId", appId);// 必填，公众号的唯一标识
//			map.put("timestamp", timeStamp);// 必填，公众号的唯一标识
//			map.put("nonceStr", noncestr);// 必填，公众号的唯一标识
//			String jsAccessToken = getAccessToken(appId, secret,mallId,platformId);
//			// guide:http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#JSSDK.E4.BD.BF.E7.94.A8.E6.AD.A5.E9.AA.A4
//			String jsTikcet = getTicket(jsAccessToken,mallId,platformId);//WeChat.getTicket(jsAccessToken);
//
//			// ticketMap.put("appId", appId);//必填，公众号的唯一标识
//			ticketMap.put("timestamp", timeStamp);// 必填，生成签名的时间戳
//			ticketMap.put("noncestr", noncestr);// 必填，生成签名的随机串
//			ticketMap.put("jsapi_ticket", jsTikcet);// 必填，公众号的唯一标识
//			map.put("jsapi_ticket", jsTikcet);// 必填，公众号的唯一标识
//			// http://blog.csdn.net/educast/article/details/7780076
//
//			// 如果走向反向代理的时候，会把工程名加上，应该去掉，在走返向代理的时候
//			String url = request.getRequestURL().toString().replaceAll(request.getContextPath() + "/", "/");
//
//			if (StringUtils.isNotBlank(request.getQueryString())) {
//				url += "?" + request.getQueryString();
//			}
//			ticketMap.put("url", url);// 必填，公众号的唯一标识
//			map.put("url", url);// 必填，公众号的唯一标识
//			// 微信 JS 接口签名校验工具
//			// http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=jsapisign
//			String signature = Pay.getSignnature(ticketMap);
//			// http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#JSSDK.E4.BD.BF.E7.94.A8.E6.AD.A5.E9.AA.A4
//			// 附录1-JS-SDK使用权限签名算法
//			ticketMap.put("signature", signature);// 必填，签名，见附录1
//			map.put("signature", signature);// 必填，签名，见附录1
//			// |||||||||||||||||||||||||||||||||||||||||||||
//			// 用户在request/session存储weixin用户信息的KEY
//			request.setAttribute(WeiXinConst.WX_SIGN_KEY, map);
//		} catch (Exception e) {
//			//SSLog.error(e.getMessage(), e);
//		}
//		return map;
//	}
//
//	/**
//	 * 通过request获取微信信息
//	 * @param request
//	 * @return
//	 */
//	public WxConfBean getAppId(HttpServletRequest request){
//		Map<String, String> map = null;
//		String str = request.getParameter("str");
//		String mallId =str.split(",")[1];
//		String platformId = str.split(",")[0];
//		WxConfBean wxConfBean = new WxConfBean();
//		wxConfBean.setMallId(mallId);
//		wxConfBean.setPlatformId(platformId);
//		return wxConfBusInterface.getWxConf(wxConfBean);
//	}
//	/**
//	 * url转码（UTF-8）
//	 */
//	public  String urlEnodeUTF8(String str) {
//		String result = str;
//		try {
//			result = URLEncoder.encode(str, "UTF-8");
//		} catch (Exception e) {
//			//SSLog.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	/**
//	 *
//	 * @param appid
//	 * @param secret
//	 * @return
//	 */
//	public String getAccessToken(String appid,String secret,String mallId,String platformId) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException, NoSuchProviderException, IOException {
//		String accessToken = "123456789abcdef";
//		long crtTime = System.currentTimeMillis() / 1000;
//		long histTime = 0;
//		WxConfBean wxConfBean = new WxConfBean();
//		wxConfBean.setAppid(appid);
//		wxConfBean.setAppsecret(secret);
//		wxConfBean.setMallId(mallId);
//		wxConfBean.setPlatformId(platformId);
//		WxConfBean wxConf =  wxConfBusInterface.getWxConf(wxConfBean);
//		//取得数据库中的token
//		if (wxConf != null) {
//			if(StringUtils.isNotBlank(wxConf.getDateline())){
//				histTime = Long.parseLong(wxConf.getDateline());
//			}
//			// 如果有APP ID 并且 7200秒以内的有效期
//			//SSLog.info("获取access_token(crtTime-histTime)" + crtTime + ":" + histTime + ":" + (crtTime - histTime));
//			if (crtTime - histTime < 7100) {// 7200秒内有效
//				accessToken = wxConf.getAccessToken();
//				return accessToken;
//			}
//		}
//		accessToken = getAccessTokenNoMap(appid, secret);
//		//更新token
//		wxConf.setAccessToken(accessToken);
//		//更新为当前日期
//		wxConf.setDateline(String.valueOf(crtTime));
//		wxConf.setTiket(null);
//		wxConf.setTdateline(null);
//		wxConf.setMallId(mallId);
//		wxConf.setPlatformId(platformId);
//		int updCnt = wxConfBusInterface.updateWxConf(wxConf);
//		if(updCnt > 0 ){
//			//SSLog.info("微信token更新成功!");
//		}
//		return accessToken;
//	}
//
//	/**
//	 *
//	 * @param appid
//	 * @param secret
//	 * @return
//	 */
//	public  String getAccessTokenNoMap(String appid, String secret) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException {
//		String accessToken;
//		String jsonStr = HttpKit.get(WeiXinConst.ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
//		Map<String, Object> map = JSONObject.parseObject(jsonStr);
//		accessToken = map.get("access_token").toString();
//		return accessToken;
//	}
//
//	/**
//	 *
//	 * @param accessToken
//	 * @return
//	 */
//	public String getTicket(String accessToken,String mallId,String platformId) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException {
//		String ticket = "";
//		long crtTime = System.currentTimeMillis() / 1000;
//		long histTime = 0;
//		WxConfBean wxConfBean = new WxConfBean();
//		wxConfBean.setAccessToken(accessToken);
//		wxConfBean.setMallId(mallId);
//		wxConfBean.setPlatformId(platformId);
//		WxConfBean wxConf =  wxConfBusInterface.getWxConf(wxConfBean);
//		if (wxConf != null) {
//			if (StringUtils.isNotBlank(wxConf.getTdateline())) {
//				histTime = Long.parseLong(wxConf.getTdateline());
//			}
//			// 如果有APP ID 并且 7200秒以内的有效期
//			//SSLog.info("获取tiket(crtTime-histTime)" + crtTime + ":" + histTime + ":" + (crtTime - histTime));
//			if (crtTime - histTime < 7100) {// 7200秒内有效
//				ticket = wxConf.getTiket();
//				return ticket;
//			}
//		}
//		String jsonStr = HttpKit.get(WeiXinConst.JSAPI_TICKET.concat(accessToken));
//		Map<String, Object> map = JSONObject.parseObject(jsonStr);
//		ticket = map.get("ticket").toString();
//
//		//更新token
//		wxConf.setAccessToken(null);
//		wxConf.setDateline(null);
//		wxConf.setTiket(ticket);
//		wxConf.setMallId(mallId);
//		wxConf.setPlatformId(platformId);
//		wxConf.setTdateline(String.valueOf(crtTime));
//		int updCnt = wxConfBusInterface.updateWxConf(wxConf);
//		if(updCnt > 0 ){
//			//SSLog.info("微信Token更新成功!");
//		}
//		return ticket;
//	}

	public Map<String, String> getWeixinUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public void getSignature(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
}
