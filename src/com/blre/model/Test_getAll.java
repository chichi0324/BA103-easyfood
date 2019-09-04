package com.blre.model;

import java.util.List;

import com.tools.tools;

public class Test_getAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlreJDBCDAO dao = new BlreJDBCDAO();
		
		// 查詢
		List<BlreVO> list = dao.getAll();
		for (BlreVO aBlre : list) {
			System.out.print(aBlre.getBlre_no()+ ",");
			System.out.print(aBlre.getBlre_con() + ",");
			System.out.print(aBlre.getBl_no() + ",");
			System.out.print(aBlre.getMem_no() + ",");
			System.out.print(new tools().timestampToString(aBlre.getBlre_date()));
			System.out.println();
		}

	}

}
