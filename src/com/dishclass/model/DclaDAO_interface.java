package com.dishclass.model;

import java.util.List;



public interface DclaDAO_interface {
	
	public void insert(DclaVO dclaVO);
	public void update(DclaVO dclaVO);
	public void delete(String dcla_no);
	public DclaVO findByPrimaryKey(String dcla_no);
	public List<DclaVO> getAll();
	public List<DclaVO> getALL();
	
	public List<DclaVO> getStr_DishClass(String str_no);
}
