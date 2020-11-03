/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.business.service.SpringSecurityAcl.MySecurity;

import java.util.HashSet;
import java.util.Set;

import com.br.tvlicai.firstchinanet.business.service.SpringSecurityAcl.RoleHierarchy.Role;

/**
 * Add the description here.
 * Create on 2016/5/12
 *
 * @author xuhai
 * @version 0.0.0
 */
public class User {
    private String id;//*
    private String username;//*登录名
    private String password;//*密码
    private boolean enabled;//*是否有效
    private Set<Role> roles = new HashSet<Role>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        roles.add(role);
    }
}
