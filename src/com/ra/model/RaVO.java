package com.ra.model;

import java.sql.Date;

public class RaVO implements java.io.Serializable{
	private String ra_no;
	private String bl_no;
	private String ra_res;
	private String ra_rev;
	
	public String getRa_no() {
		return ra_no;
	}
	public void setRa_no(String ra_no) {
		this.ra_no = ra_no;
	}
	public String getBl_no() {
		return bl_no;
	}
	public void setBl_no(String bl_no) {
		this.bl_no = bl_no;
	}
	public String getRa_res() {
		return ra_res;
	}
	public void setRa_res(String ra_res) {
		this.ra_res = ra_res;
	}
	public String getRa_rev() {
		return ra_rev;
	}
	public void setRa_rev(String ra_rev) {
		this.ra_rev = ra_rev;
	}
	
	
}
