package com.add.model;

import java.util.List;

public class AddService {

	private AddDAO_interface dao;

	public AddService() {
		dao = new AddDAO();
	}

	public AddVO addAdd(String mem_no, String add_adds) {

		AddVO addVO = new AddVO();
	
		addVO.setMem_no(mem_no);
		addVO.setAdd_adds(add_adds);
		dao.insert(addVO);

		return addVO;
	}

	public AddVO updateAdd(String add_adds, String add_no){
		AddVO addVO = new AddVO();

		addVO.setAdd_adds(add_adds);
		addVO.setAdd_no(add_no);
		dao.update(addVO);

		return addVO;
	}

	public void deleteAdd(String add_no) {
		dao.delete(add_no);
	}

	public AddVO getOneAdd(String add_no) {
		return dao.findByPrimaryKey(add_no);
	}

	public List<AddVO> getAll() {
		return dao.getAll();
	}
	
}
