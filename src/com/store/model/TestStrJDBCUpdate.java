package com.store.model;

import java.io.IOException;

import com.stream.tool.ShareTool;

public class TestStrJDBCUpdate {

	public static void main(String[] args) throws IOException {
		
		StrJDBCDAO dao = new StrJDBCDAO();
		
//		StrVO vo1 = new StrVO();
//		vo1.setStr_no("STR_0040");
//		vo1.setStr_cou("新竹市");
//		vo1.setStr_city("東區");
//		vo1.setStr_addr("北大路201號");
//		vo1.setStr_tel("03-5247851");
//		vo1.setStr_atn("李滿滿");
//		vo1.setStr_pre(15);
//		vo1.setStr_ship("TRUE");
//		vo1.setStr_ma("raylee0815@gmail.com");
//		vo1.setStr_long(120.00);
//		vo1.setStr_lat(24.99);
//		byte [] pic = ShareTool.sendPicture("images/head.png");
//		vo1.setStr_img(pic);
//		String note = ShareTool.sendInfo("info/info.txt");
//		vo1.setStr_note(note);
//		dao.update(vo1);
		
		
		StrVO vo2 = new StrVO();
		byte [] pic = ShareTool.sendPicture("images/card head 1.png");
		vo2.setStr_img(pic);
		String text = ShareTool.sendInfo("info/chinese.txt");
		vo2.setStr_note(text);
		vo2.setStr_no("STR_0040");
		dao.updateInfo(vo2);
//		
//		dao.updatePas("STR_0040", "flow815A");
//		
//		dao.updateStatus("STR_0040", "營業中");

	}

}
