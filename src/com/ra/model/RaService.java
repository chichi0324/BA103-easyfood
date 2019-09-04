package com.ra.model;

import java.util.List;


public class RaService {
	
	private RaDAO_interface dao;

	public RaService() {
		dao = new RaDAO();
	}

	public RaVO addRa(String bl_no, String ra_res, String ra_rev) {

		// 新增
		RaVO raVO1 = new RaVO();
		raVO1.setBl_no(bl_no);
		raVO1.setRa_res(ra_res);
		raVO1.setRa_rev(ra_rev);
		dao.insert(raVO1);

		
		return raVO1;
	}

	public RaVO updateRa(String ra_no, String bl_no, String ra_res, 
			String ra_rev) {
		
		// 修改
		RaVO raVO2 = new RaVO();
		raVO2.setRa_no(ra_no);
		raVO2.setBl_no(bl_no);
		raVO2.setRa_res(ra_res);
		raVO2.setRa_rev(ra_rev);
		dao.update(raVO2);

		return raVO2;
	}

	public void deleteRa(String blre_no) {
		dao.delete(blre_no);
	}

	public RaVO findByPrimaryKey(String blre_no) {
		return dao.findByPrimaryKey(blre_no);
	}

	public List<RaVO> getAll() {
		return dao.getAll();
	}
	
	public List<RaVO> findByRev(String ra_rev) {
		return dao.findByRev(ra_rev);
	}

}
