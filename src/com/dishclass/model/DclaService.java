package com.dishclass.model;

import java.util.List;

public class DclaService {
	
	public DclaDAO_interface dao;

	public DclaService() {
		dao = new DclaDAO();
	}
	
	public DclaVO addDcla (String dcla_name) {
		DclaVO dclaVO = new DclaVO();
		dclaVO.setDcla_name(dcla_name);
		dao.insert(dclaVO);
		return dclaVO;
	}
	
	public DclaVO updateDcla(String dcla_no, String dcla_name) {
		DclaVO dclaVO = new DclaVO();
		dclaVO.setDcla_no(dcla_no);
		dclaVO.setDcla_name(dcla_name);
		dao.update(dclaVO);
		return dclaVO;
	}
	
	public DclaVO getOneDcla(String dcla_no) {
		return dao.findByPrimaryKey(dcla_no);
	}
	
	public List<DclaVO> getAll() {
		return dao.getAll();
	}
	
	public List<DclaVO> getStr_DishClass(String str_no){
		return dao.getStr_DishClass(str_no);
	}
	


}
