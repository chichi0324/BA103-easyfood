package com.fea.model;

import java.util.List;

public class Teat_getAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FeaJDBCDAO dao = new FeaJDBCDAO();

		// 查詢全部
		List<FeaVO> list = dao.getAll();
		for (FeaVO feaVO : list) {
			System.out.print(feaVO.getFea_no() + ",");
			System.out.print(feaVO.getFea_name() + ",");
			System.out.println();
		}
	}

}
