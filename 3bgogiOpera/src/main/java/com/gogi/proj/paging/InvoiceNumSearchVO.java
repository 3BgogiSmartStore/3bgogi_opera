package com.gogi.proj.paging;

public class InvoiceNumSearchVO {

	private String invoiceNumDate;
	private int delivComp;
	private int recType;
	
	public InvoiceNumSearchVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceNumSearchVO(int delivComp, int recType, String invoiceNumDate) {
		super();
		this.invoiceNumDate = invoiceNumDate;
		this.delivComp = delivComp;
		this.recType = recType;
	}

	public String getInvoiceNumDate() {
		return invoiceNumDate;
	}

	public void setInvoiceNumDate(String invoiceNumDate) {
		this.invoiceNumDate = invoiceNumDate;
	}

	public int getDelivComp() {
		return delivComp;
	}

	public void setDelivComp(int delivComp) {
		this.delivComp = delivComp;
	}

	public int getRecType() {
		return recType;
	}

	public void setRecType(int recType) {
		this.recType = recType;
	}

	@Override
	public String toString() {
		return "InvoiceNumSearchVO [invoiceNumDate=" + invoiceNumDate + ", delivComp=" + delivComp + ", recType="
				+ recType + "]";
	}

}
