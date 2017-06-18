package com.mcs.cysoft.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AccessTokenCheckFilter extends ZuulFilter{
	
	@Autowired  
    private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(AccessTokenCheckFilter.class);

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

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();  
        HttpServletRequest request = ctx.getRequest();  
        if(request.getRequestURI().equals("/auth/v1/json/tickets")){
        	ctx.set("isSuccess", false);
        	return false;
        }
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
