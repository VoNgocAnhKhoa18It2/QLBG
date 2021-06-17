package com.qlgiay.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qlgiay.model.Product;

public class ProductDAOImpl implements ProductDAO {
	
	private Connection con;

	public ProductDAOImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public List<Product> getProductRamdom(int n) {
		List<Product> listProduct = new ArrayList<Product>();
		try {
			String sql="Select * from sanpham ORDER BY RAND() LIMIT "+n;
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setMasp(rs.getString(1));
				product.setTensp(rs.getString(2));
				product.setGia(rs.getString(3));
				product.setKhuyenMai(rs.getString(4));
				product.setSoLuong(rs.getString(5));
				product.setAnh(rs.getString(6));
				listProduct.add(product);
			}
			return listProduct;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
