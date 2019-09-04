package com.rep.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.model.BlogService;
import com.blog.model.BlogVO;
import com.dish.model.DishService;
import com.dish.model.DishVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.ra.model.RaService;
import com.ra.model.RaVO;
import com.rep.model.RepService;
import com.rep.model.RepVO;
import com.str.model.StrVO;
import com.str.model.strService;

public class repServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("display_str_stas".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String str_rep_s = req.getParameter("str_rep");
				Integer str_rep = Integer.parseInt(str_rep_s);

				String str_stat_c = req.getParameter("str_stat");
				String str_stat = new String(str_stat_c.getBytes("ISO-8859-1"), "UTF-8");

				strService strSvc = new strService();
				List<StrVO> strList = strSvc.findByRepStat(str_rep, str_stat);

				req.setAttribute("str_rep", str_rep_s);
				req.setAttribute("str_stat", str_stat);
				req.setAttribute("strList", strList);
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/back-end/class/report/report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/report/report.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update_str_stas".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String number_s = req.getParameter("number");
				Integer number = Integer.parseInt(number_s);

				strService strSvc = new strService();
				StrVO strVO = null;
				String str_no = null;
				String str_stat_c = null;
				String str_stat = null;
				String dish_sta="";
				for (int i = 0; i < number; i++) {
					str_no = req.getParameter("str_no_" + i);
					str_stat_c = req.getParameter("str_stat_" + i);
					str_stat = new String(str_stat_c.getBytes("ISO-8859-1"), "UTF-8");
					 System.out.println("str_no:"+str_no+",str_stat:"+str_stat);
					strVO = strSvc.getOneStr(str_no);									
					
					strSvc.updateStatus(str_no, str_stat);
					
					DishService dishSvc=new DishService();
					List<DishVO> dishList=dishSvc.getStoreDish(str_no);
					
					
					if("營業中".equals(str_stat)){
						dish_sta="販售中";
					}else if("建置中"==str_stat_c){
						dish_sta="準備中";
					}else if("暫停中".equals(str_stat)||"下架".equals(str_stat.trim())){
						dish_sta="已下架";
					}
					
					System.out.println("str_no:"+str_no+",dish_sta:"+dish_sta);
					for(int j=0;j<dishList.size();j++){
						dishSvc.updateStatus(dishList.get(j).getDish_no(), dish_sta);
					}
					
					
				}

				/*************************** 2.接收請求參數 ***************************************/
				String str_rep_s = req.getParameter("str_rep");
				Integer str_rep = Integer.parseInt(str_rep_s);
				str_stat_c = req.getParameter("str_stat");
				str_stat = new String(str_stat_c.getBytes("ISO-8859-1"), "UTF-8");


				List<StrVO> strList = strSvc.findByRepStat(str_rep, str_stat);
				req.setAttribute("str_rep", str_rep_s);
				req.setAttribute("str_stat", str_stat);
				req.setAttribute("strList", strList);

				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/back-end/class/report/report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/report/report.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("display_blog_stas".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/

				String ra_rev_c = req.getParameter("ra_rev");
				String ra_rev = new String(ra_rev_c.getBytes("ISO-8859-1"), "UTF-8");

				RaService raSvc = new RaService();
				List<RaVO> raList = raSvc.findByRev(ra_rev);

				req.setAttribute("display", "blog");
				
				req.setAttribute("ra_rev", ra_rev);
				req.setAttribute("raList", raList);
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/back-end/class/report/report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/report/report.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update_blog_stas".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String number_s = req.getParameter("number");
				Integer number = Integer.parseInt(number_s);

				RaService raSvc = new RaService();
				RaVO raVO = null;
				String ra_no = null;
				String ra_rev_c = null;
				String ra_rev = null;
				
				BlogService blogSvc=new BlogService();
				BlogVO blogVO=null;
				
				MemService memSvc=new MemService();
				MemVO memVO=null;
				
				for (int i = 0; i < number; i++) {
					ra_no = req.getParameter("ra_no_" + i);
					ra_rev_c = req.getParameter("ra_rev_" + i);
					ra_rev = new String(ra_rev_c.getBytes("ISO-8859-1"), "UTF-8");
					// System.out.println("mem_stas"+mem_stas);
					raVO = raSvc.findByPrimaryKey(ra_no);
					raSvc.updateRa(ra_no, raVO.getBl_no(), raVO.getRa_res(), ra_rev);
					
					if("通過".equals(ra_rev)){
						blogVO=blogSvc.findByPrimaryKey(raVO.getBl_no());
						memVO=memSvc.getOneMem(blogVO.getMem_no());
						memSvc.updateMem(memVO.getMem_pw(), memVO.getMem_name(), memVO.getMem_pho(), memVO.getMem_mail(), memVO.getMem_vio()+1, memVO.getMem_stas(), memVO.getMem_no());								
					}					
				}

				/*************************** 2.接收請求參數 ***************************************/
				ra_rev_c = req.getParameter("ra_rev");
				ra_rev = new String(ra_rev_c.getBytes("ISO-8859-1"), "UTF-8");


				List<RaVO> raList = raSvc.findByRev(ra_rev);

				req.setAttribute("display", "blog");
				
				req.setAttribute("ra_rev", ra_rev);
				req.setAttribute("raList", raList);

				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/back-end/class/report/report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/report/report.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("display_str_rep".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/

				String rep_rev_c = req.getParameter("rep_rev");
				String rep_rev = new String(rep_rev_c.getBytes("ISO-8859-1"), "UTF-8");

				RepService repSvc =new RepService();
				List<RepVO> replist = repSvc.findByREV(rep_rev);

				req.setAttribute("display", "store");
				
				req.setAttribute("rep_rev", rep_rev);
				req.setAttribute("replist", replist);
				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/back-end/class/report/report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/report/report.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update_str_rep".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String number_s = req.getParameter("number");
				Integer number = Integer.parseInt(number_s);

				RepService repSvc = new RepService();
				RepVO repVO = null;
				String rep_no = null;
				String rep_rev_c = null;
				String rep_rev = null;
				
				OrdService ordSvc=new OrdService();
				OrdVO ordVO=null;
				
				strService strSvc=new strService();
				StrVO strVO=null;
				
				for (int i = 0; i < number; i++) {
					rep_no = req.getParameter("rep_no_" + i);
					rep_rev_c = req.getParameter("rep_rev_" + i);
					rep_rev = new String(rep_rev_c.getBytes("ISO-8859-1"), "UTF-8");					
					repVO = repSvc.getOneRep(rep_no);
//					System.out.println("rep_rev:"+rep_rev);
//					System.out.println("rep_res:"+repVO.getRep_res());
//					System.out.println("ord_no:"+repVO.getOrd_no());
					repSvc.updateReport(repVO.getRep_res() , rep_rev, repVO.getRep_no());
					
					if("通過".equals(rep_rev)){
						ordVO=ordSvc.getOneOrd(repVO.getOrd_no());
						strVO=strSvc.getOneStr(ordVO.getStr_no());
						strSvc.updateRep(strVO.getStr_rep()+1, strVO.getStr_no());						
					}					
				}

				/*************************** 2.接收請求參數 ***************************************/
				rep_rev_c = req.getParameter("rep_rev");
				rep_rev = new String(rep_rev_c.getBytes("ISO-8859-1"), "UTF-8");


				List<RepVO> replist = repSvc.findByREV(rep_rev);

				req.setAttribute("display", "store");
				
				req.setAttribute("rep_rev", rep_rev);
				req.setAttribute("replist", replist);

				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/back-end/class/report/report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/back-end/class/report/report.jsp");
				failureView.forward(req, res);
			}
		}
		


	}

}
