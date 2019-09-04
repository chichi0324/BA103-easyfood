package com.admp.model;

import java.util.List;

import com.adm.model.AdmVO;
import com.fea.model.FeaVO;

public interface AdmpDAO_interface {
    public void insert(AdmpVO admpVO);
    public void update(AdmpVO admpVO);
    public List<AdmpVO> getTwoByADM(String adm_no);
    public List<AdmpVO> getTwoByFEA(String fea_no);
    public List<AdmpVO> getAll();
    
    public void delete(String adm_no);
    public void deleteByFea(String fea_no);
	   

}
