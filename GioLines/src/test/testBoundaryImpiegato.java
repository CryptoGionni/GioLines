package test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import boundary.BoundaryImpiegato;

public class testBoundaryImpiegato {
    
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
	public void testInserisciNumeroBagagli(){    /*Test Case: 4-5 */
		int res = BoundaryImpiegato.inserisciNumeroBagagli(3);
        int expected = 1;
        assertEquals(expected, res);
    }

    @Test
	public void testInserisciDimensioneBagaglio(){    /*Test Case: 6 */
		String[] res = BoundaryImpiegato.inserisciDimensioneBagaglio();
        String[] expected = {"3","3","3"};
        assertSame(expected, res);
    }

	
    @Test
	public void testInserisciPrezzoBigliettoMassimo(){    /*Test Case: 8 */
		float res = BoundaryImpiegato.inserisciPrezzoBigliettoMassimo();
		float expected = 1F;
		assertSame(expected, res);

	}

}
