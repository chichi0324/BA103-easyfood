package com.ra.model;

public class Test_delete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RaJDBCDAO dao = new RaJDBCDAO();
		// 刪除
        dao.delete("RA_0013");

	}

}
