package Ejercicios.EsquemasAlgoritmicos.Voraz;

import TADs.Grafo.*;
import TADs.Map.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author Ruben Blanco
 */
public class EsquemaVoraz {
    /**
     * Ejercicio 1:
     * 
     * Algoritmo del Viajante. Consideremos un mapa de carreteras, con dos tipos de
     * componentes: las ciudades (nodos) y las carreteras que las unen. Cada tramo de
     * carreteras (arco) está señalado con su longitud. “Un viajante debe recorrer una serie de
     * ciudades interconectadas entre sí, de manera que recorra todas ellas con el menor
     * número de kilómetros posible, comenzando en una ciudad determinada”. Emplea una
     * estrategia voraz para resolver el ejercicio.
     */
    public static <E> Grafo<E,Integer> viajante(Grafo<E,Integer> g, Vertice<E> v){
        List<Vertice<E>> porVisitar = new LinkedList<>();
        Iterator <Vertice<E>> itr = g.vertices();
        while(itr.hasNext()){
            porVisitar.add(itr.next());
        }
        Grafo<E, Integer> gSolucion = new MapDeMap<>();
        Vertice<E> vActual = v;
        porVisitar.remove(vActual);
        boolean continuar = true;
        
        while(!porVisitar.isEmpty() && continuar){
            Arco<E, Integer> arco = buscarArcoViajante(vActual, porVisitar, g.arcos());
            if(arco != null){
                gSolucion.insertarArco(arco);
                vActual = arco.getDestino();
                porVisitar.remove(vActual);
            }else{
                continuar = false;
            }
        }
        return gSolucion;
    }
    
    private static <E> Arco<E, Integer> buscarArcoViajante(Vertice<E> vActual, List<Vertice<E>> porVisitar, Iterator<Arco<E, Integer>> arcos){
        Arco<E, Integer> arco = null;
        int min = Integer.MAX_VALUE;
        while(arcos.hasNext()){
            Arco<E, Integer> arcoActual = arcos.next();
            if(arcoActual.getOrigen().equals(vActual) && porVisitar.contains(arcoActual.getDestino()) && arcoActual.getEtiqueta() < min){
                arco = arcoActual;
                min = arcoActual.getEtiqueta();
            }
        }
        return arco;
    }
    
    /**
     * Ejercicio 2:
     * 
     * Algoritmo de Prim. Es un algoritmo perteneciente a la teoría de los grafos para
     * encontrar un árbol recubridor mínimo en un grafo conexo, no dirigido y cuyas
     * aristas están etiquetadas. En otras palabras, el algoritmo encuentra un subconjunto de
     * aristas que forman un árbol con todos los vértices, donde el peso total de todas las
     * aristas en el árbol es el mínimo posible. Si el grafo no es conexo, entonces el algoritmo
     * encontrará el árbol recubridor mínimo para uno de los componentes conexos que
     * forman dicho grafo no conexo. Consideremos un mapa de carreteras, con dos tipos de
     * componentes: las ciudades (nodos) y las carreteras que las unen. Cada tramo de
     * carreteras (arco) está señalado con su longitud. Se desea implantar un tendido eléctrico 
     * siguiendo los trazos de las carreteras de manera que conecte todas las ciudades y que
     * la longitud total sea mínima. Emplea una estrategia voraz para resolver el ejercicio.
     */
    public static <E> Grafo<E,Integer> prim(Grafo<E,Integer> g, Vertice<E> v){
        List<Vertice<E>> porVisitar = new LinkedList<>();
        Iterator <Vertice<E>> itr = g.vertices();
        while(itr.hasNext()){
            porVisitar.add(itr.next());
        }
        Grafo<E, Integer> gSolucion = new MapDeMap<>();
        List<Vertice<E>> visitados = new LinkedList<>();
        porVisitar.remove(v);
        visitados.add(v);
        
        while(!porVisitar.isEmpty()){
            Arco<E, Integer> arco = buscarArcoPrim(porVisitar, visitados, g.arcos());
            gSolucion.insertarArco(arco);
            porVisitar.remove(arco.getDestino());
            visitados.add(arco.getDestino());
        }
        
        return gSolucion;
    }
    
