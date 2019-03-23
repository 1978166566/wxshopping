package com.cssl.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.plugin.Intercepts;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 * @author tym
 *
 */
public class MyInterceptor implements HandlerInterceptor {
	
	//请求到达控制器之前
	//chain.doFilter(request,response)
	//请求完成之后

	/**
	  * 请求到达控制器之前
	  * 返回true表示继续传递请求
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle:"+handler.toString());
		return true;
	}

	/**
	  * 请求完成之后
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle:");		
	}

	/**
	  * 视图渲染完毕后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		System.out.println("afterCompletion...");
	}

	
	
}
