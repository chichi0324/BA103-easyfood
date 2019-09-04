package com.storecategory.model;

public class StocaVO implements java.io.Serializable {
	
	private String stoca_no;
	private String stoca_name;
	private byte[] stoca_img;
	private String stoca_note;
	
	public StocaVO() {
		
	}
	
	
	public StocaVO(String stoca_no, String stoca_name, byte[] stoca_img, String stoca_note) {
		this.stoca_no = stoca_no;
		this.stoca_name = stoca_name;
		this.stoca_img = stoca_img;
		this.stoca_note = stoca_note;
	}


	public String getStoca_no() {
		return stoca_no;
	}
	public void setStoca_no(String stoca_no) {
		this.stoca_no = stoca_no;
	}
	public String getStoca_name() {
		return stoca_name;
	}
	public void setStoca_name(String stoca_name) {
		this.stoca_name = stoca_name;
	}
	public byte[] getStoca_img() {
		return stoca_img;
	}
	public void setStoca_img(byte[] stoca_img) {
		this.stoca_img = stoca_img;
	}
	public String getStoca_note() {
		return stoca_note;
	}
	public void setStoca_note(String stoca_note) {
		this.stoca_note = stoca_note;
	}
	
	
}
