package TADs.Heap;

/**
 *
 * @author Ruben Blanco
 */
public class HeapBinario<E extends Comparable<E>> implements Heap<E>{

    private static final int CAPACIDAD = 20;
    private E[] array;
    private int numElem;
    
    public E[] getArray(){
        return array;
    }
    
    public HeapBinario(int capacidad){
        if(capacidad < 0){
            throw new IllegalArgumentException();
        }
        numElem = 0;
        array = (E[]) new Comparable[capacidad + 1];
    }
    
    public HeapBinario(){
        this(CAPACIDAD);
    }
    
    @Override
    public boolean esVacio() {
        return numElem == 0;
    }

    @Override
    public E recuperarMax() throws HeapVacioExcepcion {
        if(esVacio()) throw new HeapVacioExcepcion();
        return array[1];
    }

    @Override
    public E suprimirMax() throws HeapVacioExcepcion {
        if(esVacio()) throw new HeapVacioExcepcion();
        E max = recuperarMax();
        intercambiar(1, numElem);
        array[numElem] = null;
        numElem--;
        hundir(1);
        return max;
        
    }
    
    private void intercambiar(int i, int j){
        E elem = array[i];
        array[i] = array[j];
        array[j] = elem;
    }
    
    private void hundir(int pos){
        if(esVacio()) return; //heap vacío
        
        if(2*pos > numElem){
            return; //es una hoja
        }
        if(array[pos].compareTo(array[2*pos]) >= 0 && (array[2*pos+1] == null || array[pos].compareTo(array[2*pos+1]) >= 0)){
            return; //el elemento es mayor que sus hijos
        }
        
        if(array[2*pos + 1] == null || array[2*pos].compareTo(array[2*pos+1]) >= 0){
            //intercambiar por hijo izquierdo y hundir
            intercambiar(pos, 2*pos);
            hundir(2*pos);
            return;
        }else{
            //intercambiar por hijo derecho y hundir
            intercambiar(pos, 2*pos+1);
            hundir(2*pos+1);
            return;
        }
    }

    @Override
    public void insertar(E e) throws NullPointerException {
        if(e == null) throw new NullPointerException("insertar(): el elemento es null");
        
        //Si el array está lleno
        if(numElem == array.length - 1){
            duplicarArray();
        }
        
        array[numElem + 1] = e;
        int i = numElem + 1;
        while((i > 1) && (array[i].compareTo(array[i/2]) > 0)){
            intercambiar(i, i/2);
            i = i/2;
        }
        numElem++;
    }

    private void duplicarArray(){
        E[] nuevoHeap = (E[]) new Comparable[array.length * 2]; 
        for(int j = 0; j < array.length; j++){
            nuevoHeap[j] = array[j];
        }
        array = nuevoHeap;
    }
    
    @Override
    public void anular() {
        numElem = 0;
    }
    
    /**
     * Método introducir(): añade un objeto, pero no garantiza que se mantenga la propiedad
     * de ordenación del heap o montículo binario. 
     */
    public void introducir(E elem) throws NullPointerException{
        if(elem == null) throw new NullPointerException();
        if(numElem == array.length - 1){
            duplicarArray();
        }
        numElem++;
        array[numElem] = elem;
    }
    
    /**
     * Método arreglarHeap(): restablece el orden en el montículo. Debido a que es costoso, su
     * uso está justificado si se realizan muchas operaciones introducir() entre dos accesos al
     * elemento de mayor prioridad.
     */
    public void arreglarHeap(){
        for (int i = numElem / 2; i > 0; i--) {
            hundir(i);
        }
    }
}
