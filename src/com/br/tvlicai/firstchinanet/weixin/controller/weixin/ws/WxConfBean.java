package com.br.tvlicai.firstchinanet.weixin.controller.weixin.ws;

/**
 * Created by wangnan on 2016/10/15 0015.
 */
public class WxConfBean {
    private String id;
    private String tiket;
    private String appid;
    private String appsecret;
    private String accessToken;
    private String dateline;
    private String tdateline;
    private String mallId;
    private String platformId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTiket() {
        return tiket;
    }

    public void setTiket(String tiket) {
        this.tiket = tiket;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getTdateline() {
        return tdateline;
    }

    public void setTdateline(String tdateline) {
        this.tdateline = tdateline;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
}
