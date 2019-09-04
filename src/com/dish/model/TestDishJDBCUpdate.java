package com.dish.model;

import java.io.IOException;

import com.stream.tool.ShareTool;

public class TestDishJDBCUpdate {

	public static void main(String[] args) throws IOException {
		
		DishJDBCDAO dao = new DishJDBCDAO();
		
//		DishVO vo1 = new DishVO();
//		vo1.setDish_no("DISH_0982");
//		vo1.setDish_name("酸菜白肉鍋");
//		vo1.setDish_price(120.00);
//		vo1.setDish_status("營業中");
//		vo1.setDish_img(ShareTool.sendPicture("images/sour.jpg"));
//		vo1.setDish_note("純手工醃製的酸白菜.酸香夠味.湯頭清酸帶甜 搭配辣湯. 酸辣的口味.征服您的味覺享受");
//		dao.update(vo1);
		

		
		dao.updateStatus("DISH_0862", "販售中");
		
	}

}
