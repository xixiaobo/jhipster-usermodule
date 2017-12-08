package com.hcycom.jhipster.web.rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.hcycom.jhipster.domain.Role;
import com.hcycom.jhipster.service.mapper.RoleMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资源管理接口
 * @author Xi
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = { "角色管理" }, description = "角色管理接口")
public class RolesController {
	private final Logger log = LoggerFactory.getLogger(RolesController.class);
	
	@Autowired
	private RoleMapper roleMapperr;
	
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	@Timed
	@ApiOperation(value = "创建角色", notes = "传入角色表参数，创建角色")
	public ResponseEntity<Map<String, Object>> role(@RequestBody Role role){
		Map<String, Object> map = new HashMap<String, Object>();
		int i=roleMapperr.addRole(role);
		if(i==0){
			map.put("msg","角色【"+role.getRole_name()+"】创建失败！");
			map.put("error_code", 1);
		}else if(i>0){
			map.put("data",role);
			map.put("msg","角色【"+role.getRole_name()+"】创建成功！");
			map.put("error_code", 0);
		}else {
			map.put("msg","服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/role_update", method = RequestMethod.PUT)
	@Timed
	@ApiOperation(value = "修改角色", notes = "传入角色表参数，根据角色id修改角色")
	public ResponseEntity<Map<String, Object>> role_update(@RequestBody Role role){
		Map<String, Object> map = new HashMap<String, Object>();
		int i=roleMapperr.updateRole(role);
		if(i==0){
			map.put("msg","角色【"+role.getRole_name()+"】修改失败！");
			map.put("error_code", 1);
		}else if(i>0){
			map.put("data",role);
			map.put("msg","角色【"+role.getRole_name()+"】修改成功！");
			map.put("error_code", 0);
		}else {
			map.put("msg","服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/role_delete", method = RequestMethod.DELETE)
	@Timed
	@ApiOperation(value = "删除角色", notes = "传入角色表参数，根据角色id删除角色")
	public ResponseEntity<Map<String, Object>> role_delete(@RequestBody Role role){
		Map<String, Object> map = new HashMap<String, Object>();
		int i=roleMapperr.deleteRole(role);
		if(i==0){
			map.put("msg","角色【"+role.getRole_name()+"】删除失败！");
			map.put("error_code", 1);
		}else if(i>0){
			map.put("data",role);
			map.put("msg","角色【"+role.getRole_name()+"】删除成功！");
			map.put("error_code", 0);
		}else {
			map.put("msg","服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/role_get", method = RequestMethod.GET)
	@Timed
	@ApiOperation(value = "获取角色", notes = "传入资源表参数，根据角色id获取角色")
	public ResponseEntity<Map<String, Object>> resource_get(@RequestParam(value = "uuid") String uuid){
		log.info("request roleuuid："+uuid);
		Role role=new Role();
		Map<String, Object> map = new HashMap<String, Object>();
		role=roleMapperr.getUsersAuthority(uuid);
		if(role!=null){
			map.put("data",role);
			map.put("msg","角色【"+role.getRole_name()+"】获取成功！");
			map.put("error_code", 0);
			log.info("resource："+map);
		}else {
			map.put("msg","服务器出问题了！");
			map.put("error_code", 2);
			log.info("resource："+map);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	
	
}
