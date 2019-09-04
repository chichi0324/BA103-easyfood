package com.mem.model;

import java.util.List;

public interface MemDAO_interface {
	public void insert(MemVO memVO);

	public void update(MemVO memVO);
	
	public void updateMemSelf(MemVO memVO);

	public void delete(String mem_no);

	public MemVO findByPrimaryKey(String mem_no);
	
	public MemVO findMem_noByMemAcc(String mem_acc);
	
	public String findMem_noByMem_acc(String mem_acc);

	public List<MemVO> getAll();
	
	public List<MemVO> getVio_Stas(Integer mem_vio,String mem_stas);
	
	public void updateVio(Integer mem_vio,String mem_no);
	
}
