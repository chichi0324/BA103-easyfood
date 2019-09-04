package com.fea.controller;

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
import com.admp.model.AdmpVO;
import com.fea.model.FeaService;
import com.fea.model.FeaVO;
import com.ra.model.RaService;
import com.ra.model.RaVO;
import com.tools.tools;

public class feaServlet extends HttpServlet  {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
				
		
        if ("add_fea".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsFeaAdd", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/				
				String fea_name_s = req.getParameter("fea_name").trim();
				String fea_name = new String(fea_name_s.getBytes("ISO-8859-1"), "UTF-8");
				
				if("".equals(fea_name)){
					errorMsgs.add("請勿空白.");
				}


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("display", "feature_add");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/easyfood/back-end/class/admprivileges/admprivileges.jsp");
					failureView.forward(req, res);
					return;
				}

				
				/***************************2.開始新增資料***************************************/
				
                 FeaService feaSvc=new FeaService();
                 feaSvc.addFea(fea_name);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
                req.removeAttribute("display");
								
				String url = "/easyfood/back-end/class/admprivileges/admprivileges.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/back-end/class/admprivileges/admprivileges.jsp");
				failureView.forward(req, res);
			}
		}
        
		if ("delete_fea".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String fea_no = req.getParameter("fea_no");
				
				FeaService feaSvc=new FeaService();
				feaSvc.deleteFea(fea_no);

				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				req.removeAttribute("display");
				String url = "/easyfood/back-end/class/admprivileges/admprivileges.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/admprivileges/admprivileges.jsp");
				failureView.forward(req, res);
			}
		}
		
		
        if ("display_update_fea".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsUpdate", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String fea_no = req.getParameter("fea_no");
				
                FeaService feaSvc=new FeaService();		
                FeaVO feaVO=feaSvc.getOneFea(fea_no);
				
				List<AdmVO> AdmVOList=tools.getAmpList(feaVO);
				
				List<String> AdmNOList=new ArrayList<String>();
				for(int i=0;i<AdmVOList.size();i++){
					AdmNOList.add(AdmVOList.get(i).getAdm_no());
				}
			    

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("feaVO", feaVO);
				req.setAttribute("AdmNOList", AdmNOList);
				
				req.removeAttribute("display");
				req.setAttribute("fea_man", "feature_update");
				
				String url = "/easyfood/back-end/class/admprivileges/admprivileges.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/back-end/class/admprivileges/admprivileges.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("fea_update".equals(action)) { // 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgsUpdate", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String fea_no = req.getParameter("fea_no");
				String[] adm_no_Array=req.getParameterValues("adm_no");
				/***************************2.開始新增資料***************************************/


				AdmpService admpSvc=new AdmpService();
				List<AdmpVO> admpList=admpSvc.getTwoByFEA(fea_no);


				admpSvc.deleteByFea(fea_no);

				
				for(int i=0;i<adm_no_Array.length;i++){
					admpSvc.addAdmp(fea_no, adm_no_Array[i]);	
				}	

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				req.removeAttribute("display");
				req.removeAttribute("fea_man");
				
				String url = "/easyfood/back-end/class/admprivileges/admprivileges.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/back-end/class/admprivileges/admprivileges.jsp");
				failureView.forward(req, res);
			} 
		}
 



	}
	
}

