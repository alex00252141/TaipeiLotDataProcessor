package com.example.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.type.ParkerDataObject;

public class InsertToDB {

	ConnectionManager connectionManager;

	Connection connection;

	public InsertToDB() {

		connectionManager = new ConnectionManager();
		connectionManager.openConnection();
		connection = connectionManager.connection;

		System.out.println("start connecting...");
	}

	public void InserToDB(ParkerDataObject parkerDataObject) {
		final String query = 
				" insert into parker_new ( originalId, taiwanCity, taiwanArea, name,type,summary,address,xAxis,yAxis,tel,payex,totalCar,totalMotor,serviceTime,feeInfo,simpleFeeType,simpleFee)"
				+ " values ( ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {

			PreparedStatement preparedStmt = connection.prepareStatement(query);

			preparedStmt.setString(1, parkerDataObject.getOriginalId());
			preparedStmt.setString(2, parkerDataObject.getTaiwanCity());
			preparedStmt.setString(3, parkerDataObject.getTaiwanArea());
			preparedStmt.setString(4, parkerDataObject.getName());
			preparedStmt.setString(5, parkerDataObject.getType());
			preparedStmt.setString(6, parkerDataObject.getSummary());
			preparedStmt.setString(7, parkerDataObject.getAddress());
			preparedStmt.setDouble(8, parkerDataObject.getXAxis());
			preparedStmt.setDouble(9, parkerDataObject.getYAxis());
			preparedStmt.setString(10, parkerDataObject.getTel());
			preparedStmt.setString(11, parkerDataObject.getPayex());
			preparedStmt.setInt(12, parkerDataObject.getTotalCar());
			preparedStmt.setInt(13, parkerDataObject.getTotalMotor());
			preparedStmt.setString(14, parkerDataObject.getServiceTime());
			preparedStmt.setString(15, parkerDataObject.getfeeInfo());
			preparedStmt.setString(16, parkerDataObject.getSimpleFeeType());
			preparedStmt.setInt(17, parkerDataObject.getSimpleFee());
			// execute the preparedstatement
			preparedStmt.execute();
			System.out.println("insert successful !");

		} catch (SQLException e) {
			System.out.println("sql connect error");
			System.out.println(e.getMessage());
			return;
		}
	}

}
