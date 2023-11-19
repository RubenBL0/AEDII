package TADs.Grafo;

/**
 *
 * @author Ruben Blanco
 */
public class Vertice<E> {

    private E etiqueta;

    public Vertice(E o){
        this.etiqueta = o;
    }

    public E getEtiqueta(){
        return this.etiqueta;
    }
}
