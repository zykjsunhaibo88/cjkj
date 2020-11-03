package com.br.tvlicai.firstchinanet.app.controller.base;

import com.br.tvlicai.firstchinanet.business.common.Util.Util;
import com.br.tvlicai.firstchinanet.commom.Const.Const;
import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.drm.dataexpress.DataSet;
import com.neusoft.unieap.util.DBAccessHelper;
import com.br.tvlicai.firstchinanet.app.controller.menu.BrtCmnMenuMall;
import com.br.tvlicai.firstchinanet.app.controller.menu.MenuInfo;
import com.br.tvlicai.firstchinanet.app.controller.BruUserMemberInfo;
import com.br.tvlicai.firstchinanet.app.controller.UserPersonalUIBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by peiye on 2016/7/30.
 */
public class BaseController {

    public void headInit(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type;charset=UTF-8");
    }
   

  
    /**
     * 判断token访问用户是否有appType端操作权限
     *
     * @param token
     * @param appType
     * @return
     */
    protected boolean checkAccessAuthority(String userAid, String appType) {
        boolean flag = false;
        if (Util.isCon(userAid)) {

            if (Util.isCon(appType)) {
               
                List<BruUserMemberInfo> gulist =new ArrayList();
                DBPersistenceManager pm=null;
                try {
                	pm=DBAccessHelper.getPMByName(Const.DataSourceName);
                	String sql=" select        USER_MEMBER_ID, PLATFORM_ID, MALL_ID, GROUP_USER_AID, MEMBER_AID, MEMBER_ROLE_ID, " + 
                			"    MEMBER_STS_CD, JOIN_DATE, LEAVE_DATE, CREATED_USER_ID, CREATED_DATE, UPDATED_USER_ID, " + 
                			"    UPDATED_DATE, DELETE_TYPE" + 
                			"                    	    from bru_user_member_info where MEMBER_AID='"+userAid+"'"
                					+ " and MEMBER_STS_CD=1 and DELETE_TYPE=1 ";
                	
                	 if (appType.equals("0")) {//司机
                		 sql=sql+" and MEMBER_ROLE_ID='ROLE_LOG_DRIVR'";
                     } else if (appType.contains("1")) {//调度
                    	 sql=sql+" and MEMBER_ROLE_ID='ROLE_PJ_DISPR'";
                     } else if (appType.contains("2")) {//签收
                         sql=sql+" and MEMBER_ROLE_ID='ROLE_DPR_RECPT'";
                     }
                	sql=sql+" ORDER BY CREATED_DATE DESC,MEMBER_AID";
                	DataSet ds=(DataSet)pm.executeQuery(sql);
                	while(ds!=null&&ds.next()){
                		BruUserMemberInfo bumi=new BruUserMemberInfo();
                		bumi.setUserMemberId(ds.getString("USER_MEMBER_ID"));
                		bumi.setPlatformId(ds.getString("PLATFORM_ID"));
                		bumi.setMallId(ds.getString("MALL_ID"));
                		bumi.setGroupUserAid(ds.getString("GROUP_USER_AID"));
                		bumi.setMemberAid(ds.getString("MEMBER_AID"));
                		bumi.setMemberRoleId(ds.getString("MEMBER_ROLE_ID"));
                		bumi.setMemberStsCd(ds.getString("MEMBER_STS_CD"));
                		gulist.add(bumi);
                	}
                	
                } catch (Exception e) {
                }finally {
                	if(pm!=null) {
                		pm.close();
                	}
                	
                }
                if (Util.isCon(gulist) && gulist.size() > 0) {
                    flag = true;
                }
            }
        
        }
        return flag;
    }

   
    /**
     *
     * @param date 系统时间
     * @param hour 日期节点（小时）
     * @param day 起始累计天数（负数）
     * @return
     */
    protected Date getDateStart(Date date, int hour, int day){

        int intSysHour = Integer.parseInt(this.getHour(date));

        Calendar c = Calendar.getInstance();
        c.setTime(date);//设置当前日期  

        //若系统小时 小于 日期节点小时
        if (intSysHour < hour){
            c.add(Calendar.DATE,-1);//日期减1天  
        }
        c.add(Calendar.DATE,day);//
        c.set(Calendar.HOUR_OF_DAY,hour);//时  
        c.set(Calendar.MINUTE,00);//分  
        c.set(Calendar.SECOND,00);//秒  
        return  c.getTime();
    }

    /**
     * 得到现在小时
     */
    protected String getHour(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

}
