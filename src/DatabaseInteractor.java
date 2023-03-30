import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.time.*;
import RecordTypes.*;

public class DatabaseInteractor {
	private static String DATABASE = "checkpoint_3.db";
	
	public static Scanner scanner;
	private static Connection conn;
	public static ArrayList<Member> members;
	public static ArrayList<Equipment> equipments;
	public static ArrayList<Warehouse> warehouses;
	public static int maxSerialNumber = 0;
	public static int maxInventoryId = 0;
	public static int maxUserId = 0;
	
	/**
     * Connects to the database if it exists, creates it if it does not, and returns the connection object.
     * Used from Lab 2: Embedded SQL
     * 
     * @param databaseFileName the database file name
     * @return a connection object to the designated database
     */
    public static Connection initializeDB(String databaseFileName) {
    	/**
    	 * The "Connection String" or "Connection URL".
    	 * 
    	 * "jdbc:sqlite:" is the "subprotocol".
    	 * (If this were a SQL Server database it would be "jdbc:sqlserver:".)
    	 */
        String url = "jdbc:sqlite:" + databaseFileName;
        Connection conn = null; // If you create this variable inside the Try block it will be out of scope
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
            	// Provides some positive assurance the connection and/or creation was successful.
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("The connection to the database was successful.");
            } else {
            	// Provides some feedback in case the connection failed but did not throw an exception.
            	System.out.println("Null Connection");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("There was a problem connecting to the database.");
        }
        return conn;
    }
	
	public static void main(String[] args)
	{
		scanner = new Scanner(System.in);
		members = new ArrayList<Member>();
		equipments = new ArrayList<Equipment>();
		warehouses = new ArrayList<Warehouse>();
		conn = initializeDB(DATABASE);
		TopLayerMenu(conn);
	}
	
	private static void TopLayerMenu(Connection conn) {
		Integer choice = 0;
		boolean seekingResponse = true; 
		while(seekingResponse) {
			System.out.println("Enter the input corresponding to your desired action:");
			System.out.println("1: Add new record");
			System.out.println("2: Edit record");
			System.out.println("3: Delete record");
			System.out.println("4: Search");
			System.out.println("5: Useful reports (WIP)");
			System.out.println("0: QUIT");
			if(scanner.hasNextInt()) {
				choice = scanner.nextInt();
				if(choice >= 0 && choice < 6) {
					seekingResponse = false;
				} else {
					scanner.nextLine();
					System.out.println("Input was not within the range of choices");
				}
				
			} else {
				scanner.nextLine();
				System.out.println("Input was not within the range of choices");
			}
		}
		System.out.println();
		
		switch(choice) {
		case 0:
			break;
		case 1:
			CreateHelper.AddMenu(conn);
			TopLayerMenu(conn);
			break;
		case 2:
			EditHelper.EditMenu();
			TopLayerMenu(conn);
			break;
		case 3:
			DeleteHelper.DeleteMenu();
			TopLayerMenu(conn);
			break;
		case 4:
			SearchHelper.SearchMenu();
			TopLayerMenu(conn);
			break;
		case 5:
			ReportsMenu();
			TopLayerMenu(conn);
			break;
		}
	}
	
	private static void ReportsMenu() {
		System.out.println("This feature is still WIP and is not available in this version.");
		scanner.nextLine();
	}
}
