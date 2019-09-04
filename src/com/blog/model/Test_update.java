package com.blog.model;

import com.tools.tools;

public class Test_update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlogJDBCDAO dao = new BlogJDBCDAO();
		
		//修改
		BlogVO blogVO2 = new BlogVO();
		blogVO2.setBl_no("BL_0030");
		blogVO2.setBl_name("美味火鍋大大滿足");
		blogVO2.setBl_con("好久沒吃這好吃的火鍋,養顏美容,熱量又低,口感又好,幸福又美味~~~");
		blogVO2.setBl_date(tools.nowTimestamp());
		//blogVO2.setBl_date(tools.strToTimestamp("2016-09-15 09:50:03"));
		blogVO2.setMem_no("MEM_0001");
    	blogVO2.setStr_no("STR_0001");
		dao.update(blogVO2);

	}

}
