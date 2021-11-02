package com.gogi.proj.todayPickup.vo;

import java.util.List;

public class CancleDeliveryVO {

	private String code;
	private String msg;
	private boolean success;
	private GoodsReturnRequestDTO data;
	
	public CancleDeliveryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CancleDeliveryVO(String code, String msg, boolean success, GoodsReturnRequestDTO data) {
		super();
		this.code = code;
		this.msg = msg;
		this.success = success;
		this.data = data;
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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public GoodsReturnRequestDTO getData() {
		return data;
	}

	public void setData(GoodsReturnRequestDTO data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CancelDeliveryVO [code=" + code + ", msg=" + msg + ", success=" + success + ", data=" + data + "]";
	}
	
}
