package com.pro.model;

import java.util.List;

import com.pro.model.ProVO;

public interface ProDAO_interface {
	public void insert1(ProVO proVO);
	public void insert2(ProVO proVO);
	public void update(ProVO proVO);	
	public ProVO findByPrimaryKey(String pro_no);
	public List<ProVO> getAll();
	public List<ProVO> getStrPro(String str_no);
}
