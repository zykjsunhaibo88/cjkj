package com.br.tvlicai.firstchinanet.business.common.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ldc
 * @version 1.0, 02/17/2017
 * @see
 * @since JDK 1.7
 */
public class WeatherUtil {

    /**
     * 获取SOAP的请求头，并替换其中的标志符号为用户输入的城市
     *
     * @param city
     *            用户输入的城市名称
     * @return 客户将要发送给服务器的SOAP请求
     */
    private static String getSoapRequest(String city) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>    <getWeather xmlns=\"http://WebXml.com.cn/\">"
                + "<theCityCode>" + city
                + "</theCityCode>    </getWeather>"
                + "</soap:Body></soap:Envelope>");
        return sb.toString();
    }

    /**
     * 用户把SOAP请求发送给服务器端，并返回服务器点返回的输入流
     *
     * @param city
     *            用户输入的城市名称
     * @return 服务器端返回的输入流，供客户端读取
     * @throws Exception
     */
    private static InputStream getSoapInputStream(String city) throws Exception {
        try {
            String soap = getSoapRequest(city);
            if (soap == null) {
                return null;
            }
            URL url = new URL(
                    "http://www.webxml.com.cn/WebServices/WeatherWS.asmx");
            URLConnection conn = url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", Integer.toString(soap
                    .length()));
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setRequestProperty("SOAPAction",
                    "http://WebXml.com.cn/getWeather");

            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            osw.write(soap);
            osw.flush();
            osw.close();

            InputStream is = conn.getInputStream();
            return is;
        } catch (Exception e) {
            System.out.print("error");;
            return null;
        }
    }

    /**
     * 对服务器端返回的XML进行解析
     *
     * @param city
     *            用户输入的城市名称
     * @return 字符串 用#分割
     */
    public static List<String> getWeather(String city) {
        try {
            Document doc;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream is = getSoapInputStream(city);
            if(is !=null){
	            doc = db.parse(is);
	            NodeList nl = doc.getElementsByTagName("string");
	            StringBuffer sb = new StringBuffer();
	            List<String> list = new ArrayList<>();
	            for (int count = 0; count < nl.getLength(); count++) {
	                Node n = nl.item(count);
	                if(n.getFirstChild().getNodeValue().equals("查询结果为空！")) {
	                    sb = new StringBuffer("#") ;
	                    break ;
	                }
	                list.add(n.getFirstChild().getNodeValue());
	//                sb.append(n.getFirstChild().getNodeValue() + "#");
	            }
	            is.close();
	            return list;
            }else{
            	return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getWeatherStr(String city){
        StringBuffer sb = new StringBuffer();
        List<String> list = getWeather(city);

        if (list != null && list.size() > 1){
            String weather7 = list.get(7);
            weather7.substring(weather7.indexOf(" "),weather7.length());
            sb.append(city + " ");
            sb.append(weather7.substring(weather7.indexOf(" "),weather7.length()) + " ");//晴
            sb.append(list.get(8) +" ");// 温度
            sb.append(list.get(9) +" ");// 风力
            return sb.toString();
        }else{
            return "";
        }
    }
}
