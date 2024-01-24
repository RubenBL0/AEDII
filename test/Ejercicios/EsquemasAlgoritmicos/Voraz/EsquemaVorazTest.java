package Ejercicios.EsquemasAlgoritmicos.Voraz;

import TADs.Grafo.*;
import TADs.Map.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruben Blanco
 */
public class EsquemaVorazTest {
    
    private static Grafo<Integer, Integer> g1; 
    private static Grafo<Integer, Integer> g2; 
	
    private static Vertice<Integer> uno = new Vertice<>(1);
    private static Vertice<Integer> dos = new Vertice<>(2);
    private static Vertice<Integer> tres = new Vertice<>(3);
    private static Vertice<Integer> cuatro = new Vertice<>(4);
    private static Vertice<Integer> cinco = new Vertice<>(5);
    private static Vertice<Integer> seis = new Vertice<>(6); 

    private static Map<String,Integer> cantidades;
    private static Map<String,Integer> volumenes;
    private static Map<Integer,Integer> can;
    private static Map<String,Integer> programas;
    private static String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

    private static Grafo<String, Integer> g3; 

    private static Vertice<String> SO = new Vertice<>("SO");
    private static Vertice<String> BD = new Vertice<>("BD");
    private static Vertice<String> AL = new Vertice<>("AL");
    private static Vertice<String> AEDII = new Vertice<>("AEDII");
    private static Vertice<String> EST = new Vertice<>("EST");


    private static void rellenarGrafoG(){
        g1 = new MapDeMap<>();
        g1.insertarArco(new Arco<>(uno,dos,3));
        g1.insertarArco(new Arco<>(uno,seis,5));
        g1.insertarArco(new Arco<>(dos,tres,7));
        g1.insertarArco(new Arco<>(dos,seis,10));
        g1.insertarArco(new Arco<>(seis,tres,8));
        g1.insertarArco(new Arco<>(seis,cuatro,2));
        g1.insertarArco(new Arco<>(tres,cuatro,5));
        g1.insertarArco(new Arco<>(tres,cinco,1));
        g1.insertarArco(new Arco<>(cuatro,cinco,6)); 
        // Añado los arcos en el otro sentido, hace falta para el problema del viajante
        g1.insertarArco(new Arco<>(dos,uno,3));
        g1.insertarArco(new Arco<>(seis,uno,5));
        g1.insertarArco(new Arco<>(tres,dos,7));
        g1.insertarArco(new Arco<>(seis,dos,10));
        g1.insertarArco(new Arco<>(tres,seis,8));
        g1.insertarArco(new Arco<>(cuatro,seis,2));
        g1.insertarArco(new Arco<>(cuatro,tres,5));
        g1.insertarArco(new Arco<>(cinco,tres,1));
        g1.insertarArco(new Arco<>(cinco,cuatro,6));
        
        g2 = new MapDeMap<>();
        g2.insertarArco(new Arco<>(uno,dos,3));
        g2.insertarArco(new Arco<>(uno,seis,5));
        g2.insertarArco(new Arco<>(dos,tres,7));
        g2.insertarArco(new Arco<>(dos,seis,10));
        g2.insertarArco(new Arco<>(seis,tres,8));
        g2.insertarArco(new Arco<>(seis,cuatro,2));
        g2.insertarArco(new Arco<>(tres,cuatro,5));
        g2.insertarArco(new Arco<>(tres,cinco,1));
        g2.insertarArco(new Arco<>(cuatro,cinco,6));
        g2.insertarArco(new Arco<>(dos,cuatro,6));
        // Añado los arcos en el otro sentido
        g2.insertarArco(new Arco<>(dos,uno,3));
        g2.insertarArco(new Arco<>(seis,uno,5));
        g2.insertarArco(new Arco<>(tres,dos,7));
        g2.insertarArco(new Arco<>(seis,dos,10));
        g2.insertarArco(new Arco<>(tres,seis,8));
        g2.insertarArco(new Arco<>(cuatro,seis,2));
        g2.insertarArco(new Arco<>(cuatro,tres,5));
        g2.insertarArco(new Arco<>(cinco,tres,1));
        g2.insertarArco(new Arco<>(cinco,cuatro,6));
        g2.insertarArco(new Arco<>(cuatro,dos,6));
        
        g3 = new MapDeMap<>();
        g3.insertarArco(new Arco<>(SO,AL,0));
        g3.insertarArco(new Arco<>(SO,AEDII,0));
        g3.insertarArco(new Arco<>(SO,EST,0));
        g3.insertarArco(new Arco<>(SO,BD,0));
        g3.insertarArco(new Arco<>(AEDII,BD,0));
        g3.insertarArco(new Arco<>(AL,AEDII,0));
        // Añado los arcos en el otro sentido
        g3.insertarArco(new Arco<>(AL,SO,0));
        g3.insertarArco(new Arco<>(AEDII,SO,0));
        g3.insertarArco(new Arco<>(EST,SO,0));
        g3.insertarArco(new Arco<>(BD,SO,0));
        g3.insertarArco(new Arco<>(BD,AEDII,0));
        g3.insertarArco(new Arco<>(AEDII,AL,0));
    }
    
