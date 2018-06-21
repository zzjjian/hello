package com.mcs.cysoft.auth.netflix.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user-service")
public interface UrpFeignClient {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	boolean userAuth();
}
