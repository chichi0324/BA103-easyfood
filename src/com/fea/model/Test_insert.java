package com.fea.model;

public class Test_insert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FeaJDBCDAO dao = new FeaJDBCDAO();

		// // 新增
		 FeaVO feaVO1 = new FeaVO();
		 feaVO1.setFea_name("管理平台");
		 dao.insert(feaVO1);

	}

}
