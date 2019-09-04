package com.pro.model;

import java.sql.Date;

public class ProVO implements java.io.Serializable{
	private String pro_no;
	private Date pro_str;
	private Date pro_end;
	private String str_no;
	private String pro_cat;
	private Integer pro_mon;
	private Double pro_dis;
	private String dcla_no1;
	private String dcla_no2;
	
	
	public String getPro_no() {
		return pro_no;
	}
	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
	}
	public Date getPro_str() {
		return pro_str;
	}
	public void setPro_str(Date pro_str) {
		this.pro_str = pro_str;
	}
	public Date getPro_end() {
		return pro_end;
	}
	public void setPro_end(Date pro_end) {
		this.pro_end = pro_end;
	}
	public String getStr_no() {
		return str_no;
	}
	public void setStr_no(String str_no) {
		this.str_no = str_no;
	}
	public String getPro_cat() {
		return pro_cat;
	}
	public void setPro_cat(String pro_cat) {
		this.pro_cat = pro_cat;
	}
	public Integer getPro_mon() {
		return pro_mon;
	}
	public void setPro_mon(Integer pro_mon) {
		this.pro_mon = pro_mon;
	}
	public Double getPro_dis() {
		return pro_dis;
	}
	public void setPro_dis(Double pro_dis) {
		this.pro_dis = pro_dis;
	}
	public String getDcla_no1() {
		return dcla_no1;
	}
	public void setDcla_no1(String dcla_no1) {
		this.dcla_no1 = dcla_no1;
	}
	public String getDcla_no2() {
		return dcla_no2;
	}
	public void setDcla_no2(String dcla_no2) {
		this.dcla_no2 = dcla_no2;
	}
	
	
}
