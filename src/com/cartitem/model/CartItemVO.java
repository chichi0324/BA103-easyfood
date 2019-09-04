package com.cartitem.model;

public class CartItemVO implements java.io.Serializable {

	private String mem_no;
	private String dish_no;
	private String dish_name;
	private Double dish_price;
	private String dcla_no;
	private String str_no;
	private Integer quantity;




	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getDish_no() {
		return dish_no;
	}

	public void setDish_no(String dish_no) {
		this.dish_no = dish_no;
	}

	public String getDish_name() {
		return dish_name;
	}

	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}

	public Double getDish_price() {
		return dish_price;
	}

	public void setDish_price(Double dish_price) {
		this.dish_price = dish_price;
	}

	public String getDcla_no() {
		return dcla_no;
	}

	public void setDcla_no(String dcla_no) {
		this.dcla_no = dcla_no;
	}

	public String getStr_no() {
		return str_no;
	}

	public void setStr_no(String str_no) {
		this.str_no = str_no;
	}

}
