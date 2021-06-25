package com.qlgiay.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qlgiay.model.Product;

public class AjaxDAOImpl implements AjaxDAO {
	private Connection con;

	public AjaxDAOImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public boolean checkEmail(String email) {
		try {
			String sql="select * from account where email=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> getProductForCategoru(String id) {
		try {
			List<Product> listProduct = new ArrayList<Product>();
			String sql = "";
			if (id.isEmpty()){
				sql = "SELECT * FROM sanpham";
			} else {
				sql = "SELECT * FROM `sanpham` where iddanhmuc in ("+id+")";
			}
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
