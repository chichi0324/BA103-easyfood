package com.sys.model;

public class Test_insert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SysJDBCDAO dao = new SysJDBCDAO();
		// 查詢
		SysVO sysVO1 = new SysVO();
		sysVO1.setSys_con("非常謝謝大家的愛護～～我們將會推出更多方便的功能");
		dao.insert(sysVO1);

	}

}
