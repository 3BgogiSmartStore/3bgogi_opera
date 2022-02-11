package com.gogi.proj.orders.coupang.vo;

public class CoupangResDTO {

	/**
	 * 400 (요청변수확인)	Invalid vendor ID	올바른 판매자 ID(vendorId)를 입력했는지 확인합니다. 예) A00012345
	 * 
	 * 400 (요청변수확인)	endTime-startTime range should less than31.	조회기간이 31일 이내 인지 확인합니다.
	 */
	private int code;
	
	private String message;
	private CoupangData data;
	private String nextToken;
	
	public CoupangResDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangResDTO(int code, String message, CoupangData data, String nextToken) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
		this.nextToken = nextToken;
	}

	/**
	 * 400 (요청변수확인)	Invalid vendor ID	올바른 판매자 ID(vendorId)를 입력했는지 확인합니다. 예) A00012345
	 * 
	 * 400 (요청변수확인)	endTime-startTime range should less than31.	조회기간이 31일 이내 인지 확인합니다.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 400 (요청변수확인)	Invalid vendor ID	올바른 판매자 ID(vendorId)를 입력했는지 확인합니다. 예) A00012345
	 * 
	 * 400 (요청변수확인)	endTime-startTime range should less than31.	조회기간이 31일 이내 인지 확인합니다.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CoupangData getData() {
		return data;
	}

	public void setData(CoupangData data) {
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
		return "CoupangResDTO [code=" + code + ", message=" + message + ", data=" + data + ", nextToken=" + nextToken
				+ "]";
	}
	
}
