package com.br.tvlicai.firstchinanet.web.controller.login;

import com.br.tvlicai.firstchinanet.domain.BaseDomain;

public class BruMessageInfo extends BaseDomain {

    private String msgId;

    private String msgType;

    private String msgSender;

    private String msgReciver;

    private String isRead;

    private String isAgree;

    private String isOptData;//是否操作数据

    private String optTableName;

    private String optDataId;

    private String optColumnName;

    private String msgContext;

    private String dataUrl;//访问数据URL

    private String senderName;

    private String optTime;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender == null ? null : msgSender.trim();
    }

    public String getMsgReciver() {
        return msgReciver;
    }

    public void setMsgReciver(String msgReciver) {
        this.msgReciver = msgReciver == null ? null : msgReciver.trim();
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead == null ? null : isRead.trim();
    }

    public String getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree == null ? null : isAgree.trim();
    }

    public String getOptTableName() {
        return optTableName;
    }

    public void setOptTableName(String optTableName) {
        this.optTableName = optTableName == null ? null : optTableName.trim();
    }

    public String getOptDataId() {
        return optDataId;
    }

    public void setOptDataId(String optDataId) {
        this.optDataId = optDataId == null ? null : optDataId.trim();
    }

    public String getOptColumnName() {
        return optColumnName;
    }

    public void setOptColumnName(String optColumnName) {
        this.optColumnName = optColumnName == null ? null : optColumnName.trim();
    }

    public String getMsgContext() {
        return msgContext;
    }

    public String getIsOptData() {
        return isOptData;
    }

    public void setIsOptData(String isOptData) {
        this.isOptData = isOptData;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public void setMsgContext(String msgContext) {
        this.msgContext = msgContext == null ? null : msgContext.trim();
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }
}