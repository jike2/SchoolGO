package com.hwua.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import jdk.nashorn.internal.runtime.JSONFunctions;

public class Myfilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTTIONS,DELETE");
		response.setHeader("Access-Control-Allow-Age", "3600");
		response.setHeader("Access-Control-Allow-Header", "x-request-with");
		response.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie
		
		
		String userid = request.getHeader("userid");
		if(userid !=null && userid != "") {
			filter.doFilter(req, res);
		}else {
			PrintWriter writer = response.getWriter();
			Map<String, String> map = new HashMap<String, String>();
			map.put("flag", "false");
			writer.write(JSON.toJSONString(map));
		}
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
