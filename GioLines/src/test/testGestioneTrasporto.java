package test;

import static org.junit.Assert.assertEquals;

/*(extra-debugging) */
/*
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
*/

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import control.GestioneTrasporto;

public class testGestioneTrasporto {
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Test Start!\n");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Test Finished!\n");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*Test Case: 7 */
    @Test
	public void testCheckDimensioniBagaglio(){    
		GestioneTrasporto gestioneTraspostoIstance = GestioneTrasporto.getInstance();//singleton
		
		float DIMENSIONEBAGAGLIO_H = 3F;
		float DIMENSIONEBAGAGLIO_L = 3F;
		float DIMENSIONEBAGAGLIO_D = 3F;

		float DIMENSIONEBAGAGLIO_H_DB = 4F;
		float DIMENSIONEBAGAGLIO_L_DB = 4F;
		float DIMENSIONEBAGAGLIO_D_DB = 4F;

		boolean res = gestioneTraspostoIstance.checkDimensioniBagaglio(
			DIMENSIONEBAGAGLIO_H,
			DIMENSIONEBAGAGLIO_L,
			DIMENSIONEBAGAGLIO_D,
			DIMENSIONEBAGAGLIO_H_DB,
			DIMENSIONEBAGAGLIO_L_DB,
			DIMENSIONEBAGAGLIO_D_DB		
			);

        boolean expected = false;
        assertEquals(expected, res);
    }

	/*Test Case: A (extra-debugging: esegue il control, non fa quindi controlli sulla correttezza degli input) */
	/*
	@Test
	public void testAcquistaBigliettoViaWeb() {    
		GestioneTrasporto gestioneTraspostoIstance = GestioneTrasporto.getInstance();//singleton

		try{

			ArrayList<String> propostaTrovata = null;
			ArrayList<Float> DIMENSIONEBAGAGLIO = new ArrayList<Float>();
			DIMENSIONEBAGAGLIO.add(3F);
			DIMENSIONEBAGAGLIO.add(3F);
			DIMENSIONEBAGAGLIO.add(3F);
			Time ORARIOPARTENZA = new Time(new SimpleDateFormat("HH:mm").parse("11:22").getTime());

			propostaTrovata = gestioneTraspostoIstance.acquistaBigliettoViaWeb(
				"NAPOLI",
				"ROMA",
				ORARIOPARTENZA,
				"g.bolla@studenti.unina.it",
				1,
				2,
				0,
				DIMENSIONEBAGAGLIO);    

					
			String expected = "1";
			assertEquals(expected, propostaTrovata.get(0));

		} catch (Exception oE) {
			System.out.println(oE.getMessage());
			System.out.println("Riprovare...\n");
		}



	}
	*/
}
