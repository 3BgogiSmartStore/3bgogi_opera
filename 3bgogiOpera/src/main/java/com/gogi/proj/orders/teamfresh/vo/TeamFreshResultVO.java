package com.gogi.proj.orders.teamfresh.vo;

import java.util.Arrays;

public class TeamFreshResultVO {
	private String resultCode;
	private String resultMsg;
	private Result result;
	private OrderResult [] orderResult;

	public TeamFreshResultVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeamFreshResultVO(String resultCode, String resultMsg, Result result, OrderResult[] orderResult) {
		super();
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.result = result;
		this.orderResult = orderResult;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public OrderResult[] getOrderResult() {
		return orderResult;
	}

	public void setOrderResult(OrderResult[] orderResult) {
		this.orderResult = orderResult;
	}

	@Override
	public String toString() {
		return "TeamFreshResultVO [resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", result=" + result
				+ ", orderResult=" + Arrays.toString(orderResult) + "]";
	}
	
}
