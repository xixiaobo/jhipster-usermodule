package com.hcycom.jhipster.domain;

public class Role_authority {
	private int uuid;
	private String role_uuid;
	private String authority_uuid;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	
	public String getRole_uuid() {
		return role_uuid;
	}
	public void setRole_uuid(String role_uuid) {
		this.role_uuid = role_uuid;
	}
	public String getAuthority_uuid() {
		return authority_uuid;
	}
	public void setAuthority_uuid(String authority_uuid) {
		this.authority_uuid = authority_uuid;
	}
	@Override
	public String toString() {
		return "Role_authority [uuid=" + uuid + ", role_uuid=" + role_uuid + ", authority_uuid=" + authority_uuid + "]";
	}
	
}
