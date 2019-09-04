package com.adv.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.adv.model.AdvService;
import com.adv.model.AdvVO;
import com.store.model.StrService;
import com.tools.tools;

@MultipartConfig
public class AdvServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		System.out.println("有進入控制器");
		
	
			if("In_For_Adv".equals(action)){
			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				
				
				
				java.sql.Date adv_str = java.sql.Date.valueOf(req.getParameter("adv_str").trim());
				System.out.println(adv_str);
				java.sql.Date adv_end = java.sql.Date.valueOf(req.getParameter("adv_end").trim());
				System.out.println(adv_end);
				String str_no = (String) session.getAttribute("str_no");
				System.out.println(str_no );
				String adv_sta = "待審核";
				System.out.println(adv_sta);
				Part adv_img=req.getPart("adv_img");
				System.out.println(adv_img);
				byte[] adv_img_byte = tools.getPictureByteArraypart(adv_img);
				
				
				
				
				
				System.out.println(adv_img_byte);
				
				AdvVO strVO = new AdvVO();
				
				strVO.setAdv_txt(adv_img_byte);
				strVO.setStr_no(str_no);
				strVO.setAdv_str(adv_str);
				strVO.setAdv_end(adv_end);
				strVO.setAdv_sta(adv_sta);
				
				if(adv_str.equals(adv_end)){
					errorMsgs.add("開始日跟結束日相同");					
				}
							
				if (!errorMsgs.isEmpty()) {
					System.out.println(errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/store/str_advertise02.jsp");
					failureView.forward(req, res);
					return;
				}
				
				AdvService advSvc = new AdvService();
				System.out.println(errorMsgs);
				advSvc.addAdv(str_no, adv_sta, adv_str, adv_end, adv_img_byte);
				
				String url = "/easyfood/front-end/class/store/str_advertise01.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch (Exception e) {
				errorMsgs.add("無法取得圖片");
				System.out.println(errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/store/str_advertise02.jsp");
				failureView.forward(req, res);
			}
		}
	
	
		if("Update_For_adv".equals(action)){
			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{	
				
				String adv_no =	req.getParameter("adv_no");
				String adv_sta = req.getParameter("adv_sta");
				
				AdvVO strVO = new AdvVO();
				
				strVO.setAdv_no(adv_no);
				strVO.setAdv_sta(adv_sta);
				
				AdvService advSvc = new AdvService();
				
				advSvc.updateAdv_Sta(adv_no, adv_sta);
				
				String url = "/easyfood/back-end/class/advertising/advertising.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch (Exception e) {
				
			}
		}
	}
}
