package com.blog.model;

public class Test_delete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlogJDBCDAO dao = new BlogJDBCDAO();
		
		// 刪除
		dao.delete("BL_0003");

	}

}
