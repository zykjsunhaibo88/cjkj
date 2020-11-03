package com.br.tvlicai.firstchinanet.domain;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by 13770 on 2016/4/23.
 */
public class BaseDomain {
    @Expose
    private String createdUserId;

    private Date createdDate;

    private String updatedUserId;

    private Date updatedDate;

    private String deleteType;

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
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
        this.updatedUserId = updatedUserId;
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
        this.deleteType = deleteType;
    }
}
