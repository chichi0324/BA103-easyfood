package com.adm.model;

public class Test_delete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AdmJDBCDAO dao = new AdmJDBCDAO();


		// 刪除
		dao.delete("ADM_0031");

	}

}
