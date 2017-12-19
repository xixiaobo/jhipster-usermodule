package com.hcycom.jhipster.service.dto;

import java.util.Arrays;

public class Role_authoritys {
	private int roleid;
	private Integer[] authorityids;

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}


	public Integer[] getAuthorityids() {
		return authorityids;
	}

	public void setAuthorityids(Integer[] authorityids) {
		this.authorityids = authorityids;
	}

	@Override
	public String toString() {
		return "Role_authortys [roleid=" + roleid + ", authorityids=" + Arrays.toString(authorityids) + "]";
	}

}
