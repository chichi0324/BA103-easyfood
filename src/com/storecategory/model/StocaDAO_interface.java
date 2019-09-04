package com.storecategory.model;

import java.util.List;

public interface StocaDAO_interface {
	
	public void insert(StocaVO stocaVO);
	public void update(StocaVO stocaVO);
	public void update_img(StocaVO stocaVO);
	public void update_noimg(StocaVO stocaVO);
	public void delete(String strca_no);
	public StocaVO findByPrimaryKey(String stoca_no);
	public List<StocaVO> getAll();
	public List<StocaVO> getALL();

}
