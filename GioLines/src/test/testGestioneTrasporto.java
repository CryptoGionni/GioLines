package test;

import static org.junit.Assert.assertEquals;

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
	public void testCheckDimensioniBagaglio(){    /*Test Case: 7 */
        GestioneTrasporto gestioneTraspostoIstance = GestioneTrasporto.getInstance();
		boolean res = gestioneTraspostoIstance.checkDimensioniBagaglio(3,3,3,4,4,4);
        boolean expected = false;
        assertEquals("non va", expected, res);
    }
}
