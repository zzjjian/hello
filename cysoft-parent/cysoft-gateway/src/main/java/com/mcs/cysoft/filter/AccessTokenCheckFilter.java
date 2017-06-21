package com.mcs.cysoft.filter;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AccessTokenCheckFilter extends ZuulFilter{
	
	@Autowired  
    private RestTemplate restTemplate;
	
    @Value("${cysoft.gateway.security.ignore.urls}")
    private String ignoredPaths;
	
	private Logger logger = LoggerFactory.getLogger(AccessTokenCheckFilter.class);
	
	private static PathMatcher matcher = new AntPathMatcher();

	@Override
	public Object run() {
		// access token check filter
		logger.info("access token check filter");
		
		RequestContext ctx = RequestContext.getCurrentContext();  
        HttpServletRequest request = ctx.getRequest();  
        
        String access_token = request.getHeader("Authorization");
        String user_name = request.getHeader("username");
        
        // token有效性验证
        boolean token_check = false;
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", access_token);
        headers.add("username", user_name);
        
        HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);
        // this.restTemplate
        // 可以直接从redis获取
        String url = "http://localhost:8762/v1/json/tickets/check";
        ResponseEntity<Boolean> a = this.restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class);
        token_check = a.getBody();
        
        if(token_check){
        	ctx.setSendZuulResponse(true);// 对该请求进行路由  
//            ctx.setResponseStatusCode(200);  
        	ctx.addZuulRequestHeader("username", user_name);
            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态  
            ctx.set("username", user_name);
            
            return null;
        } else {
        	 ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由  
             ctx.setResponseStatusCode(401);// 返回错误码  
             ctx.setResponseBody("{\"result\":\"access token is not correct!\"}");// 返回错误内容  
             ctx.set("isSuccess", false);  
             return null;  
        }
		//RequestContext.getCurrentContext().getResponse().sendRedirect("http://blog.csdn.net/liaokailin/");
	}
	
	public Collection<String> getIgnoredPaths() {
        if(StringUtils.isNotEmpty(ignoredPaths)) {
            return Lists.newArrayList(ignoredPaths.split(";"));
        }
        return new ArrayList<>(1);
    }

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();  
        HttpServletRequest request = ctx.getRequest();  
        for(String path: getIgnoredPaths()) {
            if(matcher.match(path, request.getRequestURI()))
            	ctx.set("isSuccess", false);
                return false;
        }
        /*if(request.getRequestURI().equals("/auth/v1/json/tickets")){
        	ctx.set("isSuccess", false);
        	return false;
        }*/
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
