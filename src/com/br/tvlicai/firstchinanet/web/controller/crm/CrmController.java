package com.br.tvlicai.firstchinanet.web.controller.crm;


import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.drm.DrmException;
import com.neusoft.drm.dataexpress.DataSet;
import com.neusoft.unieap.util.DBAccessHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.br.tvlicai.firstchinanet.CommonUtil;
import com.br.tvlicai.firstchinanet.app.controller.base.BaseController;
import com.br.tvlicai.firstchinanet.commom.Const.Const;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by wzy on 2016/8/2.
 */
@Controller
@RequestMapping("/crm")
public class CrmController extends BaseController{

    static final Logger _LOG = LoggerFactory.getLogger(CrmController.class);


	/**
	 * 创建日期:2020年4月30日<br/>
	 * 代码创建:满方皓<br/>
	 * 功能描述:通过user账号查询crm用户<br/>
	 * @param jsonobject 这个是阿里的 fastjson对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userbyaccount", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String user(HttpServletRequest request) {
	    // 直接将json信息打印出来
		String account=(String)request.getParameter("account");
	    
		JSONObject result = new JSONObject();
	    DBPersistenceManager pm=null;
        try {
        	pm=DBAccessHelper.getPMByName("CRM");
        	_LOG.error("数据元"+pm.toString());
	        
	        DataSet dsdata=(DataSet)pm.executeQuery("select  top 1 * from Dg_Guest_info where Name = '"+account+"'");   
	        _LOG.error("数据集"+dsdata.getRowCount());
	        if (dsdata!=null&&dsdata.next()) {
        		//封装user
        	   JSONObject jsonObject = new JSONObject();
               jsonObject.put("Gid", dsdata.getString("Gid"));
               System.out.print(jsonObject.toJSONString());
               jsonObject.put("Name", dsdata.getString("Name"));
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("Tel1", dsdata.getString("Tel1"));
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("Address", dsdata.getString("Address"));
               _LOG.error(jsonObject.toJSONString());
               result.put("user", jsonObject);
               result.put("msg", "success");
               result.put("code", "0");
               _LOG.error(result.toJSONString());
            }
        }catch (Exception e) {
        	_LOG.error("CrmController：查询用户异常");
            result.put("user", null);
            result.put("msg", "用户查询异常");
            result.put("code", "999");
        }finally{
        	if(pm!=null){
        		pm.close();
        	}
        }
        
	    return result.toJSONString();
	}

	/**
	 * 创建日期:2020年4月30日<br/>
	 * 代码创建:满方皓<br/>
	 * 功能描述:通过user账号查询crm用户<br/>
	 * @param jsonobject 这个是阿里的 fastjson对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderbyaccount", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String order(HttpServletRequest request) {
	    // 直接将json信息打印出来
		String account=(String)request.getParameter("account");
	    
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
	    DBPersistenceManager pm=null;
        try {
        	pm=DBAccessHelper.getPMByName("CRM");
        	_LOG.error("数据元"+pm.toString());
	        
        	String sql = "select a.*,b.*,c.* from Dg_Order_Info a "
        			+ "left join Dg_Order_Detail b on a.Oid = b.Oid "
        			+ "left join Dg_Product_Info c on c.PID = b.Pid "
        			+ "where a.GID = '"+account+"'";
        	
	        DataSet dsdata=(DataSet)pm.executeQuery(sql);   
	        _LOG.error("数据集"+dsdata.getRowCount());
	        while (dsdata!=null&&dsdata.next()) {
        		//封装user
        	   JSONObject jsonObject = new JSONObject();
               jsonObject.put("Oid", dsdata.getString("Oid"));//订单编号
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("GID", dsdata.getString("GID"));//客户编号
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("Pid", dsdata.getInt("Pid"));//商品编号
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("Regtime", dsdata.getDate("Regtime"));//下单时间
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("OState", dsdata.getInt("OState"));//订单状态
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("OTotalMoney",  dsdata.getDouble("OTotalMoney"));//订单金额
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("OPostPrice", dsdata.getDouble("OPostPrice"));//优惠金额
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("PCommonPrice", dsdata.getDouble("PCommonPrice"));//商品原单价
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("PPrice", dsdata.getDouble("PPrice"));//实际销售单价
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("PNum", dsdata.getInt("PNum"));//数量
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("OBackMoneyTime", dsdata.getDate("OBackMoneyTime"));//回款时间
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("PName", dsdata.getString("PName"));//商品名称
               _LOG.error(jsonObject.toJSONString());
               jsonObject.put("GYId", dsdata.getInt("GYId"));//供应商编号
               _LOG.error(jsonObject.toJSONString());

               array.add(jsonObject);
            }
            result.put("user", array);
            result.put("msg", "success");
            result.put("code", "0");
            _LOG.error(result.toJSONString());
        }catch (Exception e) {
        	_LOG.error("CrmController：查询用户订单异常");
            result.put("user", null);
            result.put("msg", "用户订单查询异常");
            result.put("code", "999");
        }finally{
        	if(pm!=null){
        		pm.close();
        	}
        }
        
	    return result.toJSONString();
	}
	
	
	//导入微信订单 到客户收款
    @RequestMapping("/importWxOrderpage")
    public ModelAndView importWxOrderpage(HttpServletRequest request,ModelAndView modelAndView, HttpSession session){ 
		System.out.println("进入 importWxOrder 方法");
		   modelAndView.setViewName("/importToCrm/wxToCrm");
    	   return modelAndView;
    }
    
    
  
        //   Excel导入
		@SuppressWarnings("null")
		@ResponseBody
		@RequestMapping(value="/importWxOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		public String  importWxOrder(HttpServletRequest request, HttpSession session,@RequestParam(value = "file")MultipartFile file){

		    JSONObject json = new JSONObject();
			int result=0;
			int errorResultCount=0;
			String errorResult=null;  //失败条数的计数
			
			String wxPayType=(String)request.getParameter("wxPayType");  //导入的数据类型 （0:普通微信支付类型，1：小鹅通类型）
			
		    if(null == file) {
		       // return "上传的文件为空";
		        json.put("result", "2");
		    }
		    SimpleDateFormat SimpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
			String Signs=getRandomString(36);       //每次导表生出的唯一标识
			   Date SignsNowDate=new Date();
			   Signs= SimpleDateFormat1.format(SignsNowDate)+Signs;
			   
			
			System.out.println("Signs:"+Signs);
			
		   	 DBPersistenceManager pm=null;
	            pm=DBAccessHelper.getPMByName("IMPORTCRM");
	            
	        try {

			        // 读取直到最后一行
		        	
		        	 InputStreamReader isr = new InputStreamReader(file.getInputStream());
			         BufferedReader br = new BufferedReader(isr);
		
			        String line = "";
			        int i=0;
		
			        while ((line = br.readLine()) != null) {
			            StringTokenizer st = new StringTokenizer(line, ",");//将一行数据分割开
			            
			            String[] keyWordArr=line.split(",");
		                
		                if(i >4){
		                    SimpleDateFormat SimpleDateFormat4 = new SimpleDateFormat("yyyy-MM-dd");
		                    SimpleDateFormat SimpleDateFormat5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
			                //收款时间
			                String InTimeStr=null;
			                Date InTimeDate=null;
								String InTime=keyWordArr[0].toString();
								System.out.print("InTime:"+InTime);
								InTime=InTime.replace("/", "-");
							      try {
									InTimeDate = SimpleDateFormat4.parse(InTime);
									InTimeStr=SimpleDateFormat4.format(InTimeDate);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
							
							      Date nowDate=new Date();
							      String nowDateStr= SimpleDateFormat5.format(nowDate);
							      
			                System.out.println("InTimeStr:"+InTimeStr);
			                
			                //收款金额		              
			                String  InAmountStr=keyWordArr[5].toString();
			                BigDecimal InAmount = new BigDecimal(InAmountStr);//
			                System.out.println(new BigDecimal(InAmountStr).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());//保留2位小数
			                System.out.println("InAmountBig:"+InAmount);
			                
			                //收款标识
			                String  InKey=keyWordArr[1].toString();
			                InKey= InKey.replace("`", "交易单号");
			                System.out.println("InKey:"+InKey);
			                
			                //收款类型   （0退款1代收2微信3支付宝4银行转账5现金）
			                int InType=2;
				            
				        	   String Remark="";
				        	   if(wxPayType.equals("1")){
				        		   Remark="小鹅通";
				        	   }
				        	   String UserId="8888";
						   
						       //返回插入后的状态
				        	    result=CrmController.callSpWithOutParam(InTimeStr,InType,InAmount,InKey,Remark,UserId,Signs,nowDateStr);	
								System.out.print("result:"+result);
								 
								if(result != 1){							
									errorResultCount=errorResultCount+1; //返回前台的失败条数
								}					          
		                }
		                i++;
		     
			        }
			        br.close();
		        }catch (IOException e) {
			        e.printStackTrace();
			    }finally{
		        	if(pm!=null){		              
		        		pm.close();
		        	}
	        }
	        
		    json.put("errorResultCount", errorResultCount);
		/*	json.put("errorResult", errorResult);*/
		/*	 JSONArray jSONArray=this.getWxError(Signs);
			System.out.print("json:"+jSONArray);		
			
			 json.put("results", jSONArray);*/
	
