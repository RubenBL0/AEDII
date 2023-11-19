package Ejercicios.Arboles.ArbolGeneral;

import TADs.Arboles.ArbolGeneral.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruben Blanco
 */
public class ActividadArbolGeneralTest {
    
    ArbolGeneral<Integer> arbol1 =new EnlazadoArbolGeneral<>(4, new EnlazadoArbolGeneral<>(2));
    ArbolGeneral<Integer> arbol2 =new EnlazadoArbolGeneral<>(3, new EnlazadoArbolGeneral<>(1));
    ArbolGeneral<Integer> arbol3 =new EnlazadoArbolGeneral<>(6, arbol1, arbol2, new EnlazadoArbolGeneral<>(5));
    ArbolGeneral<Integer> arbol4 =new EnlazadoArbolGeneral<>(7, arbol3);
    
    ArbolGeneral<Integer> arbolDos =new EnlazadoArbolGeneral<>(1, new EnlazadoArbolGeneral<>(2), new EnlazadoArbolGeneral<>(1));
    ArbolGeneral<Integer> arbolTres =new EnlazadoArbolGeneral<>(1, arbolDos, new EnlazadoArbolGeneral<>(3), new EnlazadoArbolGeneral<>(5));
    ArbolGeneral<Integer> arbol =new EnlazadoArbolGeneral<>(4,new EnlazadoArbolGeneral<>(3), new EnlazadoArbolGeneral<>(3), new EnlazadoArbolGeneral<>(5), new EnlazadoArbolGeneral<>(6));
    ArbolGeneral<Integer> arbolCuatro =new EnlazadoArbolGeneral<>(4, arbol, new EnlazadoArbolGeneral<>(3), new EnlazadoArbolGeneral<>(5), new EnlazadoArbolGeneral<>(6));
    
    ArbolGeneral<Integer> no23 = new EnlazadoArbolGeneral<>(1, new EnlazadoArbolGeneral<>(2, new EnlazadoArbolGeneral<>(3), new EnlazadoArbolGeneral<>(4)));

    /**
     * Test of sumaNodos method, of class ActividadArbolGeneral.
     */
    @Test
    public void testGetSumaFalse() {
        System.out.println("Test 'sumaNodos' de 'arbol4' con resultado 0");
        int expResult = 0;
        int result = ActividadArbolGeneral.getSuma(arbol4);
        assertNotEquals(expResult, result);
    }
    @Test
    public void testGetSumaTrue() {
        System.out.println("Test 'sumaNodos' de 'arbol4' con resultado 28");
        int expResult = 28;
        int result = ActividadArbolGeneral.getSuma(arbol4);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of igualEstructura method, of class ActividadArbolGeneral.
     */
    @Test
    public void testEsIgualEstructuraTrue() {
        System.out.println("Test 'igualEstructura' de 'arbol1' y 'arbol2'");
        boolean result = ActividadArbolGeneral.esIgualEstructura(arbol1, arbol2);
        assertTrue(result);
    }
    @Test
    public void testIgualEstructuraFalse() {
        System.out.println("Test 'igualEstructura' de 'arbol1' y 'arbol4'");
        boolean result = ActividadArbolGeneral.esIgualEstructura(arbol1, arbol4);
        assertFalse(result);
    }
    
    /**
     * Test of arbolDosTres method, of class ActividadArbolGeneral.
     */
    @Test
    public void testArbolDosTresTrue() {
        System.out.println("Test 'arbolDosTres' de 'arbolTres'");
        boolean result = ActividadArbolGeneral.esArbolDosTres(arbolTres);
        assertTrue(result);
    }
    @Test
    public void testArbolDosTresFalse() {
        System.out.println("Test 'arbolDosTres' de 'arbol4'");
        boolean result = ActividadArbolGeneral.esArbolDosTres(arbol4);
        assertFalse(result);
    }
    @Test
    public void testArbolDosTresFalse2() {
        System.out.println("Test 'arbolDosTres' de 'no23'");
        boolean result = ActividadArbolGeneral.esArbolDosTres(no23);
        assertFalse(result);
    }
    
    /**
     * Test of esSeleccion method, of class ActividadArbolGeneral.
     */
    @Test
    public void testEsSeleccionTrue() {
        System.out.println("Test 'esSeleccion' de 'arbolTres'");
        boolean result = ActividadArbolGeneral.esSeleccion(arbolTres);
        assertTrue(result);
    }
    @Test
    public void testEsSeleccionFalse() {
        System.out.println("Test 'esSeleccion' de 'arbol4'");
        boolean result = ActividadArbolGeneral.esSeleccion(arbol4);
        assertFalse(result);
    }
    @Test
    public void testEsSeleccionFalse2() {
        System.out.println("Test 'esSeleccion' de 'no23'");
        boolean result = ActividadArbolGeneral.esSeleccion(no23);
        assertFalse(result);
    }
}
