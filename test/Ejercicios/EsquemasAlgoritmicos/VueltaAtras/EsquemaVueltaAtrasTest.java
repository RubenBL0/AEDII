package Ejercicios.EsquemasAlgoritmicos.VueltaAtras;

import TADs.Map.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Ruben Blanco
 */
public class EsquemaVueltaAtrasTest {
    
    private static Map<Integer,Integer> can;
    private static Map<String,Integer> programas;

    private static void rellenarMapas(){

        can = new HashMap<>();
        can.put(200,10);
        can.put(500,10);

        programas=new HashMap<>();
        programas.put("p1",10);
        programas.put("p2",3);
        programas.put("p3",5);
        programas.put("p4",3);
        programas.put("p5",3);
    }

    private static <E,F> boolean mapasIguales(Map<E,F> m1, Map<E, F> m2){
        if (m1.size() == m2.size()){
            Iterator<E> itr = m1.keySet().iterator();
            while(itr.hasNext()){
                E clave = itr.next();
                if (m1.get(clave) == null || !m1.get(clave).equals(m2.get(clave)))
                    return false;
                }
                return true;
        }
        return false;
    }

    private static <E> boolean listasIguales(List<E> m1, List<E> m2){
        if (m1.size() == m2.size()){
            Iterator<E> itr = m1.iterator();
            while(itr.hasNext()){
                E clave = itr.next();
                if (!m2.contains(clave))
                    return false;
                }
                return true;
        }
        return false;
    }
    
    @Before
    public void setUp() throws Exception{
        rellenarMapas();
    }
    
    /**
     * Test of darCambio method, of class EsquemaVueltaAtras.
     */
    @Test
    public void testDarCambio() {
        System.out.println("darCambio");
        Map<Integer, Integer> mapaActual = new HashMap<>();

        EsquemaVueltaAtras.darCambio(2100, can, mapaActual);

        Map<Integer,Integer> mapaEsperado = new HashMap<>();
        mapaEsperado.put(200,3);
        mapaEsperado.put(500,3);
        
        boolean result = mapasIguales(mapaActual,mapaEsperado);
        
        assertTrue(result);
    }
    
    /**
     * Test of llenarPendrive method, of class EsquemaVueltaAtras.
     */
    @Test
    public void testLlenarPendrive() {
        System.out.println("llenarPendrive");
        int capacidadPendrive = 21;
        List<String> S = new LinkedList<>();
        List<String> expResult = new LinkedList<>();
        expResult.add("p4");
        expResult.add("p3");
        expResult.add("p2");
        expResult.add("p1");
        EsquemaVueltaAtras.llenarPendrive(capacidadPendrive, programas, S);
        
        boolean result = listasIguales(expResult,S);
        assertTrue(result);
    }
    
    /**
     * Test of subconjuntos method, of class EsquemaVueltaAtras.
     */    
    @Test
    public void testSubconjuntos(){
        System.out.println("subconjuntos");
        int[] valores = new int[]{1, 6, 1, 8, 1, 7};
        int[] solucion = new int[]{0, 0, 0, 0, 0, 0};
        int resultado = 21;
        int[] expResult = new int[]{0, 1, 0, 1, 0, 1};
        EsquemaVueltaAtras.subconjunto(valores, solucion, resultado, 0);
        
        assertArrayEquals(expResult, solucion);
    }
    
    /**
     * Test of colocarReinas method, of class EsquemaVueltaAtras.
     */
    @Test
    public void testColocarReinas() {
        System.out.println("colocarReinas");
        int[] tablero = new int[8];
        EsquemaVueltaAtras.colocarReinas(0, tablero);
        int [] solEsperada = {0,4,7,5,2,6,1,3};

        assertArrayEquals(tablero,solEsperada);
    }
}
