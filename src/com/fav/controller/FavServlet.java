package com.fav.controller;

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

import com.fav.model.FavService;
import com.fav.model.FavVO;
import com.mem.model.MemVO;

public class FavServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String mem_no = req.getParameter("mem_no");
//		System.out.println(action);
//		System.out.println(mem_no);
		
	if("getAll_For_Fav".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			FavService favSvc = new FavService();
			List<FavVO> favVO = favSvc.getoneFav(mem_no);
			
			if(favVO == null){
				errorMsgs.add("查無最愛店家");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/front-end/class/member/member_favorite.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("favVO", favVO); // 資料庫取出的favVO物件,存入req
			String url = "/easyfood/front-end/class/member/member_favorite.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
	if("delete_For_Fav".equals(action)){
		List<String> errorMsgs = new LinkedList<String>();
		
		req.setAttribute("errorMsgs", errorMsgs);
		
		try{
			String str_no = new String(req.getParameter("str_no"));
			
			HttpSession session=req.getSession();
			MemVO memVO = (MemVO) session.getAttribute("memVO");
            String login_mem_no=memVO.getMem_no();
			
			FavService favSvc = new FavService();
			favSvc.deleteFav(login_mem_no,str_no);
			
			String url = "/easyfood/front-end/class/member/member_favorite.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		} catch(Exception e){
			errorMsgs.add("刪除最愛店家失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/easyfood/front-end/class/member/member_favorite.jsp");
			failureView.forward(req, res);
		}
		
	}
	
	if("insert_For_Fav".equals(action)){
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		String str_no="";
		
		try{
			str_no = new String(req.getParameter("str_no"));			
			FavVO favVO = new FavVO();
					
			
			favVO.setMem_no(mem_no);
			favVO.setStr_no(str_no);
			
			FavService favSvc = new FavService();
			
			List<FavVO> favVOList=favSvc.getoneFav(mem_no);
			List<String> fav_str=new ArrayList<String>();
			for(int i=0;i<favVOList.size();i++){
				fav_str.add(favVOList.get(i).getStr_no());
			}
			if(!fav_str.contains(str_no)){
				favVO = favSvc.addFav(mem_no, str_no);
			}
			
			
			
			
			
			
			}catch(Exception e){
			
			errorMsgs.add("已經加入我的最愛");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/easyfood/front-end/class/search/search.do?str_no="+str_no+"&select=introduce&action=enter_store_select");
			failureView.forward(req, res);
			
			}
			
		
					
			String url = "/easyfood/front-end/class/search/search.do?str_no="+str_no+"&select=introduce&action=enter_store_select";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);	
			
			
		
	}
	
}
	
	
	
	
	
	}