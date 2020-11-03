/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.business.service.SpringSecurityAcl.MySecurity;


import com.br.tvlicai.firstchinanet.business.common.Util.Util;
import com.br.tvlicai.firstchinanet.business.service.SpringSecurityAcl.RoleHierarchy.Role;
import com.br.tvlicai.firstchinanet.commom.Const.Const;
import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.drm.dataexpress.DataSet;
import com.neusoft.unieap.util.DBAccessHelper;
import com.br.tvlicai.firstchinanet.web.controller.login.BruAuthentication;
import com.br.tvlicai.firstchinanet.web.controller.login.BruRoleDefinition;
import com.br.tvlicai.firstchinanet.web.controller.login.BruRoleUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Add the description here.
 * Create on 2016/5/12
 *
 * @author xuhai
 * @version 0.0.0
 */
public class MyUserDetailsService implements UserDetailsService {
    static final Logger _LOG = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        User user = new User();
        try {
            user = getUserByName(username);
            Set<Role> roles = user.getRoles();
            for(Role role : roles){
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRolename());
                auths.add(authority);
            }
        } catch (Exception e) {
            _LOG.error("异常信息", e);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), true, true, true, true,
                auths);
    }
    
    //根据用户名获取用户信息，包括用户的用户名和密码，和角色信息
    public User getUserByName(String username) {
        User user = new User();

        if(fillUserBaseInfo(username, user)){
            fillUserRoleInfo(username, user);
        }

        return user;
    }

    private boolean fillUserBaseInfo(String username, User user){
 
        
        BruAuthentication b = new BruAuthentication();
        DBPersistenceManager pm=null;
        try {

            pm=DBAccessHelper.getPMByName(Const.DataSourceName);
	        DataSet ds=(DataSet)pm.executeQuery("select     USER_AID, USER_ID, password, user_type, last_login_date, USER_STS_CD, CREATED_USER_ID, " + 
	        		"    CREATED_DATE, UPDATED_USER_ID, UPDATED_DATE, DELETE_TYPE  from BRU_AUTHENTICATION  where DELETE_TYPE=1  AND  binary USER_ID = '"+username+"' ");     
	        if (ds!=null&&ds.next()) {
	        	
	        	b.setUserAid(ds.getString("USER_AID"));
	        	b.setUserId(ds.getString("USER_ID"));
	        	b.setPassword(ds.getString("password"));
	        	b.setUserType(ds.getString("user_type"));
	        	b.setLastLoginDate(ds.getDate("last_login_date"));
	        	b.setUserStsCd(ds.getString("USER_STS_CD"));
	        	b.setDeleteType(ds.getString("DELETE_TYPE"));
	        }
        }catch (Exception e) {
        	_LOG.error("MyUserDetailsService1：异常 " + username + "认证信息获取失败 Author：xh");
        }finally{
        	if(pm!=null){
        		pm.close();
        	}
        }    
        if(b != null){
            user.setUsername(username);
            user.setPassword(b.getPassword());
            user.setEnabled(true);
            return true;
        }
        return false;
    }

    private void fillUserRoleInfo(String username, User user){
        List<String> roleIds =queryAllRoleIdsByUserId(username);
        for (String roleId : roleIds) {
            Role role = new Role(roleId);
            user.addRole(role);
        }
    }
    
    public List<String> queryAllRoleIdsByUserId(String userId) {
        List<String> ids = new ArrayList<>();
        List<BruRoleDefinition> lst = this.queryRoleListByUserId(userId);

        for (int i = 0; i < lst.size() ; i++) {
            ids.add(lst.get(i).getRoleId());
        }
        return ids;
    }
    
    public List<BruRoleDefinition> queryRoleListByUserId(String userId) {
        List<BruRoleDefinition> roleList = new ArrayList<>();
        List<String> roleIds = new ArrayList<>();
        
        
        DBPersistenceManager pm=null;
        try {

        //系统未阅
            pm=DBAccessHelper.getPMByName(Const.DataSourceName);
            
	            
	        if (Util.isCon(userId)) {
	        	 BruAuthentication ba = new BruAuthentication();
		            ba.setUserId(userId);
		            DataSet ds=(DataSet)pm.executeQuery("select     USER_AID, USER_ID, password, user_type, last_login_date, USER_STS_CD, CREATED_USER_ID, " + 
			        		"    CREATED_DATE, UPDATED_USER_ID, UPDATED_DATE, DELETE_TYPE  from BRU_AUTHENTICATION  where DELETE_TYPE=1  AND  binary USER_ID = '"+userId+"' ");     
			        if (ds!=null&&ds.next()) {
			        	
			        	ba.setUserAid(ds.getString("USER_AID"));
			        	ba.setUserId(ds.getString("USER_ID"));
			        	ba.setPassword(ds.getString("password"));
			        	ba.setUserType(ds.getString("user_type"));
			        	ba.setLastLoginDate(ds.getDate("last_login_date"));
			        	ba.setUserStsCd(ds.getString("USER_STS_CD"));
			        	ba.setDeleteType(ds.getString("DELETE_TYPE"));
		            }
	            if (Util.isCon(ba)) {
	                String userAid = ba.getUserAid();
	                String userType = ba.getUserType();
	                //取直接关联用户角色信息
	                List<BruRoleUser> ruList = new ArrayList();
            		DataSet dsRole=(DataSet)pm.executeQuery("select    USER_AID, ROLE_ID, CREATED_USER_ID, UPDATED_USER_ID,"
            				+ "  CREATED_DATE, UPDATED_DATE, " + 
            				"    DELETE_TYPE   from bru_role_user"
   			        		+ "  where DELETE_TYPE=1   AND  USER_AID = '"+userAid+"' ");     
   			        while (dsRole!=null&&dsRole.next()) {
   			            BruRoleUser bru = new BruRoleUser();
   			            bru.setUserAId(dsRole.getString("USER_AID"));
   			        	bru.setRoleId(dsRole.getString("ROLE_ID"));
   			        	bru.setDeleteType(dsRole.getString("DELETE_TYPE"));
   			        	ruList.add(bru);
   		            }
	                if (Util.isCon(ruList) && ruList.size() > 0) {
	                    for (BruRoleUser bruRoleUser : ruList) {
	                        roleIds.add(bruRoleUser.getRoleId());
	                    }
	                }
	            }
	        }
	        //去重
	        List<String> listWithoutDup = new ArrayList<>(new HashSet<>(roleIds));
	        if (Util.isCon(listWithoutDup) && listWithoutDup.size() > 0) {
	            for (String s : listWithoutDup) {
	                BruRoleDefinition br = new BruRoleDefinition();
	                DataSet dsRoleD=(DataSet)pm.executeQuery(" select   ROLE_ID, ROLE_TYPE, ROLE_NAME, PLATFORM_ID,"
	                		+ "  ROLE_DESCRIPTION, CREATED_USER_ID, UPDATED_USER_ID, " + 
	                		"    CREATED_DATE, UPDATED_DATE, DELETE_TYPE, SELECT_FLAG" + 
            				"        from bru_role_definition "
   			        		+ "  where DELETE_TYPE=1   AND  ROLE_ID = '"+s+"' ");     
   			        if (dsRoleD!=null&&dsRoleD.next()) {
   			        	br.setRoleId(dsRoleD.getString("ROLE_ID"));
   			        	br.setRoleType(dsRoleD.getString("ROLE_TYPE"));
   			        	br.setRoleName(dsRoleD.getString("ROLE_NAME"));
   			        	br.setPlatformId(dsRoleD.getString("PLATFORM_ID"));
   			        	br.setRoleDescription(dsRoleD.getString("ROLE_DESCRIPTION")+"");
   			        	br.setSelectFlag(dsRoleD.getString("SELECT_FLAG"));
   			        }
	                roleList.add(br);
	            }
	        }
        }catch (Exception e) {
        	_LOG.error("MyUserDetailsService2：异常 " + userId + "认证信息获取失败 Author：xh");
        }finally{
        	if(pm!=null){
        		pm.close();
        	}
        }    
        return roleList;
    }

}
