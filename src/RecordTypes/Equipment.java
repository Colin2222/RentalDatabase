package RecordTypes;

import java.time.LocalDate;

public class Equipment extends Record{
	public String name; 
	public float weight; 
	public float size; 
	public String type;
	public int inventoryId;
	public int rentalStatus; 
	public int serialNo;
	public int modelNo;
	public LocalDate warrantyExpirationDate;
	public int year;
	public String locationCode;
	public String manufacturer;
	public String description; 
	
	public Equipment() {
		
	}
}
