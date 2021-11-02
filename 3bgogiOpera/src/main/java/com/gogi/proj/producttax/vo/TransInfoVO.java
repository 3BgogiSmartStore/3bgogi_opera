package com.gogi.proj.producttax.vo;

public class TransInfoVO {

	private int tiPk;
	private String tiUsedPlace;
	private String tiRemark;
	private int tiUseCost;
	private int tiCancledCost;
	private String tiDate;
	
	public TransInfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransInfoVO(int tiPk, String tiUsedPlace, String tiRemark, int tiUseCost, int tiCancledCost, String tiDate) {
		super();
		this.tiPk = tiPk;
		this.tiUsedPlace = tiUsedPlace;
		this.tiRemark = tiRemark;
		this.tiUseCost = tiUseCost;
		this.tiCancledCost = tiCancledCost;
		this.tiDate = tiDate;
	}

	public int getTiPk() {
		return tiPk;
	}

	public void setTiPk(int tiPk) {
		this.tiPk = tiPk;
	}

	public String getTiUsedPlace() {
		return tiUsedPlace;
	}

	public void setTiUsedPlace(String tiUsedPlace) {
		this.tiUsedPlace = tiUsedPlace;
	}

	public String getTiRemark() {
		return tiRemark;
	}

	public void setTiRemark(String tiRemark) {
		this.tiRemark = tiRemark;
	}

	public int getTiUseCost() {
		return tiUseCost;
	}

	public void setTiUseCost(int tiUseCost) {
		this.tiUseCost = tiUseCost;
	}

	public int getTiCancledCost() {
		return tiCancledCost;
	}

	public void setTiCancledCost(int tiCancledCost) {
		this.tiCancledCost = tiCancledCost;
	}

	public String getTiDate() {
		return tiDate;
	}

	public void setTiDate(String tiDate) {
		this.tiDate = tiDate;
	}

	@Override
	public String toString() {
		return "TransInfoVO [tiPk=" + tiPk + ", tiUsedPlace=" + tiUsedPlace + ", tiRemark=" + tiRemark + ", tiUseCost="
				+ tiUseCost + ", tiCancledCost=" + tiCancledCost + ", tiDate=" + tiDate + "]";
	}
	
}
