package com.adm.model;

import java.util.List;



public class AdmService {
	private AdmDAO_interface dao;
	
	public AdmService() {
		dao = new AdmDAO();
	}
	
	public List<AdmVO> getAll() {
		return dao.getAll();
	}
	
	public void deleteAdm(String Adm_no) {
		dao.delete(Adm_no);
	}
	
	
	public AdmVO getOneAdm(String Adm_no) {
		return dao.findByPrimaryKey(Adm_no);
	}
	
	public AdmVO getOneAdm1(String Adm_acc) {
		return dao.findByAcc(Adm_acc);
	}
	
	public AdmVO getOneAdm2(String Adm_name) {
		return dao.findByName(Adm_name);
	}
	
	public AdmVO getOneAdmByAcc(String Adm_acc,String Adm_pas) {
		return dao.findByAcc(Adm_acc,Adm_pas);
	}
	public AdmVO addAdm(String Adm_acc, String Adm_pas, String Adm_name, String Adm_adrs, String Adm_pho, String Adm_pos,String Adm_mail) {

		 AdmVO admVO = new AdmVO();
		 
		 admVO.setAdm_acc(Adm_acc);
		 admVO.setAdm_pas(Adm_pas);
		 admVO.setAdm_name(Adm_name);
		 admVO.setAdm_adrs(Adm_adrs);
		 admVO.setAdm_pho(Adm_pho);
		 admVO.setAdm_pos(Adm_pos);
		 admVO.setAdm_mail(Adm_mail);
		 dao.insert(admVO);
		 
		return admVO;
	}
	
	public AdmVO updateAdm(String Adm_no, String Adm_acc, String Adm_pas, 
			String Adm_name, String Adm_adrs, String Adm_pho, String Adm_pos,String Adm_mail) {

		 AdmVO admVO = new AdmVO();
		 
		 admVO.setAdm_no(Adm_no);
		 admVO.setAdm_acc(Adm_acc);
		 admVO.setAdm_pas(Adm_pas);
		 admVO.setAdm_name(Adm_name);
		 admVO.setAdm_adrs(Adm_adrs);
		 admVO.setAdm_pho(Adm_pho);
		 admVO.setAdm_pos(Adm_pos);
		 admVO.setAdm_mail(Adm_mail);
		 dao.update(admVO);
		 
		 return admVO;
	}
}