		    json.put("Signs", Signs);
	        return json.toJSONString();
	
       }
		
		//向CRM插入数据
		public static int callSpWithOutParam(String InTimeStr,int InType,BigDecimal InAmount,String InKey,String Remark,String UserId,String Signs,String nowDateStr){
			
			int executeUpdate=0;
		   	 DBPersistenceManager pm=null;
	            pm=DBAccessHelper.getPMByName("UNIEAP");
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//注释部分 测试用
			/*	Connection conn= DriverManager.getConnection("jdbc:sqlserver://47.94.144.76:1433;DatabaseName=test","admin","e4L!@B2AiYiS");*/
				Connection conn= DriverManager.getConnection("jdbc:sqlserver://172.11.5.250:1433;DatabaseName=test_diangou","user_select","Abc123456");
				
				CallableStatement callStmt=conn.prepareCall("{call Fin_InCome_Add(?,?,?,?,?,?,?,?)}");
			/*	CallableStatement callStmt=conn.prepareCall("{call Fin_InCome_Add(?,?,?,?,?,?)}");*/

				
		
				//输入参数
				callStmt.setString(1, InTimeStr);
				callStmt.setInt(2, InType);
				callStmt.setBigDecimal(3, InAmount);
				callStmt.setString(4, InKey);
				callStmt.setString(5, Remark);
				callStmt.setString(6, UserId);
		
				callStmt.registerOutParameter(7,java.sql.Types.VARCHAR);  //输出参数
				callStmt.registerOutParameter(8,java.sql.Types.INTEGER);  //输出参数

				callStmt.execute();
				
			/*	executeUpdate=callStmt.executeUpdate();*/

				String resultMsg=callStmt.getString(7);
				 executeUpdate=callStmt.getInt(8);  //返回的执行结果
				
				
				System.out.println("execute:"+executeUpdate);
				/*if(execute == true){
					executeUpdate=1;
				}else{
					executeUpdate=0;
				}*/

        	
				System.out.println("执行结果："+executeUpdate);
				System.out.println("执行结果的描述："+resultMsg);
				
				/*_LOG.error*/
				_LOG.error("向CRM导客户付款记录中微信记录的执行结果:"+executeUpdate+",执行结果的描述:"+resultMsg);
				
				//向log表中插入导表记录
		/*		String executeResult="1";
				String resultMsg="测试222";*/
				String sql="insert into CRM_guideTable_log(InType,InKey,InAmount,InTime,Signs,executeResult,resultMsg,addTime,remark) values("+InType+",'"+InKey+"','"+InAmount+"','"+InTimeStr+"','"
				+Signs+"','"+executeUpdate+"','"+resultMsg+"','"+nowDateStr+"','"+Remark+"')";
				
				System.out.println("sql:"+sql);
				try {
					int k=pm.executeUpdate(sql);
					System.out.println("插入导表记录:"+k);
				} catch (DrmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				callStmt.close();
				conn.close();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("executeUpdate:"+executeUpdate);
			return executeUpdate;

			
		}
		
		//获取导入的列表
		@ResponseBody
		@RequestMapping(value = "/getWxErrorList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=UTF-8")
		public String getWxErrorList(HttpServletRequest request, HttpSession session) {
			  // 直接将json信息打印出来
			  SimpleDateFormat SimpleDateFormat5 = new SimpleDateFormat("yyyy-MM-dd");
		
				String page=(String)request.getParameter("page");
				String Signs=(String)request.getParameter("Signs");
			    if(CommonUtil.isNull(page))
			    {
			    	page="1";
			    }
			      int pageSize=15;  //每页有多少条数据
			    int pageNo=(Integer.parseInt(page)-1)*pageSize; 
			    
			    JSONArray rowA = new JSONArray();
			    int count=0;
			    int i=0;

		       DBPersistenceManager pm=null;		
			   pm=DBAccessHelper.getPMByName("UNIEAP");
				try {
				    	String checkconfTableSql="select * from CRM_guideTable_log where  Signs='"+Signs+"'";
			        	DataSet Ds = (DataSet) pm.executeQuery(checkconfTableSql);   
				    	
					        if (Ds!=null&&Ds.next()) {
					        	count=Ds.getRowCount();
				            }

								String sql="select * from CRM_guideTable_log where  Signs='"+Signs+"' ORDER BY executeResult   limit "+pageNo+","+pageSize;
								System.out.println("sql:"+sql);
		
						         DataSet checkconfTableDs = (DataSet) pm.executeQuery(sql);   
						         
						         System.out.println("checkconfTableDs的数量:"+checkconfTableDs.getRowCount());

							    while (checkconfTableDs!=null&&checkconfTableDs.next()) {
							 	   JSONObject jsonObject = new JSONObject();
								 	  jsonObject.put("InKey", checkconfTableDs.getString("InKey"));
									  jsonObject.put("InAmount", checkconfTableDs.getString("InAmount"));
									  jsonObject.put("executeResult", checkconfTableDs.getString("executeResult"));
									  jsonObject.put("resultMsg", checkconfTableDs.getString("resultMsg"));
									
									  String InTimeStr= SimpleDateFormat5.format(checkconfTableDs.getDate("InTime"));
									  jsonObject.put("InTime", InTimeStr);
									  //收款类型
									  int InType= checkconfTableDs.getInt("InType");
									  String InTypeStr=null;
									  if(InType == 0){
										  InTypeStr="退款";
									  }
									  if(InType == 1){
										  InTypeStr="代收";
									  }
									  if(InType == 2){
										  InTypeStr="微信";
									  }
									  if(InType == 3){
										  InTypeStr="支付宝";
									  }
									  if(InType == 4){
										  InTypeStr="银行转账";
									  }
									  if(InType == 5){
										  InTypeStr="现金";
									  }
									  jsonObject.put("InTypeStr", InTypeStr);
									  //成功状态
									  String executeResultStr=null;
									  int executeResult= checkconfTableDs.getInt("executeResult");
									  if(executeResult == 1){
										  executeResultStr="成功";
									  }
									  if(executeResult == 0){
										  executeResultStr="失败";
									  }
									  jsonObject.put("executeResultStr", executeResultStr);
									  //备注
									    System.out.println("备注:"+checkconfTableDs.getString("remark"));
									  jsonObject.put("remark",checkconfTableDs.getString("remark"));

						           rowA.add(i, jsonObject);
					               i++;
							    }
				    
				
				    
				    
				} catch (DrmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
		        	if(pm!=null){
		        		pm.close();
		        	}
		        }
				
			    // 将获取的json数据封装一层，然后在给返回
				JSONObject result = new JSONObject();
			    result.put("results", rowA);
			    result.put("links", "@ResponseBody");
			    result.put("count", count);
			    
			    System.out.println("查询的列表结果："+result.toJSONString());

			    return result.toJSONString();	
			

		}
		
		
		 //生成36位随机数
		 public static String getRandomString(int length){
		     String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		     Random random=new Random();
		     StringBuffer sb=new StringBuffer();
		     for(int i=0;i<length;i++){
		       int number=random.nextInt(36);
		       sb.append(str.charAt(number));
		     }
		     return sb.toString();
		 }

		
		
}
