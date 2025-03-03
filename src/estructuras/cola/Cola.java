package estructuras.cola;

/**
 *
 * @author mike
 */
public class Cola<Tipo> {
  private int capacidad;
  private int tamanio;
  private Tipo[] cola;
  private int frente;
  private int finalCola;

  /**
   * Constructor para inicializar la cola con una capacidad específica
   * 
   * @param capacidad La capacidad máxima de la cola
   */
  @SuppressWarnings("unchecked") // Para evitar advertencias de la conversion potencialmente insegura
  public Cola(int capacidad) {
    this.capacidad = capacidad;
    cola = (Tipo[]) new Object[capacidad]; // Creación de un array genérico
    frente = 0;
    finalCola = -1;
    tamanio = 0;
  }

  /**
   * Método para mostrar el elemento del frente, sin eliminarlo
   * 
   * @return el elemento del frente
   * @throws IllegalStateException si la cola está vacía
   */
  public Tipo peek() {
    if (this.isEmpty()) {
      throw new IllegalStateException("Cola vacía");
    }
    return cola[frente];
  }

  /**
   * Método para eliminar un elemento de la cola
   * 
   * @return el elemento eliminado
   * @throws IllegalStateException si la cola está vacía
   */
  public Tipo dequeue() {
    if (this.isEmpty()) {
      throw new IllegalStateException("Cola vacía");
    }
    Tipo elemento = cola[frente];
    frente = (frente + 1) % capacidad; // Siguiente de último es el primero
    tamanio--;
    return elemento;
  }

  /**
   * Método para insertar un elemento en la cola
   * 
   * @param elemento el elemento a insertar
   * @throws IllegalStateException si la cola está llena
   */
  public void enqueue(Tipo elemento) {
    if (this.isFull()) {
      throw new IllegalStateException("Cola llena");
    }
    finalCola = (finalCola + 1) % capacidad; // Siguiente de último es el primero
    cola[finalCola] = elemento;
    tamanio++;
  }

  /**
   * Método para verificar si la cola está vacía
   * 
   * @return true si la cola está vacía, falso en caso contrario
   */
  public boolean isEmpty() {
    return tamanio == 0;
  }

  /**
   * Método para verificar si la cola está llena
   * 
   * @return true si la cola está llena, falso en caso contrario
   */
  public boolean isFull() {
    return tamanio == capacidad;
  }

  /**
   * Método para verificar si un elemento está en la cola.
   * 
   * @param elemento El elemento a buscar.
   * @return true si el elemento está en la cola, false en caso contrario.
   */
  public boolean contiene(Tipo elemento) {
    for (int i = 0; i < tamanio; i++) {
      int indice = (frente + i) % capacidad; // Índice circular
      if (cola[indice].equals(elemento)) {
        return true;
      }
    }
    return false;
  }
}
