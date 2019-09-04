package com.fav.model;

import java.util.List;

public class FavService {
	
		private FavDAO_interface dao;
		
		public  FavService(){
			dao = new FavDAO(); 
		}
		
		public FavVO addFav(String mem_no,String str_no){
			
			FavVO favVO = new FavVO();
			
			favVO.setMem_no(mem_no);
			favVO.setStr_no(str_no);
			
			dao.insert(favVO);
			
			return favVO;
		}
		
		public void  deleteFav(String mem_no,String str_no){
			
			
			dao.delete(mem_no, str_no);
			
			
			
					
		}
		
		public List<FavVO> getoneFav(String mem_no){
			return dao.findByPrimaryKey(mem_no);
		}
		
		public List<FavVO> getAll(){
			return dao.getAll();
		}
	}
	
	
