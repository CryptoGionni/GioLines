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
			
			//jdbc:h2:file:C:\Users\gio\Desktop\IDS\progetto-GioLines\JAVA-GioLines\GioLines\database;AUTO_SERVER=TRUE
			
			String root = "./"; 
			String dbName = "database";
			String username = "admin";
            String password = "";

			if(conn == null || conn.isClosed()) {

                conn = DriverManager.getConnection(
                    "jdbc:h2:" + root + dbName + ";AUTO_SERVER=TRUE",
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
			ResultSet r = stmnt.executeQuery("SELECT * FROM TRATTA");
			
			
			while (r.next()){
				int id = r.getInt("IDCORSA");
				if (r.wasNull()) System.out.println("id is null"); 
				System.out.println(id );
			}
			
			r.close();
			stmnt.close();

        } catch (SQLException e) {

            e.printStackTrace();

		}	


    }

	
	/*FUNZIONE DI TEST: comunicazione DB*/
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
