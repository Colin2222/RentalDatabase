import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ReportHelper {
	public static void ReportMenu() {
		Integer choice = 0;
		boolean seekingResponse = true; 
		while(seekingResponse) {
			System.out.println("Enter the input corresponding to which type of report you would like:");
			System.out.println("1: Find the total number of equipment rentals by a single member");
			System.out.println("2: Find the most popular item in the rental catalog");
			System.out.println("3: Find the most popular manufacturer by items rented");
			System.out.println("4: Find the most popular drone by items delivered");
			System.out.println("5: Find the most active member by items rented");
			System.out.println("6: Find objects by type of a model before a specified year");
			System.out.println("0: BACK TO ACTION SELECTION");
			if(DatabaseInteractor.scanner.hasNextInt()) {
				choice = DatabaseInteractor.scanner.nextInt();
				if(choice >= 0 && choice < 7) {
					seekingResponse = false;
				} else {
					DatabaseInteractor.scanner.nextLine();
					System.out.println("Input was not within the range of choices");
					DatabaseInteractor.scanner.nextLine();
				}
			} else {
				DatabaseInteractor.scanner.nextLine();
				System.out.println("Input was not within the range of choices");
				DatabaseInteractor.scanner.nextLine();
			}
		}
		System.out.println();
		DatabaseInteractor.scanner.nextLine();
		
		switch(choice) {
		case 0:
			break;
		case 1:
			reportMemberRentalTotal();
			break;
		case 2:
			reportMostPopularItem();
			break;
		case 3:
			reportMostPopularManufacturer();
			break;
		case 4:
			reportMostPopularDrone();
			break;
		case 5:
			reportMostActiveMember();
			break;
		case 6:
			reportByTypeAndYear();
			break;
		}
	}
	
	public static void reportMemberRentalTotal() {
		System.out.print("Enter the UserID of the member: ");
		int userID = DatabaseInteractor.scanner.nextInt();
		DatabaseInteractor.scanner.nextLine();
		
		ResultSet result = DatabaseInteractor.db.reportMemberRentalTotal(userID);
		
		try {
			if(result != null) {
				ResultSetMetaData rsmd = result.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String value = rsmd.getColumnName(i);
					System.out.print(value);
					if (i < columnCount) {
						System.out.print(", ");
					}
				}
				System.out.print("\n");
				while (result.next()) {
					for (int i = 1; i <= columnCount; i++) {
						String columnValue = result.getString(i);
						System.out.print(columnValue);
						if (i < columnCount) {
							System.out.print(", ");
						}
					}
					System.out.print("\n");
				}
				
			} else {
				System.out.println("No result found with userID: '" + userID + "'.");
			}
			System.out.println();
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Error processing query results.");
			System.out.println(e.getMessage());
		}
	}
	
	public static void reportMostPopularItem() {
		ResultSet result = DatabaseInteractor.db.reportMostPopularItem();
		
		try {
			if(result != null) {
				ResultSetMetaData rsmd = result.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String value = rsmd.getColumnName(i);
					System.out.print(value);
					if (i < columnCount) {
						System.out.print(", ");
					}
				}
				System.out.print("\n");
				while (result.next()) {
					for (int i = 1; i <= columnCount; i++) {
						String columnValue = result.getString(i);
						System.out.print(columnValue);
						if (i < columnCount) {
							System.out.print(", ");
						}
					}
					System.out.print("\n");
				}
				
			} else {
				System.out.println("No result found.");
			}
			System.out.println();
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Error processing query results.");
			System.out.println(e.getMessage());
		}
	}
	
	public static void reportMostPopularManufacturer() {
		ResultSet result = DatabaseInteractor.db.reportMostPopularManufacturer();
		
		try {
			if(result != null) {
				ResultSetMetaData rsmd = result.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String value = rsmd.getColumnName(i);
					System.out.print(value);
					if (i < columnCount) {
						System.out.print(", ");
					}
				}
				System.out.print("\n");
				while (result.next()) {
					for (int i = 1; i <= columnCount; i++) {
						String columnValue = result.getString(i);
						System.out.print(columnValue);
						if (i < columnCount) {
							System.out.print(", ");
						}
					}
					System.out.print("\n");
				}
				
			} else {
				System.out.println("No result found.");
			}
			System.out.println();
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Error processing query results.");
			System.out.println(e.getMessage());
		}
	}
	
	public static void reportMostPopularDrone() {
		ResultSet result = DatabaseInteractor.db.reportMostPopularDrone();
		
		try {
			if(result != null) {
				ResultSetMetaData rsmd = result.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String value = rsmd.getColumnName(i);
					System.out.print(value);
					if (i < columnCount) {
						System.out.print(", ");
					}
				}
				System.out.print("\n");
				while (result.next()) {
					for (int i = 1; i <= columnCount; i++) {
						String columnValue = result.getString(i);
						System.out.print(columnValue);
						if (i < columnCount) {
							System.out.print(", ");
						}
					}
					System.out.print("\n");
				}
				
			} else {
				System.out.println("No result found.");
			}
			System.out.println();
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Error processing query results.");
			System.out.println(e.getMessage());
		}
	}
	
	public static void reportMostActiveMember() {
		ResultSet result = DatabaseInteractor.db.reportMostActiveMember();
		
		try {
			if(result != null) {
				ResultSetMetaData rsmd = result.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String value = rsmd.getColumnName(i);
					System.out.print(value);
					if (i < columnCount) {
						System.out.print(", ");
					}
				}
				System.out.print("\n");
				while (result.next()) {
					for (int i = 1; i <= columnCount; i++) {
						String columnValue = result.getString(i);
						System.out.print(columnValue);
						if (i < columnCount) {
							System.out.print(", ");
						}
					}
					System.out.print("\n");
				}
				
			} else {
				System.out.println("No result found.");
			}
			System.out.println();
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Error processing query results.");
			System.out.println(e.getMessage());
		}
	}
	
	public static void reportByTypeAndYear() {
		System.out.print("Enter the type to search for: ");
		String type = DatabaseInteractor.scanner.nextLine();
		System.out.print("Enter the year to search for: ");
		int year = DatabaseInteractor.scanner.nextInt();
		DatabaseInteractor.scanner.nextLine();
		
		ResultSet result = DatabaseInteractor.db.reportByTypeAndYear(type, year);
		
		try {
			if(result != null) {
				ResultSetMetaData rsmd = result.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String value = rsmd.getColumnName(i);
					System.out.print(value);
					if (i < columnCount) {
						System.out.print(", ");
					}
				}
				System.out.print("\n");
				while (result.next()) {
					for (int i = 1; i <= columnCount; i++) {
						String columnValue = result.getString(i);
						System.out.print(columnValue);
						if (i < columnCount) {
							System.out.print(", ");
						}
					}
					System.out.print("\n");
				}
				
			} else {
				System.out.println("No result found.");
			}
			System.out.println();
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Error processing query results.");
			System.out.println(e.getMessage());
		}
	}
}
