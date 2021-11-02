package com.gogi.proj.orders.cj.util;

public class CjResultMessage {

	private String message;
	private int resultCode;
	private boolean isDawnAble;
	
	public CjResultMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CjResultMessage(String message, int resultCode, boolean isDawnAble) {
		super();
		this.message = message;
		this.resultCode = resultCode;
		this.isDawnAble = isDawnAble;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public boolean getIsDawnAble() {
		return isDawnAble;
	}

	public void setIsDawnAble(boolean isDawnAble) {
		this.isDawnAble = isDawnAble;
	}

	@Override
	public String toString() {
		return "CjResultMessage [message=" + message + ", resultCode=" + resultCode + ", isDawnAble=" + isDawnAble
				+ "]";
	}
	
}
