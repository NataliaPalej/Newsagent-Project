package agile_project;

import java.sql.*;

public class Application_Newsagent extends DatabaseConnector {

	static Connection connection = null;

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws NataliaException, SQLException, RonanException {
		Options gui = new Options();
		gui.loginScreen();
	}
}