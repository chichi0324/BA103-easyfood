package com.ordit.model;

import java.util.List;

public class OrditService {

	private OrditDAO_interface dao;

	public OrditService() {
		dao = new OrditDAO();
	}

	public OrditVO addOrdit(String ord_no, String dish_no,Integer ordit_qua,
			Double ordit_pri) {

		OrditVO orditVO = new OrditVO();

		orditVO.setOrd_no(ord_no);
		orditVO.setDish_no(dish_no);
		orditVO.setOrdit_qua(ordit_qua);
		orditVO.setOrdit_pri(ordit_pri);
		dao.insert(orditVO);

		return orditVO;
	}

	public OrditVO updateOrdit(String ord_no, String dish_no, Integer ordit_qua,
			Double ordit_pri) {

		OrditVO orditVO = new OrditVO();
		orditVO.setOrd_no(ord_no);
		orditVO.setDish_no(dish_no);
		orditVO.setOrdit_qua(ordit_qua);
		orditVO.setOrdit_pri(ordit_pri);
		dao.update(orditVO);

		return orditVO;
	
	}

	public void deleteOrdit(String ord_no, String dish_no) {
		dao.delete(ord_no,dish_no);
	}

	public OrditVO getOneOrdit(String ord_no, String dish_no) {
		return dao.findByPrimaryKey(ord_no,dish_no);
	}

	public List<OrditVO> getAll() {
		return dao.getAll();
	}
	
	public List<OrditVO> getAllyStr(String ord_no) {
		return dao.getAllyStr(ord_no);
	}
	public List<OrditVO> getAll_month() {
		return dao.getAll_month();
	}
	
	public List<OrditVO> getAll_week() {
		return dao.getAll_week();
	}
	public List<OrditVO> getDish_class01_all(){
		return dao.getDish_class01_all();
	}
	public List<OrditVO> getDish_class02_all(){
		return dao.getDish_class02_all();
	}
	public List<OrditVO> getDish_class03_all(){
		return dao.getDish_class03_all();
	}
	public List<OrditVO> getDish_class05_all(){
		return dao.getDish_class05_all();
	}
	
}
