package control;

import java.util.ArrayList;
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

    private static GestioneTrasporto gC = null;
    public static GestioneTrasporto getInstance() 
	{ 
		if (gC == null) 
			gC = new GestioneTrasporto(); 

		return gC; 
	}
    
    public ArrayList<String> acquistaBigliettoViaWeb(
        String CITTAPARTENZA,
        String CITTAARRIVO,
        Date ORARIOPARTENZA,
        String MAIL,
        float PREZZOBIGLIETTIMASSIMO,
        int NUMEROSEDILI,
        int NUMEROBAGAGLI,
        float DIMENSIONEBAGAGLIO
        )throws OperationException{


        ArrayList<String> proposta = new ArrayList<String>();
        proposta.add("0");
        proposta.add("0");
        proposta.add("0");
        proposta.add("0");


        int idCorsa = 0;
        float prezzoTotale = 0;
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

            //assert NUMEROSEDILI+ea.getSediliOccupati()<=ea.getSediliMax() : "Posti sedili disponibili insufficienti";
            if(NUMEROSEDILI+ea.getSediliOccupati()>ea.getSediliMax()) {
                throw new OperationException("Posti sedili disponibili insufficienti");
            }

            //assert NUMEROBAGAGLI+ea.getBagagliOccupati()<=ea.getBagagliMax() : "Posti bagagli disponibili insufficienti";
            if(NUMEROBAGAGLI+ea.getBagagliOccupati()>ea.getBagagliMax()) {
                throw new OperationException("Posti bagagli disponibili insufficienti");
            }

            //assert DIMENSIONEBAGAGLIO <= ea.getDimensioneBagaglio() : "Dimensione bagaglio non rispettata";
            if(DIMENSIONEBAGAGLIO > ea.getDimensioneBagaglio()) {
                throw new OperationException("Dimensione bagaglio non rispettata");
            }

            //calcolo prezzo dell'ordine            
            prezzoTotale = calcolaPrezzoTotale(ec.getPrezzoBiglietto(), NUMEROSEDILI);
            if(NUMEROBAGAGLI>0){
                prezzoTotale += applicaSupplemento(NUMEROBAGAGLI);
            }

            //preparazione ArrayList di stringhe di ritorno al boundary
            proposta.set(0, String.valueOf(idCorsa));             
            proposta.set(1, String.valueOf(ec.getOrarioPartenza())); 
            proposta.set(2, String.valueOf(ec.getOrarioArrivo()));           
            proposta.set(3, String.valueOf(prezzoTotale)); 
            return proposta;

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

    private int applicaSupplemento(int N){
        int res = 0;
        for(int i = 1; i<=N; i++){
            res = res + 5;
        }
        return res;
    }

    private float calcolaPrezzoTotale(float prezzoSingolo, int numeroBiglietti){
        return (prezzoSingolo*numeroBiglietti);
    }



    // private void registrazioneInternaBiglietto(){}

    // private void invioMail(){}
}
