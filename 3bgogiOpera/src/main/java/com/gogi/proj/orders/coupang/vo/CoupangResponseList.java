package com.gogi.proj.orders.coupang.vo;

public class CoupangResponseList {

	private String shipmentBoxId;
	private boolean succeed;
	private String resultCode;
	private boolean retryRequired;
	private String resultMessage;
	
	public CoupangResponseList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangResponseList(String shipmentBoxId, boolean succeed, String resultCode, boolean retryRequired,
			String resultMessage) {
		super();
		this.shipmentBoxId = shipmentBoxId;
		this.succeed = succeed;
		this.resultCode = resultCode;
		this.retryRequired = retryRequired;
		this.resultMessage = resultMessage;
	}

	public String getShipmentBoxId() {
		return shipmentBoxId;
	}

	public void setShipmentBoxId(String shipmentBoxId) {
		this.shipmentBoxId = shipmentBoxId;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public boolean isRetryRequired() {
		return retryRequired;
	}

	public void setRetryRequired(boolean retryRequired) {
		this.retryRequired = retryRequired;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	@Override
	public String toString() {
		return "CoupangResponseList [shipmentBoxId=" + shipmentBoxId + ", succeed=" + succeed + ", resultCode="
				+ resultCode + ", retryRequired=" + retryRequired + ", resultMessage=" + resultMessage + "]";
	}

}
