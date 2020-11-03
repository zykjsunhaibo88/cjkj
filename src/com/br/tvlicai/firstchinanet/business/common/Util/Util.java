package com.br.tvlicai.firstchinanet.business.common.Util;

import com.br.tvlicai.firstchinanet.commom.Const.Const;
import net.sf.json.JSONObject;
import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Util {
	static final Logger _LOG = LoggerFactory.getLogger(Util.class);
	public final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public final static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd");
	public final static SimpleDateFormat sdfDate_8b = new SimpleDateFormat(
			"yyyyMMdd");
	public final static SimpleDateFormat sdfTime_14b = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	public static String nowDate() {
		return sdfDate.format(new Date());
	}

	public static String nowDate_8b() {
		return sdfDate_8b.format(new Date());
	}

	public static String nowTime() {
		return sdfTime.format(new Date());
	}

	public static String nowTime_14b() {
		return sdfTime_14b.format(new Date());
	}

	public static Date now4Date(String str) {
		Date date = null;
		try {
			date = sdfDate.parse(str);
		} catch (ParseException e) {
			_LOG.error("异常信息", e);
		}
		return date;
	}

	/**
	 * 根据value值判断是否添加单引号(如果value是null则不加单引号)
	 * 
	 * @param
	 * @return :insert语句用到的字段值
	 */
	public static String toSqlVal(Object value) {
		if (value == null ||(value != null && value.toString().equals("null"))){
			return null;
		}else{
			
			String tag = "'";
			String rep = "''";
			
			value = replaceAll(value.toString(),tag,rep);
			
			return "'" + value.toString() + "'";
		}
	}
	
	/**为对象加入'' 组成'对象'形式,如果是null则返回null
	 * param:value:具体条件,type:F表示前面加%,B表示后面加,ALL表示前后都加
	 * return:包装后的字符串
	 * */
	public static String toSqlValLike(Object value, String type){
		if(value == null){
			return null;
		}else{
			if(type.equals("F")){
		
				return "'%" + value.toString() + "'";
			}else if(type.equals("B")){
				return "'" + value.toString() + "%'";
			}else if(type.equals("ALL")){
				return "'%" + value.toString() + "%'";
				
			}
			return "";
		}
	}
	
	/**判断对象是否可以做为sql的查询条件
	 * param:Object
	 * return:是或者否
	 * */
	public static boolean isCon(Object obj){
		if(obj != null && !obj.toString().equals("") && !obj.toString().equals("null")){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}
	


	
	public static String list2String(List list){
		String str = "(";
		
		
		for(int i = 0;i < list.size();i++){
			str += toSqlVal(list.get(i));
			
			if((i+1) != list.size()){
				str +=",";
			}
		}
		
		str += ")";
		return str;
	}
	

	/**
	 * datestr 为yyyy-MM-dd 格式的日期字符串      
	 * bforafnum 为之后或之前的月数  如果是之前  就传负数
	 * 返回规定时间   之前或之后规定月数  的时间  也返回yyyy-MM-dd 格式的字符串
	 */
	public static String dateBfOrAfTime(String datestr, int bforafnum) {
		String reStr="";
		try {
			Date now  = sdfDate.parse(datestr);
			 Calendar ca = Calendar.getInstance();
				ca.setTime(now);
				ca.add(Calendar.MONTH, bforafnum);
				reStr = sdfDate.format(ca.getTime());
		} catch (Exception e) {
			_LOG.error("异常信息", e);
		}
		return reStr;
	}

	public static String replaceAll(String con, String tag, String rep) {
		int i = 0;
		int j = 0;
		int k = 0;
		String RETU = "";
		String temp = con;
		int tagc = tag.length();
		while (i < con.length())
			if (con.substring(i).startsWith(tag)) {
				temp = String.valueOf(con.substring(j, i))
						+ String.valueOf(rep);
				RETU = String.valueOf(RETU) + String.valueOf(temp);
				i += tagc;
				j = i;
			} else {
				i++;
			}
		RETU = String.valueOf(RETU) + String.valueOf(con.substring(j));
		return RETU;
	}

	/**
	 * 获取当前登录用户Aid
	 * @param session
	 * @return 个人用户返回登录用户切换到的群组用户Aid，否则直接返回登录用户的Aid
     */
	public static String getCurrentSelUserAid(HttpSession session) {
		String loginAid = session.getAttribute(Const.USERINFO_USER_AIDOLD) == null ? "" : session.getAttribute(Const.USERINFO_USER_AIDOLD).toString();
		return loginAid;
	}

	/**
	 * 获取当前登录用户类型
	 * @param session
	 * @return 个人用户返回登录用户切换到的群组用户类型
     */
	public static String getCurrentSelUserType(HttpSession session) {
		String loginType = session.getAttribute(Const.USERINFO_USER_TYPE) == null ? "" : session.getAttribute(Const.USERINFO_USER_TYPE).toString();
		if (Util.isCon(loginType)) {
			if (loginType.equals(Const.USER_TYPE_PERSONAL)) {//个人用户
				loginType = session.getAttribute(Const.SESSION_SEL_GROUP_TYPE) == null ? "" : session.getAttribute(Const.SESSION_SEL_GROUP_TYPE).toString();
			}
		}
		return loginType;
	}


	/**
	 * 信息认证接口
	 * @param accreditationFlag true：企业 false：身份证
	 * @param regNo 营业执照号
	 * @param companyName 企业名称
	 * @param frname 企业法人
	 * @param certificate 身份证号
	 * @param personName 姓名
	 * @return
	 */
	public static JSONObject accreditation(boolean accreditationFlag, String regNo, String companyName, String frname, String certificate, String personName){
		try {
			String url_str = null;
			if (accreditationFlag) {
				url_str = Const.ACCREDITATION_ENTERPRISE_URL + "?key=" + Const.ACCREDITATION_KEY + "&regNo=" + regNo + "&companyName=" + companyName + "&frName=" + frname;
			} else {
				url_str = Const.ACCREDITATION_CERTIFICATE_URL + "?key=" + Const.ACCREDITATION_KEY + "&idNum=" + certificate + "&name=" + personName;
			}
			URL url = new URL(url_str);
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			urlCon.connect();         //获取连接
			InputStream is = urlCon.getInputStream();

			BufferedReader buffer = new BufferedReader(new InputStreamReader(is,"utf-8"));
			StringBuffer bs = new StringBuffer();
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}
			return JSONObject.fromObject(bs.toString());
		} catch (IOException e) {
			_LOG.error("异常信息", e);
		}
		return null;
	}


	private static final Md5PasswordEncoder md5encoder = new Md5PasswordEncoder();

	public static String md5encode(String rawPass, String salt) {
		return md5encoder.encodePassword(rawPass, salt);
	}

	public static boolean md5match(String encPass, String rawPass, String salt) {
		return md5encoder.isPasswordValid(encPass, rawPass, salt);
	}
}
