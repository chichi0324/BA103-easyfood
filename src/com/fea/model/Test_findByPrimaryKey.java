package com.fea.model;

public class Test_findByPrimaryKey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FeaJDBCDAO dao = new FeaJDBCDAO();


		// 查詢單筆
		FeaVO feaVO3 = dao.findByPrimaryKey("FEA_0001");
		System.out.print(feaVO3.getFea_no() + ",");
		System.out.print(feaVO3.getFea_name() + ",");

		System.out.println("---------------------");

	}

}
