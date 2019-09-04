package com.str.model;

public class TestStrJDBCInsert {

	public static void main(String[] args) {
		
		StrJDBCDAO dao = new StrJDBCDAO();
		StrVO vo = new StrVO();
		
		vo.setStr_name("百花窯");
		vo.setStr_cou("新竹市");
		vo.setStr_city("東區");
		vo.setStr_addr("北大路186號");
		vo.setStr_tel("03-5247851");
		vo.setStr_atn("李瑞希");
		vo.setStr_pre(45);
		vo.setStr_ship("TRUE");
		vo.setStoca_no("STOCA_0008");
		vo.setStr_acc("flow815a");
		vo.setStr_pas("flow815b");
		vo.setStr_ma("raylee0815@gmail.com");
		vo.setStr_long(120.966276);
		vo.setStr_lat(24.808504);
		dao.insert(vo);

	}

}
