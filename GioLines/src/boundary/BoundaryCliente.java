package boundary;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import control.GestioneTrasporto;

import exception.OperationException;
import java.util.InputMismatchException;

public class BoundaryCliente {
    

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {	

		boolean exit = false;
		
		while(!exit) {

			System.out.println("Cliente:\n");
			System.out.println("1. Acquista biglietto via web");
			System.out.println("2. Esci");
			
			String op = scan.nextLine();

			if(op.equals("1")) {
				acquistaBigliettoViaWeb();
			} else if(op.equals("2")){
				exit = true;
				System.out.println("Uscita!");
			}else{
				System.out.println("Operazione non disponibile\n");
			}

		}	
		
		System.out.println("Arrivederci!");
		
	}

    private static void acquistaBigliettoViaWeb() {
		/*
		String CITTAPARTENZA,
        String CITTAARRIVO,
        Date ORARIOPARTENZA,
        String MAIL,
        float PREZZOBIGLIETTIMASSIMO,
        int NUMEROSEDILI,
        int NUMEROBAGAGLI,
        float DIMENSIONEBAGAGLIO
		 */
		String CITTAPARTENZA=null, CITTAARRIVO=null, MAIL=null;
		Time ORARIOPARTENZA = null;
		int NUMEROSEDILI=0, NUMEROBAGAGLI=0;
		float PREZZOBIGLIETTIMASSIMO=0, DIMENSIONEBAGAGLIO=0;

    	boolean inputValido = false;
		GestioneTrasporto gestioneTraspostoIstance = GestioneTrasporto.getInstance();


		try {

			/* acquisizione input CITTAPARTENZA e CITTAARRIVO */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci una città di partenza:");
					CITTAPARTENZA = scan.nextLine();

					System.out.println("Inserisci una città di arrivo:");
					CITTAARRIVO = scan.nextLine();

					inputValido = true;
				}catch(InputMismatchException e){
					System.out.println("Errore nell'acquisizione delle città, rirpova...\n");
				}
			}

			/* acquisizione input ORARIOPARTENZA */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci l'orario di partenza (HH:mm):");
					String orarioTemp = scan.nextLine();
					ORARIOPARTENZA = new Time(new SimpleDateFormat("HH:mm").parse(orarioTemp).getTime());

					inputValido = true;
				} catch (IllegalArgumentException | ParseException | InputMismatchException iE) {
					System.out.println("Errore nell'acquisizione dell'orario, riprovare...\n");
				}
			}

			/* acquisizione input MAIL */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci l'indirizzo mail:");
					MAIL = scan.nextLine();
					if (MAIL.contains("@") && MAIL.contains(".")) {
						inputValido = true;
					} else {
						System.out.println("Email non valida..");
					}
				}catch(InputMismatchException e){
					System.out.println("Errore nell'acquisizione della mail, rirpova...\n");
				}
			}

			/* acquisizione input NUMEROSEDILI e NUMEROBAGAGLI */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci il numero di posti da acquistare:");
					NUMEROSEDILI = Integer.parseInt(scan.nextLine());

					System.out.println("Inserisci il numero di bagagli da portare:");
					NUMEROBAGAGLI = Integer.parseInt(scan.nextLine());

					inputValido = true;
				} catch (NumberFormatException nE) {
					System.out.println("Errore, inserire un numero valido...\n");
				}
			}

			
			/* acquisizione input PREZZOBIGLIETTIMASSIMO e DIMENSIONEBAGAGLIO */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci un prezzo dei biglietti massimo:");
					PREZZOBIGLIETTIMASSIMO = Float.parseFloat(scan.nextLine());

					System.out.println("Inserisci la dimensione del bagaglio:");
					DIMENSIONEBAGAGLIO = Float.parseFloat(scan.nextLine());

					inputValido = true;
				} catch (NumberFormatException nE) {
					System.out.println("Errore, inserire un numero valido...\n");
				}
			}
    
			gestioneTraspostoIstance.acquistaBigliettoViaWeb(
				CITTAPARTENZA, 
				CITTAARRIVO,
				ORARIOPARTENZA,
				MAIL,
				PREZZOBIGLIETTIMASSIMO,
				NUMEROSEDILI,
				NUMEROBAGAGLI,
				DIMENSIONEBAGAGLIO
				);



		} catch (OperationException oE) {
			System.out.println(oE.getMessage());
			System.out.println("Riprovare..\n");
		} catch (Exception e) {
			System.out.println("Unexpected exception, riprovare..");
			System.out.println();
		}


	}

}
