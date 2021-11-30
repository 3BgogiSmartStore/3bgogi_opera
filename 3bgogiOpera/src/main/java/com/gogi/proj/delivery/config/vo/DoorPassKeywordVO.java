package com.gogi.proj.delivery.config.vo;

import java.sql.Timestamp;

public class DoorPassKeywordVO {

	private int dpkPk;
	private String dpkWord;
	private Timestamp dpkRegdate;
	
	public DoorPassKeywordVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoorPassKeywordVO(int dpkPk, String dpkWord, Timestamp dpkRegdate) {
		super();
		this.dpkPk = dpkPk;
		this.dpkWord = dpkWord;
		this.dpkRegdate = dpkRegdate;
	}

	public int getDpkPk() {
		return dpkPk;
	}

	public void setDpkPk(int dpkPk) {
		this.dpkPk = dpkPk;
	}

	public String getDpkWord() {
		return dpkWord;
	}

	public void setDpkWord(String dpkWord) {
		this.dpkWord = dpkWord;
	}

	public Timestamp getDpkRegdate() {
		return dpkRegdate;
	}

	public void setDpkRegdate(Timestamp dpkRegdate) {
		this.dpkRegdate = dpkRegdate;
	}

	@Override
	public String toString() {
		return "DoorPassKeywordVO [dpkPk=" + dpkPk + ", dpkWord=" + dpkWord + ", dpkRegdate=" + dpkRegdate + "]";
	}
	
}
