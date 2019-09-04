package com.rep.model;

import java.util.List;

public class RepService {

	private RepDAO_interface dao;

	public RepService() {
		dao = new RepDAO();
	}

	public RepVO addRep(String rep_res, String ord_no) {

		RepVO repVO = new RepVO();
		repVO.setRep_res(rep_res);
		repVO.setOrd_no(ord_no);
		dao.insert(repVO);

		return repVO;
	}

	public RepVO updateRep(String rep_res, String rep_rev, String ord_no) {

		RepVO repVO = new RepVO();
		repVO.setRep_res(rep_res);
		repVO.setRep_rev(rep_rev);
		repVO.setOrd_no(ord_no);
		dao.update(repVO);

		return repVO;
	}
	public RepVO updateReport(String rep_res, String rep_rev, String rep_no) {

		RepVO repVO = new RepVO();
		repVO.setRep_res(rep_res);
		repVO.setRep_rev(rep_rev);
		repVO.setRep_no(rep_no);
		dao.update(repVO);

		return repVO;
	}

	public void deleteRep(String rep_no) {
		dao.delete(rep_no);
	}

	public RepVO getOneRep(String rep_no) {
		return dao.findByPrimaryKey(rep_no);
	}

	public List<RepVO> getAll() {
		return dao.getAll();
	}
	
	public List<RepVO> findByREV(String rep_rev){
		return dao.findByREV(rep_rev);
	}
	
}
