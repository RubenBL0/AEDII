package TADs.Heap;

/**
 *
 * @author Ruben Blanco
 */
public interface Heap<E extends Comparable<E>>{
    
    public boolean esVacio();
    
    public E recuperarMax() throws HeapVacioExcepcion;
    
    public E suprimirMax() throws HeapVacioExcepcion;
    
    public void insertar(E e) throws NullPointerException;
    
    public void anular();
}