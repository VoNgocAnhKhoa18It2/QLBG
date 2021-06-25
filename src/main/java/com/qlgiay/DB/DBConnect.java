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
						+ ":mysql://bnqcxj75cvgskosdzwbe-mysql.services.clever-cloud.com:3306"
						+ "/bnqcxj75cvgskosdzwbe?serverTimezone=UTC","usfibaihd1cxi4vf","aUdJG480lb2lNiD6hMFG");
				System.out.println("Thanh Cong");
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