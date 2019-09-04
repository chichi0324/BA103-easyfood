package com.blog.model;

import java.util.List;

import com.tools.tools;

public class Test_findByKeyword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlogJDBCDAO dao = new BlogJDBCDAO();
		// 查詢會員全部資料
		List<BlogVO> list = dao.findByKeyword("桃園");
		for (BlogVO aBlog : list) {
			System.out.print(aBlog.getBl_no() + ",");
			System.out.print(aBlog.getBl_name() + ",");
			System.out.print(aBlog.getBl_con() + ",");
			System.out.print(tools.timestampToString(aBlog.getBl_date()) + ",");
			System.out.print(aBlog.getMem_no() + ",");
			System.out.print(aBlog.getStr_no() + ",");
			System.out.println();
		}
	}

}
