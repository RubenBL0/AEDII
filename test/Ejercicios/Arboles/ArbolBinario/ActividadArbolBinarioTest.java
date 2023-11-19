package Ejercicios.Arboles.ArbolBinario;

import TADs.Arboles.ArbolBinario.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruben Blanco
 */
public class ActividadArbolBinarioTest {
    
    public ActividadArbolBinarioTest() {
    }
    
        private static final ArbolBinario<Integer> vacio = new EnlazadoArbolBinario<>();
	private static final ArbolBinario<Integer> hoja1 = new EnlazadoArbolBinario<>(3, vacio, vacio);
	private static final ArbolBinario<Integer> hoja11 = new EnlazadoArbolBinario<>(3, vacio, vacio);
	private static final ArbolBinario<Integer> hoja2 = new EnlazadoArbolBinario<>(5, vacio, vacio);
	private static final ArbolBinario<Integer> hoja3 = new EnlazadoArbolBinario<>(7, vacio, vacio);
	private static final ArbolBinario<Integer> nodo1 = new EnlazadoArbolBinario<>(11, hoja1, hoja2); 
	private static final ArbolBinario<Integer> nodo2 = new EnlazadoArbolBinario<>(3, hoja1, hoja2);
	private static final ArbolBinario<Integer> raiz = new EnlazadoArbolBinario<>(7, nodo1, hoja3);
	private static final ArbolBinario<Integer> selec = new EnlazadoArbolBinario<>(3, nodo2, hoja3);
	private static final ArbolBinario<Integer> degen = new EnlazadoArbolBinario<>(1, new EnlazadoArbolBinario<>(13, vacio, hoja3), vacio);
	private static final ArbolBinario<Integer> nodoSinHojas = new EnlazadoArbolBinario<>(11, vacio, vacio);
	private static final ArbolBinario<Integer> raizSinHojas = new EnlazadoArbolBinario<>(7, nodoSinHojas, vacio);
	private static final ArbolBinario<Integer> raizHasta1 = new EnlazadoArbolBinario<>(7, nodoSinHojas, hoja3);
	
	private static final ArbolBinario<Character> vacioChar = new EnlazadoArbolBinario<>();
	private static final ArbolBinario<Character> hoja2Char = new EnlazadoArbolBinario<>('5', vacioChar, vacioChar);
	private static final ArbolBinario<Character> hoja1Char = new EnlazadoArbolBinario<>('3', vacioChar, vacioChar);
	private static final ArbolBinario<Character> nodo1Char = new EnlazadoArbolBinario<>('6', hoja1Char, hoja2Char); 
	
        private static final ArbolBinario<Character> hoja4Char = new EnlazadoArbolBinario<>('1', vacioChar, vacioChar);
        private static final ArbolBinario<Character> hoja5Char = new EnlazadoArbolBinario<>('8', vacioChar, vacioChar);
	private static final ArbolBinario<Character> nodo2Char = new EnlazadoArbolBinario<>('2', hoja4Char, hoja5Char);
        private static final ArbolBinario<Character> arbolCamino = new EnlazadoArbolBinario<>('7', nodo1Char, nodo2Char);
	
        private static final Integer[]elementos = {0,3}; 
        private static final Integer[]elementos2 = {0,11,3,5}; 
        private static final Integer[]elementos3 = {0,7,11,7,3,5};
    
    /**
     * Ejercicio 1
     * Test of completo method, of class ActividadArbolBinario.
     */
    @Test
    public void testVacioCompleto() {
        System.out.println("Test 'completo' con arbol vacío");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.completo(vacio);
        assertEquals(expResult, result);
    }
    @Test
    public void testHojaCompleto() {
        System.out.println("Test 'completo' con arbol 'hoja3'");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.completo(hoja3);
        assertEquals(expResult, result);
    }
    @Test
    public void testNoCompleto() {
        System.out.println("Test 'completo' con arbol 'degen'");
        boolean expResult = false;
        boolean result = ActividadArbolBinario.completo(degen);
        assertEquals(expResult, result);
    }
    @Test
    public void testCompleto() {
        System.out.println("Test 'completo' con arbol 'raiz'");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.completo(raiz);
        assertEquals(expResult, result);
    }
    
