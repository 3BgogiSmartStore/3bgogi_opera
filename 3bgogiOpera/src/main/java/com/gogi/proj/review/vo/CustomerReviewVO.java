package com.gogi.proj.review.vo;

public class CustomerReviewVO{

	private int crPk;					// 고유값
	private String crNum;				// 리뷰 글 번호
	private String crProdName;			// 리뷰 상품명
	private int crGrade;				// 평점
	private int crType;					// 리뷰 노출 타입			0 전체, 1 상황판
	private String crContent;			// 리뷰 내용
	private String crProdOrderNum;		// 상품주문번호
	private String crRegdate;			// 등록일
	private int reviewCount;			// 리뷰 개수 카운트
	
	public CustomerReviewVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerReviewVO(int crPk, String crNum, String crProdName, int crGrade, int crType, String crContent,
			String crProdOrderNum, String crRegdate, int reviewCount) {
		super();
		this.crPk = crPk;
		this.crNum = crNum;
		this.crProdName = crProdName;
		this.crGrade = crGrade;
		this.crType = crType;
		this.crContent = crContent;
		this.crProdOrderNum = crProdOrderNum;
		this.crRegdate = crRegdate;
		this.reviewCount = reviewCount;
	}

	public int getCrPk() {
		return crPk;
	}

	public void setCrPk(int crPk) {
		this.crPk = crPk;
	}

	public String getCrNum() {
		return crNum;
	}

	public void setCrNum(String crNum) {
		this.crNum = crNum;
	}

	public String getCrProdName() {
		return crProdName;
	}

	public void setCrProdName(String crProdName) {
		this.crProdName = crProdName;
	}

	public int getCrGrade() {
		return crGrade;
	}

	public void setCrGrade(int crGrade) {
		this.crGrade = crGrade;
	}

	public int getCrType() {
		return crType;
	}

	public void setCrType(int crType) {
		this.crType = crType;
	}

	public String getCrContent() {
		return crContent;
	}

	public void setCrContent(String crContent) {
		this.crContent = crContent;
	}

	public String getCrProdOrderNum() {
		return crProdOrderNum;
	}

	public void setCrProdOrderNum(String crProdOrderNum) {
		this.crProdOrderNum = crProdOrderNum;
	}

	public String getCrRegdate() {
		return crRegdate;
	}

	public void setCrRegdate(String crRegdate) {
		this.crRegdate = crRegdate;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	@Override
	public String toString() {
		return "CustomerReviewVO [crPk=" + crPk + ", crNum=" + crNum + ", crProdName=" + crProdName + ", crGrade="
				+ crGrade + ", crType=" + crType + ", crContent=" + crContent + ", crProdOrderNum=" + crProdOrderNum
				+ ", crRegdate=" + crRegdate + ", reviewCount=" + reviewCount + "]";
	}
}
