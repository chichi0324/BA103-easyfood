package com.blog.model;

import java.util.Set;

import com.ra.model.RaVO;

public class Test_getRAsByBlog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlogJDBCDAO dao = new BlogJDBCDAO();

		
		// 查詢單一文章所有檢舉
		Set<RaVO> set2 = dao.getRAsByBlog("BL_0005");
		for (RaVO aRa : set2) {
			System.out.print(aRa.getRa_no() + ",");
			System.out.print(aRa.getBl_no() + ",");
			System.out.print(aRa.getRa_res() + ",");
			System.out.print(aRa.getRa_rev());
			System.out.println();
		}

	}

}
