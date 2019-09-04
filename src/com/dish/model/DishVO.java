package com.dish.model;

public class DishVO implements java.io.Serializable {
	
	private String dish_no;
	private String dish_name;
	private Double dish_price;
	private String dcla_no;
	private String str_no;
	private String dish_status;
	private byte[] dish_img;
	private String dish_note;
	
	public DishVO() {
		
	}

	public DishVO(String dish_no, String dish_name, Double dish_price, String dcla_no, String str_no,
			String dish_status, byte[] dish_img, String dish_note) {
	
		this.dish_no = dish_no;
		this.dish_name = dish_name;
		this.dish_price = dish_price;
		this.dcla_no = dcla_no;
		this.str_no = str_no;
		this.dish_status = dish_status;
		this.dish_img = dish_img;
		this.dish_note = dish_note;
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

	public String getDish_status() {
		return dish_status;
	}

	public void setDish_status(String dish_status) {
		this.dish_status = dish_status;
	}

	public byte[] getDish_img() {
		return dish_img;
	}

	public void setDish_img(byte[] dish_img) {
		this.dish_img = dish_img;
	}

	public String getDish_note() {
		return dish_note;
	}

	public void setDish_note(String dish_note) {
		this.dish_note = dish_note;
	}
	
}
