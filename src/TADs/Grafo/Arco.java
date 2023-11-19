package TADs.Grafo;

/**
 *
 * @author Ruben Blanco
 */
public class Arco<E, T> {

    private Vertice<E> origen, destino;
    private T etiqueta;

    public Arco(Vertice<E> o, Vertice<E> d, T e){
        this.origen = o;
        this.destino = d;
        this.etiqueta = e;                
    }

    public Vertice<E> getOrigen(){
        return this.origen;
    }

    public Vertice<E> getDestino(){
        return this.destino;
    }

    public T getEtiqueta(){
        return this.etiqueta;
    }
}
