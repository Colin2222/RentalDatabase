import java.time.LocalDate;

import RecordTypes.Equipment;
import RecordTypes.Member;
import RecordTypes.Record;
import RecordTypes.Warehouse;

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
		Equipment result = null;
		System.out.print("Enter the serial number of the equipment: ");
		int searchTerm = DatabaseInteractor.scanner.nextInt();
		
		boolean resultFound = false;
		for(Equipment x : DatabaseInteractor.equipments) {
			if(x.serialNo == searchTerm) {
				resultFound = true; 
				result = x;
				break;
			}
		}
		
		if(resultFound) {
			System.out.println("Result found, enter the number corresponding to the attribute to edit:");
			System.out.println("1. Name: " + result.name);
			System.out.println("2. Serial Number: " + result.serialNo);
			System.out.println("3. Model Number: " + result.modelNo);
			System.out.println("4. Inventory ID: " + result.inventoryId);
			System.out.println("5. Type: " + result.type);
			System.out.println("6. Description: " + result.description);
			System.out.println("7. Manufacturer: " + result.manufacturer);
			System.out.println("8. Year: " + result.year);
			System.out.println("9. Size: " + result.size + "m^3");
			System.out.println("10. Weight: " + result.weight + "kg");
			System.out.println("11. Warranty Expiration Date: " + result.warrantyExpirationDate);
			System.out.println("12. Location Code: " + result.locationCode);
			System.out.print("13. Currently available to rent: ");
			if(result.rentalStatus == 0) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			
			int attributeSelection = DatabaseInteractor.scanner.nextInt();
			DatabaseInteractor.scanner.nextLine();
			switch(attributeSelection) {
			case 1:
				System.out.print("Enter the new name: ");
				result.name = DatabaseInteractor.scanner.nextLine();
				break;
			case 2:
				System.out.print("Enter the new serial number: ");
				result.serialNo = Integer.parseInt(DatabaseInteractor.scanner.nextLine());
				break;
			case 3:
				System.out.print("Enter the new model number: ");
				result.modelNo = Integer.parseInt(DatabaseInteractor.scanner.nextLine());
				break;
			case 4:
				System.out.print("Enter the new inventory ID: ");
				result.inventoryId = Integer.parseInt(DatabaseInteractor.scanner.nextLine());
				break;
			case 5:
				System.out.print("Enter the new type: ");
				result.type = DatabaseInteractor.scanner.nextLine();
				break;
			case 6:
				System.out.print("Enter the new description: ");
				result.description = DatabaseInteractor.scanner.nextLine();
				break;
			case 7:
				System.out.print("Enter the new manufacturer: ");
				result.manufacturer = DatabaseInteractor.scanner.nextLine();
				break;
			case 8:
				System.out.print("Enter the new year: ");
				result.year = Integer.parseInt(DatabaseInteractor.scanner.nextLine());
				break;
			case 9:
				System.out.print("Enter the new size (m^3): ");
				result.size = Float.parseFloat(DatabaseInteractor.scanner.nextLine());
				break;
			case 10:
				System.out.print("Enter the new weight (kg): ");
				result.weight = Float.parseFloat(DatabaseInteractor.scanner.nextLine());
				break;
			case 11:
				System.out.print("Enter the new warranty expiration date (YYYY-MM-DD): ");
				result.warrantyExpirationDate = LocalDate.parse(DatabaseInteractor.scanner.nextLine());
				break;
			case 12:
				System.out.print("Enter the new location code: ");
				result.locationCode = DatabaseInteractor.scanner.nextLine();
				break;
			case 13:
				System.out.print("Enter the new rental status (0 for available, 1 for rented out): ");
				result.rentalStatus = Integer.parseInt(DatabaseInteractor.scanner.nextLine());
				break;
			default:
				System.out.println("Invalid attribute number");
				break;
			}
		} else {
			System.out.println("Serial number not found among equipment");
		}
		
		DatabaseInteractor.scanner.nextLine();
	}
	
	public static void EditMember() {
		Member result = null;
		System.out.print("Enter the last name of the member: ");
		String searchTerm = DatabaseInteractor.scanner.nextLine();
		
		boolean resultFound = false;
		for(Member x : DatabaseInteractor.members) {
			if(x.lName.contains(searchTerm)) {
				resultFound = true; 
				result = x;
				break;
			}
		}
		
		if(resultFound) {
			System.out.println("Result found, enter the number corresponding to the attribute to edit:");
			System.out.println("1. User ID: " + result.userId);
			System.out.println("2. Last Name: " + result.lName);
			System.out.println("3. First Name: " + result.fName);
			System.out.println("4. Address: " + result.address);
			System.out.println("5. Phone Number: " + result.phoneNumber);
			System.out.println("6. Email: " + result.email);
			System.out.println("7. Start Date: " + result.startDate);
			
			int attributeSelection = DatabaseInteractor.scanner.nextInt();
			DatabaseInteractor.scanner.nextLine();
			switch(attributeSelection) {
			case 1:
				System.out.print("Enter the new user ID: ");
				result.userId = Integer.parseInt(DatabaseInteractor.scanner.nextLine());
				break;
			case 2:
				System.out.print("Enter the new last name: ");
				result.lName = DatabaseInteractor.scanner.nextLine();
				break;
			case 3:
				System.out.print("Enter the new first name: ");
				result.fName = DatabaseInteractor.scanner.nextLine();
				break;
			case 4:
				System.out.print("Enter the new address: ");
				result.address = DatabaseInteractor.scanner.nextLine();
				break;
			case 5:
				System.out.print("Enter the new phone number: ");
				result.phoneNumber = DatabaseInteractor.scanner.nextLine();
				break;
			case 6:
				System.out.print("Enter the new email: ");
				result.email = DatabaseInteractor.scanner.nextLine();
				break;
			case 7:
				System.out.print("Enter the new start date (YYYY-MM-DD): ");
				result.startDate = LocalDate.parse(DatabaseInteractor.scanner.nextLine());
				break;
			default:
				System.out.println("Invalid attribute number");
				break;
			}
		} else {
			System.out.println("Last name not found among members");
		}
		
		DatabaseInteractor.scanner.nextLine();
	}
	
	public static void EditWarehouse() {
		Warehouse result = null;
		System.out.print("Enter the address or the city of the warehouse: ");
		String searchTerm = DatabaseInteractor.scanner.nextLine();
		
		boolean resultFound = false;
		for(Warehouse x : DatabaseInteractor.warehouses) {
			if(x.address.contains(searchTerm) || x.city.contains(searchTerm)) {
				resultFound = true; 
				result = x;
				break;
			}
		}
		
		if(resultFound) {
			System.out.println("Result found, enter the number corresponding to the attribute to edit:");
			System.out.println("1. City: " + result.city);
			System.out.println("2. Address: " + result.address);
			System.out.println("3. Drone Cap: " + result.droneCap);
			System.out.println("4. Storage Cap: " + result.storageCap);
			System.out.println("5. Phone Number: " + result.phoneNumber);
			System.out.println("6. Manager Name: " + result.managerName);
			
			int attributeSelection = DatabaseInteractor.scanner.nextInt();
			DatabaseInteractor.scanner.nextLine();
			switch(attributeSelection) {
			case 1:
				System.out.print("Enter the new city: ");
				result.city = DatabaseInteractor.scanner.nextLine();
				break;
			case 2:
				System.out.print("Enter the new address: ");
				result.address = DatabaseInteractor.scanner.nextLine();
				break;
			case 3:
				System.out.print("Enter the new drone cap: ");
				result.droneCap = Integer.parseInt(DatabaseInteractor.scanner.nextLine());
				break;
			case 4:
				System.out.print("Enter the new storage cap: ");
				result.storageCap = Integer.parseInt(DatabaseInteractor.scanner.nextLine());
				break;
			case 5:
				System.out.print("Enter the new phone number: ");
				result.phoneNumber = DatabaseInteractor.scanner.nextLine();
				break;
			case 6:
				System.out.print("Enter the new manager name: ");
				result.managerName = DatabaseInteractor.scanner.nextLine();
				break;
			default:
				System.out.println("Invalid attribute number");
				break;
			}
		} else {
			System.out.println("Last name not found among warehouses");
		}
		
		DatabaseInteractor.scanner.nextLine();
	}
}

