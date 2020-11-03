package com.br.tvlicai.firstchinanet.web.controller.login;

import com.br.tvlicai.firstchinanet.domain.BaseDomain;

import java.util.Date;

public class BruAuthentication extends BaseDomain {
    private String userAid;

    private String userId;

    private String password;

    private String userType;

    private Date lastLoginDate;

    private String userStsCd;

    private Date infoChangeDate;

    public Date getInfoChangeDate() {
        return infoChangeDate;
    }

    public void setInfoChangeDate(Date infoChangeDate) {
        this.infoChangeDate = infoChangeDate;
    }

    public String getUserAid() {
        return userAid;
    }

    public void setUserAid(String userAid) {
        this.userAid = userAid == null ? null : userAid.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getUserStsCd() {
        return userStsCd;
    }

    public void setUserStsCd(String userStsCd) {
        this.userStsCd = userStsCd == null ? null : userStsCd.trim();
    }

}