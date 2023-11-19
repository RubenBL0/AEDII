package TADs.Map;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Ruben Blanco
 */
public interface Map<K, V> {
    
    public boolean esVacio();
            //Produce: cierto si this está vacío, falso en caso contrario.
    
    public V get(K clave);
            //Produce: devuelve el valor asociado a la clave y si no existe devuelve null.  
    
    public void put(K clave,V valor) throws NullPointerException;        
            //Modifica: this.
            //Produce: Coloca el par clave-valor en el mapa, si la clave ya existe, se sobreescribe.Si la clave es null lanza la excepción NullPointerException
    
    public V remove(K clave)throws NullPointerException, MapaVacioException;
            //Modifica: this.
            //Produce: Si this está vacio lanza MapaVacioException, sino si la clave es null lanza NullPointerException
                    // Si existe un valor asociado a esa clave devuelve el valor y lo elimina, sino devuelve null.
    
    public boolean containsKey(K clave);
            //Produce: True si en this existe la clave, sino false.
    
    public boolean containsValue(V value);
            //Produce: True si en this existe el valor, sino false.  
    
    public Set<K> keySet();
            //Produce: Devuelve una coleccion de todas las claves del mapa.
    
    public Collection<V> values();
            //Produce: Devuelve una colección con los valores del mapa.

    public int size();
            //Produce: Devuelve el número de pares del mapa.
    
   public void clear();
            //Modifica: this
            //Produce: Elimina todos los pares del mapa.
}
