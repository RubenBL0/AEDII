package Ejercicios.Map;

import TADs.Map.Polinomio;
import TADs.Map.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Ruben Blanco
 */
public class MapPolinomio implements Polinomio {

    private HashMap<Integer, Double> mapa;

    public MapPolinomio() {
        mapa = new HashMap<>();
    }

    @Override
    public int grado() {
        int grado = 0;

        Iterator<Integer> itKeys = mapa.keySet().iterator();
        while (itKeys.hasNext()) {
            Integer key = itKeys.next();
            if (key > grado) {
                grado = key;
            }
        }
        return grado;
    }

    @Override
    public double getCoeficiente(int exponente) throws IllegalArgumentException {
        if (exponente < 0) {
            throw new IllegalArgumentException();
        }

        Double coef = mapa.get(exponente);
        if (coef == null) {
            return 0;
        }
        return coef;
    }

    @Override
    public void añadirTermino(int exponente, double coeficiente) throws IllegalArgumentException {
        if (exponente < 0 || coeficiente == 0) {
            throw new IllegalArgumentException();
        }
        Double c = getCoeficiente(exponente);
        if (c == null) {
            mapa.put(exponente, coeficiente);
        } else {
            if (c + coeficiente == 0) {
                eliminarTermino(exponente);
            } else {
                mapa.put(exponente, c + coeficiente);
            }
        }
    }

    @Override
    public void eliminarTermino(int exponente) throws IllegalArgumentException {
        if (exponente < 0) {
            throw new IllegalArgumentException();
        }
        if (!mapa.esVacio()) {
            mapa.remove(exponente);
        }
    }

    @Override
    public Polinomio suma(Polinomio p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException();
        }

        Iterator<Integer> itKeys = mapa.keySet().iterator();
        while (itKeys.hasNext()) {
            Integer exp = itKeys.next();
            Double coef = mapa.get(exp);
            if (coef != null) {
                p.añadirTermino(exp, coef);
            }
        }
        return p;
    }

    @Override
    public Polinomio derivada() {
        Polinomio derivada = new MapPolinomio();
        Iterator<Integer> itKeys = mapa.keySet().iterator();
        while (itKeys.hasNext()) {
            Integer exp = itKeys.next();
            Double coef = mapa.get(exp);
            if (exp > 0) {
                derivada.añadirTermino(exp - 1, exp * coef);
            }
        }
        return derivada;
    }
}
