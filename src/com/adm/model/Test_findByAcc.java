package com.adm.model;

public class Test_findByAcc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AdmJDBCDAO dao = new AdmJDBCDAO();
		
		// 查詢單筆
		AdmVO admVO3 = dao.findByAcc("CHICHI");
		System.out.print(admVO3.getAdm_no() + ",");
		System.out.print(admVO3.getAdm_acc() + ",");
		System.out.print(admVO3.getAdm_pas() + ",");
		System.out.print(admVO3.getAdm_name() + ",");
		System.out.print(admVO3.getAdm_adrs() + ",");
		System.out.print(admVO3.getAdm_pho() + ",");
		System.out.print(admVO3.getAdm_pos() + ",");
		System.out.print(admVO3.getAdm_mail() + ",");

		System.out.println("---------------------");

	}

}