    private static void rellenarMapas(){
        cantidades = new HashMap<>();	
        cantidades.put("Coche",5);
        cantidades.put("Muñeca",4);

        volumenes = new HashMap<>();
        volumenes.put("Coche",10);
        volumenes.put("Muñeca",20);


        can = new HashMap<>();
        can.put(200,10);
        can.put(500,10);

        programas = new HashMap<>();
        programas.put("p1",10);
        programas.put("p2",3);
        programas.put("p3",5);
        programas.put("p4",3);
        programas.put("p5",3);
    }
    
    private <E,T> boolean grafosIguales(Grafo<E,T> g1, Grafo<E,T> g2){ 
        Iterator<Arco<E,T>> arc = g1.arcos();

        while (arc.hasNext()) {
            Iterator<Arco<E,T>> arcExpected = g2.arcos();
            boolean continuar = true;
            Arco<E,T> a1 = arc.next();
            Vertice<E> w1 = a1.getDestino();
            Vertice<E> v1 = a1.getOrigen();

            while (arcExpected.hasNext() && continuar){
                Arco<E,T> a2 = arcExpected.next();
                Vertice<E> w2 = a1.getDestino();
                Vertice<E> v2 = a1.getOrigen(); 

                if (a1.getEtiqueta().equals(a2.getEtiqueta()) && w1.equals(w2) && v1.equals(v2)) 
                    continuar = false;
                }

            if (continuar) 
                return false;
        }

        // Se comprueba en el otro sentido para garantizar que tienen exactamente los mismos arcos y no más
        arc = g2.arcos();

        while (arc.hasNext()) {
            Iterator<Arco<E,T>> arcActual = g1.arcos();
            boolean continuar = true;
            Arco<E,T> a1 = arc.next();
            Vertice<E> w1 = a1.getDestino();
            Vertice<E> v1 = a1.getOrigen();

            while (arcActual.hasNext() && continuar){
                Arco<E,T> a2 = arcActual.next();
                Vertice<E> w2 = a1.getDestino();
                Vertice<E> v2 = a1.getOrigen(); 

                if (a1.getEtiqueta().equals(a2.getEtiqueta()) && w1.equals(w2) && v1.equals(v2)) 
                    continuar = false;
            }

            if (continuar) 
                return false;
        }
    return true;
    }
    
    private static <E,F> boolean mapasIguales(Map<E,F> m1, Map<E, F> m2){
    if (m1.size() == m2.size()){
        Iterator<E> itr = m1.keySet().iterator();
        while(itr.hasNext()){
            E clave = itr.next();
            if (m1.get(clave) == null || !m1.get(clave).equals(m2.get(clave)))
                return false;
            }
            return true;
    }
    return false;
    }

    private static <E> boolean listasIguales(List<E> m1, List<E> m2){
        if (m1.size() == m2.size()){
            Iterator<E> itr = m1.iterator();
            while(itr.hasNext()){
                E clave = itr.next();
                if (!m2.contains(clave))
                    return false;
                }
                return true;
        }
        return false;
    }
    
    @Before
    public void setUp() throws Exception{
        rellenarGrafoG();
        rellenarMapas();
    }
    /**
     * Test of viajante method, of class EsquemaVoraz.
     */
    @Test
    public void testViajante() {
    System.out.println("viajante");
    Grafo<Integer, Integer> grafoActual = EsquemaVoraz.viajante(g1, uno);
    Grafo<Integer, Integer> grafoEsperado = new MapDeMap<>();

    Vertice<Integer> v1 = new Vertice<>(1);
    Vertice<Integer> v2 = new Vertice<>(2);
    Vertice<Integer> v3 = new Vertice<>(3);
    Vertice<Integer> v4 = new Vertice<>(4);
    Vertice<Integer> v5 = new Vertice<>(5);
    Vertice<Integer> v6 = new Vertice<>(6);

    grafoEsperado.insertarArco(new Arco<>(v1, v2, 3));
    grafoEsperado.insertarArco(new Arco<>(v2, v3, 7));
    grafoEsperado.insertarArco(new Arco<>(v3, v5, 1));
    grafoEsperado.insertarArco(new Arco<>(v5, v4, 6));
    grafoEsperado.insertarArco(new Arco<>(v4, v6, 2));
    
    boolean result = grafosIguales(grafoActual,grafoEsperado);
    assertTrue(result);
    }
    
    /**
     * Test of prim method, of class EsquemaVoraz.
     */
    @Test
    public void testPrim() {
        System.out.println("prim");
        Grafo<Integer, Integer> grafoActual = EsquemaVoraz.prim(g1, seis);
        Grafo<Integer, Integer> grafoEsperado = new MapDeMap<>();

        Vertice<Integer> v1 = new Vertice<>(1);
        Vertice<Integer> v2 = new Vertice<>(2);
        Vertice<Integer> v3 = new Vertice<>(3);
        Vertice<Integer> v4 = new Vertice<>(4);
        Vertice<Integer> v5 = new Vertice<>(5);
        Vertice<Integer> v6 = new Vertice<>(6);

        grafoEsperado.insertarArco(new Arco<>(v6, v4, 2));
        grafoEsperado.insertarArco(new Arco<>(v6, v1, 5));
        grafoEsperado.insertarArco(new Arco<>(v1, v2, 3));
        grafoEsperado.insertarArco(new Arco<>(v4, v3, 5));
        grafoEsperado.insertarArco(new Arco<>(v3, v5, 1));
        
        boolean result = grafosIguales(grafoActual,grafoEsperado);
        assertTrue(result);
    }
    
