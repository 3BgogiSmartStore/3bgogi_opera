package com.gogi.proj.delivery.config.vo;

import java.sql.Timestamp;

public class DoorPassVO {

	private int dpPk;					//고유값
	private String dpBuyerCnt;			//구매자번호
	private String dpReceiverCnt;		//수령자번호
	private String dpAddr;				//주소
	private String dpMsg;				//출입방법
	private Timestamp dpRegdate;		//등록일
	
	public DoorPassVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoorPassVO(int dpPk, String dpBuyerCnt, String dpReceiverCnt, String dpAddr, String dpMsg,
			Timestamp dpRegdate) {
		super();
		this.dpPk = dpPk;
		this.dpBuyerCnt = dpBuyerCnt;
		this.dpReceiverCnt = dpReceiverCnt;
		this.dpAddr = dpAddr;
		this.dpMsg = dpMsg;
		this.dpRegdate = dpRegdate;
	}

	public int getDpPk() {
		return dpPk;
	}

	public void setDpPk(int dpPk) {
		this.dpPk = dpPk;
	}

	public String getDpBuyerCnt() {
		return dpBuyerCnt;
	}

	public void setDpBuyerCnt(String dpBuyerCnt) {
		this.dpBuyerCnt = dpBuyerCnt;
	}

	public String getDpReceiverCnt() {
		return dpReceiverCnt;
	}

	public void setDpReceiverCnt(String dpReceiverCnt) {
		this.dpReceiverCnt = dpReceiverCnt;
	}

	public String getDpAddr() {
		return dpAddr;
	}

	public void setDpAddr(String dpAddr) {
		this.dpAddr = dpAddr;
	}

	public String getDpMsg() {
		return dpMsg;
	}

	public void setDpMsg(String dpMsg) {
		this.dpMsg = dpMsg;
	}

	public Timestamp getDpRegdate() {
		return dpRegdate;
	}

	public void setDpRegdate(Timestamp dpRegdate) {
		this.dpRegdate = dpRegdate;
	}

	@Override
	public String toString() {
		return "DoorPassVO [dpPk=" + dpPk + ", dpBuyerCnt=" + dpBuyerCnt + ", dpReceiverCnt=" + dpReceiverCnt
				+ ", dpAddr=" + dpAddr + ", dpMsg=" + dpMsg + ", dpRegdate=" + dpRegdate + "]";
	}
	
}
