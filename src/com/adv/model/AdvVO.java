package com.adv.model;

import java.sql.Date;

public class AdvVO implements java.io.Serializable{	
	
	private String adv_no;
	private String str_no;
	private String adv_sta;
	private Date adv_str;
	private Date adv_end;
	private byte[] adv_txt;
	
	
	public String getAdv_no() {
		return adv_no;
	}
	public void setAdv_no(String adv_no) {
		this.adv_no = adv_no;
	}
	public String getStr_no() {
		return str_no;
	}
	public void setStr_no(String str_no) {
		this.str_no = str_no;
	}
	public String getAdv_sta() {
		return adv_sta;
	}
	public void setAdv_sta(String adv_sta) {
		this.adv_sta = adv_sta;
	}
	public Date getAdv_str() {
		return adv_str;
	}
	public void setAdv_str(Date adv_str) {
		this.adv_str = adv_str;
	}
	public Date getAdv_end() {
		return adv_end;
	}
	public void setAdv_end(Date adv_end) {
		this.adv_end = adv_end;
	}
	public byte[] getAdv_txt() {
		return adv_txt;
	}
	public void setAdv_txt(byte[] adv_txt) {
		this.adv_txt = adv_txt;
	}
	
	
	


	
}
