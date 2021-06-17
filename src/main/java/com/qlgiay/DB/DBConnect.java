package com.qlgiay.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBConnect {
	private static Connection conn;
	public static Connection getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc"
						+ ":mysql://localhost:3306/qlbg?serverTimezone=UTC","root","");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}