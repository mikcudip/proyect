package estructuras.led;

/**
 *
 * @author mike
 */
public class ListaEnlazadaDoble<Tipo> {
  private NodoLED<Tipo> inicio;
  private NodoLED<Tipo> fin;

  /**
   * Constructor que inicializa una lista vacía.
   */
  public ListaEnlazadaDoble() {
    inicio = null;
    fin = null;
  }

  /**
   * Método para insertar un elemento al final de la lista.
   * 
   * @param valor Elemento a insertar.
   */
  public void insertarAlFinal(Tipo valor) {
    NodoLED<Tipo> nuevo = new NodoLED<>(valor);
    if (inicio == null) {
      inicio = nuevo;
      fin = nuevo;
    } else {
      nuevo.previo = fin;
      fin.siguiente = nuevo;
      fin = nuevo;
    }
  }

  /**
   * Método para insertar un elemento al inicio de la lista.
   * 
   * @param valor Elemento a insertar.
   */
  public void insertarAlinicio(Tipo valor) {
    NodoLED<Tipo> nuevo = new NodoLED<>(valor);
    if (inicio == null) {
      inicio = nuevo;
      fin = nuevo;
    } else {
      nuevo.siguiente = inicio;
      inicio.previo = nuevo;
      inicio = nuevo;
    }
  }

  /**
   * Método para mostrar los elementos de la lista en orden.
   */
  public void mostrar() {
    NodoLED<Tipo> actual = inicio;
    while (actual != null) {
      System.out.print(actual.dato + " ");
      actual = actual.siguiente;
    }
    System.out.println();
  }

  /**
   * Método para insertar un elemento en una posición específica de la lista.
   * 
   * @param dato     Elemento a insertar.
   * @param posicion Posición en la que se insertará el elemento (0-based).
   * @throws Exception si la posición es inválida.
   */
  public void insertarPorPosicion(Tipo dato, int posicion) throws Exception {
    if (posicion < 0) {
      throw new Exception("Posición inválida");
    }
    if (posicion == 0) {
      insertarAlinicio(dato);
    } else {
      NodoLED<Tipo> nuevo = new NodoLED<>(dato);
      NodoLED<Tipo> actual = inicio;
      int contador = 0;
      while (actual != null && contador < posicion - 1) {
        actual = actual.siguiente;
        contador++;
      }
      if (actual == null) {
        throw new Exception("Posición inválida");
      }
      nuevo.siguiente = actual.siguiente;
      nuevo.previo = actual;
      if (actual.siguiente != null) {
        actual.siguiente.previo = nuevo;
      }
      actual.siguiente = nuevo;
    }
  }

  /**
   * Método para eliminar un elemento de la lista doblemente enlazada.
   * 
   * @param dato Elemento a eliminar.
   * @return true si se eliminó, false si no se encontró.
   */
  public boolean eliminar(Tipo dato) {
    if (inicio == null) {
      return false; // La lista está vacía
    }

    NodoLED<Tipo> actual = inicio;

    while (actual != null) {
      if (actual.dato.equals(dato)) { // Se encontró el nodo a eliminar
        // Si es el primer nodo
        if (actual == inicio) {
          inicio = actual.siguiente;
          if (inicio != null) {
            inicio.previo = null;
          } else {
            fin = null; // La lista quedó vacía
          }
        }
        // Si es el último nodo
        else if (actual == fin) {
          fin = actual.previo;
          fin.siguiente = null;
        }
        // Si está en medio
        else {
          actual.previo.siguiente = actual.siguiente;
          actual.siguiente.previo = actual.previo;
        }

        return true; // Eliminación exitosa
      }
      actual = actual.siguiente;
    }

    return false; // No se encontró el elemento en la lista
  }

}