package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	
private static Connection conn = null;
	
	private DBManager() {}
	
	public static Connection getConnection() throws SQLException {
			
			//jdbc:h2:file:C:\Users\gio\Desktop\IDS\progetto-GioLines\JAVA-GioLines\GioLines\lib\H2\database;AUTO_SERVER=TRUE

			String root = "C:/Users/gio/Desktop/IDS/progetto-GioLines/JAVA-GioLines/GioLines/lib/H2/"; //~/
			String dbName = "database"; 
            String username = "admin";
            String password = "";

			if(conn == null || conn.isClosed()) {

                conn = DriverManager.getConnection(
                    "jdbc:h2:file:" + root + dbName + ";AUTO_SERVER=TRUE",
                    username, 
                    password
                );
                
			}
			
			return conn;
		
	}
	
	
	public static void closeConnection() throws SQLException {
		
			if(conn != null) {
				conn.close();
			}
	}


    public static void testConnection(){

        try{
            
			Statement stmnt = conn.createStatement();
			ResultSet r = stmnt.executeQuery("SELECT * FROM IMPIEGATO");
			
			
			while (r.next()){
				int id = r.getInt("ID");
				if (r.wasNull()) System.out.println("id is null"); 
				String p = r.getString("PASSWORD");
				if (r.wasNull()) System.out.println("name is null"); 
				System.out.println(id + ": " + p);
			}
			
			r.close();
			stmnt.close();

        } catch (SQLException e) {

            e.printStackTrace();

		}	


    }

	
	/*FUNZIONE DI TEST: comunicazione tra control e boundary*/
	// public static void main(String[] args) {
		
	// 	try {
	// 		getConnection();
	// 		testConnection();
	// 		closeConnection();
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 	}	

	// }

}
