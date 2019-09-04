package com.dish.model;

import java.util.List;

import com.dishclass.model.DclaVO;

public interface DishDAO_interface {
	
	public void insert(DishVO dishVO);
	public void update(DishVO dishVO);
	public void updateImg(DishVO dishVO);
	public void updateStatus(String dish_no, String dish_status);
	public DishVO findByDishNo(String dish_no);
	public List<DishVO> findByDishClass(String dcla_no);
	public List<DishVO> findByStore(String str_no);
	public List<DishVO> findByPrice(Double minPrice, Double maxPrice);
	public List<DishVO> findByArea(String area);
	public List<DishVO> getAll();
	public List<String> findDishNameByStore(String str_no);
	public List<DishVO> findDishImgByStore(String str_no);
	public List<DishVO> findByStatus(String dish_status);
	public List<DishVO> getDishClassForStr(String str_no);
	
	public List<DishVO> getALL();
	public List<DishVO> findByClassStore(String dcla_no,String str_no);
	
	public List<DishVO> findByClassStoreStatus(String dcla_no,String str_no,String dish_status);
	public List<DishVO> getByStrStatus(String str_no,String dish_status);
	
	public DishVO findByDishNo_Str_no(String dish_no);
	public DishVO findByDishNo_ForName(String dish_no);
	
	
	
}
