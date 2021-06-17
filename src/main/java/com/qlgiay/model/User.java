package com.qlgiay.model;

import java.io.Serializable;

public class User implements Serializable {
	
	private String id;
	private String name;
	private String birth;
	private String gioiTinh;
	private String email;
	private String password;
	
	public User() {
	}

	public User(String id, String name, String birth, String gioiTinh, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
