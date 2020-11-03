package com.br.tvlicai.firstchinanet.web.controller.crm;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceIncome {
	private String InId;
	private Date InTime;
	private BigDecimal  InAmount;
	private String InKey;
	private int InType;
	private String UserId;
	private BigDecimal  ReMainAmount;
	private BigDecimal  BackAmount;
	
	private String Remark;
	private Date AddTime;
	
	
	
	
	public String getInId() {
		return InId;
	}
	public void setInId(String inId) {
		InId = inId;
	}
	public Date getInTime() {
		return InTime;
	}
	public void setInTime(Date inTime) {
		InTime = inTime;
	}
	public BigDecimal getInAmount() {
		return InAmount;
	}
	public void setInAmount(BigDecimal inAmount) {
		InAmount = inAmount;
	}
	public String getInKey() {
		return InKey;
	}
	public void setInKey(String inKey) {
		InKey = inKey;
	}
	public int getInType() {
		return InType;
	}
	public void setInType(int inType) {
		InType = inType;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public BigDecimal getReMainAmount() {
		return ReMainAmount;
	}
	public void setReMainAmount(BigDecimal reMainAmount) {
		ReMainAmount = reMainAmount;
	}
	public BigDecimal getBackAmount() {
		return BackAmount;
	}
	public void setBackAmount(BigDecimal backAmount) {
		BackAmount = backAmount;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public Date getAddTime() {
		return AddTime;
	}
	public void setAddTime(Date addTime) {
		AddTime = addTime;
	}

	
}
