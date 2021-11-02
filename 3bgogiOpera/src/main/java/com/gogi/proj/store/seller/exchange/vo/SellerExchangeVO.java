package com.gogi.proj.store.seller.exchange.vo;

public class SellerExchangeVO {

	private int sePk;							// 고유값
	private int adminFk;						// 관리자고유값
	private int seExchangePrice;				// 환전금액
	private int seExchangeIncreaseCount;		// 환전횟수
	private boolean sePermitFlag;				// 환전 허가 여부
	private String sePermitDate;				// 허가일
	private String seBankNm;					// 은행명
	private String seBankAccount;				// 계좌번호
	private String seReqDate;					// 환전신청일
	private String sePermitAdmin;				// 허가자
	
	public SellerExchangeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SellerExchangeVO(int sePk, int adminFk, int seExchangePrice, int seExchangeIncreaseCount,
			boolean sePermitFlag, String sePermitDate, String seBankNm, String seBankAccount, String seReqDate,
			String sePermitAdmin) {
		super();
		this.sePk = sePk;
		this.adminFk = adminFk;
		this.seExchangePrice = seExchangePrice;
		this.seExchangeIncreaseCount = seExchangeIncreaseCount;
		this.sePermitFlag = sePermitFlag;
		this.sePermitDate = sePermitDate;
		this.seBankNm = seBankNm;
		this.seBankAccount = seBankAccount;
		this.seReqDate = seReqDate;
		this.sePermitAdmin = sePermitAdmin;
	}

	public int getSePk() {
		return sePk;
	}

	public void setSePk(int sePk) {
		this.sePk = sePk;
	}

	public int getAdminFk() {
		return adminFk;
	}

	public void setAdminFk(int adminFk) {
		this.adminFk = adminFk;
	}

	public int getSeExchangePrice() {
		return seExchangePrice;
	}

	public void setSeExchangePrice(int seExchangePrice) {
		this.seExchangePrice = seExchangePrice;
	}

	public int getSeExchangeIncreaseCount() {
		return seExchangeIncreaseCount;
	}

	public void setSeExchangeIncreaseCount(int seExchangeIncreaseCount) {
		this.seExchangeIncreaseCount = seExchangeIncreaseCount;
	}

	public boolean isSePermitFlag() {
		return sePermitFlag;
	}

	public void setSePermitFlag(boolean sePermitFlag) {
		this.sePermitFlag = sePermitFlag;
	}

	public String getSePermitDate() {
		return sePermitDate;
	}

	public void setSePermitDate(String sePermitDate) {
		this.sePermitDate = sePermitDate;
	}

	public String getSeBankNm() {
		return seBankNm;
	}

	public void setSeBankNm(String seBankNm) {
		this.seBankNm = seBankNm;
	}

	public String getSeBankAccount() {
		return seBankAccount;
	}

	public void setSeBankAccount(String seBankAccount) {
		this.seBankAccount = seBankAccount;
	}

	public String getSeReqDate() {
		return seReqDate;
	}

	public void setSeReqDate(String seReqDate) {
		this.seReqDate = seReqDate;
	}

	public String getSePermitAdmin() {
		return sePermitAdmin;
	}

	public void setSePermitAdmin(String sePermitAdmin) {
		this.sePermitAdmin = sePermitAdmin;
	}

	@Override
	public String toString() {
		return "SellerExchangeVO [sePk=" + sePk + ", adminFk=" + adminFk + ", seExchangePrice=" + seExchangePrice
				+ ", seExchangeIncreaseCount=" + seExchangeIncreaseCount + ", sePermitFlag=" + sePermitFlag
				+ ", sePermitDate=" + sePermitDate + ", seBankNm=" + seBankNm + ", seBankAccount=" + seBankAccount
				+ ", seReqDate=" + seReqDate + ", sePermitAdmin=" + sePermitAdmin + "]";
	}

}
