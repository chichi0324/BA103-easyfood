package com.ord.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.ordit.model.OrditService;
import com.ordit.model.OrditVO;
import com.store.model.StrService;
import com.store.model.StrVO;


public class OrdServlet2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String str_no = session.getAttribute("str_no").toString();
		System.out.println(str_no);
		String action = request.getParameter("action");
		
		if("GET_ORDERS".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String ord_no = request.getParameter("ord_no");
			String ord_date = request.getParameter("ord_date");
//			System.out.println(ord_no.length());
//			System.out.println(ord_date);
			
			if(ord_no.length() == 0) {
				
				OrdService ordSvc = new OrdService();
				List<OrdVO> ordList = new LinkedList<OrdVO>();
				String url = "/easyfood/front-end/class/store/str_orderView.jsp";
		
				if(ord_date.equals("所有訂單")) {
					
					ordList = ordSvc.getAllByStr(str_no);
					request.setAttribute("ordList", ordList);
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					
				} else if(ord_date.equals("本日訂單")) {
					
					ordList = ordSvc.getIntervalByStr(str_no, "DD");
					request.setAttribute("ordList", ordList);
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
//					System.out.println("DD");
					
				} else if(ord_date.equals("本週訂單")) {
					
					ordList = ordSvc.getIntervalByStr(str_no, "DAY");
					request.setAttribute("ordList", ordList);
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
//					System.out.println("WEEK");
					
				} else if(ord_date.equals("本月訂單")) {
					
					ordList = ordSvc.getIntervalByStr(str_no, "MONTH");
					request.setAttribute("ordList", ordList);
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
//					System.out.println("MONTH");
										
				} else if(ord_date.equals("年度訂單")) {
					
					ordList = ordSvc.getIntervalByStr(str_no, "YEAR");
					request.setAttribute("ordList", ordList);
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
//					System.out.println("YEAR");
					
				}
					
			} else {
				
				OrdService ordSvc = new OrdService();
				
				if(!ord_no.startsWith("ORD_")) {
					errorMsgs.add("訂單號碼無法辨識");
				}
				
				if(ord_no.length() != 8) {
					errorMsgs.add("訂單格式輸入錯誤");
				}
				//-----------------------------------------------
				
				OrdVO vo = ordSvc.getOneByStr(ord_no, str_no);
				if(vo == null) {
					errorMsgs.add("無效訂單號碼");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_orderView.jsp");
					failureView.forward(request, response);
					return;
				}
				
				OrdVO ordVO = ordSvc.getOneOrd(ord_no);
				List<OrdVO> ordList = new LinkedList<OrdVO>();
				ordList.add(ordVO);
				
				request.setAttribute("ordList", ordList);
				
				String url = "/easyfood/front-end/class/store/str_orderView.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			}
			
		}
		
		if("GET_ONE".equals(action)) {
			
			String ord_no = request.getParameter("ord_no");
			
			OrdService ordSvc = new OrdService();
			OrditService orditSvc = new OrditService();
			
			OrdVO ordVO = ordSvc.getOneByStr(ord_no, str_no);
			List<OrditVO> orditList = orditSvc.getAllyStr(ord_no);
			
			request.setAttribute("ordVO", ordVO);
			request.setAttribute("orditList", orditList);
			
			String ord_stat = ordVO.getOrd_stat();
			System.out.println(ord_stat);
			
			if("未確認".equals(ord_stat)) {
		
				String url = "/easyfood/front-end/class/store/str_orderOne.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} else if("已確認".equals(ord_stat)) {
				
				String url = "/easyfood/front-end/class/store/str_orderShip.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} else if("已取消".equals(ord_stat)) {
				
				String url = "/easyfood/front-end/class/store/str_orderShip.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} else if ("已出貨".equals(ord_stat)) {
				
				String url = "/easyfood/front-end/class/store/str_orderCash.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} else if ("已付款".equals(ord_stat) || "未取餐".equals(ord_stat)) {
				
				String url = "/easyfood/front-end/class/store/str_orderCash.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			}
				
		}
		
		if("MAKE_ORDERS".equals(action)) {
			
			String status = request.getParameter("status");
			String ord_no = request.getParameter("ord_no");
			OrdService ordSvc = new OrdService();
			OrdVO ordVO = null;
//			StrService strSvc = new StrService();
//			StrVO strVO = null;
				
			if(status.equals("confirm")) {

				ordSvc.updateStatus(ord_no, "已確認");
				ordVO = ordSvc.getOneByStr(ord_no, str_no);
				OrditService orditSvc = new OrditService();
				List<OrditVO> orditList = orditSvc.getAllyStr(ord_no);
				
				request.setAttribute("ordVO", ordVO);
				request.setAttribute("orditList", orditList);
				
				String url = "/easyfood/front-end/class/store/str_orderShip.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
//				SimpleDateFormat myDate = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
//				
//				String startDate = myDate.format(ordVO.getOrd_date());
//				String confirmDate = myDate.format(new Date());
//				strVO = strSvc.getOneStr(str_no);
//				System.out.println(strVO.getStr_pre()*60*1000);
//				String etdShip = myDate.format(System.currentTimeMillis() + strVO.getStr_pre()*60*1000);
				
//				System.out.println(startDate);
//				System.out.println(confirmDate);
//				System.out.println(etdShip);
				
				
//				request.setAttribute("startDate", startDate);
//				request.setAttribute("confirmDate", confirmDate);
//				request.setAttribute("etdShip", etdShip);
				
				
			}  else if(status.equals("cancel")){
				
				ordSvc.updateStatus(ord_no, "已取消");
				ordVO = ordSvc.getOneByStr(ord_no, str_no);
				OrditService orditSvc = new OrditService();
				List<OrditVO> orditList = orditSvc.getAllyStr(ord_no);
				
				request.setAttribute("ordVO", ordVO);
				request.setAttribute("orditList", orditList);
				
				String url = "/easyfood/front-end/class/store/str_orderOne.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
//				SimpleDateFormat myDate = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
//				
//				String startDate = myDate.format(ordVO.getOrd_date());
//				String confirmDate = myDate.format(new Date());
//				strVO = strSvc.getOneStr(str_no);
//				System.out.println(strVO.getStr_pre()*60*1000);
//				String etdShip = myDate.format(System.currentTimeMillis() + strVO.getStr_pre()*60*1000);
				
//				request.setAttribute("startDate", startDate);
//				request.setAttribute("confirmDate", confirmDate);
//				request.setAttribute("etdShip", etdShip);
				
				
			}
		}
		
		if("SHIP_ORDERS".equals(action)) {
			
			String ord_no = request.getParameter("ord_no");
			String ord_stat = request.getParameter("ord_stat");
			
//			System.out.println(ord_no);
//			System.out.println(ord_stat);
			
			OrdService ordSvc = new OrdService();
			ordSvc.updateStatus(ord_no, "已出貨");
			OrdVO ordVO = ordSvc.getOneByStr(ord_no, str_no);
			
			OrditService orditSvc = new OrditService();
			List<OrditVO> orditList = orditSvc.getAllyStr(ord_no);
			
			request.setAttribute("ordVO", ordVO);
			request.setAttribute("orditList", orditList);
			String url = "/easyfood/front-end/class/store/str_orderCash.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
				
		} 
		
		if("CASH_CHECK".equals(action)) {
			
			String ord_no = request.getParameter("ord_no");
			String ord_status = request.getParameter("ord_status");
//			System.out.println(ord_status);
			
			OrdService ordSvc = new OrdService();
			ordSvc.updateStatus(ord_no, ord_status);
			OrdVO ordVO = ordSvc.getOneByStr(ord_no, str_no);
			
			OrditService orditSvc = new OrditService();
			List<OrditVO> orditList = orditSvc.getAllyStr(ord_no);
			
			if("未取餐".equals(ord_status)) {
				
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(ordVO.getMem_no());
				Integer count = memVO.getMem_vio();
				count += 1;
				memVO.setMem_vio(count);
				memSvc.updateVio(memVO.getMem_vio(), memVO.getMem_no());
			}
			
			request.setAttribute("ordVO", ordVO);
			request.setAttribute("orditList", orditList);
			String url = "/easyfood/front-end/class/store/str_orderCash.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
		}
		
		
	}

}
