/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 James Li <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.br.tvlicai.firstchinanet.weixin.controller.weixin.ws;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 支付相关方法
 * @author Yulei
 * email: 28990032@qq.com
 * site:  http://www.qq.com
 *
 */
public class Pay {
    /**
     * 构造签名
     * @param params
     * @param encode
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        return temp.toString();
    }

    /**
     * 取得前台JS用的签名
     * @param params
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String getSignnature(Map<String,String> params) throws UnsupportedEncodingException{
    	String val = createSign(params, false);
		String temp = SHA1(val);
		return temp;
		
    }
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
