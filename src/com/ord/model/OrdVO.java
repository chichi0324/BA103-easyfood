package com.ord.model;

import java.sql.Timestamp;

public class OrdVO implements java.io.Serializable {
	private String ord_no;
	private String mem_no;
	private String str_no;
	private Integer ord_ev;
	private String ord_type;
	private Double ord_pri;
	private Timestamp ord_date;
	private String ord_stat;
	private String ord_eva;
	private String ord_add;
	
	public String getOrd_add() {
		return ord_add;
	}

	public void setOrd_add(String ord_add) {
		this.ord_add = ord_add;
	}

	public String getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
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

	public Integer getOrd_ev() {
		return ord_ev;
	}

	public void setOrd_ev(Integer ord_ev) {
		this.ord_ev = ord_ev;
	}

	public String getOrd_type() {
		return ord_type;
	}

	public void setOrd_type(String ord_type) {
		this.ord_type = ord_type;
	}

	public Double getOrd_pri() {
		return ord_pri;
	}

	public void setOrd_pri(Double ord_pri) {
		this.ord_pri = ord_pri;
	}

	public Timestamp getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(Timestamp ord_date) {
		this.ord_date = ord_date;
	}

	public String getOrd_stat() {
		return ord_stat;
	}

	public void setOrd_stat(String ord_stat) {
		this.ord_stat = ord_stat;
	}

	public String getOrd_eva() {
		return ord_eva;
	}

	public void setOrd_eva(String ord_eva) {
		this.ord_eva = ord_eva;
	}

}
