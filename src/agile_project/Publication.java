package agile_project;

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
	
	public void createNewPublication(String title, int issueNo, String author, double price, int quantity) throws RonanException {
//		Publication p = new Publication(id, title, issueNo, author, price, quantity);
		throw new RonanException("createNewPublication() not implemented");
	}
	
	public void updatePublication() throws RonanException {
		throw new RonanException("updatePublication() not implemented");
	}
	
	public void deletePublication() throws RonanException {
		throw new RonanException("deletePublication() not implemented");
	}

	public void readPublication() throws RonanException {
		throw new RonanException("readPublication() not implemented");
	}
	
	public void updateStock() throws RonanException {
		throw new RonanException("updateStock() not implemented");
	}
	
	public boolean isValidStock(int stock) throws RonanException {
		if (stock >= 0) {
			return true;
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
