package com.hcycom.jhipster.web.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.hcycom.jhipster.domain.Authority;
import com.hcycom.jhipster.domain.Role;
import com.hcycom.jhipster.domain.Role_authority;
import com.hcycom.jhipster.service.mapper.AuthorityMapper;
import com.hcycom.jhipster.service.mapper.RoleMapper;
import com.hcycom.jhipster.service.mapper.Role_authorityMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资源管理接口
 * 
 * @author Xi
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = { "角色权限管理" }, description = "角色权限管理接口")
public class Roles_AuthorityController {
	private final Logger log = LoggerFactory.getLogger(Roles_AuthorityController.class);

	@Autowired
	private Role_authorityMapper role_authorityMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private AuthorityMapper authorityMapper;

	@RequestMapping(value = "/role_authority", method = RequestMethod.POST)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'usermodule/api/role_authority')")
	@ApiOperation(value = "更改角色权限", notes = "传入角色id以及权限id数组，更新角色所拥有的权限", httpMethod = "POST")
	public ResponseEntity<Map<String, Object>> role_authority(@RequestParam("roleid") int roleid,
			@RequestBody int[] authorityids) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Role_authority> role_authoritys = role_authorityMapper.getAllRole_authorityByRole_uuid(roleid);
		List<Integer> ids = new ArrayList<Integer>();
		for (Role_authority role_authority : role_authoritys) {
			ids.add(role_authority.getAuthority_uuid());
		}
		List<Integer> ids2 = ids;
		List<Integer> newAuthorityids = new ArrayList(Arrays.asList(authorityids));
		ids.removeAll(newAuthorityids);
		newAuthorityids.removeAll(ids2);
		for (Integer id : ids) {
			Role_authority role_authority = new Role_authority();
			role_authority.setRole_uuid(roleid);
			role_authority.setAuthority_uuid(id);
			role_authorityMapper.deleteRole_authorityByRoleAndAuthority(role_authority);
		}
		for (Integer id : newAuthorityids) {
			Role_authority role_authority = new Role_authority();
			role_authority.setRole_uuid(roleid);
			role_authority.setAuthority_uuid(id);
			role_authorityMapper.addRole_authority(role_authority);
		}
		map.put("msg", "角色权限变更成功！");
		map.put("error_code", 0);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	// @RequestMapping(value = "/role_authority_delete/{uuid}", method =
	// RequestMethod.DELETE)
	// @Timed
	// @PreAuthorize("@InterfacePermissions.hasPermission(authentication,
	// 'usermodule/api/role_authority_delete/{uuid}')")
	// @ApiOperation(value = "删除角色权限", notes =
	// "根据记录id删除角色权限记录",httpMethod="DELETE")
	// public ResponseEntity<Map<String, Object>>
	// role_authority_delete(@PathVariable int uuid){
	// Map<String, Object> map = new HashMap<String, Object>();
	// int i=role_authorityMapper.deleteRole_authority(uuid);
	// if(i==0){
	// map.put("msg","角色权限删除失败！");
	// map.put("error_code", 1);
	// }else if(i>0){
	// map.put("msg","角色权限删除成功！");
	// map.put("error_code", 0);
	// }else {
	// map.put("msg","服务器出问题了！");
	// map.put("error_code", 2);
	// }
	// return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	// }

	// @RequestMapping(value = "/role_authority_get/{uuid}", method =
	// RequestMethod.GET)
	// @Timed
	// @PreAuthorize("@InterfacePermissions.hasPermission(authentication,
	// 'usermodule/api/role_authority_get/{uuid}')")
	// @ApiOperation(value = "获取角色权限记录详情", notes =
	// "传入角色权限记录id，根据id获取角色权限记录详情",httpMethod="GET")
	// public ResponseEntity<Map<String, Object>>
	// role_authority_get(@PathVariable int uuid){
	// log.info("request roleuuid："+uuid);
	// Map<String, Object> map = new HashMap<String, Object>();
	// Role_authority
	// role_authority=role_authorityMapper.getRole_authorityByUuid(uuid);
	// if(role_authority.getUuid()!=0){
	// Role role=roleMapper.getUsersAuthority(role_authority.getRole_uuid());
	// Authority
	// authority=authorityMapper.getAuthorityByUuid(role_authority.getAuthority_uuid());
	// map.put("roeldata",role);
	// map.put("authoritydata",authority);
	// map.put("msg","角色权限获取成功！");
	// map.put("error_code", 0);
	// log.info("resource："+map);
	// }else {
	// map.put("msg","服务器出问题了！");
	// map.put("error_code", 2);
	// log.info("resource："+map);
	// }
	// return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	// }

	@RequestMapping(value = "/getRoleAuthority/{roleid}", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'usermodule/api/getRoleAuthority')")
	@ApiOperation(value = "获取角色权限", notes = "获取角色拥有的所有权限", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> getRoleAuthority(@PathVariable int roleid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Role_authority> role_authority = role_authorityMapper.getAllRole_authorityByRole_uuid(roleid);
		if (role_authority.size() >= 0) {
			map.put("data", role_authority);
			map.put("msg", "所有接口权限获取成功！");
			map.put("error_code", 0);
			log.info("resource：" + map);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
			log.info("resource：" + map);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllInterfaceAuthority", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'usermodule/api/getAllInterfaceAuthority')")
	@ApiOperation(value = "获取所有接口权限", notes = "获取所有接口权限记录", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> getAllInterfaceAuthority() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Authority> authoritys = authorityMapper.getAllAuthorityByInterface();
		if (authoritys.size() > 0) {
			map.put("data", authoritys);
			map.put("msg", "所有接口权限获取成功！");
			map.put("error_code", 0);
			log.info("resource：" + map);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
			log.info("resource：" + map);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
