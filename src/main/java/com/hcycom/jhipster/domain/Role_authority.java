package com.hcycom.jhipster.domain;

public class Role_authority {
	private int uuid;
	private int role_uuid;
	private int authority_uuid;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public int getRole_uuid() {
		return role_uuid;
	}
	public void setRole_uuid(int role_uuid) {
		this.role_uuid = role_uuid;
	}
	public int getAuthority_uuid() {
		return authority_uuid;
	}
	public void setAuthority_uuid(int authority_uuid) {
		this.authority_uuid = authority_uuid;
	}
	@Override
	public String toString() {
		return "Role_authority [uuid=" + uuid + ", role_uuid=" + role_uuid + ", authority_uuid=" + authority_uuid + "]";
	}
	
}
