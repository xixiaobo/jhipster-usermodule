package com.hcycom.jhipster.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

import com.hcycom.jhipster.client.AuthorizedFeignClient;

@AuthorizedFeignClient(name = "jhipstergateway")
public interface GetwayService {
	
	@GetMapping("api/gateway/routes")
	List<Map<String, Object>> getGateway();
}
