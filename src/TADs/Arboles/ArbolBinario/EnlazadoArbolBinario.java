package TADs.Arboles.ArbolBinario;

/**
 *
 * @author Ruben Blanco
 */
public class EnlazadoArbolBinario<E> implements ArbolBinario<E>{

    private NodoBinario<E> nodoRaiz;
    
    public EnlazadoArbolBinario(){
        nodoRaiz = null;
    }
    
    public EnlazadoArbolBinario(E elemento, ArbolBinario<E> hi, ArbolBinario<E> hd){
        nodoRaiz = new NodoBinario<E>(null, null, null);
        setRaiz(elemento);
        setHijoIzq(hi);
        setHijoDer(hd);
    }
    
    private EnlazadoArbolBinario(NodoBinario<E> nodoRaiz){
        this.nodoRaiz = nodoRaiz;
    }
    
    @Override
    public boolean esVacio() {
        return nodoRaiz ==  null;
    }

    @Override
    public E raiz() throws ArbolVacioExcepcion {
        if(esVacio()) throw new ArbolVacioExcepcion();
        return nodoRaiz.getElemento();
    }

    @Override
    public ArbolBinario<E> hijoIzq() throws ArbolVacioExcepcion {
        if(esVacio()) throw new ArbolVacioExcepcion();
        return new EnlazadoArbolBinario<>(nodoRaiz.getIzq());
    }

    @Override
    public ArbolBinario<E> hijoDer() throws ArbolVacioExcepcion {
        if(esVacio()) throw new ArbolVacioExcepcion();
        return new EnlazadoArbolBinario<>(nodoRaiz.getDer());
    }

    @Override
    public boolean esta(E elemento) {
        if(esVacio()) return false;
        if(raiz().equals(elemento)) return true;
        return hijoIzq().esta(elemento) || hijoDer().esta(elemento);
    }

    @Override
    public void setRaiz(E elemRaiz) throws ArbolVacioExcepcion {
        if(esVacio()) throw new ArbolVacioExcepcion();
        nodoRaiz.setElemento(elemRaiz);
    }

    @Override
    public void setHijoIzq(ArbolBinario<E> hi) throws ArbolVacioExcepcion, NullPointerException {
        if(esVacio()) throw new ArbolVacioExcepcion();
        if(hi == null) throw new NullPointerException();
        nodoRaiz.setIzq(((EnlazadoArbolBinario)hi).nodoRaiz);
    }

    @Override
    public void setHijoDer(ArbolBinario<E> hd) throws ArbolVacioExcepcion, NullPointerException {
        if(esVacio()) throw new ArbolVacioExcepcion();
        if(hd == null) throw new NullPointerException();
        nodoRaiz.setDer(((EnlazadoArbolBinario)hd).nodoRaiz);
    }

    @Override
    public void suprimir() {
        nodoRaiz = null;
    }
    
}
