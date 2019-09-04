package com.ra.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.model.BlogService;
import com.blog.model.BlogVO;
import com.blre.model.BlreService;
import com.blre.model.BlreVO;
import com.ra.model.RaService;
import com.ra.model.RaVO;
import com.tools.tools;

public class repBlogServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		
		if ("insert_report".equals(action)) { // 來自addEmp.jsp的請求

			HttpSession session = req.getSession();
			String mem_no = (String) session.getAttribute("mem_no");

			List<String> errorMsgsRebl = new LinkedList<String>();
			req.setAttribute("errorMsgsRebl", errorMsgsRebl);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String bl_no = req.getParameter("bl_no");
				String ra_res =req.getParameter("ra_res").trim();
				
				if (ra_res.equals("")) {
					errorMsgsRebl.add("檢舉內容請勿空白");
				}
				
				/*************************** 2.開始get資料 *****************************************/
				

				
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.findByPrimaryKey(bl_no);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgsRebl.isEmpty()) {
					req.removeAttribute("blogVO");
					req.setAttribute("blogVO", blogVO);


					req.removeAttribute("display");
					req.setAttribute("display", "blog_display_complete");

					req.removeAttribute("display");
					req.setAttribute("display", "blog_display_complete");

					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/blog/blog.jsp");
					failureView.forward(req, res);
					return;
				}

				RaService raSvc = new RaService();
				raSvc.addRa(bl_no, ra_res, "待審核");

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/

				req.removeAttribute("blogVO");
				req.setAttribute("blogVO", blogVO);


				req.removeAttribute("display");
				req.setAttribute("display", "blog_display_complete");

				String url = "/easyfood/front-end/class/blog/blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgsRebl.add(e.getMessage());

				req.removeAttribute("display");
				req.setAttribute("display", "blog_display_complete");
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/blog/blog.jsp");
				failureView.forward(req, res);
			}
		}

	
	}

}