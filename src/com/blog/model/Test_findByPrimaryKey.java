package com.blog.model;

import com.tools.tools;

public class Test_findByPrimaryKey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlogJDBCDAO dao = new BlogJDBCDAO();
		
		//查詢單一文章
		BlogVO blogVO3 = dao.findByPrimaryKey("BL_0003");
		System.out.print(blogVO3.getBl_no() + ",");
		System.out.print(blogVO3.getBl_name() + ",");
		System.out.print(blogVO3.getBl_con() + ",");
		System.out.print(tools.timestampToString(blogVO3.getBl_date())+ ",");
		System.out.print(blogVO3.getMem_no() + ",");
		System.out.print(blogVO3.getStr_no() + ",");
		System.out.println("---------------------");	

	}

}
