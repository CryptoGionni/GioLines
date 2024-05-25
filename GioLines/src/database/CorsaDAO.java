package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityCorsa;
import exception.DAOException;
import exception.DBConnectionException;
public class CorsaDAO {


    public static EntityCorsa readCorsa(int idCorsa)throws DAOException, DBConnectionException {

        EntityCorsa ec = null;

		try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "SELECT * FROM Corsa WHERE id=?";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, idCorsa);

                ResultSet result = stmt.executeQuery();

				if(result.next()) {
                    
                    ec = new EntityCorsa(
                        result.getInt(1),
                        result.getDate(2),
                        result.getTime(3),
                        result.getTime(4),
                        result.getFloat(5)
                        );
                    
				} else{
					System.out.println("Corsa insesistente");
				}
				

				

			}catch(SQLException e) {
				throw new DAOException("Errore lettura Corsa");
			}finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione al DB");
		}


        return ec;


    }


	// public static void main(String[] args) {
		
	// 	try {
    //         System.out.println(readCorsa(1));
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}	

	// }
    
}
