package com.blog.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.blre.model.BlreVO;
import com.ra.model.RaVO;
import com.tools.tools;


public class BlogService {
	
	private BlogDAO_interface dao;

	public BlogService() {
		dao = new BlogDAO();
	}
	
	public BlogVO addBlog(String bl_name,String bl_con,
			String mem_no,String str_no){
		
		BlogDAO dao = new BlogDAO();

		//新增
		BlogVO blogVO1 = new BlogVO();
		blogVO1.setBl_name(bl_name);
		blogVO1.setBl_con(bl_con);
		blogVO1.setBl_date(tools.nowTimestamp());
		//blogVO2.setBl_date(tools.strToTimestamp("2016-09-15 09:50:03"));
		blogVO1.setMem_no(mem_no);
   	    blogVO1.setStr_no(str_no);
		dao.insert(blogVO1);
		
		return blogVO1;
	}
	
	public BlogVO updateBlog(String bl_no,String bl_name,String bl_con,
			String mem_no,String str_no){
		
		BlogDAO dao = new BlogDAO();
		
		//修改
		BlogVO blogVO2 = new BlogVO();
		blogVO2.setBl_no(bl_no);
		blogVO2.setBl_name(bl_name);
		blogVO2.setBl_con(bl_con);
		//blogVO2.setBl_date(tools.strToTimestamp(bl_date));
		blogVO2.setBl_date(tools.nowTimestamp());
		blogVO2.setMem_no(mem_no);
    	blogVO2.setStr_no(str_no);
		dao.update(blogVO2);
		
		return blogVO2;
	}
	
	public void deleteBlog(String bl_no) {
		dao.delete(bl_no);
	}

	public BlogVO findByPrimaryKey(String bl_no) {
		return dao.findByPrimaryKey(bl_no);
	}
	
	public BlogVO findBLRECount(String bl_no){
		return dao.findBLRECount(bl_no);
	}
	
	public List<BlogVO> findByMember(String mem_no){
		return dao.findByMember(mem_no);
	}

	public List<BlogVO> getAll() {
		return dao.getAll();
	}
	
	public Set<BlreVO> getBLREsByBlog(String bl_no){
		return dao.getBLREsByBlog(bl_no);
	}
	
	public Set<RaVO> getRAsByBlog(String bl_no){
		return dao.getRAsByBlog(bl_no);
	}

	 public List<BlogVO> findByTime(Timestamp timestamp1,Timestamp timestamp2){
		 return dao.findByTime(timestamp1, timestamp2);
	 }
	 
	 public List<BlogVO> findByKeyword(String keyword){
		 return dao.findByKeyword(keyword);
	 }
	 
	 public List<BlogVO> findByStore(String str_no){
		 return dao.findByStore(str_no);
	 }
	 
	 public List<String> getRAstasByBlog(String bl_no){
		 return dao.getRAstasByBlog(bl_no);
	 }


}
