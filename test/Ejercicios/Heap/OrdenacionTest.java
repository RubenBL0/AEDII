package Ejercicios.Heap;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Ruben Blanco
 */
public class OrdenacionTest {
    
    public OrdenacionTest() {
    }

    /**
     * Test of heapSort method, of class Ordenacion.
     */
    @Test
    public void testHeapSortVacio() {
        System.out.println("heapSort vacío");
        int[] elementos = {};
        int[] expResult = {};
        elementos = Ordenacion.heapSort(elementos);
        assertTrue(Arrays.equals(elementos, expResult));
    }
    @Test
    public void testHeapSort1() {
        System.out.println("heapSort 1 elemento");
        int[] elementos = {23};
        int[] expResult = {23};
        elementos = Ordenacion.heapSort(elementos);
        assertTrue(Arrays.equals(elementos, expResult));
    }
    @Test
    public void testHeapSort() {
        System.out.println("heapSort varios elementos");
        int[] elementos = {23,45,12,34,67,43,76,88,69,28,61};
        int[] expResult = {88,76,69,67,61,45,43,34,28,23,12};
        elementos = Ordenacion.heapSort(elementos);
        assertTrue(Arrays.equals(elementos, expResult));
    }
    
}
