package com.cartorder.model;

import java.util.Vector;

import com.cartitem.model.CartItemVO;

public class CartOrderVO {

	private String ord_no;
	private String mem_no;
	private String str_no;
	private String str_name;
	private String str_ship;
	private String ord_type;
	private Double ord_pri;
	private Vector<CartItemVO> buylist;
	private String pro_cat;
	private Double pro_dis;
	private String dcla_no1;
	private String dcla_no2;
	private Integer pro_mon;
	private String add_adds;
	boolean ProMoneyMeet;
	boolean ProClassMeet;
	private Double disAmount;

	public String getStr_ship() {
		return str_ship;
	}

	public void setStr_ship(String str_ship) {
		this.str_ship = str_ship;
	}

	public Double getDisAmount() {
		return disAmount;
	}

	public void setDisAmount(Double disAmount) {
		this.disAmount = disAmount;
	}

	public boolean isProMoneyMeet() {
		return ProMoneyMeet;
	}

	public void setProMoneyMeet(boolean proMoneyMeet) {
		ProMoneyMeet = proMoneyMeet;
	}

	public boolean isProClassMeet() {
		return ProClassMeet;
	}

	public void setProClassMeet(boolean proClassMeet) {
		ProClassMeet = proClassMeet;
	}

	public String getAdd_adds() {
		return add_adds;
	}

	public void setAdd_adds(String add_adds) {
		this.add_adds = add_adds;
	}

	public Integer getPro_mon() {
		return pro_mon;
	}

	public void setPro_mon(Integer pro_mon) {
		this.pro_mon = pro_mon;
	}

	public String getPro_cat() {
		return pro_cat;
	}

	public void setPro_cat(String pro_cat) {
		this.pro_cat = pro_cat;
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

	public String getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}

	public String getStr_name() {
		return str_name;
	}

	public void setStr_name(String str_name) {
		this.str_name = str_name;
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

	public Vector<CartItemVO> getBuylist() {
		return buylist;
	}

	public void setBuylist(Vector<CartItemVO> buylist) {
		this.buylist = buylist;
	}
}
