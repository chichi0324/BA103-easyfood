package com.storecategory.model;
import java.io.IOException;
import java.util.List;

import com.stream.tool.ShareTool;

public class TestStocaJDBCGetAll {

	public static void main(String[] args) throws IOException {
		
		int count = 0;
		StocaJDBCDAO dao = new StocaJDBCDAO();
		List<StocaVO> vo = dao.getAll();
		
		for(StocaVO obj : vo) {
			System.out.println(obj.getStoca_no() +" " + obj.getStoca_name());
			ShareTool.readPicture(obj.getStoca_img());
			System.out.println(obj.getStoca_note());
			count++;
		}
		System.out.println("total data : " + count);

	}

}
