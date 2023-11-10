package agile_project;

import java.time.LocalDate;

public class Order {
    private int orderID;
    private LocalDate orderDate;
    private String type;
    private int pubID;
    private String title;
    private double pubPrice;
    private int custID;
    private String custName;
    
    public Order(int orderID, LocalDate orderDate, String type, int pubID, String title, double pubPrice, int custID, String custName) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.type = type;
        this.pubID = pubID;
        this.title = title;
        this.pubPrice = pubPrice;
        this.custID = custID;
        this.custName = custName;
    }
    
    public boolean validOrderID(int orderID) {
        return orderID > 0; // Assuming order IDs are positive integers
    }

    public boolean validOrderDate(LocalDate date) {
        return date.isEqual(LocalDate.now());
    }

    public boolean validType(String type) {
        return type.equals("daily") || type.equals("weekly") || type.equals("monthly");
    }

    public boolean validPubID(int pubID) {
        // Example: Validation logic could involve a database check
        // Return true for simplicity; actual validation requires a database lookup
        return pubID > 0 && pubID <= 1000; // Assuming publication IDs range from 1 to 1000
    }

    public boolean validTitle(String title) {
        // Example: Validation logic checks if the title length is within a specific range
        return title.length() >= 5 && title.length() <= 50; // Assuming title length constraints
    }

    public boolean validPubPrice(double pubPrice) {
        // Example: Validation logic ensures the price is within a reasonable range
        return pubPrice > 0 && pubPrice <= 10000; // Assuming prices range from 0 to 10000
    }

    public boolean validCustID(int custID) {
        // Example: Validation logic could involve a database check
        // Return true for simplicity; actual validation requires a database lookup
        return custID > 0 && custID <= 500; // Assuming customer IDs range from 1 to 500
    }

    public boolean validCustName(String custName) {
        // Example: Validation logic checks if the name length is within a specific range
        return custName.length() >= 3 && custName.length() <= 50; // Assuming name length constraints
    }
    
    // Other methods, constructor, or further functionality can be added here
}
