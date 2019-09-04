package com.admp.model;

import java.util.List;


public class AdmpService {
private AdmpDAO_interface dao;
	
	public AdmpService() {
		dao = new AdmpDAO();
	}
	
	public List<AdmpVO> getAll() {
		return dao.getAll();
	}
	
	public List<AdmpVO> getTwoByADM(String adm_no) {
		
		return dao.getTwoByADM(adm_no);		
	}
	
	public List<AdmpVO> getTwoByFEA(String fea_no) {
		
		return dao.getTwoByFEA(fea_no);	
	}
	
	public AdmpVO addAdmp(String Fea_no,String Adm_no) {

		AdmpVO admpVO = new AdmpVO();
		admpVO.setFea_no(Fea_no);
		admpVO.setAdm_no(Adm_no);
		dao.insert(admpVO);
		
		return admpVO;
	}
	
	public AdmpVO updateAdmp(String Fea_no,String Adm_no) {

		AdmpVO admpVO = new AdmpVO();
		admpVO.setFea_no(Fea_no);
		admpVO.setAdm_no(Adm_no);
		dao.update(admpVO);
		
		return admpVO;
	}
	
	public void deleteAdm(String adm_no){
		dao.delete(adm_no);
	}
	
	public void deleteByFea(String fea_no){
		dao.deleteByFea(fea_no);
	}
}
