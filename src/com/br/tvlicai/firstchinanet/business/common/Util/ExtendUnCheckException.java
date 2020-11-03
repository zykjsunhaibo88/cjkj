
package com.br.tvlicai.firstchinanet.business.common.Util;

public class ExtendUnCheckException extends RuntimeException {
	
	private String errorCode;

	public ExtendUnCheckException() {
		super();
	}	

	public ExtendUnCheckException(String s) {
		super(s);
	}
	public ExtendUnCheckException(Throwable t) {
		super(t);
	}
	public  ExtendUnCheckException(String errcode,String errmsg){
		super(errmsg);
		setErrorCode(errcode);
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
}
