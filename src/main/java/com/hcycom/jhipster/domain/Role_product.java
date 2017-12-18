package com.hcycom.jhipster.domain;

public class Role_product {
	private String uuid;
	private String role_uuid;
	private String product_uuid;
	private int type;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRole_uuid() {
		return role_uuid;
	}

	public void setRole_uuid(String role_uuid) {
		this.role_uuid = role_uuid;
	}

	public String getProduct_uuid() {
		return product_uuid;
	}

	public void setProduct_uuid(String product_uuid) {
		this.product_uuid = product_uuid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Role_product [uuid=" + uuid + ", role_uuid=" + role_uuid + ", product_uuid=" + product_uuid + ", type="
				+ type + "]";
	}

}
