package TADs.Map;

/**
 *
 * @author Ruben Blanco
 */
public interface Polinomio {

    public int grado();
        // Produce: Devuelve el grado del polinomio, es decir, el mayor exponente de un
                // término con coeficiente distinto de cero. Devuelve 0 si es el polinomio cero.

    public double getCoeficiente(int exponente) throws IllegalArgumentException;
        //Produce: si el exponente es negativo lanza la excepción IllegalArgumentException;
                //en otro caso, devuelve el coeficiente del término con el exponente que se pasa como
                //parámetro. Si el término no existe, devuelve 0.

    public void añadirTermino(int exponente, double coeficiente) throws IllegalArgumentException;
        // Modifica: this
        // Produce: si el exponente es negativo o el coeficiente es 0 lanza la excepción
                // IllegalArgumentException; en otro caso, añade el termino (coeficiente x exponente) a this

    public void eliminarTermino(int exponente) throws IllegalArgumentException;
        // Modifica: this
        // Produce: si el exponente es negativo lanza la excepción IllegalArgumentException
                // en otro caso, elimina el término con el exponente que se pasa como parámetro

    public Polinomio suma(Polinomio p) throws NullPointerException;
        // Produce: si p == null lanza NullPointerException; en otro caso, crea un nuevo
                // polinomio que es la suma de los polinomios p y this

    public Polinomio derivada();
        // Produce: Devuelve la derivada del polinomio
}
