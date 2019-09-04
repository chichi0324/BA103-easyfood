package com.rep.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rep.model.*;

/**
 * Servlet implementation class RepServlet
 */
@WebServlet("/RepServlet")
public class front_RepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("addRep".equals(action)) { // 來自registerMem.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String rep_res = req.getParameter("rep_res").trim();
				if (rep_res == null || (rep_res.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉內容");
				}				
				
				String ord_no = req.getParameter("ord_no").trim();
				
				RepVO repVO = new RepVO();
				repVO.setRep_res(rep_res);;
				repVO.setOrd_no(ord_no);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/report.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RepService repSvc = new RepService();
				repVO = repSvc.addRep(rep_res, ord_no);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				// req.setAttribute("memVO", memVO);
				// 將memVO放進session
				errorMsgs.add("檢舉已送出");
				String url = "/easyfood/front-end/class/member/report.jsp";				
				req.setAttribute("repVO", repVO);
				req.setAttribute("ord_no", ord_no);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp				
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getRep_For_display".equals(action)) {
			
			String ord_no = req.getParameter("ord_no");
			String rep_no = null;
			RepService repSvc = new RepService();
			List<RepVO> replist = repSvc.getAll();
			for(int i = 0; i < replist.size(); i++){
				RepVO arepVO = replist.get(i);
				if(arepVO.getOrd_no().equals(ord_no)){
					rep_no = arepVO.getRep_no();
				}
			}
			RepVO repVO = repSvc.getOneRep(rep_no);
			
			req.setAttribute("repVO", repVO);
			String url = "/easyfood/front-end/class/member/report.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
			
		}
	}

}
