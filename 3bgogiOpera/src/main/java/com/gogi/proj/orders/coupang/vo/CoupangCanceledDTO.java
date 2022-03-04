package com.gogi.proj.orders.coupang.vo;

import java.util.Arrays;

public class CoupangCanceledDTO {

	private String code;
	private String message;
	private CoupangCancelResponseData [] data;
	private String nextToken;
	
	public CoupangCanceledDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangCanceledDTO(String code, String message, CoupangCancelResponseData[] data, String nextToken) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
		this.nextToken = nextToken;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CoupangCancelResponseData[] getData() {
		return data;
	}

	public void setData(CoupangCancelResponseData[] data) {
		this.data = data;
	}

	public String getNextToken() {
		return nextToken;
	}

	public void setNextToken(String nextToken) {
		this.nextToken = nextToken;
	}

	@Override
	public String toString() {
		return "CoupangCanceledDTO [code=" + code + ", message=" + message + ", data=" + Arrays.toString(data)
				+ ", nextToken=" + nextToken + "]";
	}

}
