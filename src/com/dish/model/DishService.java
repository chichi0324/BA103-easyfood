package com.dish.model;

import java.util.List;

public class DishService {
	
	public DishDAO_interface dao;
	
	public DishService() {
		dao = new DishDAO();
	}
	
	public DishVO addDish(String dish_name, Double dish_price, String dcla_no, String str_no, byte[] dish_img, String dish_note) {
		DishVO dishVO = new DishVO();
		dishVO.setDish_name(dish_name);
		dishVO.setDish_price(dish_price);
		dishVO.setDcla_no(dcla_no);
		dishVO.setStr_no(str_no);
		dishVO.setDish_img(dish_img);
		dishVO.setDish_note(dish_note);
		dao.insert(dishVO);
		return dishVO;
	}
	
	public DishVO update(String dish_no, String dish_name, Double dish_price, String dish_status, String dish_note) {
		
		DishVO dishVO = new DishVO();
		dishVO.setDish_no(dish_no);
		dishVO.setDish_name(dish_name);
		dishVO.setDish_price(dish_price);
		dishVO.setDish_status(dish_status);
		dishVO.setDish_note(dish_note);
		dao.update(dishVO);
		return dishVO;
		
	}
	
	public DishVO updateImg(String dish_no, String dish_name, Double dish_price, String dish_status, byte[] dish_img, String dish_note) {
		
		DishVO dishVO = new DishVO();
		dishVO.setDish_no(dish_no);
		dishVO.setDish_name(dish_name);
		dishVO.setDish_price(dish_price);
		dishVO.setDish_status(dish_status);
		dishVO.setDish_img(dish_img);
		dishVO.setDish_note(dish_note);
		dao.update(dishVO);
		return dishVO;
	}
	
	public void updateStatus(String dish_no, String dish_status) {
		dao.updateStatus(dish_no, dish_status);
	}
	
	public DishVO getOneDish(String dish_no) {
		return dao.findByDishNo(dish_no);
	}
	
	public List<DishVO> getDclaDish(String dcla_no) {
		return dao.findByDishClass(dcla_no);
	}
	
	public List<DishVO> getStoreDish(String str_no) {
		return dao.findByStore(str_no);
	}
	
	public List<DishVO> getStatusDish(String dish_status) {
		return dao.findByStatus(dish_status);
	}
	
	public List<String> getDishNameByStore(String str_no) {
		return dao.findDishNameByStore(str_no);
	}
	public List<DishVO> getDishImgByStore(String str_no) {
		return dao.findDishImgByStore(str_no);
	}
	
	public List<DishVO> getPriceLevelDish(Double minPrice, Double maxPrice) {
		return dao.findByPrice(minPrice, maxPrice);
	}
	
	public List<DishVO> getAreaDish(String area) {
		return dao.findByArea(area);
	}
	
	public List<DishVO> getAll() {
		return dao.getAll();
	}
	
	public List<DishVO> getDishClassForStr(String str_no) {
		return dao.getDishClassForStr(str_no);
	}
	
	
	public List<DishVO> findByClassStore(String dcla_no,String str_no){
		return dao.findByClassStore(dcla_no, str_no);
	}
	
	public List<DishVO> findByClassStoreStatus(String dcla_no,String str_no,String dish_status){
		return dao.findByClassStoreStatus(dcla_no, str_no, dish_status);
	}
	public List<DishVO> getByStrStatus(String str_no,String dish_status){
		return dao.getByStrStatus(str_no, dish_status);
	}
	public DishVO getOneDish_Name(String dish_no) {
		return dao.findByDishNo_ForName(dish_no);
	}
	public DishVO getDishForStrAll(String dish_no) {
		return dao.findByDishNo_Str_no(dish_no);
	}

	
	
}
