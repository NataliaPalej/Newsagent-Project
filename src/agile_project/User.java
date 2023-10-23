package agile_project;

public class User {
	private String username, password, role;
	
	// User constructor 
	public User(String username, String password, String role) throws NataliaException {
		this.username = username;
	    this.password = password;
	    this.role = role;
	}
	
	/** 
	 * User methods
	 * @param username between 1-10 characters
	 * @param password between 6-10 characters 
	 * @param role : admin/newsagent/driver
	 */
	
	public static User createUserAccount(String username, String password, String role) throws NataliaException {
		return new User(username, password, role);
	}
	
	/**
	 * Getters and setters
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
