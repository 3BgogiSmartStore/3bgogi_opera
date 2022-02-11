package com.gogi.proj.orders.coupang.vo;

public class CoupangReceiver {

	private String name;				//수령자 이름
	private String safeNumber;			//수령자 연락처(안심번호)
	private String receiverNumber;		//수취인 실 연락처 null값 처리됨
	private String addr1;				//수취인 배송지1
	private String addr2;				//수취인 배송지2 ( 상세 배송지 )
	private String postCode;			//우편번호
	
	public CoupangReceiver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangReceiver(String name, String safeNumber, String receiverNumber, String addr1, String addr2,
			String postCode) {
		super();
		this.name = name;
		this.safeNumber = safeNumber;
		this.receiverNumber = receiverNumber;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.postCode = postCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSafeNumber() {
		return safeNumber;
	}

	public void setSafeNumber(String safeNumber) {
		this.safeNumber = safeNumber;
	}

	public String getReceiverNumber() {
		return receiverNumber;
	}

	public void setReceiverNumber(String receiverNumber) {
		this.receiverNumber = receiverNumber;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Override
	public String toString() {
		return "CoupangReceiver [name=" + name + ", safeNumber=" + safeNumber + ", receiverNumber=" + receiverNumber
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", postCode=" + postCode + "]";
	}
	
}
