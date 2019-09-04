package com.dish.controller;

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

import com.dish.model.DishService;
import com.dish.model.DishVO;
import com.stream.tool.ShareTool;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class DishServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		
//		 -----------------------查詢菜單菜單開始----------------------
		if("GET_DISHES".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String str_no = session.getAttribute("str_no").toString();
//			System.out.println(str_no);
			
			String dish_no = request.getParameter("dish_no").trim();
			String dishClass = request.getParameter("dishClass");
			String dishStatus = request.getParameter("dishStatus");
			
//			System.out.println(dish_no);
//			System.out.println(dishClass);
//			System.out.println(dishStatus);
			
			if(dish_no.length() == 0) {
				
				DishService dishSvc = new DishService();
				String url = "/easyfood/front-end/class/store/str_dishView.jsp";
				
				if("所有種類".equals(dishClass) && "所有狀態".equals(dishStatus)) {
					
					List<DishVO> dishList = dishSvc.getStoreDish(str_no);
					request.setAttribute("dishList", dishList);
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					
				} else if("所有種類".equals(dishClass) && !("所有狀態".equals(dishStatus))) {
					
					List<DishVO> dishList_dc = dishSvc.getStoreDish(str_no);
					List<DishVO> dishList = new LinkedList<DishVO>();
					
					for(DishVO dishVO : dishList_dc) {
						if(dishVO.getDish_status().equals(dishStatus)) {
							dishList.add(dishVO);
						}
					}
					
					request.setAttribute("dishList", dishList);
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					
				} else if (!"所有種類".equals(dishClass) && "所有狀態".equals(dishStatus)) {
					
					List<DishVO> dclaList = dishSvc.getDclaDish(dishClass);
					List<DishVO> dishList = new LinkedList<DishVO>();
					
					for(DishVO dishVO : dclaList) {
						if(dishVO.getStr_no().equals(str_no)) {
							dishList.add(dishVO);
						}
					}
					
					request.setAttribute("dishList", dishList);
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					
				} else {
					
					List<DishVO> list = dishSvc.getDclaDish(dishClass);
					List<DishVO> dishList_str = new LinkedList<DishVO>();
					List<DishVO> dishList = new LinkedList<DishVO>();
					
					for(DishVO dishVO : list) {
						if(dishVO.getStr_no().equals(str_no)) {
							dishList_str.add(dishVO);
						}
					}
					
					for(DishVO dishVO : dishList_str) {
						if(dishVO.getDish_status().equals(dishStatus)) {
							dishList.add(dishVO);
						}
					}
					
					request.setAttribute("dishList", dishList);
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
						
				}
				
			} else {
				
				if(!dish_no.startsWith("DISH_")) {
					errorMsgs.add("餐點號碼無法辨識");
				}
				
				if(dish_no.length() != 9) {
					errorMsgs.add("餐點號碼格式輸入錯誤");
				}
				
				DishService dishSvc = new DishService();
				DishVO dishVO = dishSvc.getOneDish(dish_no);
				
				if(dishVO == null) {
					errorMsgs.add("餐點不存在");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishView.jsp");
					failureView.forward(request, response);
					return;
				}
				
				if(!dishVO.getStr_no().equals(str_no)) {
					errorMsgs.add("無效餐點號碼");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishView.jsp");
					failureView.forward(request, response);
					return;
				}
				
				List<DishVO> dishList = new LinkedList<DishVO>();
				dishList.add(dishVO);
				
				request.setAttribute("dishList", dishList);
				String url = "/easyfood/front-end/class/store/str_dishView.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			}
		}
		
//		 ------------------------新增菜單開始------------------------
		if("INSERT_DISHES".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String str_no = session.getAttribute("str_no").toString();
//			System.out.println(str_no);
			
			try {
				DishService dishSvc = new DishService();
				List<String> nameList = dishSvc.getDishNameByStore(str_no);
				
				String[] dcla_no = request.getParameterValues("dcla_no");
				String[] dish_name = request.getParameterValues("dish_name");
				String[] dish_note = request.getParameterValues("dish_note");
				String[] dish_price = request.getParameterValues("dish_price");
				
				Part part = null;		
				List<Part> imgList = new LinkedList<Part>();
				
				for(int i = 0 ; i < dish_name.length ; i++) {
					part = request.getPart("dish_pic"+i);
					imgList.add(part);
//					System.out.println(dcla_no[i]);
//					System.out.println(dish_name[i]);
//					System.out.println(dish_note[i]);
//					System.out.println(dish_price[i]);
				}
				
				if(dcla_no == null || dcla_no.equals("菜單分類")) {
					errorMsgs.add("請選擇菜單分類");
				}
				if(dish_name == null || dish_name.length == 0) {
					errorMsgs.add("請填入菜單名稱");
				}
				if(dish_note == null || dish_note.length == 0) {
					errorMsgs.add("請填入菜單介紹");
				}
				if(dish_price == null || dish_price.length == 0) {
					errorMsgs.add("請填入菜單價格");	
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishEdd.jsp");
					failureView.forward(request, response);
					return;
				}
				for(int i = 0 ; i < dish_name.length ; i++) {
					if(nameList.contains(dish_name[i])) {
						errorMsgs.add("菜單名稱不可重複");
					}
				}
				if(part.getSize() == 0) {
					errorMsgs.add("請提供菜單圖片");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishEdd.jsp");
					failureView.forward(request, response);
					return;
				}
				
				List<DishVO> list = new LinkedList<DishVO>();
				
				System.out.println(imgList.size());
				
				DishVO dishVO = null;
				for(int i = 0 ; i < dish_name.length ; i++) {
					
					dishVO = new DishVO();
					dishVO.setDish_name(dish_name[i]);
					dishVO.setDish_price(new Double(dish_price[i]));
					dishVO.setStr_no(str_no);
					dishVO.setDcla_no(dcla_no[i]);
					dishVO.setDish_note(dish_note[i]);
					dishVO.setDish_img(ShareTool.sendPicture(imgList.get(i)));
					list.add(dishVO);
					
				}
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("dishVO", dishVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishEdd.jsp");
					failureView.forward(request, response);
					return;	
				}
				
				for(DishVO vo : list) {
					dishSvc.addDish(vo.getDish_name(), vo.getDish_price(), vo.getDcla_no(), str_no, vo.getDish_img(), vo.getDish_note());
				}
				
				System.out.print("add success");
				request.setAttribute("dishList", list);
				
				String url = "/easyfood/front-end/class/store/str_dishView.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (NumberFormatException e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishEdd.jsp");
				failureView.forward(request, response);
				
			} catch (IllegalStateException e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishEdd.jsp");
				failureView.forward(request, response);
			}
			
		}

