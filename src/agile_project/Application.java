/**
 * 
 */
package agile_project;

import java.util.Scanner;
import agile_project.FINISHED.DatabaseConnector;

public class Application extends DatabaseConnector {
	
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println(" ---------------------------------------");
        System.out.println("| Welcome to the Newsagent Application! |");
        System.out.println("|---------------------------------------|");
        
        System.out.println("|              Login                    |");
        System.out.print("|        Enter username:                |");
        String username = in.next();
        System.out.print("|        Enter password:                |");
        String password = in.next();
        System.out.println(" ---------------------------------------\n");
		
	}
}
