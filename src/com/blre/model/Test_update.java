package com.blre.model;

import com.tools.tools;

public class Test_update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlreJDBCDAO dao = new BlreJDBCDAO();
		
		// 修改		
		BlreVO blreVO2 = new BlreVO();
		blreVO2.setBlre_no("BLRE_0018");
		blreVO2.setBlre_con("這家難吃死了....");
		blreVO2.setBl_no("BL_0007");
		blreVO2.setMem_no("MEM_0002");
		blreVO2.setBlre_date(new tools().nowTimestamp());
		dao.update(blreVO2);

	}

}
