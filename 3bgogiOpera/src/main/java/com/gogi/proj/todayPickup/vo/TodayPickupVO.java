package com.gogi.proj.todayPickup.vo;

public class TodayPickupVO {

	private String childrenMallId;
	private String deliveryAddress;
	private String deliveryMessage;
	private String deliveryName;
	private String deliveryPhone;
	private String deliveryPostal;
	private String deliveryTel;
	private String deliveryDt;
	private String goodsName;
	private String invoiceNumber;
	private String invoicePrintYn="Y";
	private String mallName = "삼형제고기";
	private String optionName;
	private String orderNumber;
	private int quantity;
	
	public TodayPickupVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TodayPickupVO(String childrenMallId, String deliveryAddress, String deliveryMessage, String deliveryName,
			String deliveryPhone, String deliveryPostal, String deliveryTel, String deliveryDt, String goodsName,
			String invoiceNumber, String invoicePrintYn, String mallName, String optionName, String orderNumber,
			int quantity) {
		super();
		this.childrenMallId = childrenMallId;
		this.deliveryAddress = deliveryAddress;
		this.deliveryMessage = deliveryMessage;
		this.deliveryName = deliveryName;
		this.deliveryPhone = deliveryPhone;
		this.deliveryPostal = deliveryPostal;
		this.deliveryTel = deliveryTel;
		this.deliveryDt = deliveryDt;
		this.goodsName = goodsName;
		this.invoiceNumber = invoiceNumber;
		this.invoicePrintYn = invoicePrintYn;
		this.mallName = mallName;
		this.optionName = optionName;
		this.orderNumber = orderNumber;
		this.quantity = quantity;
	}

	public String getChildrenMallId() {
		return childrenMallId;
	}

	public void setChildrenMallId(String childrenMallId) {
		this.childrenMallId = childrenMallId;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryMessage() {
		return deliveryMessage;
	}

	public void setDeliveryMessage(String deliveryMessage) {
		this.deliveryMessage = deliveryMessage;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}

	public String getDeliveryPostal() {
		return deliveryPostal;
	}

	public void setDeliveryPostal(String deliveryPostal) {
		this.deliveryPostal = deliveryPostal;
	}

	public String getDeliveryTel() {
		return deliveryTel;
	}

	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}

	public String getDeliveryDt() {
		return deliveryDt;
	}

	public void setDeliveryDt(String deliveryDt) {
		this.deliveryDt = deliveryDt;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoicePrintYn() {
		return invoicePrintYn;
	}

	public void setInvoicePrintYn(String invoicePrintYn) {
		this.invoicePrintYn = invoicePrintYn;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "TodayPickupVO [childrenMallId=" + childrenMallId + ", deliveryAddress=" + deliveryAddress
				+ ", deliveryMessage=" + deliveryMessage + ", deliveryName=" + deliveryName + ", deliveryPhone="
				+ deliveryPhone + ", deliveryPostal=" + deliveryPostal + ", deliveryTel=" + deliveryTel
				+ ", deliveryDt=" + deliveryDt + ", goodsName=" + goodsName + ", invoiceNumber=" + invoiceNumber
				+ ", invoicePrintYn=" + invoicePrintYn + ", mallName=" + mallName + ", optionName=" + optionName
				+ ", orderNumber=" + orderNumber + ", quantity=" + quantity + "]";
	}
	
	
}
