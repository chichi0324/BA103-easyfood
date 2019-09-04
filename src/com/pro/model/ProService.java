package com.pro.model;

import java.sql.Date;
import java.util.List;

public class ProService {
	
	private ProDAO_interface dao;
	
	public ProService(){
		dao = new ProDAO();
	}
	
	public ProVO addPro1(Date pro_str,Date pro_end,String str_no, String pro_cat,
			Integer pro_mon,Double pro_dis){
		
		ProVO proVO = new ProVO();
		
		proVO.setPro_str(pro_str);
		proVO.setPro_end(pro_end);
		proVO.setStr_no(str_no);
		proVO.setPro_cat(pro_cat);
		proVO.setPro_mon(pro_mon);
		proVO.setPro_dis(pro_dis);
		
		dao.insert1(proVO);
		return proVO;
	}
	
	public ProVO addPro2(Date pro_str,Date pro_end,String str_no, String pro_cat,
			Double pro_dis,String dcla_no1,String dcla_no2){
		
		ProVO proVO = new ProVO();
		
		proVO.setPro_str(pro_str);
		proVO.setPro_end(pro_end);
		proVO.setStr_no(str_no);
		proVO.setPro_cat(pro_cat);
		proVO.setPro_dis(pro_dis);
		proVO.setDcla_no1(dcla_no1);
		proVO.setDcla_no2(dcla_no2);
		
		dao.insert2(proVO);
		return proVO;
	}
	
	public ProVO updatePro(Date pro_end,String pro_no){
		
		ProVO proVO = new ProVO();
		
		proVO.setPro_end(pro_end);
		proVO.setPro_no(pro_no);
		
		return proVO;
	}

	public List<ProVO> getAll(){
		
		return dao.getAll();
	}
	
	public List<ProVO> getStrPro(String str_no){
		
		return dao.getStrPro(str_no);
	}
	
	
}
