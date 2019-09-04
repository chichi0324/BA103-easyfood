package com.dishclass.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dishclass.model.DclaService;
import com.dishclass.model.DclaVO;

public class DclaServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("ADD".equals(action)) {
			
			List<String> errorMsgs = new ArrayList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String dcla_name = request.getParameter("dcla_name").trim();
			System.out.println(dcla_name);
			
			if(dcla_name == null || dcla_name.length() == 0) {
				errorMsgs.add("請輸入菜色名稱");
			}
			
			DclaService dclaSvc = new DclaService();
			List<DclaVO> dclaList = dclaSvc.getAll();
			for(DclaVO dclaVO : dclaList) {
				String name = dclaVO.getDcla_name();
				if(name.equals(dcla_name)) {
					errorMsgs.add("菜色名稱不可重複");
				}
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/back-end/class/store/Dcla_add.jsp");
				failureView.forward(request, response);
				return;
			}
				
			DclaVO dclaVO =  dclaSvc.addDcla(dcla_name);
			System.out.println("update success");
			request.setAttribute("dclaVO", dclaVO);
			
			String url = "/easyfood/back-end/class/store/Dcla_all.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		
		if("UPDATE_ONE".equals(action)) {
			
			List<String> errorMsgs = new ArrayList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String dcla_no = request.getParameter("dcla_no");
			DclaService dclaSvc = new DclaService();
			DclaVO dclaVO = dclaSvc.getOneDcla(dcla_no);
			
			request.setAttribute("dclaVO", dclaVO);
			String url = "/easyfood/back-end/class/store/Dcla_update.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
		}
		
		if("UPDATE".equals(action)) {
			
			List<String> errorMsgs = new ArrayList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String dcla_no = request.getParameter("dcla_no");
			String dcla_name = request.getParameter("dcla_name");
			
			System.out.println("dcla_no = " + dcla_no);
			System.out.println("dcla_name = " + dcla_name);
			
			if(dcla_name == null || dcla_name.trim().length() == 0) {
				errorMsgs.add("請輸入菜色名稱");
			}
			
			DclaService dclaSvc = new DclaService();
			
			List<DclaVO> dclaList = dclaSvc.getAll();
			for(DclaVO dclaVO : dclaList) {
				String name = dclaVO.getDcla_name();
				if(name.equals(dcla_name)) {
					errorMsgs.add("菜色名稱重複 不得修改");
				}
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/back-end/class/store/Dcla_all.jsp");
				failureView.forward(request, response);
				return;
			}
			
			DclaVO dclaVO = dclaSvc.updateDcla(dcla_no, dcla_name);
			request.setAttribute("dclaVO", dclaVO);
			String url = "/easyfood/back-end/class/store/Dcla_one.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
		}
		
		
	}

}
