package com.gogi.proj.orders.coupang.vo;

import java.util.Arrays;

public class CoupangData {

	private String shipmentBoxId;					//배송번호(묶음번호)
	private String orderId;							//주문번호
	private String orderedAt;						//주문일시
	private CoupangOrderer orderer;					//주문자
	private String paidAt;							//결제일시
	private String status;							//발주서 상태(주문 상태)
	private int shippingPrice;						//배송비
	private int remotePrice;						//도서산간배송비
	private boolean remoteArea;						//도서산간여부
	private String parcelPrintMessage;				//배송메세지
	private boolean splitShipping;					//분리배송여부
	private boolean ableSplitShipping;				//분리배송가능여부
	private CoupangReceiver receiver;				//수취인 정보
	private CoupangOrderer [] orderItems;			//주문한 상품 정보
	private CoupangOverseaShippingInfo overseaShippingInfoDto; //해외배송정보
	private String deliveryCompanyName;
	private String invoiceNumber;
	private String inTrasitDateTime;
	private String deliveredDate;
	private String refer;
	private String shipmentType;
	
	public CoupangData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangData(String shipmentBoxId, String orderId, String orderedAt, CoupangOrderer orderer, String paidAt,
			String status, int shippingPrice, int remotePrice, boolean remoteArea, String parcelPrintMessage,
			boolean splitShipping, boolean ableSplitShipping, CoupangReceiver receiver, CoupangOrderer[] orderItems,
			CoupangOverseaShippingInfo overseaShippingInfoDto, String deliveryCompanyName, String invoiceNumber,
			String inTrasitDateTime, String deliveredDate, String refer, String shipmentType) {
		super();
		this.shipmentBoxId = shipmentBoxId;
		this.orderId = orderId;
		this.orderedAt = orderedAt;
		this.orderer = orderer;
		this.paidAt = paidAt;
		this.status = status;
		this.shippingPrice = shippingPrice;
		this.remotePrice = remotePrice;
		this.remoteArea = remoteArea;
		this.parcelPrintMessage = parcelPrintMessage;
		this.splitShipping = splitShipping;
		this.ableSplitShipping = ableSplitShipping;
		this.receiver = receiver;
		this.orderItems = orderItems;
		this.overseaShippingInfoDto = overseaShippingInfoDto;
		this.deliveryCompanyName = deliveryCompanyName;
		this.invoiceNumber = invoiceNumber;
		this.inTrasitDateTime = inTrasitDateTime;
		this.deliveredDate = deliveredDate;
		this.refer = refer;
		this.shipmentType = shipmentType;
	}

	public String getShipmentBoxId() {
		return shipmentBoxId;
	}

	public void setShipmentBoxId(String shipmentBoxId) {
		this.shipmentBoxId = shipmentBoxId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(String orderedAt) {
		this.orderedAt = orderedAt;
	}

	public CoupangOrderer getOrderer() {
		return orderer;
	}

	public void setOrderer(CoupangOrderer orderer) {
		this.orderer = orderer;
	}

	public String getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(String paidAt) {
		this.paidAt = paidAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(int shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public int getRemotePrice() {
		return remotePrice;
	}

	public void setRemotePrice(int remotePrice) {
		this.remotePrice = remotePrice;
	}

	public boolean isRemoteArea() {
		return remoteArea;
	}

	public void setRemoteArea(boolean remoteArea) {
		this.remoteArea = remoteArea;
	}

	public String getParcelPrintMessage() {
		return parcelPrintMessage;
	}

	public void setParcelPrintMessage(String parcelPrintMessage) {
		this.parcelPrintMessage = parcelPrintMessage;
	}

	public boolean isSplitShipping() {
		return splitShipping;
	}

	public void setSplitShipping(boolean splitShipping) {
		this.splitShipping = splitShipping;
	}

	public boolean isAbleSplitShipping() {
		return ableSplitShipping;
	}

	public void setAbleSplitShipping(boolean ableSplitShipping) {
		this.ableSplitShipping = ableSplitShipping;
	}

	public CoupangReceiver getReceiver() {
		return receiver;
	}

	public void setReceiver(CoupangReceiver receiver) {
		this.receiver = receiver;
	}

	public CoupangOrderer[] getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(CoupangOrderer[] orderItems) {
		this.orderItems = orderItems;
	}

	public CoupangOverseaShippingInfo getOverseaShippingInfoDto() {
		return overseaShippingInfoDto;
	}

	public void setOverseaShippingInfoDto(CoupangOverseaShippingInfo overseaShippingInfoDto) {
		this.overseaShippingInfoDto = overseaShippingInfoDto;
	}

	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}

	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInTrasitDateTime() {
		return inTrasitDateTime;
	}

	public void setInTrasitDateTime(String inTrasitDateTime) {
		this.inTrasitDateTime = inTrasitDateTime;
	}

	public String getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}

	@Override
	public String toString() {
		return "CoupangData [shipmentBoxId=" + shipmentBoxId + ", orderId=" + orderId + ", orderedAt=" + orderedAt
				+ ", orderer=" + orderer + ", paidAt=" + paidAt + ", status=" + status + ", shippingPrice="
				+ shippingPrice + ", remotePrice=" + remotePrice + ", remoteArea=" + remoteArea
				+ ", parcelPrintMessage=" + parcelPrintMessage + ", splitShipping=" + splitShipping
				+ ", ableSplitShipping=" + ableSplitShipping + ", receiver=" + receiver + ", orderItems="
				+ Arrays.toString(orderItems) + ", overseaShippingInfoDto=" + overseaShippingInfoDto
				+ ", deliveryCompanyName=" + deliveryCompanyName + ", invoiceNumber=" + invoiceNumber
				+ ", inTrasitDateTime=" + inTrasitDateTime + ", deliveredDate=" + deliveredDate + ", refer=" + refer
				+ ", shipmentType=" + shipmentType + "]";
	}	
}
