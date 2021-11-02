package com.gogi.proj.todayPickup.vo;

import java.util.List;

public class GoodsListVO {

	private String success;
	private String code;
	private String msg;
	private List<TodayPickupVO> data;
	
	public GoodsListVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsListVO(String success, String code, String msg, List<TodayPickupVO> data) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
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

	public List<TodayPickupVO> getData() {
		return data;
	}

	public void setData(List<TodayPickupVO> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GoodsListVO [success=" + success + ", code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
