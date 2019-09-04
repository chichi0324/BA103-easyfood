package com.ra.model;

public class Test_update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RaJDBCDAO dao = new RaJDBCDAO();
		
		// 修改
		RaVO raVO2 = new RaVO();
		raVO2.setRa_no("RA_0008");
		raVO2.setBl_no("BL_0023");
		raVO2.setRa_res("言論有點人身攻擊");
		raVO2.setRa_rev("通過");
		dao.update(raVO2);

	}

}
