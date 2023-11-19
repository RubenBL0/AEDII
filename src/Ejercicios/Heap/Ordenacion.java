package Ejercicios.Heap;

import TADs.Heap.*;
/**
 *
 * @author Ruben Blanco
 */
public class Ordenacion {
    
    public static int[] heapSort(int[] elementos){
        int[] array = new int[elementos.length];
        HeapBinario<Integer> heap = new HeapBinario();
        for(int e: elementos){
            heap.introducir(e);
        }
        heap.arreglarHeap();
        int i = 0;
        while(!heap.esVacio()){
            array[i++] = heap.suprimirMax();
        }
        return array;
    }
}
