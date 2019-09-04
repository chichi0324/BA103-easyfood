package com.adv.model;

import java.util.List;



public class AdvService {
	
	private AdvDAO_interface dao;
	
	public AdvService(){
		dao= new AdvDAO();
		
	}
	
	public AdvVO addAdv(String str_no,String adv_sta,java.sql.Date adv_str,
			java.sql.Date adv_end,byte[] adv_txt){
		AdvVO advVO = new AdvVO();
		
		advVO.setStr_no(str_no);
		advVO.setAdv_sta(adv_sta);
		advVO.setAdv_str(adv_str);
		advVO.setAdv_end(adv_end);
		advVO.setAdv_txt(adv_txt);
		dao.insert(advVO);
		
		return advVO;
	}
	
	public AdvVO updateAdv(String adv_no,java.sql.Date adv_end,byte[] adv_txt){
		AdvVO advVO = new AdvVO();
		
		advVO.setAdv_no(adv_no);
		advVO.setAdv_end(adv_end);
		advVO.setAdv_txt(adv_txt);
		
		dao.update(advVO);
		
		return advVO;
	}
	
	public AdvVO updateAdv_Sta(String adv_no,String adv_sta){
		AdvVO advVO = new AdvVO();
		
		advVO.setAdv_no(adv_no);
		advVO.setAdv_sta(adv_sta);
	
		
		dao.update(advVO);
		
		return advVO;
	}
	
	
	public void deleteAdv(String adv_no) {
		dao.delete(adv_no);
	}

	public AdvVO getOneAdv(String adv_no) {
		return dao.findByPrimaryKey(adv_no);
	}
	
	public List<AdvVO> getAll() {
		return dao.getAll();
	}
	
	public List<AdvVO> getAllStrAudit() {
		return dao.getAllStrAudit();
	}
	
	public List<AdvVO> getAllStrAuditOk() {
		return dao.getAllStrAuditOk();
	}
	
	public List<AdvVO> getAllStrAdv(String str_no){
		return dao.getAllStrAdv(str_no);
		
	}
	

}
