package com.br.tvlicai.firstchinanet.web.controller.password;

import com.br.tvlicai.firstchinanet.business.common.Util.Util;
import com.br.tvlicai.firstchinanet.commom.Const.Const;
import com.br.tvlicai.firstchinanet.commom.utils.MD5Util;
import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.drm.dataexpress.DataSet;
import com.neusoft.unieap.util.DBAccessHelper;
import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by wzy on 2016/8/2.
 */
@Controller
@RequestMapping("/")
public class PasswordController {
    static final Logger _LOG = LoggerFactory.getLogger(PasswordController.class);


    /**
     * 忘记密码跳转
     * @param modelAndView
     * @return
     */
    @RequestMapping("/resetPasswordPre")
    public ModelAndView resetPasswordPre(ModelAndView modelAndView) {
        try {
            modelAndView.setViewName("user/systemUpdatePassword");
        } catch (Exception e) {
            modelAndView.setViewName("/error/index404");
            modelAndView.addObject("errorMsg", "resetPasswordPre 异常请联系管理员");
            _LOG.error("异常信息：" + e.toString() + "Author：wzy");
        }
        return modelAndView;
    }

    /**
     * 单位用户修改密码
     *
     * @param session
     * @return
     */
    @RequestMapping("/systemUpdatePassword")
    public ModelAndView systemUpdatePassword(ModelAndView modelAndView, HttpSession session, String againPassword, String checkPassword,String userId) {
     
    DBPersistenceManager pm = null;
     try {
        	pm = DBAccessHelper.getPMByName(Const.DataSourceName);
        	String a=userId;
        	if(a.endsWith(",")){
        		a=a.substring(0,a.length()-1);
        	}
        	DataSet ds=(DataSet)pm.executeQuery("select count(1) as num from  	bru_authentication  WHERE user_id='"+a+"' "); 
     	    if(ds!=null&&ds.next())
     	    {
     		  int select_flag=Util.isCon(ds.getString("num"))?Integer.parseInt(ds.getString("num")):0;
	     		 if (select_flag > 0) {
	             } else {
	            	 modelAndView.addObject("userNameExist", "用户名不存在");
	                 modelAndView.setViewName("user/resetPasswordSecond");
	                 return modelAndView;
	             }
     	    }
             
     	    int update_flag =  pm.executeUpdate("update bru_authentication set UPDATED_USER_ID='"+a+"', UPDATED_DATE=now(),password='"+Util.md5encode(againPassword, a)+"'   WHERE user_id='"+a+"'   ");
         
             if (update_flag != 0) {
                 modelAndView.addObject("update_fail", "修改密码成功！");
             } else {
                 modelAndView.addObject("update_fail", "原密码不正确！");
             }
        } catch (Exception e) {
            modelAndView.addObject("update_fail", "原密码不正确！");
            _LOG.error("systemUpdatePassword异常信息：" + e.toString() + "Author：wzy");
        }finally{
        	if(pm!=null)
        	{
        		pm.close();
        	}
        	
        }
        modelAndView.setViewName("user/systemUpdatePassword");
        return modelAndView;
    }
}