package com.storecategory.model;

import java.io.IOException;

import com.stream.tool.ShareTool;


public class TestStocaJDBCGetOne {
	
	public static String path = "";
	public static String name = "";

	public static void main(String[] args) throws IOException {
		
		StocaJDBCDAO dao = new StocaJDBCDAO();
		StocaVO vo = dao.findByPrimaryKey("STOCA_0008");
		
		path = vo.getStoca_no();
		name = vo.getStoca_name();
		System.out.println( path + " " + name);
		
		byte[] pic = vo.getStoca_img();
		ShareTool.readPicture(pic);
		
		
		String text = "";
		if(text.length() != 0) {
			text = vo.getStoca_note();
			ShareTool.readFile(text);
		}
		
		
		System.out.println("success");
		
	}

}
