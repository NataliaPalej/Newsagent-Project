
public class Customer {
	
	private int id;
	private String name;
	private String address;
	private String phoneNumber;
	
	void setId(int custId) {
		id = custId;
	}
	
	void setName(String custName) {
		name = custName;
	}
	
	void setAddress(String custAddr) {
		address = custAddr;
	}
	
	void setPhoneNumber(String custPhone) {
		phoneNumber = custPhone;
	}
	
	int getId() {
		return id;
	}
	
	String getName() {
		return name;
	}
	
	String getAddress() {
		return address;
	}
	
	String getPhoneNumber() {
		return phoneNumber;
		
	}
	
	public Customer(String custName, String custAddr, String custPhone) throws CustomerExceptionHandler  {
		
		id = 0;
		
		// Validate Input
		try {
			
			validateName(custName);
			validateAddress(custAddr);
			validatePhoneNumber(custPhone);
			
		}
		catch (CustomerExceptionHandler e) {
			throw e;
		}
		
		// Set Attributes
		name = custName;
		address = custAddr;
		phoneNumber = custPhone;
	}
	
	public static void validateName(String custName) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Name"
		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
		
		if (custName.isBlank() || custName.isEmpty())
			throw new CustomerExceptionHandler("Customer Name NOT specified");
		else if (custName.length() < 2)
			throw new CustomerExceptionHandler("Customer Name does not meet minimum length requirements");
		else if (custName.length() > 50)
			throw new CustomerExceptionHandler("Customer Name does not exceeds maximum length requirements");
		
	}
	
	public static void validateAddress(String custAddr) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Address"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (custAddr.isBlank() || custAddr.isEmpty())
			throw new CustomerExceptionHandler("Customer Address NOT specified");
		else if (custAddr.length() < 5)
			throw new CustomerExceptionHandler("Customer Address does not meet minimum length requirements");
		else if (custAddr.length() > 60)
			throw new CustomerExceptionHandler("Customer Address does not exceeds maximum length requirements");
		
	}
	
	public static void validatePhoneNumber(String custPhone) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer PhoneNumber"
		//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
		
		if (custPhone.isBlank() || custPhone.isEmpty())
			throw new CustomerExceptionHandler("Customer PhoneNumber NOT specified");
		else if (custPhone.length() < 7)
			throw new CustomerExceptionHandler("Customer PhoneNumber does not meet minimum length requirements");
		else if (custPhone.length() > 15)
			throw new CustomerExceptionHandler("Customer PhoneNumber does not exceeds maximum length requirements");
		
	}
	

}
