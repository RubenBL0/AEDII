package TADs.Arboles;

/**
 *
 * @author Ruben Blanco
 */
public class ArbolVacioExcepcion extends RuntimeException{
    
    public ArbolVacioExcepcion(){
    }
    
    public ArbolVacioExcepcion(String msg){
        super(msg);
    }
}
