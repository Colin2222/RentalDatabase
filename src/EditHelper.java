import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

public class EditHelper {
	public static void EditMenu() {
		Integer choice = 0;
		boolean seekingResponse = true; 
		while(seekingResponse) {
			System.out.println("Enter the input corresponding to which type you would like to edit:");
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
			EditEquipment();
			break;
		case 2:
			EditMember();
			break;
		case 3:
			EditWarehouse();
			break;
		}
	}
	
	public static void EditEquipment() {
		System.out.print("Enter the serial number of the equipment: ");
		String serialNo = DatabaseInteractor.scanner.nextLine();

		ResultSet result = DatabaseInteractor.db.selectEquipmentBySerialNo(serialNo);
		
		if(result != null) {
			try {
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
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}

			System.out.print("Select number corresponding to the column of the attribute to edit: ");
			int attributeSelection = DatabaseInteractor.scanner.nextInt();
			String query;
			DatabaseInteractor.scanner.nextLine();
			boolean success = false;
			switch(attributeSelection) {
				case 1:
					System.out.print("Enter new equipment serial number: ");
					String newSerialNo = DatabaseInteractor.scanner.nextLine();
					
					query = "UPDATE EQUIPMENT SET serialNo=? WHERE serialNo=?";
					success = DatabaseInteractor.db.updateEquipmentBySerialNo(query, serialNo, newSerialNo);
					break;
				case 2:
					System.out.print("Enter new equipment rental status (Available or Rented): ");
					String newRentalStatus = DatabaseInteractor.scanner.nextLine();
					
					query = "UPDATE EQUIPMENT SET rentalStatus=? WHERE serialNo=?";
					success = DatabaseInteractor.db.updateEquipmentBySerialNo(query, serialNo, newRentalStatus);
					break;
				case 3:
					System.out.print("Enter new equipment inventory ID: ");
					String newInvID = DatabaseInteractor.scanner.nextLine();
					
					query = "UPDATE EQUIPMENT SET inventoryID=? WHERE serialNo=?";
					success = DatabaseInteractor.db.updateEquipmentBySerialNo(query, serialNo, newInvID);
					break;
				default:
					System.out.println("Invalid attribute number");
					break;
			}
			
			if (success)
				System.out.println("Success!");
			else
				System.out.println("Failure!");
		} else {
			System.out.println("Serial number not found among equipment");
		}
		
		DatabaseInteractor.scanner.nextLine();
	}
	
	public static void EditMember() {
		System.out.print("Enter the user ID of the member: ");
		int userID = DatabaseInteractor.scanner.nextInt();

		ResultSet result = DatabaseInteractor.db.selectMemberByUserID(userID);
		
		if(result != null) {
			try {
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
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}

			System.out.print("Select number corresponding to the column of the attribute to edit: ");
			int attributeSelection = DatabaseInteractor.scanner.nextInt();
			String query;
			DatabaseInteractor.scanner.nextLine();
			boolean success = false;
			switch(attributeSelection) {
				case 1:
					// set start date of member to current date
					System.out.print("Enter new member user ID: ");
					int newUserID = DatabaseInteractor.scanner.nextInt();
					
					query = "UPDATE MEMBER SET userID=? WHERE userID=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, userID, newUserID);
					break;
				case 2:
					System.out.print("Enter new member first name: ");
					String newFName = DatabaseInteractor.scanner.nextLine();

					query = "UPDATE MEMBER SET fName=? WHERE userID=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, userID, newFName);
					break;
				case 3:
					// get name of member
					System.out.print("Enter new member last name: ");
					String newLName = DatabaseInteractor.scanner.nextLine();

					query = "UPDATE MEMBER SET lName=? WHERE userID=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, userID, newLName);
					break;
				case 4:
					// get address of member
					System.out.print("Enter new member address: ");
					String newAddress = DatabaseInteractor.scanner.nextLine();

					query = "UPDATE MEMBER SET address=? WHERE userID=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, userID, newAddress);
					break;
				case 5:
					// get phone number of member
					System.out.print("Enter new member phone number: ");
					String newPhoneNum = DatabaseInteractor.scanner.nextLine();

					query = "UPDATE MEMBER SET phoneNumb=? WHERE userID=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, userID, newPhoneNum);
					break;
				case 6:
					// get email of member 
					System.out.print("Enter new member email: ");
					String newEmail = DatabaseInteractor.scanner.nextLine();

					query = "UPDATE MEMBER SET email=? WHERE userID=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, userID, newEmail);
					break;
				case 7:
					// set start date of member to current date
					System.out.print("Enter new member start date (yyyy-mm-dd): ");
					LocalDate newDate = LocalDate.parse(DatabaseInteractor.scanner.nextLine());
					
					query = "UPDATE MEMBER SET startDate=? WHERE userID=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, userID, newDate);
					break;
				default:
					System.out.println("Invalid attribute number");
					break;
			}
			
			if (success)
				System.out.println("Success!");
			else
				System.out.println("Failure!");
		} else {
			System.out.println("Serial number not found among equipment");
		}
		
		DatabaseInteractor.scanner.nextLine();
	}
	
	public static void EditWarehouse() {
		System.out.print("Enter the address of warehouse: ");
		int address = DatabaseInteractor.scanner.nextInt();

		ResultSet result = DatabaseInteractor.db.selectMemberByUserID(address);
		
		if(result != null) {
			try {
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
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}

			System.out.print("Select number corresponding to the column of the attribute to edit: ");
			int attributeSelection = DatabaseInteractor.scanner.nextInt();
			String query;
			DatabaseInteractor.scanner.nextLine();
			boolean success = false;
			switch(attributeSelection) {
				case 1:
					// get address of warehouse
					System.out.print("Enter new warehouse address: ");
					String newAddress = DatabaseInteractor.scanner.nextLine();
	
					query = "UPDATE WAREHOUSE SET address=? WHERE address=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, address, newAddress);
					break;
				case 2:
					// get city of warehouse
					System.out.print("Enter new warehouse city: ");
					String newWarehouseCity = DatabaseInteractor.scanner.nextLine();

					query = "UPDATE WAREHOUSE SET city=? WHERE address=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, address, newWarehouseCity);
					break;
				case 3:
					// get drone cap of warehouse
					System.out.print("Enter new warehouse drone cap: ");
					int newDroneCap = DatabaseInteractor.scanner.nextInt();

					query = "UPDATE WAREHOUSE SET droneCap=? WHERE address=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, address, newDroneCap);
					break;
				case 4:
					// get storage cap of warehouse 
					System.out.print("Enter new warehouse storage cap: ");
					int newStorageCap = DatabaseInteractor.scanner.nextInt();

					query = "UPDATE WAREHOUSE SET storageCap=? WHERE address=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, address, newStorageCap);
					break;
				case 5:
					// get phone number of warehouse
					System.out.print("Enter new warehouse phone number: ");
					String newPhoneNum = DatabaseInteractor.scanner.nextLine();

					query = "UPDATE WAREHOUSE SET phoneNo=? WHERE address=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, address, newPhoneNum);
					break;
				case 6:
					// get manager name of warehouse
					System.out.print("Enter new warehouse manager name: ");
					String newManagerName = DatabaseInteractor.scanner.nextLine();

					query = "UPDATE WAREHOUSE SET managerName=? WHERE address=?";
					success = DatabaseInteractor.db.updateMemberByUserID(query, address, newManagerName);
					break;
				default:
					System.out.println("Invalid attribute number");
					break;
			}
			
			if (success)
				System.out.println("Success!");
			else
				System.out.println("Failure!");
		} else {
			System.out.println("Serial number not found among equipment");
		}
	}
}

