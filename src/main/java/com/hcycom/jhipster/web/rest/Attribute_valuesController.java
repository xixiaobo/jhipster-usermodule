package com.hcycom.jhipster.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.hcycom.jhipster.domain.Attribute_values;
import com.hcycom.jhipster.service.mapper.Attribute_valuesMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资源属性值管理接口
 * 
 * @author Xi
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = { "资源属性值管理" }, description = "资源属性值管理接口") 
public class Attribute_valuesController {

	@Autowired
	private Attribute_valuesMapper attribute_valuesMapper;

	@RequestMapping(value = "/attributevalues", method = RequestMethod.POST)
	@Timed
	@ApiOperation(value = "新增资源数据记录接口", notes = "传入资源属性值表参数，新增资源数据记录")
	public ResponseEntity<Map<String, Object>> attribute(@RequestBody Attribute_values attribute_values) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = attribute_valuesMapper.addAttribute_values(attribute_values);
		if (i == 0) {
			map.put("msg",
					"资源【" + attribute_values.getResource_name() + "】录入一条数据失败！");
			map.put("error_code", 1);
		} else if (i > 0) {
			map.put("data", attribute_values);
			map.put("msg",
					"资源【" + attribute_values.getResource_name() + "】成功录入一条数据！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/attributevalues_update", method = RequestMethod.PUT)
	@Timed
	@ApiOperation(value = "修改资源数据记录", notes = "传入资源数据记录参数，根据资源数据记录的uuid、资源数据记录属性key和资源名称修改资源数据记录")
	public ResponseEntity<Map<String, Object>> attribute_update(@RequestBody Attribute_values attribute_values) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = attribute_valuesMapper.updateAttribute_values(attribute_values);
		if (i == 0) {
			map.put("msg",
					"记录修改失败！");
			map.put("error_code", 1);
		} else if (i > 0) {
			map.put("data", attribute_values);
			map.put("msg",
					"记录修改成功");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/attributevalues_delete", method = RequestMethod.DELETE)
	@Timed
	@ApiOperation(value = "删除资源数据记录", notes = "传入资源数据记录参数，根据资源数据记录的uuid和资源名称删除资源数据记录")
	public ResponseEntity<Map<String, Object>> attribute_delete(@RequestBody Attribute_values attribute_values) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = attribute_valuesMapper.deleteAttribute_values(attribute_values);
		if (i == 0) {
				map.put("msg", "资源记录删除失败");
			map.put("error_code", 1);
		} else if (i > 0) {
			map.put("data", attribute_values);
			map.put("msg", "资源记录删除成功");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/attributevalues_get", method = RequestMethod.GET)
	@Timed
	@ApiOperation(value = "获取资源数据记录", notes = "传入资源数据记录参数，根据资源数据记录的uuid和资源名称获取资源数据记录")
	public ResponseEntity<Map<String, Object>> attribute_get(@RequestParam(value="uuid", required=true) String uuid, @RequestParam(value="resource_name") String resource_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		Attribute_values attribute_values=new Attribute_values();
		attribute_values.setUuid(uuid);
		attribute_values.setResource_name(resource_name);
		if (attribute_values.getUuid().equals("") || attribute_values.getUuid() == null) {
			List<Attribute_values> list = attribute_valuesMapper.findAttribute_valuesByResource_name(attribute_values);
			map.put("data", list);
			map.put("msg", "资源【" + attribute_values.getResource_name() + "】的所有属性查询成功！");
			map.put("error_code", 0);

		} else {
			List<Attribute_values> list =attribute_valuesMapper.findAttribute_valuesByResource_nameANDUuid(attribute_values);
			map.put("data", list);
			map.put("msg",
					"资源【" + attribute_values.getResource_name() + "】的【" + attribute_values.getUuid() + "】属性查询成功！");
			map.put("error_code", 0);
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
