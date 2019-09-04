package com.dishclass.model;

import java.util.List;

public class TestDclaJDBCAll {

	public static void main(String[] args) {
		
		DclaJDBCDAO dao = new DclaJDBCDAO();
		
		DclaVO vo1 = new DclaVO();
		vo1.setDcla_name("野味蕈菇");
		dao.insert(vo1);
		
//		DclaVO vo2 = new DclaVO();
//		vo2.setDcla_name("嚴選高湯");
//		vo2.setDcla_no("DCLA_0008");
//		dao.update(vo2);
//		
//		dao.delete("DCLA_0008");
//		
//		DclaVO vo3 = dao.findByPrimaryKey("DCLA_0002");
//		System.out.println(vo3.getDcla_no() + " : " + vo3.getDcla_name());
//		
//		int count = 0;
//		List<DclaVO> vo4 = dao.getALL();
//		
//		for(DclaVO dclaVO : vo4) {
//			System.out.println(dclaVO.getDcla_no() + " : " + dclaVO.getDcla_name());
//			count++;
//		}
//		
//		System.out.println("total count = " + count);
	}

}
