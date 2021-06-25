package com.qlgiay.model;

import java.io.Serializable;

public class Cart implements Serializable{
	private String id;
	private int sl;
	private int gia;
	
	public Cart() {
		
	}

	public Cart(String id, int sl, int gia) {
		super();
		this.id = id;
		this.sl = sl;
		this.gia = gia;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}
	
	@Override
	public String toString() {
		return this.id;
	}

}
