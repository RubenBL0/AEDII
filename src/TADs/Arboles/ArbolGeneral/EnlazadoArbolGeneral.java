package TADs.Arboles.ArbolGeneral;

import TADs.Arboles.ArbolVacioExcepcion;

/**
 *
 * @author Ruben Blanco
 */
public class EnlazadoArbolGeneral<E> implements ArbolGeneral<E> {

    private NodoGeneral<E> nodoRaiz;
    private ArbolGeneral<E> hijoMasIzq;
    private ArbolGeneral<E> hermanoDer;
    
    public EnlazadoArbolGeneral(){
        nodoRaiz = null;
    }
    
    public EnlazadoArbolGeneral(E elemento, ArbolGeneral<E> ...hijos){
        nodoRaiz = new NodoGeneral<>(null, null, null);
        setRaiz(elemento);
        for(ArbolGeneral<E> hijo: hijos){
            setHijo(hijo);
        }
    }
    
    private EnlazadoArbolGeneral(NodoGeneral<E> nodo){
        nodoRaiz = nodo;
    }
    
    @Override
    public boolean esVacio() {
        return nodoRaiz == null;
    }

    @Override
    public E raiz() throws ArbolVacioExcepcion {
        if(esVacio()) throw new ArbolVacioExcepcion();
        return nodoRaiz.getElemento();
    }

    @Override
    public ArbolGeneral<E> hijoMasIzq() throws ArbolVacioExcepcion {
        if(esVacio()) throw new ArbolVacioExcepcion();
        return new EnlazadoArbolGeneral(nodoRaiz.getHijoMasIzq());
    }

    @Override
    public ArbolGeneral<E> hermanoDer() throws ArbolVacioExcepcion {
        if(esVacio()) throw new ArbolVacioExcepcion();
        return new EnlazadoArbolGeneral(nodoRaiz.getHermanoDer());
    }

    @Override
    public boolean esta(E elemento) {
        if(esVacio()) return false;
        if(raiz().equals(elemento)) return true;
        return hijoMasIzq.esta(elemento) || hermanoDer.esta(elemento);
    }

    @Override
    public void setRaiz(E elemRaiz) throws ArbolVacioExcepcion {
        if(esVacio()) throw new ArbolVacioExcepcion();
        nodoRaiz.setElemento(elemRaiz);
    }

    @Override
    public void setHijo(ArbolGeneral<E> hijo) throws ArbolVacioExcepcion, NullPointerException {
        if(esVacio()) throw new ArbolVacioExcepcion();
        if(hijo == null) throw new NullPointerException();
        if(hijoMasIzq().esVacio()){
            nodoRaiz.setHijoMasIzq(((EnlazadoArbolGeneral)hijo).nodoRaiz);
        }else{
            ArbolGeneral ultimoHijo = hijoMasIzq();
            while(!ultimoHijo.hermanoDer().esVacio()){
                ultimoHijo = ultimoHijo.hermanoDer();
            }
            ((EnlazadoArbolGeneral)ultimoHijo).nodoRaiz.setHermanoDer(((EnlazadoArbolGeneral)hijo).nodoRaiz);
        }
    }

    @Override
    public void suprimir() {
        nodoRaiz = null;
    }
    
}
