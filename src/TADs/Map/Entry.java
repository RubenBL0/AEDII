package TADs.Map;

/**
 *
 * @author Ruben Blanco
 */
public class Entry<K, V> {
    K clave;
    V valor;

    public Entry(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public K getClave() {
        return clave;
    }

    public V getValor() {
        return valor;
    }

    public void setClave(K clave) {
        this.clave = clave;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }
}
