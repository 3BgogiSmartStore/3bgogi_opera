package com.gogi.proj.aligo.kakao;

import java.util.List;

public class AligoKakaoResultDTO {

	private int code;
	private String message;
	private String token;
	private String urlencode;
	private Info info;
	private AligoKaKaoTempletList [] list;
	
	public AligoKakaoResultDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AligoKakaoResultDTO(int code, String message, String token, String urlencode, Info info,
			AligoKaKaoTempletList [] list) {
		super();
		this.code = code;
		this.message = message;
		this.token = token;
		this.urlencode = urlencode;
		this.info = info;
		this.list = list;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrlencode() {
		return urlencode;
	}

	public void setUrlencode(String urlencode) {
		this.urlencode = urlencode;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public AligoKaKaoTempletList [] getList() {
		return list;
	}

	public void setList(AligoKaKaoTempletList [] list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "AligoKakaoResultDTO [code=" + code + ", message=" + message + ", token=" + token + ", urlencode="
				+ urlencode + ", info=" + info + ", list=" + list + "]";
	}
	
}
