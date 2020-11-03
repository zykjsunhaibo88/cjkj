/*
 * Copyright @ 2016 沈阳首华财经网络科技有限公司.
 * All rights reserved.
 */
package com.br.tvlicai.firstchinanet.logSystem.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestAttributes;

/**
 * Add the description here.
 * Create on 2016/8/23
 *
 * @author xuhai
 * @version 0.0.0
 */
public class LSMethodDesc {

    private long startTime;
    private long endTime;

    private Object result;
    private String errorMsg;

    private ProceedingJoinPoint pjd;
    private LSController lsc;

    private RequestAttributes ras;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ProceedingJoinPoint getPjd() {
        return pjd;
    }

    public void setPjd(ProceedingJoinPoint pjd) {
        this.pjd = pjd;
    }

    public LSController getLsc() {
        return lsc;
    }

    public void setLsc(LSController lsc) {
        this.lsc = lsc;
    }

    public RequestAttributes getRas() {
        return ras;
    }

    public void setRas(RequestAttributes ras) {
        this.ras = ras;
    }
}
