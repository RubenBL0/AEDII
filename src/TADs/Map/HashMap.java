package TADs.Map;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 *
 * @author Ruben Blanco
 */
public class HashMap<K, V> implements Map<K, V> {

    private ArrayList<Entry<K, V>>[] array;
    private int capacidad = 5;
    private int numElem;

    public HashMap(int capacidad) throws IllegalArgumentException {
        if (capacidad <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacidad = capacidad;
        array = new ArrayList[this.capacidad];
        for (int i = 0; i < this.capacidad; i++) {
            array[i] = new ArrayList<Entry<K, V>>();
        }
        this.numElem = 0;
    }

    public HashMap() {
        array = new ArrayList[this.capacidad];
        for (int i = 0; i < this.capacidad; i++) {
            array[i] = new ArrayList<Entry<K, V>>();
        }
        this.numElem = 0;
    }

    @Override
    public boolean esVacio() {
        return numElem == 0;
    }

    private int funcionHash(K clave) {
        int hash = clave.hashCode() % capacidad;
        return Math.abs(hash);
    }

    @Override
    public V get(K clave) {
        int indice = funcionHash(clave);
        for (Entry<K, V> e : array[indice]) {
            if (e.getClave().equals(clave)) {
                return e.getValor();
            }
        }
        return null;
    }

    @Override
    public void put(K clave, V valor) throws NullPointerException {
        if (clave == null) {
            throw new NullPointerException();
        }
        int indice = funcionHash(clave);
        boolean colocado = false;
        for (Entry<K, V> e : array[indice]) {
            if (e.getClave().equals(clave)) {
                e.setValor(valor);
                colocado = true;
            }
        }

        if (!colocado) {
            array[indice].add(new Entry(clave, valor));
            numElem++;
        }
    }

    @Override
    public V remove(K clave) throws NullPointerException, MapaVacioException {
        if (clave == null) {
            throw new NullPointerException();
        }
        if (esVacio()) {
            throw new MapaVacioException();
        }

        int indice = funcionHash(clave);
        for (int i = 0; i < array[indice].size(); i++) {
            if (array[indice].get(i).getClave().equals(clave)) {
                Entry<K, V> elemento = array[indice].remove(i);
                numElem--;
                return elemento.getValor();
            }
        }
        return null;

    }

    @Override
    public boolean containsKey(K clave) {
        return get(clave) != null;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < capacidad; i++) {
            for (Entry<K, V> e : array[i]) {
                if (e.getValor().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        for (int i = 0; i < capacidad; i++) {
            for (Entry<K, V> e : array[i]) {
                set.add(e.getClave());
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> list = new ArrayList<>();
        for (int i = 0; i < capacidad; i++) {
            for (Entry<K, V> e : array[i]) {
                list.add(e.getValor());
            }
        }
        return list;
    }

    @Override
    public int size() {
        return numElem;
    }

    @Override
    public void clear() {
        for(int i = 0; i < capacidad; i++){
            array[i].clear();
        }
        numElem = 0;
    }
}
