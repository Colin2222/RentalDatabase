import java.time.LocalDate;

import RecordTypes.Equipment;
import RecordTypes.Member;
import RecordTypes.Warehouse;

public class DeleteHelper {
	public static void DeleteMenu() {
		Integer choice = 0;
		boolean seekingResponse = true; 
		while(seekingResponse) {
			System.out.println("Enter the input corresponding to which type you would like to delete:");
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
			DeleteEquipment();
			break;
		case 2:
			DeleteMember();
			break;
		case 3:
			DeleteWarehouse();
			break;
		}
	}
	
	public static void DeleteEquipment() {
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
			System.out.println("Result found:");
			System.out.println("Name: " + result.name);
			System.out.println("Serial Number: " + result.serialNo);
			System.out.println("Model Number: " + result.modelNo);
			System.out.println("Inventory ID: " + result.inventoryId);
			System.out.println("Type: " + result.type);
			System.out.println("Description: " + result.description);
			System.out.println("Manufacturer: " + result.manufacturer);
			System.out.println("Year: " + result.year);
			System.out.println("Size: " + result.size + "m^3");
			System.out.println("Weight: " + result.weight + "kg");
			System.out.println("Warranty Expiration Date: " + result.warrantyExpirationDate);
			System.out.println("Location Code: " + result.locationCode);
			System.out.print("Currently available to rent: ");
			if(result.rentalStatus == 0) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			
			System.out.println();
			System.out.print("Enter 'y' to confirm deletion or any other string to cancel deletion:");
			if(DatabaseInteractor.scanner.nextLine().equals("y")) {
				DatabaseInteractor.equipments.remove(result);
				System.out.println("Deletion successful");
			}
		} else {
			System.out.println("Serial number not found among equipment");
		}
		
		DatabaseInteractor.scanner.nextLine();
	}
	
	public static void DeleteMember() {
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
			System.out.println("Result found:");
			System.out.println("User ID: " + result.userId);
			System.out.println("Last Name: " + result.lName);
			System.out.println("First Name: " + result.fName);
			System.out.println("Address: " + result.address);
			System.out.println("Phone Number: " + result.phoneNumber);
			System.out.println("Email: " + result.email);
			System.out.println("Start Date: " + result.startDate);
			
			System.out.println();
			System.out.print("Enter 'y' to confirm deletion or any other string to cancel deletion:");
			if(DatabaseInteractor.scanner.nextLine().equals("y")) {
				DatabaseInteractor.members.remove(result);
				System.out.println("Deletion successful");
			}
		} else {
			System.out.println("Last name not found among members");
		}
		
		DatabaseInteractor.scanner.nextLine();
	}
	
	public static void DeleteWarehouse() {
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
			System.out.println("Result found:");
			System.out.println("City: " + result.city);
			System.out.println("Address: " + result.address);
			System.out.println("Drone Cap: " + result.droneCap);
			System.out.println("Storage Cap: " + result.storageCap);
			System.out.println("Phone Number: " + result.phoneNumber);
			System.out.println("Manager Name: " + result.managerName);
			
			System.out.println();
			System.out.print("Enter 'y' to confirm deletion or any other string to cancel deletion:");
			if(DatabaseInteractor.scanner.nextLine().equals("y")) {
				DatabaseInteractor.warehouses.remove(result);
				System.out.println("Deletion successful");
			}
		} else {
			System.out.println("Address/city not found among warehouses");
		}
		
		DatabaseInteractor.scanner.nextLine();
	}
}
