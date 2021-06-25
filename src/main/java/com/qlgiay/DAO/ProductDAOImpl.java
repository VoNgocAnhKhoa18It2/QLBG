package com.qlgiay.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qlgiay.model.Category;
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

	@Override
	public List<Product> getProduct() {
		List<Product> listProduct = new ArrayList<Product>();
		try {
			String sql="Select * from sanpham";
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

	@Override
	public List<Category> getCategory() {
		List<Category> categoties = new ArrayList<Category>();
		try {
			String sql="SELECT * FROM `danhmuc`";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Category product = new Category();
				product.setId(rs.getString(1));
				product.setName(rs.getString(2));
				categoties.add(product);
			}
			return categoties;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> getProductFromCart(String s) {
		List<Product> listProduct = new ArrayList<Product>();
		try {
			String sql="SELECT * FROM `sanpham` WHERE `masp` IN("+s+")";
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

	@Override
	public Product getProductById(String id) {
		try {
			String sql="SELECT * FROM `sanpham` WHERE `masp`=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			Product product = new Product();
			if(rs.next()) {
				product.setMasp(rs.getString(1));
				product.setTensp(rs.getString(2));
				product.setGia(rs.getString(3));
				product.setKhuyenMai(rs.getString(4));
				product.setSoLuong(rs.getString(5));
				product.setAnh(rs.getString(6));
			}
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
