package agile_project;

import java.sql.*;

public class DatabaseConnector {
	
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/newsagentdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() {
		try {
			Class.forName(DB_DRIVER);
			return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		}
		catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("COULDN'T CONNECT TO DATABASE:\n " + e.getMessage());
		}
	}
}