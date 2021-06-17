package com.qlgiay.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.qlgiay.model.User;

public class UserDAOImpl implements UserDAO {
	
	private Connection con;

	public UserDAOImpl(Connection conn) {
		super();
		this.con = conn;
	}

	@Override
	public boolean userRegister(User us) {
		try {
			String sql="INSERT INTO `account`(`name`, `birth`, `gt`, `email`, `password`) VALUES (?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, us.getName());
			ps.setString(2, us.getBirth());
			ps.setString(3, us.getGioiTinh());
			ps.setString(4, us.getEmail());
			ps.setString(5, us.getPassword());
			
			int i=ps.executeUpdate();
			if(i==1)
			{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User login(String email, String password) {
		try {
			User us=new User();
			String sql="select * from account where email=? and password=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				us.setId(rs.getString(1));
				us.setName(rs.getString(2));
				us.setBirth(rs.getString(3));
				us.setGioiTinh(rs.getString(4));
				us.setEmail(rs.getString(5));
				us.setPassword(rs.getString(6));
			}
			return us;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
