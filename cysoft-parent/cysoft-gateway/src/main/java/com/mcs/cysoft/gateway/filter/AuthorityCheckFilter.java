package com.mcs.cysoft.gateway.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.mcs.cysoft.common.entity.UserRedisEntity;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AuthorityCheckFilter extends ZuulFilter{
	
	@Autowired
	private RedisTemplate<String, UserRedisEntity> redisTemplate;
	
	private static PathMatcher matcher = new AntPathMatcher();

	@Override
	public Object run() {
		// user access authority check filter
		RequestContext ctx = RequestContext.getCurrentContext(); 
		HttpServletRequest request = ctx.getRequest();
		String user_name = ctx.get("username").toString();
		
		// authority check
		// 从redis获取用户可访问的api路径进行权限验证
		UserRedisEntity entity = redisTemplate.boundValueOps(user_name).get();
		List<String> urlList = entity.getUrlList();
		boolean url = false;
		for (String path : urlList) {
			if (matcher.match(path, request.getRequestURI())) {
				ctx.setSendZuulResponse(true);// 对该请求进行路由
				// ctx.setResponseStatusCode(200);
				ctx.addZuulRequestHeader("username", user_name);
				ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
				ctx.set("username", user_name);
				url = true;
				break;
			}
		}
		if(!url){
			ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
			ctx.setResponseStatusCode(403);// 返回错误码
			ctx.setResponseBody("{\"result\":\"no permission to access url!\"}");// 返回错误内容
			ctx.set("isSuccess", false);
		}
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		RequestContext ctx = RequestContext.getCurrentContext();
		
		boolean ignoreFilter = (boolean) ctx.get("ignoreFilter");
		
		if(ignoreFilter){
			return false;
		}
		
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
