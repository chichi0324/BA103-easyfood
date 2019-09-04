package com.store.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.store.model.StrService;
import com.store.model.StrVO;


public class StoreFilter implements Filter {

	private FilterConfig config;
	
	public void init(FilterConfig config) throws ServletException {
		
		this.config = config;
	}
	
	public void destroy() {
		
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		Object str_no = session.getAttribute("str_no");


		
		if(str_no == null) {
			
			session.setAttribute("lacation", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/easyfood/front-end/class/store/str_login.jsp");
			return;
			
		} else {
			
			chain.doFilter(request, response);
			
		}
		
		
	}

}
