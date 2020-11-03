/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.business.service.SpringSecurityAcl.MySecurity;


import com.br.tvlicai.firstchinanet.app.controller.menu.BrtCmnMenuMall;
import com.br.tvlicai.firstchinanet.commom.Const.Const;
import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.drm.dataexpress.DataSet;
import com.neusoft.unieap.util.DBAccessHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * Add the description here.
 * Create on 2016/5/12
 *
 * @author xuhai
 * @version 0.0.0
 */
public class MyInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    static final Logger _LOG = LoggerFactory.getLogger(MyInvocationSecurityMetadataSourceService.class);
    
    private List<BrtCmnMenuMall> bruModuleInfos;
    
    private long lastTimestamp = 0l;

    final static Pattern pattern = Pattern.compile("^.*?\\.(js|png|jpg|jpeg|bmp|gif|css|icon|swf)$");

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        String url = ((FilterInvocation) object).getRequestUrl();
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        DBPersistenceManager pm=null;
        if(url.equals("/"))
        {
        	
        }else{
        	if(url.indexOf(".")==-1)
        	{
		        try {
		        	pm=DBAccessHelper.getPMByName(Const.DataSourceName);
		        	int i=pm.executeUpdate("INSERT INTO sys_access_log( REQUEST_URL, REQUEST_METHOD, REQUEST_USER_AGENT, REFERER, START_TIME, END_TIME, RESPONSE_CODE, REMOTE_IP) "
		                          +"VALUES ('"+url+"', '"+request.getMethod()+"', '', '', now(), '2018-03-28 12:26:47', 200, '"+request.getRemoteAddr()+"')");
		          	
		        } catch (Exception e) {
		        }finally {
	            	if(pm!=null) {
	            		pm.close();
	            	}
	            	
	            }
        	}
        }
        _LOG.debug("url:" + url);
        List<ConfigAttribute> result = new ArrayList<ConfigAttribute>();

        try {
            Set<String> roles =getRolesByUri(url);
            for (String role : roles) {
                ConfigAttribute conf = new SecurityConfig(role);
                result.add(conf);
            }
        } catch (Exception e) {
            _LOG.error("异常信息", e);
        }
        return result;
    }

    

    //根据请求的url路径，获取所对应的权限要求
    public Set<String> getRolesByUri(String requestUri) {
        Set<String> roles = new HashSet<>();


        // start 关于开发中画面特殊处理
        if (requestUri.equals("/businessDeveloping")) {
            return roles;
        }
        // end 关于开发中画面特殊处理

        if(!urlFilte(requestUri)){
            return roles;
        }


        List<BrtCmnMenuMall> plist = this.getPermList();

        if(plist != null){
            for (BrtCmnMenuMall brbMenuMgBean : plist) {
                String targetUrl = brbMenuMgBean.getTargetUrl();
                if(targetUrl == null){
                    continue;
                }
                if (urlMatcher(targetUrl, requestUri)){
                    roles.add(brbMenuMgBean.getRoleId());
                }
            }
        }

        return roles;
    }

    private List<BrtCmnMenuMall> getPermList(){
        long currTimestamp = System.currentTimeMillis();
        if(currTimestamp - lastTimestamp >= 30*1000){
        	
            List<BrtCmnMenuMall> bruModuleInfos =new ArrayList();
            
            
            DBPersistenceManager pm=null;
            try {
            	pm=DBAccessHelper.getPMByName(Const.DataSourceName);
               	
            	DataSet ds=(DataSet)pm.executeQuery(" SELECT  " + 
            			"            m.*,  " + 
            			"            rm.ROLE_ID AS ROLE_ID  " + 
            			"          FROM  " + 
            			"            brt_cmn_menu_mall AS m,  " + 
            			"            brt_cmn_menu_role_ship AS rm  " + 
            			"          WHERE  " + 
            			"            m.MALL_MENU_ID = rm.MALL_MENU_ID  " + 
            			"            AND m.PLATFORM_ID = rm.PLATFORM_ID  " + 
            			"            AND m.MALL_ID = rm.MALL_ID  " + 
            			"            AND m.SYSTEM_ID = rm.SYSTEM_ID  " + 
            			"            AND m.DELETE_TYPE = rm.DELETE_TYPE  " + 
            			"            AND m.USE_FLAG = '1'  " + 
            			"            AND m.DELETE_TYPE = '1'  " + 
            			"            AND m.TARGET_URL IS NOT NULL  " + 
            			"          ORDER BY m.MALL_MENU_ID");
            	while(ds!=null&&ds.next()) {
            		
            		BrtCmnMenuMall bcmm = new BrtCmnMenuMall();
            		bcmm.setMallMenuId(ds.getString("MALL_MENU_ID"));
            		bcmm.setPlatformMenuId(ds.getString("PLATFORM_MENU_ID"));
            		bcmm.setPlatformId(ds.getString("PLATFORM_ID"));
            		bcmm.setMallId(ds.getString("MALL_ID"));
            		bcmm.setSystemId(ds.getString("SYSTEM_ID"));
            		bcmm.setMenuLevel(ds.getString("MENU_LEVEL"));
            		bcmm.setParentMenuId(ds.getString("PARENT_MENU_ID"));
            		bcmm.setMenuType(ds.getString("MENU_TYPE"));
            		bcmm.setMenuSeq(ds.getInt("MENU_SEQ"));
            		bcmm.setMenuName(ds.getString("MENU_NAME"));
            		bcmm.setMenuImgId(ds.getString("MENU_IMG_ID"));
            		bcmm.setTargetType(ds.getString("TARGET_TYPE"));
            		bcmm.setTargetUrl(ds.getString("TARGET_URL"));
            		bcmm.setUrlType(ds.getString("URL_TYPE"));
            		bcmm.setDefaultFlag(ds.getString("DEFAULT_FLAG"));
            		bcmm.setUseFlag(ds.getString("USE_FLAG"));
            		
            		bcmm.setVersionNo(ds.getString("VERSION_NO"));
            		bcmm.setDeleteType(ds.getString("DELETE_TYPE"));
            		bcmm.setApproveFlag(ds.getString("APPROVE_FLAG"));
            		
            		bcmm.setRoleId(ds.getString("ROLE_ID"));
            		bruModuleInfos.add(bcmm);
            	}
            	
            } catch (Exception e) {
            }finally {
            	if(pm!=null) {
            		pm.close();
            	}
            	
            }
            this.bruModuleInfos = bruModuleInfos;
            lastTimestamp = currTimestamp;
        }

        return bruModuleInfos;
    }

    //路径匹配
    private boolean urlMatcher(String permstr, String requestUri) {
        boolean isMatcher = false;
        PathMatcher matcher = new AntPathMatcher();
        isMatcher = matcher.match(permstr, requestUri);
        return isMatcher;
    }

    private boolean urlFilte(String url){
        Matcher matcher = pattern.matcher(url);

        if(matcher.find()) {
            return false;
        }
        return true;
    }
    
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }

}
