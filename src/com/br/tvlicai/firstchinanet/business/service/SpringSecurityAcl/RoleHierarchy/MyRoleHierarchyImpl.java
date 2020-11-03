/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.business.service.SpringSecurityAcl.RoleHierarchy;

import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.br.tvlicai.firstchinanet.commom.Const.Const;
import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.drm.dataexpress.DataSet;
import com.neusoft.unieap.util.DBAccessHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Add the description here.
 * Create on 2016/5/13
 *
 * @author xuhai
 * @version 0.0.0
 */
public class MyRoleHierarchyImpl implements RoleHierarchy {


    private Map<String, List<String>> roleMap;

    private long lastTimestamp = 0l;
    
    public MyRoleHierarchyImpl(){
        int i = 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getReachableGrantedAuthorities(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null || authorities.isEmpty()) {
            return AuthorityUtils.NO_AUTHORITIES;
        }

        List<GrantedAuthority> reachableGrantedAuthorities=new ArrayList<GrantedAuthority>();
        List<GrantedAuthority> currentAuthorities = new ArrayList<GrantedAuthority>();
        currentAuthorities.addAll(authorities);
        reachableGrantedAuthorities.addAll(currentAuthorities);

        if(currentAuthorities.get(0).getAuthority().equals("ROLE_ANONYMOUS")){
            return reachableGrantedAuthorities;
        }

        List<String> roleNames = new ArrayList<>();
        for(GrantedAuthority grantedAuthority : authorities){
            roleNames.add(new String(grantedAuthority.getAuthority()));
        }

        List<String> reachableRoles = getReachableRoles(roleNames);

        for(String roleName : reachableRoles){
            reachableGrantedAuthorities.add(new SimpleGrantedAuthority(roleName));
        }

        return reachableGrantedAuthorities;
    }
    
    
    public List<String> getReachableRoles(List<String> roleNames) {
        Map<String, List<String>> roleMap = getRoleMap();

        List<String> roleList = new ArrayList<>();

        for(String rn : roleNames){
            List<String> tmpList = roleMap.get(rn);
            if(tmpList != null){
                roleList.addAll(tmpList);
            }
        }

        return roleList;
    }
    
    
    private Map<String, List<String>> getRoleMap(){
        long currTimestamp = System.currentTimeMillis();

        if(currTimestamp - lastTimestamp > 1000*30){
           
            List<BruRoleParent> bruRoleParents = new ArrayList();     
            DBPersistenceManager pm=null;
            try {
            	pm=DBAccessHelper.getPMByName(Const.DataSourceName);
               	
            	DataSet ds=(DataSet)pm.executeQuery("select ROLE_PID, ROLE_ID, CREATED_USER_ID, UPDATED_USER_ID, CREATED_DATE,"
            			+ " UPDATED_DATE,  DELETE_TYPE from bru_role_parent where DELETE_TYPE = 1 ");
            	while(ds!=null&&ds.next()) {
            		
            		BruRoleParent bruRoleParent = new BruRoleParent();
            		bruRoleParent.setRolePid(ds.getString("ROLE_PID"));
            		bruRoleParent.setRoleId(ds.getString("ROLE_ID"));
            		bruRoleParent.setDeleteType(ds.getString("DELETE_TYPE"));
            		bruRoleParents.add(bruRoleParent);
            	}
            	
            } catch (Exception e) {
            }finally {
            	if(pm!=null) {
            		pm.close();
            	}
            	
            }
            roleMap = makeRoleMap(bruRoleParents);
            lastTimestamp = currTimestamp;
        }

        return roleMap;
    }
    private Map<String, List<String>> makeRoleMap(List<BruRoleParent> bruRoleParents){

        MapHelper mapHelper = new MapHelper(bruRoleParents);

        return mapHelper.makeParentMap();
    }
    class MapHelper{
        private Map<String, List<String>> roleMap = new HashMap<>();

        private Map<String, List<String>> parentRoleMap = new HashMap<>();

        private List<BruRoleParent> bruRoleParents;

        public MapHelper(List<BruRoleParent> bruRoleParents){
            this.bruRoleParents = bruRoleParents;
        }

        private void insert(Map<String, List<String>> map, String key, String value){
            List<String> list = map.get(key);

            if(list == null){
                list = new ArrayList<>();
                map.put(key, list);
            }

            list.add(value);
        }


        public Map<String, List<String>> makeParentMap(){

            for(BruRoleParent bruRoleParent : bruRoleParents){
                insert(roleMap, bruRoleParent.getRoleId(), bruRoleParent.getRolePid());
            }

            for(Map.Entry<String, List<String>> entry : roleMap.entrySet()){
                List<String> parRoleIds = entry.getValue();
                String key = entry.getKey();
                List<String> newList = new ArrayList<>(parRoleIds);
                findAndInsert(key, newList, parRoleIds);
                parentRoleMap.put(key, newList);
            }

            return parentRoleMap;
        }

        private void findAndInsert(String guard, List<String> newList, List<String> oldParList){
            for(String pr : oldParList){
                if(!guard.equals(pr)){
                    List<String> tmpList = roleMap.get(pr);
                    if(tmpList != null){
                        newList.addAll(tmpList);
                        findAndInsert(guard, newList, tmpList);
                    }
                }
            }
        }

    }
}
