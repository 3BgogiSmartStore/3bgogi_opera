package com.gogi.proj.orders.coupang.vo;

public class CoupangReturnDeliveryDtos {

	private String deliveryCompanyCode;
	private String deliveryInvoiceNo;
	
	public CoupangReturnDeliveryDtos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangReturnDeliveryDtos(String deliveryCompanyCode, String deliveryInvoiceNo) {
		super();
		this.deliveryCompanyCode = deliveryCompanyCode;
		this.deliveryInvoiceNo = deliveryInvoiceNo;
	}

	public String getDeliveryCompanyCode() {
		return deliveryCompanyCode;
	}

	public void setDeliveryCompanyCode(String deliveryCompanyCode) {
		this.deliveryCompanyCode = deliveryCompanyCode;
	}

	public String getDeliveryInvoiceNo() {
		return deliveryInvoiceNo;
	}

	public void setDeliveryInvoiceNo(String deliveryInvoiceNo) {
		this.deliveryInvoiceNo = deliveryInvoiceNo;
	}

	@Override
	public String toString() {
		return "CoupangReturnDeliveryDtos [deliveryCompanyCode=" + deliveryCompanyCode + ", deliveryInvoiceNo="
				+ deliveryInvoiceNo + "]";
	}

}
