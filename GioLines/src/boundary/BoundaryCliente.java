package boundary;

import java.util.Scanner;
import control.GestioneTrasporto;

public class BoundaryCliente {
    

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {	

		boolean exit = false;
		
		while(!exit) {

			System.out.println("Cliente:");
			System.out.println("1. Acquista biglietto via web");
			System.out.println("2. Esci");
			
			String op = scan.nextLine();

			if(op.equals("1")) {
				acquistaBigliettoViaWeb();
			} else if(op.equals("2")){
				exit = true;
			}else{
				System.out.println("Operazione non disponibile\n");
			}

		}	
		
		System.out.println("Arrivederci!");
		
	}

    private static void acquistaBigliettoViaWeb() {
    
    
    
    }

}
