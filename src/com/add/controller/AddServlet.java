package com.add.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.add.model.AddService;
import com.add.model.AddVO;
import com.cartitem.model.CartItemVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("updateAdd".equals(action)) { // 來自registerMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String add_adds = req.getParameter("add_adds").trim();
				if (add_adds == null || (add_adds.trim()).length() == 0) {
					errorMsgs.add("地址不能空白");
				}
				Integer index = Integer.parseInt(req.getParameter("index"));
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				String mem_no = memVO.getMem_no();
				AddVO addVO = new AddVO();
				addVO.setMem_no(mem_no);
				AddService addSvc = new AddService();
				List<AddVO> addlist = addSvc.getAll();
				for(int i = 0; i < addlist.size(); i++){
					AddVO aAddVO  = addlist.get(i);
					if(i == index){
						aAddVO.setAdd_adds(add_adds);
					}
				}
				/*************************** 2.開始新增資料 ***************************************/

				addVO = addSvc.addAdd(mem_no, add_adds);
				//包裝資料回去用

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/

				String url = "/easyfood/front-end/class/member/member_information.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_information.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("addAdd".equals(action)) { // 來自registerMem.jsp的請求

			List<String> errorMsgs1 = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs1", errorMsgs1);

			try {
				/***********************
				 * * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String add_adds = req.getParameter("add_adds").trim();
				if (add_adds == null || (add_adds.trim()).length() == 0) {
					errorMsgs1.add("地址不能空白");
				}
				HttpSession session = req.getSession();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				String mem_no = memVO.getMem_no();
				AddVO addVO = new AddVO();
				addVO.setAdd_adds(add_adds);
				addVO.setMem_no(mem_no);
				if (!errorMsgs1.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_information.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/

				AddService addSvc = new AddService();
				addVO = addSvc.addAdd(mem_no, add_adds);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/

				String url = "/easyfood/front-end/class/member/member_information.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs1.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_information.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
