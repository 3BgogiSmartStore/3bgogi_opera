package com.gogi.proj.kakao;

public class Meta {
	private int total_count;
	private int pageable_count;
	private boolean is_end;
	
	public Meta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Meta(int total_count, int pageable_count, boolean is_end) {
		super();
		this.total_count = total_count;
		this.pageable_count = pageable_count;
		this.is_end = is_end;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public int getPageable_count() {
		return pageable_count;
	}

	public void setPageable_count(int pageable_count) {
		this.pageable_count = pageable_count;
	}

	public boolean isIs_end() {
		return is_end;
	}

	public void setIs_end(boolean is_end) {
		this.is_end = is_end;
	}

	@Override
	public String toString() {
		return "Meta [total_count=" + total_count + ", pageable_count=" + pageable_count + ", is_end=" + is_end + "]";
	}
	
}
