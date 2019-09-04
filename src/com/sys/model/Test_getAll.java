package com.sys.model;

import java.util.List;

public class Test_getAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SysJDBCDAO dao = new SysJDBCDAO();		
		
		// 查詢
		List<SysVO> list = dao.getAll();
		for (SysVO aSys : list) {
			System.out.print(aSys.getSys_no()+ ",");
			System.out.print(aSys.getSys_con());
			System.out.println();
		}

	}

}
