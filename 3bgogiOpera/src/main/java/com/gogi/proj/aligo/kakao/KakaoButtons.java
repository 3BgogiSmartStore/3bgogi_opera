package com.gogi.proj.aligo.kakao;

public class KakaoButtons {

	private String ordering;
	private String name;
	private String linkType;
	private String linkTypeName;
	private String linkMo;
	private String linkPc;
	private String linkIos;
	private String linkAnd;
	
	public KakaoButtons() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KakaoButtons(String ordering, String name, String linkType, String linkTypeName, String linkMo,
			String linkPc, String linkIos, String linkAnd) {
		super();
		this.ordering = ordering;
		this.name = name;
		this.linkType = linkType;
		this.linkTypeName = linkTypeName;
		this.linkMo = linkMo;
		this.linkPc = linkPc;
		this.linkIos = linkIos;
		this.linkAnd = linkAnd;
	}

	public String getOrdering() {
		return ordering;
	}

	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getLinkTypeName() {
		return linkTypeName;
	}

	public void setLinkTypeName(String linkTypeName) {
		this.linkTypeName = linkTypeName;
	}

	public String getLinkMo() {
		return linkMo;
	}

	public void setLinkMo(String linkMo) {
		this.linkMo = linkMo;
	}

	public String getLinkPc() {
		return linkPc;
	}

	public void setLinkPc(String linkPc) {
		this.linkPc = linkPc;
	}

	public String getLinkIos() {
		return linkIos;
	}

	public void setLinkIos(String linkIos) {
		this.linkIos = linkIos;
	}

	public String getLinkAnd() {
		return linkAnd;
	}

	public void setLinkAnd(String linkAnd) {
		this.linkAnd = linkAnd;
	}

	@Override
	public String toString() {
		return "KakaoButtons [ordering=" + ordering + ", name=" + name + ", linkType=" + linkType + ", linkTypeName="
				+ linkTypeName + ", linkMo=" + linkMo + ", linkPc=" + linkPc + ", linkIos=" + linkIos + ", linkAnd="
				+ linkAnd + "]";
	}

}
