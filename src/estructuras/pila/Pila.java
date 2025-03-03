package estructuras.pila;

/**
 *
 * @author mike
 */
public class Pila<Tipo> {
  private NodoP<Tipo> cima;

  /**
   * Constructor para inicializar una pila vacía.
   */
  public Pila() {
    cima = null;
  }

  /**
   * Método para apilar un elemento en la cima de la pila.
   * 
   * @param dato El elemento a apilar.
   */
  public void apilar(Tipo dato) {
    NodoP<Tipo> nuevoNodo = new NodoP<>(dato);
    nuevoNodo.siguiente = cima;
    cima = nuevoNodo;
  }

  /**
   * Método para desapilar un elemento de la pila.
   * 
   * @return El elemento desapilado.
   * @throws IllegalStateException si la pila está vacía.
   */
  public Tipo desapilar() {
    if (cima == null)
      throw new IllegalStateException("¡Pila vacía!");
    Tipo dato = cima.dato;
    cima = cima.siguiente;
    return dato;
  }

  /**
   * Método para mirar el elemento en la cima de la pila sin eliminarlo.
   * 
   * @return El elemento en la cima de la pila.
   * @throws IllegalStateException si la pila está vacía.
   */
  public Tipo mirar() {
    if (cima == null)
      throw new IllegalStateException("¡Pila vacía!");
    return cima.dato;
  }

  /**
   * Método para verificar si la pila está vacía.
   * 
   * @return true si la pila está vacía, false en caso contrario.
   */
  public boolean estaVacia() {
    return cima == null;
  }

  /**
   * Método para recorrer la pila y obtener sus elementos en orden.
   * 
   * @return Un String con los elementos de la pila desde la cima hasta la base.
   */
  public String recorrer() {
    if (estaVacia()) {
      return "La pila está vacía.";
    }
    StringBuilder resultado = new StringBuilder();
    NodoP<Tipo> actual = cima;
    while (actual != null) {
      resultado.append(actual.dato.toString());
      if (actual.siguiente != null) {
        resultado.append(" -> ");
      }
      actual = actual.siguiente;
    }
    return resultado.toString();
  }

}