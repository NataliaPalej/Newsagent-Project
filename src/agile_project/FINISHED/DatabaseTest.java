package agile_project.FINISHED;

import java.sql.*;

public class DatabaseTest {
	
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/newsagentdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName(DB_DRIVER);
		Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		Statement query = connection.createStatement();
		ResultSet resultSet = query.executeQuery("Select * from customerDetails");
		
		// Print customerDetails table to check connectivity
		try {
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "  "+resultSet.getString(2)+"  "+resultSet.getString(3));
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e);
		}
		finally 
		{
			if (resultSet != null) {
				resultSet.close();
				}
			if (query != null) {
				query.close();
				}
			if (connection != null) {
				connection.close();
				}
		}
	}
}