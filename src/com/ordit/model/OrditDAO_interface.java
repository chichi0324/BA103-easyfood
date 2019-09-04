package com.ordit.model;

import java.util.List;



public interface OrditDAO_interface {

	public void insert(OrditVO orditVO);

	public void update(OrditVO orditVO);

	public void delete(String ord_no, String dish_no);

	public OrditVO findByPrimaryKey(String ord_no, String dish_no);

	public List<OrditVO> getAll();
	
	public List<OrditVO> getAllyStr(String ord_no);	
	
	public List<OrditVO> getAll_month();
	
	public List<OrditVO> getAll_week();
	
	public List<OrditVO> getDish_class01_all();
	
	public List<OrditVO> getDish_class02_all();
	
	public List<OrditVO> getDish_class03_all();
	
	public List<OrditVO> getDish_class05_all();
	
}
