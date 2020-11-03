package com.br.tvlicai.firstchinanet.app.controller;

import com.br.tvlicai.firstchinanet.domain.BaseDomain;
import com.fasterxml.jackson.databind.deser.Deserializers;

import java.util.Date;

public class BruUserMemberInfo extends BaseDomain {
    private String userMemberId;//

    private String platformId;//

    private String mallId;

    private String groupUserAid;//

    private String memberAid;//

    private String memberRoleId;//

    private String memberStsCd;//

    private Date joinDate;//

    private Date leaveDate;//

    public String getUserMemberId() {
        return userMemberId;
    }

    public void setUserMemberId(String userMemberId) {
        this.userMemberId = userMemberId == null ? null : userMemberId.trim();
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }


    public String getGroupUserAid() {
        return groupUserAid;
    }

    public void setGroupUserAid(String groupUserAid) {
        this.groupUserAid = groupUserAid;
    }

    public String getMemberAid() {
        return memberAid;
    }

    public void setMemberAid(String memberAid) {
        this.memberAid = memberAid == null ? null : memberAid.trim();
    }

    public String getMemberRoleId() {
        return memberRoleId;
    }

    public void setMemberRoleId(String memberRoleId) {
        this.memberRoleId = memberRoleId == null ? null : memberRoleId.trim();
    }

    public String getMemberStsCd() {
        return memberStsCd;
    }

    public void setMemberStsCd(String memberStsCd) {
        this.memberStsCd = memberStsCd == null ? null : memberStsCd.trim();
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }
}