    private static <E> Arco<E, Integer> buscarArcoPrim(List<Vertice<E>> porVisitar, List<Vertice<E>> visitados, Iterator<Arco<E, Integer>> arcos){
        Arco<E, Integer> arco = null;
        int min = Integer.MAX_VALUE;
        while(arcos.hasNext()){
            Arco<E, Integer> arcoActual = arcos.next();
            if(porVisitar.contains(arcoActual.getDestino()) && visitados.contains(arcoActual.getOrigen()) && min > arcoActual.getEtiqueta()){
                arco = arcoActual;
                min = arcoActual.getEtiqueta();
            }
        }
        return arco;
    }
    
    /**
     * Ejercicio 3:
     * 
     * Escribe el algoritmo de Dijkstra, también llamado algoritmo de caminos mínimos,
     * es un algoritmo para la determinación del camino más corto dado un vértice origen al
     * resto de vértices en un grafo dirigido y con pesos en cada arista.
     * La idea subyacente en este algoritmo consiste en ir explorando todos los caminos más
     * cortos que parten del vértice origen y que llevan a los demás vértices; cuando se
     * obtiene el camino más corto desde el vértice origen, al resto de vértices que
     * componen el grafo, el algoritmo se detiene. Emplea una estrategia voraz para
     * resolver el ejercicio.
     */
    public static <E> Map<Vertice<E>,Integer> dijkstra(Grafo<E, Integer> g, Vertice<E> v){
        List<Vertice<E>> porVisitar = new LinkedList<>();
        Iterator<Vertice<E>> itr = g.vertices();
        while(itr.hasNext()){
            porVisitar.add(itr.next());
        }
        Map<Vertice<E>, Integer> distancia = new HashMap<>();
        final Integer INFINITO = Integer.MAX_VALUE;
        for(Vertice<E> vertice: porVisitar){
            distancia.put(vertice, INFINITO);
        }
        distancia.put(v, 0);
        
        while(!porVisitar.isEmpty()){
            Vertice<E> vMinimo = distanciaMinima(distancia, porVisitar);
            porVisitar.remove(vMinimo);
        
            //Obtengo los arcos
            if(!distancia.get(vMinimo).equals(INFINITO)){
                Iterator<Arco<E, Integer>> itrArco = g.arcos();
                while(itrArco.hasNext()){
                    Arco<E, Integer> arco = itrArco.next();
                    if(arco.getOrigen().equals(vMinimo) && porVisitar.contains(arco.getDestino())){
                        Integer distanciaArco = arco.getEtiqueta();
                        Integer distanciaVertice = distancia.get(vMinimo);
                        if((distanciaArco + distanciaVertice) < distancia.get(arco.getDestino())){
                            distancia.put(arco.getDestino(), distanciaArco + distanciaVertice);
                        }
                    }                            
                }
            }
        }
        return distancia;
    }
    
    private static <E> Vertice<E> distanciaMinima(Map<Vertice<E>, Integer> distancia, List<Vertice<E>> porVisitar){
        Vertice<E> vMinimo = null;
        Integer distMinima = Integer.MAX_VALUE;
        for(Vertice<E> v: porVisitar){
            if(vMinimo == null || distancia.get(v) < distMinima){
                vMinimo = v;
                distMinima = distancia.get(v);
            }
        }
        return vMinimo;
    }
    
    /**
     * Ejercicio 4:
     * 
     * El algoritmo de número cromático indica el número de colores mínimo necesario
     * para colorear los vértices de un grafo. El teorema de los cuatro colores justifica que
     * el número cromático de un grafo plano es menor o igual que cuatro.
     * El objetivo de este ejercicio consiste en, dado un grafo plano que representa un mapa
     * político, indicar cómo deben colorearse cada uno de los vértices de dicho grafo,
     * empleando el menor número posible de colores (según el teorema citado
     * anteriormente como máximo cuatro). Emplea una estrategia voraz para resolver el
     * ejercicio.
     */
    public static <E> Map<Vertice<E>,String> colorearMapa (Grafo<E, Integer> g, String [] colores){
        Map<Vertice<E>, String> mapaColores = new HashMap<>();
        Iterator<Vertice<E>> itCandidatos = g.vertices();
        
        while(itCandidatos.hasNext()){
            Vertice<E> verticeActual = itCandidatos.next();
            String color = seleccionarColor(mapaColores, verticeActual, g, colores);
            mapaColores.put(verticeActual, color);
        }
        return mapaColores;
    }
    
