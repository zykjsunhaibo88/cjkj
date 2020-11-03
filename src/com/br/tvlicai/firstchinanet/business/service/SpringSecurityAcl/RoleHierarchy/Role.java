/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.business.service.SpringSecurityAcl.RoleHierarchy;

import java.util.HashSet;
import java.util.Set;

/**
 * Add the description here.
 * Create on 2016/5/12
 *
 * @author xuhai
 * @version 0.0.0
 */
public class Role {
    private String id;//*
    private String rolename;//*

    public Role(){

    }

    public Role(String rolename){
        this.rolename = rolename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
