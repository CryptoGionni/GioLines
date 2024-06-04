package test;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import control.GestioneTrasporto;

public class testGestioneTrasporto {
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAcquistaBigliettoViaWeb() {    /*Test Case: 0 */
		GestioneTrasporto gestioneTraspostoIstance = GestioneTrasporto.getInstance();//singleton

		try{

			ArrayList<String> propostaTrovata = null;
			ArrayList<Float> DIMENSIONEBAGAGLIO = new ArrayList<Float>();
			DIMENSIONEBAGAGLIO.add(12F);
			DIMENSIONEBAGAGLIO.add(18F);
			DIMENSIONEBAGAGLIO.add(10F);
			Time ORARIOPARTENZA = new Time(new SimpleDateFormat("HH:mm").parse("11:22").getTime());

			propostaTrovata = gestioneTraspostoIstance.acquistaBigliettoViaWeb(
				"napoli",
				"roma",
				ORARIOPARTENZA,
				"g.bolla@studenti.unina.it",
				77,
				2,
				2,
				DIMENSIONEBAGAGLIO);    

					
			String expected = "1";
			assertEquals(expected, propostaTrovata.get(0));

		} catch (Exception oE) {
			System.out.println(oE.getMessage());
			System.out.println("Riprovare...\n");
		}



	}

	@Test
	public void testAcquistaBigliettoViaWeb_senzaBagaglio() {    /*Test Case: 1 */
		GestioneTrasporto gestioneTraspostoIstance = GestioneTrasporto.getInstance();//singleton

		try{

			ArrayList<String> propostaTrovata = null;
			ArrayList<Float> DIMENSIONEBAGAGLIO = new ArrayList<Float>();
			DIMENSIONEBAGAGLIO.add(3F);
			DIMENSIONEBAGAGLIO.add(3F);
			DIMENSIONEBAGAGLIO.add(3F);
			Time ORARIOPARTENZA = new Time(new SimpleDateFormat("HH:mm").parse("11:22").getTime());

			propostaTrovata = gestioneTraspostoIstance.acquistaBigliettoViaWeb(
				"napoli",
				"roma",
				ORARIOPARTENZA,
				"g.bolla@studenti.unina.it",
				77,
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

    @Test
	public void testCheckDimensioniBagaglio(){    /*Test Case: 7 */
		GestioneTrasporto gestioneTraspostoIstance = GestioneTrasporto.getInstance();//singleton

		boolean res = gestioneTraspostoIstance.checkDimensioniBagaglio(3,3,3,4,4,4);
        boolean expected = false;
        assertEquals(expected, res);
    }


}
