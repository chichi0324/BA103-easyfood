package com.storecategory.model;

import java.util.List;

public class StocaService {
	
	private StocaDAO_interface dao;
	
	public StocaService() {
		dao = new StocaDAO();
	}
	
	public StocaVO addStoca(String stoca_name, byte[] stoca_img, String stoca_note) {
		
		StocaVO stocaVO = new StocaVO();
		stocaVO.setStoca_name(stoca_name);
		stocaVO.setStoca_img(stoca_img);
		stocaVO.setStoca_note(stoca_note);
		dao.insert(stocaVO);
		return stocaVO;
	}
	
	public StocaVO updateStoca(String stoca_no, String stoca_name, byte[] stoca_img, String stoca_note) {
		
		StocaVO stocaVO = new StocaVO();
		stocaVO.setStoca_no(stoca_no);
		stocaVO.setStoca_name(stoca_name);
		stocaVO.setStoca_img(stoca_img);
		stocaVO.setStoca_note(stoca_note);
		dao.update(stocaVO);
		return stocaVO;
	}
	
	public StocaVO updateImg(byte[] stoca_img, String stoca_no) {
		
		StocaVO stocaVO = new StocaVO();
		stocaVO.setStoca_img(stoca_img);
		stocaVO.setStoca_no(stoca_no);
		dao.update_img(stocaVO);
		return stocaVO;
		
	}
	
	public StocaVO updateNoimg(String stoca_no, String stoca_name, String stoca_note) {
		
		StocaVO stocaVO = new StocaVO();
		stocaVO.setStoca_no(stoca_no);
		stocaVO.setStoca_name(stoca_name);
		stocaVO.setStoca_note(stoca_note);
		dao.update(stocaVO);
		return stocaVO;
	}
	
	public StocaVO getOneStoca(String stoca_no) {
		return dao.findByPrimaryKey(stoca_no);
	}
	
	public List<StocaVO> getAll() {
		return dao.getAll();
	}
	
	public List<StocaVO> getALL() {
		return dao.getALL();
	}
		
}
