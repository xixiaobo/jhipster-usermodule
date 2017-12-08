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
import com.hcycom.jhipster.domain.Resource;
import com.hcycom.jhipster.service.mapper.ResourceMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资源管理接口
 * @author Xi
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = { "资源管理" }, description = "资源管理接口")
public class ResourceController {
	private final Logger log = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private ResourceMapper resourceMapper;
	
	@RequestMapping(value = "/resource", method = RequestMethod.POST)
	@Timed
	@ApiOperation(value = "创建资源", notes = "传入资源表参数，创建资源")
	public ResponseEntity<Map<String, Object>> resource(@RequestBody Resource resource){
		Map<String, Object> map = new HashMap<String, Object>();
		int i=resourceMapper.addResource(resource);
		if(i==0){
			map.put("msg","资源【"+resource.getResource_name()+"】创建失败！");
			map.put("error_code", 1);
		}else if(i>0){
			map.put("data",resource);
			map.put("msg","资源【"+resource.getResource_name()+"】创建成功！");
			map.put("error_code", 0);
		}else {
			map.put("msg","服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/resource_update", method = RequestMethod.PUT)
	@Timed
	@ApiOperation(value = "修改资源", notes = "传入资源表参数，根据资源名称修改资源")
	public ResponseEntity<Map<String, Object>> resource_update(@RequestBody Resource resource){
		Map<String, Object> map = new HashMap<String, Object>();
		int i=resourceMapper.updateResource(resource);
		if(i==0){
			map.put("msg","资源【"+resource.getResource_name()+"】修改失败！");
			map.put("error_code", 1);
		}else if(i>0){
			map.put("data",resource);
			map.put("msg","资源【"+resource.getResource_name()+"】修改成功！");
			map.put("error_code", 0);
		}else {
			map.put("msg","服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/resource_delete", method = RequestMethod.DELETE)
	@Timed
	@ApiOperation(value = "删除资源", notes = "传入资源表参数，根据资源名称删除资源")
	public ResponseEntity<Map<String, Object>> resource_delete(@RequestBody Resource resource){
		Map<String, Object> map = new HashMap<String, Object>();
		int i=resourceMapper.deleteResource(resource);
		if(i==0){
			map.put("msg","资源【"+resource.getResource_name()+"】删除失败！");
			map.put("error_code", 1);
		}else if(i>0){
			map.put("data",resource);
			map.put("msg","资源【"+resource.getResource_name()+"】删除成功！");
			map.put("error_code", 0);
		}else {
			map.put("msg","服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/resource_get", method = RequestMethod.GET)
	@Timed
	@ApiOperation(value = "获取资源", notes = "传入资源表参数，根据资源名称获取资源")
//	public ResponseEntity<Map<String, Object>> resource_get(@RequestParam(value = "resource") Resource resource){
//		log.info("request Resource："+resource);
//		Map<String, Object> map = new HashMap<String, Object>();
//		resource=resourceMapper.findResoureByResource_name(resource.getResource_name());
//		if(resource!=null){
//			map.put("data",resource);
//			map.put("msg","资源【"+resource.getResource_name()+"】获取成功！");
//			map.put("error_code", 0);
//			log.info("resource："+map);
//		}else {
//			map.put("msg","服务器出问题了！");
//			map.put("error_code", 2);
//			log.info("resource："+map);
//		}
//		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//	}
	public ResponseEntity<Map<String, Object>> resource_get(@RequestParam(value = "resource_name") String resource_name){
		log.info("request Resource："+resource_name);
		Resource resource=new Resource();
		Map<String, Object> map = new HashMap<String, Object>();
		resource=resourceMapper.findResoureByResource_name(resource_name);
		if(resource!=null){
			map.put("data",resource);
			map.put("msg","资源【"+resource_name+"】获取成功！");
			map.put("error_code", 0);
			log.info("resource："+map);
		}else {
			map.put("msg","服务器出问题了！");
			map.put("error_code", 2);
			log.info("resource："+map);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	/*
	 * public ResponseEntity<Map<String, Object>> resource_get(@RequestBody Map<String, String> params){
		String resource_name = params.get("resource_name");
		log.info("request Resource："+resource_name);
		Resource resource=new Resource();
		Map<String, Object> map = new HashMap<String, Object>();
		resource=resourceMapper.findResoureByResource_name(resource_name);
		if(resource!=null){
			map.put("data",resource);
			map.put("msg","资源【"+resource_name+"】获取成功！");
			map.put("error_code", 0);
			log.info("resource："+map);
		}else {
			map.put("msg","服务器出问题了！");
			map.put("error_code", 2);
			log.info("resource："+map);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	 * */
	
	
}
