package com.storecategory.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import com.storecategory.model.StocaService;
import com.storecategory.model.StocaVO;
import com.stream.tool.ShareTool;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class StocaServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("image/jpg");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("ADD".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String stoca_name = request.getParameter("stoca_name").trim();
			String stoca_note = request.getParameter("stoca_note").trim();
			
//			System.out.println(stoca_name);
//			System.out.println(stoca_note);
			Part part = request.getPart("stoca_img");
			
			
			if(stoca_name.length() == 0) {
				errorMsgs.add("名稱不可空白");
			}
			if(stoca_note.length() == 0) {
				stoca_note = null;
			}
			if(part.getSize() == 0) {
				errorMsgs.add("請提供圖片");
			}
			
			StocaService stocaSvc = new StocaService();
			List<StocaVO> stocaList = stocaSvc.getAll();
			
			for(StocaVO stocaVO : stocaList) {
				if(stocaVO.getStoca_name().equals(stoca_name)) {
					errorMsgs.add("類別名稱不可重複");
				}
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/back-end/class/store/Stoca_add.jsp");
				failureView.forward(request, response);
				return;
			}
			
			byte[] stoca_img = ShareTool.sendPicture(part);
//			System.out.println(stoca_img.length);
			
			
			StocaVO stocaVO = stocaSvc.addStoca(stoca_name, stoca_img, stoca_note);
			System.out.println("update success");
			
			request.setAttribute("stocaVO", stocaVO);
			String url = "/easyfood/back-end/class/store/Stoca_all.jsp";
			RequestDispatcher reristerSuccess= request.getRequestDispatcher(url);
			reristerSuccess.forward(request, response);
		}
		
		
		
		if("UPDATE_ONE".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String stoca_no = request.getParameter("stoca_no");
				
				StocaService stocaSvc = new StocaService();
				StocaVO stocaVO = stocaSvc.getOneStoca(stoca_no);
				
				request.setAttribute("stocaVO", stocaVO);
				String url = "/easyfood/back-end/class/store/Stoca_update.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/back-end/class/store/Stoca_all.jsp");
				failureView.forward(request, response);
			}
			
		}
		
		if("UPDATE".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String stoca_no = request.getParameter("stoca_no");
			String stoca_name = request.getParameter("stoca_name");
			String stoca_note = request.getParameter("stoca_note").trim();
			Part part = request.getPart("stoca_img");
			
			System.out.println("stoca_no = " + stoca_no);
			System.out.println("stoca_name = " + stoca_name);
			System.out.println("Stoca_note = " + stoca_note);
			
			if(stoca_name == null || stoca_name.length() == 0 ) {
				errorMsgs.add("請提供修改資料");
			}
			if(stoca_note == null || stoca_note.length() == 0) {
				errorMsgs.add("請提供修改資料");
			}
			
			StocaService stocaSvc = new StocaService();
			
		
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/back-end/class/store/Stoca_all.jsp");
				failureView.forward(request, response);
				return;
			}
			
			byte[] stoca_img = ShareTool.sendPicture(part);
			System.out.println("length of stoca_img = " + stoca_img.length);
			
			
			StocaVO stocaVO = null;
			
			if(stoca_img.length == 0) {
				stocaVO = stocaSvc.updateNoimg(stoca_no, stoca_name, stoca_note);
				System.out.println("update info success");
				
			} else {
				stocaVO = stocaSvc.updateStoca(stoca_no, stoca_name, stoca_img, stoca_note);
				System.out.println("update success");
			}
			
			request.setAttribute("stocaVO", stocaVO);
			String url = "/easyfood/back-end/class/store/Stoca_one.jsp";
			RequestDispatcher reristerSuccess= request.getRequestDispatcher(url);
			reristerSuccess.forward(request, response);
		}
		
		
		
	}
	
		
	
	
	
	
}



