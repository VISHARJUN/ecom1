package com.ecomz.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MasterDAL {
	public static Connection getConnectionToDatabase(boolean localDBServer) {
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection connection = null;
			try {
				if (localDBServer) {
					connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
				} else {
					connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.210:1521:orcl", "hr", "hr");
				}
			} catch (SQLException sqlException) {
				System.err.println("MasterDAL:getConnectionToDatabase():" + sqlException);
			}
			return connection;
		} catch (Exception exception) {
			System.err.println("MasterDAL:getConnectionToDatabase(boolean localDBServer):" + exception);
		}
		return null;
	}

	public static int insertRow(String insertQuery) {
		try {
			System.out.println("MasterDAL:insertRow(String insertQuery):" + insertQuery);
			Statement insertStatement = null;
			int noOfRowsInserted = 0;
			try {
				insertStatement = getConnectionToDatabase(true).createStatement();
				noOfRowsInserted = insertStatement.executeUpdate(insertQuery);
				return noOfRowsInserted;
			} catch (SQLException sqlException) {
				System.err.println("MasterDAL:insertRow(String insertQuery):" + sqlException);
				return noOfRowsInserted;
			}
		} catch (Exception exception) {
			System.err.println("MasterDAL:insertRow(String insertQuery):" + exception);
		}
		return 0;
	}

	public static int updateRow(String updateQuery) {
		try {
			System.out.println("MasterDAL:updateRow(String updateQuery):" + updateQuery);
			Statement updateStatement = null;
			int noOfRowsUpdated = 0;
			try {
				updateStatement = getConnectionToDatabase(true).createStatement();
				noOfRowsUpdated = updateStatement.executeUpdate(updateQuery);
				return noOfRowsUpdated;
			} catch (SQLException sqlException) {
				System.err.println("MasterDAL:updateRow(String updateQuery):" + sqlException);
				return noOfRowsUpdated;
			}
		} catch (Exception exception) {
			System.err.println("MasterDAL:updateRow(String updateQuery):" + exception);
		}
		return 0;
	}

	public static int deleteRow(String deleteQuery) {
		try {
			System.out.println("MasterDAL:deleteRow(String deleteQuery):" + deleteQuery);
			Statement deleteStatement = null;
			int noOfRowsdeleted = 0;
			try {
				deleteStatement = getConnectionToDatabase(true).createStatement();
				noOfRowsdeleted = deleteStatement.executeUpdate(deleteQuery);
				return noOfRowsdeleted;
			} catch (SQLException sqlException) {
				System.err.println("MasterDAL:deleteRow(String deleteQuery):" + sqlException);
				return noOfRowsdeleted;
			}
		} catch (Exception exception) {
			System.err.println("MasterDAL:deleteRow(String deleteQuery):" + exception);
		}
		return 0;
	}
}
