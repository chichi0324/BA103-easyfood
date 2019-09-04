package com.dish.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.store.model.StrJDBCDAO;
import com.store.model.StrVO;

public class TestDishJDBCGet {
	
	static String dishNo = "";
	static String dishName = "";

	public static void main(String[] args) throws IOException {
		
		DishJDBCDAO dao = new DishJDBCDAO();
		
		
//		DishVO vo1  = dao.findByDishNo("DISH_0001");
//		dishNo = vo1.getDish_no();
//		dishName = vo1.getDish_name();
//		System.out.print(dishNo + " ");
//		System.out.print(dishName + " ");
//		System.out.print(vo1.getDish_price() + " ");
//		System.out.print(vo1.getDcla_no() + " ");
//		System.out.print(vo1.getStr_no() + " ");
//		System.out.print(vo1.getDish_status() + " ");
//		byte [] pic1 = vo1.getDish_img();
//		readDishPicture(pic1);
//		System.out.print(vo1.getDish_note() + " ");
//		
//		
//
//		int count2 = 0;
//		List<DishVO> vo2 = dao.findByDishClass("DCLA_0001");
//		for(DishVO obj2 : vo2) {
//			dishNo = obj2.getDish_no();
//			dishName = obj2.getDish_name();
//			System.out.print(dishNo + " ");
//			System.out.print(dishName + " ");
//			System.out.print(obj2.getDish_price() + " ");
//			System.out.print(obj2.getDcla_no() + " ");
//			System.out.print(obj2.getStr_no() + " ");
//			System.out.print(obj2.getDish_status() + " ");
//			byte[] pic2 = obj2.getDish_img();
//			readDishPicture(pic2);
//			System.out.print(obj2.getDish_note() + " ");
//			System.out.println();
//			count2++;
//		}
//		
//		System.out.println("Total count = " + count2 +" 筆");
//		
//		
//		
		int count3 = 0;
		List<DishVO> vo3 = dao.findByStore("STR_0040");
		for(DishVO obj3 : vo3) {
			String dish_no = obj3.getDish_no();
			System.out.print(dish_no + " ");
			System.out.print(obj3.getDish_name());
			System.out.print(obj3.getDish_price() + " ");
			System.out.print(obj3.getDcla_no() + " ");
			System.out.print(obj3.getStr_no() + " ");
			byte [] pic = obj3.getDish_img();
			readPicture(pic, dish_no);
			System.out.print(obj3.getDish_note() + " ");
			System.out.println();
			count3++;
		}
		System.out.println("Total count = " + count3 +" 筆");
//		
//	
//		int count4 = 0;
//		List<DishVO> vo4 = dao.findByPrice(185.00, 200.00);
//		for(DishVO obj4 : vo4) {
//			dishNo = obj4.getDish_no();
//			dishName = obj4.getDish_name();
//			System.out.print(dishNo + " ");
//			System.out.print(dishName + " ");
//			System.out.print(obj4.getDish_price() + " ");
//			System.out.print(obj4.getDcla_no() + " ");
//			System.out.print(obj4.getStr_no() + " ");
//			byte [] pic = obj4.getDish_img();
//			readDishPicture(pic);
//			System.out.print(obj4.getDish_note() + " ");
//			System.out.println();
//			count4++;
//		}
//		System.out.println("Total count = " + count4 + " 筆");
//		
//		
//		int count5 = 0;
//		List<DishVO> vo5 = dao.findByArea("新竹");
//		for(DishVO obj5 : vo5) {
//			dishNo = obj5.getDish_no();
//			dishName = obj5.getDish_name();
//			System.out.print(dishNo + " " + dishName + " ");
//			System.out.print(obj5.getDish_price() + " ");
//			System.out.print(obj5.getStr_no() + " ");
//			byte[] pic = obj5.getDish_img();
//			if(pic != null) {
//				readDishPicture(pic);
//			} else {
//				System.out.print("Noimg ");
//			}
//			String text = obj5.getDish_note();
//			if(text != null) {
//				readFile(text);
//			} else {
//				System.out.print("Nofile ");
//			}
//			
//			System.out.println();
//			count5++;
//		}
//		System.out.println("Total count = " + count5 + " 筆");
//		
//		
//		
//		
//		int count = 0;
//		List<DishVO> vo = dao.getALL();
//		for(DishVO obj : vo) {
//			dishNo = obj.getDish_no();
//			dishName = obj.getDish_name();
//			System.out.print(dishNo + " " + dishName + " ");
//			System.out.print(obj.getDish_price() + " ");
//			System.out.print(obj.getDcla_no() + " ");
//			System.out.print(obj.getStr_no() + " ");
//			byte[] pic = obj.getDish_img();
//			if(pic != null) {
//				readDishPicture(pic);
//			} else {
//				System.out.print("Noimg ");
//			}
//			String text = obj.getDish_note();
//			if(text != null) {
//				readFile(text);
//			} else {
//				System.out.print("Nofile ");
//			}
//			
//			System.out.println();
//			count++;
//		}
//		System.out.println("Total count = " + count + " 筆");
//		
//		List<String> vo6 = dao.findDishNameByStore("STR_0001");
//		for(String obj6 : vo6) {
//			System.out.println(obj6+ " ");
//		}
	}
	
	
	public static void readPicture(byte[] bytes) throws IOException {
		
		FileOutputStream out = null;
		if(bytes.length != 0) {
			out = new FileOutputStream("images/"+ dishNo + ".jpg");
			out.write(bytes);
			out.flush();
		}
		
		out.close();
		
	}
	public static void readPicture(byte[] bytes, String name) throws IOException {
		
		FileOutputStream out = null;
		if(bytes.length != 0) {
			out = new FileOutputStream("images/"+ name + ".jpg");
			out.write(bytes);
			out.flush();
		}
		
		out.close();
		
	}
	
	public static void readFile(String str) throws IOException {
		
		PrintWriter out = null;
		if(str.length() != 0) {
			out = new PrintWriter("show/" + dishName + ".txt");
			out.write(str);
			out.flush();
		}
		
		out.close();
		
	}

}
