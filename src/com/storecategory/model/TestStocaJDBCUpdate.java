package com.storecategory.model;

import java.io.IOException;

import com.stream.tool.ShareTool;

public class TestStocaJDBCUpdate {

	public static void main(String[] args) throws IOException {
		
		StocaJDBCDAO dao = new StocaJDBCDAO();
		StocaVO vo = new StocaVO();
		
		vo.setStoca_no("STOCA_0007");
		vo.setStoca_name("摩洛哥料理");
		
		byte [ ] pic = ShareTool.sendPicture("images/head2.png");
		vo.setStoca_img(pic);
		
		String article = ShareTool.sendInfo("info/india.txt");
		vo.setStoca_note(article);
		
		dao.update(vo);
		System.out.println("success");
		
	}

}
