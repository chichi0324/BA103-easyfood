package com.mem.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class frontIndexServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
        if ("navbar".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 檢查輸入資料是否空白*************************/
            String title=req.getParameter("title");
            String url ="/easyfood/front-end/index.jsp";
            
            HttpSession session=req.getSession();
            
            if("search".equals(title)){
            	session.setAttribute("navbar_select", "search");
            	url="/easyfood/front-end/class/search/search.jsp";
            }else if("blog".equals(title)){
            	session.setAttribute("navbar_select", "blog");
            	url="/easyfood/front-end/class/blog/blog.jsp";
            }else if("rank".equals(title)){
            	session.setAttribute("navbar_select", "rank");
            	url="/easyfood/front-end/class/rank/rank.jsp";
            }else if("shoppingCart".equals(title)){
            	session.setAttribute("navbar_select", "shoppingCart");
            	url="/easyfood/front-end/class/shoppingCart/shoppingCart.jsp";
            }else if("member".equals(title)){
            	session.setAttribute("navbar_select", "member");
            	url="/easyfood/front-end/class/member/member.jsp";
            }else if("index".equals(title)){
            	session.removeAttribute("navbar_select");
            	url="/easyfood/front-end/index.jsp";
            }else if("member_blog".equals(title)){
            	session.setAttribute("navbar_select", "member");
            	url="/easyfood/front-end/class/member/member_blog.jsp";
            }
            

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/front-end/index.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
