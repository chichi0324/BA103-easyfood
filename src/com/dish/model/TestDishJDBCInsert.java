package com.dish.model;

import java.io.IOException;

import com.stream.tool.ShareTool;

public class TestDishJDBCInsert {

	public static void main(String[] args) throws IOException {
		
		DishJDBCDAO dao = new DishJDBCDAO();
		DishVO vo = new DishVO();
		
		vo.setDish_name("川蜀麻辣鍋");
		vo.setDish_price(100.00);
		vo.setDcla_no("DCLA_0009");
		vo.setStr_no("STR_0040");
		vo.setDish_img(ShareTool.sendPicture("images/spicy.jpg"));
		vo.setDish_note("湯底嚴選十多種溫性中藥，再加入數種食材提味，搭配特選燈籠辣椒，融合麻辣鍋各門各派的作法，去蕪存菁後，研發出獨特的口味");
		dao.insert(vo);

	}

}
