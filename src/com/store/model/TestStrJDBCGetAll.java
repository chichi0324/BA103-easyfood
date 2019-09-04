package com.store.model;

import java.io.IOException;
import java.util.List;

public class TestStrJDBCGetAll {
	
	static String strNo = "";
	static String strName = "";

	public static void main(String[] args) throws IOException {
		
		int count = 0;
		StrJDBCDAO dao = new StrJDBCDAO();
		List<StrVO> vo = dao.getAll();
		
		for(StrVO obj : vo) {
			
			strNo = obj.getStr_no();
			strName = obj.getStr_name();
			
			byte [] pic = obj.getStr_img();
			TestStrJDBCGetOne.readPicture(pic);
			
			String text = obj.getStr_note();
			TestStrJDBCGetOne.readFile(text);
			
			
			System.out.print(strNo + " ");
			System.out.print(strName + " ");
			System.out.print(obj.getStr_cou() + " ");
			System.out.print(obj.getStr_city() + " ");
			System.out.print(obj.getStr_addr() + " ");
			System.out.print(obj.getStr_tel() + " ");
			System.out.print(obj.getStr_atn() + " ");
			System.out.print(obj.getStr_pre() + " ");
			System.out.print(obj.getStr_ship() + " ");
			System.out.print(obj.getStoca_no() + " ");
			System.out.print(obj.getStr_acc() + " ");
			System.out.print(obj.getStr_pas() + " ");
			System.out.print(obj.getStr_stat() + " ");
			System.out.print(obj.getStr_ma() + " ");
			System.out.print(obj.getStr_rep() + " ");
			System.out.print(obj.getStr_long() + " ");
			System.out.print(obj.getStr_lat() + " ");
			System.out.println();
			count++;
			
		}
		System.out.println("Total : " + count + " 筆");

	}

}
