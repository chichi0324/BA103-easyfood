package com.add.model;

import java.util.List;



public interface AddDAO_interface {

	public void insert(AddVO addVO);

	public void update(AddVO addVO);

	public void delete(String add_no);

	public AddVO findByPrimaryKey(String add_no);

	public List<AddVO> getAll();
	
}
