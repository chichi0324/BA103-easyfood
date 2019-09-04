package com.str.model;

import java.util.List;
import java.util.Map;

public interface StrDAO_interface {
	
	public void insert(StrVO strVO);
	public void update(StrVO strVO);
	public void updateInfo(StrVO strVO);
	public void updatePas(String str_no, String str_pas);
	public void updateStatus(String str_no, String str_stat);
	public StrVO findByPrimaryKey(String str_no);
	public List<StrVO> getALl();
	public List<StrVO> findByArea(String area);
	public List<StrVO> findByStoca(String stoca_no);
	
	public List<StrVO> findByMuti(Map<Integer, List<String>> map);
	
	public List<StrVO> findByRepStat(Integer str_rep,String str_stat);
	public void updateRep(Integer str_rep, String str_no);
	
	public List<StrVO> findByStrSta(String str_stat);
}
