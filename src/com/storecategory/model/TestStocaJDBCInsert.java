package com.storecategory.model;

import java.io.IOException;

import com.stream.tool.ShareTool;

public class TestStocaJDBCInsert {

	public static void main(String[] args) throws IOException {
		
		
		StocaJDBCDAO dao = new StocaJDBCDAO();
		StocaVO vo = new StocaVO();
		
		vo.setStoca_name("中式料理");

		byte[ ] pic = ShareTool.sendPicture("images/head1.png");
		vo.setStoca_img(pic);
		
		String info = ShareTool.sendInfo("info/chinese.txt");
		vo.setStoca_note(info);
		
		dao.insert(vo);
		System.out.println("success");
	}
	
	
	
}
