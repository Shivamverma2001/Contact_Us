package com.contactUs.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDao {
	String url = "jdbc:mysql://localhost:3306/contactUs";
	String user = "root";
	String mysqlPassword = "8055";
	

	public void insert(String name, String email, String message, boolean active) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
		
		String query="INSERT INTO messageinfo (name, email, message,active) VALUES (?, ?, ?, true)";
		Connection connection=DriverManager.getConnection(url,user,mysqlPassword);
		PreparedStatement statement=connection.prepareStatement(query);
		
		statement.setString(1,name);
		statement.setString(2, email);
		statement.setString(3,message);
		
		statement.execute();
	}
	
	public boolean check(String email, String pass) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String query="Select * from adminlogin where email=? and password=?;";
		Connection connection=DriverManager.getConnection(url,user,mysqlPassword);
		PreparedStatement statement=connection.prepareStatement(query);
		
		statement.setString(1, email);
		statement.setString(2, pass);
		
		ResultSet resultSet=statement.executeQuery();
		
		if(resultSet.next()) {
			return true;
		}
		
		return false;
	}
	
	public ResultSet getMessangerData() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String query="Select * from messageinfo";
		Connection connection=DriverManager.getConnection(url,user,mysqlPassword);
		Statement statement=connection.createStatement();
		
		ResultSet resultSet=statement.executeQuery(query);
		
		return resultSet;
	}
	
	public void changeTypeOfMessage(int id) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String query="Update messageinfo set active=not active where id=?";
		Connection connection=DriverManager.getConnection(url,user,mysqlPassword);
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		
		preparedStatement.setInt(1, id);
		
		preparedStatement.execute();
	}

}
