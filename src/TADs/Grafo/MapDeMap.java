package TADs.Grafo;

import TADs.Map.Map;
import TADs.Map.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *
 * @author Ruben Blanco
 */
public class MapDeMap<E, T> implements Grafo<E, T> {

    private HashMap <Vertice<E>, HashMap<Vertice<E>, T>> mapaVertices;

    public MapDeMap() {
        this.mapaVertices = new HashMap<Vertice<E>, HashMap<Vertice<E>, T>>();
    }

    @Override
    public boolean esVacio() {
        return mapaVertices.esVacio();
    }

    @Override
    public boolean estaVertice(Vertice<E> v) {
        return mapaVertices.containsKey(v);
    }

    @Override
    public boolean estaArco(Arco<E, T> a) {
        HashMap<Vertice<E>, T> vertice = mapaVertices.get(a.getOrigen());
        if (vertice != null) {
            T etiqueta = vertice.get(a.getDestino());
            return a.getEtiqueta().equals(etiqueta);
        }
        return false;
    }

    @Override
    public Iterator<Vertice<E>> vertices() {
        return mapaVertices.keySet().iterator();
    }

    @Override
    public Iterator<Arco<E, T>> arcos() {
        Iterator<Vertice<E>> vertices = vertices();
        ArrayList<Arco<E, T>> list = new ArrayList<>();
        while (vertices.hasNext()) {
            Vertice<E> v = vertices.next();
            Iterator<Vertice<E>> adyacentes = mapaVertices.get(v).keySet().iterator();
            while (adyacentes.hasNext()) {
                Vertice<E> a = adyacentes.next();
                Arco<E, T> arco = new Arco(v, a, mapaVertices.get(v).get(a));
                list.add(arco);
            }
        }
        return list.iterator();
    }

    @Override
    public Iterator<Vertice<E>> adyacentes(Vertice<E> v) {
        return mapaVertices.get(v).keySet().iterator();
    }

    @Override
    public void insertarVertice(Vertice<E> v) {
        mapaVertices.put(v, new HashMap<Vertice<E>, T>());
    }

    @Override
    public void insertarArco(Arco<E, T> a) {
        if (!mapaVertices.containsKey(a.getOrigen())) {
            insertarVertice(a.getOrigen());
        }
        if (!mapaVertices.containsKey(a.getDestino())) {
            insertarVertice(a.getDestino());
        }
        mapaVertices.get(a.getOrigen()).put(a.getDestino(), a.getEtiqueta());
    }

    @Override
    public void eliminarVertice(Vertice<E> v) {
        mapaVertices.remove(v);
        Iterator<HashMap<Vertice<E>, T>> it = mapaVertices.values().iterator();
        while (it.hasNext()) {
            it.next().remove(v);
        }
    }

    @Override
    public void eliminarArco(Arco<E, T> a) {
        mapaVertices.get(a.getOrigen()).remove(a.getDestino());
    }

}
