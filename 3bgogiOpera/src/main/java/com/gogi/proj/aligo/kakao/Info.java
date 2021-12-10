package com.gogi.proj.aligo.kakao;

public class Info {

	private String type;
	private int mid;
	private float current;
	private float unit;
	private float total;
	private int scnt;
	private int fcnt;
	
	private String reg;
	private String req;
	private String apr;
	private String rej;

	public Info() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Info(String type, int mid, float current, float unit, float total, int scnt, int fcnt, String reg,
			String req, String apr, String rej) {
		super();
		this.type = type;
		this.mid = mid;
		this.current = current;
		this.unit = unit;
		this.total = total;
		this.scnt = scnt;
		this.fcnt = fcnt;
		this.reg = reg;
		this.req = req;
		this.apr = apr;
		this.rej = rej;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public float getCurrent() {
		return current;
	}

	public void setCurrent(float current) {
		this.current = current;
	}

	public float getUnit() {
		return unit;
	}

	public void setUnit(float unit) {
		this.unit = unit;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getScnt() {
		return scnt;
	}

	public void setScnt(int scnt) {
		this.scnt = scnt;
	}

	public int getFcnt() {
		return fcnt;
	}

	public void setFcnt(int fcnt) {
		this.fcnt = fcnt;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public String getApr() {
		return apr;
	}

	public void setApr(String apr) {
		this.apr = apr;
	}

	public String getRej() {
		return rej;
	}

	public void setRej(String rej) {
		this.rej = rej;
	}

	@Override
	public String toString() {
		return "Info [type=" + type + ", mid=" + mid + ", current=" + current + ", unit=" + unit + ", total=" + total
				+ ", scnt=" + scnt + ", fcnt=" + fcnt + ", reg=" + reg + ", req=" + req + ", apr=" + apr + ", rej="
				+ rej + "]";
	}
	
}
