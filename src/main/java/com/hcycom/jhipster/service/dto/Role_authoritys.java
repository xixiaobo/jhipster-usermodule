package com.hcycom.jhipster.service.dto;

import java.util.Arrays;

public class Role_authoritys {
	private String roleid;
	private String authorityid;
	private String[] authorityids;


	public String getRoleid() {
		return roleid;
	}


	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}


	public String getAuthorityid() {
		return authorityid;
	}


	public void setAuthorityid(String authorityid) {
		this.authorityid = authorityid;
	}


	public String[] getAuthorityids() {
		return authorityids;
	}


	public void setAuthorityids(String[] authorityids) {
		this.authorityids = authorityids;
	}


	@Override
	public String toString() {
		return "Role_authoritys [roleid=" + roleid + ", authorityid=" + authorityid + ", authorityids="
				+ Arrays.toString(authorityids) + "]";
	}

}
