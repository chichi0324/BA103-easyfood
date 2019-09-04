package com.ordit.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cartitem.model.CartItemVO;
import com.cartorder.model.CartOrderVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.ordit.model.OrditService;
import com.ordit.model.OrditVO;

/**
 * Servlet implementation class OrtitServlet
 */
@WebServlet("/OrtitServlet")
public class OrtitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = (String) req.getAttribute("action");
		if ("addOrdit".equals(action)) { // 來自OrdServlet的請求

			CartOrderVO CartOrder = (CartOrderVO) req.getAttribute("CartOrder");
			HttpSession session = req.getSession();
			MemVO memVO = (MemVO) session.getAttribute("memVO");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***********************
			 * * 1.接收請求參數 - 輸入格式的錯誤處理
			 *************************/
			String mem_no = memVO.getMem_no();
			String ord_no = CartOrder.getOrd_no();
			Vector<CartItemVO> buylist = CartOrder.getBuylist();
			for (int i = 0; i < buylist.size(); i++) {
				CartItemVO CartItem = buylist.get(i);
				String dish_no = CartItem.getDish_no();
				Integer ordit_qua = CartItem.getQuantity();
				Double ordit_pri = CartItem.getDish_price();
				OrditVO orditVO = new OrditVO();
				orditVO.setOrd_no(ord_no);
				orditVO.setDish_no(dish_no);
				orditVO.setOrdit_qua(ordit_qua);
				orditVO.setOrdit_pri(ordit_pri);
				/*************************** 2.開始新增資料 ***************************************/
				OrditService orditSvc = new OrditService();
				orditSvc.addOrdit(ord_no, dish_no, ordit_qua, ordit_pri);
			}

			MemService memSvc = new MemService();
			memVO = memSvc.getOneMem(mem_no);

			/***************************
			 * 3.新增完成,準備轉交(Send the Success view)
			 ***********/
			String url = "/easyfood/front-end/class/member/member.jsp";
			session = req.getSession();
			session.setAttribute("memVO", memVO); //
			// 將memVO放進session
			System.out.println("OrtitServlet memVO : " + memVO);
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
			// } catch (Exception e) {
			// errorMsgs.add(e.getMessage());
			// RequestDispatcher failureView =
			// req.getRequestDispatcher("/shoppingCart.jsp");
			// failureView.forward(req, res);
			// }
		}

	}

}
