package com.vision.perceptor.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseTest {
	String userName = "student";
	String password = "student";

	String url = "jdbc:mysql://127.0.0.1:3306/homedb?autoReconnect=true&useSSL=false&serverTimezone=UTC";

	public void connect() {
		System.out.println("start connect >>>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			Connection conn = DriverManager.getConnection(url, userName, password);

			Statement statement = conn.createStatement();
			String queryString = "select * from test_table";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
