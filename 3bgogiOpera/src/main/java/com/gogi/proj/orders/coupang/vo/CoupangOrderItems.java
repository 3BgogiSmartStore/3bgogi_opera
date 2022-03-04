package com.gogi.proj.orders.coupang.vo;

import java.util.Arrays;
import java.util.Map;

public class CoupangOrderItems {

	private String vendorItemPackageId;					//미사용
	private String vendorItemPackageName;				//미사용
	private String productId;								//
	private String vendorItemId;						//vendorItemId
	private String vendorItemName;						//vendorItemName
	/**
	 * shippingCount = 주문시 item의 구매 수량
		holdCountForCancel = 취소가 되어 환불 예정이 수량
		cancelCount = 취소가 확정된 수량
		발주 가능 수량 = shippingCount - (holdCountForCancel + cancelCount )
	 */
	private int shippingCount;	
	/**
	 * 개당 상품 가격(price of one item)
	 */
	private int salesPrice;	
	/**
	 * 결제 가격 : salesPrice*shippingCount
	 */
	private int orderPrice;				
	/**
	 * 총 할인 가격,
		discountPrice(총 할인 금액) =
		instantCouponDiscount(즉시할인 쿠폰) +
		downloadableCoupon(다운로드 쿠폰) +
		DiscountcoupangDiscount(쿠팡 지원 할인)
	 */
	private int discountPrice;
	private int instantCouponDiscount;
	private int downloadableCouponDiscount;
	private int coupangDiscount;
	private String externalVendorSkuCode;
	private String etcInfoHeader;
	/**
	 * 상품별 개별 입력 항목에 대한 사용자의 입력값
		optional
		필드는 존재하나 값이 없는 상태입니다. 필요시에는 아래의 etcInfoValues를 사용하시기 바랍니다.
	 */
	private String etcInfoValue;
	private String [] etcInfoValues;
	private String sellerProductId;
	private String sellerProductName;
	private String sellerProductItemName;
	private String firstSellerProductItemName;
	private int cancelCount;
	private int holdCountForCancel;
	private String estimatedShippingDate;
	private String plannedShippingDate;
	private String invoiceNumberUploadDate;
	
	//private Map<String, Object> extraProperties;
	
	private boolean pricingBadge;
	private boolean usedProduct;
	private String confirmDate;
	private String deliveryChargeTypeName;
	private boolean canceled;
	private String releaseStatus;
	private String cancelCompleteUser;
	private int purchaseCount;
	private String shipmentBoxId;
	
	public CoupangOrderItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangOrderItems(String vendorItemPackageId, String vendorItemPackageName, String productId,
			String vendorItemId, String vendorItemName, int shippingCount, int salesPrice, int orderPrice,
			int discountPrice, int instantCouponDiscount, int downloadableCouponDiscount, int coupangDiscount,
			String externalVendorSkuCode, String etcInfoHeader, String etcInfoValue, String[] etcInfoValues,
			String sellerProductId, String sellerProductName, String sellerProductItemName,
			String firstSellerProductItemName, int cancelCount, int holdCountForCancel, String estimatedShippingDate,
			String plannedShippingDate, String invoiceNumberUploadDate, boolean pricingBadge, boolean usedProduct,
			String confirmDate, String deliveryChargeTypeName, boolean canceled, String releaseStatus,
			String cancelCompleteUser, int purchaseCount, String shipmentBoxId) {
		super();
		this.vendorItemPackageId = vendorItemPackageId;
		this.vendorItemPackageName = vendorItemPackageName;
		this.productId = productId;
		this.vendorItemId = vendorItemId;
		this.vendorItemName = vendorItemName;
		this.shippingCount = shippingCount;
		this.salesPrice = salesPrice;
		this.orderPrice = orderPrice;
		this.discountPrice = discountPrice;
		this.instantCouponDiscount = instantCouponDiscount;
		this.downloadableCouponDiscount = downloadableCouponDiscount;
		this.coupangDiscount = coupangDiscount;
		this.externalVendorSkuCode = externalVendorSkuCode;
		this.etcInfoHeader = etcInfoHeader;
		this.etcInfoValue = etcInfoValue;
		this.etcInfoValues = etcInfoValues;
		this.sellerProductId = sellerProductId;
		this.sellerProductName = sellerProductName;
		this.sellerProductItemName = sellerProductItemName;
		this.firstSellerProductItemName = firstSellerProductItemName;
		this.cancelCount = cancelCount;
		this.holdCountForCancel = holdCountForCancel;
		this.estimatedShippingDate = estimatedShippingDate;
		this.plannedShippingDate = plannedShippingDate;
		this.invoiceNumberUploadDate = invoiceNumberUploadDate;
		this.pricingBadge = pricingBadge;
		this.usedProduct = usedProduct;
		this.confirmDate = confirmDate;
		this.deliveryChargeTypeName = deliveryChargeTypeName;
		this.canceled = canceled;
		this.releaseStatus = releaseStatus;
		this.cancelCompleteUser = cancelCompleteUser;
		this.purchaseCount = purchaseCount;
		this.shipmentBoxId = shipmentBoxId;
	}

