package com.adm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adm.model.AdmService;
import com.adm.model.AdmVO;
import com.admp.model.AdmpService;
import com.fea.model.FeaService;
import com.fea.model.FeaVO;
import com.ra.model.RaService;
import com.ra.model.RaVO;
import com.tools.MailService;
import com.tools.adm_password_get;
import com.tools.tools;

public class admServlet extends HttpServlet  {
	//開發階段216~222行已註解,展示在使用寄信功能			
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("display_adm".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/

				String adm_acc = req.getParameter("adm_acc");

				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAdm1(adm_acc);

				//req.setAttribute("display", "blog");
                if(admVO==null){
                	String admStr="noData";
                	req.setAttribute("admStr", admStr);
                }else{
                	req.setAttribute("admVO", admVO);
                }
				
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/back-end/class/administrator/administrator.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/administrator/administrator.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete_adm".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String adm_no = req.getParameter("adm_no");
				
				AdmService admSvc=new AdmService();
				admSvc.deleteAdm(adm_no);


				/*************************** 2.接收請求參數 ***************************************/


				req.setAttribute("display", "adm_all");
				

				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/back-end/class/administrator/administrator.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/administrator/administrator.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("adm_add".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsAdd", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String adm_acc = req.getParameter("adm_acc").trim();
				
				String adm_name_s = req.getParameter("adm_name").trim();
				String adm_name = new String(adm_name_s.getBytes("ISO-8859-1"), "UTF-8");
				
				String adm_adrs_s = req.getParameter("adm_adrs").trim();
				String adm_adrs = new String(adm_adrs_s.getBytes("ISO-8859-1"), "UTF-8");
				
				String adm_pho = req.getParameter("adm_pho").trim();
				
				String adm_pos_s = req.getParameter("adm_pos").trim();
				String adm_pos = new String(adm_pos_s.getBytes("ISO-8859-1"), "UTF-8");
				
				String adm_mail = req.getParameter("adm_mail").trim();
				
				String[] fea_no=req.getParameterValues("fea_no");

				
				AdmService admSvc = new AdmService();
				if("".equals(adm_acc)){
					errorMsgs.add("帳號請勿空白.");
				}else if(admSvc.getOneAdm1(adm_acc)!=null){
						errorMsgs.add("帳號已使用過.");					
				}
				
				if("".equals(adm_name)){
					errorMsgs.add("姓名請勿空白.");
				}
				
				if("".equals(adm_adrs)){
					errorMsgs.add("地址請勿空白.");
				}
				
				if("".equals(adm_mail)){
					errorMsgs.add("信箱請勿空白.");
				}
				
				if("".equals(adm_pho)){
					errorMsgs.add("電話請勿空白.");
				}else{
					Integer adm_pho_i = null;
					try {
						adm_pho_i = new Integer(adm_pho);
					} catch (NumberFormatException e) {
						adm_pho_i = 0;
						errorMsgs.add("電話請填數字.");
					}
				}
				
				if("請選擇".equals(adm_pos)){
					errorMsgs.add("職位請選擇.");
				}
				
				List<String> feaNoList=new ArrayList<String>();
				if(fea_no!=null){

					for(int i=0;i<fea_no.length;i++){
						feaNoList.add(fea_no[i]);					
				}					
			}
				
				AdmVO admVO=new AdmVO();
				admVO.setAdm_acc(adm_acc);
				admVO.setAdm_name(adm_name);
				admVO.setAdm_adrs(adm_adrs);
				admVO.setAdm_pho(adm_pho);
				admVO.setAdm_pos(adm_pos);
				admVO.setAdm_mail(adm_mail);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("display", "adm_add");
					req.setAttribute("feaNoList", feaNoList);
					req.setAttribute("admVO_add", admVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/easyfood/back-end/class/administrator/administrator.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String adm_pas=adm_password_get.producePas();
				
				
				/***************************2.開始新增資料***************************************/
				
				admVO = admSvc.addAdm(adm_acc, adm_pas, adm_name, adm_adrs, adm_pho, adm_pos,adm_mail);
				
				admVO =admSvc.getOneAdm1(adm_acc);
				
				AdmpService admpSvc=new AdmpService();
				for(int i=0;i<feaNoList.size();i++){
					admpSvc.addAdmp(feaNoList.get(i), admVO.getAdm_no());		
				}	
				
//開發階段216~222行已註解,展示在使用寄信功能				
				 String to = admVO.getAdm_mail();			      
			     String subject = "食在方便新進員工[密碼通知]";
			      
			      String ch_name = admVO.getAdm_name();
  		          String passRandom = adm_pas;
			      String messageText = "Hello! " + ch_name + " 您好, 請謹記此密碼: " + passRandom + "\n" +" (已經啟用) \n from:食在方便有限公司"; 
                  MailService.sendMail(to, subject, messageText);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("admVO", admVO);
				req.removeAttribute("display");
				
				
				String url = "/easyfood/back-end/class/administrator/administrator.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/back-end/class/administrator/administrator.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        if ("display_update_adm".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsUpdate", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String adm_no = req.getParameter("adm_no");
				

				
				AdmService admSvc = new AdmService();				
                AdmVO admVO=admSvc.getOneAdm(adm_no);
				
				List<FeaVO> feaVOList=tools.getFeaName(admVO);
				
				List<String> feaNoList=new ArrayList<String>();
				for(int i=0;i<feaVOList.size();i++){
					feaNoList.add(feaVOList.get(i).getFea_no());		
				}


				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("adm_no", adm_no);
				req.setAttribute("admVO_update", admVO);
				req.setAttribute("feaNoList", feaNoList);
				
				req.removeAttribute("display");
				req.setAttribute("adm_select", "update");
				
				String url = "/easyfood/back-end/class/administrator/administrator.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/back-end/class/administrator/administrator.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        if ("adm_update".equals(action)) { // 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsUpdate", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String adm_acc = req.getParameter("adm_acc").trim();
				
				String adm_name_s = req.getParameter("adm_name").trim();
				String adm_name = new String(adm_name_s.getBytes("ISO-8859-1"), "UTF-8");
				
				String adm_adrs_s = req.getParameter("adm_adrs").trim();
				String adm_adrs = new String(adm_adrs_s.getBytes("ISO-8859-1"), "UTF-8");
				
				String adm_pho = req.getParameter("adm_pho").trim();
				
				String adm_pos_s = req.getParameter("adm_pos").trim();
				String adm_pos = new String(adm_pos_s.getBytes("ISO-8859-1"), "UTF-8");
				
				String adm_mail = req.getParameter("adm_mail").trim();
				
				String[] fea_no=req.getParameterValues("fea_no");

				
				
				if("".equals(adm_acc)){
					errorMsgs.add("帳號請勿空白.");
				}
				
				if("".equals(adm_name)){
					errorMsgs.add("姓名請勿空白.");
				}
				
				if("".equals(adm_adrs)){
					errorMsgs.add("地址請勿空白.");
				}
				
				if("".equals(adm_mail)){
					errorMsgs.add("信箱請勿空白.");
				}
				
				if("".equals(adm_pho)){
					errorMsgs.add("電話請勿空白.");
				}else{
					Integer adm_pho_i = null;
					try {
						adm_pho_i = new Integer(adm_pho);
					} catch (NumberFormatException e) {
						adm_pho_i = 0;
						errorMsgs.add("電話請填數字.");
					}
				}
				
				if("請選擇".equals(adm_pos)){
					errorMsgs.add("職位請選擇.");
				}
				
				List<String> feaNoList=new ArrayList<String>();
				if(fea_no!=null){

						for(int i=0;i<fea_no.length;i++){
							feaNoList.add(fea_no[i]);					
					}					
				}
				
				AdmVO admVO=new AdmVO();
				admVO.setAdm_acc(adm_acc);
				admVO.setAdm_name(adm_name);
				admVO.setAdm_adrs(adm_adrs);
				admVO.setAdm_pho(adm_pho);
				admVO.setAdm_pos(adm_pos);
				admVO.setAdm_mail(adm_mail);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("feaNoList", feaNoList);
					req.setAttribute("admVO_update", admVO);
					
					req.removeAttribute("display");
					req.setAttribute("adm_select", "update");
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/easyfood/back-end/class/administrator/administrator.jsp");
					failureView.forward(req, res);
					return;
				}
				
				

				
				/***************************2.開始新增資料***************************************/
				String adm_no = req.getParameter("adm_no");
				
				AdmService admSvc = new AdmService();
				admVO =admSvc.getOneAdm(adm_no);

				admSvc.updateAdm(adm_no, adm_acc, admVO.getAdm_pas(), adm_name, adm_adrs, adm_pho, adm_pos ,adm_mail);
				
				AdmpService admpSvc=new AdmpService();
				admpSvc.deleteAdm(adm_no);
				for(int i=0;i<feaNoList.size();i++){
					admpSvc.addAdmp(feaNoList.get(i), adm_no);	
				}	
				
				admVO =admSvc.getOneAdm(adm_no);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("admVO", admVO);
				
				req.removeAttribute("display");
				req.removeAttribute("update");
				
				String url = "/easyfood/back-end/class/administrator/administrator.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/back-end/class/administrator/administrator.jsp");
				failureView.forward(req, res);
			} 
		}



	}
	
}

