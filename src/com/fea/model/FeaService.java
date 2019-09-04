package com.fea.model;

import java.util.List;


public class FeaService {
	private FeaDAO_interface dao;
	
	public FeaService() {
		dao = new FeaDAO();
	}
	
	public List<FeaVO> getAll() {
		return dao.getAll();
	}
	
	public void deleteFea(String Fea_no) {
		dao.delete(Fea_no);
	}
	
	public FeaVO getOneFea(String Fea_no) {
		return dao.findByPrimaryKey(Fea_no);
	}
	
	public FeaVO addFea(String Fea_name) {

		FeaVO feaVO = new FeaVO();
		feaVO.setFea_name(Fea_name);
		dao.insert(feaVO);
		
		return feaVO;
	}
	
	public FeaVO updateFea(String Fea_no, String Fea_name) {

		FeaVO feaVO = new FeaVO();

		feaVO.setFea_no(Fea_no);
		feaVO.setFea_name(Fea_name);
		dao.update(feaVO);
		
		return feaVO;
	}
}
