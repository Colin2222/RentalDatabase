import java.util.Scanner;

public class DatabaseInteractor {
	private static String DATABASE_FP = "checkpoint_3.db";
	
	public static Scanner scanner;
	public static Database db;
	public static int maxSerialNumber = 0;
	public static int maxInventoryId = 0;
	public static int maxUserId = 18;
	
	public static void main(String[] args)
	{
		scanner = new Scanner(System.in);
		db = new Database(DATABASE_FP);
		TopLayerMenu();
	}
	
	private static void TopLayerMenu() {
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
			CreateHelper.AddMenu();
			TopLayerMenu();
			break;
		case 2:
			EditHelper.EditMenu();
			TopLayerMenu();
			break;
		case 3:
			DeleteHelper.DeleteMenu();
			TopLayerMenu();
			break;
		case 4:
			SearchHelper.SearchMenu();
			TopLayerMenu();
			break;
		case 5:
			ReportsMenu();
			TopLayerMenu();
			break;
		}
	}
	
	private static void ReportsMenu() {
		System.out.println("This feature is still WIP and is not available in this version.");
		scanner.nextLine();
	}
}
