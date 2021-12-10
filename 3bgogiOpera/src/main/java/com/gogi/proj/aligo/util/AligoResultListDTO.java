package com.gogi.proj.aligo.util;

public class AligoResultListDTO {

	private String mdid;
	private String type;
	private String sender;
	private String receiver;
	private String sms_state;
	private String reg_date;
	private String send_date;
	private String reserve_date;
	
	public AligoResultListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AligoResultListDTO(String mdid, String type, String sender, String receiver, String sms_state,
			String reg_date, String send_date, String reserve_date) {
		super();
		this.mdid = mdid;
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.sms_state = sms_state;
		this.reg_date = reg_date;
		this.send_date = send_date;
		this.reserve_date = reserve_date;
	}

	public String getMdid() {
		return mdid;
	}

	public void setMdid(String mdid) {
		this.mdid = mdid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSms_state() {
		return sms_state;
	}

	public void setSms_state(String sms_state) {
		this.sms_state = sms_state;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}

	public String getReserve_date() {
		return reserve_date;
	}

	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}

	@Override
	public String toString() {
		return "AligoResultListDTO [mdid=" + mdid + ", type=" + type + ", sender=" + sender + ", receiver=" + receiver
				+ ", sms_state=" + sms_state + ", reg_date=" + reg_date + ", send_date=" + send_date + ", reserve_date="
				+ reserve_date + "]";
	}
	
}
