package com.adm.model;

public class Test_insert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AdmJDBCDAO dao = new AdmJDBCDAO();

		// // 新增
		 AdmVO admVO1 = new AdmVO();
		 admVO1.setAdm_acc("Andy");
		 admVO1.setAdm_pas("123456");
		 admVO1.setAdm_name("安迪");
		 admVO1.setAdm_adrs("新北市三峽區肝樹路二巷82號4樓");
		 admVO1.setAdm_pho("0988675456");
		 admVO1.setAdm_pos("檢舉管理員");
		 admVO1.setAdm_mail("j43343@gmail.com");
		 dao.insert(admVO1);

	}

}
