package com.gogi.proj.aligo.util;

import java.util.Arrays;

public class AligoResultDTO {

	private String result_code;
	private AligoResultListDTO [] list;
	private String message;
	private String next_yn;
	
	public AligoResultDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AligoResultDTO(String result_code, AligoResultListDTO[] list, String message, String next_yn) {
		super();
		this.result_code = result_code;
		this.list = list;
		this.message = message;
		this.next_yn = next_yn;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public AligoResultListDTO[] getList() {
		return list;
	}

	public void setList(AligoResultListDTO[] list) {
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNext_yn() {
		return next_yn;
	}

	public void setNext_yn(String next_yn) {
		this.next_yn = next_yn;
	}

	@Override
	public String toString() {
		return "AligoResultDTO [result_code=" + result_code + ", list=" + Arrays.toString(list) + ", message=" + message
				+ ", next_yn=" + next_yn + "]";
	}

}
