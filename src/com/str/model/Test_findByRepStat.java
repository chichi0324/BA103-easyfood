package com.str.model;

import java.util.List;

public class Test_findByRepStat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StrJDBCDAO dao = new StrJDBCDAO();
		List<StrVO> vo = dao.findByRepStat(1, "營業中");

		for(StrVO obj : vo) {
			
			
			System.out.print(obj.getStr_no() + " ");
			System.out.print(obj.getStr_name() + " ");
			System.out.print(obj.getStr_cou() + " ");
			System.out.print(obj.getStr_city() + " ");
			System.out.print(obj.getStr_addr() + " ");
			System.out.print(obj.getStr_tel() + " ");
			System.out.print(obj.getStr_atn() + " ");
			System.out.print(obj.getStr_pre() + " ");
			System.out.print(obj.getStr_ship() + " ");
			System.out.print(obj.getStoca_no() + " ");
			System.out.print(obj.getStr_acc() + " ");
			System.out.print(obj.getStr_pas() + " ");
			System.out.print(obj.getStr_img() + " ");
			System.out.print(obj.getStr_stat() + " ");
			System.out.print(obj.getStr_ma() + " ");
			System.out.print(obj.getStr_rep() + " ");
			System.out.print(obj.getStr_long() + " ");
			System.out.print(obj.getStr_lat() + " ");
			System.out.println();
			
		}
	}

}
