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
import com.hcycom.jhipster.domain.Attribute;
import com.hcycom.jhipster.service.mapper.AttributeMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资源属性管理接口
 * 
 * @author Xi
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = { "资源属性管理" }, description = "资源属性管理接口")
public class AttributeController {

	@Autowired
	private AttributeMapper attributeMapper;

	@RequestMapping(value = "/attribute", method = RequestMethod.POST)
	@Timed
	@ApiOperation(value = "创建资源属性", notes = "传入资源属性表参数，创建资源属性")
	public ResponseEntity<Map<String, Object>> attribute(@RequestBody Attribute attribute) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = attributeMapper.addAttribute(attribute);
		if (i == 0) {
			map.put("msg",
					"资源【" + attribute.getResource_name_foreign() + "】的【" + attribute.getAttribute_name() + "】属性创建失败！");
			map.put("error_code", 1);
		} else if (i > 0) {
			map.put("data", attribute);
			map.put("msg",
					"资源【" + attribute.getResource_name_foreign() + "】的【" + attribute.getAttribute_name() + "】属性创建失败！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/attribute_update", method = RequestMethod.PUT)
	@Timed
	@ApiOperation(value = "修改资源属性", notes = "传入资源属性表参数，根据资源属性key和资源名称修改资源属性")
	public ResponseEntity<Map<String, Object>> attribute_update(@RequestBody Attribute attribute) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = attributeMapper.updateAttribute(attribute);
		if (i == 0) {
			map.put("msg",
					"资源【" + attribute.getResource_name_foreign() + "】的【" + attribute.getAttribute_name() + "】属性修改失败！");
			map.put("error_code", 1);
		} else if (i > 0) {
			map.put("data", attribute);
			map.put("msg",
					"资源【" + attribute.getResource_name_foreign() + "】的【" + attribute.getAttribute_name() + "】属性修改成功！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/attribute_delete", method = RequestMethod.DELETE)
	@Timed
	@ApiOperation(value = "删除资源属性", notes = "传入资源属性表参数，根据资源属性key和资源名称删除资源属性")
	public ResponseEntity<Map<String, Object>> attribute_delete(@RequestBody Attribute attribute) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = attributeMapper.deleteAttribute(attribute);
		if (i == 0) {
			if (attribute.getAttribute_key().equals("") || attribute.getAttribute_key() == null) {
				map.put("msg", "资源【" + attribute.getResource_name_foreign() + "】的所有属性删除失败！");
			} else {
				map.put("msg", "资源【" + attribute.getResource_name_foreign() + "】的【" + attribute.getAttribute_key()
						+ "】属性删除失败！");
			}
			map.put("error_code", 1);
		} else if (i > 0) {
			map.put("data", attribute);
			if (attribute.getAttribute_key().equals("") || attribute.getAttribute_key() == null) {
				map.put("msg", "资源【" + attribute.getResource_name_foreign() + "】的所有属性删除成功！");
			} else {
				map.put("msg", "资源【" + attribute.getResource_name_foreign() + "】的【" + attribute.getAttribute_key()
						+ "】属性删除成功！");
			}
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器出问题了！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/attribute_get", method = RequestMethod.GET)
	@Timed
	@ApiOperation(value = "获取资源属性", notes = "传入资源属性表参数，根据资源属性key和资源名称获取资源")
	public ResponseEntity<Map<String, Object>> attribute_get(@RequestParam(value="attribute_key", required=true) String attribute_key, @RequestParam(value="resource_name") String resource_name ) {
		Map<String, Object> map = new HashMap<String, Object>();
		Attribute attribute = new Attribute();
		attribute.setAttribute_key(attribute_key);
		attribute.setResource_name_foreign(resource_name);
		if (attribute.getAttribute_key().equals("") || attribute.getAttribute_key() == null) {
			List<Attribute> list = attributeMapper.findAttributeByResource_name(attribute);
			map.put("data", list);
			map.put("msg", "资源【" + attribute.getResource_name_foreign() + "】的所有属性查询成功！");
			map.put("error_code", 0);

		} else {
			attribute=attributeMapper.findAttributeByResource_nameAndAttribute_key(attribute);
			map.put("data", attribute);
			map.put("msg",
					"资源【" + attribute.getResource_name_foreign() + "】的【" + attribute.getAttribute_key() + "】属性查询成功！");
			map.put("error_code", 0);
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
