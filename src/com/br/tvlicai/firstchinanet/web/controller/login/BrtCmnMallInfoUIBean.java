package com.br.tvlicai.firstchinanet.web.controller.login;

import java.util.Date;

public class BrtCmnMallInfoUIBean {
    private String mallUuid;

    private String mallId;

    private String platformId;

    private String mallName;

    private String mallSts;

    private String mallLevel;

    private String createdUserId;

    private Date createdDate;

    private String updatedUserId;

    private Date updatedDate;

    private String deleteType;

    public String getMallUuid() {
        return mallUuid;
    }

    public void setMallUuid(String mallUuid) {
        this.mallUuid = mallUuid == null ? null : mallUuid.trim();
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId == null ? null : mallId.trim();
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName == null ? null : mallName.trim();
    }

    public String getMallSts() {
        return mallSts;
    }

    public void setMallSts(String mallSts) {
        this.mallSts = mallSts == null ? null : mallSts.trim();
    }

    public String getMallLevel() {
        return mallLevel;
    }

    public void setMallLevel(String mallLevel) {
        this.mallLevel = mallLevel;
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