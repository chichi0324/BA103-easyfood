package com.sys.model;

public class Test_delete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SysJDBCDAO dao = new SysJDBCDAO();		
		
		// 刪除
		dao.delete("SYS_0002");

	}

}
