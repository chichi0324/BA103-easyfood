package com.sys.model;

import java.util.List;

public interface SysDAO_interface {
    public void insert(SysVO sysVO);
    public void update(SysVO sysVO);
    public void delete(String sys_no);
    public SysVO findByPrimaryKey(String sys_no);
    public List<SysVO> getAll();

}
