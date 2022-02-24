package com.gogi.proj.orders.coupang.vo;

import java.util.Arrays;

public class CoupangResponseData {

	private String responseKey;
	private String responseCode;
	private String responseMessage;
	private CoupangResponseList [] responseList;
	
	public CoupangResponseData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangResponseData(String responseKey, String responseCode, String responseMessage,
			CoupangResponseList[] responseList) {
		super();
		this.responseKey = responseKey;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.responseList = responseList;
	}

	public String getResponseKey() {
		return responseKey;
	}

	public void setResponseKey(String responseKey) {
		this.responseKey = responseKey;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public CoupangResponseList[] getResponseList() {
		return responseList;
	}

	public void setResponseList(CoupangResponseList[] responseList) {
		this.responseList = responseList;
	}

	@Override
	public String toString() {
		return "CoupangResponseData [responseKey=" + responseKey + ", responseCode=" + responseCode
				+ ", responseMessage=" + responseMessage + ", responseList=" + Arrays.toString(responseList) + "]";
	}

}
