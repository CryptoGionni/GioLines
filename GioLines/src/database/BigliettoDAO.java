package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
//import java.text.SimpleDateFormat; //fornisce un metodo di conversione comodo per la funzione di test di inserimento dati


import exception.DAOException;
import exception.DBConnectionException;

public class BigliettoDAO {
    
    public static int updateBiglietto(Date data, Time ora, int impiegato, boolean bagaglio)throws DAOException, DBConnectionException {

        try {

                    Connection conn = DBManager.getConnection();

                    try {

                        String query = "INSERT INTO BIGLIETTO(IDIMPIEGATO , ORAEMISSIONE , DATAEMISSIONE , PRESENZABAGAGLIO) VALUES(?,?,?,?);";
                        
                        PreparedStatement stmt = conn.prepareStatement(query);

                        stmt.setInt(1, impiegato);
                        stmt.setTime(2, ora);				
                        stmt.setDate(3, data);
                        stmt.setBoolean(4, bagaglio);

                        return stmt.executeUpdate();
                        

                    }catch(SQLException e) {
                        throw new DAOException("Errore scrittura Biglietto");
                    }finally {
                        DBManager.closeConnection();
                    }
                    
                }catch(SQLException e) {
                    throw new DBConnectionException("Errore di connessione al DB");
                }



    }


    /*FUNZIONE DI TEST: comunicazione DB*/
	// public static void main(String[] args) {
		
	// 	try {
    //         Time t = new Time(new SimpleDateFormat("HH:mm").parse("11:22").getTime());
    //         Date d = Date.valueOf("2024-05-28");
    //         System.out.println(updateBiglietto(d, t, 1, true));
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}	

	// }



}
