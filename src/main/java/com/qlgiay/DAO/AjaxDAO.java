package com.qlgiay.DAO;

import java.util.List;

import com.qlgiay.model.Product;

public interface AjaxDAO {
	public boolean checkEmail(String email);
	public List<Product> getProductForCategoru(String id);
}
