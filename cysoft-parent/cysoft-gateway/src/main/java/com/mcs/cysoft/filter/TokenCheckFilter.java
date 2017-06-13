package com.mcs.cysoft.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

@Component
public class TokenCheckFilter extends ZuulFilter{
	
	private Logger logger = LoggerFactory.getLogger(TokenCheckFilter.class);

	@Override
	public Object run() {
		// access token check filter
		logger.info("access token check filter");
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}
