package com.gogi.proj.delivery.config.vo;

public class DoorPassVO {

	private String dpBuyerCnt;			//구매자번호
	private String dpReceiverCnt;		//수령자번호
	private String dpAddr;				//주소
	private String dpMsg;				//출입방법
	
	public DoorPassVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoorPassVO(String dpBuyerCnt, String dpReceiverCnt, String dpAddr, String dpMsg) {
		super();
		this.dpBuyerCnt = dpBuyerCnt;
		this.dpReceiverCnt = dpReceiverCnt;
		this.dpAddr = dpAddr;
		this.dpMsg = dpMsg;
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

	@Override
	public String toString() {
		return "DoorPassVO [dpBuyerCnt=" + dpBuyerCnt + ", dpReceiverCnt=" + dpReceiverCnt + ", dpAddr=" + dpAddr
				+ ", dpMsg=" + dpMsg + "]";
	}
	
}
