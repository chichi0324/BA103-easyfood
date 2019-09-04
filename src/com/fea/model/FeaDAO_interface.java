package com.fea.model;

import java.util.List;
import java.util.Set;


public interface FeaDAO_interface {
    public void insert(FeaVO feaVO);
    public void update(FeaVO feaVO);
    public void delete(String fea_no);
    public FeaVO findByPrimaryKey(String fea_no);
    public List<FeaVO> getAll();
    
    //public Set<FeaVO> getEmpsByDeptno(String fea_no);
   
}