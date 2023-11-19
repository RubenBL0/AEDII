package TADs.Arboles.ArbolGeneral;

/**
 *
 * @author Ruben Blanco
 */
public class NodoGeneral<E> {
    
    private E elemento;
    private NodoGeneral<E> hijoMasIzq;
    private NodoGeneral<E> hermanoDer;
    
    public NodoGeneral(E elemento, NodoGeneral<E> hijoMasIzq, NodoGeneral<E> hermanoDer){
        this.elemento = elemento;
        this.hijoMasIzq = hijoMasIzq;
        this.hermanoDer = hermanoDer;
    }

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public NodoGeneral<E> getHijoMasIzq() {
        return hijoMasIzq;
    }

    public void setHijoMasIzq(NodoGeneral<E> hijoMasIzq) {
        this.hijoMasIzq = hijoMasIzq;
    }

    public NodoGeneral<E> getHermanoDer() {
        return hermanoDer;
    }

    public void setHermanoDer(NodoGeneral<E> hermanoDer) {
        this.hermanoDer = hermanoDer;
    }
    
    
}
