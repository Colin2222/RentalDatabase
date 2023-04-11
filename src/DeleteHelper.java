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
		System.out.print("Enter the serial number of the equipment: ");
		String serialNo = DatabaseInteractor.scanner.nextLine();
		
		boolean success = DatabaseInteractor.db.deleteEquipmentBySerialNo(serialNo);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void DeleteMember() {
		System.out.print("Enter the user ID of the member: ");
		int userID = DatabaseInteractor.scanner.nextInt();
		DatabaseInteractor.scanner.nextLine();
		
		boolean success = DatabaseInteractor.db.deleteMemberByUserID(userID);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
	
	public static void DeleteWarehouse() {
		System.out.print("Enter the address of the warehouse: ");
		String address = DatabaseInteractor.scanner.nextLine();
		
		boolean success = DatabaseInteractor.db.deleteWarehouseByAddress(address);
		
		if (success)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
}
