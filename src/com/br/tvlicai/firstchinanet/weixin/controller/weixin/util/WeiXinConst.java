/*
 * ==================================================
 * 系统开发地 : 中国辽宁沈阳2014年
 * 系统名称: 弗艾互联流管理系统（GNS_SHIPPING）
 * 文件名: WeiXinConst.java
 * ==================================================
 *  开发环境: JDK1.7
 * ==================================================
 * -变更履历  作者   YYYY/MM/DD REV. 备注
 * -制作          李鑫    2016-1-15 1.00 新建文件
 * ==================================================
 * (C) Copyright LiXin 2014 All Rights Reserved.
 */
package com.br.tvlicai.firstchinanet.weixin.controller.weixin.util;

/**
 * @author James
 *
 */
public class WeiXinConst {
	/**用户在request/session存储weixin用户信息的KEY*/
	public final static String WX_USER_PATH = "WX_USER_INFO";
	
	/**画面调用微信对象的签名存储用KEY*/
	public final static String WX_SIGN_KEY = "WX_SIGN_KEY";
	
	/**
	 * 取得系统Token路径
	 */
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	// 获取第二步的refresh_token后，请求以下链接获取access_token： (ssl:基本没什么用)
	public final  static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	
	//
	public final static String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	public static final String PAYFEEDBACK_URL = "https://api.weixin.qq.com/payfeedback/update";
	public static final String DEFAULT_HANDLER = "com.gson.inf.DefaultMessageProcessingHandlerImpl";
	public static final String GET_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=";
	public static final String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	public static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";
    
	
}
