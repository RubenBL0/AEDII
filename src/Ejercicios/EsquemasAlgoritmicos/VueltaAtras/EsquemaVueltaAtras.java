package Ejercicios.EsquemasAlgoritmicos.VueltaAtras;

import TADs.Grafo.*;
import TADs.Map.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author Ruben Blanco
 */
public class EsquemaVueltaAtras {
    
    /**
     * Ejercicio 1:
     * 
     * Problema de dar el cambio. Supongamos que el cajero sólo tiene billetes de 200 y 500 euros y
     * nos debe dar 2100 euros. Con una estrategia voraz nunca llegaría a la solución correcta (daría
     * 4 de 500 y no podría dar el resto), mientras que con vuelta atrás podemos ir dando billetes y si
     * llegamos a una combinación sin solución, volvemos atrás intentándolo con otro billete y así
     * sucesivamente. En este caso la solución sería 3 billetes de 500 y 3 de 200. Implementa este
     * algoritmo siguiendo una estrategia de vuelta atrás.
     */
    public static boolean darCambio(int importeDevolver, Map<Integer, Integer> mapCanti, Map<Integer,Integer> mapSol){
        boolean objetivo = false;
        Iterator<Integer> itClaves = mapCanti.keySet().iterator();
        
        while(itClaves.hasNext() && !objetivo){
            int billete = itClaves.next();
            
            if(mapCanti.get(billete) > 0 && importeDevolver >= billete){
                mapCanti.put(billete, mapCanti.get(billete) - 1);
                if(mapSol.get(billete) == null){
                    mapSol.put(billete, 1);
                }else{
                    mapSol.put(billete, mapSol.get(billete) + 1);
                }
                
                if(billete == importeDevolver){
                    objetivo = true;
                }else{
                    objetivo = darCambio(importeDevolver - billete, mapCanti, mapSol);
                    if(!objetivo){
                        mapCanti.put(billete, mapCanti.get(billete) + 1);
                        mapSol.put(billete, mapSol.get(billete) - 1);
                    }
                }
            }
        }
        return objetivo;
    }
    
    /**
     * Ejercicio 2:
     * 
     * Considere que tenemos n programas distintos para grabar en un pendrive, pero el
     * espacio de memoria que necesitan excede la capacidad del dispositivo. Cada
     * programa Pi requiere mi kilobytes de memoria, la capacidad del pendrive es de C
     * kilobytes y C es menor que la suma del espacio necesario para almacenar todos los programas.
     * Resuelve este algoritmo siguiendo un esquema de vuelta atrás, de tal forma que si
     * existe solución consigue ocupar el espacio del pendrive completamente, sino lo
     * devuelve vacío. 
     */
    public static boolean llenarPendrive(int capacidadMaxima, Map<String, Integer> espacioProgramas, List<String> pendrive){
        boolean objetivo = false;
        Iterator<String> itClaves = espacioProgramas.keySet().iterator();
        while(itClaves.hasNext() && !objetivo){
            String clave = itClaves.next();
            int tam = espacioProgramas.get(clave);
            if(tam <= capacidadMaxima && !pendrive.contains(clave)){
                pendrive.add(clave);
                
                if(capacidadMaxima == tam){
                    objetivo = true;
                }else{
                    objetivo = llenarPendrive(capacidadMaxima - tam, espacioProgramas, pendrive);
                    if(!objetivo){
                        pendrive.remove(clave);
                    }
                }
            }
        }
        return objetivo;
    }
    
    /**
     * Ejercicio 3:
     * 
     * Considérese un “vector” que almacena n enteros positivos distintos: vector = {v1, v2,
     * v3, …, vn}. El problema de la suma consiste en encontrar un subconjunto del vector
     * cuya suma sea exactamente “resultado”.
     * La “solución” tendrá la forma de vector con valores 1 y 0, es decir, solución = (s1, s2,
     * s3, …, sn} con si ∈{0,1} y deberá cumplir: ∑ vi*si = resultado.
     * Para resolver este problema deberá utilizarse la estrategia de vuelta atrás. Que irá
     * considerando todas las posibilidades (incluir / no incluir cada elemento en la
     * solución).
     */
    public static boolean subconjunto(int[] valores, int[] solucion, int resultado, int indice){

        boolean objetivo = false;
        for(int i = indice; i < valores.length && !objetivo; i++){
            if(valores[i] <= resultado){
                solucion[i] = 1;
                System.out.println("Añadido " + valores[i]);
                if(valores[i] == resultado){
                    objetivo = true;
                }else{
                    objetivo = subconjunto(valores, solucion, resultado - valores[i], i + 1);
                    if(!objetivo){
                        solucion[i] = 0;
                        System.out.println("Retirado " + valores[i]);
                    }
                }
            }
        }
        return objetivo;
    }
    
    /**
     * Ejercicio 4:
     * 
     * Problema de las 8 reinas. El problema consiste en colocar ocho reinas en un tablero
     * de ajedrez sin que se den jaque (dos reinas se dan jaque si comparten fila, columna o
     * diagonal). Puesto que no puede haber más de una reina por fila, podemos replantear
     * el problema como "colocar una reina en cada fila del tablero de forma que no se den
     * jaque". En este caso, para ver si dos reinas se dan jaque basta con ver si comparten
     * columna o diagonal (emplea el método buenSitio). Por lo tanto, toda solución del
     * problema se puede representar como una 8-tupla (X0,...,X7) en la que Xi es la columna
     * en la que se coloca la reina que está en la fila i del tablero. Implementa el algoritmo
     * colocarReinas, siguiendo una estrategia de vuelta atrás para resolver el ejercicio.
     */
    public static boolean colocarReinas (int reina, int[] tablero){
        boolean objetivo = false;
        int col = 0;
        while(col <= 7 && !objetivo){
            if(buenSitio(reina, col, tablero)){
                tablero[reina] = col;
                
                if(reina == 7){
                    objetivo = true;
                }else{
                    objetivo = colocarReinas(reina + 1, tablero);
                    if(!objetivo){
                        tablero[reina] = -1;
                    }
                }
            }
            col++;
        }
        return objetivo;
    }
    
    private static boolean buenSitio (int r, int col, int[] tabl){
        // ¿Es amenaza colocar la reina “r” en la columna “col”?
        int reiAnt=0;
        boolean continuar=true;
        while (reiAnt < r && continuar) {
            if (tabl[reiAnt] == col)
            continuar =false;
            else if (Math.abs(reiAnt-r) == Math.abs(tabl[reiAnt]-col))
            continuar=false;
            reiAnt++;
            }
        return continuar;
    }
}
