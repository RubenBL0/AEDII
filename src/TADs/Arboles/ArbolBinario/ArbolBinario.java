package TADs.Arboles.ArbolBinario;

import TADs.Arboles.ArbolVacioExcepcion;

/**
 *
 * @author Ruben Blanco
 */
public interface ArbolBinario<E> {
    
    public boolean esVacio();
    
    public E raiz() throws ArbolVacioExcepcion;
    
    public ArbolBinario<E> hijoIzq() throws ArbolVacioExcepcion;
    
    public ArbolBinario<E> hijoDer() throws  ArbolVacioExcepcion;
    
    public boolean esta(E elemento);
    
    public void setRaiz(E elemRaiz) throws ArbolVacioExcepcion;
    
    public void setHijoIzq(ArbolBinario<E> hi) throws ArbolVacioExcepcion, NullPointerException;
    
    public void setHijoDer(ArbolBinario<E> hd) throws ArbolVacioExcepcion, NullPointerException;
    
    public void suprimir();
}
