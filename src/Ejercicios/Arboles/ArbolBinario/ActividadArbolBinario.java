package Ejercicios.Arboles.ArbolBinario;

import TADs.Arboles.ArbolBinario.*;
/**
 *
 * @author Ruben Blanco
 */
public class ActividadArbolBinario {
    
    /**
     * Ejercicio 1:
     *
     * Escribe un método que dado un árbol binario devuelva verdadero si el árbol es completo y falso en
     * otro caso. Un árbol binario es completo si todos sus nodos tienen dos descendientes, excepto las hojas.
     */
    public static boolean completo(ArbolBinario arbol){
        if(arbol.esVacio()) return true;
        if((arbol.hijoIzq().esVacio() || arbol.hijoDer().esVacio()) && (!arbol.hijoIzq().esVacio() || !arbol.hijoDer().esVacio())){
            return false;
        }
        return completo(arbol.hijoIzq()) && completo(arbol.hijoDer());
    }
    
    /**
     * Ejercicio 2:
     *
     * Escribe un método que dados dos árboles binarios A y B, determine si son idénticos o no.
     */
    public static boolean identicos(ArbolBinario a1, ArbolBinario a2){
        if(a1.esVacio() && a2.esVacio()) return true;
        if(!a1.raiz().equals(a2.raiz())) return false;
        return identicos(a1.hijoIzq(), a2.hijoIzq()) && identicos(a1.hijoDer(), a2.hijoDer());
    }
    
    /**
     * Ejercicio 3:
     * 
     * Escribe un método que cuente el número de nodos que están en el nivel n de un árbol binario
     */
    public static int contarNivel(ArbolBinario arbol, int n){
        if(arbol.esVacio()) return 0;
        if(n == 0) return 1;
        return contarNivel(arbol.hijoIzq(), n - 1) + contarNivel(arbol.hijoDer(), n - 1);
    }
    
    /**
     * Ejercicio 4:
     * 
     * Escribe un método que dado un árbol binario A, cree un árbol binario B igual que A, pero sin las
     * hojas. 
     */
    public static EnlazadoArbolBinario eliminarHojas(ArbolBinario a){
        if(a.hijoIzq().esVacio() && a.hijoDer().esVacio()) return new EnlazadoArbolBinario();
        return new EnlazadoArbolBinario(a.raiz(), eliminarHojas(a.hijoIzq()), eliminarHojas(a.hijoDer()));
        
    }
    
    /**
     * Ejercicio 5:
     * 
     * Escribe un método que calcule la altura de un árbol binario.
     */
    public static int altura(ArbolBinario a){
        if(a.esVacio()) return -1;
        return 1 + Math.max(altura(a.hijoIzq()), altura(a.hijoDer()));
    }
    
    /**
     * Ejercicio 6:
     * 
     * Escribe un método que dados los recorridos en preoden e inorden de un árbol binario, reconstruya el
     * árbol. Suponemos que los recorridos son String y que no hay caracteres repetidos.
     */
    public static EnlazadoArbolBinario construir(String preorden, String inorden){
        if(preorden.isEmpty()) return new EnlazadoArbolBinario();
        char raiz = preorden.charAt(0);
        int index = inorden.indexOf(raiz);
        String preIzq = preorden.substring(1, index + 1);
        String preDer = preorden.substring(index + 1);
        String inIzq = inorden.substring(0, index);
        String inDer = inorden.substring(index + 1);
        EnlazadoArbolBinario hi = construir(preIzq, inIzq);
        EnlazadoArbolBinario hd = construir(preDer, inDer);
        return new EnlazadoArbolBinario(raiz, hi, hd);
    }
    
    /**
     * Ejercicio 7:
     * 
     * Un árbol de selección es un árbol binario donde cada nodo representa al menor de sus dos hijos,
     * excepto las hojas. Construir un método que, dado un árbol binario, indique si es o no un árbol de
     * selección. 
     */
    public static <E extends Comparable> boolean esArbolSeleccion(ArbolBinario<E> arbol){
        if(arbol.esVacio()) return true;
        if(arbol.hijoIzq().esVacio() && arbol.hijoDer().esVacio()) return true;
        E minimo;
        if(arbol.hijoIzq().esVacio()){
            minimo = arbol.hijoDer().raiz();
        }else if(arbol.hijoDer().esVacio()){
            minimo = arbol.hijoIzq().raiz();
        }else if(arbol.hijoIzq().raiz().compareTo(arbol.hijoDer().raiz()) < 0){
            minimo = arbol.hijoIzq().raiz();
        }else{
            minimo = arbol.hijoDer().raiz();
        }
        
        if(!arbol.raiz().equals(minimo)) return false;
        
        return esArbolSeleccion(arbol.hijoIzq()) && esArbolSeleccion(arbol.hijoDer());
    }
    
    /**
     * Ejercicio 8:
     * 
     * Escribe un método booleano que dados un árbol binario y un camino expresado en forma de String
     * determine si existe dicho camino en el árbol, teniendo en cuenta que el camino debe comenzar
     * necesariamente en la raíz.
     */
    public static boolean esCamino(ArbolBinario<Character> arbol, String camino){
        if(camino.isEmpty()) return true;
        if(!arbol.raiz().equals(camino.charAt(0))) return false;
        return esCamino(arbol.hijoIzq(), camino.substring(1)) || esCamino(arbol.hijoDer(), camino.substring(1));
    }
    
    /**
     * Ejercicio 9:
     * 
     * Escribe un método que dado un árbol binario y un nivel n del árbol, realice una copia del árbol hasta
     * dicho nivel.
     */
    public static <E> ArbolBinario<E> copiaHastaNivel(ArbolBinario<E> a, int nivel){
        if(nivel == -1 || a.esVacio()) return new EnlazadoArbolBinario<>();
        return new EnlazadoArbolBinario<>(a.raiz(), copiaHastaNivel(a.hijoIzq(), nivel - 1), copiaHastaNivel(a.hijoDer(), nivel - 1));
    }
    
    /**
     * Ejercicio 10:
     * 
     * Un elemento de un árbol se dirá de nivel k si aparece en el árbol a distancia k de la raíz. Escribe un
     * método que determine si un elemento está a distancia k.
     */
    public static <E> boolean esDistanciaK(ArbolBinario<E> a, E elem, int k){
        if(a.esVacio()) return false;
        if(k == 0 && a.raiz().equals(elem)) return true;
        if(k == 0 && !a.raiz().equals(elem)) return false;
        return esDistanciaK(a.hijoIzq(), elem, k - 1) || esDistanciaK(a.hijoDer(), elem, k - 1);
    }
}
