package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.DAOException;
import exception.DBConnectionException;

public class TrattaDAO {

    public static int readTratta(String CITTAPARTENZA, String CITTAARRIVO)throws DAOException, DBConnectionException {

        int  idCorsa = 0;

		try {

			Connection conn = DBManager.getConnection();

			try {
				
				String query = "SELECT idCorsa FROM Tratta WHERE cittàPartenza=? AND cittàArrivo=?";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, CITTAPARTENZA);
				stmt.setString(2, CITTAARRIVO);

                ResultSet result = stmt.executeQuery();

				if(result.next()) {
					idCorsa = result.getInt(1);
				} else{
					System.out.println("Tratta inesistente");
				}
				

				

			}catch(SQLException e) {
				throw new DAOException("Errore lettura Tratta");
			}finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione al DB");
		}


        return idCorsa;


    }


	// public static void main(String[] args) {
		
	// 	try {
    //         System.out.println(readTratta("NAPOLI","ROMA"));
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}	

	// }
    
}
