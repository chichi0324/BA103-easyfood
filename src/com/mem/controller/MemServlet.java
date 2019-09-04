package com.mem.controller;

import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.io.IOUtils;
import com.mem.model.*;

//@WebServlet("/MemServlet")
@MultipartConfig(maxFileSize = 16177215)
public class MemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("login".equals(action)) { // 來自login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			String mem_acc = (String) req.getParameter("mem_acc");
			if (mem_acc == null || (mem_acc.trim()).length() == 0) {
				errorMsgs.add("請輸入會員帳號");
			}

			String mem_pw = (String) req.getParameter("mem_pw");
			if (mem_pw == null || (mem_pw.trim()).length() == 0) {
				errorMsgs.add("請輸入會員密碼");
			}
			MemVO memVO = new MemVO();
			memVO.setMem_acc(mem_acc);
			memVO.setMem_pw(mem_pw);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			MemService memSvc = new MemService();

			// 帳號驗證
			boolean accIsRight = false;
			List<MemVO> memlist = memSvc.getAll();
			for (int i = 0; i < memlist.size(); i++) {
				MemVO amemVO = memlist.get(i);
				if (amemVO.getMem_acc().equals(mem_acc)) {
					accIsRight = true;
				}
			}
			if (!accIsRight) {
				errorMsgs.add("帳號錯誤");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			String memVO_no_priv=memSvc.findMem_noByMemAcc(mem_acc).getMem_stas();
			if("停權".equals(memVO_no_priv)){
				errorMsgs.add("帳號暫時被停權");
			}

			// 密碼驗證
			String mem_no = memSvc.getOneMem_no(mem_acc);
			MemVO memVO1 = memSvc.getOneMem(mem_no);
			boolean pwIsRight = false;
			if (memVO1.getMem_pw().equals(mem_pw)) {
				pwIsRight = true;
			}
			if (!pwIsRight) {
				errorMsgs.add("密碼錯誤");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/

			HttpSession session = req.getSession();
			session.setAttribute("mem_acc", mem_acc);
			session.setAttribute("memVO", memVO1); // 資料庫取出的memVO物件,存入session



			if (memVO1.getMem_img() != null) {
				byte[] mem_img = memVO1.getMem_img(); // 取得圖片Byte[]
				String showImgByBase64 = Base64.getMimeEncoder().encodeToString(mem_img);
				session.setAttribute("showImgByBase64", showImgByBase64);
			}
			
			try {
				String front_location = (String) session.getAttribute("front_location");
				if (front_location != null) {
					session.removeAttribute("front_location");
					res.sendRedirect(front_location);
					//System.out.println("location :"+front_location);
					return;
				}
			} catch (Exception ignored) {
			}
			

			
			session.setAttribute("navbar_select", "member");

			String url = "/easyfood/front-end/class/member/member.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // listOneMem.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView =
			// req.getRequestDispatcher("/login.jsp");
			// failureView.forward(req, res);
			// }
		}
		
        if ("front_logout".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 檢查輸入資料是否空白*************************/
				HttpSession session =req.getSession();
				session.removeAttribute("memVO");
			    session.removeAttribute("navbar_select");	
			    session.removeAttribute("front_location");
			    session.removeAttribute("shoppingcart");
			    session.removeAttribute("showImgByBase64");
				res.sendRedirect(req.getContextPath() + "/easyfood/front-end/login.jsp");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/front-end/login.jsp");
				failureView.forward(req, res);
			}
		}

