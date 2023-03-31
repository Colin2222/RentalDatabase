import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Database {
    private Connection conn;
    private static String sqlGetMaxUserID = "SELECT MAX(UserID) FROM MEMBER";
	private static String sqlInsertMember = "INSERT INTO MEMBER VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static String sqlInsertWarehouse = "INSERT INTO WAREHOUSE VALUES (?, ?, ?, ?, ?, ?);";
	private static String sqlInsertDroneFleet = "INSERT INTO DRONE_FLEET VALUES (?, ?, ?, ?);";
	private static String sqlInsertDroneTech = "INSERT INTO DRONE_TECH VALUES (?, ?, ?, ?, ?, ?);";
	private static String sqlInsertSupplier = "INSERT INTO SUPPLIER VALUES (?, ?, ?, ?, ?);";
	private static String sqlInsertItemModel = "INSERT INTO ITEM_MODEL VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static String sqlInsertInventoryItem = "INSERT INTO INVENTORY_ITEM VALUES (?, ?, ?, ?, ?);";
	private static String sqlInsertEquipment = "INSERT INTO EQUIPMENT VALUES (?, ?, ?);";
	private static String sqlInsertEquipmentRental = "INSERT INTO EQUIPMENT_RENTAL VALUES (?, ?, ?, ?, ?);";
	private static String sqlInsertDrone = "INSERT INTO DRONE VALUES (?, ?, ?);";
	private static String sqlInsertEquipmentDelivery = "INSERT INTO EQUIPMENT_DELIVERY VALUES (?, ?, ?);";
	private static String sqlInsertReview = "INSERT INTO REVIEW VALUES (?, ?, ?, ?, ?);";
	private static String sqlInsertInventoryOrder = "INSERT INTO INVENTORY_ORDER VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static String sqlInsertDroneRepair = "INSERT INTO DRONE_REPAIR VALUES (?, ?, ?);";
	private static String selectMemberByUserIDSQL = "SELECT * FROM MEMBER WHERE userID=?";
	private static String selectWarehouseByAddressSQL = "SELECT * FROM WAREHOUSE WHERE address=?;";
	
	
	
	
	private static String selectEquipmentBySerialNoSQL = "SELECT * FROM EQUIPMENT WHERE serialNo=?;";
	private static String deleteMemberByUserIDSQL = "DELETE FROM MEMBER WHERE userID=?;";
	private static String deleteWarehouseByAddressSQL = "DELETE FROM WAREHOUSE WHERE address=?;";
	private static String deleteEquipmentBySerialNoSQL = "DELETE FROM EQUIPMENT WHERE serialNo=?;";
	
	private static String memberRentalReportSql = "SELECT count(*) " + 
			"FROM MEMBER AS M, EQUIPMENT_RENTAL AS R " + 
			"WHERE M.UserID = R.UserID " + 
			"AND M.UserID = ?;";
	private static String mostPopularItemReportSql = "SELECT ModelName, MAX(rental_total) " + 
			"FROM  (SELECT ModelName, COUNT(*) as rental_total " + 
			"    FROM EQUIPMENT, EQUIPMENT_RENTAL, INVENTORY_ITEM, ITEM_MODEL " + 
			"    WHERE EQUIPMENT.SerialNo = EQUIPMENT_RENTAL.EquipmentSerialNo " + 
			"    AND EQUIPMENT.SerialNo = INVENTORY_ITEM.SerialNo " + 
			"    AND INVENTORY_ITEM.ModelNo = ITEM_MODEL.ModelNo " + 
			"    GROUP BY ITEM_MODEL.ModelNo);";
	private static String mostPopularManufacturerReportSql = "SELECT SupplierName, MAX(rental_total) " + 
			"FROM (SELECT SupplierName, COUNT(*) as rental_total " + 
			"    FROM EQUIPMENT, EQUIPMENT_RENTAL, INVENTORY_ITEM, ITEM_MODEL, SUPPLIER " + 
			"    WHERE EQUIPMENT.SerialNo = EQUIPMENT_RENTAL.EquipmentSerialNo " + 
			"    AND EQUIPMENT.SerialNo = INVENTORY_ITEM.SerialNo " + 
			"    AND INVENTORY_ITEM.ModelNo = ITEM_MODEL.ModelNo " + 
			"    AND ITEM_MODEL.MWebsite = SUPPLIER.Website " + 
			"    GROUP BY SUPPLIER.Website);";
	private static String mostPopularDroneReportSql = "SELECT SerialNo, MAX(deliv_total) FROM (" +
			"SELECT SerialNo, COUNT(*) as deliv_total" + 
			"FROM DRONE, EQUIPMENT_DELIVERY" + 
			"WHERE DRONE.SerialNo = EQUIPMENT_DELIVERY.DroneSerialNo" + 
			"GROUP BY DRONE.SerialNo" + 
			"ORDER BY deliv_total DESC);";
	
	private static String mostActiveMemberReportSql = "SELECT UserID, FName, LName, MAX(rental_total) " + 
			"FROM (SELECT EQUIPMENT_RENTAL.UserID, MEMBER.LName, MEMBER.FName, COUNT(*) as rental_total " + 
			"    FROM EQUIPMENT_RENTAL, MEMBER " + 
			"    WHERE EQUIPMENT_RENTAL.UserID = MEMBER.UserID " + 
			"    GROUP BY EQUIPMENT_RENTAL.UserID);";
	
	private static String typeAndYearReportSql = "SELECT ModelName, Description " + 
			"FROM ITEM_MODEL AS M " + 
			"WHERE M.Type = ? AND M.Year < ?;";
	
    public Database(String databaseFileName) {
        /**
         * The "Connection String" or "Connection URL".
         *
         * "jdbc:sqlite:" is the "subprotocol". (If this were a SQL Server
         * database it would be "jdbc:sqlserver:".)
         */
        String url = "jdbc:sqlite:" + databaseFileName;
        this.conn = null; // If you create this variable inside the Try block it will be out of scope
        try {
            this.conn = DriverManager.getConnection(url);
            if (this.conn != null) {
                // Provides some positive assurance the connection and/or creation was successful.
                DatabaseMetaData meta = this.conn.getMetaData();
                System.out
                        .println("The driver name is " + meta.getDriverName());
                System.out.println(
                        "The connection to the database was successful.");
            } else {
                // Provides some feedback in case the connection failed but did not throw an exception.
                System.out.println("Null Connection");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out
                    .println("There was a problem connecting to the database.");
        }
        
        // set the maximum member user id value based on existing database values to prevent overlap
        try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlGetMaxUserID);
			ResultSet rSet = stmt.executeQuery();
			DatabaseInteractor.maxUserId = rSet.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
    
    public void closeConnection() {
    	try {
			this.conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("There was a problem while closing the database connection.");
		}
    }

    public boolean insertMember(String lName, String fName, String address, String phoneNum, String email, LocalDate date, int userID) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertMember);
			
			stmt.setString(3, lName);
			stmt.setString(2, fName);
			stmt.setString(4, address);
			stmt.setString(5, phoneNum);
			stmt.setString(6, email);
			stmt.setDate(7, java.sql.Date.valueOf(date));
			stmt.setInt(1, userID);
			
			stmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertWarehouse(String city, String address, String managerName, String phoneNum, int droneCap, int storageCap) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertWarehouse);
			
			stmt.setString(2, city);
			stmt.setString(1, address);
			stmt.setString(6, managerName);
			stmt.setString(5, phoneNum);
			stmt.setInt(3, droneCap);
			stmt.setInt(4, storageCap);
			
			stmt.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertDroneFleet(String fleetID, float distance, float maxSpeed, float weightCap) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertDroneFleet);
			
			stmt.setString(1, fleetID);
			stmt.setFloat(2, distance);
			stmt.setFloat(3, maxSpeed);
			stmt.setFloat(2, weightCap);
			
			stmt.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertDroneTech(String phoneNum, String lName, String fName, String email, String city, String website) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertDroneTech);
			
			stmt.setString(1, phoneNum);
			stmt.setString(3, lName);
			stmt.setString(2, fName);
			stmt.setString(4, email);
			stmt.setString(5, city);
			stmt.setString(6, website); 
			
			stmt.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertSupplier(String website, String name, String address, String warehouse, String phoneNum) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertSupplier);
			
			stmt.setString(1, website);
			stmt.setString(2, name);
			stmt.setString(3, address);
			stmt.setString(4, warehouse);
			stmt.setString(5, phoneNum);
			
			stmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertItemModel(String modelNo, String modelName, String description, String website, String type, int year, float size, float weight) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertItemModel);
			
			stmt.setString(1, modelNo);
			stmt.setString(2, modelName);
			stmt.setString(3, description);
			stmt.setString(4, website);
			stmt.setString(6, type);
			stmt.setInt(5, year);
			stmt.setFloat(7, size);
			stmt.setFloat(8, weight);
			
			stmt.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertInventoryItem(String serialNo, String warehouseAddress, String invOrderNo, LocalDate warrantyExpiryDate, String modelNo) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertInventoryItem);
			
			stmt.setString(2, serialNo);
			stmt.setString(1, warehouseAddress);
			stmt.setString(3, invOrderNo);
			stmt.setDate(4, java.sql.Date.valueOf(warrantyExpiryDate));
			stmt.setString(5, modelNo);
			
			stmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertEquipment(String serialNo, String rentalStatus, String invID) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertEquipment);
			
			stmt.setString(1, serialNo);
			stmt.setString(2, rentalStatus);
			stmt.setString(3, invID);
			
			stmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertEquipmentRental(int userID, String serialNo, LocalDate checkoutDate, LocalDate returnDate, LocalDate dueDate) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertEquipmentRental);
			
			stmt.setInt(1, userID);
			stmt.setString(2, serialNo);
			stmt.setDate(3, java.sql.Date.valueOf(checkoutDate));
			stmt.setDate(4, java.sql.Date.valueOf(returnDate));
			stmt.setDate(5, java.sql.Date.valueOf(dueDate));
			
			stmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertDrone(String serialNo, String fleetID, String availability) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlInsertDrone);
			
			stmt.setString(1, serialNo);
			stmt.setString(2, fleetID);
			stmt.setString(3, availability);
			
			stmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertEquipmentDelivery(String equipSerialNo, String droneSerialNo, LocalDate deliveryDate) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertEquipmentDelivery);
			
			stmt.setString(1, equipSerialNo);
			stmt.setString(2, droneSerialNo);
			stmt.setDate(3, java.sql.Date.valueOf(deliveryDate));
			
			stmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertReview(String equipSerialNo, String reviewID, int userID, String comment, String rating) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertReview);
			
			stmt.setString(1, equipSerialNo);
			stmt.setString(2, reviewID);
			stmt.setInt(3, userID);
			stmt.setString(4, comment);
			stmt.setString(5, rating);
			
			stmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertInventoryOrder(String warehouseAddress, String supplierAddress, String orderID, String modelNo, float quantity, LocalDate ead, LocalDate aad) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertInventoryOrder);
			
			stmt.setString(1, warehouseAddress);
			stmt.setString(2, supplierAddress);
			stmt.setString(3, orderID);
			stmt.setString(4, modelNo);
			stmt.setFloat(5, quantity);
			stmt.setDate(6, java.sql.Date.valueOf(ead));
			stmt.setDate(7, java.sql.Date.valueOf(aad));
			
			stmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insertDroneRepair(String droneSerialNo, String techPhoneNum, LocalDate date) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsertDroneRepair);
			
			stmt.setString(1, droneSerialNo);
			stmt.setString(2, techPhoneNum);
			stmt.setDate(3, java.sql.Date.valueOf(date));
			
			stmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public ResultSet selectEquipmentBySerialNo(String serialNo) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(selectEquipmentBySerialNoSQL);
			stmt.setString(1, serialNo);
			
			ResultSet res = stmt.executeQuery();
			return res;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ResultSet selectMemberByUserID(int userID) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(selectMemberByUserIDSQL);
			stmt.setInt(1, userID);
			
			ResultSet res = stmt.executeQuery();
			return res;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ResultSet selectWarehouseByAddress(String address) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(selectWarehouseByAddressSQL);
			stmt.setString(1, address);
			
			ResultSet res = stmt.executeQuery();
			return res;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean deleteEquipmentBySerialNo(String serialNo) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(deleteEquipmentBySerialNoSQL);
			stmt.setString(1, serialNo);
			
			stmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean deleteMemberByUserID(int userID) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(deleteMemberByUserIDSQL);
			stmt.setInt(1, userID);
			
			stmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean deleteWarehouseByAddress(String address) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(deleteWarehouseByAddressSQL);
			stmt.setString(1, address);
			
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public <T> boolean updateEquipmentBySerialNo(String preparedQuery, String serialNo, T newValue) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(preparedQuery);
			stmt.setString(2, serialNo);
			
			if (newValue instanceof String) {
				stmt.setString(1, (String)newValue);
				
			} else if (newValue instanceof Integer) {
				stmt.setInt(1, (int)newValue);
				
			} else if (newValue instanceof Float) {
				stmt.setFloat(1, (float)newValue);
				
			} else if (newValue instanceof LocalDate) {
				stmt.setDate(1, java.sql.Date.valueOf((LocalDate)newValue));
				
			}
			
			stmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public <T> boolean updateMemberByUserID(String preparedQuery, int userID, T newValue) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(preparedQuery);
			stmt.setInt(2, userID);
			
			if (newValue instanceof String) {
				stmt.setString(1, (String)newValue);
				
			} else if (newValue instanceof Integer) {
				stmt.setInt(1, (int)newValue);
				
			} else if (newValue instanceof Float) {
				stmt.setFloat(1, (float)newValue);
				
			} else if (newValue instanceof LocalDate) {
				stmt.setDate(1, java.sql.Date.valueOf((LocalDate)newValue));
				
			}
			
			stmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public <T> boolean updateWarehouseByAddress(String preparedQuery, String address, T newValue) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(preparedQuery);
			stmt.setString(2, address);
			
			if (newValue instanceof String) {
				stmt.setString(1, (String)newValue);
				
			} else if (newValue instanceof Integer) {
				stmt.setInt(1, (int)newValue);
				
			} else if (newValue instanceof Float) {
				stmt.setFloat(1, (float)newValue);
				
			} else if (newValue instanceof LocalDate) {
				stmt.setDate(1, java.sql.Date.valueOf((LocalDate)newValue));
				
			}
			
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public ResultSet reportMemberRentalTotal(int userID) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(memberRentalReportSql);
			stmt.setInt(1, userID);
			
			ResultSet res = stmt.executeQuery();
			return res;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ResultSet reportMostPopularItem() {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(mostPopularItemReportSql);
			
			ResultSet res = stmt.executeQuery();
			return res;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ResultSet reportMostPopularManufacturer() {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(mostPopularManufacturerReportSql);
			
			ResultSet res = stmt.executeQuery();
			return res;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ResultSet reportMostPopularDrone() {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(mostPopularDroneReportSql);
			
			ResultSet res = stmt.executeQuery();
			return res;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ResultSet reportMostActiveMember() {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(mostActiveMemberReportSql);
			
			ResultSet res = stmt.executeQuery();
			return res;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ResultSet reportByTypeAndYear(String type, int year) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(typeAndYearReportSql);
			stmt.setString(1, type);
			stmt.setInt(2, year);
			
			ResultSet res = stmt.executeQuery();
			return res;
			
		} catch (SQLException e ){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
