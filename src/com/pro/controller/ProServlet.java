package com.pro.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.model.ProService;
import com.pro.model.ProVO;

public class ProServlet extends HttpServlet{
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
		String str_no = req.getParameter("str_no");
		System.out.print(str_no);
		
		
		if("Insert_For_Pro".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			ProService proSvc = new ProService(); 
			
			
			java.sql.Date pro_str  = null;
			java.sql.Date pro_end = null;
			Integer pro_mon = null;
			Double pro_dis = null;
			String dcla_no1 = null;
			String dcla_no2 = null;
			
			pro_str = java.sql.Date.valueOf(req.getParameter("datestr").trim());
			pro_end = java.sql.Date.valueOf(req.getParameter("dateend").trim());
			
			long startDate = pro_str.getTime();			
			long endDate = pro_end.getTime();
			boolean date_is_same=false;
			
			List<ProVO> list = new ArrayList<ProVO>();
			list = proSvc.getStrPro(str_no);
			
			
			
			for(ProVO listdate:list){
				
				Date old_str =listdate.getPro_str();
				Date old_end =listdate.getPro_end();
				
				long old_str_long = old_str.getTime();			
				long old_end_long = old_end.getTime();
				
				
				if(startDate>=old_str_long & startDate<=old_end_long){
					date_is_same =true;
					
				}
				
			}
			if(date_is_same){
				errorMsgs.add("這時段已經有優惠");		
				}
			
			
			System.out.println(startDate);
			System.out.println(endDate);
			
			
			if(pro_str .equals(pro_end) ){
			
				errorMsgs.add("開始結束日期不能相同");
			}
			
			if(endDate < startDate){
				errorMsgs.add("結束日期不能在開始日期前");
			}
			String pro_cat =req.getParameter("style"); 		
				
			if ("money".equals(pro_cat)){
				
				System.out.print(pro_cat);
				//pro_cat="總金額";
				
				
				
				
				try{
					pro_mon = new Integer(req.getParameter("condition").trim());
				
					}catch (NumberFormatException e){
						errorMsgs.add("請輸入條件金額");
				 
				}
				try{
					pro_dis = new Double(req.getParameter("discount").trim());
					
					pro_dis=pro_dis/100;
				} catch (NumberFormatException e){
					errorMsgs.add("請輸入折扣金額");
				}
				
				ProVO proVO = new ProVO();
				
				proVO.setPro_str(pro_str);
				proVO.setPro_end(pro_end);
				proVO.setStr_no(str_no);
				proVO.setPro_cat(pro_cat);
				proVO.setPro_mon(pro_mon);
				proVO.setPro_dis(pro_dis);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/easyfood/front-end/class/store/str_bonus_02.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				proVO =proSvc.addPro1(pro_str, pro_end, str_no, pro_cat, pro_mon, pro_dis);
				
				String url = "/easyfood/front-end/class/store/str_bonus_01.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				}else{
					//pro_cat="種類";
					dcla_no1=new String(req.getParameter("dcla_1").trim());
					dcla_no2=new String(req.getParameter("dcla_2").trim());
					System.out.println(dcla_no1);
					System.out.println(dcla_no2);
					if(dcla_no1.equals(dcla_no2)){
						errorMsgs.add("種類相同");
					}
					try{
						System.out.println(req.getParameter("discount2"));
						pro_dis = new Double(req.getParameter("discount2").trim());
						
						pro_dis=pro_dis/100;
					} catch (NumberFormatException e){
						errorMsgs.add("請輸入折扣");
					}	
					
					ProVO proVO = new ProVO();
					
					proVO.setPro_str(pro_str);
					proVO.setPro_end(pro_end);
					proVO.setStr_no(str_no);
					proVO.setPro_cat(pro_cat);
					proVO.setDcla_no1(dcla_no1);
					proVO.setDcla_no2(dcla_no2);
					proVO.setPro_dis(pro_dis);
					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("proVO", proVO); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("/easyfood/front-end/class/store/str_bonus_02.jsp");
						failureView.forward(req, res);
						return;
					}
					
					
					proVO =proSvc.addPro2(pro_str, pro_end, str_no, pro_cat,pro_dis, dcla_no1,dcla_no2 );
					
					String url = "/easyfood/front-end/class/store/str_bonus_01.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
					
				}
				
				}
				
			
	}

	
}