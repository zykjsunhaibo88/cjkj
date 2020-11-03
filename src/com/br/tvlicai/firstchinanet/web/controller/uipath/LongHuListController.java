package com.br.tvlicai.firstchinanet.web.controller.uipath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
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
import org.apache.velocity.texen.util.FileUtil;
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


//北上资金累计净买入
@Controller
@RequestMapping("")
public class LongHuListController extends BaseController{
	 static final Logger _LOG = LoggerFactory.getLogger(LongHuListController.class);
		String checkTableSql="show tables like 'uip_longHuList'";


	 
		/**
		 * 龙虎榜机构席位跟踪
		 * 功能描述:通过request的方式来获取到json数据<br/>
		 * @param jsonobject 这个是阿里的 fastjson对象
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/longHuList/", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=UTF-8")
		public String longHuList(HttpServletRequest request, HttpSession session) {

			  // 直接将json信息打印出来
			String page=(String)request.getParameter("page");
		    if(CommonUtil.isNull(page))
		    {
		    	page="1";
		    }
		      int pageSize=100;
		    int pageNo=(Integer.parseInt(page)-1)*pageSize; 
		    
		    JSONArray rowA = new JSONArray();
		    int count=0;
		    int i=0;
		    DBPersistenceManager pm=null;
			pm=DBAccessHelper.getPMByName(Const.DataSourceName);


		        try {
		        	//查询数据库中是否包含uip_longHuList这个表
		    	
					DataSet checkTableDs = (DataSet) pm.executeQuery(checkTableSql);
					
				    if(checkTableDs!=null&&checkTableDs.next()){
		        	
		        	
						//龙虎榜机构席位跟踪
						String checkconfTableSql="select * from uip_longHuList";
			        	DataSet checkconfTableDs = (DataSet) pm.executeQuery(checkconfTableSql);
						
			       
					    if(checkconfTableDs!=null&&checkconfTableDs.next()){
					    	
					   /* 	    String northNetBuyName=checkconfTableDs.getString("northNetBuyName");  
						        DataSet ds=(DataSet)pm.executeQuery(" select * from "+northNetBuyName); */    
					    	
