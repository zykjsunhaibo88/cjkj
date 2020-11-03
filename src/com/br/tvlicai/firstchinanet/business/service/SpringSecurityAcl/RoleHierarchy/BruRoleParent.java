package com.br.tvlicai.firstchinanet.business.service.SpringSecurityAcl.RoleHierarchy;

import com.br.tvlicai.firstchinanet.domain.BaseDomain;

public class BruRoleParent extends BaseDomain {
    private String rolePid;//角色父Id

    private String roleId;//角色Id

    public String getRolePid() {
        return rolePid;
    }

    public void setRolePid(String rolePid) {
        this.rolePid = rolePid == null ? null : rolePid.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}