	public String getVendorItemPackageId() {
		return vendorItemPackageId;
	}

	public void setVendorItemPackageId(String vendorItemPackageId) {
		this.vendorItemPackageId = vendorItemPackageId;
	}

	public String getVendorItemPackageName() {
		return vendorItemPackageName;
	}

	public void setVendorItemPackageName(String vendorItemPackageName) {
		this.vendorItemPackageName = vendorItemPackageName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getVendorItemId() {
		return vendorItemId;
	}

	public void setVendorItemId(String vendorItemId) {
		this.vendorItemId = vendorItemId;
	}

	public String getVendorItemName() {
		return vendorItemName;
	}

	public void setVendorItemName(String vendorItemName) {
		this.vendorItemName = vendorItemName;
	}

	public int getShippingCount() {
		return shippingCount;
	}

	public void setShippingCount(int shippingCount) {
		this.shippingCount = shippingCount;
	}

	public int getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(int salesPrice) {
		this.salesPrice = salesPrice;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getInstantCouponDiscount() {
		return instantCouponDiscount;
	}

	public void setInstantCouponDiscount(int instantCouponDiscount) {
		this.instantCouponDiscount = instantCouponDiscount;
	}

	public int getDownloadableCouponDiscount() {
		return downloadableCouponDiscount;
	}

	public void setDownloadableCouponDiscount(int downloadableCouponDiscount) {
		this.downloadableCouponDiscount = downloadableCouponDiscount;
	}

	public int getCoupangDiscount() {
		return coupangDiscount;
	}

	public void setCoupangDiscount(int coupangDiscount) {
		this.coupangDiscount = coupangDiscount;
	}

	public String getExternalVendorSkuCode() {
		return externalVendorSkuCode;
	}

	public void setExternalVendorSkuCode(String externalVendorSkuCode) {
		this.externalVendorSkuCode = externalVendorSkuCode;
	}

	public String getEtcInfoHeader() {
		return etcInfoHeader;
	}

	public void setEtcInfoHeader(String etcInfoHeader) {
		this.etcInfoHeader = etcInfoHeader;
	}

	public String getEtcInfoValue() {
		return etcInfoValue;
	}

	public void setEtcInfoValue(String etcInfoValue) {
		this.etcInfoValue = etcInfoValue;
	}

	public String[] getEtcInfoValues() {
		return etcInfoValues;
	}

	public void setEtcInfoValues(String[] etcInfoValues) {
		this.etcInfoValues = etcInfoValues;
	}

	public String getSellerProductId() {
		return sellerProductId;
	}

	public void setSellerProductId(String sellerProductId) {
		this.sellerProductId = sellerProductId;
	}

	public String getSellerProductName() {
		return sellerProductName;
	}

	public void setSellerProductName(String sellerProductName) {
		this.sellerProductName = sellerProductName;
	}

	public String getSellerProductItemName() {
		return sellerProductItemName;
	}

	public void setSellerProductItemName(String sellerProductItemName) {
		this.sellerProductItemName = sellerProductItemName;
	}

	public String getFirstSellerProductItemName() {
		return firstSellerProductItemName;
	}

	public void setFirstSellerProductItemName(String firstSellerProductItemName) {
		this.firstSellerProductItemName = firstSellerProductItemName;
	}

	public int getCancelCount() {
		return cancelCount;
	}

	public void setCancelCount(int cancelCount) {
		this.cancelCount = cancelCount;
	}

	public int getHoldCountForCancel() {
		return holdCountForCancel;
	}

	public void setHoldCountForCancel(int holdCountForCancel) {
		this.holdCountForCancel = holdCountForCancel;
	}

	public String getEstimatedShippingDate() {
		return estimatedShippingDate;
	}

	public void setEstimatedShippingDate(String estimatedShippingDate) {
		this.estimatedShippingDate = estimatedShippingDate;
	}

	public String getPlannedShippingDate() {
		return plannedShippingDate;
	}

	public void setPlannedShippingDate(String plannedShippingDate) {
		this.plannedShippingDate = plannedShippingDate;
	}

	public String getInvoiceNumberUploadDate() {
		return invoiceNumberUploadDate;
	}

	public void setInvoiceNumberUploadDate(String invoiceNumberUploadDate) {
		this.invoiceNumberUploadDate = invoiceNumberUploadDate;
	}

	public boolean isPricingBadge() {
		return pricingBadge;
	}

	public void setPricingBadge(boolean pricingBadge) {
		this.pricingBadge = pricingBadge;
	}

	public boolean isUsedProduct() {
		return usedProduct;
	}

	public void setUsedProduct(boolean usedProduct) {
		this.usedProduct = usedProduct;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getDeliveryChargeTypeName() {
		return deliveryChargeTypeName;
	}

	public void setDeliveryChargeTypeName(String deliveryChargeTypeName) {
		this.deliveryChargeTypeName = deliveryChargeTypeName;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public String getCancelCompleteUser() {
		return cancelCompleteUser;
	}

	public void setCancelCompleteUser(String cancelCompleteUser) {
		this.cancelCompleteUser = cancelCompleteUser;
	}

	public int getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(int purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public String getShipmentBoxId() {
		return shipmentBoxId;
	}

	public void setShipmentBoxId(String shipmentBoxId) {
		this.shipmentBoxId = shipmentBoxId;
	}

	@Override
	public String toString() {
		return "CoupangOrderItems [vendorItemPackageId=" + vendorItemPackageId + ", vendorItemPackageName="
				+ vendorItemPackageName + ", productId=" + productId + ", vendorItemId=" + vendorItemId
				+ ", vendorItemName=" + vendorItemName + ", shippingCount=" + shippingCount + ", salesPrice="
				+ salesPrice + ", orderPrice=" + orderPrice + ", discountPrice=" + discountPrice
				+ ", instantCouponDiscount=" + instantCouponDiscount + ", downloadableCouponDiscount="
				+ downloadableCouponDiscount + ", coupangDiscount=" + coupangDiscount + ", externalVendorSkuCode="
				+ externalVendorSkuCode + ", etcInfoHeader=" + etcInfoHeader + ", etcInfoValue=" + etcInfoValue
				+ ", etcInfoValues=" + Arrays.toString(etcInfoValues) + ", sellerProductId=" + sellerProductId
				+ ", sellerProductName=" + sellerProductName + ", sellerProductItemName=" + sellerProductItemName
				+ ", firstSellerProductItemName=" + firstSellerProductItemName + ", cancelCount=" + cancelCount
				+ ", holdCountForCancel=" + holdCountForCancel + ", estimatedShippingDate=" + estimatedShippingDate
				+ ", plannedShippingDate=" + plannedShippingDate + ", invoiceNumberUploadDate="
				+ invoiceNumberUploadDate + ", pricingBadge=" + pricingBadge + ", usedProduct=" + usedProduct
				+ ", confirmDate=" + confirmDate + ", deliveryChargeTypeName=" + deliveryChargeTypeName + ", canceled="
				+ canceled + ", releaseStatus=" + releaseStatus + ", cancelCompleteUser=" + cancelCompleteUser
				+ ", purchaseCount=" + purchaseCount + ", shipmentBoxId=" + shipmentBoxId + "]";
	}
	
}
