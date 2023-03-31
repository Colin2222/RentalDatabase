import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SearchHelper {
	public static void SearchMenu() {
		Integer choice = 0;
		boolean seekingResponse = true; 
		while(seekingResponse) {
			System.out.println("Enter the input corresponding to which type you would like to search for:");
			System.out.println("1: Equipment");
			System.out.println("2: Member");
			System.out.println("3: Warehouse");
			System.out.println("0: BACK TO ACTION SELECTION");
			if(DatabaseInteractor.scanner.hasNextInt()) {
				choice = DatabaseInteractor.scanner.nextInt();
				if(choice >= 0 && choice < 4) {
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
			SearchEquipment();
			break;
		case 2:
			SearchMember();
			break;
		case 3:
			SearchWarehouse();
			break;
		}
	}
	
	public static void SearchEquipment() {
		System.out.print("Enter the serial number of the equipment: ");
		String serialNo = DatabaseInteractor.scanner.nextLine();
		
		ResultSet result = DatabaseInteractor.db.selectEquipmentBySerialNo(serialNo);
		
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
				System.out.println("No result found with serial number: '" + serialNo + "'.");
			}
			System.out.println();
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Error processing query results.");
			System.out.println(e.getMessage());
		}
	}
	
	public static void SearchMember() {
		System.out.print("Enter the user ID of the member: ");
		int userID = DatabaseInteractor.scanner.nextInt();
		
		ResultSet result = DatabaseInteractor.db.selectMemberByUserID(userID);
		
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
				System.out.println("No result found with user ID: '" + userID + "'.");
			}
			System.out.println();
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Error processing query results.");
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void SearchWarehouse() {
		System.out.print("Enter the address of the warehouse: ");
		String address = DatabaseInteractor.scanner.nextLine();
		
		ResultSet result = DatabaseInteractor.db.selectWarehouseByAddress(address);
		
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
				System.out.println("No result found with address: '" + address + "'.");
			}
			System.out.println();
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Error processing query results.");
			System.out.println(e.getMessage());
		}
	}
}
