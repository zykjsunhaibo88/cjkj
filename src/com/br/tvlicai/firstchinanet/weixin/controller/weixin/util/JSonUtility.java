/*
 * ==================================================
 * 系统开发地 : 中国辽宁沈阳
 * 系统名称: GNS（GNS_SHIPPING）
 * 文件名: JSonUtility.java
 * ==================================================
 *  开发环境: JDK1.7
 * ==================================================
 * -变更履历  作者   YYYY/MM/DD REV. 备注
 * -制作          李鑫    2015-12-27 1.00 新建文件
 * ==================================================
 * (C) Copyright SSL 2014-2016 All Rights Reserved.
 */
package com.br.tvlicai.firstchinanet.weixin.controller.weixin.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.br.tvlicai.firstchinanet.cmn.pay.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * @author James
 *
 */
public class JSonUtility {
	/**
	 * 将Json对象转换成Map
	 *
	 * @param
	 *
	 * @return Map对象
	 * @throws JSONException
	 */
	public static Map<String, String> toMap(String jsonString) throws JSONException {
		Map<String, String> result = new HashMap<String, String>();
		if (StringUtils.isNotBlank(jsonString)) {
			JSONObject jsonObject = new JSONObject(jsonString);
			Iterator<String> iterator = jsonObject.keys();
			String key = null;
			String value = null;
			while (iterator.hasNext()) {
				key = iterator.next();
				value = String.valueOf(jsonObject.get(key));
				result.put(key, value);
			}
		}
		return result;
	}
	/**
	 * 将Json对象转换成Map的String
	 *
	 * @param
	 *
	 * @return Map对象
	 * @throws JSONException
	 */
	public static String toJSonString(Map<String, String> map) throws JSONException {
		String jsonString = "";
		if(map!= null){
			// 实例Google Json对象
			Gson gson = new Gson();
			// 转换数据
			jsonString = gson.toJson(map);
		}
		// 返回Gson文本
		return jsonString;
	}

	public static void main(String [] args){
		//String json = "{\"access_token\":\"OezXcEiiBSKSxW0eoylIeAQDwdK3RqcWWqoT02Cmoq509VCCWVFC0-P9m97d5ErgWFJSjY_ndSQd9RIMHioQa8brRyB-Z5-yKDXxu0vVZULGp2HJNwUncKXh20QxTfw8H4IM6Ev6AJ06rjVcIdtTag\",\"expires_in\":7200,\"refresh_token\":\"OezXcEiiBSKSxW0eoylIeAQDwdK3RqcWWqoT02Cmoq509VCCWVFC0-P9m97d5ErgcC0mqMDcM5dht9ThCwIbDheluJknPXRzRFgTzhgEp6FAJQHhGA8j3F0GPTL4oCJCU69E-1M0ovj4SVmzCYd4bA\",\"openid\":\"oI2Mzt-G5bTS2syM8n1QR6XtBqog\",\"scope\":\"snsapi_userinfo\"}";
		//toMap(json);
	}
}
