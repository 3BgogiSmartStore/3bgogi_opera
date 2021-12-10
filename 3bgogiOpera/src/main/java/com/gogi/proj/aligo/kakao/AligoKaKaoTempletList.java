package com.gogi.proj.aligo.kakao;

import java.util.List;

public class AligoKaKaoTempletList {

	private String templtContent;
	private String templtName;
	private String status;
	private String inspStatus;
	private String senderKey;
	private KakaoButtons [] buttons;
	private String cdate;
	private String templtCode;
	private Object comments;
	
	public AligoKaKaoTempletList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AligoKaKaoTempletList(String templtContent, String templtName, String status, String inspStatus,
			String senderKey, KakaoButtons [] buttons, String cdate, String templtCode, Object comments) {
		super();
		this.templtContent = templtContent;
		this.templtName = templtName;
		this.status = status;
		this.inspStatus = inspStatus;
		this.senderKey = senderKey;
		this.buttons = buttons;
		this.cdate = cdate;
		this.templtCode = templtCode;
		this.comments = comments;
	}

	public String getTempltContent() {
		return templtContent;
	}

	public void setTempltContent(String templtContent) {
		this.templtContent = templtContent;
	}

	public String getTempltName() {
		return templtName;
	}

	public void setTempltName(String templtName) {
		this.templtName = templtName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInspStatus() {
		return inspStatus;
	}

	public void setInspStatus(String inspStatus) {
		this.inspStatus = inspStatus;
	}

	public String getSenderKey() {
		return senderKey;
	}

	public void setSenderKey(String senderKey) {
		this.senderKey = senderKey;
	}

	public KakaoButtons [] getButtons() {
		return buttons;
	}

	public void setButtons(KakaoButtons [] buttons) {
		this.buttons = buttons;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getTempltCode() {
		return templtCode;
	}

	public void setTempltCode(String templtCode) {
		this.templtCode = templtCode;
	}

	public Object getComments() {
		return comments;
	}

	public void setComments(Object comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "AligoKaKaoTempletList [templtContent=" + templtContent + ", templtName=" + templtName + ", status="
				+ status + ", inspStatus=" + inspStatus + ", senderKey=" + senderKey + ", buttons=" + buttons
				+ ", cdate=" + cdate + ", templtCode=" + templtCode + ", comments=" + comments + "]";
	}

}
