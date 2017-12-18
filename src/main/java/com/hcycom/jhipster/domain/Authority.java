package com.hcycom.jhipster.domain;

public class Authority {
	private int uuid;
	private String authority_name;
	private String authority_type;
	private int authority_number;
	private String authority_url;
	private int authority_status;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getAuthority_name() {
		return authority_name;
	}
	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}
	public String getAuthority_type() {
		return authority_type;
	}
	public void setAuthority_type(String authority_type) {
		this.authority_type = authority_type;
	}
	public int getAuthority_number() {
		return authority_number;
	}
	public void setAuthority_number(int authority_number) {
		this.authority_number = authority_number;
	}
	public String getAuthority_url() {
		return authority_url;
	}
	public void setAuthority_url(String authority_url) {
		this.authority_url = authority_url;
	}
	public int getAuthority_status() {
		return authority_status;
	}
	public void setAuthority_status(int authority_status) {
		this.authority_status = authority_status;
	}
	@Override
	public String toString() {
		return "Authority [uuid=" + uuid + ", authority_name=" + authority_name + ", authority_type=" + authority_type
				+ ", authority_number=" + authority_number + ", authority_url=" + authority_url + ", authority_status="
				+ authority_status + "]";
	}
	
	
}
