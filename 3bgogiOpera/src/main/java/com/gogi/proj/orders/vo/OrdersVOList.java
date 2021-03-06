package com.gogi.proj.orders.vo;

import java.sql.Date;
import java.util.List;

public class OrdersVOList {

	private String orSerialSpecialNumber; //주문서 고유 특수번호
	private String orBuyerId; // 구매자 아이디
	private String orBuyerName; //구매자명
	private String orBuyerAnotherName; //구매자명 치환
	private String orBuyerContractNumber1; //구매자 연락처 1
	private String orBuyerContractNumber2; //구매자 연락처 2
	private String orReceiverName; //수취인명
	private String orReceiverContractNumber1; //수취인 연락처1
	private String orReceiverContractNumber2; //수취인 연락처2
	private String orDeliveryInvoiceNumber; // 송장번호
	private String orDeliveryCompany;
	private String orShippingAddress;
	private String orShippingAddressDetail;
	private String orOrderNumber;
	private String orDelivEnter;
	private boolean orDelivEnterFlag;
	private String ssName;
	private Date orSendingDay;
	private Date orSendingDeadline;
	private int orAbsDelivType;
	
	private List<OrdersVO> orVoList;

	public OrdersVOList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersVOList(String orSerialSpecialNumber, String orBuyerId, String orBuyerName, String orBuyerAnotherName,
			String orBuyerContractNumber1, String orBuyerContractNumber2, String orReceiverName,
			String orReceiverContractNumber1, String orReceiverContractNumber2, String orDeliveryInvoiceNumber,
			String orDeliveryCompany, String orShippingAddress, String orShippingAddressDetail, String orOrderNumber,
			String orDelivEnter, boolean orDelivEnterFlag, String ssName, Date orSendingDay, Date orSendingDeadline,
			List<OrdersVO> orVoList) {
		super();
		this.orSerialSpecialNumber = orSerialSpecialNumber;
		this.orBuyerId = orBuyerId;
		this.orBuyerName = orBuyerName;
		this.orBuyerAnotherName = orBuyerAnotherName;
		this.orBuyerContractNumber1 = orBuyerContractNumber1;
		this.orBuyerContractNumber2 = orBuyerContractNumber2;
		this.orReceiverName = orReceiverName;
		this.orReceiverContractNumber1 = orReceiverContractNumber1;
		this.orReceiverContractNumber2 = orReceiverContractNumber2;
		this.orDeliveryInvoiceNumber = orDeliveryInvoiceNumber;
		this.orDeliveryCompany = orDeliveryCompany;
		this.orShippingAddress = orShippingAddress;
		this.orShippingAddressDetail = orShippingAddressDetail;
		this.orOrderNumber = orOrderNumber;
		this.orDelivEnter = orDelivEnter;
		this.orDelivEnterFlag = orDelivEnterFlag;
		this.ssName = ssName;
		this.orSendingDay = orSendingDay;
		this.orSendingDeadline = orSendingDeadline;
		this.orVoList = orVoList;
	}
	
	public int getOrAbsDelivType() {
		return orAbsDelivType;
	}

	public void setOrAbsDelivType(int orAbsDelivType) {
		this.orAbsDelivType = orAbsDelivType;
	}

	public String getOrSerialSpecialNumber() {
		return orSerialSpecialNumber;
	}

	public void setOrSerialSpecialNumber(String orSerialSpecialNumber) {
		this.orSerialSpecialNumber = orSerialSpecialNumber;
	}

	public String getOrBuyerId() {
		return orBuyerId;
	}

	public void setOrBuyerId(String orBuyerId) {
		this.orBuyerId = orBuyerId;
	}

	public String getOrBuyerName() {
		return orBuyerName;
	}

	public void setOrBuyerName(String orBuyerName) {
		this.orBuyerName = orBuyerName;
	}

	public String getOrBuyerAnotherName() {
		return orBuyerAnotherName;
	}

	public void setOrBuyerAnotherName(String orBuyerAnotherName) {
		this.orBuyerAnotherName = orBuyerAnotherName;
	}

	public String getOrBuyerContractNumber1() {
		return orBuyerContractNumber1;
	}

	public void setOrBuyerContractNumber1(String orBuyerContractNumber1) {
		this.orBuyerContractNumber1 = orBuyerContractNumber1;
	}

	public String getOrBuyerContractNumber2() {
		return orBuyerContractNumber2;
	}

