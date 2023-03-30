import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Database {
	private static String addEquipmentSql = "INSERT INTO EQUIPMENT VALUES (?, ?, ?);";
    Connection conn;

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
    }

    public void insertMember(String lName, String fName, String address, String phoneNum, String email, LocalDate date, int userID) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(3, lName);
			stmt.setString(2, fName);
			stmt.setString(4, address);
			stmt.setString(5, phoneNum);
			stmt.setString(6, email);
			stmt.setDate(7, java.sql.Date.valueOf(date));
			stmt.setInt(1, userID);
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertWarehouse(String city, String address, String managerName, String phoneNum, int droneCap, int storageCap) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(2, city);
			stmt.setString(1, address);
			stmt.setString(6, managerName);
			stmt.setString(5, phoneNum);
			stmt.setInt(3, droneCap);
			stmt.setInt(4, storageCap);
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertDroneFleet(String fleetID, float distance, float maxSpeed, float weightCap) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(1, fleetID);
			stmt.setFloat(2, distance);
			stmt.setFloat(3, maxSpeed);
			stmt.setFloat(2, weightCap);
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertDroneTech(String phoneNum, String lName, String fName, String email, String city, String website) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(1, phoneNum);
			stmt.setString(3, lName);
			stmt.setString(2, fName);
			stmt.setString(4, email);
			stmt.setString(5, city);
			stmt.setString(6, website); 
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertSupplier(String website, String name, String address, String warehouse, String phoneNum) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(1, website);
			stmt.setString(2, name);
			stmt.setString(3, address);
			stmt.setString(4, warehouse);
			stmt.setString(5, phoneNum);
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertItemModel(String modelNo, String modelName, String description, String website, String type, int year, float size, float weight) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(1, modelNo);
			stmt.setString(2, modelName);
			stmt.setString(3, description);
			stmt.setString(4, website);
			stmt.setString(6, type);
			stmt.setInt(5, year);
			stmt.setFloat(7, size);
			stmt.setFloat(8, weight);
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertInventoryItem(String serialNo, String warehouseAddress, String invOrderNo, LocalDate warrantyExpiryDate, String modelNo) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(2, serialNo);
			stmt.setString(1, warehouseAddress);
			stmt.setString(3, invOrderNo);
			stmt.setDate(4, java.sql.Date.valueOf(warrantyExpiryDate));
			stmt.setString(5, modelNo);
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertEquipment(String serialNo, String rentalStatus, String invID) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(1, serialNo);
			stmt.setString(2, rentalStatus);
			stmt.setString(3, invID);
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertEquipmentRental(int userID, String serialNo, LocalDate checkoutDate, LocalDate returnDate, LocalDate dueDate) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setInt(1, userID);
			stmt.setString(2, serialNo);
			stmt.setDate(3, java.sql.Date.valueOf(checkoutDate));
			stmt.setDate(4, java.sql.Date.valueOf(returnDate));
			stmt.setDate(5, java.sql.Date.valueOf(dueDate));
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertDrone(String serialNo, String fleetID, String availability) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(1, serialNo);
			stmt.setString(2, fleetID);
			stmt.setString(3, availability);
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertEquipmentDelivery(String equipSerialNo, String droneSerialNo, LocalDate deliveryDate) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(1, equipSerialNo);
			stmt.setString(2, droneSerialNo);
			stmt.setDate(3, java.sql.Date.valueOf(deliveryDate));
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertReview(String equipSerialNo, String reviewID, int userID, String comment, String rating) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(1, equipSerialNo);
			stmt.setString(2, reviewID);
			stmt.setInt(3, userID);
			stmt.setString(4, comment);
			stmt.setString(5, rating);
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertInventoryOrder(String warehouseAddress, String supplierAddress, String orderID, String modelNo, float quantity, LocalDate ead, LocalDate aad) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(1, warehouseAddress);
			stmt.setString(2, supplierAddress);
			stmt.setString(3, orderID);
			stmt.setString(4, modelNo);
			stmt.setFloat(5, quantity);
			stmt.setDate(6, java.sql.Date.valueOf(ead));
			stmt.setDate(7, java.sql.Date.valueOf(aad));
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertDroneRepair(String droneSerialNo, String techPhoneNum, LocalDate date) {
		try {
			PreparedStatement stmt = this.conn.prepareStatement(addEquipmentSql);
			
			stmt.setString(1, droneSerialNo);
			stmt.setString(2, techPhoneNum);
			stmt.setDate(3, java.sql.Date.valueOf(date));
			
			stmt.executeUpdate();
			
			System.out.println("Insertion successful");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
