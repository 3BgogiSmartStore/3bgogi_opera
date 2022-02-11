package com.gogi.proj.orders.coupang.vo;

public class CoupangOrderer {

	private String name;
	private String email;				//마스킹 처리됨
	private String safeNumber;			//
	private String ordererNumber;		//null
	
	public CoupangOrderer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoupangOrderer(String name, String email, String safeNumber, String ordererNumber) {
		super();
		this.name = name;
		this.email = email;
		this.safeNumber = safeNumber;
		this.ordererNumber = ordererNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSafeNumber() {
		return safeNumber;
	}

	public void setSafeNumber(String safeNumber) {
		this.safeNumber = safeNumber;
	}

	public String getOrdererNumber() {
		return ordererNumber;
	}

	public void setOrdererNumber(String ordererNumber) {
		this.ordererNumber = ordererNumber;
	}

	@Override
	public String toString() {
		return "CoupangOrderer [name=" + name + ", email=" + email + ", safeNumber=" + safeNumber + ", ordererNumber="
				+ ordererNumber + "]";
	}

}
