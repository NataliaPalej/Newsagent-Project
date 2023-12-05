package agile_project.GUI;

import java.sql.*;

import agile_project.DatabaseConnector;
import agile_project.Exceptions.NataliaException;
import agile_project.Exceptions.RonanException;

public class Application_Newsagent extends DatabaseConnector {

	static Connection connection = null;

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws NataliaException, SQLException, RonanException {
		Options gui = new Options();
		gui.loginScreen();
	}
}