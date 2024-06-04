package test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import boundary.BoundaryCliente;

public class testBoundaryCliente {
    
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

	/*Test Case: 1 */
	@Test
	public void testAcquistaBigliettoViaWeb_senzaBagaglio() {    
        BoundaryCliente._acquistaBigliettoViaWeb();
    }

	/************* *************/

    /*Test Case: 2 */	
	@Test
	public void tesCheckCittàArrivo(){
		String CITTAARRIVO = "roma";
		boolean res = BoundaryCliente.checkCittàArrivo(CITTAARRIVO);
		boolean expected = false;
		assertEquals(expected, res);
	}

	@Test
	public void testInserisciCittàArrivo() {    
        String res = BoundaryCliente.inserisciCittàArrivo();
        String expected = "ROMA";
        assertEquals(expected, res);
    }

    /************* *************/

	/*Test Case: 3 */
	@Test
	public void testCheckNumeroSedili(){
		int NUMEROSEDILI = 2;
		boolean res = BoundaryCliente.checkNumeroSedili(NUMEROSEDILI);
		boolean expected = false;
		assertEquals(expected, res);
	}

	@Test
	public void testInserisciNumeroSedili(){    
		int res = BoundaryCliente.inserisciNumeroSedili();
        int expected = 1;
        assertEquals(expected, res);

    }
	
	/************* *************/

    
}
