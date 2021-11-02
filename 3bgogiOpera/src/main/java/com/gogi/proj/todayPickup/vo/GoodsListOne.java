package com.gogi.proj.todayPickup.vo;

public class GoodsListOne {

	private String success;
	private String code;
	private String msg;
	
	public GoodsListOne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsListOne(String success, String code, String msg) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "GoodsListOne [success=" + success + ", code=" + code + ", msg=" + msg + "]";
	}
	
}
