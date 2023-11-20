package agile_project;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Publication {

	private int id;
	private String title;
	private int issueNo;
	private String author;
	private double price;
	private int stock;

	static Scanner in = new Scanner(System.in);

	public Publication() throws RonanException {
		// TODO Auto-generated constructor stub

	}

	public Publication(String title, int issueNo, String author, double price, int stock) throws RonanException {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.issueNo = issueNo;
		this.author = author;
		this.price = price;
		this.stock = stock;
	}

	public void createPublication() throws RonanException, SQLException{

		System.out.println("* ---------------------- *");
		System.out.println("|   Create Publication   |");
		System.out.println("* ---------------------- *");

		System.out.print("Enter Title: ");
		String title = in.next();
		if (!isValidString(title)) {
			throw new RonanException("Invalid title. Name must be between 1-50 characters.");
		}
		System.out.print("Enter Issue No.: ");
		int issueNo = in.nextInt();
		if (!isValidInt(issueNo)) {
			throw new RonanException("Invalid last name. Issue No. must be greater than 0.");
		}
		System.out.print("Enter Author: ");
		String author = in.next();
		if (!isValidString(author)) {
			throw new RonanException("Invalid author. Author must be between 1-50 characters.");
		}
		System.out.print("Enter Price (€): ");
		double price = in.nextDouble();
		if (!isValidPrice(price)) {
			throw new RonanException("Invalid Price. Price must be in format 4.99.");
		}
		System.out.print("Enter Stock: ");
		int stock = in.nextInt();
		if (!isValidInt(stock)) {
			throw new RonanException("Invalid Stock. Stock must be greater than 0.");
		}

		Connection connection = null;
		try {
			connection = DatabaseConnector.getConnection();
			String query = "INSERT INTO publications (title, issueNo, author, price, stock) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, title);
			stmt.setInt(2, issueNo);
			stmt.setString(3, author);
			stmt.setDouble(4, price);
			stmt.setInt(5, stock);
			stmt.executeUpdate();

			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int publicationId = generatedKeys.getInt(1);
				this.setId(publicationId);
				this.setTitle(title);
				this.setIssueNo(issueNo);
				this.setAuthor(author);
				this.setPrice(price);
				this.setStock(stock);

				System.out.println("Publication: " + this.getId() + ", " + this.getTitle() + " #" + this.getIssueNo() + " was successfully created!");
			}
		} catch (SQLException e) {
			throw new RonanException("Couldn't create Publication.\n" + e.getMessage());
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}


	public void updatePublication(int id) throws RonanException, SQLException {

		System.out.println("* ---------------------- *");
		System.out.println("|   Update Publication   |");
		System.out.println("* ---------------------- *");

		String query = "SELECT * FROM publications WHERE publicationID = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String publicationID = resultSet.getString("publicationID");
				String title = resultSet.getString("title");

				System.out.println("Are you sure you want to update publication " + publicationID + ": " + title + "? (Y/N)");
				String answer = in.next();

				if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
					System.out.println("OPTIONS:\n1. UPDATE title\n2. UPDATE issueNo\n3. UPDATE author\n4. UPDATE price\n5. UPDATE stock");
					System.out.print("Enter option [1/2/3/4/5]: ");
					int option = in.nextInt();

					String updateColumn = "";
					String updatePrompt = "";
					String successMessage = "";

					switch (option) {
					case 1:
						updateColumn = "title";
						updatePrompt = "Enter new title: ";
						successMessage = "Title updated successfully. New Title: ";
						break;
					case 2:
						updateColumn = "issueNo";
						updatePrompt = "Enter new issue no.: ";
						successMessage = "Issue No. updated successfully. New issue no.: ";
						break;
					case 3:
						updateColumn = "author";
						updatePrompt = "Enter new author: ";
						successMessage = "Author updated successfully. New author: ";
						break;
					case 4:
						updateColumn = "price";
						updatePrompt = "Enter new price: ";
						successMessage = "Price updated successfully. New price: ";
						break;
					case 5:
						updateColumn = "stock";
						updatePrompt = "Enter new stock: ";
						successMessage = "Stock updated successfully. New stock: ";
						break;
					default:
						System.out.println("Invalid option.");
						return;
					}
					System.out.println(updatePrompt);
					// Allow for spaces in new input
					in.nextLine();
					String newValue = in.nextLine();

					String updateQuery = "UPDATE publications SET " + updateColumn + " = ? WHERE publicationID = ?";
					preparedStatement = connection.prepareStatement(updateQuery);
					preparedStatement.setString(1, newValue);
					preparedStatement.setInt(2, id);
					preparedStatement.executeUpdate();
					System.out.println(successMessage + newValue);
				}
			}
		}
		catch (SQLException e) {
			throw new RonanException("Database error: " + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RonanException("Error while closing database resources: " + e.getMessage());
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public Publication getPublicationById(int id) throws RonanException {
		System.out.println("* ---------------------------- *");
		System.out.println("|  Print Publication Details   |");
		System.out.println("* ---------------------------- *");

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Publication publicationObj = new Publication();

		try {
			connection = DatabaseConnector.getConnection();

			String query = "SELECT * FROM publications WHERE publicationID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				// Retrieve publication details from the result set
				publicationObj.setId(id);
				publicationObj.setTitle(resultSet.getString("title"));
				publicationObj.setIssueNo(resultSet.getInt("issueNo"));
				publicationObj.setAuthor(resultSet.getString("author"));
				publicationObj.setPrice(resultSet.getDouble("price"));
				publicationObj.setStock(resultSet.getInt("stock"));


				System.out.println("Publication ID: " + publicationObj.getId() + "\nTitle: " + publicationObj.getTitle() + "\nIssue No.: " 
						+ publicationObj.getIssueNo() + "\n" + "Author: " + publicationObj.getAuthor() + "\nPrice: " + publicationObj.getPrice()+ "\nStock: " + publicationObj.getStock());
			} else {
				throw new RonanException("Publication with ID(" + publicationObj.getId() + ") NOT found.");
			}
		} catch (SQLException error) {
			throw new RonanException("Database error.\n" + error.getMessage());
		} finally {
			// Close the database resources
			try {
				if (resultSet != null) resultSet.close();
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				// Handle resource closing exceptions
				throw new RonanException("Error while closing database resources.\n" + e.getMessage());
			}
		}
		return publicationObj;
	}

	public String getAllPublications() throws RonanException {

		System.out.println("* ----------------------------- *");
		System.out.println("|  Print All Publications Details  |");
		System.out.println("* ----------------------------- *");

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnector.getConnection();

			String query = "SELECT * FROM publications ORDER BY publicationID ASC";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			boolean publicationList = false;
			String allPublicationsDetails = "";

			while (resultSet.next()) {
				publicationList = true;
				String publicationID = resultSet.getString("publicationID");
				String title = resultSet.getString("title");
				int issueNo = resultSet.getInt("issueNo");
				String author = resultSet.getString("author");
				double price = resultSet.getDouble("price");
				int stock = resultSet.getInt("stock");

				String formattedPublicationID = String.format("%-8s", publicationID);
				String formattedTitle = String.format("%-15s", title);
				String formattedIssueNo = String.format("%-15s", issueNo);
				String formattedAuthor = String.format("%-20s", author);
				String formattedPrice = String.format("%-15s", price);
				String formattedStock = String.format("%-15s", stock);

				System.out.println("Publication ID: " + formattedPublicationID +
						"Title: " + formattedTitle +
						"Issue No.: " + formattedIssueNo +
						"Author: " + formattedAuthor +
						"Price: " + formattedPrice +
						"Stock: " + formattedStock);
			}
			if (!publicationList) {
				throw new RonanException("Publication database is empty or not found.");
			}
			return allPublicationsDetails.toString();
		} catch (SQLException error) {
			throw new RonanException("Database error.\n" + error.getMessage());
		} finally {
			try {
				if (resultSet != null) resultSet.close();
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				throw new RonanException("Error while closing database resources.\n" + e.getMessage());
			}
		}
	}

	public void deletePublication(int id) throws RonanException, SQLException {

		System.out.println("* ------------------- *");
		System.out.println("|   Delete Publication   |");
		System.out.println("* ------------------- *");

		System.out.println("Are you sure you want to delete publication: " + id + "? (Y/N)");
		String answer = in.next();

		Connection connection = null;
		String query = "DELETE FROM publications WHERE publicationID = ?";

		try {
			connection = DatabaseConnector.getConnection();
			if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("YES")){
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setInt(1, id); // Set the value of the userID parameter
				int rowsDeleted = stmt.executeUpdate();
				// stmt.executeUpdate();  //Execute the delete query

				if (rowsDeleted > 0) {
					System.out.println("Publication " + id + " has been successfully deleted.");
				}
			} else {
				System.out.println("Deletion cancelled.");
			}
		} catch (SQLException e) {
			throw new RonanException("Publication " + id + " doesn't exist.");
		} finally {
			connection.close();
		}
	}

	public boolean isValidInt(int x) throws RonanException {
		if (x >= 0) {
			return true;
		}
		return false;
	}

	public boolean isValidPrice(double price) throws RonanException {		
		if (price >= 0.01 & price <= 999.99 & !(BigDecimal.valueOf(price).scale() > 2)) {
			return true;
		}

		return false;
	}

	public boolean isValidString(String string) throws RonanException {
		if (string != null) {
			if (string.length() <= 50 & !(string.isBlank())) {
				return true;
			}
		}
		return false;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getIssueNo() {
		return issueNo;
	}


	public void setIssueNo(int issueNo) {
		this.issueNo = issueNo;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}




}
