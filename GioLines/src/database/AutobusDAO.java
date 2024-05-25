package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityAutobus;
import exception.DAOException;
import exception.DBConnectionException;

public class AutobusDAO {
    
    public static EntityAutobus readAutobus(int idCorsa)throws DAOException, DBConnectionException {

        EntityAutobus ea = null;

		try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "SELECT * FROM Autobus WHERE idCorsa=?";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, idCorsa);

                ResultSet result = stmt.executeQuery();

				if(result.next()) {
                    
                    ea = new EntityAutobus(
                        result.getInt(1),                        
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getInt(5),
                        result.getInt(6),
                        result.getFloat(7)
                        );
                    
				} else{
					System.out.println("Autobus insesistente");
				}
				

				

			}catch(SQLException e) {
				throw new DAOException("Errore lettura Autobus");
			}finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione al DB");
		}


        return ea;


    }


	// public static void main(String[] args) {
		
	// 	try {
    //         System.out.println(readAutobus(1));
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}	

	// }
}
