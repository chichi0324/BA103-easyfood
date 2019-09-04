package com.blre.model;

public class Test_delete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlreJDBCDAO dao = new BlreJDBCDAO();		
		
		// 刪除
		dao.delete("BLRE_0009");

	}

}
