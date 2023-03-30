import java.time.LocalDate;

public class CreateHelper {
	public static void AddMenu() {
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
			AddEquipment();
			break;
		case 2:
			AddMember();
			break;
		case 3:
			AddWarehouse();
			break;
		case 4:
			AddReview();
			break;
		case 5:
			AddDrone();
			break;
		case 6:
			AddEquipmentDelivery();
			break;
		case 7:
			AddDroneFleet();
			break;
		case 8:
			AddDroneTech();
			break;
		case 9:
			AddDroneRepair();
			break;
		case 10:
			AddInventoryOrder();
			break;
		case 11:
			AddSupplier();
			break;
		case 12:
			AddInventoryItem();
			break;
		case 13:
			AddItemModel();
			break;
		case 14:
			AddEquipmentRental();
			break;
		}
	}
	
	public static void AddMember() {
		// get name of member
		System.out.print("Member last name: ");
		String lName = DatabaseInteractor.scanner.nextLine();
		
		System.out.print("First name: ");
		String fName = DatabaseInteractor.scanner.nextLine();
		
		// get address of member
		System.out.print("Address: ");
		String address = DatabaseInteractor.scanner.nextLine();
		
		// get phone number of member
		System.out.print("Phone number: ");
		String phoneNum = DatabaseInteractor.scanner.nextLine();
		
		// get email of member 
		System.out.print("Email: ");
		String email = DatabaseInteractor.scanner.nextLine();
		
		// set procedural values (user ID)
		int userID = ++(DatabaseInteractor.maxUserId);
		
		// set start date of member to current date
		LocalDate date = LocalDate.now();
		
		DatabaseInteractor.scanner.nextLine();
		boolean success = DatabaseInteractor.db.insertMember(lName, fName, address, phoneNum, email, date, userID);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddWarehouse() {
		// get city of warehouse
		System.out.print("Warehouse city: ");
		String warehouseCity = DatabaseInteractor.scanner.nextLine();
		
		// get address of warehouse
		System.out.print("Address: ");
		String address = DatabaseInteractor.scanner.nextLine();
		
		// get manager name of warehouse
		System.out.print("Manager name: ");
		String managerName = DatabaseInteractor.scanner.nextLine();
		
		// get phone number of warehouse
		System.out.print("Phone number: ");
		String phoneNum = DatabaseInteractor.scanner.nextLine();
		
		// get drone cap of warehouse
		System.out.print("Drone cap: ");
		int droneCap = DatabaseInteractor.scanner.nextInt();
		
		// get storage cap of warehouse 
		System.out.print("Storage cap: ");
		int storageCap = DatabaseInteractor.scanner.nextInt();
		
		DatabaseInteractor.scanner.nextLine();
		boolean success = DatabaseInteractor.db.insertWarehouse(warehouseCity, address, managerName, phoneNum, droneCap, storageCap);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddDroneFleet() {
		// get id of fleet
		System.out.print("Fleet ID: ");
		String fleetID = DatabaseInteractor.scanner.nextLine();
		
		// get distance coverage of fleet
		System.out.print("Distance coverage (miles): ");
		float distance = DatabaseInteractor.scanner.nextFloat();
		
		// get max speed of fleet
		System.out.print("Max speed (mph): ");
		float maxSpeed = DatabaseInteractor.scanner.nextFloat();
		
		// get weight cap of fleet
		System.out.print("Weight cap (lbs): ");
		float weightCap = DatabaseInteractor.scanner.nextFloat();
		
		DatabaseInteractor.scanner.nextLine();
		boolean success = DatabaseInteractor.db.insertDroneFleet(fleetID, distance, maxSpeed, weightCap);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddDroneTech() {
		// get phone number of tech
		System.out.print("Phone Number: ");
		String phoneNo = DatabaseInteractor.scanner.nextLine();
		
		// get name of member
		System.out.print("Tech last name: ");
		String techLName = DatabaseInteractor.scanner.nextLine();
		System.out.print("Tech first name: ");
		String techFName = DatabaseInteractor.scanner.nextLine();
		
		// get email of tech
		System.out.print("Email: ");
		String techEmail = DatabaseInteractor.scanner.nextLine();
		
		// get city of operation of tech
		System.out.print("City: ");
		String city = DatabaseInteractor.scanner.nextLine();
		
		// get website of tech
		System.out.print("Website: ");
		String website = DatabaseInteractor.scanner.nextLine();
		
		boolean success = DatabaseInteractor.db.insertDroneTech(phoneNo, techLName, techFName, techEmail, city, website);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddSupplier() {
		// get website
		System.out.print("Website: ");
		String website = DatabaseInteractor.scanner.nextLine();
		
		// get name
		System.out.print("Name: ");
		String name = DatabaseInteractor.scanner.nextLine();
		
		// get address
		System.out.print("Address: ");
		String address = DatabaseInteractor.scanner.nextLine();
		
		// get city
		System.out.print("Warehouse: ");
		String warehouse = DatabaseInteractor.scanner.nextLine();
		
		// get phone number
		System.out.print("Phone Number: ");
		String phoneNo = DatabaseInteractor.scanner.nextLine();
		
		boolean success = DatabaseInteractor.db.insertSupplier(website, name, address, warehouse, phoneNo);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddItemModel() {
		// get model no
		System.out.print("Model No: ");
		String modelNo = DatabaseInteractor.scanner.nextLine();
		
		// get name
		System.out.print("Model Name: ");
		String modelName = DatabaseInteractor.scanner.nextLine();
		
		// get description
		System.out.print("Description: ");
		String description = DatabaseInteractor.scanner.nextLine();
		
		// get website
		System.out.print("Website: ");
		String website = DatabaseInteractor.scanner.nextLine();
		
		// get type
		System.out.print("Type: ");
		String type = DatabaseInteractor.scanner.nextLine();
		
		// get year
		System.out.print("Year: ");
		int year = DatabaseInteractor.scanner.nextInt();
		
		// get size
		System.out.print("Size: ");
		float size = DatabaseInteractor.scanner.nextFloat();
		
		// get weight
		System.out.print("Weight: ");
		float weight = DatabaseInteractor.scanner.nextFloat();
		
		DatabaseInteractor.scanner.nextLine();
		boolean success = DatabaseInteractor.db.insertItemModel(modelNo, modelName, description, website, type, year, size, weight);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddInventoryItem() {
		// get serial no
		System.out.print("Serial no: ");
		String serialNo = DatabaseInteractor.scanner.nextLine();
		
		// get warehouse
		System.out.print("Housing warehouse address: ");
		String warehouseAddress = DatabaseInteractor.scanner.nextLine();
		
		// get order no
		System.out.print("Inventory order no: ");
		String invOrderID = DatabaseInteractor.scanner.nextLine();
		
		// get warranty expiration date
		System.out.print("Warranty expiration date (yyyy-mm-dd): ");
		LocalDate warrantyExpiryDate = LocalDate.parse(DatabaseInteractor.scanner.nextLine());
		
		// get order no
		System.out.print("Model no: ");
		String modelNo = DatabaseInteractor.scanner.nextLine();
		
		boolean success = DatabaseInteractor.db.insertInventoryItem(serialNo, warehouseAddress, invOrderID, warrantyExpiryDate, modelNo);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}	
	
	public static void AddEquipment() {
		// get serial no
		System.out.print("Serial no: ");
		String serialNo = DatabaseInteractor.scanner.nextLine();
		
		// get rental status
		System.out.print("Rental status (Available/Unavailable): ");
		String rentalStatus = DatabaseInteractor.scanner.nextLine();
		
		// get inventory id
		System.out.print("Inventory id: ");
		String invID = DatabaseInteractor.scanner.nextLine();
		
		boolean success = DatabaseInteractor.db.insertEquipment(serialNo, rentalStatus, invID);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddEquipmentRental() {
		// get user id
		System.out.print("Renting user id: ");
		int userID = DatabaseInteractor.scanner.nextInt();
		DatabaseInteractor.scanner.nextLine();
		
		// get equipment serial no
		System.out.print("Equipment serial no: ");
		String serialNo = DatabaseInteractor.scanner.nextLine();
		
		// get checkout date
		System.out.print("Checkout date (yyyy-mm-dd): ");
		LocalDate checkoutDate = LocalDate.parse(DatabaseInteractor.scanner.nextLine());
		
		// get return date
		System.out.print("Return date (yyyy-mm-dd): ");
		LocalDate returnDate = LocalDate.parse(DatabaseInteractor.scanner.nextLine());
		
		// get due date
		System.out.print("Due date (yyyy-mm-dd): ");
		LocalDate dueDate = LocalDate.parse(DatabaseInteractor.scanner.nextLine());

		boolean success = DatabaseInteractor.db.insertEquipmentRental(userID, serialNo, checkoutDate, returnDate, dueDate);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddDrone() {
		// get serial no
		System.out.print("Serial no: ");
		String serialNo = DatabaseInteractor.scanner.nextLine();
		
		// get fleet id
		System.out.print("Fleet ID: ");
		String fleetID = DatabaseInteractor.scanner.nextLine();
		
		// get status
		System.out.print("Availability (Available, Unavailable): ");
		String availability = DatabaseInteractor.scanner.nextLine();
		
		boolean success = DatabaseInteractor.db.insertDrone(serialNo, fleetID, availability);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddEquipmentDelivery() {
		// get equipment serial no
		System.out.print("Equipment serial no: ");
		String equipSerialNo = DatabaseInteractor.scanner.nextLine();
		
		// get drone serial no
		System.out.print("Drone serial no: ");
		String droneSerialNo = DatabaseInteractor.scanner.nextLine();
		
		// get delivery date
		System.out.print("delivery date (yyyy-mm-dd): ");
		LocalDate deliveryDate = LocalDate.parse(DatabaseInteractor.scanner.nextLine());
		
		boolean success = DatabaseInteractor.db.insertEquipmentDelivery(equipSerialNo, droneSerialNo, deliveryDate);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddReview() {
		// get equipment serial no
		System.out.print("Equipment serial no: ");
		String serialNo = DatabaseInteractor.scanner.nextLine();
		
		// get review id
		System.out.print("Review ID: ");
		String reviewID = DatabaseInteractor.scanner.nextLine();
		
		// get user id
		System.out.print("User id: ");
		int userID = DatabaseInteractor.scanner.nextInt();
		DatabaseInteractor.scanner.nextLine();
		
		// get comment
		System.out.print("Comment: ");
		String comment = DatabaseInteractor.scanner.nextLine();
		
		// get rating
		System.out.print("Rating: ");
		String rating = DatabaseInteractor.scanner.nextLine();
		
		boolean success = DatabaseInteractor.db.insertReview(serialNo, reviewID, userID, comment, rating);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddInventoryOrder() {
		// get warehouse address
		System.out.print("Warehouse address: ");
		String warehouseAddress = DatabaseInteractor.scanner.nextLine();
		
		// get supplier website
		System.out.print("Supplier website: ");
		String supplierWebsite = DatabaseInteractor.scanner.nextLine();
		
		// get order id
		System.out.print("Order id: ");
		String orderId = DatabaseInteractor.scanner.nextLine();
		
		// get model no
		System.out.print("Model no: ");
		String modelNo = DatabaseInteractor.scanner.nextLine();
		
		// get number ordered
		System.out.print("Number ordered: ");
		float quantity = DatabaseInteractor.scanner.nextFloat();
		DatabaseInteractor.scanner.nextLine();
		
		// get estimated arrival date
		System.out.print("Estimated arrival date (yyyy-mm-dd): ");
		LocalDate ead = LocalDate.parse(DatabaseInteractor.scanner.nextLine());
		
		// get estimated arrival date
		System.out.print("Actual arrival date (yyyy-mm-dd): ");
		LocalDate aad = LocalDate.parse(DatabaseInteractor.scanner.nextLine());
		
		boolean success = DatabaseInteractor.db.insertInventoryOrder(warehouseAddress, supplierWebsite, orderId, modelNo, quantity, ead, aad);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void AddDroneRepair() {
		// get drone serial number
		System.out.print("Drone serial number: ");
		String droneSerialNo = DatabaseInteractor.scanner.nextLine();
		
		// get technician phone number
		System.out.print("Technician phone number: ");
		String techPhoneNo = DatabaseInteractor.scanner.nextLine();
		
		// get date
		System.out.print("Date: ");
		LocalDate date = LocalDate.parse(DatabaseInteractor.scanner.nextLine());
		
		boolean success = DatabaseInteractor.db.insertDroneRepair(droneSerialNo, techPhoneNo, date);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
}
