package com.blre.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.model.BlogService;
import com.blog.model.BlogVO;
import com.blre.model.BlreService;
import com.blre.model.BlreVO;
import com.mem.model.MemVO;
import com.tools.replaceSQLorHTMLtoCheck;
import com.tools.tools;

public class BlreServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");

		if ("leave_message".equals(action)) { // 來自addEmp.jsp的請求

			HttpSession session = req.getSession();
			MemVO memVO = (MemVO) session.getAttribute("memVO");
            String mem_no=memVO.getMem_no();

			List<String> errorMsgsBlre = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsBlre", errorMsgsBlre);

			try {

				/*********************** 接收請求參數 &查資料 *************************/

				String bl_no = req.getParameter("bl_no");
				String blre_con = req.getParameter("blre_con").trim();
				blre_con =replaceSQLorHTMLtoCheck.check(blre_con);
				
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.findByPrimaryKey(bl_no);

				if (blre_con.equals("")) {
					errorMsgsBlre.add("內容請勿空白");
				}

				BlreVO blreVO = new BlreVO();
				blreVO.setBlre_con(blre_con);
				blreVO.setBl_no(bl_no);
				blreVO.setMem_no(mem_no);
				blreVO.setBlre_date(tools.nowTimestamp());
				// blreVO1.setBlre_date(tools.strToTimestamp("2014-09-23
				// 21:34:12"));

				// Send the use back to the form, if there were errors
				if (!errorMsgsBlre.isEmpty()) {
					req.removeAttribute("blogVO");
					req.setAttribute("blogVO", blogVO);

					req.setAttribute("blreVO", blreVO); // 含有輸入格式錯誤的empVO物件,也存入req

					req.removeAttribute("default_tab");

					req.removeAttribute("display");
					req.setAttribute("display", "display");

					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				BlreService blreSvc1 = new BlreService();
				blreVO = blreSvc1.addBlre(blre_con, bl_no, mem_no);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				req.removeAttribute("blogVO");
				req.setAttribute("blogVO", blogVO);

				req.setAttribute("blreVO", blreVO); // 含有輸入格式錯誤的empVO物件,也存入req

				req.removeAttribute("default_tab");

				req.removeAttribute("display");
				req.setAttribute("display", "display");

				String url = "/easyfood/front-end/class/member/member_blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgsBlre.add(e.getMessage());
				req.removeAttribute("default_tab");
				req.setAttribute("default_tab", "add");
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("res_delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String blre_no = req.getParameter("blre_no");
				String bl_no = req.getParameter("bl_no");
				String delete_style=req.getParameter("delete_style");

				/*************************** 2.開始刪除資料 ***************************************/
				BlreService blreSvc = new BlreService();
				blreSvc.deleteBlre(blre_no);
				BlreVO blreVO = blreSvc.findByPrimaryKey(blre_no);
				
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.findByPrimaryKey(bl_no);
				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				
				if ("delete_res_member".equals(delete_style)) {
					
					req.removeAttribute("blogVO");
					req.setAttribute("blogVO", blogVO);
					
					req.removeAttribute("blreVO");
					req.setAttribute("blreVO", blreVO);
					
					req.removeAttribute("default_tab");
					req.removeAttribute("display");
					req.setAttribute("display", "display");
					
					String url = "/easyfood/front-end/class/member/member_blog.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
				}else if("delete_res_blog".equals(delete_style)){
					
					req.removeAttribute("blogVO");
					req.setAttribute("blogVO", blogVO);
					
					req.removeAttribute("blreVO");
					req.setAttribute("blreVO", blreVO);
					
					req.removeAttribute("display");
					req.setAttribute("display", "blog_display_complete");
					
					String url = "/easyfood/front-end/class/blog/blog.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
			    }else{
			    	
					req.removeAttribute("default_tab");
					req.setAttribute("default_tab", "res");

					String url = "/easyfood/front-end/class/member/member_blog.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
			    }
				
				

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				req.removeAttribute("default_tab");
				req.setAttribute("default_tab", "res");
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("display_res_update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgsRes = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsRes", errorMsgsRes);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String blre_no = req.getParameter("blre_no");
				
				String res_style = req.getParameter("res_style");

				/*************************** 2.開始查詢資料 ****************************************/
				BlreService blreSvc = new BlreService();
				BlreVO blreVO = blreSvc.findByPrimaryKey(blre_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				String url="";
				req.removeAttribute("blreVO");
				req.setAttribute("blreVO", blreVO); // 資料庫取出的empVO物件,存入req
				
				if("other_res".equals(res_style)){
					
					req.removeAttribute("display");
					req.setAttribute("display", "blog_display_update");

					url ="/easyfood/front-end/class/blog/blog.jsp";			
					
				}else{

					req.removeAttribute("default_tab");

					req.removeAttribute("display");
					req.setAttribute("display", "res_update_display");

					url = "/easyfood/front-end/class/member/member_blog.jsp";
					
				}
								
				
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgsRes.add("無法取得要修改的資料:" + e.getMessage());
				req.removeAttribute("default_tab");

				req.removeAttribute("display");
				req.setAttribute("display", "display");
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}

		if ("respond_Update".equals(action)) { // 來自update_emp_input.jsp的請求

			HttpSession session = req.getSession();
			MemVO memVO = (MemVO) session.getAttribute("memVO");
            String mem_no=memVO.getMem_no();

			List<String> errorMsgRes = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsRes", errorMsgRes);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String blre_no = req.getParameter("blre_no");
				String bl_no = req.getParameter("bl_no");
				String blre_con = req.getParameter("blre_con").trim();
				blre_con =replaceSQLorHTMLtoCheck.check(blre_con);
				
				if (blre_con.equals("")) {
					errorMsgRes.add("內容請勿空白");
				}

				BlreVO blreVO = new BlreVO();
				blreVO.setBlre_no(blre_no);
				blreVO.setBlre_con(blre_con);
				blreVO.setBl_no(bl_no);
				blreVO.setMem_no(mem_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgRes.isEmpty()) {
					req.removeAttribute("blreVO");
					req.setAttribute("blreVO", blreVO); // 含有輸入格式錯誤的empVO物件,也存入req
					
					req.removeAttribute("default_tab");

					req.removeAttribute("display");
					req.setAttribute("display", "display");

					RequestDispatcher failureView = req.getRequestDispatcher("m/easyfood/front-end/class/member/member_blog.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				BlreService blreSvc = new BlreService();
				blreVO = blreSvc.updateBlre(blre_no, blre_con, bl_no, mem_no);

				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.findByPrimaryKey(bl_no);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.removeAttribute("blreVO");
				req.setAttribute("blreVO", blreVO); // 含有輸入格式錯誤的empVO物件,也存入req

				req.removeAttribute("blogVO");
				req.setAttribute("blogVO", blogVO);

				req.removeAttribute("default_tab");

				req.removeAttribute("display");
				req.setAttribute("display", "display");

				String url = "/easyfood/front-end/class/member/member_blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgRes.add("修改資料失敗:" + e.getMessage());
				
				req.removeAttribute("default_tab");

				req.removeAttribute("display");
				req.setAttribute("display", "display");
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_blog.jsp");
				failureView.forward(req, res);
			}
		}
		
		//--------------navbar美食日記回應部份-----------------------
		
		if ("blog_display_res_update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgsRes = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsRes", errorMsgsRes);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String blre_no = req.getParameter("blre_no");

				/*************************** 2.開始查詢資料 ****************************************/
				BlreService blreSvc = new BlreService();
				BlreVO blreVO = blreSvc.findByPrimaryKey(blre_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.removeAttribute("blreVO");
				req.setAttribute("blreVO", blreVO);
				
				
				req.removeAttribute("display");
				req.setAttribute("display", "blog_display_update");

				String url = "/easyfood/front-end/class/blog/blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgsRes.add("無法取得要修改的資料:" + e.getMessage());
				
				
				req.removeAttribute("display");
				req.setAttribute("display", "blog_display_update");
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/blog/blog.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("blog_respond_Update".equals(action)) { // 來自update_emp_input.jsp的請求

			HttpSession session = req.getSession();
			MemVO memVO = (MemVO) session.getAttribute("memVO");
            String mem_no=memVO.getMem_no();

			List<String> errorMsgRes = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsRes", errorMsgRes);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String blre_no = req.getParameter("blre_no");
				String bl_no = req.getParameter("bl_no");
				String blre_con = req.getParameter("blre_con").trim();
				blre_con =replaceSQLorHTMLtoCheck.check(blre_con);
				
				if (blre_con.equals("")) {
					errorMsgRes.add("內容請勿空白");
				}

				BlreVO blreVO = new BlreVO();
				blreVO.setBlre_no(blre_no);
				blreVO.setBlre_con(blre_con);
				blreVO.setBl_no(bl_no);
				blreVO.setMem_no(mem_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgRes.isEmpty()) {
					req.removeAttribute("blreVO");
					req.setAttribute("blreVO", blreVO); // 含有輸入格式錯誤的empVO物件,也存入req

					req.removeAttribute("display");
					req.setAttribute("display", "blog_display_complete");

					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/blog/blog.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				BlreService blreSvc = new BlreService();
				blreVO = blreSvc.updateBlre(blre_no, blre_con, bl_no, mem_no);

				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.findByPrimaryKey(bl_no);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.removeAttribute("blreVO");
				req.setAttribute("blreVO", blreVO); // 含有輸入格式錯誤的empVO物件,也存入req

				req.removeAttribute("blogVO");
				req.setAttribute("blogVO", blogVO);


				req.removeAttribute("display");
				req.setAttribute("display", "blog_display_complete");

				session.setAttribute("navbar_select", "blog");
				
				String url = "/easyfood/front-end/class/blog/blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgRes.add("修改資料失敗:" + e.getMessage());


				req.removeAttribute("display");
				req.setAttribute("display", "blog_display_complete");
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/blog/blog.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("blog_leave_message".equals(action)) { // 來自addEmp.jsp的請求

			HttpSession session = req.getSession();
			MemVO memVO = (MemVO) session.getAttribute("memVO");
            String mem_no=memVO.getMem_no();

			List<String> errorMsgsBlre = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsBlre", errorMsgsBlre);

			try {

				/*********************** 接收請求參數 &查資料 *************************/

				String bl_no = req.getParameter("bl_no");
				String blre_con = req.getParameter("blre_con").trim();
				blre_con =replaceSQLorHTMLtoCheck.check(blre_con);
				
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.findByPrimaryKey(bl_no);

				if (blre_con.equals("")) {
					errorMsgsBlre.add("內容請勿空白");
				}

				BlreVO blreVO = new BlreVO();
				blreVO.setBlre_con(blre_con);
				blreVO.setBl_no(bl_no);
				blreVO.setMem_no(mem_no);
				blreVO.setBlre_date(tools.nowTimestamp());
				// blreVO1.setBlre_date(tools.strToTimestamp("2014-09-23
				// 21:34:12"));

				// Send the use back to the form, if there were errors
				if (!errorMsgsBlre.isEmpty()) {
					req.removeAttribute("blogVO");
					req.setAttribute("blogVO", blogVO);

					req.setAttribute("blreVO", blreVO); // 含有輸入格式錯誤的empVO物件,也存入req

					req.removeAttribute("default_tab");

					req.removeAttribute("display");
					req.setAttribute("display", "blog_display_complete");

					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/blog/blog.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				BlreService blreSvc1 = new BlreService();
				blreVO = blreSvc1.addBlre(blre_con, bl_no, mem_no);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				req.removeAttribute("blogVO");
				req.setAttribute("blogVO", blogVO);

				req.setAttribute("blreVO", blreVO); // 含有輸入格式錯誤的empVO物件,也存入req



				req.removeAttribute("display");
				req.setAttribute("display", "blog_display_complete");

				String url = "/easyfood/front-end/class/blog/blog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgsBlre.add(e.getMessage());
				req.removeAttribute("display");
				req.setAttribute("display", "blog_display_complete");
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/blog/blog.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
