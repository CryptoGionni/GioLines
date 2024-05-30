package boundary;

import java.sql.Time;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;

import control.GestioneTrasporto;
import exception.OperationException;

public class BoundaryImpiegato {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		boolean exit = false;

		while (!exit) {

			System.out.println("\n************* Impiegato: *************");
			System.out.println("1. Vendi biglietto");
			System.out.println("2. Esci");

			String op = scan.nextLine();

			if (op.equals("1")) {
				vendiBiglietto();
			} else if (op.equals("2")) {
				exit = true;
				System.out.println("Uscita...");
			} else {
				System.out.println("Operazione non disponibile\n");
			}

		}

		System.out.println("Arrivederci!");

	}

	private static void vendiBiglietto() {

		String CITTAPARTENZA = null, CITTAARRIVO = null;
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
					;

					System.out.println("Inserisci una città di arrivo:");
					CITTAARRIVO = scan.nextLine().toUpperCase();
					;

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
					System.out.println("Errore nell'acquisizione data e ora, riprovare...\n");
				}
			}

			/* acquisizione input NUMEROSEDILI */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci il numero di posti da acquistare:");
					NUMEROSEDILI = Integer.parseInt(scan.nextLine());

					inputValido = true;
				} catch (NumberFormatException nE) {
					System.out.println("Errore, inserire un numero valido...\n");
				}
			}

			/* acquisizione input NUMEROBAGAGLI */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println(
							"Inserisci il numero di bagagli da portare \n(max 1 per persona, con supplemento di 5 euro/cad):");
					NUMEROBAGAGLI = Integer.parseInt(scan.nextLine());

					if (NUMEROBAGAGLI < NUMEROSEDILI) {
						inputValido = true;
					} else {
						System.out.println("Numero di bagagli maggiore del numero di posti, riprova...\n");
					}
				} catch (NumberFormatException nE) {
					System.out.println("Errore, inserire un numero valido...\n");
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

			/* acquisizione input DIMENSIONEBAGAGLIO */
			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci la dimensione del bagaglio (HxLxD):");
					String tripla = scan.nextLine();
					String regex = "[x]";
					String[] triplaArr = tripla.split(regex);
					for (int i = 0; i < 3; i++) {
						DIMENSIONEBAGAGLIO.set(i, Float.parseFloat(triplaArr[i]));
					}

					inputValido = true;
				} catch (NumberFormatException nE) {
					System.out.println("Errore, inserire le dimensioni nel formato giusto...\n");
				}
			}

			propostaTrovata = gestioneTraspostoIstance.vendiBiglietto(
					CITTAPARTENZA,
					CITTAARRIVO,
					ORARIOPARTENZA,
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
					"\nPosti: " + NUMEROSEDILI + " (" + propostaTrovata.get(4) + "euro/cad)" +
					"\nBagagli: " + NUMEROBAGAGLI + " (5 euro/cad)" +
					"\nAl prezzo di: " + propostaTrovata.get(3) + " euro ");

			System.out.println("\n************* Confermi? *************");
			System.out.println("Digita 's' per confermare la vendita" +
					"\noppure \nDigita qualunque altro carattere per annullare");
			String confermaProposta = scan.nextLine();

			if (!confermaProposta.equals("S") && !confermaProposta.equals("s")) {
				System.out.println("Vendita annullata...\n");
				return;
			}

			System.out.println("Pagamento in corso...");
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Pagamento effettuato!");

			gestioneTraspostoIstance.confermaVendita(propostaTrovata, NUMEROSEDILI, NUMEROBAGAGLI);

			System.out.println("\nStampa biglietti in corso...");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Biglietti stampati!");

			System.out.println("\n************* Vendita completata! *************");
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
