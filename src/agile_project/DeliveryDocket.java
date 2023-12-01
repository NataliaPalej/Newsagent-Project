package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryDocket extends Order {


	public DeliveryDocket() {
		// TODO Auto-generated constructor stub
	}

	public String printMyDocket() throws NataliaException {

		System.out.println("* ------------------------ *");
		System.out.println("|  Delivery Docket Area:   |");
		System.out.println("* ------------------------ *");

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnector.getConnection();

			String query = "SELECT * FROM customerdetails ORDER BY custID ASC";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			boolean customerList = false;
			String allCustomersDetails = "";

			while (resultSet.next()) {
				customerList = true;
				String custID = resultSet.getString("custID");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String address = resultSet.getString("address");
				String phoneNo = resultSet.getString("phoneNo");

				String formattedCustID = String.format("%-8s", custID);
				String formattedFirstName = String.format("%-15s", firstName);
				String formattedLastName = String.format("%-15s", lastName);
				String formattedAddress = String.format("%-20s", address);
				String formattedPhoneNo = String.format("%-15s", phoneNo);

				System.out.println("Customer ID: " + formattedCustID +
						"First Name: " + formattedFirstName +
						"Last Name: " + formattedLastName +
						"Address: " + formattedAddress +
						"Phone Number: " + formattedPhoneNo);
			}
			if (!customerList) {
				throw new NataliaException("Customer database is empty or not found.");
			}
			return allCustomersDetails.toString();
		} catch (SQLException error) {
			throw new NataliaException("Database error.\n" + error.getMessage());
		} finally {
			try {
				if (resultSet != null) resultSet.close();
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				throw new NataliaException("Error while closing database resources.\n" + e.getMessage());
			}
		}
	}


}