		// if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
		//
		// List<String> errorMsgs = new LinkedList<String>();
		// // Store this set in the request scope, in case we need to
		// // send the ErrorPage view.
		// req.setAttribute("errorMsgs", errorMsgs);
		//
		// try {
		// /***************************1.�����ШD�Ѽ�****************************************/
		// Integer empno = new Integer(req.getParameter("empno"));
		//
		// /***************************2.�}�l�d�߸��****************************************/
		// EmpService empSvc = new EmpService();
		// EmpVO empVO = empSvc.getOneEmp(empno);
		//
		// /***************************3.�d�ߧ���,�ǳ����(Send the Success
		// view)************/
		// req.setAttribute("empVO", empVO); // ��Ʈw���X��empVO����,�s�Jreq
		// String url = "/emp/update_emp_input.jsp";
		// RequestDispatcher successView = req.getRequestDispatcher(url);//
		// ���\��� update_emp_input.jsp
		// successView.forward(req, res);
		//
		// /***************************��L�i�઺���~�B�z**********************************/
		// } catch (Exception e) {
		// errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
		// RequestDispatcher failureView = req
		// .getRequestDispatcher("/emp/listAllEmp.jsp");
		// failureView.forward(req, res);
		// }
		// }

		if ("update".equals(action)) { // 來自member_information.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String mem_pw = req.getParameter("mem_pw").trim();
				if (mem_pw == null || (mem_pw.trim()).length() == 0) {
					errorMsgs.add("密碼不能空白");
				}
				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || (mem_name.trim()).length() == 0) {
					errorMsgs.add("姓名不能空白");
				}
				String mem_pho = req.getParameter("mem_pho").trim();
				if (mem_pho == null || (mem_pho.trim()).length() == 0) {
					errorMsgs.add("手機號碼不能空白");
				}
				String mem_mail = req.getParameter("mem_mail").trim();
				if (mem_mail == null || (mem_mail.trim()).length() == 0) {
					errorMsgs.add("Mail不能空白");
				}

				/*************************** 更新圖片處理 *****************************************/
				HttpSession session = req.getSession();
				InputStream inputStream = null;
				byte[] mem_img;
				String showImgByBase64 = null;

				Part filePart = req.getPart("mem_img");
				if (filePart.getSize() != 0) {
					inputStream = filePart.getInputStream();
					mem_img = IOUtils.toByteArray(inputStream);
					showImgByBase64 = Base64.getMimeEncoder().encodeToString(mem_img);
					session.setAttribute("showImgByBase64", showImgByBase64);
				} else {
					MemVO memVO2 = (MemVO) session.getAttribute("memVO");
					mem_img = memVO2.getMem_img();
				}
				/*************************** 更新圖片處理結束 *****************************************/

				// 從資料庫取得mem_no by mem_acc
				String mem_acc = req.getParameter("mem_acc").trim();
				MemService memSvc1 = new MemService();
				String mem_no = memSvc1.getOneMem_no(mem_acc);

