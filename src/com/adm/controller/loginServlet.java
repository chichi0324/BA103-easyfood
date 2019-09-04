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
import com.admp.model.AdmpVO;

public class loginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
        if ("back_login".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 檢查輸入資料是否空白*************************/
				String adm_acc = req.getParameter("adm_acc").trim();
				String adm_pas = req.getParameter("adm_pas").trim();

				if("".equals(adm_acc)){
					errorMsgs.add("帳號請勿空白");
				}
				
				if("".equals(adm_pas)){
					errorMsgs.add("密碼請勿空白");
				}
				

				AdmVO admVO = new AdmVO();
				admVO.setAdm_acc(adm_acc);
				admVO.setAdm_pas(adm_pas);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/easyfood/back-end/login.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.接收請求參數 - 檢查輸入資料是否正確***************************************/

				AdmService admSvc = new AdmService();
				admVO = admSvc.getOneAdm1(adm_acc);  
				boolean check=false;
				
				if(admVO==null){
					errorMsgs.add("帳號輸入錯誤");
				}else {
					String password=admVO.getAdm_pas();
					
					if(password.equals(adm_pas)){
						check=true;
					}else{
						errorMsgs.add("密碼輸入錯誤");
					}			
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/easyfood/back-end/login.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.資料正確***************************************/
				
				if(check){
					HttpSession session =req.getSession();
					session.setAttribute("admVO_account", admVO);
					
					
					AdmpService admpSvc=new AdmpService();
					List<AdmpVO> admplist=admpSvc.getTwoByADM(admVO.getAdm_no());
					
					List<String> fea_noList=new ArrayList<String>();
					for(AdmpVO list: admplist){
						fea_noList.add(list.getFea_no());
					}
					session.setAttribute(admVO.getAdm_acc(), fea_noList);
					
					try{
						 String location = (String) session.getAttribute("location");
						 if(location != null){
							 session.removeAttribute("location");
					         res.sendRedirect(location);            
					         return;
						 }
					}catch(Exception ignored){}
					
					res.sendRedirect(req.getContextPath()+"/easyfood/back-end/index.jsp");
				}
							
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/back-end/login.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("back_logout".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 檢查輸入資料是否空白*************************/
				HttpSession session =req.getSession();
				session.invalidate();
							
				res.sendRedirect(req.getContextPath() + "/easyfood/back-end/login.jsp");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/back-end/login.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("navbar".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 檢查輸入資料是否空白*************************/
            String fea_no=req.getParameter("fea_no");
            String url ="/easyfood/back-end/index.jsp";
            
            HttpSession session =req.getSession();
            
            if("FEA_0002".equals(fea_no)){
            	session.setAttribute("leftGroup", "member");
            	url="/easyfood/back-end/class/member/member.jsp";
            }else if("FEA_0003".equals(fea_no)){
            	session.setAttribute("leftGroup", "store");
            	url="/easyfood/back-end/class/store/store.jsp";
            }else if("FEA_0005".equals(fea_no)){
            	session.setAttribute("leftGroup", "administrator");
            	url="/easyfood/back-end/class/administrator/administrator.jsp";
            }else if("FEA_0004".equals(fea_no)){
            	session.setAttribute("leftGroup", "admprivileges");
            	url="/easyfood/back-end/class/admprivileges/admprivileges.jsp";
            }else if("FEA_0006".equals(fea_no)){
            	session.setAttribute("leftGroup", "report");
            	url="/easyfood/back-end/class/report/report.jsp";
            }else if("FEA_0001".equals(fea_no)){
            	session.setAttribute("leftGroup", "advertising");
            	url="/easyfood/back-end/class/advertising/advertising.jsp";
            }else if("index".equals(fea_no)){
            	session.removeAttribute("leftGroup");
            	url="/easyfood/back-end/index.jsp";
            }
            

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/back-end/index.jsp");
				failureView.forward(req, res);
			}
		}


	}
	
}
