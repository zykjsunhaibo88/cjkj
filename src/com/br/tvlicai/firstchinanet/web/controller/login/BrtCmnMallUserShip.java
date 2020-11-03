package com.br.tvlicai.firstchinanet.web.controller.login;

import java.util.Date;

public class BrtCmnMallUserShip {
    private String shipId;

    private String platformId;

    private String mallId;

    private String userAid;

    private String approveStsCd;

    private String approveUserId;

    private Date approveDate;

    private String createdUserId;

    private Date createdDate;

    private String updatedUserId;

    private Date updatedDate;

    private String deleteType;

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId == null ? null : shipId.trim();
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId == null ? null : mallId.trim();
    }

    public String getUserAid() {
        return userAid;
    }

    public void setUserAid(String userAid) {
        this.userAid = userAid == null ? null : userAid.trim();
    }

    public String getApproveStsCd() {
        return approveStsCd;
    }

    public void setApproveStsCd(String approveStsCd) {
        this.approveStsCd = approveStsCd == null ? null : approveStsCd.trim();
    }

    public String getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(String approveUserId) {
        this.approveUserId = approveUserId == null ? null : approveUserId.trim();
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId == null ? null : createdUserId.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId == null ? null : updatedUserId.trim();
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType == null ? null : deleteType.trim();
    }
}