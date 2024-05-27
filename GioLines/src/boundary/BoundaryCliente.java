package boundary;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import control.GestioneTrasporto;

import exception.OperationException;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class BoundaryCliente {
    

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {	

		boolean exit = false;
		
		while(!exit) {

			System.out.println("\n************* Cliente: *************\n");
			System.out.println("1. Acquista biglietto via web");
			System.out.println("2. Esci");
			
			String op = scan.nextLine();

			if(op.equals("1")) {
				acquistaBigliettoViaWeb();
			} else if(op.equals("2")){
				exit = true;
				System.out.println("Uscita...");
			}else{
				System.out.println("Operazione non disponibile\n");
			}

		}	
		
		System.out.println("Arrivederci!");
		
	}

    private static void acquistaBigliettoViaWeb() {

		String CITTAPARTENZA=null, CITTAARRIVO=null, MAIL=null;
		Time ORARIOPARTENZA = null;
		int NUMEROSEDILI=0, NUMEROBAGAGLI=0;
		float PREZZOBIGLIETTIMASSIMO=0, DIMENSIONEBAGAGLIO=0;

    	boolean inputValido = false;
		GestioneTrasporto gestioneTraspostoIstance = GestioneTrasporto.getInstance();
		ArrayList<String> propostaTrovata = null;

		try {
			
			System.out.println("\n************* Inserisci parametri di ricerca *************");
			// /* acquisizione input CITTAPARTENZA e CITTAARRIVO */
			// inputValido = false;
			// while (!inputValido) {
			// 	try {
			// 		System.out.println("Inserisci una città di partenza:");
			// 		CITTAPARTENZA = scan.nextLine();

			// 		System.out.println("Inserisci una città di arrivo:");
			// 		CITTAARRIVO = scan.nextLine();

			// 		inputValido = true;
			// 	}catch(InputMismatchException e){
			// 		System.out.println("Errore nell'acquisizione delle città, rirpova...\n");
			// 	}
			// }

			/* acquisizione input ORARIOPARTENZA */
			// inputValido = false;
			// while (!inputValido) {
			// 	try {
			// 		System.out.println("Inserisci l'orario di partenza (HH:mm):");
			// 		String orarioTemp = scan.nextLine();
			// 		ORARIOPARTENZA = new Time(new SimpleDateFormat("HH:mm").parse(orarioTemp).getTime());

			// 		inputValido = true;
			// 	} catch (IllegalArgumentException | ParseException | InputMismatchException iE) {
			// 		System.out.println("Errore nell'acquisizione dell'orario, riprovare...\n");
			// 	}
			// }

			// /* acquisizione input MAIL */
			// inputValido = false;
			// while (!inputValido) {
			// 	try {
			// 		System.out.println("Inserisci l'indirizzo mail:");
			// 		MAIL = scan.nextLine();
			// 		if (MAIL.contains("@") && MAIL.contains(".")) {
			// 			inputValido = true;
			// 		} else {
			// 			System.out.println("Email non valida..");
			// 		}
			// 	}catch(InputMismatchException e){
			// 		System.out.println("Errore nell'acquisizione della mail, rirpova...\n");
			// 	}
			// }

			// /* acquisizione input NUMEROSEDILI e NUMEROBAGAGLI */
			// inputValido = false;
			// while (!inputValido) {
			// 	try {
			// 		System.out.println("Inserisci il numero di posti da acquistare:");
			// 		NUMEROSEDILI = Integer.parseInt(scan.nextLine());

			// 		System.out.println("Inserisci il numero di bagagli da portare:");
			// 		NUMEROBAGAGLI = Integer.parseInt(scan.nextLine());

			// 		inputValido = true;
			// 	} catch (NumberFormatException nE) {
			// 		System.out.println("Errore, inserire un numero valido...\n");
			// 	}
			// }

			
			// /* acquisizione input PREZZOBIGLIETTIMASSIMO e DIMENSIONEBAGAGLIO */
			// inputValido = false;
			// while (!inputValido) {
			// 	try {
			// 		System.out.println("Inserisci un prezzo dei biglietti massimo:");
			// 		PREZZOBIGLIETTIMASSIMO = Float.parseFloat(scan.nextLine());

			// 		System.out.println("Inserisci la dimensione del bagaglio:");
			// 		DIMENSIONEBAGAGLIO = Float.parseFloat(scan.nextLine());

			// 		inputValido = true;
			// 	} catch (NumberFormatException nE) {
			// 		System.out.println("Errore, inserire un numero valido...\n");
			// 	}
			// }
    
			// propostaTrovata = gestioneTraspostoIstance.acquistaBigliettoViaWeb(
			// 	CITTAPARTENZA, 
			// 	CITTAARRIVO,
			// 	ORARIOPARTENZA,
			// 	MAIL,
			// 	PREZZOBIGLIETTIMASSIMO,
			// 	NUMEROSEDILI,
			// 	NUMEROBAGAGLI,
			// 	DIMENSIONEBAGAGLIO
			// 	);

			/*FUNZIONE DI TEST: comunicazione tra control e boundary*/
			ORARIOPARTENZA = new Time(new SimpleDateFormat("HH:mm").parse("11:22").getTime());
			propostaTrovata = gestioneTraspostoIstance.acquistaBigliettoViaWeb(
				"Napoli", 
				"Roma",
				ORARIOPARTENZA,
				"m@m.m",
				55,
				1,
				0,
				2
				);

			/* stampa della proposta trovata */
			System.out.println("\n************* Ecco l'autobus perfetto per te! *************" +
								"\nAutobus " + CITTAPARTENZA + "-" + CITTAARRIVO + " numero: " + propostaTrovata.get(0) + 
								"\nOrario di partenza: " + propostaTrovata.get(1) +
								"\nOrario di arrivo: " + propostaTrovata.get(2) +
								"\nPosti: " + NUMEROSEDILI + "\nBagagli: " + NUMEROBAGAGLI +
								"\nAl prezzo di: " + propostaTrovata.get(3) + "€"
								);

			System.out.println("\n************* Confermi? *************");
			System.out.println("Digita 'S' per confermare l'acquisto all'indirizzo " + MAIL + 
								"\noppure \nDigita qualunque altro carattere per annullare");
			String confermaProposta = scan.nextLine();

			if (!confermaProposta.equals("S") && !confermaProposta.equals("s")) {
				System.out.println("Acquisto annullato...\n");
				return;
			}

			inputValido = false;
			while (!inputValido) {
				System.out.println("\nInserire il numero di carta:");

				String numeroCarta = scan.nextLine();

				try {
					Long.parseLong(numeroCarta);

					if (numeroCarta.length() == 16) {
						inputValido = true;
					} else {
						System.out.println("Errore inserimento carta, deve essere di 16 cifre..");
					}
				} catch (NumberFormatException e) {
					System.out.println("Errore inserimento carta, deve contenere solo numeri..");
				}
			}


			System.out.println("Pagamento in corso...");
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Pagamento effettuato!");

			gestioneTraspostoIstance.confermaAcquisto(propostaTrovata, NUMEROSEDILI, NUMEROBAGAGLI, MAIL);
			
			System.out.println("\nInvio biglietti in corso...");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Biglietti inviati sulla mail " + MAIL);

			System.out.println("\n************* Acquisto completato! *************");
			System.out.println();
			System.out.println();

		} catch (OperationException oE) {
			System.out.println(oE.getMessage());
			System.out.println("Riprovare..\n");
		} catch (Exception e) {
			System.out.println("Unexpected exception, riprovare..");
			System.out.println();
		}


	}

}
