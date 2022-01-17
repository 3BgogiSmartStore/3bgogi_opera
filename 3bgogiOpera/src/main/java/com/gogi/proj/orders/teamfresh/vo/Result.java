package com.gogi.proj.orders.teamfresh.vo;

public class Result {

	private String areaGroupLabel;
	private String hubName;
	private String delyverYn;
	private String delyverResult;
	
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(String areaGroupLabel, String hubName, String delyverYn, String delyverResult) {
		super();
		this.areaGroupLabel = areaGroupLabel;
		this.hubName = hubName;
		this.delyverYn = delyverYn;
		this.delyverResult = delyverResult;
	}

	public String getAreaGroupLabel() {
		return areaGroupLabel;
	}

	public void setAreaGroupLabel(String areaGroupLabel) {
		this.areaGroupLabel = areaGroupLabel;
	}

	public String getHubName() {
		return hubName;
	}

	public void setHubName(String hubName) {
		this.hubName = hubName;
	}

	public String getDelyverYn() {
		return delyverYn;
	}

	public void setDelyverYn(String delyverYn) {
		this.delyverYn = delyverYn;
	}

	public String getDelyverResult() {
		return delyverResult;
	}

	public void setDelyverResult(String delyverResult) {
		this.delyverResult = delyverResult;
	}

	@Override
	public String toString() {
		return "result [areaGroupLabel=" + areaGroupLabel + ", hubName=" + hubName + ", delyverYn=" + delyverYn
				+ ", delyverResult=" + delyverResult + "]";
	}
}
