/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.business.service.SpringSecurityAcl.MySecurity;

import com.br.tvlicai.firstchinanet.commom.Const.Const;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Add the description here.
 * Create on 2016/5/12
 *
 * @author xuhai
 * @version 0.0.0
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    //private String redirectUrl = "/sysLogin.vm";

    private String sessionKey = Const.USERINFO_USER_AID;

    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //String url = filterConfig.getInitParameter("redirectUrl");
        String key = filterConfig.getInitParameter("sessionKey");
        //redirectUrl = url == null? redirectUrl:url;
        sessionKey = key == null ? sessionKey : key ;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        if( session == null || session.getAttribute(sessionKey) == null){
            //如果判断是 AJAX 请求,直接设置为session超时
            if( req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equals("XMLHttpRequest") ) {
                rep.setHeader("sessionstatus", "timeout");
            } else {
                //rep.sendRedirect( req.getContextPath() + redirectUrl);
            }
        }else {
            //chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void invoke(FilterInvocation fi) throws IOException,
            ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    public void setSecurityMetadataSource(
            FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

}
