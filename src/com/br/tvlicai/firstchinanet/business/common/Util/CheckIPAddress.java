package com.br.tvlicai.firstchinanet.business.common.Util;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

import groovy.util.logging.Slf4j;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class CheckIPAddress {

	private static Logger log = LoggerFactory.getLogger(CheckIPAddress.class);
	
    /**
     * 判断是否在某段IP之间
     * @param ipStart  开始IP
     * @param ipEnd    结束IP
     * @param ipToCheck    需要比较IP
     * @return 是否属于局域网ip
     */
    public static boolean  isValidRange(String ipStart,String ipEnd,String ipToCheck) throws Exception{

        try {
            long ipLo = ipTolong(InetAddress.getByName(ipStart));
            long ipHi = ipTolong(InetAddress.getByName(ipEnd));
            long ipcheck = ipTolong(InetAddress.getByName(ipToCheck));
            return (ipcheck >ipLo && ipcheck < ipHi);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 将IP地址转换为长整型
     * @param ip
     * @return
     * @throws Exception
     */
    private static long ipTolong(InetAddress ip) throws Exception{
        long result = 0;
        byte[] ipAdds=ip.getAddress();
        for (byte b : ipAdds) {
            result <<= 8;
            result |= b&0xff;
        }
        return result;
    }

    /**
     * 判断是否在之间
     * @param ipAddress  检查的IP
     * @return 是否属于局域网ip
     *
     * 局域网的IP地址为三类，如下：
     * A类：10.0.0.0-10.255.255.255
     * B类：172.16.0.0-172.31.255.255
     * C类：192.168.0.0-192.168.255.255
     */
    public static boolean isLocalAddress(String ipAddress) throws Exception
    {
        return isValidRange("10.0.0.0","10.255.255.255",ipAddress)||
                isValidRange("172.16.0.0","172.31.255.255",ipAddress)||
                isValidRange("192.168.0.0","192.168.255.255",ipAddress)||
                ipAddress.equals("127.0.0.1");
    }
    // 获取ip地址，通过外部服务器返回，不使用本地网卡返回
    public static IPInfoEntity getRemoteAddress() throws Exception
    {
        StringBuilder ipJsonString = new StringBuilder("");
        Scanner scanner = null;
        IPInfoEntity ipInfo=null;
        try {
            scanner = new Scanner(new URL("http://pv.sohu.com/cityjson").openConnection().getInputStream(),
                    "gbk");
            while (scanner.hasNext()) {
                ipJsonString.append(scanner.next());
            }
            String str=ipJsonString.toString().split("=")[1];
            str=str.substring(0,str.length()-1);
            ipInfo = JSON.parseObject(str, IPInfoEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("获取远程地址异常："+e.getMessage());
        }
        return ipInfo;
    }
    
    public static String getIp() {
    	Map<String,Integer> map = new HashMap<>();
    	
    	String ip = ifconfigCo();
    	if(!StringUtils.isEmpty(ip)) {
        	if(map.containsKey(ip)) {
        		map.put(ip, map.get(ip)+1);
        	}else {
        		map.put(ip, 1);
        	}
    	}

    	ip = ifconfigMe();
    	if(!StringUtils.isEmpty(ip)) {
         	if(map.containsKey(ip)) {
         		map.put(ip, map.get(ip)+1);
         	}else {
         		map.put(ip, 1);
         	}
    	}
     	
    	ip = httpbin();
    	if(!StringUtils.isEmpty(ip)) {
         	if(map.containsKey(ip)) {
         		map.put(ip, map.get(ip)+1);
         	}else {
         		map.put(ip, 1);
         	}
    	}
     	
    	ip = qqwry();
    	if(!StringUtils.isEmpty(ip)) {
         	if(map.containsKey(ip)) {
         		map.put(ip, map.get(ip)+1);
         	}else {
         		map.put(ip, 1);
         	}
    	}
     	
    	ip = ipify();
    	if(!StringUtils.isEmpty(ip)) {
         	if(map.containsKey(ip)) {
         		map.put(ip, map.get(ip)+1);
         	}else {
         		map.put(ip, 1);
         	}
    	}

    	String key = getMaxValue(map);
    	return key;
    }
    
    /**
    * 求Map<K,V>中Value(值)的最大值
    * @param map
    * @return
    */
    public static String getMaxValue(Map<String, Integer> map) {
    	List<Map.Entry<String,Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));
        
        return list.get(0).getKey();
    }
    
    /**
     * ifconfig.co网址
     * @return
     */
    public static String ifconfigCo() {
        String inputLine = "";  
        String read = "";  
        String ip = "";  
        try {  
            URL url = new URL("https://ifconfig.co/ip");  
            HttpURLConnection urlConnection = (HttpURLConnection) url  
                    .openConnection();  
            urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000); 
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    urlConnection.getInputStream()));  
            while ((read = in.readLine()) != null) {  
                inputLine += read;  
            } 
            ip = inputLine;
            if(isLocalAddress(ip)) {
            	return "";
            }else {
            	return ip;
            }
        } catch(SocketTimeoutException se) {
	    	log.debug("ifconfigCo请求超时");  
	    	return "";
	    } catch (Exception e) {  
	    	log.debug("ifconfigMe请求异常：{}",e.getMessage());   
	        return "";
	    }
    }

    /**
     * ifconfig.me网址
     * @return
     */
    public static String ifconfigMe() {
        String inputLine = "";  
        String read = "";  
        String ip = "";  
        try {  
            URL url = new URL("http://ifconfig.me/ip");  
            HttpURLConnection urlConnection = (HttpURLConnection) url  
                    .openConnection();  
            urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000); 
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    urlConnection.getInputStream()));  
            while ((read = in.readLine()) != null) {  
                inputLine += read;  
            } 
            ip = inputLine;
            if(isLocalAddress(ip)) {
            	return "";
            }else {
            	return ip;
            }
        } catch(SocketTimeoutException se) {
	    	log.debug("ifconfigMe请求超时");  
	    	return "";
	    } catch (Exception e) {  
	    	log.debug("ifconfigMe请求异常：{}",e.getMessage());   
	        return "";
	    }
    }
    
    /**
     * httpbin网址
     * @return
     */
    public static String httpbin() {
        String inputLine = "";  
        String read = "";  
        String ip = "";  
        try {  
            URL url = new URL("https://httpbin.org/ip");  
            HttpURLConnection urlConnection = (HttpURLConnection) url  
                    .openConnection();  
            urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000); 
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    urlConnection.getInputStream()));  
            while ((read = in.readLine()) != null) {  
                inputLine += read;  
            } 
            JSONObject json = JSONObject.fromObject(inputLine);
            log.debug(json.toString());
            ip = json.getString("origin");
            if(isLocalAddress(ip)) {
            	return "";
            }else {
            	return ip;
            }
        } catch(SocketTimeoutException se) {
	    	log.debug("httpbin请求超时");  
	    	return "";
	    } catch (Exception e) {  
	    	log.debug("httpbin请求异常：{}",e.getMessage());   
	        return "";
	    }
    }
    
    
    /**
     * qqwry网址
     * @return
     */
    public static String qqwry() {
        String inputLine = "";  
        String read = "";  
        String ip = "";  
        try {  
            URL url = new URL("https://api.ttt.sh/ip/qqwry/");  
            HttpURLConnection urlConnection = (HttpURLConnection) url  
                    .openConnection();  
            urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000); 
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    urlConnection.getInputStream()));  
            while ((read = in.readLine()) != null) {  
                inputLine += read;  
            } 
            JSONObject json = JSONObject.fromObject(inputLine);
            log.debug(json.toString());
            ip = json.getString("ip");
            if(isLocalAddress(ip)) {
            	return "";
            }else {
            	return ip;
            }
	    } catch(SocketTimeoutException se) {
	    	log.debug("qqwry请求超时");  
	    	return "";
	    } catch (Exception e) {  
	    	log.debug("qqwry请求异常：{}",e.getMessage());   
	        return "";
	    } 
    }
    
    /**
     * ipify网址
     * @return
     */
    public static String ipify() {
        String inputLine = "";  
        String read = "";  
        String ip = "";  
        try {  
            URL url = new URL("https://api.ipify.org/");  
            HttpURLConnection urlConnection = (HttpURLConnection) url  
                    .openConnection();  
            urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000); 
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    urlConnection.getInputStream()));  
            while ((read = in.readLine()) != null) {  
                inputLine += read;  
            } 
            ip = inputLine;
            if(isLocalAddress(ip)) {
            	return "";
            }else {
            	return ip;
            }
        } catch(SocketTimeoutException se) {
        	log.debug("ipify请求超时");  
        	return "";
        } catch (Exception e) {  
        	log.debug("ipify请求异常：{}",e.getMessage());   
            return "";
        } 
    }
    	
    public static void main(String[] args) {
		try {
			System.out.print(getIp());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
