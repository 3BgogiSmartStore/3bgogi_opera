package com.gogi.proj.kakao;

import java.util.List;

public class KakaoDTO {

	private Meta meta;
	private List<Documents> documents;
	
	public KakaoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KakaoDTO(Meta meta, List<Documents> documents) {
		super();
		this.meta = meta;
		this.documents = documents;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Documents> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "KakaoDTO [meta=" + meta + ", documents=" + documents + "]";
	}

}
