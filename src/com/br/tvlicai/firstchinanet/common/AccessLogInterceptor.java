package com.br.tvlicai.firstchinanet.common;

import com.br.tvlicai.firstchinanet.commom.Const.Const;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccessLogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger("AccessLog");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(Const.USERINFO_USER_AID);
        MDC.put("remoteIp", getRemortIP(request));
        MDC.put("sessionId", session.getId());
        MDC.put("userId", userId == null ? "" : userId);
        logger.info("start " + request.getMethod() + " " + request.getRequestURL().toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("end " + request.getMethod() + " " + request.getRequestURL().toString());
        MDC.clear();
    }

    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
