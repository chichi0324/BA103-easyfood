package com.rep.model;

import java.util.List;



public interface RepDAO_interface {

	public void insert(RepVO repVO);

	public void update(RepVO repVO);

	public void delete(String rep_no);

	public RepVO findByPrimaryKey(String rep_no);

	public List<RepVO> getAll();
	
	public List<RepVO> findByREV(String rep_rev);
	
}
