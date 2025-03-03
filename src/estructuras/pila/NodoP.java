package estructuras.pila;


/**
 * Clase interna que representa un nodo en la estructura de lista enlazada.
 * 
 * @param <Tipo> Tipo de dato almacenado en el nodo.
 */
class NodoP<Tipo> {
  Tipo dato;
  NodoP<Tipo> siguiente;

  /**
   * Constructor para inicializar un nodo con un dato.
   * 
   * @param dato El dato a almacenar en el nodo.
   */
  public NodoP(Tipo dato) {
    this.dato = dato;
    this.siguiente = null;
  }
}