    private static <E> String seleccionarColor(Map<Vertice<E>, String> mapaColores, Vertice<E> verticeActual, Grafo<E, Integer> g, String[] colores){
        int i = 0;
        String color = "";
        boolean encontrado = false;
        while(i < colores.length && !encontrado){
            color = colores[i];
            encontrado = true;
            Iterator<Vertice<E>> itAdy = g.adyacentes(verticeActual);
            while(itAdy.hasNext() && encontrado){
                Vertice<E> adyacente = itAdy.next();
                if(mapaColores.get(adyacente) != null && mapaColores.get(adyacente).equals(color)){
                    encontrado = false;
                    i++;
                }
            }
        }
        return color;
    }
    
    /**
     * Ejercicio 5:
     * 
     * Supongamos que sólo están disponibles los siguientes billetes y monedas: 100, 50,
     * 20, 10, 5 y 1 euro (con un número limitado de cada tipo de moneda). Nuestro
     * problema consiste en diseñar un algoritmo para pagar una cierta cantidad a un cliente,
     * utilizando el menor número posible de billetes y monedas. Por ejemplo, si tenemos
     * que pagar 289 euros y tenemos 5 billetes y monedas de cada tipo, la mejor solución
     * consiste en dar al cliente los siguientes billetes/monedas: 2 de 100, 1 de 50, 1 de 20, 1
     * de 10, 1 de 5 y 4 de 1 euro. Implementa este algoritmo siguiendo una estrategia
     * voraz. 
     */
    public static Map<Integer, Integer> darCambio(int importeDevolver, Map<Integer, Integer> mapCanti){
        Map<Integer, Integer> cantidad = new HashMap<>();
        while(importeDevolver != 0){
            Integer valor = seleccionarCambio(importeDevolver, mapCanti);
            mapCanti.put(valor, mapCanti.get(valor) - 1);
            if(cantidad.get(valor) == null){
                cantidad.put(valor, 1);
            }else{
                cantidad.put(valor, cantidad.get(valor) + 1);
            }
            importeDevolver -= valor;
        }
        return cantidad;
    }
    
    private static Integer seleccionarCambio(int importeDevolver, Map<Integer, Integer> mapCanti){
        Integer seleccion = 0;
        Iterator<Integer> itBilletes = mapCanti.keySet().iterator();
        while(itBilletes.hasNext()){
            Integer actual = itBilletes.next();
            if((actual > seleccion) && (actual <= importeDevolver) && mapCanti.get(actual) > 0){
                seleccion = actual;
            }
        }
        return seleccion;
    }
    
    /**
     * Ejercicio 6: 
     * 
     * Considere que tenemos n programas distintos para grabar en un disco, pero el espacio
     * de memoria que necesitan excede la capacidad del disco. Cada programa Pi requiere
     * mi kilobytes de memoria, la capacidad del disco es de C kilobytes y C es menor que la
     * suma del espacio necesario para almacenar todos los programas.
     * Diseñe un algoritmo, utilizando un esquema voraz, que calcule una colección de
     * estos programas para grabar en el disco, de manera que se utilice la máxima
     * capacidad posible del disco en la grabación de estos programas
     */
    public static List<String> llenarCD(int capacidadMaxima, Map<String, Integer> espacioProgramas){
        List<String> lista = new LinkedList<>();
        while(!espacioProgramas.esVacio() && capacidadMaxima > 0){
            String seleccion = seleccionarCD(capacidadMaxima, espacioProgramas);
            lista.add(seleccion);
            capacidadMaxima -= espacioProgramas.get(seleccion);
            espacioProgramas.remove(seleccion);
        }
        return lista;
    }
    
    private static String seleccionarCD(int capacidadMaxima, Map<String, Integer> espacioProgramas){
        String seleccion = "";
        Integer tamMax = 0;
        Iterator<String> itClaves = espacioProgramas.keySet().iterator();
        while(itClaves.hasNext()){
            String actual = itClaves.next();
            if(espacioProgramas.get(actual) <= capacidadMaxima && espacioProgramas.get(actual) > tamMax){
                seleccion = actual;
                tamMax = espacioProgramas.get(actual);
            }
        }
        return seleccion;
    }

