package com.blre.model;

import java.sql.Date;
import java.sql.Timestamp;

public class BlreVO implements java.io.Serializable{
	private String blre_no;
	private String blre_con;
	private String bl_no;
	private String mem_no;
	private Timestamp blre_date ;
	
	public String getBlre_no() {
		return blre_no;
	}
	public void setBlre_no(String blre_no) {
		this.blre_no = blre_no;
	}
	public String getBlre_con() {
		return blre_con;
	}
	public void setBlre_con(String blre_con) {
		this.blre_con = blre_con;
	}
	public String getBl_no() {
		return bl_no;
	}
	public void setBl_no(String bl_no) {
		this.bl_no = bl_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Timestamp getBlre_date() {
		return blre_date;
	}
	public void setBlre_date(Timestamp blre_date) {
		this.blre_date = blre_date;
	}
	


}
