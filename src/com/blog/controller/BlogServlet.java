package com.blog.controller;

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
import com.mem.model.MemVO;
import com.tools.tools;

public class BlogServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			HttpSession session = req.getSession();
			MemVO memVO = (MemVO) session.getAttribute("memVO");
            String mem_no=memVO.getMem_no();
            
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String title = req.getParameter("title").trim();
				String article_store =req.getParameter("article_store").trim();
				String article_content = req.getParameter("article_content").trim();
				java.sql.Timestamp time = tools.nowTimestamp();

				if (title.equals("")) {
					errorMsgs.add("標題請勿空白");
				}

				if (article_content.equals("")) {
					errorMsgs.add("內容請勿空白");
				}
				
				if (article_store.equals("請選擇")) {
					errorMsgs.add("請選擇店家");
				}
				

				BlogVO blogVO = new BlogVO();
				blogVO.setBl_name(title);
				blogVO.setBl_con(article_content);
				blogVO.setBl_date(time);
				// blogVO2.setBl_date(tools.strToTimestamp("2016-09-15
				// 09:50:03"));
				blogVO.setMem_no(mem_no);
				blogVO.setStr_no(article_store);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO1", blogVO); // 含有輸入格式錯誤的empVO物件,也存入req

					req.removeAttribute("default_tab");
					req.setAttribute("default_tab", "add");

					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				BlogService blogSvc1 = new BlogService();
				blogVO = blogSvc1.addBlog(title, article_content, mem_no, article_store);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				// req.setAttribute("blogVO1", blogVO);

				req.removeAttribute("default_tab");
				// req.setAttribute("default_tab", "add");
				//
				// req.removeAttribute("display");
				// req.setAttribute("display", "add_display");
				String url = "/easyfood/front-end/class/member/member_blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				req.removeAttribute("default_tab");
				req.setAttribute("default_tab", "add");
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert_con".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				req.removeAttribute("blogVO");
				req.removeAttribute("default_tab");
				req.setAttribute("default_tab", "add");
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/front-end/class/member/member_blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("member_blog".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String bl_no = req.getParameter("bl_no");
				BlogService blogSvc = new BlogService();
				BlogVO blobVO = blogSvc.findByPrimaryKey(bl_no);

				req.removeAttribute("blogVO");
				req.removeAttribute("default_tab");
				req.removeAttribute("display");
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/front-end/class/member/member_blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("display_complete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String bl_no = req.getParameter("bl_no");
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.findByPrimaryKey(bl_no);

				req.removeAttribute("blogVO");
				req.setAttribute("blogVO", blogVO);

				req.removeAttribute("default_tab");

				req.removeAttribute("display");
				req.setAttribute("display", "display");
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				HttpSession session=req.getSession();
				session.setAttribute("navbar_select", "member");
				
				String url = "/easyfood/front-end/class/member/member_blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String bl_no = req.getParameter("bl_no");

				/*************************** 2.開始刪除資料 ***************************************/
				BlogService blogSvc = new BlogService();
				blogSvc.deleteBlog(bl_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/

				req.removeAttribute("default_tab");
				req.setAttribute("default_tab", "man");

				String url = "/easyfood/front-end/class/member/member_blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("display_update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String bl_no = req.getParameter("bl_no");

				/*************************** 2.開始查詢資料 ****************************************/
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.findByPrimaryKey(bl_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.removeAttribute("blogVO");
				req.setAttribute("blogVO", blogVO); // 資料庫取出的empVO物件,存入req

				req.removeAttribute("default_tab");

				req.removeAttribute("display");
				req.setAttribute("display", "update_display");

				HttpSession session=req.getSession();
				session.setAttribute("navbar_select", "member");
				
				String url = "/easyfood/front-end/class/member/member_blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			HttpSession session = req.getSession();
			MemVO memVO = (MemVO) session.getAttribute("memVO");
            String mem_no=memVO.getMem_no();

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs1", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String bl_no = req.getParameter("bl_no");
				String bl_name = req.getParameter("bl_name").trim();
				String article_store =req.getParameter("article_store").trim();
				String bl_con = req.getParameter("bl_con").trim();
				java.sql.Timestamp bl_date = tools.nowTimestamp();

				if (bl_name.equals("")) {
					errorMsgs.add("標題請勿空白");
				}

				if (bl_con.equals("")) {
					errorMsgs.add("內容請勿空白");
				}
				
				if (article_store.equals("請選擇")) {
					errorMsgs.add("請選擇店家");
				}

				BlogVO blogVO = new BlogVO();
				blogVO.setBl_name(bl_name);
				blogVO.setBl_con(bl_con);
				blogVO.setBl_date(bl_date);
				// blogVO2.setBl_date(tools.strToTimestamp("2016-09-15
				// 09:50:03"));
				blogVO.setMem_no(mem_no);
				blogVO.setStr_no(article_store);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的empVO物件,也存入req

					req.removeAttribute("default_tab");

					req.removeAttribute("display");
					req.setAttribute("display", "update_display");

					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				BlogService blogSvc = new BlogService();
				blogVO = blogSvc.updateBlog(bl_no, bl_name, bl_con, mem_no, article_store);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("blogVO", blogVO);

				req.removeAttribute("default_tab");

				req.removeAttribute("display");
				req.setAttribute("display", "display");
				
				session.setAttribute("navbar_select", "member");
				
				String url = "/easyfood/front-end/class/member/member_blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				req.removeAttribute("default_tab");
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}

		// ------------------navbar所有文章顯示----------------------------------

		if ("blog_display_all".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String bl_no = req.getParameter("bl_no");
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.findByPrimaryKey(bl_no);

				req.removeAttribute("blogVO");
				req.setAttribute("blogVO", blogVO);

				req.removeAttribute("display");
				req.setAttribute("display", "blog_display_complete");
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				HttpSession session=req.getSession();
				session.setAttribute("navbar_select", "blog");
				
				String url = "/easyfood/front-end/class/blog/blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/blog/blog.jsp");
				failureView.forward(req, res);
			}
		}

		// ------------------美食日記搜尋----------------------------------

		if ("select_diary".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String year = req.getParameter("year");
				int month = Integer.parseInt(req.getParameter("month"));
				String monthStr = "";
				if (month < 10) {
					monthStr = "0" + month;
				} else {
					monthStr = month + "";
				}

				Timestamp timestamp1 = tools.strToTimestamp(year + "-" + monthStr + "-01 00:00:00");
				Timestamp timestamp2 = tools.strToTimestamp(year + "-" + monthStr + "-29 23:59:59");

				BlogService blogSvc = new BlogService();
				List<BlogVO> blogList = blogSvc.findByTime(timestamp1, timestamp2);

				req.setAttribute("year", year);
				req.setAttribute("month", monthStr);

				req.setAttribute("blogList", blogList);

				req.removeAttribute("select");
				req.setAttribute("select", "selectTime");

				req.removeAttribute("display");
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/front-end/class/blog/blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/blog/blog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("select_diary_keyword".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String keyWord = req.getParameter("keyWord");
				// String keyWord=new
				// String(searchKeyword.getBytes("ISO-8859-1"),"UTF-8");

				BlogService blogSvc = new BlogService();
				List<BlogVO> blogList = blogSvc.findByKeyword(keyWord);

				req.setAttribute("keyWord", keyWord);

				req.removeAttribute("blogList");
				req.setAttribute("blogList", blogList);

				req.removeAttribute("select");
				req.setAttribute("select", "selectKeyWord");

				req.removeAttribute("display");
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/front-end/class/blog/blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/blog/blog.jsp");
				failureView.forward(req, res);
			}
		}

	}

}