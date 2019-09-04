package com.mem.controller;

import java.io.IOException;
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
import com.blog.model.BlogService;
import com.blog.model.BlogVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

public class memVioServlet extends HttpServlet  {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("display_mem_stas".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String mem_vio_s = req.getParameter("mem_vio");
				Integer mem_vio= Integer.parseInt(mem_vio_s);
				
				String mem_stas_c = req.getParameter("mem_stas");
				String mem_stas=new String(mem_stas_c.getBytes("ISO-8859-1"),"UTF-8");

				MemService memSvc= new MemService();
				List<MemVO> memList=memSvc.getVio_Stas(mem_vio, mem_stas);

				
				req.setAttribute("mem_vio", mem_vio_s);
				req.setAttribute("mem_stas", mem_stas);
				req.setAttribute("memList", memList);
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/back-end/class/member/member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/member/member.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update_mem_stas".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String number_s = req.getParameter("number");
				Integer number= Integer.parseInt(number_s);
				
				MemService memSvc= new MemService();
				MemVO memVO=null;
				String mem_no=null;
				String mem_stas_c=null;
				String mem_stas=null;
				for(int i=0;i<number;i++){
					mem_no=req.getParameter("mem_no_"+i);
					mem_stas_c=req.getParameter("mem_stas_"+i);
					mem_stas=new String(mem_stas_c.getBytes("ISO-8859-1"),"UTF-8");
					//System.out.println("mem_stas"+mem_stas);
					memVO=memSvc.getOneMem(mem_no);
					memSvc.updateMem(memVO.getMem_pw(), memVO.getMem_name(), memVO.getMem_pho(), memVO.getMem_mail(), memVO.getMem_vio(),mem_stas , mem_no);
				}				
				
				String mem_vio_s = req.getParameter("mem_vio");								
				mem_stas_c = req.getParameter("mem_stas");
				

				
				if("noData".equals(mem_vio_s)||"noData".equals(mem_stas_c)){
					req.setAttribute("display", "display_all");
				}else{
					Integer mem_vio= Integer.parseInt(mem_vio_s);
					mem_stas=new String(mem_stas_c.getBytes("ISO-8859-1"),"UTF-8");
					
					List<MemVO> memList=memSvc.getVio_Stas(mem_vio, mem_stas);
					req.setAttribute("mem_vio", mem_vio_s);
					req.setAttribute("mem_stas", mem_stas);
					req.setAttribute("memList", memList);
				}
				

				

				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/back-end/class/member/member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/member/member.jsp");
				failureView.forward(req, res);
			}
		}


	}
	
}

