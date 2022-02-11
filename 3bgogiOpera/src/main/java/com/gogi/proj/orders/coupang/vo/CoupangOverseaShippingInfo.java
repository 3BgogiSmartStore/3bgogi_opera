package com.gogi.proj.orders.coupang.vo;

public class CoupangOverseaShippingInfo {

	private String personalCustomsClearanceCode;
	private String orderersSsn;
	private String ordererPhoneNumber;

	public CoupangOverseaShippingInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangOverseaShippingInfo(String personalCustomsClearanceCode, String orderersSsn,
			String ordererPhoneNumber) {
		super();
		this.personalCustomsClearanceCode = personalCustomsClearanceCode;
		this.orderersSsn = orderersSsn;
		this.ordererPhoneNumber = ordererPhoneNumber;
	}

	public String getPersonalCustomsClearanceCode() {
		return personalCustomsClearanceCode;
	}

	public void setPersonalCustomsClearanceCode(String personalCustomsClearanceCode) {
		this.personalCustomsClearanceCode = personalCustomsClearanceCode;
	}

	public String getOrderersSsn() {
		return orderersSsn;
	}

	public void setOrderersSsn(String orderersSsn) {
		this.orderersSsn = orderersSsn;
	}

	public String getOrdererPhoneNumber() {
		return ordererPhoneNumber;
	}

	public void setOrdererPhoneNumber(String ordererPhoneNumber) {
		this.ordererPhoneNumber = ordererPhoneNumber;
	}

	@Override
	public String toString() {
		return "CoupangOverseaShippingInfo [personalCustomsClearanceCode=" + personalCustomsClearanceCode
				+ ", orderersSsn=" + orderersSsn + ", ordererPhoneNumber=" + ordererPhoneNumber + "]";
	}

}
