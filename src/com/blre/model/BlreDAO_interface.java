package com.blre.model;

import java.util.List;


public interface BlreDAO_interface {
    public void insert(BlreVO blreVO);
    public void update(BlreVO  blreVO);
    public void delete(String blre_no);
    public BlreVO findByPrimaryKey(String blre_no);
    public List<BlreVO> findByMember(String mem_no);
    public List<BlreVO> getAll();

}
