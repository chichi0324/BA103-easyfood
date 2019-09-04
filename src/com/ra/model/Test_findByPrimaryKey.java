package com.ra.model;

public class Test_findByPrimaryKey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RaJDBCDAO dao = new RaJDBCDAO();
		
		//單筆查詢
		RaVO raVO3 = dao.findByPrimaryKey("RA_0012");
		System.out.print(raVO3.getRa_no() + ",");
		System.out.print(raVO3.getBl_no() + ",");
		System.out.print(raVO3.getRa_res() + ",");
		System.out.print(raVO3.getRa_rev() + ",");
		System.out.println("---------------------");

	}

}
