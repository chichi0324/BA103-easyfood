package com.fea.model;

public class TEst_delete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FeaJDBCDAO dao = new FeaJDBCDAO();


		// 刪除
		dao.delete("FEA_0009");

	}

}
