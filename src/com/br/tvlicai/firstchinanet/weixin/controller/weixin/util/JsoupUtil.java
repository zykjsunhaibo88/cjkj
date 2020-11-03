/*
 * ==================================================
 * 系统开发地 : 中国辽宁沈阳
 * 系统名称: GNS（GNS_SHIPPING）
 * 文件名: JsoupUtil.java
 * ==================================================
 *  开发环境: JDK1.7
 * ==================================================
 * -变更履历  作者   YYYY/MM/DD REV. 备注
 * -制作          李鑫    2015-12-27 1.00 新建文件
 * ==================================================
 * (C) Copyright SSL 2014-2016 All Rights Reserved.
 */
package com.br.tvlicai.firstchinanet.weixin.controller.weixin.util;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * @author James
 *
 */
public class JsoupUtil {
	private static final int TIMEOUT = 100000;
	public static final String EXPLORER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";

	/**
	 * 取得Json数据
	 *
	 * @param url
	 * @return
	 */
	public static String getJson(String url) {
		String ret = "";
		Connection conn = Jsoup.connect(url);
		conn.method(Method.POST);
		conn.timeout(5000);// 10 seconds
		conn.userAgent(EXPLORER_AGENT);
		try {
			Document doc = conn.get();
			ret = doc.text();
		} catch (IOException e) {
			//SSLog.error("取得Json数据", e);
		}
		return ret;
	}

	public static void main(String [] args){
		//String val = getJson("http://m.bjcjitec.com/wxp/activityAdtmList.jhtml?=1&callback=jQuery20006628331659491246_1452590607123");
		//Map<String,String> a = JSonUtility.toMap(val);
		//System.out.println(a.keySet());
	}
}
