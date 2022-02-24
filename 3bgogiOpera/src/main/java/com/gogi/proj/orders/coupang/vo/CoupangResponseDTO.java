package com.gogi.proj.orders.coupang.vo;

public class CoupangResponseDTO {

	private int code;
	private String message;
	private CoupangResponseData data;
	
	public CoupangResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangResponseDTO(int code, String message, CoupangResponseData data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CoupangResponseData getData() {
		return data;
	}

	public void setData(CoupangResponseData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CoupangResponseDTO [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	
}
