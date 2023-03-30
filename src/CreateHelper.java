import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import RecordTypes.Equipment;
import RecordTypes.Member;
import RecordTypes.Warehouse;

public class CreateHelper {
	private static String addEquipmentSql = "INSERT INTO EQUIPMENT VALUES (?, ?, ?);";
	
	public static void AddMenu(Connection conn) {
		Integer choice = 0;
		boolean seekingResponse = true; 
		while(seekingResponse) {
			System.out.println("Enter the input corresponding to which type of record to create:");
			System.out.println("1: Equipment");
			System.out.println("2: Member");
			System.out.println("3: Warehouse");
			System.out.println("4: Review");
			System.out.println("5: Drone");
			System.out.println("6: Equipment Delivery");
			System.out.println("7: Drone Fleet");
			System.out.println("8: Drone Technician");
			System.out.println("9: Drone Repair");
			System.out.println("10: Inventory Order");
			System.out.println("11: Supplier");
			System.out.println("12: Inventory Item");
			System.out.println("13: Item Model");
			System.out.println("14: Equipment Rental");
			System.out.println("0: BACK TO ACTION SELECTION");
			if(DatabaseInteractor.scanner.hasNextInt()) {
				choice = DatabaseInteractor.scanner.nextInt();
				if(choice >= 0 && choice < 16) {
					seekingResponse = false;
				} else {
					DatabaseInteractor.scanner.nextLine();
					System.out.println("Input was not within the range of choices");
				}
			} else {
				DatabaseInteractor.scanner.nextLine();
				System.out.println("Input was not within the range of choices");
			}
		}
		System.out.println();
		DatabaseInteractor.scanner.nextLine();
		
		switch(choice) {
		case 0:
			break;
		case 1:
			AddEquipment(conn);
			break;
		case 2:
			AddMember(conn);
			break;
		case 3:
			AddWarehouse(conn);
			break;
		case 4:
			AddReview(conn);
			break;
		case 5:
			AddDrone(conn);
			break;
		case 6:
			AddEquipmentDelivery(conn);
			break;
		case 7:
			AddDroneFleet(conn);
			break;
		case 8:
			AddDroneTech(conn);
			break;
		case 9:
			AddDroneRepair(conn);
			break;
		case 10:
			AddInventoryOrder(conn);
			break;
		case 11:
			AddSupplier(conn);
			break;
		case 12:
			AddInventoryItem(conn);
			break;
		case 13:
			AddItemModel(conn);
			break;
		case 14:
			AddEquipmentRental(conn);
			break;
		}
	}
	
