package estructuras.led;

/**
 * Clase interna que representa un nodo en la lista doblemente enlazada.
 * 
 * @param <Tipo> Tipo de dato almacenado en el nodo.
 */
class NodoLED<Tipo> {
  Tipo dato;
  NodoLED<Tipo> siguiente;
  NodoLED<Tipo> previo;

  /**
   * Constructor para inicializar un nodo con un dato.
   * 
   * @param dato El dato a almacenar en el nodo.
   */
  public NodoLED(Tipo dato) {
    this.dato = dato;
    this.siguiente = null;
    this.previo = null;
  }
}
