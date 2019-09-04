package com.ord.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.cartorder.model.CartOrderVO;
import com.dish.model.DishService;
import com.dish.model.DishVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.ordit.model.OrditService;
import com.ordit.model.OrditVO;
import com.store.model.StrVO;
import com.store.model.StrService;
import com.tools.tools;

@WebServlet("/OrdServlet")
public class OrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String actionFromCartServlet = (String) session.getAttribute("action");
		String actionFromMemJspOrEvJSP = (String) req.getParameter("action");
		System.out.println("actionFromCartServlet:"+actionFromCartServlet);
		if ("addOrd".equals(actionFromCartServlet)) { // 來自CartServlet的請求
			System.out.println("u進來OrdServlet的addOrd");
			CartOrderVO CartOrder = (CartOrderVO) session.getAttribute("CartOrder");
			MemVO memVO =(MemVO)session.getAttribute("memVO");
			System.out.println("OrdServlet memVO: " +memVO);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************
				 * * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				System.out.println("memVO:"+memVO);
				System.out.println("memVO.getMem_no():"+memVO.getMem_no());
				String mem_no = memVO.getMem_no();
				String str_no = CartOrder.getStr_no();
				String ord_type = CartOrder.getOrd_type();
				Double ord_pri = CartOrder.getOrd_pri();
				Timestamp ord_date = new tools().nowTimestamp();
				String ord_add = CartOrder.getAdd_adds();

				OrdVO ordVO = new OrdVO();
				ordVO.setMem_no(mem_no);
				ordVO.setStr_no(str_no);
				ordVO.setOrd_type(ord_type);
				ordVO.setOrd_pri(ord_pri);
				ordVO.setOrd_date(ord_date);
				ordVO.setOrd_add(ord_add);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/shoppingCart.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.addOrd(mem_no, str_no, ord_type, ord_pri, ord_date, ord_add);
				// 以下準備資料給新增ordit用
				OrdService OrdSvc = new OrdService();
				// 找出全部訂單
				List<OrdVO> ordlist = OrdSvc.getAll();

				OrdVO ordVO1 = ordlist.get(ordlist.size() - 1);
				CartOrder.setOrd_no(ordVO1.getOrd_no());
				session.removeAttribute("action");
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/easyfood/front-end/class/member/Ortit.do";
				req.setAttribute("action", "addOrdit");
				req.setAttribute("CartOrder", CartOrder);
				session.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入session
				// 將memVO放進session
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/shoppingCart.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("getOneOrd_For_Display".equals(actionFromMemJspOrEvJSP)) { // 來自Member.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String ord_no = req.getParameter("ord_no");

				/*************************** 2.開始查詢資料 *****************************************/
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.getOneOrd(ord_no); // 取得OrdVO

				OrditService orditSvc = new OrditService();
				List<OrditVO> aOrditVOlist = new ArrayList();
				List<OrditVO> allOrditlist = orditSvc.getAll();
				for (int i = 0; i < allOrditlist.size(); i++) { // 取得此訂單下所有的訂單細項(OrditVO)
					OrditVO aOrdit = allOrditlist.get(i);
					if (aOrdit.getOrd_no().equals(ord_no)) {
						aOrditVOlist.add(aOrdit);

					}
				}

				StrService strSvc = new StrService();
				StrVO strVO = strSvc.getOneStr(ordVO.getStr_no()); // 取得StrVO

				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(ordVO.getMem_no());

				List<DishVO> aDishVOlist = new ArrayList();
				DishService dishSvc = new DishService();
				List<DishVO> allDishlist = dishSvc.getAll(); // 為了在order_details.jsp顯示菜名而用的
				for (int i = 0; i < aOrditVOlist.size(); i++) {
					OrditVO aOrdit = aOrditVOlist.get(i);
					for (int j = 0; j < allDishlist.size(); j++) {
						DishVO aDish = allDishlist.get(j);
						if (aDish.getDish_no().equals(aOrdit.getDish_no())) {
							aDishVOlist.add(aDish);
						}
					}
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				System.out.println("ordVO:"+ordVO.getOrd_no());
				req.setAttribute("ordVO", ordVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("aOrditlist", aOrditVOlist);
				req.setAttribute("strVO", strVO);
				req.setAttribute("memVO", memVO);
				req.setAttribute("aDishlist", aDishVOlist);
				String url = "/easyfood/front-end/class/member/order_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member.jsp");
				failureView.forward(req, res);
			}
		}

		if ("addOrModifyEv".equals(actionFromMemJspOrEvJSP)) { // 來自member_information.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/

			String ord_ev_string = req.getParameter("ord_ev");

			if (ord_ev_string == "") {
				errorMsgs.add("星級一定要選");
			}
			String ord_eva = req.getParameter("ord_eva").trim();
			OrdVO ordVO = new OrdVO();
			ordVO.setOrd_eva(ord_eva);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的memVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/evaluate.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			
			String ord_no = req.getParameter("ord_no");
			OrdService ordSvc = new OrdService();			
			Integer ord_ev = Integer.parseInt(ord_ev_string);

			/*************************** 2.開始修改資料 *****************************************/
			ordVO = ordSvc.updateOrd(ord_ev, ord_eva, ord_no);
			/****************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("ordVO", ordVO); // 資料庫update成功後,正確的的empVO物件,存入req
			errorMsgs.add("評價已送出");
			String url = "/easyfood/front-end/class/member/evaluate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交member_informationt.jsp
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("修改資料失敗:" + e.getMessage());
			// RequestDispatcher failureView =
			// req.getRequestDispatcher("/evaluate.jsp");
			// failureView.forward(req, res);
			// }
		}	
		

		
		if ("displayOneEv".equals(actionFromMemJspOrEvJSP)) { // 來自member_information.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 *********************/
			
			String ord_no = req.getParameter("ord_no");
			OrdService ordSvc = new OrdService();			
			OrdVO ordVO = ordSvc.getOneOrd(ord_no);

			/****************************
			 * 2.修改完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("ordVO", ordVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/easyfood/front-end/class/member/evaluate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交member_informationt.jsp
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("修改資料失敗:" + e.getMessage());
			// RequestDispatcher failureView =
			// req.getRequestDispatcher("/evaluate.jsp");
			// failureView.forward(req, res);
			// }
		
		}
	}

}
