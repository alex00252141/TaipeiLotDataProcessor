package com.example.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {
	private final String localhost = "127.0.0.1";
	private final String post = "3306";
	private final String DBName = "testparker";
	private final String user = "root";
	private final String password = "";
	
	
	private final String url = "jdbc:mysql://"+localhost+":"+post+"/"+DBName+"?"
			+ "user="+user
			+"&password="+password
			+"&useUnicode=true&characterEncoding=utf-8";

	Connection connection;
	
	public ConnectionManager(){
		 connection = null;
	}
	

	public void openConnection()	
	{
		System.out.println("-------- MySQL JDBC Connection ------------");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found !!");
		    return;
		}
		   System.out.println("MySQL JDBC Driver Registered!");
		  
		try 
		{
			   connection = DriverManager //jdbc:mysql://localhost:3306/data
		    		.getConnection(url);
		   
		    System.out.println("SQL Connection to database established!");
		         
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			System.out.println(e.getMessage());
			return;
		} 
	
	}
	
	public void closeConnection(){
		
		try{
	    	if(connection != null)connection.close();
	    	System.out.println("Connection closed !!");
	    } catch (SQLException e) {
	         e.printStackTrace();
	    }
		
		
	}
	

}
