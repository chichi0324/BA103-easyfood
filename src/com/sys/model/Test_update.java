package com.sys.model;

public class Test_update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SysJDBCDAO dao = new SysJDBCDAO();
		//修改
		SysVO sysVO2 = new SysVO();
		sysVO2.setSys_no("SYS_0002");
		sysVO2.setSys_con("網站將休息一段時間");
		dao.update(sysVO2);

	}

}
