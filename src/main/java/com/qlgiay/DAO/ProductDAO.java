package com.qlgiay.DAO;

import java.util.List;

import com.qlgiay.model.Product;
import com.qlgiay.model.Category;

public interface ProductDAO {
	public List<Product> getProductRamdom(int n);
	public List<Product> getProduct();
	public List<Category> getCategory();
	public List<Product> getProductFromCart(String s);
	public Product getProductById(String id);
}
