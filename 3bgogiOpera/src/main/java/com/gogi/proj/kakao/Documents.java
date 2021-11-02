package com.gogi.proj.kakao;

public class Documents {
	private String address_name;
	private String address_type;
	private String x;
	private String y;
	private Address address;
	private RoadAddress road_address;
	
	public Documents() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Documents(String address_name, String address_type, String x, String y, Address address,
			RoadAddress road_address) {
		super();
		this.address_name = address_name;
		this.address_type = address_type;
		this.x = x;
		this.y = y;
		this.address = address;
		this.road_address = road_address;
	}

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public String getAddress_type() {
		return address_type;
	}

	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public RoadAddress getRoad_address() {
		return road_address;
	}

	public void setRoad_address(RoadAddress road_address) {
		this.road_address = road_address;
	}

	@Override
	public String toString() {
		return "Documents [address_name=" + address_name + ", address_type=" + address_type + ", x=" + x + ", y=" + y
				+ ", address=" + address + ", road_address=" + road_address + "]";
	}

}
