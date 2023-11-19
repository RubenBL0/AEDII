package Ejercicios.Arboles.ArbolGeneral;

import TADs.Arboles.ArbolGeneral.*;
/**
 *
 * @author Ruben Blanco
 */
public class ActividadArbolGeneral {
    
    /**
     * Ejercicio 1:
     * 
     * Escribe un método que, dada una referencia a un árbol general de enteros, calcule la suma de los
     * valores almacenados en sus nodos.
     */
    public static int getSuma(ArbolGeneral<Integer> arbol){
        if(arbol.esVacio()) return 0;
        int suma = arbol.raiz();
        ArbolGeneral<Integer> hijo = arbol.hijoMasIzq();
        while(!hijo.esVacio()){
            suma += getSuma(hijo);
            hijo = hijo.hermanoDer();
        }
        return suma;
    }
    
    /**
     * Ejercicio 2:
     * 
     * Escribe un método booleano que dados dos árboles generales determine si tienen la misma
     * estructura. Por ejemplo, los árboles generales que siguen tienen la misma estructura, aunque, como
     * puede observarse, no coincidan los valores que se almacenan en los nodos.
     */
    public static <E> boolean esIgualEstructura(ArbolGeneral<E> a, ArbolGeneral<E> b){
        if(a.esVacio() || b.esVacio()) return a.esVacio() && b.esVacio();
        
        ArbolGeneral<E> hijoA = a.hijoMasIzq();
        ArbolGeneral<E> hijoB = b.hijoMasIzq();
        
        while(!hijoA.esVacio() && !hijoB.esVacio() && esIgualEstructura(hijoA, hijoB)){
            hijoA = hijoA.hermanoDer();
            hijoB = hijoB.hermanoDer();
        }
        return esIgualEstructura(hijoA, hijoB);
    }
    
    /**
     * Ejercicio 3:
     * 
     * Escribe un método que dado un árbol general, devuelva cierto si se trata de un árbol 2-3. Un árbol 2-
     * 3 es aquel en el que cada nodo tiene exactamente 2 o 3 hijos, excepto las hojas.
     */
    public static <E> boolean esArbolDosTres(ArbolGeneral<E> a){
        if(a.esVacio() || a.hijoMasIzq().esVacio()) return true;
        
        if(numHijos(a) != 2 && numHijos(a) != 3) return false;
        
        ArbolGeneral<E> hijo = a.hijoMasIzq();
        while (!hijo.esVacio() && esArbolDosTres(hijo)) {
            hijo = hijo.hermanoDer();
        }
        return hijo.esVacio();
    }
    
    private static <E> int numHijos(ArbolGeneral<E> a){
        int cont = 0;
        ArbolGeneral<E> hijo = a.hijoMasIzq();
        while (!hijo.esVacio()) {
            cont++;
            hijo = hijo.hermanoDer();
        }
        return cont;
    }
    
    /**
     * Ejercicio 4:
     * 
     * Un árbol de selección es un árbol donde cada nodo representa al menor de sus hijos, excepto las
     * hojas. Construir un método que, dado un árbol general, indique si es o no un árbol de selección.
     */
    public static <E extends Comparable<E>> boolean esSeleccion(ArbolGeneral<E> a){
        if(a.esVacio() || a.hijoMasIzq().esVacio()) return true;
        ArbolGeneral<E> hijo = a.hijoMasIzq();
        E menor = hijo.raiz();
        while (!hijo.esVacio()) {
            if (!esSeleccion(hijo)) return false;
            
            if (hijo.raiz().compareTo(menor) < 0) {
                menor = hijo.raiz();
            }
            hijo = hijo.hermanoDer();
        }
        return menor.compareTo(a.raiz()) == 0;
    }
    
    /**
     * Ejercicio 5:
     * 
     * Escribe un método que determine el nivel de un elemento concreto en el árbol general.
     */
    
    /**
     * Ejercicio 6:
     * 
     * Escribe un método que devuelve el grado de un árbol general. El grado de un árbol es el máximo de
     * los grados de sus nodos.
     */
    
    /**
     * Ejercicio 7:
     * 
     * Escribe un método que devuelva la altura de un árbol general.
     */
    
    /**
     * Ejercicio 8:
     * 
     * Haciendo uso de una cola, escribe un método que muestre el recorrido en anchura de un árbol
     * general.
     */
    
    /**
     * Ejercicio 9:
     * 
     * Escribe un método que cuente el número de nodos pares en un árbol de enteros que se pasa como
     * parámetro.
     */
    
    /**
     * Ejercicio 10:
     * 
     * Escribe un método que dado un árbol general y una lista que se pasa como parámetro, guarde en la
     * lista las hojas del árbol general.
     */
}
