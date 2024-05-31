package Ejercicios.EsquemasAlgoritmicos.DivideVenceras;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ruben Blanco
 */
public class EsquemaDivideVenceras {

    /**
     * Ejercicio 1:
     *
     * Un problema que sigue la estrategia divide y vencerás es encontrar el
     * k-ésimo menor elemento de un array. Obviamente, se puede realizar
     * ordenando el array, pero es de esperar que selección sea un proceso más
     * rápido, al solicitarse menor información. Haciendo un pequeño cambio en
     * el algoritmo quicksort, se puede resolver el problema de selección en
     * tiempo lineal, en promedio. Llamamos a este algoritmo selección rápida.
     * Los pasos a realizar son los siguientes:
     *
     */
    public static int SeleccionRapida(int[] array, int k_menor, int i, int j) {
        int indicePivote = buscaPivote(array, i, j);
        if (indicePivote != -1) {
            int pivote = array[indicePivote];
            System.out.println(Arrays.toString(array));
            intercambiar(array, indicePivote, j);
            System.out.println(Arrays.toString(array));
            System.out.println("Pivote: " + pivote);
            int particion = particion(array, i, j, pivote);
            if (k_menor <= particion) {
                return SeleccionRapida(array, k_menor, i, particion - 1);
            } else if (k_menor == particion + 1) {
                return pivote;
            } else {
                return SeleccionRapida(array, k_menor, particion, j);
            }
        } else {
            return array[i]; //Todos iguales o solo hay 1 elemento
        }
    }

    // Selecciona un elemento pivote: entre dos elementos diferentes devuelve el elemento mayor.
    private static int buscaPivote(int[] aux, int inicio, int fin) {
        int primer = aux[inicio];
        int k = inicio + 1;
        while (k <= fin) {
            if (aux[k] > primer) {
                return k;
            } else if (aux[k] < primer) {
                return inicio;
            } else {
                k++;
            }
        }
        return -1;
    }

    //Divide el array en menores que pivote, y mayores e igual a pivote
    //Devuelve la posición del primer elemento de los mayores o iguales a pivote (es decir el número de
    //elementos que hay en la partición de la izquierda).
    private static int particion(int[] aux, int inicio, int fin, int pivote) {
        int derecha = inicio;
        int izquierda = fin - 1;
        while (derecha <= izquierda) {
            while (aux[derecha] < pivote) {
                derecha++;
            }
            while (aux[izquierda] >= pivote) {
                izquierda--;
            }
            if (derecha < izquierda) {
                intercambiar(aux, derecha, izquierda);
            }
        }

        return derecha;
    }

    private static void intercambiar(int[] aux, int i, int j) {
        int temp = aux[i];
        aux[i] = aux[j];
        aux[j] = temp;
    }

    /**
     * Ejercicio 2:
     *
     * El de las Torres de Hanoi es un juego matemático consistente en mover
     * unos discos de una torre a otra. La leyenda cuenta que existe un templo
     * (llamado Benares), bajo la bóveda que marca el centro del mundo, donde
     * hay tres varillas de diamante creadas por Dios al crear el mundo,
     * colocando 64 discos de oro en la primera de ellas. Unos monjes mueven los
     * discos a razón de uno por día, y, el día en que tengan todos los discos
     * en la tercera varilla, el mundo terminará. Como se comprobará a
     * continuación, en realidad 64 discos son suficientes para muchos años. En
     * este juego, se trata de pasar un número de discos (típicamente, con tres
     * existe una dificultad suficiente como para plantearlo como un
     * pasatiempo), de un poste de origen (el primero, más a la izquierda) a un
     * poste de destino (el tercero, a la derecha), utilizando como poste
     * auxiliar el del medio. Sólo se puede mover un disco de cada vez, y nunca
     * poner un disco sobre un segundo que sea de menor diámetro que el primero.
     * Así, al comienzo del juego todos los discos están apilados en el primero
     * (el de la izquierda), cada disco se asienta sobre otro de mayor diámetro,
     * de manera que, tomados desde la base hacia arriba, su tamaño es
     * decreciente. El objetivo, como ya se ha dicho, es mover uno a uno los
     * discos desde el poste A (origen) al poste C (destino) utilizando el poste
     * B como auxiliar, para lo cual se puede emplear una técnica divide y
     * vencerás, como se explica a continuación.
     */
    /**
     * Ejercicio 3:
     *
     */
    public static int buscarPosK(int[] v, int inicio, int fin) {
        if (inicio >= fin) {
            return -1;
        }
        int medio = (inicio + fin) / 2;
        if (v[medio] < v[medio - 1] && v[medio] < v[medio + 1]) {
            return medio; //encontrado
        } else if (v[medio] < v[medio - 1] && v[medio] > v[medio + 1]) {
            return buscarPosK(v, medio + 1, fin); //busca en la mitad derecha
        } else {
            return buscarPosK(v, inicio, medio); //busca en la mitad izquierda
        }
    }

    /**
     * Ejercicio 4:
     *
     *
     */
    public static int inversiones(int[] array, int inicio, int fin) {
        if(inicio == fin){
            return 0; //Caso base
        }
        int medio = (inicio + fin) / 2;
        int inv1 = inversiones(array, inicio, medio);
        int inv2 = inversiones(array, medio + 1, fin);
        int inv3 = mergeCount(array, inicio, medio, medio + 1, fin);
        return inv1 + inv2 + inv3;
    }

    // mezcla y ordena los elementos de las dos mitades y devuelve el número de inversiones entre ellas
    // inicio1, inicio2: primera posición de la primera y segunda mitad respectivamente
    // fin1 y fin2: última posición de la primera y segunda mitad respectivamente.
    private static int mergeCount(int[] aux, int inicio1, int fin1, int inicio2, int fin2) {
        int i = inicio1;
        int j = inicio2;
        int cont = 0;
        int pos = inicio2 - inicio1; //número máximo de inversiones
        ArrayList<Integer> temp = new ArrayList<>();
        while (i <= fin1 && j <= fin2) {
            if (aux[i] <= aux[j]) {
                temp.add(aux[i++]);
                pos--;
            } else {
                temp.add(aux[j++]);
                cont += pos;
            }
        }
        while (i <= fin1) {
            temp.add(aux[i++]);
        }
        while (j <= fin2) {
            temp.add(aux[j++]);
        }
        for (i = inicio1; i <= fin2; i++) {
            aux[i] = temp.remove(0);
        }
        return cont;
    }
}
