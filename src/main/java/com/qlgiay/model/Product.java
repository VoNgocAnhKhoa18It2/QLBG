package com.qlgiay.model;

import java.io.Serializable;

public class Product implements Serializable {
	private String masp;
	private String tensp;
	private String gia;
	private String khuyenMai;
	private String soLuong;
	private String anh;
	private String idDanhMuc;
	private String chiTiet;
	
	public Product() {
	}

	public Product(String masp, String tensp, String gia, String khuyenMai, String soLuong, String anh,
			String idDanhMuc, String chiTiet) {
		super();
		this.masp = masp;
		this.tensp = tensp;
		this.gia = gia;
		this.khuyenMai = khuyenMai;
		this.soLuong = soLuong;
		this.anh = anh;
		this.idDanhMuc = idDanhMuc;
		this.chiTiet = chiTiet;
	}

	public String getMasp() {
		return masp;
	}

	public void setMasp(String masp) {
		this.masp = masp;
	}

	public String getTensp() {
		return tensp;
	}

	public void setTensp(String tensp) {
		this.tensp = tensp;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(String khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public String getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getIdDanhMuc() {
		return idDanhMuc;
	}

	public void setIdDanhMuc(String idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}

	public String getChiTiet() {
		return chiTiet;
	}

	public void setChiTiet(String chiTiet) {
		this.chiTiet = chiTiet;
	}

}
