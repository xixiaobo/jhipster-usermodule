package com.hcycom.jhipster.web.rest;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.hcycom.jhipster.domain.Authority;
import com.hcycom.jhipster.service.UAAUrlService;
import com.hcycom.jhipster.service.mapper.AuthorityMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api")
@Api(tags = { "UAA认证服务器接口接口路径管理" })
public class UAAUrlMapping {
	private final Logger log = LoggerFactory.getLogger(UAAUrlMapping.class);

	String applicationName = "jhipsteruaa";

	@Autowired
	private UAAUrlService urlService;

	@Autowired
	private AuthorityMapper authorityMapper;

	@RequestMapping(value = "/getUAAUrlMapping", method = RequestMethod.GET)
	@Timed
	@ApiOperation(value = "获取所有接口", notes = "获取所有应用接口,无权限管理", httpMethod = "GET")
	public void getUrlMapping() {
		String json = urlService.getDocumentation();
		List<Map<String, String>> map = getinterface(json);
		List<Authority> list = authorityMapper.getAllAuthorityByInterface();
		List<Authority> list2 = new ArrayList<Authority>();
		for (Map<String, String> map2 : map) {
			Authority authority = new Authority();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			authority.setUuid(uuid);
			authority.setAuthority_type("4");
			authority.setAuthority_url(map2.get("url"));
			authority.setAuthority_name(map2.get("summary"));
			authority.setForeign_uuid(map2.get("tags"));
			authority.setAuthority_status(1);
			int k = 0;
			for (Authority authority2 : list) {
				if (authority2.getAuthority_url().equals(authority.getAuthority_url())) {
					k = 1;
				}
			}
			if (k == 0) {
				list2.add(authority);
			}
		}

		if (list2.size() > 0) {
			authorityMapper.addMoreInterfaceAuthority(list2);
		}
	}

	private List<Map<String, String>> getinterface(String urljson) {
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
					urlmap.put("url", applicationName + urlkey + "--" + methodkey);
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

		// JSONArray tagsarray = JSONArray.fromObject(map.get("tags"));
		// List<Map<String, String>> tagslist = JSONArray.toList(tagsarray,
		// Map.class);
		//
		// for (Map<String, String> tagsmap : tagslist) {
		// List<Map<String, String>> allList =new ArrayList<Map<String,
		// String>>();
		// for (Map<String, String> urlmap : urllist) {
		// if (urlmap.get("tags").equals(tagsmap.get("name"))) {
		// allList.add(urlmap);
		// }
		// }
		// getInterface.put(tagsmap.get("name"), allList);
		// }
		return urllist;
	}

}
