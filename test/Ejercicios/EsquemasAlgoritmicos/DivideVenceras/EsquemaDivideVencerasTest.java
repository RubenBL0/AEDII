package Ejercicios.EsquemasAlgoritmicos.DivideVenceras;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruben Blanco
 */
public class EsquemaDivideVencerasTest {
    
    public EsquemaDivideVencerasTest() {
    }

    private static int[] selecRapi = {4,2,8,12,6,9,23,43,78,14};
    private static int [] buscaPos = {9,7,5,4,3,2,1,3,4,7};
    private static int [] inver = {1,5,4,8,10,2,6,9,12,11,3,7};
    private static int [] existeN={-3,0,1,2,4,12,15};

     

    /**
     * Test of SeleccionRapida method, of class Esquemas.
     */
    @Test
    public void testSeleccionRapida() {
        System.out.println("SeleccionRapida");
        int sol = EsquemaDivideVenceras.SeleccionRapida(selecRapi,5,0,selecRapi.length-1);

        assertEquals(9,sol); 
    }

//    /**
//     * Test of SeleccionRapida method, of class Esquemas.
//     */
//    @Test
//    public void testTorresHanoi() {
//        System.out.println("TorresHanoi");
//        EsquemaDivideVenceras.moverDiscos(3,"A","B","C");
//    }
//
    /**
     * Test of buscaPosK method, of class Esquemas.
     */
    @Test
    public void testBuscaPosK() {
        System.out.println("Busca Posición K");
        int sol = EsquemaDivideVenceras.buscarPosK(buscaPos, 0,buscaPos.length-1);

        assertEquals(6, sol); 
    }

    /**
     * Test of SeleccionRapida method, of class Esquemas.
     */
    @Test
    public void testInversiones() {
        System.out.println("inversiones");
        int sol = EsquemaDivideVenceras.inversiones(inver, 0, inver.length - 1);

        assertEquals(sol, 22);
    }
    
}
