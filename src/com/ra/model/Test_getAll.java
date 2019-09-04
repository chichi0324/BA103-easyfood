package com.ra.model;

import java.util.List;

public class Test_getAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RaJDBCDAO dao = new RaJDBCDAO();

		//查詢
		List<RaVO> list = dao.getAll();
		for (RaVO aRa : list) {
			System.out.print(aRa.getRa_no()+ ",");
			System.out.print(aRa.getBl_no()+ ",");
			System.out.print(aRa.getRa_res()+ ",");
			System.out.print(aRa.getRa_rev());
			System.out.println();
		}

	}

}
