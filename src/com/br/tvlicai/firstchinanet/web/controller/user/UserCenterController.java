package com.br.tvlicai.firstchinanet.web.controller.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.drm.DrmException;
import com.neusoft.drm.dataexpress.DataSet;
import com.neusoft.unieap.util.DBAccessHelper;
/**
 * Created by wzy on 2016/8/2.
 */
@Controller
@RequestMapping("")
public class UserCenterController extends BaseController{

    static final Logger _LOG = LoggerFactory.getLogger(UserCenterController.class);

    /**
     * 根据不同类型的用户跳到相应的用户中心中
     *
     * @param modelAndView
     * @param session
     * @return
     */
    @RequestMapping("/userCenter")
    public ModelAndView userCenter(ModelAndView modelAndView, HttpSession session) {

        String userType = (String) session.getAttribute(Const.USERINFO_USER_TYPE);//用户类型 
        modelAndView.setViewName("user/mainCenter");

        return modelAndView;

    }
    
    
    @RequestMapping("/userCenterA")
    public ModelAndView userCenterA(ModelAndView modelAndView, HttpSession session) {

        String userType = (String) session.getAttribute(Const.USERINFO_USER_TYPE);//用户类型 
        modelAndView.setViewName("user/mainCenter");

        return modelAndView;

    }
    

    /**
     * 个人用户切换操作群体用户
     *
     * @param modelAndView
     * @param session
     * @param aid
     * @param groupName
     * @param groupType
     * @return
     */
    @RequestMapping("/jumpToGroupView")
    public ModelAndView jumpToGroupView(HttpServletRequest request,ModelAndView modelAndView, HttpSession session, String aid, String groupName, String groupType) {
        try {

            String userType = (String) session.getAttribute(Const.USERINFO_USER_TYPE);
            String userId = (String) session.getAttribute(Const.USERINFO_USER_ID);
            String userAid = Util.getCurrentSelUserAid(session);
            String userPlatformId = (String) session.getAttribute(Const.USERINFO_PLATFORM_ID);
            String userMallId = (String) session.getAttribute(Const.USERINFO_MALL_ID);
            
            
    	    DBPersistenceManager pm=null;
    	    pm=DBAccessHelper.getPMByName(Const.DataSourceName); 
			//北上资金累计净买入的表
			String checkconfTableSql="select * from uip_northfundnetbuy ORDER BY trade_date";
        	DataSet dsdata = (DataSet) pm.executeQuery(checkconfTableSql);
        	
        	  SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd"); 
        	  int i=0;        	
	        if (dsdata!=null) {
	        	int count=dsdata.getRowCount();
	        	
	        	String[] tradeDateArray=new String [count];     //日期
	            String[] cumulativeNetBuyArray = new String[count];   //累计净买入
	            
	            StringBuffer tradeDateSb = new StringBuffer();
	            StringBuffer cumulativeNetBuySb = new StringBuffer();
	            tradeDateSb.append("[");
	            cumulativeNetBuySb.append("[");
	            
	            String tradeDate=null;
	            String cumulativeNetBuy=null;
	            while (dsdata!=null&&dsdata.next()) {	            	
	            	    tradeDate = dff.format(dff.parse(dsdata.getString("trade_date")));
	            	    cumulativeNetBuy = dsdata.getString("cumulative_net_buy");
	            	   
	            	    System.out.println("i:"+i+"日期：:"+tradeDate);
	            	    
	                   BigDecimal celljeBd=new BigDecimal(cumulativeNetBuy);
	                    cumulativeNetBuy=celljeBd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();   ////北向合计  累计净买入

	            	   if(tradeDate != null &&  tradeDate.length() != 0  && cumulativeNetBuy  != null && cumulativeNetBuy.length() != 0 ){
	            		   tradeDateSb.append("'"+tradeDate+"',");
	            		   cumulativeNetBuySb.append("'"+cumulativeNetBuy+"',");
	            	   }
	            	   i++;
	            	   
	            	 

	            }	            
	            String tradeDateStr = tradeDateSb.substring(0, tradeDateSb.length()-1);
	            tradeDateStr=tradeDateStr+"]";
	            String cumulativeNetBuyStr = cumulativeNetBuySb.substring(0, cumulativeNetBuySb.length()-1);
	            cumulativeNetBuyStr=cumulativeNetBuyStr+"]";

	            System.out.println("tradeDateStr:"+tradeDateStr+",cumulativeNetBuyStr:"+cumulativeNetBuyStr);
	            
		        modelAndView.addObject("tradeDate",tradeDateStr);
		        modelAndView.addObject("cumulativeNetBuy",cumulativeNetBuyStr);
	            
            }
        	



            
            
            
            modelAndView.setViewName("/welcomeMenu");
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("/error/index404");
            modelAndView.addObject("errorMsg", "loginMain 异常请联系管理员");
            _LOG.error("异常信息：" + e.toString() + "Author：lzq");
        }
        return modelAndView;
    }


