package com.adm.model;

public class Test_update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AdmJDBCDAO dao = new AdmJDBCDAO();

		 //修改
		 AdmVO admVO2 = new AdmVO();
		 admVO2.setAdm_no("ADM_0031");
		 admVO2.setAdm_acc("QQQ");
		 admVO2.setAdm_pas("QQQ");
		 admVO2.setAdm_name("QQQ");
		 admVO2.setAdm_adrs("QQQ");
		 admVO2.setAdm_pho("QQQ");
		 admVO2.setAdm_pos("QQQ");
		 admVO2.setAdm_mail("123");
		 dao.update(admVO2);

	}

}
