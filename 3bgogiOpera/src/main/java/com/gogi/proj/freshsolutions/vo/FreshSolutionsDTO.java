package com.gogi.proj.freshsolutions.vo;

public class FreshSolutionsDTO {

	private String operationTime;

	public FreshSolutionsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FreshSolutionsDTO(String operationTime) {
		super();
		this.operationTime = operationTime;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	@Override
	public String toString() {
		return "FreshSolutionsDTO [operationTime=" + operationTime + "]";
	}
	
}
