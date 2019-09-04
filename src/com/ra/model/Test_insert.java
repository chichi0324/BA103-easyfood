package com.ra.model;

public class Test_insert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RaJDBCDAO dao = new RaJDBCDAO();
		// 新增
		RaVO raVO1 = new RaVO();
		raVO1.setBl_no("BL_0005");
		raVO1.setRa_res("不喜歡他的文章");
		raVO1.setRa_rev("不通過");
		dao.insert(raVO1);

	}

}
