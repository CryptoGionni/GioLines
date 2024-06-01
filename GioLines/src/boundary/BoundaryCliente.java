package boundary;

import java.sql.Time;
import java.sql.Date;
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

		while (!exit) {

			System.out.println("\n************* Cliente: *************");
			System.out.println("1. Acquista biglietto via web");
			System.out.println("2. Esci");

			String op = scan.nextLine();

			if (op.equals("1")) {
				acquistaBigliettoViaWeb();
			} else if (op.equals("2")) {
				exit = true;
				System.out.println("Uscita...");
			} else {
				System.out.println("Operazione non disponibile\n");
			}

		}

		System.out.println("Arrivederci!");

	}

	/*Test Case: 1 */
	public static void _acquistaBigliettoViaWeb(){
		acquistaBigliettoViaWeb();
	}
	
	/*Test Case: 2 */
	public static String inserisciCittàArrivo() {

		String città = "";
		boolean pass = true;

		while (pass) {

			System.out.println("Inserisci una città di arrivo:");
			città = scan.nextLine();

			if (città.matches(".*\\d.*")) {
				System.out.println("Città non valida!");
			} else {
				pass = false;
			}

		}
		return città.toUpperCase();
	}

	/*Test Case: 3 */
	public static int inserisciNumeroSedili() {

		boolean pass = true;
		int sed = 0;

		while (pass) {

			System.out.println("Inserisci il numero di posti da acquistare:");
			sed = Integer.parseInt(scan.nextLine());

			if (sed <= 0) {
				System.out.println("Numero di posti non valido!");
			} else {
				pass = false;
			}
		}
		return sed;
	}

	/*Test Case: 4-5 */
	public static int inserisciNumeroBagagli(int sed) {

		boolean pass = true;
		int bag = 0;

		while (pass) {

			System.out.println(
				"Inserisci il numero di bagagli da portare: \n(max 1 per persona, con supplemento di 5 euro/cad)");
			bag = Integer.parseInt(scan.nextLine());

			if (bag < 0){
				System.out.println("Numero di bagagli non valido, riprova...\n");
			}
			else if (bag <= sed && bag >= 0) {
				pass = false;
			} else {
				System.out.println("Numero di bagagli maggiore del numero di posti, riprova...\n");
			}
		}
		return bag;
	}

	/*Test Case: 6 */
	public static String[] inserisciDimensioneBagaglio() {

		boolean pass = true;
		String regex = "[x]";
		String tripla = "";

		while (pass) {


			System.out.println("Inserisci la dimensione del bagaglio (HxLxD):");
			tripla = scan.nextLine();		

			if (tripla.contains("x")){			
				if(tripla.substring(tripla.lastIndexOf("x")).contains("x")){
					pass = false;
				}else{
					System.out.println("Inserire le dimensioni nel formato giusto, riprova...\n");
				}
			} else {
				System.out.println("Inserire le dimensioni nel formato giusto, riprova...\n");
			}
		}

		String[] triplaArr = tripla.split(regex);
		return triplaArr;
	}

	
	private static void acquistaBigliettoViaWeb() {

		String CITTAPARTENZA = null, CITTAARRIVO = null, MAIL = null;
		Time ORARIOPARTENZA = null;
		Date DATAPARTENZA = null;
		int NUMEROSEDILI = 0, NUMEROBAGAGLI = 0;
		float PREZZOBIGLIETTIMASSIMO = 0;

		boolean inputValido = false;
		GestioneTrasporto gestioneTraspostoIstance = GestioneTrasporto.getInstance();
		ArrayList<String> propostaTrovata = null;
		ArrayList<Float> DIMENSIONEBAGAGLIO = new ArrayList<Float>();
		DIMENSIONEBAGAGLIO.add(0F);
		DIMENSIONEBAGAGLIO.add(0F);
		DIMENSIONEBAGAGLIO.add(0F);

		try {

			System.out.println("************* Inserisci parametri di ricerca *************");
			/* acquisizione input CITTAPARTENZA e CITTAARRIVO */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci una città di partenza:");
					CITTAPARTENZA = scan.nextLine().toUpperCase();

					// System.out.println("Inserisci una città di arrivo:");
					// CITTAARRIVO = scan.nextLine().toUpperCase();

					CITTAARRIVO = inserisciCittàArrivo();

					inputValido = true;
				} catch (InputMismatchException e) {
					System.out.println("Errore nell'acquisizione delle città, rirpova...\n");
				}
			}

			/* acquisizione input ORARIOPARTENZA e DATAPARTENZA */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci la data (aaaa-MM-gg)");
					String dataTemp = scan.nextLine();
					DATAPARTENZA = Date.valueOf(dataTemp);

					System.out.println("Inserisci l'orario di partenza (HH:mm):");
					String orarioTemp = scan.nextLine();
					ORARIOPARTENZA = new Time(new SimpleDateFormat("HH:mm").parse(orarioTemp).getTime());

					inputValido = true;
				} catch (IllegalArgumentException | ParseException | InputMismatchException iE) {
					System.out.println("Errore nell'acquisizione data e ora, riprova...\n");
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
						System.out.println("Email non valida, riprova...\n");
					}
				} catch (InputMismatchException e) {
					System.out.println("Errore nell'acquisizione della mail, rirpova...\n");
				}
			}

			/* acquisizione input NUMEROSEDILI */
			inputValido = false;
			while (!inputValido) {
				try {

					// System.out.println("Inserisci il numero di posti da acquistare:");
					// NUMEROSEDILI = Integer.parseInt(scan.nextLine());

					NUMEROSEDILI = inserisciNumeroSedili();

					inputValido = true;
				} catch (NumberFormatException nE) {
					System.out.println("Errore, inserire un numero valido...\n");
				}
			}

			/* acquisizione input NUMEROBAGAGLI */
			inputValido = false;
			while (!inputValido) {
				try {
					// System.out.println(
					// 		"Inserisci il numero di bagagli da portare \n(max 1 per persona, con supplemento di 5 euro/cad):");
					// NUMEROBAGAGLI = Integer.parseInt(scan.nextLine());

					// if (NUMEROBAGAGLI < NUMEROSEDILI) {
					// 	inputValido = true;
					// } else {
					// 	System.out.println("Numero di bagagli maggiore del numero di posti, riprova...\n");
					// }
					NUMEROBAGAGLI = inserisciNumeroBagagli(NUMEROSEDILI);
					
					inputValido = true;

				} catch (NumberFormatException nE) {
					System.out.println("Errore, inserire un numero valido...\n");
				}
			}

			/* acquisizione input DIMENSIONEBAGAGLIO */
			inputValido = false;
			while (!inputValido) {
				try {
					// System.out.println("Inserisci la dimensione del bagaglio (HxLxD):");
					// String tripla = scan.nextLine();
					// String regex = "[x]";
					// String[] triplaArr = tripla.split(regex);
					// for (int i = 0; i < 3; i++) {
					// 	DIMENSIONEBAGAGLIO.set(i, Float.parseFloat(triplaArr[i]));
					// }
					if(NUMEROBAGAGLI>0){
						String[] triplaArr = inserisciDimensioneBagaglio();
						for (int i = 0; i < 3; i++) {
							DIMENSIONEBAGAGLIO.set(i, Float.parseFloat(triplaArr[i]));
						}
					}
					
					inputValido = true;
				} catch (NumberFormatException nE) {
					System.out.println("Errore, inserire le dimensioni nel formato giusto...\n");
				}
			}

			/* acquisizione input PREZZOBIGLIETTIMASSIMO */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci un prezzo massimo per un singolo biglietto:");
					PREZZOBIGLIETTIMASSIMO = Float.parseFloat(scan.nextLine());

					inputValido = true;
				} catch (NumberFormatException nE) {
					System.out.println("Errore, inserire un numero valido...\n");
				}
			}



			propostaTrovata = gestioneTraspostoIstance.acquistaBigliettoViaWeb(
					CITTAPARTENZA,
					CITTAARRIVO,
					ORARIOPARTENZA,
					MAIL,
					PREZZOBIGLIETTIMASSIMO,
					NUMEROSEDILI,
					NUMEROBAGAGLI,
					DIMENSIONEBAGAGLIO);
					

			/* stampa della proposta trovata */
			System.out.println("\n************* Ecco l'autobus perfetto per te! *************" +
					"\nAutobus " + CITTAPARTENZA + "-" + CITTAARRIVO + " numero: " + propostaTrovata.get(0) +
					"\nPer il giorno: " + DATAPARTENZA +
					"\nOrario di partenza: " + propostaTrovata.get(1).substring(0, 5) +
					"\nOrario di arrivo: " + propostaTrovata.get(2).substring(0, 5) +
					"\nPosti: " + NUMEROSEDILI + " (" + propostaTrovata.get(4) + " euro/cad)" +
					"\nBagagli: " + NUMEROBAGAGLI + " (5 euro/cad)" +
					"\nAl prezzo di: " + propostaTrovata.get(3) + " euro ");

			System.out.println("\n************* Confermi? *************");
			System.out.println("Digita 's' per confermare l'acquisto all'indirizzo " + MAIL +
					"\noppure \nDigita qualunque altro carattere per annullare");
			String confermaProposta = scan.nextLine();

			if (!confermaProposta.equals("S") && !confermaProposta.equals("s")) {
				System.out.println("Acquisto annullato...\n");
				return;
			}

			inputValido = false;
			while (!inputValido) {
				System.out.println("\nInserisci il numero di carta:");

				String numeroCarta = scan.nextLine(); // "1234567812345678";

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
			System.out.println("Biglietti inviati sulla mail " + MAIL + "!");

			System.out.println("\n************* Acquisto completato! *************");
			System.out.println("Grazie per aver scelto GioLines!\n\n\n\n");

		} catch (OperationException oE) {
			System.out.println(oE.getMessage());
			System.out.println("Riprovare...\n");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Unexpected exception, riprovare...");
			System.out.println();
		}

	}

}
