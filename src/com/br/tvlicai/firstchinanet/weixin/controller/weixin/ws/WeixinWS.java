/*
 * ==================================================
 * 系统开发地 : 中国辽宁沈阳
 * 系统名称: 超级组讯平台（SS）
 * 文件名: WeixinWS.java
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface WeixinWS {
	/**
	 * 核心处理3：用户授权访问，后重定向
	 *
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	public void oauth2(HttpServletRequest req, HttpServletResponse res) throws Exception;



}
