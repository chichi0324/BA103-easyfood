package com.blre.model;

import com.tools.tools;

public class Test_findByPrimaryKey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlreJDBCDAO dao = new BlreJDBCDAO();
		
		// 單一查詢
		BlreVO blreVO3 = dao.findByPrimaryKey("BLRE_0019");
		System.out.print(blreVO3.getBlre_no() + ",");
		System.out.print(blreVO3.getBlre_con() + ",");
		System.out.print(blreVO3.getBl_no() + ",");
		System.out.print(blreVO3.getMem_no() + ",");
		System.out.print(new tools().timestampToString(blreVO3.getBlre_date()));
		System.out.println("---------------------");

	}

}
