package com.blre.model;

import com.tools.tools;

public class Test_insert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlreJDBCDAO dao = new BlreJDBCDAO();

		// 新增
		BlreVO blreVO1 = new BlreVO();
		blreVO1.setBlre_con("很詳細的介紹文,看的我都流口水了");
		blreVO1.setBl_no("BL_0004");
		blreVO1.setMem_no("MEM_0001");
		blreVO1.setBlre_date(tools.nowTimestamp());
//		blreVO1.setBlre_date(tools.strToTimestamp("2014-09-23 21:34:12"));
		dao.insert(blreVO1);

	}

}
