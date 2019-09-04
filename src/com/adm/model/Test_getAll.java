package com.adm.model;

import java.util.List;

public class Test_getAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AdmJDBCDAO dao = new AdmJDBCDAO();
		// 查詢全部
		List<AdmVO> list = dao.getAll();
		for (AdmVO admVO : list) {
			System.out.print(admVO.getAdm_no() + ",");
			System.out.print(admVO.getAdm_acc() + ",");
			System.out.print(admVO.getAdm_pas() + ",");
			System.out.print(admVO.getAdm_name() + ",");
			System.out.print(admVO.getAdm_adrs() + ",");
			System.out.print(admVO.getAdm_pho() + ",");
			System.out.print(admVO.getAdm_pos() + ",");
			System.out.print(admVO.getAdm_mail() + ",");
			System.out.println();
		}

	}

}
