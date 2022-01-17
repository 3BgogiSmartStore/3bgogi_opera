package com.gogi.proj.orders.teamfresh.vo;

import java.util.Arrays;

public class OrderResult {

	private String [] barcodeNumList;
	private String customerOrderNum;
	private String orderResult;
	private String orderNum;
	private String idx;
	private String orderResultCode;
	private String [] timfOrderNum;
	private String orderResultMsg;
	
	public OrderResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderResult(String[] barcodeNumList, String customerOrderNum, String orderResult, String orderNum,
			String idx, String orderResultCode, String[] timfOrderNum, String orderResultMsg) {
		super();
		this.barcodeNumList = barcodeNumList;
		this.customerOrderNum = customerOrderNum;
		this.orderResult = orderResult;
		this.orderNum = orderNum;
		this.idx = idx;
		this.orderResultCode = orderResultCode;
		this.timfOrderNum = timfOrderNum;
		this.orderResultMsg = orderResultMsg;
	}

	public String[] getBarcodeNumList() {
		return barcodeNumList;
	}

	public void setBarcodeNumList(String[] barcodeNumList) {
		this.barcodeNumList = barcodeNumList;
	}

	public String getCustomerOrderNum() {
		return customerOrderNum;
	}

	public void setCustomerOrderNum(String customerOrderNum) {
		this.customerOrderNum = customerOrderNum;
	}

	public String getOrderResult() {
		return orderResult;
	}

	public void setOrderResult(String orderResult) {
		this.orderResult = orderResult;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getOrderResultCode() {
		return orderResultCode;
	}

	public void setOrderResultCode(String orderResultCode) {
		this.orderResultCode = orderResultCode;
	}

	public String[] getTimfOrderNum() {
		return timfOrderNum;
	}

	public void setTimfOrderNum(String[] timfOrderNum) {
		this.timfOrderNum = timfOrderNum;
	}

	public String getOrderResultMsg() {
		return orderResultMsg;
	}

	public void setOrderResultMsg(String orderResultMsg) {
		this.orderResultMsg = orderResultMsg;
	}

	@Override
	public String toString() {
		return "OrderResult [barcodeNumList=" + Arrays.toString(barcodeNumList) + ", customerOrderNum="
				+ customerOrderNum + ", orderResult=" + orderResult + ", orderNum=" + orderNum + ", idx=" + idx
				+ ", orderResultCode=" + orderResultCode + ", timfOrderNum=" + Arrays.toString(timfOrderNum)
				+ ", orderResultMsg=" + orderResultMsg + "]";
	}

}
