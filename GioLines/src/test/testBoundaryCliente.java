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
	public void testAcquistaBigliettoViaWeb() {    /*Test Case: 1 */
        BoundaryCliente._acquistaBigliettoViaWeb();
    }

    
	@Test
	public void testInserisciCittàArrivo() {    /*Test Case: 2 */
        
        // BoundaryCliente c = new BoundaryCliente.BoundaryCliente(); //così è non-static
		String res = BoundaryCliente.inserisciCittàArrivo();
        String expected = "ROMA";
        assertEquals("non va", expected, res);
    }
    
	@Test
	public void testInserisciNumeroSedili(){    /*Test Case: 3 */
		int res = BoundaryCliente.inserisciNumeroSedili();
        int expected = 1;
        assertEquals("non va", expected, res);

    }
    
	@Test
	public void testInserisciNumeroBagagli(){    /*Test Case: 4-5 */
		int res = BoundaryCliente.inserisciNumeroBagagli(3);
        int expected = 1;
        assertEquals("non va", expected, res);
    }

    @Test
	public void testInserisciDimensioneBagaglio(){    /*Test Case: 6 */
		String[] res = BoundaryCliente.inserisciDimensioneBagaglio();
        String[] expected = {"3","3","3"};
        assertSame("non va", expected, res);
    }

}
