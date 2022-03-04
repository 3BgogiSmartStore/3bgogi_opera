package com.gogi.proj.orders.coupang.vo;

import java.util.Arrays;

public class CoupangCancelResponseData {

	private String receiptId;
	private String orderId;
	private String paymentId;
	private String receiptType;
	private String receiptStatus;
	private String createdAt;
	private String modifiedAt;
	private String requesterName;
	private String requesterPhoneNumber;
	private String requesterRealPhoneNumber;
	private String requesterAddress;
	private String requesterAddressDetail;
	private String requesterZipCode;
	private String cancelReasonCategory1;
	private String cancelReasonCategory2;
	private String cancelReason;
	private int cancelCountSum;
	private String returnDeliveryId;
	private String returnDeliveryType;
	private String releaseStopStatus;
	private int enclosePrice;
	private String faultByType;
	private boolean preRefund;
	private String completeConfirmType;
	private String completeConfirmDate;
	private CoupangOrderItems [] returnItems;
	private CoupangReturnDeliveryDtos [] returnDeliveryDtos;
	private String reasonCode;
	private String reasonCodeText;
	private long returnShippingCharge;
	private String nextToken;
	
	public CoupangCancelResponseData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangCancelResponseData(String receiptId, String orderId, String paymentId, String receiptType,
			String receiptStatus, String createdAt, String modifiedAt, String requesterName,
			String requesterPhoneNumber, String requesterRealPhoneNumber, String requesterAddress,
			String requesterAddressDetail, String requesterZipCode, String cancelReasonCategory1,
			String cancelReasonCategory2, String cancelReason, int cancelCountSum, String returnDeliveryId,
			String returnDeliveryType, String releaseStopStatus, int enclosePrice, String faultByType,
			boolean preRefund, String completeConfirmType, String completeConfirmDate, CoupangOrderItems[] returnItems,
			CoupangReturnDeliveryDtos[] returnDeliveryDtos, String reasonCode, String reasonCodeText,
			long returnShippingCharge, String nextToken) {
		super();
		this.receiptId = receiptId;
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.receiptType = receiptType;
		this.receiptStatus = receiptStatus;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.requesterName = requesterName;
		this.requesterPhoneNumber = requesterPhoneNumber;
		this.requesterRealPhoneNumber = requesterRealPhoneNumber;
		this.requesterAddress = requesterAddress;
		this.requesterAddressDetail = requesterAddressDetail;
		this.requesterZipCode = requesterZipCode;
		this.cancelReasonCategory1 = cancelReasonCategory1;
		this.cancelReasonCategory2 = cancelReasonCategory2;
		this.cancelReason = cancelReason;
		this.cancelCountSum = cancelCountSum;
		this.returnDeliveryId = returnDeliveryId;
		this.returnDeliveryType = returnDeliveryType;
		this.releaseStopStatus = releaseStopStatus;
		this.enclosePrice = enclosePrice;
		this.faultByType = faultByType;
		this.preRefund = preRefund;
		this.completeConfirmType = completeConfirmType;
		this.completeConfirmDate = completeConfirmDate;
		this.returnItems = returnItems;
		this.returnDeliveryDtos = returnDeliveryDtos;
		this.reasonCode = reasonCode;
		this.reasonCodeText = reasonCodeText;
		this.returnShippingCharge = returnShippingCharge;
		this.nextToken = nextToken;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getRequesterPhoneNumber() {
		return requesterPhoneNumber;
	}

	public void setRequesterPhoneNumber(String requesterPhoneNumber) {
		this.requesterPhoneNumber = requesterPhoneNumber;
	}

	public String getRequesterRealPhoneNumber() {
		return requesterRealPhoneNumber;
	}

	public void setRequesterRealPhoneNumber(String requesterRealPhoneNumber) {
		this.requesterRealPhoneNumber = requesterRealPhoneNumber;
	}

	public String getRequesterAddress() {
		return requesterAddress;
	}

	public void setRequesterAddress(String requesterAddress) {
		this.requesterAddress = requesterAddress;
	}

	public String getRequesterAddressDetail() {
		return requesterAddressDetail;
	}

	public void setRequesterAddressDetail(String requesterAddressDetail) {
		this.requesterAddressDetail = requesterAddressDetail;
	}

	public String getRequesterZipCode() {
		return requesterZipCode;
	}

	public void setRequesterZipCode(String requesterZipCode) {
		this.requesterZipCode = requesterZipCode;
	}

	public String getCancelReasonCategory1() {
		return cancelReasonCategory1;
	}

	public void setCancelReasonCategory1(String cancelReasonCategory1) {
		this.cancelReasonCategory1 = cancelReasonCategory1;
	}

	public String getCancelReasonCategory2() {
		return cancelReasonCategory2;
	}

	public void setCancelReasonCategory2(String cancelReasonCategory2) {
		this.cancelReasonCategory2 = cancelReasonCategory2;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public int getCancelCountSum() {
		return cancelCountSum;
	}

	public void setCancelCountSum(int cancelCountSum) {
		this.cancelCountSum = cancelCountSum;
	}

	public String getReturnDeliveryId() {
		return returnDeliveryId;
	}

	public void setReturnDeliveryId(String returnDeliveryId) {
		this.returnDeliveryId = returnDeliveryId;
	}

	public String getReturnDeliveryType() {
		return returnDeliveryType;
	}

	public void setReturnDeliveryType(String returnDeliveryType) {
		this.returnDeliveryType = returnDeliveryType;
	}

	public String getReleaseStopStatus() {
		return releaseStopStatus;
	}

	public void setReleaseStopStatus(String releaseStopStatus) {
		this.releaseStopStatus = releaseStopStatus;
	}

	public int getEnclosePrice() {
		return enclosePrice;
	}

	public void setEnclosePrice(int enclosePrice) {
		this.enclosePrice = enclosePrice;
	}

	public String getFaultByType() {
		return faultByType;
	}

	public void setFaultByType(String faultByType) {
		this.faultByType = faultByType;
	}

	public boolean isPreRefund() {
		return preRefund;
	}

	public void setPreRefund(boolean preRefund) {
		this.preRefund = preRefund;
	}

	public String getCompleteConfirmType() {
		return completeConfirmType;
	}

	public void setCompleteConfirmType(String completeConfirmType) {
		this.completeConfirmType = completeConfirmType;
	}

	public String getCompleteConfirmDate() {
		return completeConfirmDate;
	}

	public void setCompleteConfirmDate(String completeConfirmDate) {
		this.completeConfirmDate = completeConfirmDate;
	}

	public CoupangOrderItems[] getReturnItems() {
		return returnItems;
	}

	public void setReturnItems(CoupangOrderItems[] returnItems) {
		this.returnItems = returnItems;
	}

	public CoupangReturnDeliveryDtos[] getReturnDeliveryDtos() {
		return returnDeliveryDtos;
	}

	public void setReturnDeliveryDtos(CoupangReturnDeliveryDtos[] returnDeliveryDtos) {
		this.returnDeliveryDtos = returnDeliveryDtos;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonCodeText() {
		return reasonCodeText;
	}

	public void setReasonCodeText(String reasonCodeText) {
		this.reasonCodeText = reasonCodeText;
	}

	public long getReturnShippingCharge() {
		return returnShippingCharge;
	}

	public void setReturnShippingCharge(long returnShippingCharge) {
		this.returnShippingCharge = returnShippingCharge;
	}

	public String getNextToken() {
		return nextToken;
	}

	public void setNextToken(String nextToken) {
		this.nextToken = nextToken;
	}

	@Override
	public String toString() {
		return "CoupangCancelResponseData [receiptId=" + receiptId + ", orderId=" + orderId + ", paymentId=" + paymentId
				+ ", receiptType=" + receiptType + ", receiptStatus=" + receiptStatus + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + ", requesterName=" + requesterName + ", requesterPhoneNumber="
				+ requesterPhoneNumber + ", requesterRealPhoneNumber=" + requesterRealPhoneNumber
				+ ", requesterAddress=" + requesterAddress + ", requesterAddressDetail=" + requesterAddressDetail
				+ ", requesterZipCode=" + requesterZipCode + ", cancelReasonCategory1=" + cancelReasonCategory1
				+ ", cancelReasonCategory2=" + cancelReasonCategory2 + ", cancelReason=" + cancelReason
				+ ", cancelCountSum=" + cancelCountSum + ", returnDeliveryId=" + returnDeliveryId
				+ ", returnDeliveryType=" + returnDeliveryType + ", releaseStopStatus=" + releaseStopStatus
				+ ", enclosePrice=" + enclosePrice + ", faultByType=" + faultByType + ", preRefund=" + preRefund
				+ ", completeConfirmType=" + completeConfirmType + ", completeConfirmDate=" + completeConfirmDate
				+ ", returnItems=" + Arrays.toString(returnItems) + ", returnDeliveryDtos="
				+ Arrays.toString(returnDeliveryDtos) + ", reasonCode=" + reasonCode + ", reasonCodeText="
				+ reasonCodeText + ", returnShippingCharge=" + returnShippingCharge + ", nextToken=" + nextToken + "]";
	}
	
}
