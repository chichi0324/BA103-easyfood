package com.ord.model;

import java.sql.Timestamp;
import java.util.List;

public class OrdService {

	private OrdDAO_interface dao;

	public OrdService() {
		dao = new OrdDAO();
	}

	public OrdVO addOrd(String mem_no, String str_no, String ord_type, Double ord_pri, Timestamp ord_date, String ord_add) {

		OrdVO ordVO = new OrdVO();
		ordVO.setMem_no(mem_no);
		ordVO.setStr_no(str_no);
		ordVO.setOrd_type(ord_type);
		ordVO.setOrd_pri(ord_pri);
		ordVO.setOrd_date(ord_date);
		ordVO.setOrd_add(ord_add);
		dao.insert(ordVO);

		return ordVO;
	}

	public OrdVO updateOrd(Integer ord_ev, String ord_eva, String ord_no) {

		OrdVO ordVO = new OrdVO();
		ordVO.setOrd_ev(ord_ev);
		ordVO.setOrd_eva(ord_eva);
		ordVO.setOrd_no(ord_no);
		dao.update(ordVO);

		return ordVO;
	}

	public void deleteOrd(String ord_no) {
		dao.delete(ord_no);
	}

	public OrdVO getOneOrd(String ord_no) {
		return dao.findByPrimaryKey(ord_no);
	}

	public List<OrdVO> getAll() {
		return dao.getAll();
	}
	
	public List<OrdVO> getAll_byMemSelf() {
		return dao.getAll_byMemSelf();
	}
	
	public List<OrdVO> getAllByStr(String str_no) {
		return dao.getAllByStr(str_no);
	}
	
	public List<OrdVO> getIntervalByStr(String str_no, String interval) {
		return dao.getIntervalByStr(str_no, interval);
	}
	
	public OrdVO getOneByStr(String ord_no, String str_no) {
		return dao.getOneByStr(ord_no, str_no);
	}
	
	public List<OrdVO> getStatus(String str_no, String ord_stat) {
		return dao.getStatus(str_no, ord_stat);
	}
	
	public void updateStatus(String ord_no, String ord_stat) {
		dao.updateStatus(ord_no, ord_stat);
	}
	
	public List<OrdVO> getAllByStar(){
		return dao.getAllByStar();
	}
	
	public List<OrdVO> findByStore(String str_no){
		return dao.findByStore(str_no);
	}
	

}
