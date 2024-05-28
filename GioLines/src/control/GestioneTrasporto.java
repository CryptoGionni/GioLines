package control;

import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate; // import the LocalDate class
import java.time.LocalTime; // import the LocalTime class
import java.time.temporal.ChronoUnit;

import database.BigliettoDAO; //fornisce la updateBiglietto
import database.TrattaDAO;  //fornisce la funzione readTratta
import database.CorsaDAO;   //fornisce la funzione readCorsa
import database.AutobusDAO; //fornisce la funzione readAutobus e updateAutobus
import entity.EntityCorsa;  //fornisce il tipo EntityCorsa
import entity.EntityAutobus;  //fornisce il tipo EntityAutobus


import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        Time ORARIOPARTENZA,
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

            /* calcolo prezzo dell'ordine */
            prezzoTotale = calcolaPrezzoTotale(ec.getPrezzoBiglietto(), NUMEROSEDILI);
            if(NUMEROBAGAGLI>0){
                prezzoTotale += applicaSupplemento(NUMEROBAGAGLI);
            }

            /* preparazione ArrayList di stringhe di ritorno al boundary */
            proposta.set(0, String.valueOf(idCorsa));             
            proposta.set(1, String.valueOf(ec.getOrarioPartenza())); 
            proposta.set(2, String.valueOf(ec.getOrarioArrivo()));           
            proposta.set(3, String.valueOf(prezzoTotale)); 

            /* destroy delle entità di appoggio */
            ec = null;
            ea = null;

            return proposta;

        }catch(DBConnectionException dbEx) {
            throw new OperationException("\nRiscontrato problema interno applicazione [ab]!\n");
        }catch(DAOException ex) {
            throw new OperationException("\nOps, qualcosa e' andato storto...\n");
        }
        

    }


    public void confermaAcquisto(ArrayList<String> propostaConfermata, int numSedili, int numBagagli, String mail)throws OperationException{

        try{

            setNumeroSediliDisponibili(numSedili, numBagagli, propostaConfermata.get(0));            
            
            for(int i = 1; i<=numBagagli; i++){
                registrazioneInternaBiglietto(String.valueOf(LocalDate.now()),String.valueOf(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)),42,true);
            }

            for(int i = 1; i<=(numSedili-numBagagli); i++){
                registrazioneInternaBiglietto(String.valueOf(LocalDate.now()),String.valueOf(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)),42,false);
            }

            invioMail(propostaConfermata.get(0), propostaConfermata.get(1), propostaConfermata.get(2), propostaConfermata.get(3), numSedili, numBagagli, mail);

        }catch(DBConnectionException dbEx) {
            throw new OperationException("\nErrore connessione DB!\n");
        }catch(DAOException ex) {
            throw new OperationException("\nOperazione DAO fallita\n");
        }catch(OperationException e) {
            throw new OperationException("\nRiscontrato problema interno applicazione [ca]!\n");
        }catch(ParseException pEx){
            throw new OperationException("\nErrore Time...\n");
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

    public void confermaVendita(){


        
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



    private void registrazioneInternaBiglietto(String data, String ora, int impiegato, boolean bagaglio)throws DBConnectionException, DAOException, OperationException, ParseException{
        try{
            Time t = new Time(new SimpleDateFormat("HH:mm").parse(ora).getTime());
            Date d = Date.valueOf(data);

            BigliettoDAO.updateBiglietto(d,t,impiegato,bagaglio);

        }catch(DBConnectionException dbEx) {
            throw new DBConnectionException("\nRiscontrato problema interno applicazione [ri]!\n");
        }catch(DAOException ex) {
            throw new DAOException("\nOps, qualcosa e' andato storto...\n");
        }catch(ParseException pEx){
            throw new OperationException("\nErrore Time...\n");
        }
    }

    private int invioMail(String id, String oraP, String oraA, String prezzo, int nS, int nB, String m){

        return 1;

    }

    private int setNumeroSediliDisponibili(int numSedili_, int numBagagli_, String idCorsaStringa_)throws OperationException{

        EntityAutobus ea = null;

        try{

            ea = AutobusDAO.readAutobus(Integer.parseInt(idCorsaStringa_));

            return AutobusDAO.updateAutobus(ea.getBagagliOccupati() + numBagagli_, ea.getSediliOccupati() + numSedili_ , idCorsaStringa_);

        }catch(DBConnectionException dbEx) {
            throw new OperationException("\nRiscontrato problema interno applicazione [ssd]!\n");
        }catch(DAOException ex) {
            throw new OperationException("\nOps, qualcosa e' andato storto...\n");
        }
    }

}