	public static void AddMember(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get name of member
			System.out.print("Member last name: ");
			stmt.setString(3, DatabaseInteractor.scanner.nextLine());
			System.out.print("First name: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get address of member
			System.out.print("Address: ");
			stmt.setString(4, DatabaseInteractor.scanner.nextLine());
			
			// get phone number of member
			System.out.print("Phone number: ");
			stmt.setString(5, DatabaseInteractor.scanner.nextLine());
			
			// get email of member 
			System.out.print("Email: ");
			stmt.setString(6, DatabaseInteractor.scanner.nextLine());
			
			// set start date of member to current date
			stmt.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
			
			// set procedural values (user ID)
			stmt.setInt(1, ++(DatabaseInteractor.maxUserId));
			
			stmt.executeUpdate();
			
			System.out.println("Addition successful");
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddWarehouse(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get city of warehouse
			System.out.print("Warehouse city: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get address of warehouse
			System.out.print("Address: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get manager name of warehouse
			System.out.print("Manager name: ");
			stmt.setString(6, DatabaseInteractor.scanner.nextLine());
			
			// get phone number of warehouse
			System.out.print("Phone number: ");
			stmt.setString(5, DatabaseInteractor.scanner.nextLine());
			
			// get drone cap of warehouse
			System.out.print("Drone cap: ");
			stmt.setInt(3, DatabaseInteractor.scanner.nextInt());
			
			// get storage cap of warehouse 
			System.out.print("Storage cap: ");
			stmt.setInt(4, DatabaseInteractor.scanner.nextInt());
			
			stmt.executeUpdate();
			
			System.out.println("Addition successful");
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddDroneFleet(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get id of fleet
			System.out.print("Fleet ID: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get distance coverage of fleet
			System.out.print("Distance coverage (miles): ");
			stmt.setFloat(2, DatabaseInteractor.scanner.nextFloat());
			
			// get max speed of fleet
			System.out.print("Max speed (mph): ");
			stmt.setFloat(3, DatabaseInteractor.scanner.nextFloat());
			
			// get weight cap of fleet
			System.out.print("Weight cap (lbs): ");
			stmt.setFloat(2, DatabaseInteractor.scanner.nextFloat());
			
			stmt.executeUpdate();
			
			System.out.println("Addition successful");
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddDroneTech(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get phone number of tech
			System.out.print("Phone Number: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get name of member
			System.out.print("Tech last name: ");
			stmt.setString(3, DatabaseInteractor.scanner.nextLine());
			System.out.print("Tech first name: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get email of tech
			System.out.print("Email: ");
			stmt.setString(4, DatabaseInteractor.scanner.nextLine());
			
			// get city of operation of tech
			System.out.print("City: ");
			stmt.setString(5, DatabaseInteractor.scanner.nextLine());
			
			// get website of tech
			System.out.print("Website: ");
			stmt.setString(6, DatabaseInteractor.scanner.nextLine());
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddSupplier(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get website
			System.out.print("Website: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get name
			System.out.print("Name: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get address
			System.out.print("Address: ");
			stmt.setString(3, DatabaseInteractor.scanner.nextLine());
			
			// get city
			System.out.print("Warehouse: ");
			stmt.setString(4, DatabaseInteractor.scanner.nextLine());
			
			// get phone number
			System.out.print("Phone Number: ");
			stmt.setString(5, DatabaseInteractor.scanner.nextLine());
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddItemModel(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get model no
			System.out.print("Model No: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get name
			System.out.print("Model Name: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get description
			System.out.print("Description: ");
			stmt.setString(3, DatabaseInteractor.scanner.nextLine());
			
			// get website
			System.out.print("Website: ");
			stmt.setString(4, DatabaseInteractor.scanner.nextLine());
			
			// get type
			System.out.print("Type: ");
			stmt.setString(6, DatabaseInteractor.scanner.nextLine());
			
			// get year
			System.out.print("Year: ");
			stmt.setInt(5, DatabaseInteractor.scanner.nextInt());
			
			// get size
			System.out.print("Size: ");
			stmt.setFloat(7, DatabaseInteractor.scanner.nextFloat());
			
			// get weight
			System.out.print("Weight: ");
			stmt.setFloat(8, DatabaseInteractor.scanner.nextFloat());
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
			DatabaseInteractor.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddInventoryItem(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get serial no
			System.out.print("Serial no: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get warehouse
			System.out.print("Housing warehouse address: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get order no
			System.out.print("Inventory order no: ");
			stmt.setString(3, DatabaseInteractor.scanner.nextLine());
			
			// get warranty expiration date
			System.out.print("Warranty expiration date (yyyy-mm-dd): ");
			stmt.setDate(4, java.sql.Date.valueOf(DatabaseInteractor.scanner.nextLine()));
			
			// get order no
			System.out.print("Model no: ");
			stmt.setString(5, DatabaseInteractor.scanner.nextLine());
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddEquipment(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get serial no
			System.out.print("Serial no: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get rental status
			System.out.print("Rental status (Available/Unavailable): ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get inventory id
			System.out.print("Inventory id: ");
			stmt.setString(3, DatabaseInteractor.scanner.nextLine());
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddEquipmentRental(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get user id
			System.out.print("Renting user id: ");
			stmt.setInt(1, DatabaseInteractor.scanner.nextInt());
			DatabaseInteractor.scanner.nextLine();
			
			// get equipment serial no
			System.out.print("Equipment serial no: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get checkout date
			System.out.print("Checkout date (yyyy-mm-dd): ");
			stmt.setDate(3, java.sql.Date.valueOf(DatabaseInteractor.scanner.nextLine()));
			
			// get return date
			System.out.print("Return date (yyyy-mm-dd): ");
			stmt.setDate(4, java.sql.Date.valueOf(DatabaseInteractor.scanner.nextLine()));
			
			// get due date
			System.out.print("Due date (yyyy-mm-dd): ");
			stmt.setDate(5, java.sql.Date.valueOf(DatabaseInteractor.scanner.nextLine()));
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddDrone(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get serial no
			System.out.print("Serial no: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get fleet id
			System.out.print("Fleet ID: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get status
			System.out.print("Availability (Available, Unavailable): ");
			stmt.setString(3, DatabaseInteractor.scanner.nextLine());
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddEquipmentDelivery(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get equipment serial no
			System.out.print("Equipment serial no: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get drone serial no
			System.out.print("Drone serial no: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get delivery date
			System.out.print("delivery date (yyyy-mm-dd): ");
			stmt.setDate(3, java.sql.Date.valueOf(DatabaseInteractor.scanner.nextLine()));
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddReview(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get equipment serial no
			System.out.print("Equipment serial no: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get review id
			System.out.print("Review ID: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get user id
			System.out.print("User id: ");
			stmt.setInt(3, DatabaseInteractor.scanner.nextInt());
			DatabaseInteractor.scanner.nextLine();
			
			// get comment
			System.out.print("Comment: ");
			stmt.setString(4, DatabaseInteractor.scanner.nextLine());
			
			// get rating
			System.out.print("Comment: ");
			stmt.setString(5, DatabaseInteractor.scanner.nextLine());
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddInventoryOrder(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get warehouse address
			System.out.print("Warehouse address: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get supplier website
			System.out.print("Supplier website: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get order id
			System.out.print("Order id: ");
			stmt.setString(3, DatabaseInteractor.scanner.nextLine());
			
			// get model no
			System.out.print("Model no: ");
			stmt.setString(4, DatabaseInteractor.scanner.nextLine());
			
			// get number ordered
			System.out.print("Number ordered: ");
			stmt.setFloat(5, DatabaseInteractor.scanner.nextFloat());
			DatabaseInteractor.scanner.nextLine();
			
			// get estimated arrival date
			System.out.print("Estimated arrival date (yyyy-mm-dd): ");
			stmt.setDate(6, java.sql.Date.valueOf(DatabaseInteractor.scanner.nextLine()));
			
			// get estimated arrival date
			System.out.print("Actual arrival date (yyyy-mm-dd): ");
			stmt.setDate(7, java.sql.Date.valueOf(DatabaseInteractor.scanner.nextLine()));
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void AddDroneRepair(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			// get drone serial number
			System.out.print("Drone serial number: ");
			stmt.setString(1, DatabaseInteractor.scanner.nextLine());
			
			// get technician phone number
			System.out.print("Technician phone number: ");
			stmt.setString(2, DatabaseInteractor.scanner.nextLine());
			
			// get date
			System.out.print("Date: ");
			stmt.setDate(3, java.sql.Date.valueOf(DatabaseInteractor.scanner.nextLine()));
			
			stmt.executeUpdate();

			System.out.println("Addition successful");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
