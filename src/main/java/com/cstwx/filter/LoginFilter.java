package com.cstwx.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest=(HttpServletRequest)request;  
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		//如果session不存在 则跳转到登陆页面
		if(session.getAttribute("id")==null){
			httpServletResponse.setContentType("text/html;charset=utf-8");
			httpServletResponse.setHeader("Cache-Control", "no-store"); //HTTP1.1
			httpServletResponse.setHeader("Pragma", "no-cache"); //HTTP1.0
			httpServletResponse.setDateHeader("Expires", 0); //prevents catching at proxy server
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("isLogin", false);
			PrintWriter out = response.getWriter();
			out.println(jsonObject.toString());
			out.flush();
			out.close();
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
