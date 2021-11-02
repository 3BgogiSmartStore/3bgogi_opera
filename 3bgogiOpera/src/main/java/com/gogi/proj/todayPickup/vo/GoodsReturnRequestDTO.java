package com.gogi.proj.todayPickup.vo;

public class GoodsReturnRequestDTO {

	private String invoiceNumber;
	private String deliveryState;
	private String deliveryStateName;
	private String etc;
	private String receiveTime;
	private int riderKey;
	private String riderName;
	private int riderPrice;
	
	public GoodsReturnRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsReturnRequestDTO(String invoiceNumber, String deliveryState, String deliveryStateName, String etc,
			String receiveTime, int riderKey, String riderName, int riderPrice) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.deliveryState = deliveryState;
		this.deliveryStateName = deliveryStateName;
		this.etc = etc;
		this.receiveTime = receiveTime;
		this.riderKey = riderKey;
		this.riderName = riderName;
		this.riderPrice = riderPrice;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(String deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getDeliveryStateName() {
		return deliveryStateName;
	}

	public void setDeliveryStateName(String deliveryStateName) {
		this.deliveryStateName = deliveryStateName;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public int getRiderKey() {
		return riderKey;
	}

	public void setRiderKey(int riderKey) {
		this.riderKey = riderKey;
	}

	public String getRiderName() {
		return riderName;
	}

	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}

	public int getRiderPrice() {
		return riderPrice;
	}

	public void setRiderPrice(int riderPrice) {
		this.riderPrice = riderPrice;
	}

	@Override
	public String toString() {
		return "GoodsReturnRequestDTO [invoiceNumber=" + invoiceNumber + ", deliveryState=" + deliveryState
				+ ", deliveryStateName=" + deliveryStateName + ", etc=" + etc + ", receiveTime=" + receiveTime
				+ ", riderKey=" + riderKey + ", riderName=" + riderName + ", riderPrice=" + riderPrice + "]";
	}

}
