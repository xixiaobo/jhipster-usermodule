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
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.hcycom.jhipster.domain.Authority;
import com.hcycom.jhipster.domain.Role;
import com.hcycom.jhipster.domain.Role_authority;
import com.hcycom.jhipster.service.dto.Role_authoritys;
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
	private AuthorityMapper authorityMapper;
	
	@Autowired
	private RoleMapper roleMapper;

	@RequestMapping(value = "/role_authority", method = RequestMethod.POST)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'usermodule/api/role_authority')")
	@ApiOperation(value = "更改角色权限", notes = "传入角色id以及权限id数组，更新角色所拥有的权限", httpMethod = "POST")
	public ResponseEntity<Map<String, Object>> role_authority(@RequestBody Role_authoritys role_authoritys) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Role_authority> list = role_authorityMapper.getAllRole_authorityByRole_uuid(role_authoritys.getRoleid());
		List<String> ids = new ArrayList<String>();
		for (Role_authority role_authority : list) {
			ids.add(role_authority.getAuthority_uuid());
		}
		List<String> ids2 = ids;
		List<String> newAuthorityids = new ArrayList<String>(Arrays.asList(role_authoritys.getAuthorityids()));
		ids.removeAll(newAuthorityids);
		newAuthorityids.removeAll(ids2);
		for (String id : ids) {
			Role_authority role_authority = new Role_authority();
			role_authority.setRole_uuid(role_authoritys.getRoleid());
			role_authority.setAuthority_uuid(id);
			role_authorityMapper.deleteRole_authorityByRoleAndAuthority(role_authority);
		}
		for (String id : newAuthorityids) {
			Role_authority role_authority = new Role_authority();
			role_authority.setRole_uuid(role_authoritys.getRoleid());
			role_authority.setAuthority_uuid(id);
			role_authorityMapper.addRole_authority(role_authority);
		}
		map.put("msg", "角色权限变更成功！");
		map.put("error_code", 0);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/role_authority_delete", method = RequestMethod.DELETE)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication,'usermodule/api/role_authority_delete')")
	@ApiOperation(value = "删除角色权限", notes = "根据角色id和权限id删除角色权限", httpMethod = "DELETE")
	public ResponseEntity<Map<String, Object>> role_authority_delete(@RequestBody Role_authoritys role_authoritys) {
		Map<String, Object> map = new HashMap<String, Object>();
		Role_authority role_authority = new Role_authority();
		role_authority.setAuthority_uuid(role_authoritys.getAuthorityid());
		role_authority.setRole_uuid(role_authoritys.getRoleid());
		int i = role_authorityMapper.deleteRole_authorityByRoleAndAuthority(role_authority);
		if (i == 0) {
			map.put("msg", "角色权限删除失败！");
			map.put("error_code", 1);
		} else if (i > 0) {
			map.put("msg", "角色权限删除成功！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/role_authority_deleteByMore", method = RequestMethod.DELETE)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication,'usermodule/api/role_authority_deleteByMore')")
	@ApiOperation(value = "删除多个角色权限", notes = "根据角色id和权限id数组删除角色所拥有的权限", httpMethod = "DELETE")
	public ResponseEntity<Map<String, Object>> role_authority_deleteByMore(@RequestBody Role_authoritys role_authoritys) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i=0;
		for (String authorityid : role_authoritys.getAuthorityids()) {
			Role_authority role_authority = new Role_authority();
			role_authority.setAuthority_uuid(authorityid);
			role_authority.setRole_uuid(role_authoritys.getRoleid());
			i = role_authorityMapper.deleteRole_authorityByRoleAndAuthority(role_authority);
		}
		if (i == 0) {
			map.put("msg", "角色权限批量删除失败！");
			map.put("error_code", 1);
		} else if (i > 0) {
			map.put("msg", "角色权限批量删除成功！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/role_authority_deleteByAll", method = RequestMethod.DELETE)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication,'usermodule/api/role_authority_deleteByAll')")
	@ApiOperation(value = "删除角色所有权限", notes = "根据角色id删除角色所拥有的所有权限", httpMethod = "DELETE")
	public ResponseEntity<Map<String, Object>> role_authority_deleteByAll(@RequestBody String roleid) {
		Map<String, Object> map = new HashMap<String, Object>();
			int i = role_authorityMapper.deleteRole_authorityByRole(roleid);
		if (i == 0) {
			map.put("msg", "角色所有权限删除失败！");
			map.put("error_code", 1);
		} else if (i > 0) {
			map.put("msg", "角色所有权限删除成功！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

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
	public ResponseEntity<Map<String, Object>> getRoleAuthority(@PathVariable String roleid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Role role=new Role();
		role=roleMapper.getUsersAuthority(roleid);
		if(role.getRole_name().equals("ROLE_ADMIN")){
			List<Authority> list=authorityMapper.getAllAuthorityByInterface();
			map.put("data", list);
			map.put("roledata", role);
			map.put("msg", role.getRole_name()+"的所有拥有的接口权限获取成功！");
			map.put("error_code", 0);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		List<Role_authority> role_authority = role_authorityMapper.getAllRole_authorityByRole_uuid(roleid);
		
		List<Authority> list=new ArrayList<Authority>();
		for (Role_authority role_authority2 : role_authority) {
			Authority authority=new Authority();
			authority=authorityMapper.getAuthorityByUuid(role_authority2.getAuthority_uuid());
			list.add(authority);
		}
		if (role_authority.size() >0) {
			map.put("data", list);
			map.put("roledata", role);
			map.put("msg", role.getRole_name()+"的所有拥有的接口权限获取成功！");
			map.put("error_code", 0);
			log.info("resource：" + map);
		} else if(role_authority.size() ==0){
			map.put("roledata", role);
			map.put("msg", role.getRole_name()+"的所有拥有的接口权限获取失败或为空！");
			map.put("error_code", 1);
			log.info("resource：" + map);
		}else {
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
