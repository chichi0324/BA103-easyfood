package com.mem.model;

import java.util.List;

public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemDAO();
	}

	public MemVO addMem(String mem_acc, String mem_pw, String mem_name, String mem_pho, String mem_mail, byte[] mem_img) {

		MemVO memVO = new MemVO();

		memVO.setMem_acc(mem_acc);
		memVO.setMem_pw(mem_pw);
		memVO.setMem_name(mem_name);
		memVO.setMem_pho(mem_pho);
		memVO.setMem_mail(mem_mail);
		memVO.setMem_img(mem_img);
		dao.insert(memVO);

		return memVO;
	}
	
//修改mem_vio, mem_stas
	public MemVO updateMem(String mem_pw, String mem_name, 
			String mem_pho, String mem_mail, Integer mem_vio, String mem_stas, String mem_no){
		MemVO memVO = new MemVO();

		memVO.setMem_pw(mem_pw);
		memVO.setMem_name(mem_name);
		memVO.setMem_pho(mem_pho);
		memVO.setMem_mail(mem_mail);
		memVO.setMem_vio(mem_vio);
		memVO.setMem_stas(mem_stas);
		memVO.setMem_no(mem_no);		
		dao.update(memVO);

		return memVO;
	}
	
	//多載, 會員自己修改用
	public MemVO updateMem(String mem_pw, String mem_name, 
			String mem_pho, String mem_mail, String mem_no, String mem_acc,byte[] mem_img){
		MemVO memVO = new MemVO();

		memVO.setMem_pw(mem_pw);
		memVO.setMem_name(mem_name);
		memVO.setMem_pho(mem_pho);
		memVO.setMem_mail(mem_mail);
		memVO.setMem_no(mem_no);
		memVO.setMem_acc(mem_acc);
		memVO.setMem_img(mem_img);
		dao.updateMemSelf(memVO);

		return memVO;
	}

	public void deleteMem(String mem_no) {
		dao.delete(mem_no);
	}

	public MemVO getOneMem(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}
	
	public String getOneMem_no(String mem_acc) {
		return dao.findMem_noByMem_acc(mem_acc);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
//	public static void main(String[] args) {
//				
//		String mem_no = null;
//		mem_no = "MEM_0001";
//		MemService memSvc = new MemService();
//		MemVO memVO = memSvc.getOneMem(mem_no);
//		
//	}
	
	public MemVO findMem_noByMemAcc(String mem_acc){
		return dao.findMem_noByMemAcc(mem_acc);
	}
	
	public List<MemVO> getVio_Stas(Integer mem_vio,String mem_stas){
		return dao.getVio_Stas(mem_vio, mem_stas);
	}
	
	public void updateVio(Integer mem_vio,String mem_no) {
		dao.updateVio(mem_vio, mem_no);
		
	}
}
