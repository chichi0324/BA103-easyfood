package com.str.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_findByMuti {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list1=new ArrayList<>();
		list1.add("台北");
		List<String> list2=new ArrayList<>();
//		list2.add("TRUE");
		List<String> list3=new ArrayList<>();
		list3.add("STOCA_0001");
		list3.add("STOCA_0002");
		List<String> list4=new ArrayList<>();
		list4.add("印度");
		
		Map<Integer, List<String>> map=new HashMap<Integer, List<String>>();
		map.put(0, list1);
		map.put(1, list2);
		map.put(2, list3);
		map.put(3, list4);
		
		
		StrJDBCDAO dao = new StrJDBCDAO();
		List<StrVO> vo = dao.findByMuti(map);
		
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
