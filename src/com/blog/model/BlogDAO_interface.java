package com.blog.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.blre.model.BlreVO;
import com.ra.model.RaVO;


public interface BlogDAO_interface {
    public void insert(BlogVO blogVO);
    public void update(BlogVO  blogVO);
    public void delete(String bl_no);
    public BlogVO findByPrimaryKey(String bl_no);
    public List<BlogVO> findByMember(String mem_no);
    public List<BlogVO> getAll();
    public List<BlogVO> findByKeyword(String keyword);
    
    public BlogVO findBLRECount(String bl_no);
    
    public List<BlogVO> findByTime(Timestamp timestamp1,Timestamp timestamp2);
    public List<BlogVO> findByStore(String str_no);
    public Set<BlreVO> getBLREsByBlog(String bl_no);
    public Set<RaVO> getRAsByBlog(String bl_no);
    public List<String> getRAstasByBlog(String bl_no);
}
