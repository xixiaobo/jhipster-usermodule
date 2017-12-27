package com.hcycom.jhipster.service;

import org.springframework.web.bind.annotation.GetMapping;

import com.hcycom.jhipster.client.AuthorizedFeignClient;

@AuthorizedFeignClient(name = "${spring.application.name}")
public interface UrlService {
	
	@GetMapping("/v2/api-docs")
	String getDocumentation();
}
