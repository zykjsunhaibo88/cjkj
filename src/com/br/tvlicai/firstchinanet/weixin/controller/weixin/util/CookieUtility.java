/*
 * ==================================================
 * 系统开发地 : 中国辽宁沈阳2011年
 * 系统名称: SSL_PRE
 * 文件名: CookieUtility.java
 * ==================================================
 *  开发环境: JDK1.6
 * ==================================================
 * -变更履历  作者   YYYY/MM/DD REV. 备注
 * -制作          李鑫    2011-7-20 1.00 新建文件
 * ==================================================
 * (C) Copyright LiXin 2011 All Rights Reserved.
 */
package com.br.tvlicai.firstchinanet.weixin.controller.weixin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;


/**
 * Two static methods for use in cookie handling. Updated to Java 5.
 * <P>
 * Taken from Core Servlets and JavaServer Pages 2nd Edition from Prentice Hall
 * and Sun Microsystems Press, http://www.coreservlets.com/. &copy; 2003 Marty
 * Hall; may be freely used or adapted.
 */

public class CookieUtility {

	/**
	 * Given the request object, a name, and a default value, this method tries
	 * to find the value of the cookie with the given name. If no cookie matches
	 * the name, the default value is returned.
	 */

	public static String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
		Cookie[] cookies = request.getCookies();
		String cookieValue = defaultValue;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				//SSLog.debug(cookie.getName() + ":" + cookie.getValue());
				if (cookieName.equals(cookie.getName())) {
					cookieValue = cookie.getValue();
					break;
				}
			}
		}
		if (StringUtils.isNotBlank(cookieValue)) {
			try {
				cookieValue = URLDecoder.decode(cookieValue, "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
		}
		if(!StringUtils.isBlank(cookieValue)){
			if(request.getAttribute(cookieName) != null){
				cookieValue = String.valueOf(request.getAttribute(cookieName));
			}
		}
		if(!StringUtils.isBlank(cookieValue)){
			if(request.getParameter(cookieName) != null){
				cookieValue = request.getParameter(cookieName);
			}
		}
		return cookieValue;
	}

	/**
	 * Given the request object and a name, this method tries to find and return
	 * the cookie that has the given name. If no cookie matches the name, null
	 * is returned.
	 */

	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					return (cookie);
				}
			}
		}
		return (null);
	}

	public static final int ONE_YEAR = 60 * 60 * 24 * 365;

	/**
	 * 设定Cookiet10年
	 * 
	 * @param res
	 * @param cookieName
	 * @param value
	 * @param seconds
	 */
	public static void setCookieValue(HttpServletResponse res, String cookieName, String value, int seconds) {
		String cookieValue = value;
		if (StringUtils.isNotBlank(cookieValue)) {
			try {
				cookieValue = URLEncoder.encode(cookieValue, "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
		}
		Cookie userCookie = new Cookie(cookieName, cookieValue);
		//userCookie.setDomain(ServiceConst.DOMAIN_SITE);
		userCookie.setPath("/");
		userCookie.setMaxAge(seconds);
		res.addCookie(userCookie);
	}

	/**
	 * 设定Cookiet10年
	 * 
	 * @param res
	 * @param cookieName
	 * @param value
	 */
	public static void setCookieValue(HttpServletResponse res, String cookieName, String value) {
		String cookieValue = value;
		if (StringUtils.isNotBlank(cookieValue)) {
			try {
				cookieValue = URLEncoder.encode(cookieValue, "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
		}
		Cookie userCookie = new Cookie(cookieName, cookieValue);
		//userCookie.setDomain(ServiceConst.DOMAIN_SITE);
		userCookie.setPath("/");
		userCookie.setMaxAge(ONE_YEAR * 10);
		res.addCookie(userCookie);
	}

	public static void deleteCookie(HttpServletResponse res, String cookieName) {
		Cookie userCookie = new Cookie(cookieName, "");
		//userCookie.setDomain(ServiceConst.DOMAIN_SITE);
		userCookie.setPath("/");
		userCookie.setMaxAge(0);
		res.addCookie(userCookie);
	}

	public static void main(String args[]) {
		// lixin'sPC
		System.out.println(DigestUtils.md5Hex("100000"));
		// yuanp'sPC
		System.out.println(DigestUtils.md5Hex(""));
		// wuhuiyue
		System.out.println(DigestUtils.md5Hex("00-E0-4C-83-15-5B"));
		// lidong
		System.out.println(DigestUtils.md5Hex("F0-DE-F1-50-12-57"));
		// QRCodeEncoderHandler.createQRCodePng("","");
		// try {
		// cdd91b293c0245a2aee517814918fc36
		// //c4ca4238a0b923820dcc509a6f75849b
		// String domain = ServiceConst.QR_PATH ;
		// String url = domain + "/sign.do?atu=30";
		// QRCodeEncoderHandler.createQRCodePng(ServiceConst.QR_PATH +
		// "\\30.png", url);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// Log.logger.error("系统异常", e);
		// }

	}
}