package agile_project;

import java.sql.*;

public class Application_Newsagent extends DatabaseConnector {

	static Connection connection = null;

	public static void main(String[] args) throws NataliaException, SQLException {
		Options gui = new Options();
		gui.loginScreen();
	}
}