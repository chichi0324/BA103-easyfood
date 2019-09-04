package com.blog.model;

import java.text.ParseException;

import com.tools.tools;

public class Test_insert {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		BlogJDBCDAO dao = new BlogJDBCDAO();

		//新增
		BlogVO blogVO1 = new BlogVO();
		blogVO1.setBl_name("好吃的牛排");
		blogVO1.setBl_con("實在太好吃了,下次一定要再來");
		blogVO1.setBl_date(tools.nowTimestamp());
		//blogVO2.setBl_date(tools.strToTimestamp("2016-09-15 09:50:03"));
		blogVO1.setMem_no("MEM_0004");
   	    blogVO1.setStr_no("STR_0001");
		dao.insert(blogVO1);

	}

}
