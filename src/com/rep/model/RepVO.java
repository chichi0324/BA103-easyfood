package com.rep.model;

public class RepVO implements java.io.Serializable {

	private String rep_no;
	private String rep_res;
	private String rep_rev;
	private String ord_no;
	
	//===========join用===============
	private String str_no;
	private String mem_no;
	//==========================
	
	public String getRep_no() {
		return rep_no;
	}
	public void setRep_no(String rep_no) {
		this.rep_no = rep_no;
	}
	public String getRep_res() {
		return rep_res;
	}
	public void setRep_res(String rep_res) {
		this.rep_res = rep_res;
	}
	public String getRep_rev() {
		return rep_rev;
	}
	public void setRep_rev(String rep_rev) {
		this.rep_rev = rep_rev;
	}
	public String getOrd_no() {
		return ord_no;
	}
	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}
	
	//===========join用===============
	public String getStr_no() {
		return str_no;
	}
	public void setStr_no(String str_no) {
		this.str_no = str_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	
	

}
