package com.store.search.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dish.model.DishService;
import com.dish.model.DishVO;
import com.str.model.StrVO;
import com.str.model.strService;
import com.tools.tools;

public class StoreSearchServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		
		if ("search_display".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String Address = req.getParameter("address");
				String address=new String(Address.getBytes("ISO-8859-1"),"UTF-8");
				List<String> list1=new ArrayList<>();//依地址查詢
				if(!"".equals(address.trim())){
					list1.add(address);
				}
						
				String type = req.getParameter("type");
				List<String> list2=new ArrayList<>();//依外送與否查詢
				if("TRUE".equals(type.trim())||"FALSE".equals(type.trim())){
					list2.add(type);
				}
						
				String[] store_style=req.getParameterValues("store_style");
				List<String> list3=new ArrayList<>();//依店家類別查詢
				if(store_style!=null){
					for(int i=0;i<store_style.length;i++){
						list3.add(store_style[i]);
					}
				}

					
				String KeyWord = req.getParameter("keyWord");
				String keyWord = new String(KeyWord.getBytes("ISO-8859-1"),"UTF-8");
				List<String> list4=new ArrayList<>();//依關鍵字查詢
				if(!"".equals(keyWord.trim())){
					list4.add(keyWord);
				}
				

				
				Map<Integer, List<String>> strMap=new HashMap<Integer, List<String>>();
				strMap.put(0, list1);//0為依地址查詢
				strMap.put(1, list2);//1為依外送與否查詢
				strMap.put(2, list3);//2為依店家類別查詢
				strMap.put(3, list4);//3為依關鍵字查詢
				
				strService strSvc = new strService();
				List<StrVO> strList =null;
				if(list1.size()==0 && list2.size()==0 && list3.size()==0 && list4.size()==0){
					strList=(List<StrVO>) strSvc.getAll();
				}else{
					strList = (List<StrVO>) strSvc.findByMuti(strMap);
				}
				
				////開發時期,店家圖片暫不產生,此迴圈先不執行
//				for(int i=0;i<strList.size();i++){
//					tools.readPicture(strList.get(i).getStr_img(), strList.get(i).getStr_no());
//				}
				
				HttpSession session =req.getSession();
				session.setAttribute("address", address);
				session.setAttribute("type", type);
				session.setAttribute("storeType", list3);
				session.setAttribute("keyWord", keyWord);

				
				req.removeAttribute("strList");
				req.setAttribute("strList", strList);
								

				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/

				session.setAttribute("navbar_select", "search");
				
				String url = "/easyfood/front-end/class/search/search.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/search/search.jsp");
				failureView.forward(req, res);
			}
		}
		
		

		
		if ("enter_store_select".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String str_no = req.getParameter("str_no");
				String dcla_no = req.getParameter("dcla_no");
				String select = req.getParameter("select");
				
				strService strSvc = new strService();
				StrVO strVO=strSvc.getOneStr(str_no);
			
				
				req.setAttribute("strVO", strVO);
				
				req.removeAttribute("enter_display");
				
				if("introduce".equals(select)){
					//req.setAttribute("enter_display", "enter_store_introduce");
				}else if("menu".equals(select)){
					DishService dishSvc= new DishService();
					
					
					//菜單圖片太多,此迴圈先不執行
//					for(int i=0;i<dishList.size();i++){
//						tools.readDishPicture(dishList.get(i).getDish_img(), dishList.get(i).getDish_no());
//					}
					
					if("all".equals(dcla_no)){
						req.setAttribute("menu_select", "all_menu");
						req.setAttribute("str_no", str_no);
					}else{
						List<DishVO> dishList=dishSvc.findByClassStoreStatus(dcla_no, str_no, "販售中");	
						req.setAttribute("dcla_no", dcla_no);
						req.setAttribute("dishList", dishList);
					}
					
					req.setAttribute("enter_display", "enter_store_menu");
					

					
				}else if("blog".equals(select)){
					
					req.setAttribute("enter_display", "enter_store_blog");
								
				}else if("comment".equals(select)){
					
					req.setAttribute("enter_display", "enter_store_comment");
					
				}else if("online".equals(select)){
					
					req.setAttribute("enter_display", "enter_store_online");
				}
				
				req.removeAttribute("display");
				req.setAttribute("display", "enter_store");
				
				
				HttpSession session=req.getSession();
				session.setAttribute("navbar_select", "search");
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/front-end/class/search/search.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} 
			catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/search/search.jsp");
				failureView.forward(req, res);
			}
		}
				    
		
	}

}