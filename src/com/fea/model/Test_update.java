package com.fea.model;

public class Test_update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FeaJDBCDAO dao = new FeaJDBCDAO();


		 //修改
		 FeaVO feaVO2 = new FeaVO();
		 feaVO2.setFea_no("FEA_0009");
		 feaVO2.setFea_name("QQQ");
		 dao.update(feaVO2);

	}

}
