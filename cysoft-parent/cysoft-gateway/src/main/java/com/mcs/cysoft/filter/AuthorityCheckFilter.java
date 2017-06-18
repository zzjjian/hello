package com.mcs.cysoft.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AuthorityCheckFilter extends ZuulFilter{

	@Override
	public Object run() {
		// user access authority check filter
		RequestContext ctx = RequestContext.getCurrentContext(); 
		HttpServletRequest request = ctx.getRequest();
		String user_name = ctx.get("username").toString();
		
		// authority check
		// 传递用户名和当前的访问path
		System.out.println("access authority check filter");
		System.out.println(request.getRequestURI());
		System.out.println(request.getPathInfo());
		System.out.println(request.getServletPath());
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		RequestContext ctx = RequestContext.getCurrentContext();  
		
        // 如果前一个过滤器的结果为true，则说明上一个过滤器成功了，需要进入当前的过滤，如果前一个过滤器的结果为false，则说明上一个过滤器没有成功，则无需进行下面的过滤动作了，直接跳过后面的所有过滤器并返回结果
		return (boolean) ctx.get("isSuccess");
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}
