package agile_project;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Publication {
	
	private int id;
	private String title;
	private int issueNo;
	private String author;
	private double price;
	private int stock;
	
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
	
	public void createNewPublication() throws RonanException {
		throw new RonanException("createNewPublication() not implemented");
	}
	
	public void updatePublication() throws RonanException {
		throw new RonanException("updatePublication() not implemented");
	}
	
	public void deletePublication() throws RonanException {
		throw new RonanException("deletePublication() not implemented");
	}

	public Publication getPublicationById(int id) throws RonanException {
//		throw new RonanException("getPublicationById() not implemented");
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
				// Retrieve customer details from the result set
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
	
	public void updateStock() throws RonanException {
		throw new RonanException("updateStock() not implemented");
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