//		 ------------------------修改菜單開始------------------------
		if("UPDATE_ONE_DISHES".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String str_no = session.getAttribute("str_no").toString();
			System.out.println(str_no);
			
			
			try {
				String option = request.getParameter("dishCheck");
				System.out.println(option);
				
				String dish_no = request.getParameter("dish_no").trim();
				System.out.println(dish_no);
				
				String dish_name = request.getParameter("dish_name").trim();
				String dish_note = request.getParameter("dish_note").trim();
				String dish_status = request.getParameter("dish_status");
				byte[] dish_img = ShareTool.sendPicture(request.getPart("dish_pic"));
				
				System.out.println("length of dish_img = " + dish_img.length);

				Double dish_price = null;
				try {
					dish_price = new Double(request.getParameter("dish_price").trim());
					if(dish_price < 0) {
						errorMsgs.add("菜單價格輸入錯誤");
					}
					
				} catch (NumberFormatException e) {
					errorMsgs.add("菜單價格輸入錯誤");
					RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishUpdate.jsp");
					failureView.forward(request, response);
					return;
				}
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishUpdate.jsp");
					failureView.forward(request, response);
					return;	
				}
				
				if("修改".equals(option)) {
					
					DishVO dishVO = new DishVO();
					dishVO.setDish_name(dish_name);
					dishVO.setDish_price(dish_price);
					dishVO.setDish_status("準備中");
					dishVO.setDish_img(dish_img);
					dishVO.setDish_note(dish_note);
					dishVO.setDish_no(dish_no);
					
					if (!errorMsgs.isEmpty()) {
						request.setAttribute("dishVO", dishVO);
						RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishUpdate.jsp");
						failureView.forward(request, response);
						return;
					}
					
					DishService dishSvc = new DishService();
					List<DishVO> dishList = new LinkedList<DishVO>();
					
					if(dish_img.length == 0) {
						
						dishVO = dishSvc.update(dish_no, dish_name, dish_price, dish_status, dish_note);
						System.out.println("update success");
						
					} else {
						
						dishVO = dishSvc.updateImg(dish_no, dish_name, dish_price, dish_status, dish_img, dish_note);
						System.out.println("update img success");
			
					}
					
					dishList.add(dishVO);
					request.setAttribute("dishList", dishList);
					String url = "/easyfood/front-end/class/store/str_dishView.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					
				} else if ("下架".equals(option)) {
					
					System.out.println(dish_no);
					System.out.println(dish_status);
					
					DishService dishSvc = new DishService();
					dishSvc.updateStatus(dish_no, "已下架");
					System.out.println("update status success");
					
					DishVO dishVO = dishSvc.getOneDish(dish_no);
					List<DishVO> dishList = new LinkedList<DishVO>();
					dishList.add(dishVO);
					request.setAttribute("dishList", dishList);
					
					String url = "/easyfood/front-end/class/store/str_dishView.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					
				}
							
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/front-end/class/store/str_dishUpdate.jsp");
				failureView.forward(request, response);
			}

		}
		
		
//		 ------------------------後台審核 餐點狀態修改------------------------
		
		if("UPDATE_ONE".equals(action)) {
			
			String dish_no = request.getParameter("dish_no");
			DishService dishSvc = new DishService();
			DishVO dishVO = dishSvc.getOneDish(dish_no);
			
			request.setAttribute("dishVO", dishVO);
			String url = "/easyfood/back-end/class/store/Dish_update.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
		}
		
		if("UPDATE".equals(action)) {
			
			String dish_status = request.getParameter("dish_status");
			String dish_no = request.getParameter("dish_no");
				
			System.out.println(dish_status);
			System.out.println(dish_no);
			
			DishService dishSvc = new DishService();
			dishSvc.updateStatus(dish_no, dish_status);
			System.out.println("update success");
			
			List<DishVO> dishList = dishSvc.getStatusDish(dish_status);
			request.setAttribute("dishList", dishList);
			String url = "/easyfood/back-end/class/store/Dish_portion.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
		}
		
		if("SEARCH_STATUS".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String dish_status = request.getParameter("dish_status");
			System.out.println(dish_status);
			
			if(dish_status.equals("餐點狀態")) {
				errorMsgs.add("請選擇餐點狀態");
			}
			
			if(! errorMsgs.isEmpty()) {
				request.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = request.getRequestDispatcher("/easyfood/back-end/class/store/Dish_all.jsp");
				failureView.forward(request, response);
				return;
			}
			
			DishService dishSvc = new DishService();
			List<DishVO> dishList = dishSvc.getStatusDish(dish_status);
			request.setAttribute("dishList", dishList);
			
			String url = "/easyfood/back-end/class/store/Dish_portion.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
	
	}

}


