package TADs.Heap;

/**
 *
 * @author Ruben Blanco
 */
public class HeapVacioExcepcion extends RuntimeException{
    
    public HeapVacioExcepcion(){
        
    }
    
    public HeapVacioExcepcion(String msg){
        super(msg);
    }
}
