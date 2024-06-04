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


	@Test
	public void testAcquistaBigliettoViaWeb_senzaBagaglio() {    /*Test Case: 1 */
        BoundaryCliente._acquistaBigliettoViaWeb();
    }

    
	@Test
	public void testInserisciCittàArrivo() {    /*Test Case: 2 */
        String res = BoundaryCliente.inserisciCittàArrivo();
        String expected = "ROMA";
        assertEquals(expected, res);
    }
    
	@Test
	public void testInserisciNumeroSedili(){    /*Test Case: 3 */
		int res = BoundaryCliente.inserisciNumeroSedili();
        int expected = 1;
        assertEquals(expected, res);

    }
    
}
