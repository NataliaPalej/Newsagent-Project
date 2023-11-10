package agile_project;

import java.time.LocalDate;

public class Invoice {
    private int invoiceID;
    private LocalDate invoiceDate;
    private int custID;
    private String custName;
    private String custAddress;
    private int quantityDelivered;
//    Summary of pubPrice;
    private double totalValue;

    public Invoice(int invoiceID, LocalDate invoiceDate, int custID, String custName,String custAddress,int quantityDelivered,double totalValue) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.custID = custID;
        this.custName = custName;
        this.custAddress = custAddress;
        this.quantityDelivered = quantityDelivered;
        this.totalValue = totalValue;
    }

    public boolean validInvoiceID(int invoiceID) {
        return invoiceID > 0; // Assuming invoice is positive integers
    }
    public boolean validInvoiceDate(LocalDate date) {
        return date.isEqual(LocalDate.now());
    }
    
    public boolean validCustID(int custID) {
        return custID > 0; // Assuming custID is positive integers
    }
    
    public boolean validCustName(String custName) {
        // Example: Validation logic checks if the name length is within a specific range
        return custName.length() >= 3 && custName.length() <= 50; // Assuming name length constraints
    }
    
    public boolean validcustAddress(String custAddress) {
        // Example: Validation logic checks if the address length is within a specific range
        return custAddress.length() >= 3 && custAddress.length() <= 50; // Assuming address length constraints
    }
    
    public boolean validQuantityDelivered(int quantityDelivered) {
        // Summary of all price values from selected customer by custID orders.
        return quantityDelivered>0; // Assuming address length constraints
    }
    
    public boolean ValidTotalValue(int totalValue) {
        return  totalValue>0;
    }
    

}
