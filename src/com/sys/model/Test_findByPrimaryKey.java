package com.sys.model;

public class Test_findByPrimaryKey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SysJDBCDAO dao = new SysJDBCDAO();	
		// 單筆查詢
		SysVO sysVO3 = dao.findByPrimaryKey("SYS_0006");
		System.out.print(sysVO3.getSys_no() + ",");
		System.out.print(sysVO3.getSys_con());
		System.out.println("---------------------");

	}

}