  //跳转每日北上资金数据
    @RequestMapping("/jumpToEvereDayNorthFundData")
    public ModelAndView jumpToEvereDayNorthFundData(HttpServletRequest request,ModelAndView modelAndView, HttpSession session, String aid, String groupName, String groupType) {
        try {

            String userType = (String) session.getAttribute(Const.USERINFO_USER_TYPE);
            String userId = (String) session.getAttribute(Const.USERINFO_USER_ID);
            String userAid = Util.getCurrentSelUserAid(session);
            String userPlatformId = (String) session.getAttribute(Const.USERINFO_PLATFORM_ID);
            String userMallId = (String) session.getAttribute(Const.USERINFO_MALL_ID);
            modelAndView.setViewName("/uiPath/evereDayNorthFundData");
            
           
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("/error/index404");
            modelAndView.addObject("errorMsg", "loginMain 异常请联系管理员");
            _LOG.error("异常信息：" + e.toString() + "Author：lzq");
        }
        return modelAndView;
    }
    
    
    //跳转北上资金累计净买入
       @RequestMapping("/jumpToNorthFundNetBuy")
    public ModelAndView jumpToNorthFundNetBuy(HttpServletRequest request,ModelAndView modelAndView, HttpSession session, String aid, String groupName, String groupType) {
        try {

            String userType = (String) session.getAttribute(Const.USERINFO_USER_TYPE);
            String userId = (String) session.getAttribute(Const.USERINFO_USER_ID);
            String userAid = Util.getCurrentSelUserAid(session);
            String userPlatformId = (String) session.getAttribute(Const.USERINFO_PLATFORM_ID);
            String userMallId = (String) session.getAttribute(Const.USERINFO_MALL_ID);
            modelAndView.setViewName("/uiPath/northFundNetBuy");
            
           
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("/error/index404");
            modelAndView.addObject("errorMsg", "loginMain 异常请联系管理员");
            _LOG.error("异常信息：" + e.toString() + "Author：lzq");
        }
        return modelAndView;
    }
    
    
    //北上资金前十大活跃股  northFundTopTen
    @RequestMapping("/jumpToNorthFundTopTen")
    public ModelAndView jumpToNorthFundTopTen(HttpServletRequest request,ModelAndView modelAndView, HttpSession session, String aid, String groupName, String groupType) {
        try {

            String userType = (String) session.getAttribute(Const.USERINFO_USER_TYPE);
            String userId = (String) session.getAttribute(Const.USERINFO_USER_ID);
            String userAid = Util.getCurrentSelUserAid(session);
            String userPlatformId = (String) session.getAttribute(Const.USERINFO_PLATFORM_ID);
            String userMallId = (String) session.getAttribute(Const.USERINFO_MALL_ID);
            modelAndView.setViewName("/uiPath/northFundTopTen");
            
           
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("/error/index404");
            modelAndView.addObject("errorMsg", "loginMain 异常请联系管理员");
            _LOG.error("异常信息：" + e.toString() + "Author：lzq");
        }
        return modelAndView;
    }
    
    //jumpToLongHuList
    //北上资金前十大活跃股  northFundTopTen
    @RequestMapping("/jumpToLongHuList")
    public ModelAndView jumpToLongHuList(HttpServletRequest request,ModelAndView modelAndView, HttpSession session, String aid, String groupName, String groupType) {
        try {

            String userType = (String) session.getAttribute(Const.USERINFO_USER_TYPE);
            String userId = (String) session.getAttribute(Const.USERINFO_USER_ID);
            String userAid = Util.getCurrentSelUserAid(session);
            String userPlatformId = (String) session.getAttribute(Const.USERINFO_PLATFORM_ID);
            String userMallId = (String) session.getAttribute(Const.USERINFO_MALL_ID);
            modelAndView.setViewName("/uiPath/longHuList");
                       
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("/error/index404");
            modelAndView.addObject("errorMsg", "loginMain 异常请联系管理员");
            _LOG.error("异常信息：" + e.toString() + "Author：lzq");
        }
        return modelAndView;
    }
    
    
	//导入微信订单 到客户收款
/*	@ResponseBody*/
	/*@RequestMapping(value = "/importWxOrder", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=UTF-8")	
	public String importWxOrder(HttpServletRequest request) {
		System.out.println("进入 importWxOrder 方法");
		return "";
	}*/

	@ResponseBody
    @RequestMapping("/importWxOrder")
    public ModelAndView importWxOrder(HttpServletRequest request,ModelAndView modelAndView, HttpSession session){ 
		System.out.println("进入 importWxOrder 方法");
	    modelAndView.setViewName("/uiPath/longHuList");
    	   return modelAndView;
    }

}
