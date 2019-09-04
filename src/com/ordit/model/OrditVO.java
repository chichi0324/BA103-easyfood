package com.ordit.model;

public class OrditVO  implements java.io.Serializable {
	
private String ord_no;
private String dish_no;
private Integer ordit_qua;
private Double ordit_pri;

public String getOrd_no() {
	return ord_no;
}
public void setOrd_no(String ord_no) {
	this.ord_no = ord_no;
}
public String getDish_no() {
	return dish_no;
}
public void setDish_no(String dish_no) {
	this.dish_no = dish_no;
}
public Integer getOrdit_qua() {
	return ordit_qua;
}
public void setOrdit_qua(Integer ordit_qua) {
	this.ordit_qua = ordit_qua;
}
public Double getOrdit_pri() {
	return ordit_pri;
}
public void setOrdit_pri(Double ordit_pri) {
	this.ordit_pri = ordit_pri;
}

}
