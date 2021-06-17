package com.qlgiay.DAO;

import java.util.List;

import com.qlgiay.model.Product;

public interface ProductDAO {
	public List<Product> getProductRamdom(int n);
}
