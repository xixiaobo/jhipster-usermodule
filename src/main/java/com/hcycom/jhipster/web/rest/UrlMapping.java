package com.hcycom.jhipster.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.hcycom.jhipster.service.UrlService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api")
@Api(tags = { "接口路径管理" })
public class UrlMapping {
	private final Logger log = LoggerFactory.getLogger(UrlMapping.class);

	@Value("${spring.application.name}")
	String applicationName;
	
	@Autowired
	private UrlService urlService;

	@RequestMapping(value = "/getUrlMapping", method = RequestMethod.GET)
	@Timed
	@ApiOperation(value = "获取所有接口", notes = "获取所有应用接口,无权限管理", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> getUrlMapping() {
		String json = urlService.getDocumentation();
		Map<String, Object> map=getinterface(json);
		map.put("applicationName", applicationName);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	private Map<String, Object> getinterface(String urljson){
		Map<String, Object> getInterface = new HashMap<String, Object>();
		JSONObject array = JSONObject.fromObject(urljson);
		Map map = (Map) JSONObject.toBean(array, Map.class);

		JSONArray pathsarray = JSONArray.fromObject(map.get("paths"));
		List<Map<String, String>> pathslist = JSONArray.toList(pathsarray, Map.class);
		List<Map<String, String>> urllist = new ArrayList<Map<String, String>>();
		for (Map<String, String> pathsmap : pathslist) {
			for (String urlkey : pathsmap.keySet()) {
				Map<String, String> urlmap = new HashMap<String, String>();
				JSONObject methodarray = JSONObject.fromObject(pathsmap.get(urlkey));
				Map<String, Object> methodmap = (Map<String, Object>) JSONObject.toBean(methodarray, Map.class);
				for (String methodkey : methodmap.keySet()) {
					urlmap.put("url", applicationName+urlkey + "--" + methodkey);
					JSONObject labelarray = JSONObject.fromObject(methodmap.get(methodkey));
					Map labelmap = (Map) JSONObject.toBean(labelarray, Map.class);
					urlmap.put("summary", labelmap.get("summary") + "");
					urlmap.put("description", labelmap.get("description") + "");
					String tags = labelmap.get("tags") + "";
					String tags2 = tags.replaceAll("[\\[\\]]", "");
					urlmap.put("tags", tags2);
				}
				urllist.add(urlmap);
			}
		}

		JSONArray tagsarray = JSONArray.fromObject(map.get("tags"));
		List<Map<String, String>> tagslist = JSONArray.toList(tagsarray, Map.class);

		for (Map<String, String> tagsmap : tagslist) {
			List<Map<String, String>> allList =new ArrayList<Map<String, String>>();
			for (Map<String, String> urlmap : urllist) {
				if (urlmap.get("tags").equals(tagsmap.get("name"))) {
					allList.add(urlmap);
				}
			}
			getInterface.put(tagsmap.get("name"), allList);
		}
		return getInterface;
	}

}
