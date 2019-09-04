package com.blog.model;

import java.util.Set;

import com.blre.model.BlreVO;
import com.tools.tools;

public class Test_getBLREsByBlog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlogJDBCDAO dao = new BlogJDBCDAO();

		
		// 查詢單一文章所有回應
		Set<BlreVO> set = dao.getBLREsByBlog("BL_0005");
		for (BlreVO aBlre : set) {
			System.out.print(aBlre.getBlre_no() + ",");
			System.out.print(aBlre.getBlre_con() + ",");
			System.out.print(aBlre.getBl_no() + ",");
			System.out.print(aBlre.getMem_no() + ",");
			System.out.print(tools.timestampToString(aBlre.getBlre_date()));
			System.out.println();
		}

	}

}
