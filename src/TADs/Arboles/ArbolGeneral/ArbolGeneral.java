package TADs.Arboles.ArbolGeneral;

import TADs.Arboles.ArbolVacioExcepcion;

/**
 *
 * @author Ruben Blanco
 */
public interface ArbolGeneral<E> {

    public boolean esVacio();

    public E raiz() throws ArbolVacioExcepcion;

    public ArbolGeneral<E> hijoMasIzq() throws ArbolVacioExcepcion;

    public ArbolGeneral<E> hermanoDer() throws ArbolVacioExcepcion;

    public boolean esta(E elemento);

    public void setRaiz(E elemRaiz) throws ArbolVacioExcepcion;

    public void setHijo(ArbolGeneral<E> hijo) throws ArbolVacioExcepcion, NullPointerException;
    
    public void suprimir(); 
}
