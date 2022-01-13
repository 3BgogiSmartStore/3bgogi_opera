package com.gogi.proj.delivery.config.vo;

import java.sql.Timestamp;

public class EarlyDelivAreaVO {

	private int edaPk;					//지역고유값
	private int edtFk;					//빠른배송고유값
	private String edaAddr;				//우편번호
	private boolean edaSearchTypeFlag;	//0일 경우 완벽하게 같게 (false), 1일 경우(true) 포함으로
	private Timestamp edaRegdate;		//등록일
	private int delivCount;				//배송 불가 총 개수
	
	public EarlyDelivAreaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EarlyDelivAreaVO(int edaPk, int edtFk, String edaAddr, boolean edaSearchTypeFlag, Timestamp edaRegdate,
			int delivCount) {
		super();
		this.edaPk = edaPk;
		this.edtFk = edtFk;
		this.edaAddr = edaAddr;
		this.edaSearchTypeFlag = edaSearchTypeFlag;
		this.edaRegdate = edaRegdate;
		this.delivCount = delivCount;
	}

	public int getEdaPk() {
		return edaPk;
	}

	public void setEdaPk(int edaPk) {
		this.edaPk = edaPk;
	}

	public int getEdtFk() {
		return edtFk;
	}

	public void setEdtFk(int edtFk) {
		this.edtFk = edtFk;
	}

	public String getEdaAddr() {
		return edaAddr;
	}

	public void setEdaAddr(String edaAddr) {
		this.edaAddr = edaAddr;
	}

	
	
	/**
	 * 
	 * @MethodName : isEdaSearchTypeFlag
	 * @date : 2022. 1. 12.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 0일 경우 완벽하게 같게 (false), 1일 경우(true) 포함으로
	 */
	public boolean isEdaSearchTypeFlag() {
		return edaSearchTypeFlag;
	}

	public void setEdaSearchTypeFlag(boolean edaSearchTypeFlag) {
		this.edaSearchTypeFlag = edaSearchTypeFlag;
	}

	public Timestamp getEdaRegdate() {
		return edaRegdate;
	}

	public void setEdaRegdate(Timestamp edaRegdate) {
		this.edaRegdate = edaRegdate;
	}

	public int getDelivCount() {
		return delivCount;
	}

	public void setDelivCount(int delivCount) {
		this.delivCount = delivCount;
	}

	@Override
	public String toString() {
		return "EarlyDelivAreaVO [edaPk=" + edaPk + ", edtFk=" + edtFk + ", edaAddr=" + edaAddr + ", edaSearchTypeFlag="
				+ edaSearchTypeFlag + ", edaRegdate=" + edaRegdate + ", delivCount=" + delivCount + "]";
	}

}
