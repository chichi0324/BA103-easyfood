package com.adm.model;

import java.util.*;



public interface AdmDAO_interface {
	public void insert(AdmVO admVO);
    public void update(AdmVO admVO);
    public void delete(String adm_no);
    public AdmVO findByPrimaryKey(String adm_no);
    public List<AdmVO> getAll();
    public AdmVO findByAcc(String adm_acc,String adm_pas);
    public AdmVO findByAcc(String adm_acc);
    public AdmVO findByName(String adm_Name);
}
