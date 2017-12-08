package com.hcycom.jhipster.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.hcycom.jhipster")
public class FeignConfiguration {

}
