package com.fav.model;

import java.util.List;


public interface FavDAO_interface {
	public void insert(FavVO favVO);
	public void delete(String mem_no,String str_no);
	public List<FavVO> findByPrimaryKey(String mem_no);
	public List<FavVO> getAll();
}
