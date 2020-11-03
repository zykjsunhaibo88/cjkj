package com.br.tvlicai.firstchinanet.web.controller.login;

import com.br.tvlicai.firstchinanet.domain.BaseDomain;

import java.util.Date;

public class BruRoleDefinition extends BaseDomain {

    private String roleId;//角色Id

    private String roleType;//角色类型

    private String roleName;//角色名称

    private String platformId;//平台ID

    private String roleDescription;//角色描述

    private String selectFlag;//

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(String selectFlag) {
        this.selectFlag = selectFlag;
    }
}