package com.sys.model;

import java.util.List;


public class SysService {
	
	private SysDAO_interface dao;

	public SysService() {
		dao = new SysDAO();
	}

	public SysVO addSys(String sys_con) {

		// 新增
		SysVO sysVO1 = new SysVO();
		sysVO1.setSys_con(sys_con);
		dao.insert(sysVO1);

		return sysVO1;
	}

	public SysVO updateSys(String sys_no, String sys_con) {
		
		// 修改
		SysVO sysVO2 = new SysVO();
		sysVO2.setSys_no(sys_no);
		sysVO2.setSys_con(sys_con);
		dao.update(sysVO2);

		return sysVO2;
	}

	public void deleteSys(String blre_no) {
		dao.delete(blre_no);
	}

	public SysVO findByPrimaryKey(String blre_no) {
		return dao.findByPrimaryKey(blre_no);
	}

	public List<SysVO> getAll() {
		return dao.getAll();
	}

}
