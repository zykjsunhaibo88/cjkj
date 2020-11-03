package com.br.tvlicai.firstchinanet.web.controller.uipath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.br.tvlicai.firstchinanet.CommonUtil;
import com.br.tvlicai.firstchinanet.app.controller.base.BaseController;
import com.br.tvlicai.firstchinanet.business.common.Util.Util;
import com.br.tvlicai.firstchinanet.commom.Const.Const;

/*import com.br.tvlicai.firstchinanet.commom.utils.DateUtil;*/

import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.drm.DrmException;
import com.neusoft.drm.dataexpress.DataSet;
import com.neusoft.unieap.util.DBAccessHelper;

import javafx.scene.chart.XYChart.Series;
import net.sf.json.JSON;


//北上资金累计净买入
@Controller
@RequestMapping("")
public class NorthFundNetBuyController extends BaseController{
	 static final Logger _LOG = LoggerFactory.getLogger(NorthFundNetBuyController.class);
		String checkTableSql="show tables like 'uip_northfundnetbuy'";
	 
	 /**
		 * 北上资金累计净买入
		 * 功能描述:通过request的方式来获取到json数据<br/>
		 * @param jsonobject 这个是阿里的 fastjson对象
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/northFundNetBuy/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		public String northFundNetBuyList(HttpServletRequest request, HttpSession session) {
		    // 直接将json信息打印出来
			String page=(String)request.getParameter("page");
		    if(CommonUtil.isNull(page))
		    {
		    	page="1";
		    }
		    int pageSize=15;
		    int pageNo=(Integer.parseInt(page)-1)*pageSize; 
		    
		    JSONArray rowA = new JSONArray();
		    int count=0;
		    int i=0;
		    DBPersistenceManager pm=null;
			pm=DBAccessHelper.getPMByName(Const.DataSourceName);
	        try {
	        	//查询数据库中是否包含uip_northfundnetbuy 这个表
				DataSet checkTableDs = (DataSet) pm.executeQuery(checkTableSql);
				
			    if(checkTableDs!=null&&checkTableDs.next()){
	        	
					//北上资金累计净买入的表
					String checkconfTableSql="select * from uip_northfundnetbuy";
		        	DataSet checkconfTableDs = (DataSet) pm.executeQuery(checkconfTableSql);
				
	       
			       if(checkconfTableDs!=null&&checkconfTableDs.next()){
			    	
				    /*	    String northNetBuyName=checkconfTableDs.getString("northNetBuyName");  
					        DataSet ds=(DataSet)pm.executeQuery(" select * from "+northNetBuyName); */    
					        if (checkconfTableDs!=null&&checkconfTableDs.next()) {
					        	count=checkconfTableDs.getRowCount();
				            }
					        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
					        DataSet dsdata=(DataSet)pm.executeQuery(" select * from uip_northfundnetbuy limit "+pageNo+","+pageSize);     
					        while (dsdata!=null&&dsdata.next()) {
					        	
					        	   JSONObject jsonObject = new JSONObject();
					               jsonObject.put("uipId", dsdata.getString("uip_id"));
					               
					               String tradeDate = dff.format(dff.parse(dsdata.getString("trade_date")));
					               
					               jsonObject.put("tradeDate", dsdata.getString("trade_date"));  
					               jsonObject.put("tradeDate", tradeDate);
					               jsonObject.put("cumulativeNetBuy", dsdata.getString("cumulative_net_buy"));
					               
					               rowA.add(i, jsonObject);
					               i++;
				            }
				    }
			    }

	        }catch (Exception e) {
	        	_LOG.error("UserCenterController1：异常  认证信息获取失败 Author：xh");
	        }finally{
	        	if(pm!=null){
	        		pm.close();
	        	}
	        }
	        
		    // 将获取的json数据封装一层，然后在给返回
		    JSONObject result = new JSONObject();
		    result.put("results", rowA);
		    result.put("links", "@ResponseBody");
		    result.put("count", count+"");

		    return result.toJSONString();	
		}
		
		
		 //成交统计（按交易方向）北向资金，统计半年
		   //北上资金累计净买入（截至到当天日期）                 Excel导入
			@ResponseBody
			@RequestMapping(value="/northFundNetBuy/createTable_bszjljjmr/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
			public String createTable_bszjljjmr(HttpServletRequest request, HttpSession session,@RequestParam(value = "file")MultipartFile file){

				//获取Excel
		        File newFile = new File(file.getOriginalFilename());//获取文件名及后缀
		        String fileName = newFile.getName();
		        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		        
		        String datestr;    //日期字符串      
		        String ljjsr;   //累计净收入（北上合计）
		        
		        DBPersistenceManager pm=null;
		        pm=DBAccessHelper.getPMByName(Const.DataSourceName);
		        FileInputStream fis =null;
	            Workbook workbook = null;

	            
		        try {
		        	
		        	    //创建一个表  （导入前 需要数据库 创建一个表）    开始
				    	String tableName="uiP_northFundNetBuy";
				        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  				        
				        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");  

					    String oldTableDateStr = this.addDate(new Date(),-1);	//当前日期减去 1天 是老表名后边的日期	
					    oldTableDateStr=formatter.format(formatter2.parse(oldTableDateStr));
						System.out.println("newDateStr:"+oldTableDateStr);
				        
				    	
				    	String oldTableName=tableName+oldTableDateStr;

						//判断是否存在 uiP_northFundNetBuy，存在就把这个表的表名改为  oldTableName   （uiP_northFundNetBuy + 当前日期减一天）
						DataSet checkTableDs = (DataSet) pm.executeQuery(checkTableSql);
						
					    if(checkTableDs!=null&&checkTableDs.next()){
							String checkOldTableSql="show tables like '"+oldTableName+"'";
							DataSet oldTableNameDs = (DataSet) pm.executeQuery(checkOldTableSql);
						    if(oldTableNameDs!=null&&oldTableNameDs.next()){		
						    	 String dropOldTableSql="DROP TABLE IF EXISTS "+oldTableName;
						         pm.executeUpdate(dropOldTableSql.toString());
						    }
					    	 String updateSql="rename table "+tableName+" to "+oldTableName+"";  
					    	 pm.executeUpdate(updateSql);  	//把上一个表的表名进行修改（改为拼接日期的）
	                     }
                	    //之前没有这个表，则直接新增这个表
                	    String addTableSql="create table "+tableName+"  (`uip_id` int(11) NOT NULL AUTO_INCREMENT, trade_date datetime DEFAULT NULL COMMENT '日期', `cumulative_net_buy` varchar(36) DEFAULT NULL COMMENT '累计净买入',PRIMARY KEY (`uip_id`))";
					    pm.executeUpdate(addTableSql);  //新增一个新表	
					    
						
			    //创建一个表  （导入前 需要数据库 创建一个表）    结束
	   
				////////////////////////////////  往新建的表中填入 excel中的数据     //////////////////////////////////////////////////////////////////////////////////
		              file.transferTo(newFile);
		            //获取一个绝对地址的流
		              fis = new FileInputStream(newFile);
		              //workbook = new XSSFWorkbook(fis);//得到工作簿
		              Workbook wb = null;
		              if ("xls".equals(suffix)) {
		            	  workbook = new HSSFWorkbook(fis);

		              } else if ("xlsx".equals(suffix)) {
		            	  workbook = new XSSFWorkbook(fis);

		              } else {
		                  // 无效后缀名称，这里之能保证excel的后缀名称，不能保证文件类型正确，不过没关系，在创建Workbook的时候会校验文件格式
		                  throw new IllegalArgumentException("Invalid excel version");
		              }
		            	              
		              // 获取第一个sheet页（有可能一个excel有多个选项卡，即多个excel文件，咱们取第一个文件）
		              Sheet sheet = workbook.getSheetAt(0); 
		              
		           // 循环excel文件中的每一行
		              for (int i = 2; i < sheet.getLastRowNum()-1; i++) { // i从1开始，表示不读取表头
		                  // 获得单元格的对象，然后获取单元格的值，并设置到对象中
		            	   System.out.println(i);//日号与星期
		            	  Row row;
		            	  Cell cellje = null;  
		            	  Cell cellrq = null;  
		                  row = sheet.getRow(i);
	                      cellrq = row.getCell(0); //日期
	                      cellje = row.getCell(5); //北向合计  累计净买入
	                      
	                      String date2=null;
	                      if(DateUtil.isCellDateFormatted(row.getCell(0))){// 判断单元格是否属于日期格式  
	                          Date date1 = row.getCell(0).getDateCellValue();
	                           SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
	                            date2 = dff.format(date1);   //日期转化                          
		                   }
	                      
	                      System.out.println(cellje.toString()+","+date2);//日号与星期
		               
		                  //插入数据
	                      String addDataToTableSql="insert into "+tableName+" (trade_date,cumulative_net_buy) VALUES ('"+date2+"',"+cellje.toString()+")";
	                      pm.executeUpdate(addDataToTableSql);  //添加数据             
		              } 
		            
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		
				}finally{
				  	if(pm!=null){
		        		pm.close();
		        	}
				}   
				
				System.out.println("createTable_bszjljjmr");
		        return null;
			}
			
			
			//导出 Excel  outToExcel_bszjljjmr
		   @RequestMapping(value="/northFundNetBuy/outToExcel_bszjljjmr/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		   public ModelAndView outToExcel_bszjljjmr(HttpServletResponse response) throws IOException {
				
			   //创建HSSFWorkbook对象(excel的文档对象)
		        HSSFWorkbook wb = new HSSFWorkbook();
		      //建立新的sheet对象（excel的表单）
		        HSSFSheet sheet=wb.createSheet("sheet1");
		      //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		        HSSFRow row1=sheet.createRow(0);
		      //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		        HSSFCell cell=row1.createCell(0);			
		      //在sheet里创建第一行
		        HSSFRow row2=sheet.createRow(0);
		        //创建单元格并设置单元格内容
		        row2.createCell(0).setCellValue("日期");
		        row2.createCell(1).setCellValue("累计净收入");

		    	//北上资金累计净买入的表名(最新导入表的表名)  配置最新表的表名  uip_confTableName
			    DBPersistenceManager pm=DBAccessHelper.getPMByName(Const.DataSourceName);


	        	
				try {
				
					DataSet checkTableDs = (DataSet) pm.executeQuery(checkTableSql);
					
				    if(checkTableDs!=null&&checkTableDs.next()){
    
					     DataSet ds=(DataSet)pm.executeQuery(" select * from uip_northfundnetbuy");   		     
					     SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
					     int i=1;
				         sheet.setColumnWidth((short) 0, (short) 5000);  //设置列的宽度
				         
					     while (ds!=null&&ds.next()) {
					    	    HSSFRow row=sheet.createRow(i);
			
					    	    sheet.setColumnWidth((short) i, (short) 5000);  //设置列的宽度
					    	    String tradeDate=null;
					    	    try {
									 tradeDate = dff.format(dff.parse(ds.getString("trade_date"))); //日期						
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					    		String cumulativeNetBuy=ds.getString("cumulative_net_buy");  //累计净收入

			                    BigDecimal celljeBd=new BigDecimal(cumulativeNetBuy);
			                    cumulativeNetBuy=celljeBd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();   ////北向合计  累计净买入

						         HSSFRichTextString richString = new HSSFRichTextString(tradeDate); //textValue是要设置大小的单元格数据
						         HSSFRichTextString richString2 = new HSSFRichTextString(cumulativeNetBuy); //textValue是要设置大小的单元格数据
						         
			                    HSSFFont font = wb.createFont();
			                    font.setColor(HSSFColor.BLACK.index);//设置excel数据字体颜色
			                    font.setFontName("黑体");
			                    font.setFontHeightInPoints((short) 11);//设置excel数据字体大小
			                    //Arial
			                    richString.applyFont(font);
			                    richString2.applyFont(font);
			                    row.createCell(0).setCellValue(richString);
			                    row.createCell(1).setCellValue(richString2);
					            i++;
					     }
					     
					     //输出Excel文件
					        OutputStream output=response.getOutputStream();
					        response.reset();
					     //   response.setHeader("Content-disposition", "attachment; filename=成交统计(按交易方向).xlsx");
					        String ExcelName="北上资金累计净买入.xlsx";
					 
					        response.setHeader("Content-disposition", "attachment; filename="+toUtf8String(ExcelName));
					        response.setContentType("application/msexcel");
					        wb.write(output);
					        output.close();
				    }
				    
				} catch (DrmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     
			   System.out.println("northFundNetBuy");
			   
			    return new ModelAndView("/uiPath/northFundNetBuy");
			   //return new ModelAndView(new RedirectView("/northFundNetBuy/"));
			}
		   
		   //折线图
			//导出 Excel  outToExcel_bszjljjmr
		/*   @RequestMapping(value="/northFundNetBuy/lineChart/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		   public void lineChart(HttpServletResponse response,String time) throws ParseException{
	            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	            EcOrderCount ecOrderCounts=ecOrderStatisticsService.getCountsByTime(format1.parse(time));
	            //图例
	            List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "全部订单","支付订单"}));
	            //横坐标的值
	            List<String> axis = new ArrayList<String>(
	                    Arrays.asList(new String[] {"1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00"}));
	            //纵坐标的值（每个时间段对应的量）
	            List<Series> series = new ArrayList<Series>();
	            series.add(new Series());
	            series.add(new Series("支付订单","line",new ArrayList<Integer>(Arrays.asList(ecOrderCounts.getPaycount()))));
	            Echarts echarts = new Echarts(legend, axis, series);
	            response.setContentType("text/html;charset=utf-8");
	            PrintWriter out;
	            try{
	                out = response.getWriter();
	                String jsonstr = JSON.toJSONString(echarts);
	                System.out.println("str:"+jsonstr);
	                out.write(jsonstr);
	                out.flush();
	                out.close();
	            }catch(IOException e) {
	                e.printStackTrace();
	            }
	        }*/

		   
		   
		   
		   
		   
			//解决中文的乱码
			public static String toUtf8String(String s){ 
			     StringBuffer sb = new StringBuffer(); 
			       for (int i=0;i<s.length();i++){ 
			          char c = s.charAt(i); 
			          if (c >= 0 && c <= 255){sb.append(c);} 
			        else{ 
			        byte[] b; 
			         try { b = Character.toString(c).getBytes("utf-8");} 
			         catch (Exception ex) { 
			             System.out.println(ex); 
			                  b = new byte[0]; 
			         } 
			            for (int j = 0; j < b.length; j++) { 
			             int k = b[j]; 
			              if (k < 0) k += 256; 
			              sb.append("%" + Integer.toHexString(k).toUpperCase()); 
			              } 
			     } 
			  } 
			  return sb.toString(); 
			}
			
			
		    /**
		     * 日期加天数
		     *
		     * @param date
		     *            日期
		     * @param day
		     *            天数
		     * @return 返回相加后的日期
		     */
		    public static String addDate(java.util.Date date, int day) {
		        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		        java.util.Calendar c = java.util.Calendar.getInstance();
		        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);

		        return dft.format(c.getTime());
		    }
		    
		    /**
		     * 返回毫秒
		     *
		     * @param date
		     *            日期
		     * @return 返回毫秒
		     */
		    public static long getMillis(java.util.Date date) {
		        java.util.Calendar c = java.util.Calendar.getInstance();
		        c.setTime(date);
		        return c.getTimeInMillis();
		    }
}