    /**
     * Test of dijkstra method, of class EsquemaVoraz.
     */
    @Test
    public void testDijkstra() {
        System.out.println("dijkstra");
        Map<Vertice<Integer>, Integer> grafoActual = EsquemaVoraz.dijkstra(g1, uno); 
	Map<Vertice<Integer>, Integer> expResult= new HashMap<>();
        expResult.put(uno, 0);
        expResult.put(dos, 3);
        expResult.put(tres, 10);
        expResult.put(cuatro, 7);
        expResult.put(cinco, 11);
        expResult.put(seis, 5);
        boolean eq = mapasIguales(grafoActual, expResult);
        assertTrue(eq);  
    }  
    
    /**
     * Test of colorearMapa method, of class EsquemaVoraz.
     */
    @Test
    public void colorearMapaTest() {
        System.out.println("colorearMapaTrue");
        String [] colores = {"rojo","azul","verde","amarillo"};
        Map<Vertice<Integer>, String> grafoActual = EsquemaVoraz.colorearMapa(g2,colores);
	Map<Vertice<Integer>, String> expResult = new HashMap<>();
        expResult.put(uno, "rojo");
        expResult.put(tres, "verde");
        expResult.put(dos, "amarillo");
        expResult.put(cuatro, "rojo");
        expResult.put(cinco, "azul");
        expResult.put(seis, "azul");
        boolean eq = mapasIguales(grafoActual, expResult);
        assertTrue(eq);
    }
    @Test
    public void colorearMapaTestFalse() {
        System.out.println("colorearMapaFalse");
        String [] colores = {"rojo","azul","verde","amarillo"};
        Map<Vertice<Integer>, String> grafoActual = EsquemaVoraz.colorearMapa(g2, colores);
	Map<Vertice<Integer>, String> expResult = new HashMap<>();
        expResult.put(uno, "rojo");
        expResult.put(dos, "azul");
        expResult.put(tres, "amarillo");
        expResult.put(cuatro, "rojo");
        expResult.put(cinco, "azul");
        expResult.put(seis, "verde");
        boolean eq = mapasIguales(grafoActual, expResult);
        assertFalse(eq);
    }    
    
    /**
     * Test of darCambio method, of class EsquemaVoraz.
     */
    @Test
    public void testDarCambio() {
        System.out.println("darCambio");
        Map<Integer, Integer> mapaActual = EsquemaVoraz.darCambio(1200, can);
        Map<Integer,Integer> mapaEsperado = new HashMap<>();
        mapaEsperado.put(200,1);
        mapaEsperado.put(500,2);
        
        boolean result = mapasIguales(mapaActual,mapaEsperado);
        
        assertTrue(result);
    }
    
    /**
     * Test of llenarCD method, of class EsquemaVoraz.
     */
    @Test
    public void testLlenarCD() {
        System.out.println("llenarCD");
        int capacidadMaxima = 21;
        List<String> expResult = new LinkedList<>();
        expResult.add("p1");
        expResult.add("p3");
        expResult.add("p2");
        expResult.add("p4");
        List<String> result = EsquemaVoraz.llenarCD(capacidadMaxima, programas);
        
        boolean toRet = listasIguales(expResult,result);
        assertTrue(toRet);
    }
    
    /**
     * Test of llenarMochila method, of class EsquemaVoraz.
     */
    @Test
    public void testLlenarMochila() {
        System.out.println("llenarMochila");
        Map<String,Integer> mapaActual = EsquemaVoraz.llenarMochila(55, cantidades, volumenes);
        
        Map<String,Integer> mapaEsperado = new HashMap<>();
        mapaEsperado.put("Coche",1);
        mapaEsperado.put("Muñeca",2);
        
        boolean result = mapasIguales(mapaActual,mapaEsperado);
        
        assertTrue(result);
    }
    
    /**
     * Test of horarioExamenes, of class EsquemaVoraz.
     */
    @Test
    public void testHorarioExamenes() {
        System.out.println("Horario Exámenes Voraz");
        Map<Vertice<String>, String> mapaActual = EsquemaVoraz.horarioExamenes(g3, diasSemana);
        Map<Vertice<String>,String> mapaEsperado = new HashMap<>();
        mapaEsperado.put(SO,"Martes");
        mapaEsperado.put(BD,"Lunes");
        mapaEsperado.put(EST,"Lunes");
        mapaEsperado.put(AEDII,"Miércoles");
        mapaEsperado.put(AL,"Lunes");
        
        boolean result = mapasIguales(mapaActual,mapaEsperado);
        
        assertTrue(result);
    }
}
 