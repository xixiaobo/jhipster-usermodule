package com.hcycom.jhipster.domain;

public class Product {

	private String uuid;
	private String product_name;
	private String product_desc;
	private String product_status;
	private String product_icon;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

	public String getProduct_status() {
		return product_status;
	}

	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}

	public String getProduct_icon() {
		return product_icon;
	}

	public void setProduct_icon(String product_icon) {
		this.product_icon = product_icon;
	}

	@Override
	public String toString() {
		return "Product [uuid=" + uuid + ", product_name=" + product_name + ", product_desc=" + product_desc
				+ ", product_status=" + product_status + ", product_icon=" + product_icon + "]";
	}

}