	public void setOrBuyerContractNumber2(String orBuyerContractNumber2) {
		this.orBuyerContractNumber2 = orBuyerContractNumber2;
	}

	public String getOrReceiverName() {
		return orReceiverName;
	}

	public void setOrReceiverName(String orReceiverName) {
		this.orReceiverName = orReceiverName;
	}

	public String getOrReceiverContractNumber1() {
		return orReceiverContractNumber1;
	}

	public void setOrReceiverContractNumber1(String orReceiverContractNumber1) {
		this.orReceiverContractNumber1 = orReceiverContractNumber1;
	}

	public String getOrReceiverContractNumber2() {
		return orReceiverContractNumber2;
	}

	public void setOrReceiverContractNumber2(String orReceiverContractNumber2) {
		this.orReceiverContractNumber2 = orReceiverContractNumber2;
	}

	public String getOrDeliveryInvoiceNumber() {
		return orDeliveryInvoiceNumber;
	}

	public void setOrDeliveryInvoiceNumber(String orDeliveryInvoiceNumber) {
		this.orDeliveryInvoiceNumber = orDeliveryInvoiceNumber;
	}

	public String getOrDeliveryCompany() {
		return orDeliveryCompany;
	}

	public void setOrDeliveryCompany(String orDeliveryCompany) {
		this.orDeliveryCompany = orDeliveryCompany;
	}

	public String getOrShippingAddress() {
		return orShippingAddress;
	}

	public void setOrShippingAddress(String orShippingAddress) {
		this.orShippingAddress = orShippingAddress;
	}

	public String getOrShippingAddressDetail() {
		return orShippingAddressDetail;
	}

	public void setOrShippingAddressDetail(String orShippingAddressDetail) {
		this.orShippingAddressDetail = orShippingAddressDetail;
	}

	public String getOrOrderNumber() {
		return orOrderNumber;
	}

	public void setOrOrderNumber(String orOrderNumber) {
		this.orOrderNumber = orOrderNumber;
	}

	public String getOrDelivEnter() {
		return orDelivEnter;
	}

	public void setOrDelivEnter(String orDelivEnter) {
		this.orDelivEnter = orDelivEnter;
	}

	public boolean isOrDelivEnterFlag() {
		return orDelivEnterFlag;
	}

	public void setOrDelivEnterFlag(boolean orDelivEnterFlag) {
		this.orDelivEnterFlag = orDelivEnterFlag;
	}

	public String getSsName() {
		return ssName;
	}

	public void setSsName(String ssName) {
		this.ssName = ssName;
	}

	public Date getOrSendingDay() {
		return orSendingDay;
	}

	public void setOrSendingDay(Date orSendingDay) {
		this.orSendingDay = orSendingDay;
	}

	public Date getOrSendingDeadline() {
		return orSendingDeadline;
	}

	public void setOrSendingDeadline(Date orSendingDeadline) {
		this.orSendingDeadline = orSendingDeadline;
	}

	public List<OrdersVO> getOrVoList() {
		return orVoList;
	}

	public void setOrVoList(List<OrdersVO> orVoList) {
		this.orVoList = orVoList;
	}

	@Override
	public String toString() {
		return "OrdersVOList [orSerialSpecialNumber=" + orSerialSpecialNumber + ", orBuyerId=" + orBuyerId
				+ ", orBuyerName=" + orBuyerName + ", orBuyerAnotherName=" + orBuyerAnotherName
				+ ", orBuyerContractNumber1=" + orBuyerContractNumber1 + ", orBuyerContractNumber2="
				+ orBuyerContractNumber2 + ", orReceiverName=" + orReceiverName + ", orReceiverContractNumber1="
				+ orReceiverContractNumber1 + ", orReceiverContractNumber2=" + orReceiverContractNumber2
				+ ", orDeliveryInvoiceNumber=" + orDeliveryInvoiceNumber + ", orDeliveryCompany=" + orDeliveryCompany
				+ ", orShippingAddress=" + orShippingAddress + ", orShippingAddressDetail=" + orShippingAddressDetail
				+ ", orOrderNumber=" + orOrderNumber + ", orDelivEnter=" + orDelivEnter + ", orDelivEnterFlag="
				+ orDelivEnterFlag + ", ssName=" + ssName + ", orSendingDay=" + orSendingDay + ", orSendingDeadline="
				+ orSendingDeadline + ", orVoList=" + orVoList + "]";
	}
	
}