				MemVO memVO = new MemVO();
				memVO.setMem_pw(mem_pw);
				memVO.setMem_name(mem_name);
				memVO.setMem_pho(mem_pho);
				memVO.setMem_mail(mem_mail);
				memVO.setMem_no(mem_no);
				memVO.setMem_acc(mem_acc);
				memVO.setMem_img(mem_img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_information.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemService memSvc2 = new MemService();
				memVO = memSvc2.updateMem(mem_pw, mem_name, mem_pho, mem_mail, mem_no, mem_acc, mem_img);

				/****************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				session.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req

				errorMsgs.add("修改成功");
				String url = "/easyfood/front-end/class/member/member_information.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交member_informationt.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/class/member/member_information.jsp");
				failureView.forward(req, res);
			}
		}

		if ("register".equals(action)) { // 來自registerMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String mem_acc = req.getParameter("mem_acc").trim();
				if (mem_acc == null || (mem_acc.trim()).length() == 0) {
					errorMsgs.add("請輸入會員帳號");
				}
				
				// 帳號有無被使用驗證
				MemService memSvc = new MemService();				
				List<MemVO> memlist = memSvc.getAll();
				for (int i = 0; i < memlist.size(); i++) {
					MemVO amemVO = memlist.get(i);
					if (amemVO.getMem_acc().equals(mem_acc)) {
						errorMsgs.add("此帳號已被使用");
					}
				}

				String mem_pw = req.getParameter("mem_pw").trim();
				if (mem_pw == null || (mem_pw.trim()).length() == 0) {
					errorMsgs.add("請輸入會員密碼");
				//	密碼少於6碼驗證
				}else if((mem_pw.trim()).length()<6){
					errorMsgs.add("密碼不得少於6碼");
				}
				
				String mem_pw_confirm = req.getParameter("mem_pw_confirm").trim();
				if (mem_pw_confirm == null || (mem_pw_confirm.trim()).length() == 0) {
					errorMsgs.add("請輸入確認密碼");
				}
				
				//確認密碼與密碼有無相符驗證
				if(!mem_pw.equals(mem_pw_confirm)){
					errorMsgs.add("密碼與確認密碼不符");
				}

				String mem_name = req.getParameter("mem_name").trim();
				if (mem_name == null || (mem_name.trim()).length() == 0) {
					errorMsgs.add("請輸入會員姓名");
				}

				String mem_pho = req.getParameter("mem_pho").trim();
				if (mem_pho == null || (mem_pho.trim()).length() == 0) {
					errorMsgs.add("請輸入會員手機號碼");
				}

				String mem_mail = req.getParameter("mem_mail").trim();
				if (mem_mail == null || (mem_mail.trim()).length() == 0) {
					errorMsgs.add("請輸入會員Mail");
				}

				/*************************** 上傳圖片處理 *****************************************/

				InputStream inputStream = null;
				Part filePart = req.getPart("mem_img");
				inputStream = filePart.getInputStream();
				byte[] mem_img = IOUtils.toByteArray(inputStream);
				String showImgByBase64 = Base64.getMimeEncoder().encodeToString(mem_img);

				/*************************** 上傳圖片處理結束 *****************************************/

				MemVO memVO = new MemVO();
				memVO.setMem_acc(mem_acc);
				memVO.setMem_pw(mem_pw);
				memVO.setMem_name(mem_name);
				memVO.setMem_pho(mem_pho);
				memVO.setMem_mail(mem_mail);
				memVO.setMem_img(mem_img);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/registerMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				memVO = memSvc.addMem(mem_acc, mem_pw, mem_name, mem_pho, mem_mail, mem_img);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String mem_no = memSvc.getOneMem_no(mem_acc);
				memVO.setMem_no(mem_no);

				String url = "/easyfood/front-end/class/member/member.jsp";
				// req.setAttribute("memVO", memVO);
				// 將memVO放進session
				HttpSession session = req.getSession();
				session.setAttribute("mem_acc", mem_acc);
				session.setAttribute("memVO", memVO);
				session.setAttribute("showImgByBase64", showImgByBase64);
				session.setAttribute("navbar_select", "member");
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}
				
				session.setAttribute("navbar_select", "member");
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/easyfood/front-end/registerMem.jsp");
				failureView.forward(req, res);
			}
		}
		//
		//
		// if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
		//
		// List<String> errorMsgs = new LinkedList<String>();
		// // Store this set in the request scope, in case we need to
		// // send the ErrorPage view.
		// req.setAttribute("errorMsgs", errorMsgs);
		//
		// try {
		// /***************************1.�����ШD�Ѽ�***************************************/
		// Integer empno = new Integer(req.getParameter("empno"));
		//
		// /***************************2.�}�l�R�����***************************************/
		// EmpService empSvc = new EmpService();
		// empSvc.deleteEmp(empno);
		//
		// /***************************3.�R������,�ǳ����(Send the Success
		// view)***********/
		// String url = "/emp/listAllEmp.jsp";
		// RequestDispatcher successView = req.getRequestDispatcher(url);//
		// �R�����\��,���^�e�X�R�����ӷ�����
		// successView.forward(req, res);
		//
		// /***************************��L�i�઺���~�B�z**********************************/
		// } catch (Exception e) {
		// errorMsgs.add("�R����ƥ���:"+e.getMessage());
		// RequestDispatcher failureView = req
		// .getRequestDispatcher("/emp/listAllEmp.jsp");
		// failureView.forward(req, res);
		// }
		// }
	}

}
