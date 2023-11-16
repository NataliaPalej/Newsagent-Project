package agile_project;

import java.sql.SQLException;
import java.util.Scanner;

public class main {

	private int id;
	private static String title;
	private static int issueNo;
	private static String author;
	private static double price;
	private static int stock;

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws RonanException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("Hello, enter pub deets");
		Publication pub = new Publication();
		pub.createPublication();
	}

}
