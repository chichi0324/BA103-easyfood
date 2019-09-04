package com.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FilterLogin implements Filter {

	private FilterConfig config;

	@Override
	public void init(FilterConfig config) {
		this.config = config;
	}

	@Override
	public void destroy() {
		config = null;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object memVO = session.getAttribute("memVO");
		//ystem.out.println("Filter memVO:"+memVO);
		if (memVO == null) {
			session.setAttribute("front_location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/easyfood/front-end/login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

}
