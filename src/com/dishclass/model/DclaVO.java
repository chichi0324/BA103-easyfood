package com.dishclass.model;

public class DclaVO implements java.io.Serializable {
	
	private String dcla_no;
	private String dcla_name;
	
	public DclaVO() {
		
	}
	
	
	public DclaVO(String dcla_no, String dcla_name) {
		this.dcla_no = dcla_no;
		this.dcla_name = dcla_name;
	}


	public String getDcla_no() {
		return dcla_no;
	}


	public void setDcla_no(String dcla_no) {
		this.dcla_no = dcla_no;
	}


	public String getDcla_name() {
		return dcla_name;
	}


	public void setDcla_name(String dcla_name) {
		this.dcla_name = dcla_name;
	}

	
}
