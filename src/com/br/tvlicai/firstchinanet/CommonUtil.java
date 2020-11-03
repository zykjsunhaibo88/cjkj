package com.br.tvlicai.firstchinanet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

import com.br.tvlicai.firstchinanet.business.common.Util.Util;
import com.neusoft.udolink.DBPersistenceManager;

import sun.misc.BASE64Encoder;
/**
 * 公用
 *
 * @param request
 * @return
 * @throws Exception
 */
public class CommonUtil {
	 public static String[] chars = new String[] {  "0", "1", "2", "3", "4", "5","6", "7", "8", "9" };
	 
	 public static String generateShortUUID() {
	     StringBuffer shortBuffer = new StringBuffer();
	     String uuid = UUID.randomUUID().toString().replace("-", "");
	     for (int i = 0; i < 8; i++) {
	         String str = uuid.substring(i * 4, i * 4 + 4);
	         int x = Integer.parseInt(str, 16);
	         shortBuffer.append(chars[x % 0xA]);
	     }
	     return shortBuffer.toString();
	 }
	/**
	 * 获取18位UUID：yMMddHHmmssSSS + 四位轮询的随机数
	 */
	private static AtomicLong seq = new AtomicLong(999);
	public static String getUuid(){
		
		StringBuffer bf = new StringBuffer();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		bf.append(sdFormat.format(new Date()).substring(3));
		if(seq.getAndIncrement() >= 10000){
			seq.set(999);
		}
		return bf.append(seq.getAndIncrement()).toString();
		
	 }
	
	public static   ConcurrentHashMap hp=new ConcurrentHashMap();
	public static DBPersistenceManager _pm=null;
	
	
	public static boolean isNull(String str){
		
		return ((str == null) || (str.trim().equals("")) || (str.equalsIgnoreCase("null")));
	    
	}
	
	
	//获取系统当前时间（年/月/日）
	public static String getSystemDate(){
		//取系统当前时间
		Date date = new Date(); 
		String systemDate = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
		
		return systemDate;
	}
	// 获取4位置随机数
	public static String getRandom4() {
		
		Random random = new Random();
		String result = "";
		
		for (int i = 0; i < 4; i++) {
			result += random.nextInt(10);
		}
		
		return result;
	}
	  /**
			 * 返回结果信息设置
			 * 
			 * @param data 返回数据
			 * @param code 结果Code
			 * @param message 返回消息
			 * @return
			 */
			public static Map<String, Object> setResultInfo(Object data, Object code, String message, Logger log) {
				if ("exception".equals(message)) {
					message = "exception_display_content" + " : " + "[" + getSystemTime4() + "]";
				}
				
				Map<String, Object> result = new HashMap();
				result.put("data", data);
				result.put("errorcode", code);
				result.put("message", message);
				
				return result;
			}
			//获取系统当前时间（年/月/日）
			public static String getSystemTime4(){
				//取系统当前时间
				Date date = new Date(); 
				String systemDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS")).format(date);
				
				return systemDate;
			}
			/**
			 * null转0
			 * @param str
			 * @return
			 */
			public static String nullTopie(String str) {
				if (isNull(str)) {
					return "''";
				}
				return str;
			}
					
			
			/**
		     * 将inputstream转为Base64
		     * 
		     * @param is
		     * @return
		     * @throws Exception
		     */
		    public static String getBase64FromInputStream(InputStream is) throws Exception {
		        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		        byte[] data = null;

		        // 读取图片字节数组
		        try {
		            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		            byte[] buff = new byte[100];
		            int rc = 0;
		            while ((rc = is.read(buff, 0, 100)) > 0) {
		                swapStream.write(buff, 0, rc);
		            }
		            data = swapStream.toByteArray();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            if (is != null) {
		                try {
		                    is.close();
		                } catch (IOException e) {
		                    throw new Exception("输入流关闭异常");
		                }
		            }
		        }
		        
		        BASE64Encoder encoder = new BASE64Encoder();
	        
		        return encoder.encode(data);

		    }
		    
		    
		    
 public static String getbaidukak() {
		    /**鲁斌*/ 
		    String BAIDU_AK = "nrWVaLzg4APuvhgFm4x5NDG2";
		    Calendar calendar = Calendar.getInstance();
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			if(hour < 2){
				/**李圣标*/ 
				BAIDU_AK = "wsH9j0w5O0njwi1diPpIQPm5P6dGQhUk";
			}else if(hour < 3){
				/**许星*/ 
				BAIDU_AK = "ncs6o6l0i8FwMUipN9O1SndDN2IACP5M";
			}else if(hour < 4){
				/**马峰*/ 
				BAIDU_AK = "t2ywZKykoPokAgsRZZ8fuB6IePSRh3YS";
			}else if(hour < 5){
				/**胡林芳*/ 
				BAIDU_AK = "UrCQDNzDNHqhPfjz6wddaQ7O6tcozIGQ";
			}
			return BAIDU_AK;
   }
 
 
 
 
    public static String glnrsjTonow(Date timeString){
     
     String systemDate = "";
	systemDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(timeString);
     return systemDate;
  }
    public static boolean isNumericzidai(String str) {
    	Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
    	Matcher isNum = pattern.matcher(str);
    	if (!isNum.matches()) {
    	return false;
    	}
    	return true;
    	}
    
    
    public static Date strToDateLong(String strDate) {
    	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
    	    ParsePosition pos = new ParsePosition(0);
    	   Date strtodate = formatter.parse(strDate, pos);
    	   return strtodate;
    	 }
    
    
    public static String strToZero(String youNumber) {  
        // 0 代表前面补充0  
        // 1 代表长度为1 
        // d 代表参数为正数型  
    	if(Util.isCon(youNumber)){
    		
    	}else{
    		youNumber="0";
    	}
    	
        String str = String.format("%01d", Integer.parseInt(youNumber));  
        //System.out.println(str); // 0001  
        return str;
      }  
    
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if(file.exists()) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if(!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if(!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }
	//获取系统当前时间（年/月/日）
	public static String getSystemTime(){
		//取系统当前时间
		Date date = new Date(); 
		String systemDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
		
		return systemDate;
	}
	
	//获取系统当前时间（年/月/日）
		public static String getSystemDate3(Date date){
			String systemDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(date);
			return systemDate;
		}
}

