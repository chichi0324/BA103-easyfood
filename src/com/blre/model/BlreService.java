package com.blre.model;

import java.util.List;

import com.tools.tools;


public class BlreService {
	
	private BlreDAO_interface dao;

	public BlreService() {
		dao = new BlreDAO();
	}

	public BlreVO addBlre(String blre_con, String bl_no, 
			String mem_no) {

		// 新增
		BlreVO blreVO1 = new BlreVO();
		blreVO1.setBlre_con(blre_con);
		blreVO1.setBl_no(bl_no);
		blreVO1.setMem_no(mem_no);
		blreVO1.setBlre_date(new tools().nowTimestamp());
		dao.insert(blreVO1);

		return blreVO1;
	}

	public BlreVO updateBlre(String blre_no, String blre_con, String bl_no, 
			String mem_no) {
		
		// 修改		
		BlreVO blreVO2 = new BlreVO();
		blreVO2.setBlre_no(blre_no);
		blreVO2.setBlre_con(blre_con);
		blreVO2.setBl_no(bl_no);
		blreVO2.setMem_no(mem_no);
		blreVO2.setBlre_date(new tools().nowTimestamp());
		dao.update(blreVO2);

		return blreVO2;
	}

	public void deleteBlre(String blre_no) {
		dao.delete(blre_no);
	}

	public BlreVO findByPrimaryKey(String blre_no) {
		return dao.findByPrimaryKey(blre_no);
	}
	
	public List<BlreVO> findByMember(String mem_no){
		return dao.findByMember(mem_no);
	}

	public List<BlreVO> getAll() {
		return dao.getAll();
	}

}
