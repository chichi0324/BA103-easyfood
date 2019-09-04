package com.blog.model;

import java.sql.Timestamp;

public class BlogVO implements java.io.Serializable{
	private String bl_no;
	private String bl_name;
	private String bl_con;
	private Timestamp bl_date;
	private String mem_no;
	private String str_no;
	private String blre_count;
	
	public String getBl_no() {
		return bl_no;
	}
	public void setBl_no(String bl_no) {
		this.bl_no = bl_no;
	}
	public String getBl_name() {
		return bl_name;
	}
	public void setBl_name(String bl_name) {
		this.bl_name = bl_name;
	}
	public String getBl_con() {
		return bl_con;
	}
	public void setBl_con(String bl_con) {
		this.bl_con = bl_con;
	}
	public Timestamp getBl_date() {
		return bl_date;
	}
	public void setBl_date(Timestamp bl_date) {
		this.bl_date = bl_date;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getStr_no() {
		return str_no;
	}
	public void setStr_no(String str_no) {
		this.str_no = str_no;
	}
	
	public String getBlre_count() {
		return blre_count;
	}
	public void setBlre_count(String blre_count) {
		this.blre_count = blre_count;
	}

	
}
