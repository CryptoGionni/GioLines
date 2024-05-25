package control;

import java.util.Date;

import database.TrattaDAO;  //fornisce la funzione readTratta
import database.CorsaDAO;   //fornisce la funzione readCorsa
import database.AutobusDAO; //fornisce la funzione readAutobus
import entity.EntityCorsa;  //fornisce il tipo EntityCorsa
import entity.EntityAutobus;  //fornisce il tipo EntityAutobus

import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class GestioneTrasporto {
    
    public void acquistaBigliettoViaWeb(
        String CITTAPARTENZA,
        String CITTAARRIVO,
        Date ORARIOPARTENZA,
        String MAIL,
        float PREZZOBIGLIETTIMASSIMO,
        int NUMEROSEDILI,
        int NUMEROBAGAGLI,
        float DIMENSIONEBAGAGLIO
    )throws OperationException{

        int idCorsa = 0;
        EntityCorsa ec = null;
        EntityAutobus ea = null;

        try {

            //assert NUMEROSEDILI >= NUMEROBAGAGLI : "numero bagagli errato";
            if(NUMEROSEDILI < NUMEROBAGAGLI) {
				throw new OperationException("Numero bagagli errato");
			}
            idCorsa = TrattaDAO.readTratta(CITTAPARTENZA, CITTAARRIVO);

            //assert idCorsa != 0 : "tratta non disponibile";            
            if(idCorsa == 0) {
				throw new OperationException("Tratta non disponibile");
			}
            ec = CorsaDAO.readCorsa(idCorsa);

            //assert ORARIOPARTENZA <= ec.getOrarioPartenza() || PREZZOBIGLIETTIMASSIMO >= ec.getPrezzoBiglietto() : "Corsa non compatibile";            
            if(ORARIOPARTENZA.after(ec.getOrarioPartenza()) || PREZZOBIGLIETTIMASSIMO < ec.getPrezzoBiglietto()) {
                throw new OperationException("Corsa non compatibile");
            }
            ea = AutobusDAO.readAutobus(idCorsa);

            //assert NUMEROSEDILI+ea.getSediliOccupati()<=ea.getSediliMax() : Posti sedili disponibili insufficienti;
            if(NUMEROSEDILI+ea.getSediliOccupati()>ea.getSediliMax()) {
                throw new OperationException("Posti sedili disponibili insufficienti");
            }

            //assert NUMEROBAGAGLI+ea.getBagagliOccupati()<=ea.getBagagliMax() : Posti bagagli disponibili insufficienti;
            if(NUMEROBAGAGLI+ea.getBagagliOccupati()>ea.getBagagliMax()) {
                throw new OperationException("Posti bagagli disponibili insufficienti");
            }

            //assert DIMENSIONEBAGAGLIO <= ea.getDimensioneBagaglio() : Dimensione bagaglio non rispettata;
            if(DIMENSIONEBAGAGLIO > ea.getDimensioneBagaglio()) {
                throw new OperationException("Dimensione bagaglio non rispettata");
            }


        //}catch(AssertionError e) {
            //System.out.println(e.getMessage());
        }catch(DBConnectionException dbEx) {
            throw new OperationException("\nRiscontrato problema interno applicazione!\n");
        }catch(DAOException ex) {
            throw new OperationException("\nOps, qualcosa e' andato storto..\n");
        }
        

    }



    public void vendiBiglietto(
        String CITTAPARTENZA,
        String CITTAARRIVO,
        Date ORARIOPARTENZA,
        float PREZZOBIGLIETTIMASSIMO,
        int NUMEROSEDILI,
        int NUMEROBAGAGLI,
        float DIMENSIONEBAGAGLIO
    )throws OperationException{



    }


    private void invioMail(){}
    private void applicaSupplemento(){}
    private void registrazioneInternaBiglietto(){}

}