    /**
     * Ejercicio 2
     * Test of identicos method, of class ActividadArbolBinario.
     */
    @Test
    public void testVacioIdenticos() {
        System.out.println("Test 'identicos' con árboles vacios");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.identicos(vacio, new EnlazadoArbolBinario<>() );
        assertEquals(expResult, result);
    }
    @Test
    public void testHojaIdenticos() {
        System.out.println("Test 'identicos' con árboles 'hoja1' y 'hoja11'");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.identicos(hoja1, hoja11);
        assertEquals(expResult, result);
    }
    @Test
    public void testNoIdenticos() {
        System.out.println("Test 'identicos' con árboles 'raiz' y 'selec'");
        boolean expResult = false;
        boolean result = ActividadArbolBinario.identicos(raiz, selec);
        assertEquals(expResult, result);
    }
    @Test
    public void testIdenticos() {
        System.out.println("Test 'identicos' con el mismo árbol");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.identicos(raiz, raiz);
        assertEquals(expResult, result);
    }
    
    /**
     * Ejercicio 3
     * Test of contarNivel method, of class ActividadArbolBinario.
     */
    @Test
    public void testVacioContarNivel() {
        System.out.println("Test 'contarNivel' con árbol vacío");
        int expResult = 0;
        int result = ActividadArbolBinario.contarNivel(vacio, 0);
        assertEquals(expResult, result);
    }
    @Test
    public void testNoContarNivel() {
        System.out.println("Test 'contarNivel' con árbol 'hoja1'");
        int expResult = 0;
        int result = ActividadArbolBinario.contarNivel(hoja1, 2);
        assertEquals(expResult, result);
    }
    @Test
    public void testContarNivel() {
        System.out.println("Test 'contarNivel' con árbol 'raiz'");
        int expResult = 2;
        int result = ActividadArbolBinario.contarNivel(raiz, 2);
        assertEquals(expResult, result);
    }
    
    /**
     * Ejercicio 4   
     * Test of eliminarHojas method, of class ActividadArbolBinario.
     */
    @Test
    public void testVacioEliminarHojas() {
        System.out.println("Test 'eliminarHojas' con árbol 'hoja1'");
        ArbolBinario expResult = vacio;
        ArbolBinario result = ActividadArbolBinario.eliminarHojas(hoja1);
        boolean eq = ActividadArbolBinario.identicos(expResult, result);
        assertTrue(eq);
    }
    @Test
    public void testEliminarHojas() {
        System.out.println("Test 'eliminarHojas' con árbol 'raiz'");
        ArbolBinario expResult = raizSinHojas;
        ArbolBinario result = ActividadArbolBinario.eliminarHojas(raiz);
        boolean eq = ActividadArbolBinario.identicos(expResult, result);
        assertTrue(eq);
    }
    
    /**
     * Ejercicio 5
     * Test of altura method, of class ActividadArbolBinario.
     */
    @Test
    public void testAlturaVacio() {
        System.out.println("Test 'altura' con árbol vacío");
        int expResult = -1;
        int result = ActividadArbolBinario.altura(vacio);
        assertEquals(expResult, result);
    }
    @Test
    public void testAlturaHoja() {
        System.out.println("Test 'altura' con árbol 'hoja1'");
        int expResult = 0;
        int result = ActividadArbolBinario.altura(hoja1);
        assertEquals(expResult, result);
    }
    @Test
    public void testAltura() {
        System.out.println("Test 'altura' con árbol 'raiz'");
        int expResult = 2;
        int result = ActividadArbolBinario.altura(raiz);
        assertEquals(expResult, result);
    }
    
    /** Ejercicio 6
     * Test of construir method, of class ActividadArbolBinario.
     */
    @Test
    public void testConstruir() {
        System.out.println("Test 'construir' a partir de recorridos '3' y '3'");
        ArbolBinario<Character> expResult = hoja1Char;
        ArbolBinario<Character> result = ActividadArbolBinario.construir("3","3");
        boolean eq = ActividadArbolBinario.identicos(expResult, result);
        assertTrue(eq);
    }
    @Test
    public void testConstruir2() {
        System.out.println("Test 'construir' a partir de recorridos '7635218' y '3657128'");
        ArbolBinario<Character> expResult = arbolCamino;
        ArbolBinario<Character> result = ActividadArbolBinario.construir("7635218", "3657128");
        boolean eq = ActividadArbolBinario.identicos(expResult, result);
        assertTrue(eq);
    }
    
