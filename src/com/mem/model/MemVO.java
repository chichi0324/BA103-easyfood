package com.mem.model;

public class MemVO implements java.io.Serializable {

	private String mem_no;
	private String mem_acc;
	private String mem_pw;
	private String mem_name;
	private String mem_pho;
	private String mem_mail;
	private Integer mem_vio;
	private String mem_stas;
	private byte[] mem_img;

	public byte[] getMem_img() {
		return mem_img;
	}

	public void setMem_img(byte[] mem_img) {
		this.mem_img = mem_img;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_acc() {
		return mem_acc;
	}

	public void setMem_acc(String mem_acc) {
		this.mem_acc = mem_acc;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_pho() {
		return mem_pho;
	}

	public void setMem_pho(String mem_pho) {
		this.mem_pho = mem_pho;
	}

	public String getMem_mail() {
		return mem_mail;
	}

	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}

	public Integer getMem_vio() {
		return mem_vio;
	}

	public void setMem_vio(Integer mem_vio) {
		this.mem_vio = mem_vio;
	}

	public String getMem_stas() {
		return mem_stas;
	}

	public void setMem_stas(String mem_stas) {
		this.mem_stas = mem_stas;
	}
}
