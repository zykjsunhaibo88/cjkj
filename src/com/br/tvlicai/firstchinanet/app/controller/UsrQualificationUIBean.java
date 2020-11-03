package com.br.tvlicai.firstchinanet.app.controller;

import com.br.tvlicai.firstchinanet.domain.BaseDomain;

import java.io.Serializable;

/**
 * 资质Bean
 */
public class UsrQualificationUIBean extends BaseDomain implements Serializable{

    private String qualificationId;//资质Id

    private String qualificationDetailId;//资质详细Id

    private String qualificationName;//资质名称

    private String qualificationCode;//资质编号

    private String qualificationLimitDate;//资质有效期

    private String qualificationType;//资质类型

    private String qualificationLevel;//资质等级

    private String qualificationUrl;//资质上传图片路径

    private String qualificationContext;//资质内容


    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualificationDetailId() {
        return qualificationDetailId;
    }

    public void setQualificationDetailId(String qualificationDetailId) {
        this.qualificationDetailId = qualificationDetailId;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
    }

    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    public String getQualificationUrl() {
        return qualificationUrl;
    }

    public void setQualificationUrl(String qualificationUrl) {
        this.qualificationUrl = qualificationUrl;
    }

    public String getQualificationContext() {
        return qualificationContext;
    }

    public void setQualificationContext(String qualificationContext) {
        this.qualificationContext = qualificationContext;
    }

    public String getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
    }

    public String getQualificationLimitDate() {
        return qualificationLimitDate;
    }

    public void setQualificationLimitDate(String qualificationLimitDate) {
        this.qualificationLimitDate = qualificationLimitDate;
    }
}
