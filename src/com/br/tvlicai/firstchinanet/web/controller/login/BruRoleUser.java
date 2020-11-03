package com.br.tvlicai.firstchinanet.web.controller.login;

import com.br.tvlicai.firstchinanet.domain.BaseDomain;

public class BruRoleUser extends BaseDomain {
    private String userAId;//用户认证Id

    private String roleId;//角色Id

    public String getUserAId() {
        return userAId;
    }

    public void setUserAId(String userAId) {
        this.userAId = userAId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}