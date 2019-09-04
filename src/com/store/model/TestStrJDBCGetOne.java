package com.store.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class TestStrJDBCGetOne {
	
	static String strNo ="";
	static String strName ="";

	public static void main(String[] args) throws IOException {
		
		StrJDBCDAO dao = new StrJDBCDAO();
//		StrVO vo = dao.findByPrimaryKey("STR_0001");
//		
//		strNo = vo.getStr_no();
//		strName = vo.getStr_name();
//		System.out.println(strNo);
//		System.out.println(strName);
//		
//		byte[] pic = vo.getStr_img();
//		readDishPicture(pic);
//		
//		String text = vo.getStr_note();
//		readFile(text);
//		
//		System.out.println(vo.getStr_cou());
//		System.out.println(vo.getStr_city());
//		System.out.println(vo.getStr_addr());
//		System.out.println(vo.getStr_tel());
//		System.out.println(vo.getStr_atn());
//		System.out.println(vo.getStr_pre());
//		System.out.println(vo.getStr_ship());
//		System.out.println(vo.getStoca_no());
//		System.out.println(vo.getStr_acc());
//		System.out.println(vo.getStr_pas());
//		System.out.println(vo.getStr_stat());
//		System.out.println(vo.getStr_ma());
//		System.out.println(vo.getStr_rep());
//		System.out.println(vo.getStr_long());
//		System.out.println(vo.getStr_lat());
		
		
		List<StrVO> vo = dao.findByArea("新竹");
		for(StrVO obj : vo) {
			System.out.print(obj.getStr_no() + " ");
			System.out.print(obj.getStr_name() + " ");
			System.out.print(obj.getStr_cou()+obj.getStr_city()+obj.getStr_addr() + " ");
			System.out.print(obj.getStr_atn() + " ");
			System.out.print(obj.getStr_tel() + " ");
			System.out.print(obj.getStr_stat() + " ");
			System.out.print(obj.getStr_ship() + " ");
			System.out.print(obj.getStr_long() + " ");
			System.out.print(obj.getStr_lat() + " ");
			System.out.println();
		}

	}
	
	public static void readPicture(byte[] bytes) throws IOException {
		
		FileOutputStream out = null;
		if(bytes.length != 0) {
			out = new FileOutputStream("show/"+ TestStrJDBCGetOne.strNo + ".png");
			out.write(bytes);
			out.flush();
		}
		
		out.close();
		
	}
	
	public static void readFile(String str) throws IOException {
		
		PrintWriter out = null;
		if(str.length() != 0) {
			out = new PrintWriter("show/" + TestStrJDBCGetOne.strName + ".txt");
			out.write(str);
			out.flush();
		}
		
		out.close();
		
	}

}
