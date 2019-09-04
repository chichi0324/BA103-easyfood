package com.str.model;

import java.util.List;
import java.util.Map;

public class strService {
	
	public StrDAO_interface dao;
	
	public strService() {
		dao = new StrDAO();
	}
	
	public StrVO addStr(String str_name, String str_cou, String str_city, String str_addr, String str_tel, 
			String str_atn, Integer str_pre, String stoca_no, String str_acc, String str_pas, String str_ma, Double str_long, Double str_lat) {
		
		StrVO strVO = new StrVO();
		strVO.setStr_name(str_name);
		strVO.setStr_cou(str_cou);
		strVO.setStr_city(str_city);
		strVO.setStr_addr(str_addr);
		strVO.setStr_tel(str_tel);
		strVO.setStr_atn(str_atn);
		strVO.setStr_pre(str_pre);
		strVO.setStoca_no(stoca_no);
		strVO.setStr_acc(str_acc);
		strVO.setStr_pas(str_pas);
		strVO.setStr_ma(str_ma);
		strVO.setStr_long(str_long);
		strVO.setStr_lat(str_lat);
		dao.insert(strVO);
		return strVO;
	}
	
	public StrVO updateStr(String str_no, String str_name, String str_cou, String str_city, String str_addr, String str_tel, 
			String str_atn, Integer str_pre, String str_ship, String str_ma, Double str_long, Double str_lat) {
		
		StrVO strVO = new StrVO();
		strVO.setStr_no(str_no);
		strVO.setStr_name(str_name);
		strVO.setStr_cou(str_cou);
		strVO.setStr_city(str_city);
		strVO.setStr_addr(str_addr);
		strVO.setStr_tel(str_tel);
		strVO.setStr_atn(str_atn);
		strVO.setStr_pre(str_pre);
		strVO.setStr_ship(str_ship);
		strVO.setStr_ma(str_ma);
		strVO.setStr_long(str_long);
		strVO.setStr_lat(str_lat);
		dao.update(strVO);
		return strVO;
	}
	
	public StrVO updateInfo(byte [] str_img, String str_note, String str_no) {
		StrVO strVO = new StrVO();
		strVO.setStr_no(str_no);
		strVO.setStr_img(str_img);
		strVO.setStr_note(str_note);
		dao.updateInfo(strVO);
		return strVO;
	}
	
	public void updatePas(String str_no, String str_pas) {
		dao.updatePas(str_no, str_pas);
	}
	
	public void updateStatus(String str_no, String str_stat) {
		dao.updateStatus(str_no, str_stat);
	}
	
	public StrVO getOneStr(String str_no) {
		return dao.findByPrimaryKey(str_no);
	}
	
	public List<StrVO> getAreaStr(String area) {
		return dao.findByArea(area);
	}
	
	public List<StrVO> getStocaStr(String stoca_no) {
		return dao.findByStoca(stoca_no);
	}
	
	public List<StrVO> getAll() {
		return dao.getALl();
	}
	
	public List<StrVO> findByMuti(Map<Integer, List<String>> map){
		return dao.findByMuti(map);
	}
	
	public List<StrVO> findByRepStat(Integer str_rep,String str_stat){
		return dao.findByRepStat(str_rep, str_stat);
	}
	public void updateRep(Integer str_rep, String str_no){
		dao.updateRep(str_rep, str_no);
	}	
	
	public List<StrVO> findByStrSta(String str_stat){
		return dao.findByStrSta(str_stat);
	}
	
}
