package com.hwua.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyIntercepor implements HandlerInterceptor{
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("拦截器拦截之后");


	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("拦截器拦截之前");

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		System.out.println("拦截请求: " + request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "0");
		response.setHeader("Access-Control-Allow-Headers",
				"Auhthorization, Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,userid");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("XDomainRequestAllowed", "1");

		//获取URI 
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		if(requestURI.contains("admin")) {
			if(requestURI.contains("login")) {
				return true;
			}else {
				String username = request.getHeader("userid");
				Enumeration<String> headerNames = request.getHeaderNames();
				while(headerNames.hasMoreElements()) {
					String string = headerNames.nextElement();
					System.out.println(string);
				}
				if(null==username){ 
					return false;
				}
			}
			
		}
		if(!requestURI.contains(".do")){
			String username = request.getHeader("userId");
			System.out.println("username="+username);
			if(null==username){ 
				return false;
			} 
		}
		System.out.println("通过了");
		return true;
	}



}