    /**
     * Ejercicio 7:
     * 
     * Problema de la mochila. Se desea llenar una mochila hasta un volumen máximo V, y
     * para ello se dispone de n objetos, en cantidades limitadas c1, ..., cn y cuyos valores por
     * unidad de volumen son v1, ..., vn, respectivamente. Debe seleccionarse de cada objeto
     * una cantidad máxima ki con tal de que ki * vi <= Volumen que resta para llenar la
     * mochila. El problema consiste en determinar las cantidades k1, ..., kn que llenan la
     * mochila maximizando el valor total Σ vi * ki , i є {1, …, n}. Emplea una estrategia
     * voraz para resolver el ejercicio.
     */
    public static Map<String, Integer> llenarMochila (int volumenMaximo, Map<String, Integer> cantidades, Map<String,Integer> volumenes){
        Map<String, Integer> mochila = new HashMap<>();
        boolean continuar = true;
        while(volumenMaximo > 0 && continuar){
            String seleccion = seleccionarVolumen(volumenMaximo, cantidades, volumenes);
            if(seleccion == null){
                continuar = false;
            }else{
                if(mochila.get(seleccion) == null){                
                    mochila.put(seleccion, 1);
                }else{
                    mochila.put(seleccion, mochila.get(seleccion) + 1);
                }
                volumenMaximo -= volumenes.get(seleccion);
                cantidades.put(seleccion, cantidades.get(seleccion) - 1);   
            }
            
        }
        return mochila;
    }
    
    private static String seleccionarVolumen(int volumenMaximo, Map<String, Integer> cantidades, Map<String, Integer> volumenes){
        String seleccion = null;
        Integer volMax = 0;
        Iterator<String> itClaves = cantidades.keySet().iterator();
        while(itClaves.hasNext()){
            String actual = itClaves.next();
            if(cantidades.get(actual) > 0 && volumenes.get(actual) <= volumenMaximo && volumenes.get(actual) > volMax){
                seleccion = actual;
            }
        }
        return seleccion;
    }
    
    /**
     * Ejercicio 8:
     * 
     * Dado un grafo con las asignaturas (vértices) y las conexiones entre ellas (arcos), las
     * cuales indican que algún profesor imparte ambas asignaturas, implementa el método
     * que se describe a continuación para establecer los días de examen de dichas
     * asignaturas. El método devuelve un mapa con las asignaturas y el día de la semana en
     * el que se realizará la prueba de dicha asignatura. Los exámenes se realizarán en una
     * semana (de lunes a viernes de 10:00 a 14:00).
     * El objetivo es minimizar el número de días para la realización de todas las pruebas,
     * garantizando que un profesor no puede examinar en diferentes asignaturas el mismo
     * día; para lo cual se debe emplear una estrategia voraz.
     */
    public static <T> Map<Vertice<String>, String> horarioExamenes (Grafo<String, T> g, String [] diasSemana){
        List<Vertice<String>> porVisitar = new LinkedList<>();
        Iterator<Vertice<String>> itAsig = g.vertices();
        while(itAsig.hasNext()){
            porVisitar.add(itAsig.next());
        }
        Map<Vertice<String>, String> horario = new HashMap<>();
        
        while(!porVisitar.isEmpty()){
            Vertice<String> asignatura = porVisitar.get(0);
            int pos = seleccionarDia(asignatura, horario, g, diasSemana);
            porVisitar.remove(asignatura);
            horario.put(asignatura, diasSemana[pos]);
        }
        return horario;
    }
    
    private static <T> int seleccionarDia(Vertice<String> asignatura, Map<Vertice<String>,String> horario, Grafo<String, T> g, String[] diasSemana){
        int pos = 0;
        boolean encontrado = false;
        while(!encontrado){
            encontrado = true;
            Iterator<Vertice<String>> itAdy = g.adyacentes(asignatura);
            while(itAdy.hasNext() && encontrado){
                Vertice<String> actual = itAdy.next();
                if(horario.get(actual) != null && horario.get(actual).equals(diasSemana[pos])){
                    encontrado = false; 
                    pos++;
                }
            }
        }
        return pos;
    }
}