						        if (checkconfTableDs!=null&&checkconfTableDs.next()) {
						        	count=checkconfTableDs.getRowCount();
					            }
						        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
						        DataSet dsdata=(DataSet)pm.executeQuery(" select * from uip_longHuList limit "+pageNo+","+pageSize);     
						        while (dsdata!=null&&dsdata.next()) {
		        	
						        	   JSONObject jsonObject = new JSONObject();
						        	       jsonObject.put("uipId", i+1);	
							               jsonObject.put("securitiesCode", dsdata.getString("securities_code"));  //证券代码
							               jsonObject.put("securitiesName", dsdata.getString("securities_name"));  //证券名称
							               jsonObject.put("belongDongcai", dsdata.getString("belong_dongcai")); //所属东财行业指数[行业类别]
							            	  							            
							               jsonObject.put("longHuBuy", dsdata.getString("longHu_buy"));  //龙虎榜买入额
							               jsonObject.put("longHuSell", dsdata.getString("longHu_sell"));  //龙虎榜卖出额
							               jsonObject.put("longHuNet", dsdata.getString("longHu_net"));  //龙虎榜净额
							               
							               jsonObject.put("institutionalSeatBuy", dsdata.getString("institutionalSeat_buy"));  //机构席位龙虎榜买入额
							               jsonObject.put("institutionalSeatBuyCount", dsdata.getString("institutionalSeat_buy_count"));  //机构席位买入次数
							               jsonObject.put("institutionalSeatSell", dsdata.getString("institutionalSeat_sell"));  //机构席位龙虎榜卖出额
							               jsonObject.put("institutionalSeatSellCount", dsdata.getString("institutionalSeat_sell_count"));  //机构席位卖出次数
							               jsonObject.put("institutionalSeatNet", dsdata.getString("institutional_seat_net"));  //机构席位龙虎榜净额
							               
							               jsonObject.put("securitiesAbnormalMove", dsdata.getString("securities_abnormalMove"));  //股票异动类型
							               jsonObject.put("lushenSeatLonghuBuy", dsdata.getString("lushenSeat_longhu_Buy"));  //沪（深）股通席位龙虎榜买入额
							               jsonObject.put("lushenSeatLonghuSell", dsdata.getString("lushenSeat_longhu_sell"));  //沪(深)股通席位龙虎榜卖出额
							               jsonObject.put("lushenSeatLonghuNet", dsdata.getString("lushenSeat_longhu_net"));  //沪(深)股通席位龙虎榜净额
							               jsonObject.put("onListCountToTwoweeks", dsdata.getString("onList_count_toTwoweeks"));  //上榜次数（截止日2周前
							               jsonObject.put("longHuBuyToTwoweeks", dsdata.getString("longHu_buy_toTwoweeks"));  //龙虎榜买入额（截止日2周前）
							               jsonObject.put("longHuSellToTwoweeks", dsdata.getString("longHu_sell_toTwoweeks"));  //龙虎榜卖出额（截止日2周前）
							               
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

		   //Excel导入
		@SuppressWarnings("null")
		@ResponseBody
		@RequestMapping(value="/importExp/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		public String importExp(HttpServletRequest request, HttpSession session,@RequestParam(value = "file")MultipartFile file){
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
				    	String tableName="uip_longHuList";
				        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  				        
				        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");  

					    String oldTableDateStr = this.addDate(new Date(),-1);	//当前日期减去 1天 是老表名后边的日期	
					    oldTableDateStr=formatter.format(formatter2.parse(oldTableDateStr));

				    	String oldTableName=tableName+oldTableDateStr;

						//判断是否存在 uiP_northFundNetBuy，存在就把这个表的表名改为  oldTableName   （uiP_northFundNetBuy + 当前日期减一天）				
						DataSet checkTableDs = (DataSet) pm.executeQuery(checkTableSql);						
					    if(checkTableDs!=null&&checkTableDs.next()){
							String checkOldTableSql="show tables like  '"+oldTableName+"'";
							DataSet oldTableNameDs = (DataSet) pm.executeQuery(checkOldTableSql);
						    if(oldTableNameDs!=null&&oldTableNameDs.next()){		
						    	 String dropOldTableSql="DROP TABLE IF EXISTS "+oldTableName;
						         pm.executeUpdate(dropOldTableSql.toString());
						    }
					    	 String updateSql="rename table "+tableName+" to "+oldTableName+"";  
					    	 pm.executeUpdate(updateSql);  	//把上一个表的表名进行修改（改为拼接日期的）
	                     }

	          	    String addTableSql="create table "+tableName+"  (`uip_id` int(11) NOT NULL AUTO_INCREMENT, "
	          	    	              +"`securities_code` varchar(36) DEFAULT NULL COMMENT '证券代码',"
	          	    	              +"`securities_name` varchar(36) DEFAULT NULL COMMENT '证券名称',"
	          	    	            +"`belong_dongcai` varchar(36) DEFAULT NULL COMMENT '所属东财行业指数[行业类别]',"
	          	    	              +"`longHu_buy` varchar(36) DEFAULT NULL COMMENT '龙虎榜买入额',"
	          	    	              +"`longHu_sell` varchar(36) DEFAULT NULL COMMENT '龙虎榜卖出额',"
	          	    	              +"`longHu_net` varchar(36) DEFAULT NULL COMMENT '龙虎榜净额',"
	          	    	              +"`institutionalSeat_buy` varchar(36) DEFAULT NULL COMMENT '机构席位龙虎榜买入额',"
	          	    	              +"`institutionalSeat_buy_count` varchar(36) DEFAULT NULL COMMENT '机构席位买入次数',"
			          	    	    +"`institutionalSeat_sell` varchar(36) DEFAULT NULL COMMENT '机构席位龙虎榜卖出额',"
			          	    	    +"`institutionalSeat_sell_count` varchar(36) DEFAULT NULL COMMENT '机构席位卖出次数',"
			          	    	    +"`institutional_seat_net` decimal(50,20) DEFAULT NULL COMMENT '机构席位龙虎榜净额',"         //排序字段
			          	    	    +"`securities_abnormalMove` text COMMENT '股票异动类型',"
			          	    	    +"`lushenSeat_longhu_Buy` varchar(50) DEFAULT NULL COMMENT '沪（深）股通席位龙虎榜买入额',"
			          	    	    +"`lushenSeat_longhu_sell` varchar(50) DEFAULT NULL COMMENT '沪(深)股通席位龙虎榜卖出额',"	          	    	    
		                           +"`lushenSeat_longhu_net` decimal(50,20) DEFAULT NULL COMMENT '沪(深)股通席位龙虎榜净额',"     //排序字段
		                           +"`onList_count_toTwoweeks` varchar(36) DEFAULT NULL COMMENT '上榜次数（截止日2周前）',"                           
		                           +"`longHu_buy_toTwoweeks` varchar(36) DEFAULT NULL COMMENT '龙虎榜买入额（截止日2周前）',"
		                           +"`longHu_sell_toTwoweeks` varchar(36) DEFAULT NULL COMMENT '龙虎榜卖出额（截止日2周前）',PRIMARY KEY (`uip_id`))"; 

						    pm.executeUpdate(addTableSql);  //新增一个新表	
					    
						
			    //创建一个表  （导入前 需要数据库 创建一个表）    结束
	   
				////////////////////////////////  往新建的表中填入 excel中的数据     //////////////////////////////////////////////////////////////////////////////////
		              file.transferTo(newFile);
		            //获取一个绝对地址的流
		              fis = new FileInputStream(newFile);
		             // workbook = new XSSFWorkbook(fis);//得到工作簿

		              Workbook wb = null;
		              if ("xls".equals(suffix)) {
		            	  workbook = new HSSFWorkbook(fis);

		              } else if ("xlsx".equals(suffix)) {
		            	  workbook = new XSSFWorkbook(fis);

		              } else {
		                  // 无效后缀名称，这里之能保证excel的后缀名称，不能保证文件类型正确，不过没关系，在创建Workbook的时候会校验文件格式
		                  throw new IllegalArgumentException("Invalid excel version");
		              }
		              
		            	              
		          	  HSSFCellStyle textStyle = (HSSFCellStyle) workbook.createCellStyle();
		        	  HSSFDataFormat format = (HSSFDataFormat) workbook.createDataFormat();
		              
		              // 获取第一个sheet页（有可能一个excel有多个选项卡，即多个excel文件，咱们取第一个文件）
		              Sheet sheet = workbook.getSheetAt(0); 
		              
		              System.out.println("个数："+sheet.getLastRowNum());
		              
		            /*  sheet.getRow(arg0*/
		              
		           // 循环excel文件中的每一行
		              for (int i = 1; i < sheet.getLastRowNum()-1; i++) { // i从1开始，表示不读取表头		                  

		              // 获得单元格的对象，然后获取单元格的值，并设置到对象中
		            	   System.out.println(i);//日号与星期
		            	  Row row=null;
		            	  row = sheet.getRow(i);

		            	  
		            	  /**设置单元格格式为文本格式*/
		      /*      	  HSSFCellStyle textStyle = workbook.createCellStyle();
		            	  HSSFDataFormat format = workBook.createDataFormat();*/
		            	  textStyle.setDataFormat(format.getFormat("@"));
		            	/*  cell.setCellStyle(textStyle);//设置单元格格式为"文本"
		            	  cell.setCellType(HSSFCell.CELL_TYPE_STRING); */
		            	  

		      
		            	  
		            	 
		            	  
		            	  String securitiesCode = row.getCell(0).toString(); //证券代码
		            	  String securitiesName = row.getCell(1).toString(); //证券名称
		            	  String belongDongcai = row.getCell(2).toString(); //所属东财行业指数[行业类别]
		            	  String longHuBuy = row.getCell(3).toString(); //龙虎榜买入额
		            	  String longHuSell = row.getCell(4).toString(); //龙虎榜卖出额		            	  
		            	  String longHuNet = row.getCell(5).toString(); //龙虎榜净额
		            	  String institutionalSeatBuy = row.getCell(6).toString(); //机构席位龙虎榜买入额
		            	  String institutionalSeatBuyCount = row.getCell(7).toString(); //机构席位买入次数
		            	  String institutionalSeatSell = row.getCell(8).toString(); //机构席位龙虎榜卖出额
		            	  String institutionalSeatSellCount = row.getCell(9).toString(); //机构席位卖出次数
		            	  String institutionalSeatNet = row.getCell(10).toString(); //机构席位龙虎榜净额     (排序字段)
		            	  String securitiesAbnormalMove = row.getCell(11).toString(); //股票异动类型
		            	  
		            	  
		            	  row.getCell(12).setCellStyle(textStyle);
		            	  row.getCell(12).setCellType(HSSFCell.CELL_TYPE_STRING);		            	  
		            	  row.getCell(13).setCellStyle(textStyle);
		            	  row.getCell(13).setCellType(HSSFCell.CELL_TYPE_STRING);		            	  
		            	  row.getCell(14).setCellStyle(textStyle);
		            	  row.getCell(14).setCellType(HSSFCell.CELL_TYPE_STRING);
		            	  
		            	  String lushenSeatLonghuBuy = row.getCell(12).toString(); //沪（深）股通席位龙虎榜买入额
		            	  String lushenSeatLonghuSell = row.getCell(13).toString(); //沪(深)股通席位龙虎榜卖出额
		            	  String lushenSeatLonghuNet = row.getCell(14).toString(); //沪(深)股通席位龙虎榜净额        (排序字段)

		            	  String onListCountToTwoweeks = row.getCell(15).toString(); //上榜次数（截止日2周前）
		            	  String longHuBuyToTwoweeks = row.getCell(16).toString(); //龙虎榜买入额（截止日2周前）
		            	  String longHuSellToTwoweeks = row.getCell(17).toString(); //龙虎榜卖出额（截止日2周前）
		            	  
		            	  //  jsonObject.put("belongDongcai", dsdata.getString("belong_dongcai"));  //所属东财行业指数[行业类别]
	  
		            	  
		            	  if(institutionalSeatNet.equals("——") || institutionalSeatNet ==null  || "".equals(institutionalSeatNet)){
		            		  institutionalSeatNet="0.0";
		            	  }
		            	  
		            	  if(lushenSeatLonghuNet.equals("——") || lushenSeatLonghuNet ==null  || "".equals(lushenSeatLonghuNet)){
		            		  lushenSeatLonghuNet="0.0";
		            	  }

		                  //插入数据
	                      String addDataToTableSql="insert into "+tableName+" (securities_code,securities_name,belong_dongcai,longHu_buy,longHu_sell,longHu_net,"
	                      		+ "institutionalSeat_buy,institutionalSeat_buy_count,institutionalSeat_sell,institutionalSeat_sell_count,institutional_seat_net,"
	                      		+ "securities_abnormalMove,lushenSeat_longhu_Buy,lushenSeat_longhu_sell,lushenSeat_longhu_net,onList_count_toTwoweeks,longHu_buy_toTwoweeks,"
	                      		+ "longHu_sell_toTwoweeks)"
	                      		+ "VALUES ('"+securitiesCode+"','"+securitiesName+"','"+belongDongcai+"','"+longHuBuy+"','"+longHuSell+"','"+longHuNet+"','"+institutionalSeatBuy+"','"	   
	                      		+institutionalSeatBuyCount+"','"+institutionalSeatSell+"','"+institutionalSeatSellCount+"','"+institutionalSeatNet+"','"+securitiesAbnormalMove+"','"+lushenSeatLonghuBuy+"','"
	                      		+lushenSeatLonghuSell+"','"+lushenSeatLonghuNet+"','"+onListCountToTwoweeks+"','"+longHuBuyToTwoweeks+"','"+longHuSellToTwoweeks+"')";
	
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
		     //   return new ModelAndView("/uiPath/longHuList");
		        return null;
			}
			
		//1、龙虎榜机构席位跟踪 的 导出Excel
		//导出 Excel 
		   @SuppressWarnings("null")
		   @ResponseBody
		   @RequestMapping(value="/LongHuList/outToExcel_institutionalSeatTrack/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		   public ModelAndView outToExcel_institutionalSeatTrack(HttpServletResponse response) throws IOException {
				
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
		        row2.createCell(0).setCellValue("序号");
		        row2.createCell(1).setCellValue("证券代码");
		        row2.createCell(2).setCellValue("证券名称");	
		        row2.createCell(3).setCellValue("买入额");			
		        row2.createCell(4).setCellValue("买入家数");			
		        row2.createCell(5).setCellValue("卖出额");			
		        row2.createCell(6).setCellValue("卖出家数");			
		        row2.createCell(7).setCellValue("机构席位龙虎榜净额");			
		        row2.createCell(8).setCellValue("股票异动类型");	
		        
		    	//北上资金累计净买入的表名(最新导入表的表名)  配置最新表的表名  uip_confTableName
			    DBPersistenceManager pm=DBAccessHelper.getPMByName(Const.DataSourceName);

				try {
					DataSet checkTableDs = (DataSet) pm.executeQuery(checkTableSql);						
				    if(checkTableDs!=null&&checkTableDs.next()){
					     DataSet ds=(DataSet)pm.executeQuery("select * from (select * from uip_longHuList where institutional_seat_net > 0 order by institutional_seat_net desc) a"
					     		+ " union all select * from (select * from uip_longHuList where institutional_seat_net < 0 order by institutional_seat_net desc) b");   		     
					    
					     
					     SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
					     int i=1;
				         sheet.setColumnWidth((short) 0, (short) 5000);  //设置列的宽度
				         
					     while (ds!=null&&ds.next()) {
					    	    HSSFRow row=sheet.createRow(i);
			
					    	    sheet.setColumnWidth((short) i, (short) 5000);  //设置列的宽度

					    	    String serialNum=String.valueOf(i);//序列号
					    		String securitiesCode=ds.getString("securities_code");  //证券代码
					    		String securitiesName=ds.getString("securities_name");  //证券名称
					    		String institutionalSeatBuy=ds.getString("institutionalSeat_buy");  //机构席位龙虎榜买入额	
					    		String institutionalSeatBuyCount=ds.getString("institutionalSeat_buy_count");  //机构席位买入次数(买入家数)
					    		String institutionalSeatSell=ds.getString("institutionalSeat_sell");  //机构席位龙虎榜卖出额
					    		String institutionalSeatSellCount=ds.getString("institutionalSeat_sell_count");  //机构席位卖出次数(卖出家数)
					    		String institutionalSeatNet=ds.getString("institutional_seat_net");  //机构席位龙虎榜净额					    		
					    		String securitiesAbnormalMove=ds.getString("securities_abnormalMove");  //股票异动类型
					    		
					    		//的证券（*） 为 空格
					    		//securitiesAbnormalMove
		                        if (securitiesAbnormalMove.indexOf("的证券(")!=-1){
		    			             //只要securitiesAbnormalMove.indexOf('的证券（')返回的值不是-1说明securitiesAbnormalMove字符串中包含字符串'的证券（'
		                        	securitiesAbnormalMove = securitiesAbnormalMove.replace("的证券","");		    			             
		    			         }
		                        if (securitiesAbnormalMove.indexOf("(")!=-1){
		                        	securitiesAbnormalMove=ClearBracket(securitiesAbnormalMove);
		                        }

		                        if(!"——".equals(institutionalSeatBuy)){
		                            BigDecimal cellInstitutionalSeatBuy=new BigDecimal(institutionalSeatBuy);
				                    institutionalSeatBuy=cellInstitutionalSeatBuy.setScale(2, BigDecimal.ROUND_HALF_UP).toString();  
		                        }
		                        if(!"——".equals(institutionalSeatSell)){
		                        	  BigDecimal cellInstitutionalSeatSell=new BigDecimal(institutionalSeatSell);
					                  institutionalSeatSell=cellInstitutionalSeatSell.setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 		
		                        }
		                        if(!"——".equals(institutionalSeatNet)){
				                    BigDecimal cellInstitutionalSeatNet=new BigDecimal(institutionalSeatNet);
				                    institutionalSeatNet=cellInstitutionalSeatNet.setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 	
		                        }

				    		   HSSFRichTextString richSerialNum = new HSSFRichTextString(serialNum); //序列号
	                           HSSFRichTextString richSecuritiesCode = new HSSFRichTextString(securitiesCode); //证券代码
	                           HSSFRichTextString richSecuritiesName= new HSSFRichTextString(securitiesName); //证券名称
	                           HSSFRichTextString richInstitutionalSeatBuy = new HSSFRichTextString(institutionalSeatBuy); //机构席位龙虎榜买入额	
	                           HSSFRichTextString richInstitutionalSeatBuyCount = new HSSFRichTextString(institutionalSeatBuyCount); //机构席位买入次数(买入家数)
	                           HSSFRichTextString richInstitutionalSeatSell = new HSSFRichTextString(institutionalSeatSell); //机构席位龙虎榜卖出额
	                           HSSFRichTextString richInstitutionalSeatSellCount = new HSSFRichTextString(institutionalSeatSellCount); //机构席位卖出次数(卖出家数)
	                           HSSFRichTextString richInstitutionalSeatNet = new HSSFRichTextString(institutionalSeatNet); //机构席位龙虎榜净额		
	                           HSSFRichTextString richSecuritiesAbnormalMove = new HSSFRichTextString(securitiesAbnormalMove); //股票异动类型
                               
	                            //普通黑色字体
			                    HSSFFont font = wb.createFont();
			                    font.setColor(HSSFColor.BLACK.index);//设置excel数据字体颜色
			                    font.setFontName("黑体");
			                    font.setFontHeightInPoints((short) 11);//设置excel数据字体大小
			                    //红色字体
			                    HSSFFont redFont = wb.createFont();
			                    redFont.setColor(HSSFColor.RED.index);//设置excel数据字体颜色
			                    redFont.setFontName("黑体");
			                    redFont.setFontHeightInPoints((short) 11);//设置excel数据字体大小
			                    //绿色字体
			                    HSSFFont greenFont = wb.createFont();
			                    greenFont.setColor(HSSFColor.GREEN.index);//设置excel数据字体颜色
			                    greenFont.setFontName("黑体");
			                    greenFont.setFontHeightInPoints((short) 11);//设置excel数据字体大小
			                    
			                    //Arial
			                    richSerialNum.applyFont(font);
			                    richSecuritiesCode.applyFont(font);				                    
			                    richSecuritiesName.applyFont(font);
			                    richInstitutionalSeatBuy.applyFont(font);			                    
			                    richInstitutionalSeatBuyCount.applyFont(font);
			                    richInstitutionalSeatSell.applyFont(font);
			                    richInstitutionalSeatSellCount.applyFont(font);
			                    richInstitutionalSeatNet.applyFont(font);
			                 /*   richSecuritiesAbnormalMove.applyFont(font);*/
			                    
			                    //如果包含 当日涨幅偏离值达7% 则刷红色
			                    if (securitiesAbnormalMove.indexOf("当日涨幅偏离值达7%")!=-1){
			                    	 //当日跌幅偏离值达7%,  部分刷红色，其他部分本色
			                           if(securitiesAbnormalMove.indexOf("当日涨幅偏离值达7%,")!=-1){
			                        	   String[] subStr = securitiesAbnormalMove.split(",");
			                       		String sText = subStr[0] + "," + subStr[1];
			                        		HSSFRichTextString textString = new HSSFRichTextString(sText);
			                        		textString.applyFont(
			                        				sText.indexOf(subStr[0]),
			                        				sText.indexOf(subStr[0]) + subStr[0].length(),
			                        				redFont
			                        				);
			                        		textString.applyFont(
			                        				sText.indexOf(subStr[1]),
			                        				sText.indexOf(subStr[1]) + subStr[1].length(),
			                        				font
			                        				);
			                        		//cell.setCellValue(textString);
			                        		  row.createCell(8).setCellValue(textString);
	
			                           }else{
			                        	   richSecuritiesAbnormalMove.applyFont(redFont);		
			                        	   row.createCell(8).setCellValue(richSecuritiesAbnormalMove);
			                           }
			                    	   
			                    	   
		    			         } else if (securitiesAbnormalMove.indexOf("当日跌幅偏离值达7%")!=-1){    //如果包含当日跌幅偏离值达7%则刷绿色	
		    			        	 //当日跌幅偏离值达7%,  部分刷绿色，其他部分本色
			                           if(securitiesAbnormalMove.indexOf("当日跌幅偏离值达7%,")!=-1){
			                        	   String[] subStr = securitiesAbnormalMove.split(",");
			                       		String sText = subStr[0] + "," + subStr[1];
			                        		HSSFRichTextString textString = new HSSFRichTextString(sText);
			                        		textString.applyFont(
			                        				sText.indexOf(subStr[0]),
			                        				sText.indexOf(subStr[0]) + subStr[0].length(),
			                        				greenFont
			                        				);
			                        		textString.applyFont(
			                        				sText.indexOf(subStr[1]),
			                        				sText.indexOf(subStr[1]) + subStr[1].length(),			                        			
			                        				font
			                        				);
			                        		//cell.setCellValue(textString);
			                        		  row.createCell(8).setCellValue(textString);
	
			                           }else{
			                        	   richSecuritiesAbnormalMove.applyFont(greenFont);		
			                        	   row.createCell(8).setCellValue(richSecuritiesAbnormalMove);
			                           }
			                    	   
			                    	   
		    			         }else{
		    			        	 richSecuritiesAbnormalMove.applyFont(font);
		    			        	  row.createCell(8).setCellValue(richSecuritiesAbnormalMove);
		    			         }

			                    row.createCell(0).setCellValue(richSerialNum);
			                    row.createCell(1).setCellValue(richSecuritiesCode);				                    
			                    row.createCell(2).setCellValue(richSecuritiesName);
			                    row.createCell(3).setCellValue(richInstitutionalSeatBuy);		
			                    row.createCell(4).setCellValue(richInstitutionalSeatBuyCount);
			                    row.createCell(5).setCellValue(richInstitutionalSeatSell);				                    
			                    row.createCell(6).setCellValue(richInstitutionalSeatSellCount);
			                    row.createCell(7).setCellValue(richInstitutionalSeatNet);	
			                  /*  row.createCell(8).setCellValue(richSecuritiesAbnormalMove);*/
					            i++;
					     }
					     
					     //输出Excel文件
					        OutputStream output=response.getOutputStream();
					        response.reset();
					     //   response.setHeader("Content-disposition", "attachment; filename=成交统计(按交易方向).xlsx");
					        String ExcelName="龙虎榜机构席位跟踪.xlsx";
					 
					        response.setHeader("Content-disposition", "attachment; filename="+toUtf8String(ExcelName));
					        response.setContentType("application/msexcel");
					        wb.write(output);
					        output.close();
				    }
				    
				} catch (DrmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      
		        return new ModelAndView("/uiPath/longHuList");
			}
		   
		   //2、龙虎榜上榜次数top10
			//导出 Excel 
		   @SuppressWarnings("null")
		   @ResponseBody
		   @RequestMapping(value="/LongHuList/outToExcel_onListCount/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		   public ModelAndView outToExcel_onListCount(HttpServletResponse response) throws IOException {
				
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
		        row2.createCell(0).setCellValue("序号");
		        row2.createCell(1).setCellValue("证券代码");
		        row2.createCell(2).setCellValue("证券名称");	
		        row2.createCell(3).setCellValue("上榜次数");			
		        row2.createCell(4).setCellValue("买入额");			
		        row2.createCell(5).setCellValue("卖出额");			
		        row2.createCell(6).setCellValue("净买入额");			

		        
		    	//北上资金累计净买入的表名(最新导入表的表名)  配置最新表的表名  uip_confTableName
			    DBPersistenceManager pm=DBAccessHelper.getPMByName(Const.DataSourceName);

				try {
					DataSet checkTableDs = (DataSet) pm.executeQuery(checkTableSql);						
				    if(checkTableDs!=null&&checkTableDs.next()){
					     DataSet ds=(DataSet)pm.executeQuery("select securities_code,securities_name,onList_count_toTwoweeks,longHu_buy_toTwoweeks,longHu_sell_toTwoweeks,(longHu_buy_toTwoweeks-longHu_sell_toTwoweeks) As netBuy from uip_longHuList  ORDER BY onList_count_toTwoweeks desc   limit 10");   		     
					    
					     
					     SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
					     int i=1;
				         sheet.setColumnWidth((short) 0, (short) 5000);  //设置列的宽度
				         
					     while (ds!=null&&ds.next()) {
					    	    HSSFRow row=sheet.createRow(i);
			
					    	    sheet.setColumnWidth((short) i, (short) 5000);  //设置列的宽度

					    	    //龙虎榜上榜次数TOP10
					    	    String serialNum=String.valueOf(i);//序列号
					    		String securitiesCode=ds.getString("securities_code");  //证券代码
					    		String securitiesName=ds.getString("securities_name");  //证券名称
					    		String onListCountToTwoweeks=ds.getString("onList_count_toTwoweeks");  //上榜次数
					    		String longHuBuy=ds.getString("longHu_buy_toTwoweeks");  //龙虎榜上榜次数中的买入额					    		
					    		String longHuSell=ds.getString("longHu_sell_toTwoweeks");  //龙虎榜上榜次数中的卖出额
					    		String netBuy=ds.getString("netBuy");  //净买入额=（龙虎榜买入额-龙虎榜卖出额）

						    	    if(!"——".equals(onListCountToTwoweeks)){
			                            BigDecimal cellOnListCountToTwoweeks=new BigDecimal(onListCountToTwoweeks);
			                            onListCountToTwoweeks=cellOnListCountToTwoweeks.setScale(0, BigDecimal.ROUND_HALF_UP).toString();  
			                        }
					    		    if(!"——".equals(longHuBuy)){
			                            BigDecimal cellLongHuBuy=new BigDecimal(longHuBuy);
			                            longHuBuy=cellLongHuBuy.setScale(2, BigDecimal.ROUND_HALF_UP).toString();  
			                        }
			                        if(!"——".equals(longHuSell)){
			                        	  BigDecimal cellLongHuSell=new BigDecimal(longHuSell);
			                        	  longHuSell=cellLongHuSell.setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 		
			                        }
			                        if(!"——".equals(netBuy)){
			                        	  BigDecimal cellNetBuy=new BigDecimal(netBuy);
			                        	  netBuy=cellNetBuy.setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 		
			                        }
					    		
					    		
					    		
				    		   HSSFRichTextString richSerialNum = new HSSFRichTextString(serialNum); //序列号
	                           HSSFRichTextString richSecuritiesCode = new HSSFRichTextString(securitiesCode); //证券代码
	                           HSSFRichTextString richSecuritiesName= new HSSFRichTextString(securitiesName); //证券名称	                           
	                           HSSFRichTextString richOnListCountToTwoweeks = new HSSFRichTextString(onListCountToTwoweeks); //上榜次数	
	                           HSSFRichTextString richLongHuBuy = new HSSFRichTextString(longHuBuy); //龙虎榜买入额		
	                           HSSFRichTextString richLongHuSell = new HSSFRichTextString(longHuSell); //龙虎榜卖出额
	                           HSSFRichTextString richNetBuy = new HSSFRichTextString(netBuy); //净买入额=（龙虎榜买入额-龙虎榜卖出额）

                               
	                            //普通黑色字体
			                    HSSFFont font = wb.createFont();
			                    font.setColor(HSSFColor.BLACK.index);//设置excel数据字体颜色
			                    font.setFontName("黑体");
			                    font.setFontHeightInPoints((short) 11);//设置excel数据字体大小
			
			                    
			                    //Arial
			                    richSerialNum.applyFont(font);
			                    richSecuritiesCode.applyFont(font);				                    
			                    richSecuritiesName.applyFont(font);
			                    richOnListCountToTwoweeks.applyFont(font);			                    
			                    richLongHuBuy.applyFont(font);
			                    richLongHuSell.applyFont(font);
			                    richNetBuy.applyFont(font);

			                    row.createCell(0).setCellValue(richSerialNum);
			                    row.createCell(1).setCellValue(richSecuritiesCode);				                    
			                    row.createCell(2).setCellValue(richSecuritiesName);
			                    row.createCell(3).setCellValue(richOnListCountToTwoweeks);		
			                    row.createCell(4).setCellValue(richLongHuBuy);
			                    row.createCell(5).setCellValue(richLongHuSell);				                    
			                    row.createCell(6).setCellValue(richNetBuy);
					            i++;
					     }
					     
					     //输出Excel文件
					        OutputStream output=response.getOutputStream();
					        response.reset();
					     //   response.setHeader("Content-disposition", "attachment; filename=成交统计(按交易方向).xlsx");
					        String ExcelName="龙虎榜上榜次数top10.xlsx";
					 
					        response.setHeader("Content-disposition", "attachment; filename="+toUtf8String(ExcelName));
					        response.setContentType("application/msexcel");
					        wb.write(output);
					        output.close();
				    }
				    
				} catch (DrmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      
		        return new ModelAndView("/uiPath/longHuList");
			}

	 
		   //3、机构流入前十名,4、机构流出前十名
				//导出 Excel 
			   @SuppressWarnings("null")
			   @ResponseBody
			   @RequestMapping(value="/LongHuList/outToExcel_InstitutionalInflowTopTen/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
			   public ModelAndView outToExcel_InstitutionalInflowTopTen(HttpServletResponse response,HttpServletRequest request) throws IOException {
					
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
			        row2.createCell(0).setCellValue("序号");
			        row2.createCell(1).setCellValue("证券代码");
			        row2.createCell(2).setCellValue("证券名称");	
			        row2.createCell(3).setCellValue("所属行业");			
			        row2.createCell(4).setCellValue("龙虎榜买入额");			
			        row2.createCell(5).setCellValue("龙虎榜卖出额");			
			        row2.createCell(6).setCellValue("龙虎榜净额");		
			        row2.createCell(7).setCellValue("机构席位龙虎榜净额");			

			        
			    	//北上资金累计净买入的表名(最新导入表的表名)  配置最新表的表名  uip_confTableName
				    DBPersistenceManager pm=DBAccessHelper.getPMByName(Const.DataSourceName);

					try {
						DataSet checkTableDs = (DataSet) pm.executeQuery(checkTableSql);						
					    if(checkTableDs!=null&&checkTableDs.next()){
					    	
					    	
					    	 //机构流入前十名
					    	String  moveStyle=(String)request.getParameter("moveStyle");
					    	// moveStyle 如果等于inflow 说明是流入,其他是流出					    	
					    	 DataSet ds=null;
					    	 if(moveStyle.equals("inflow")){
					    		 ds=(DataSet)pm.executeQuery("select securities_code,securities_name,belong_dongcai,longHu_buy,longHu_sell,longHu_net,institutional_seat_net from uip_longHuList  where institutional_seat_net > 0 ORDER BY institutional_seat_net desc   limit 10");
					    	 }else{
					    		 ds=(DataSet)pm.executeQuery("select securities_code,securities_name,belong_dongcai,longHu_buy,longHu_sell,longHu_net,institutional_seat_net from uip_longHuList  where institutional_seat_net < 0 ORDER BY institutional_seat_net   limit 10");
					    	 }
	     
						    
						     
						     SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
						     int i=1;
					         sheet.setColumnWidth((short) 0, (short) 5000);  //设置列的宽度
					         
					         int rowCount = ds.getRowCount();
					         System.out.println("rowCount:"+rowCount);
					         
						     while (ds!=null&&ds.next()) {
						    	    HSSFRow row=sheet.createRow(i);				
						    	    sheet.setColumnWidth((short) i, (short) 5000);  //设置列的宽度

						    	    String serialNum=String.valueOf(i);//序列号
						    		String securitiesCode=ds.getString("securities_code");  //证券代码
						    		String securitiesName=ds.getString("securities_name");  //证券名称
						    		String onListCountToTwoweeks=ds.getString("belong_dongcai");  //所属行业
						    		String longHuBuy=ds.getString("longHu_buy");  //龙虎榜买入额					    		
						    		String longHuSell=ds.getString("longHu_sell");  //龙虎榜卖出额
						    		String longHuNet=ds.getString("longHu_net");  //龙虎榜净额
						    		String institutionalSeatNet=ds.getString("institutional_seat_net");  //机构席位龙虎榜净额
						    		
					    		    if(!"——".equals(longHuBuy)){
			                            BigDecimal cellLongHuBuy=new BigDecimal(longHuBuy);
			                            longHuBuy=cellLongHuBuy.setScale(2, BigDecimal.ROUND_HALF_UP).toString();  
			                        }
			                        if(!"——".equals(longHuSell)){
			                        	  BigDecimal cellLongHuSell=new BigDecimal(longHuSell);
			                        	  longHuSell=cellLongHuSell.setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 		
			                        }
			                        if(!"——".equals(longHuNet)){
			                        	  BigDecimal cellNetBuy=new BigDecimal(longHuNet);
			                        	  longHuNet=cellNetBuy.setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 		
			                        }
			                 	    if(!"——".equals(institutionalSeatNet)){
			                            BigDecimal cellInstitutionalSeatNet=new BigDecimal(institutionalSeatNet);			                       
			                            institutionalSeatNet=cellInstitutionalSeatNet.setScale(4, BigDecimal.ROUND_HALF_UP).toString();  
			                        }
						    		
						    		
						    		
					    		   HSSFRichTextString richSerialNum = new HSSFRichTextString(serialNum); //序列号
		                           HSSFRichTextString richSecuritiesCode = new HSSFRichTextString(securitiesCode); //证券代码
		                           HSSFRichTextString richSecuritiesName= new HSSFRichTextString(securitiesName); //证券名称	                           
		                           HSSFRichTextString richOnListCountToTwoweeks = new HSSFRichTextString(onListCountToTwoweeks); //所属行业
		                           HSSFRichTextString richLongHuBuy = new HSSFRichTextString(longHuBuy); //龙虎榜买入额		
		                           HSSFRichTextString richLongHuSell = new HSSFRichTextString(longHuSell); //龙虎榜卖出额
		                           HSSFRichTextString richLongHuNet = new HSSFRichTextString(longHuNet); //龙虎榜净额
		                           HSSFRichTextString richInstitutionalSeatNet = new HSSFRichTextString(institutionalSeatNet); ///机构席位龙虎榜净额

	                               
		                            //普通黑色字体
				                    HSSFFont font = wb.createFont();
				                    font.setColor(HSSFColor.BLACK.index);//设置excel数据字体颜色
				                    font.setFontName("黑体");
				                    font.setFontHeightInPoints((short) 11);//设置excel数据字体大小
				
				                    
				                    //Arial
				                    richSerialNum.applyFont(font);
				                    richSecuritiesCode.applyFont(font);				                    
				                    richSecuritiesName.applyFont(font);
				                    richOnListCountToTwoweeks.applyFont(font);			                    
				                    richLongHuBuy.applyFont(font);
				                    richLongHuSell.applyFont(font);
				                    richLongHuNet.applyFont(font);
				                    richInstitutionalSeatNet.applyFont(font);

				                    row.createCell(0).setCellValue(richSerialNum);
				                    row.createCell(1).setCellValue(richSecuritiesCode);				                    
				                    row.createCell(2).setCellValue(richSecuritiesName);
				                    row.createCell(3).setCellValue(richOnListCountToTwoweeks);		
				                    row.createCell(4).setCellValue(richLongHuBuy);
				                    row.createCell(5).setCellValue(richLongHuSell);				                    
				                    row.createCell(6).setCellValue(richLongHuNet);				                    
				                    row.createCell(7).setCellValue(richInstitutionalSeatNet);
      
						            i++;
						     }
						     
				              //不足10条数据用横线补齐
			                    if(rowCount<10){
			                    	  for(int k=rowCount+1;k<=10;k++){		
				                      	    HSSFRow newrow=sheet.createRow(k);		
								    	    sheet.setColumnWidth((short) k, (short) 5000);  //设置列的宽度
								    	    
								    	    String serialNum=String.valueOf(k);//序列号								    	    
								    	    HSSFRichTextString richSerialNum = new HSSFRichTextString(serialNum); //序列号
								    	    //普通黑色字体
						                    HSSFFont font = wb.createFont();
						                    font.setColor(HSSFColor.BLACK.index);//设置excel数据字体颜色
						                    font.setFontName("黑体");
						                    font.setFontHeightInPoints((short) 11);//设置excel数据字体大小						                    
						                    //Arial
						                    richSerialNum.applyFont(font);			                    		  
								    	    newrow.createCell(0).setCellValue(richSerialNum);
								    	    newrow.createCell(1).setCellValue("——");				                    
								    	    newrow.createCell(2).setCellValue("——");
								    	    newrow.createCell(3).setCellValue("——");		
								    	    newrow.createCell(4).setCellValue("——");
								    	    newrow.createCell(5).setCellValue("——");				                    
								    	    newrow.createCell(6).setCellValue("——");				                    
								    	    newrow.createCell(7).setCellValue("——");
			                    	  }
			                    
			                    }
						     
						     //输出Excel文件
						        OutputStream output=response.getOutputStream();
						        response.reset();
						        String ExcelName=null;						        
						    	// moveStyle 如果等于inflow 说明是流入,其他是流出					    	
						    	 if(moveStyle.equals("inflow")){
						    		 ExcelName="机构流入前十名.xlsx";						    		 
						    	 }else{
						    		 ExcelName="机构流出前十名.xlsx";			
						    	 }
						 
						        response.setHeader("Content-disposition", "attachment; filename="+toUtf8String(ExcelName));
						        response.setContentType("application/msexcel");
						        wb.write(output);
						        output.close();
					    }
					    
					} catch (DrmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      
			        return new ModelAndView("/uiPath/longHuList");
				}

	 
			   
			   //outToExcel_lushenInflowTopTen
			   //5、沪深股通席位流入前十名,6、沪深股通席位流出前十名
				//导出 Excel 
			   @SuppressWarnings("null")
			   @ResponseBody
			   @RequestMapping(value="/LongHuList/outToExcel_lushenInflowTopTen/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
			   public ModelAndView outToExcel_lushenInflowTopTen(HttpServletResponse response,HttpServletRequest request) throws IOException {
					
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
			        row2.createCell(0).setCellValue("序号");
			        row2.createCell(1).setCellValue("证券代码");
			        row2.createCell(2).setCellValue("证券名称");	
			        row2.createCell(3).setCellValue("所属行业");			
			        row2.createCell(4).setCellValue("龙虎榜买入额");			
			        row2.createCell(5).setCellValue("龙虎榜卖出额");			
			        row2.createCell(6).setCellValue("龙虎榜净额");		

			    	//北上资金累计净买入的表名(最新导入表的表名)  配置最新表的表名  uip_confTableName
				    DBPersistenceManager pm=DBAccessHelper.getPMByName(Const.DataSourceName);

					try {
						DataSet checkTableDs = (DataSet) pm.executeQuery(checkTableSql);						
					    if(checkTableDs!=null&&checkTableDs.next()){
					    	
					    	
					    	 //机构流入前十名
					    	String  moveStyle=(String)request.getParameter("moveStyle");
					    	// moveStyle 如果等于inflow 说明是流入,其他是流出					    	
					    	 DataSet ds=null;
					    	 if(moveStyle.equals("inflow")){
					    		 ds=(DataSet)pm.executeQuery("select securities_code,securities_name,belong_dongcai,lushenSeat_longhu_Buy,lushenSeat_longhu_sell,lushenSeat_longhu_net from uip_longHuList  where lushenSeat_longhu_net > 0 ORDER BY lushenSeat_longhu_net desc limit 10");

					    	 }else{
					    		 ds=(DataSet)pm.executeQuery("select securities_code,securities_name,belong_dongcai,lushenSeat_longhu_Buy,lushenSeat_longhu_sell,lushenSeat_longhu_net from uip_longHuList  where lushenSeat_longhu_net < 0 ORDER BY lushenSeat_longhu_net limit 10");
					    	 }
	     
						    
						     
						     SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
						     int i=1;
					         sheet.setColumnWidth((short) 0, (short) 5000);  //设置列的宽度
					         
					         int rowCount = ds.getRowCount();
				         
						     while (ds!=null&&ds.next()) {
						    	    HSSFRow row=sheet.createRow(i);				
						    	    sheet.setColumnWidth((short) i, (short) 5000);  //设置列的宽度

						    	    String serialNum=String.valueOf(i);//序列号
						    		String securitiesCode=ds.getString("securities_code");  //证券代码
						    		String securitiesName=ds.getString("securities_name");  //证券名称
						    		String onListCountToTwoweeks=ds.getString("belong_dongcai");  //所属行业
						    		String longHuBuy=ds.getString("lushenSeat_longhu_Buy");  //龙虎榜买入额					    		
						    		String longHuSell=ds.getString("lushenSeat_longhu_sell");  //龙虎榜卖出额
						    		String longHuNet=ds.getString("lushenSeat_longhu_net");  //龙虎榜净额
						    		
					    		    if(!"——".equals(longHuBuy)){
			                            BigDecimal cellLongHuBuy=new BigDecimal(longHuBuy);
			                            BigDecimal cellLongHuBuy2=new BigDecimal("100000000");
			                          //  longHuBuy=cellLongHuBuy.setScale(2, BigDecimal.ROUND_HALF_UP).toString();  			 
			                            longHuBuy=cellLongHuBuy.divide(cellLongHuBuy2, 2, BigDecimal.ROUND_HALF_UP).toString();  
			                            
			                        }
			                        if(!"——".equals(longHuSell)){
			                        	  BigDecimal cellLongHuSell=new BigDecimal(longHuSell);
			                        	  BigDecimal cellLongHuSell2=new BigDecimal("100000000");
			                        	 // longHuSell=cellLongHuSell.setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 	
			                        	  longHuSell=cellLongHuSell.divide(cellLongHuSell2, 2, BigDecimal.ROUND_HALF_UP).toString();  
			                        }
			                        if(!"——".equals(longHuNet)){
			                        	  BigDecimal cellNetBuy=new BigDecimal(longHuNet);
			                        	  BigDecimal cellNetBuy2=new BigDecimal("100000000");
			                        	//  longHuNet=cellNetBuy.setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 	
			                        	  
			                        	  longHuNet=cellNetBuy.divide(cellNetBuy2,2, BigDecimal.ROUND_HALF_UP).toString(); 	
			                        }
			            
						    		
						    		
						    		
					    		   HSSFRichTextString richSerialNum = new HSSFRichTextString(serialNum); //序列号
		                           HSSFRichTextString richSecuritiesCode = new HSSFRichTextString(securitiesCode); //证券代码
		                           HSSFRichTextString richSecuritiesName= new HSSFRichTextString(securitiesName); //证券名称	                           
		                           HSSFRichTextString richOnListCountToTwoweeks = new HSSFRichTextString(onListCountToTwoweeks); //所属行业
		                           HSSFRichTextString richLongHuBuy = new HSSFRichTextString(longHuBuy); //龙虎榜买入额		
		                           HSSFRichTextString richLongHuSell = new HSSFRichTextString(longHuSell); //龙虎榜卖出额
		                           HSSFRichTextString richLongHuNet = new HSSFRichTextString(longHuNet); //龙虎榜净额
		                      

	                               
		                            //普通黑色字体
				                    HSSFFont font = wb.createFont();
				                    font.setColor(HSSFColor.BLACK.index);//设置excel数据字体颜色
				                    font.setFontName("黑体");
				                    font.setFontHeightInPoints((short) 11);//设置excel数据字体大小
				
				                    
				                    //Arial
				                    richSerialNum.applyFont(font);
				                    richSecuritiesCode.applyFont(font);				                    
				                    richSecuritiesName.applyFont(font);
				                    richOnListCountToTwoweeks.applyFont(font);			                    
				                    richLongHuBuy.applyFont(font);
				                    richLongHuSell.applyFont(font);
				                    richLongHuNet.applyFont(font);
			

				                    row.createCell(0).setCellValue(richSerialNum);
				                    row.createCell(1).setCellValue(richSecuritiesCode);				                    
				                    row.createCell(2).setCellValue(richSecuritiesName);
				                    row.createCell(3).setCellValue(richOnListCountToTwoweeks);		
				                    row.createCell(4).setCellValue(richLongHuBuy);
				                    row.createCell(5).setCellValue(richLongHuSell);				                    
				                    row.createCell(6).setCellValue(richLongHuNet);				                    

      
						            i++;
						     }
						     
				              //不足10条数据用横线补齐
			                    if(rowCount<10){
			                    	  for(int k=rowCount+1;k<=10;k++){		
				                      	    HSSFRow newrow=sheet.createRow(k);		
								    	    sheet.setColumnWidth((short) k, (short) 5000);  //设置列的宽度
								    	    
								    	    String serialNum=String.valueOf(k);//序列号								    	    
								    	    HSSFRichTextString richSerialNum = new HSSFRichTextString(serialNum); //序列号
								    	    //普通黑色字体
						                    HSSFFont font = wb.createFont();
						                    font.setColor(HSSFColor.BLACK.index);//设置excel数据字体颜色
						                    font.setFontName("黑体");
						                    font.setFontHeightInPoints((short) 11);//设置excel数据字体大小						                    
						                    //Arial
						                    richSerialNum.applyFont(font);			                    		  
								    	    newrow.createCell(0).setCellValue(richSerialNum);
								    	    newrow.createCell(1).setCellValue("——");				                    
								    	    newrow.createCell(2).setCellValue("——");
								    	    newrow.createCell(3).setCellValue("——");		
								    	    newrow.createCell(4).setCellValue("——");
								    	    newrow.createCell(5).setCellValue("——");				                    
								    	    newrow.createCell(6).setCellValue("——");				                    
		
			                    	  }
			                    
			                    }
						     
						     //输出Excel文件
						        OutputStream output=response.getOutputStream();
						        response.reset();
						        String ExcelName=null;						        
						    	// moveStyle 如果等于inflow 说明是流入,其他是流出					    	
						    	 if(moveStyle.equals("inflow")){
						    		 ExcelName="沪深股通席位流入前十名.xlsx";						    		 
						    	 }else{
						    		 ExcelName="沪深股通席位流出前十名.xlsx";			
						    	 }
						 
						        response.setHeader("Content-disposition", "attachment; filename="+toUtf8String(ExcelName));
						        response.setContentType("application/msexcel");
						        wb.write(output);
						        output.close();
					    }
					    
					} catch (DrmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      
			        return new ModelAndView("/uiPath/longHuList");
				}
			   
	
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
			
			
			
			
			//去除字符串中的括号以及里面的内容
			  private String ClearBracket(String context) {
		 
		        // 修改原来的逻辑，防止右括号出现在左括号前面的位置
		        int head = context.indexOf('('); // 标记第一个使用左括号的位置
		        if (head == -1) {
		            return context; // 如果context中不存在括号，什么也不做，直接跑到函数底端返回初值str
		        } else {
		            int next = head + 1; // 从head+1起检查每个字符
		            int count = 1; // 记录括号情况
		            do {
		                if (context.charAt(next) == '(') {
		                    count++;
		                } else if (context.charAt(next) == ')') {
		                    count--;
		                }
		                next++; // 更新即将读取的下一个字符的位置
		                if (count == 0) { // 已经找到匹配的括号
		                    String temp = context.substring(head, next);
		                    context = context.replace(temp, ""); // 用空内容替换，复制给context
		                    head = context.indexOf('('); // 找寻下一个左括号
		                    next = head + 1; // 标记下一个左括号后的字符位置
		                    count = 1; // count的值还原成1
		                }
		            } while (head != -1); // 如果在该段落中找不到左括号了，就终止循环
		        }
		        return context; // 返回更新后的context
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
