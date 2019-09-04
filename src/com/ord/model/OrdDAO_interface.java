package com.ord.model;

import java.util.List;

public interface OrdDAO_interface {

	public void insert(OrdVO ordVO);

	public void update(OrdVO ordVO);

	public void delete(String ord_no);

	public OrdVO findByPrimaryKey(String ord_no);

	public List<OrdVO> getAll();
	
	public List<OrdVO> getAll_byMemSelf();
//	------------------new increase----------------------//	
	public List<OrdVO> getAllByStr(String str_no); //all
	
	public List<OrdVO> getIntervalByStr(String str_no, String interval);
	
	public OrdVO getOneByStr(String ord_no, String str_no);
	
	public List<OrdVO> getStatus(String str_no, String ord_stat);
	
	public void updateStatus(String ord_no, String ord_stat);
	
	public List<OrdVO> findByStore(String str_no);
	
	public List<OrdVO> getAllByStar();
	
}