    /**
     * Ejercicio 7
     * Test of esArbolSeleccion method, of class ActividadArbolBinario.
     */
    @Test
    public void testVacioEsArbolSeleccion() {
        System.out.println("Test 'esArbolSelección' con árbol vacío");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.esArbolSeleccion(vacio);
        assertEquals(expResult, result);
       
    }
    @Test
    public void testHojaEsArbolSeleccion() {
        System.out.println("Test 'esArbolSelección' con árbol 'hoja1'");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.esArbolSeleccion(hoja1);
        assertEquals(expResult, result);
    }
    @Test
    public void testNoEsArbolSeleccion() {
        System.out.println("Test 'esArbolSelección' con árbol 'raiz'");
        boolean expResult = false;
        boolean result = ActividadArbolBinario.esArbolSeleccion(raiz);
        assertEquals(expResult, result);   
    }
    @Test
    public void testEsArbolSeleccion() {
        System.out.println("Test 'esArbolSelección' con árbol 'selec'");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.esArbolSeleccion(selec);
        assertEquals(expResult, result);
    }
    
    /**
     * Ejercicio 8
     * Test of esCamino method, of class ActividadArbolBinario.
     */
    @Test
    public void testVacioEsCamino() {
        System.out.println("Test 'esCamino' con árbol vacío y camino vacio");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.esCamino(vacioChar, "");
        assertEquals(expResult, result);
    }
    @Test
    public void testNoEsCamino() {
        System.out.println("Test 'esCamino' con árbol 'arbolCamino' y camino '5'");
        boolean expResult = false;
        boolean result = ActividadArbolBinario.esCamino(arbolCamino, "5");
        assertEquals(expResult, result);
    }
    @Test
    public void testEsCamino() {
        System.out.println("Test 'esCamino' con árbol 'arbolCamino' y camino '763'");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.esCamino(arbolCamino, "763");
        assertEquals(expResult, result);
    }
    @Test
    public void testEsCamino2() {
        System.out.println("Test 'esCamino' con árbol 'arbolCamino' y camino '721'");
        boolean expResult = true;
        boolean result = ActividadArbolBinario.esCamino(arbolCamino, "721");
        assertEquals(expResult, result);
    }
    
    /**
      * Ejercicio 9
     * Test of copiaHastaNivel method, of class ActividadArbolBinario.
     */
    @Test
    public void testVacioCopiaHastaNivel() {
        System.out.println("Test 'copiaHastaNivel' con árbol vacío y nivel 0");
        ArbolBinario expResult = vacio;
        ArbolBinario result = ActividadArbolBinario.copiaHastaNivel(vacio, 0);
        boolean eq = ActividadArbolBinario.identicos(expResult, result);
        assertTrue(eq);
    }
    @Test
    public void testCopiaHastaNivel() {
        System.out.println("Test 'copiaHastaNivel' con árbol 'raiz' y nivel 1");
        ArbolBinario expResult = raizHasta1;
        ArbolBinario result = ActividadArbolBinario.copiaHastaNivel(raiz, 1);
        boolean eq = ActividadArbolBinario.identicos(expResult, result);
        assertTrue(eq);
    }
    @Test
    public void testSobreCopiaHastaNivel() {
        System.out.println("Test 'copiaHastaNivel' con árbol 'raiz' y nivel 4");
        ArbolBinario expResult = raiz;
        ArbolBinario result = ActividadArbolBinario.copiaHastaNivel(raiz, 4);
        boolean eq = ActividadArbolBinario.identicos(expResult, result);
        assertTrue(eq);
    }    
    
    /**
     * Ejercicio 10
     * Test of esDistanciaK method, of class ActividadArbolBinario.
     */
    @Test
    public void testDistanciaVacioFalse() {
        System.out.println("Test 'distanciaK' con árbol vacío, elemento 2 y distancia 0");
        assertFalse(ActividadArbolBinario.esDistanciaK(vacio, 2, 0));
    }
    @Test
    public void testDistanciaFalse() {
        System.out.println("Test 'distanciaK' con árbol 'raiz', elemento 5 y distancia 1");
        assertFalse(ActividadArbolBinario.esDistanciaK(raiz, 5, 1));
    }
    @Test
    public void testDistanciaTrue() {
        System.out.println("Test 'distanciaK' con árbol 'raiz', elemento 5 y distancia 2");
        assertTrue(ActividadArbolBinario.esDistanciaK(raiz, 5, 2));
    }
}
