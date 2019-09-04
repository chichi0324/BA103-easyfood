package com.ra.model;

import java.util.List;


public interface RaDAO_interface {
    public void insert(RaVO raVO);
    public void update(RaVO raVO);
    public void delete(String ra_no);
    public RaVO findByPrimaryKey(String ra_no);
    public List<RaVO> getAll();
    public List<RaVO> findByRev(String ra_rev);
}
