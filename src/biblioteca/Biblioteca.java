package biblioteca;

import estructuras.avl.AVL;
import estructuras.avl.NodoAVL;
import estructuras.pila.Pila;
import estructuras.cola.Cola;
import estructuras.led.ListaEnlazadaDoble;
import modelo.Libro;

public class Biblioteca {
  private AVL<Libro> catalogo;
  private ListaEnlazadaDoble<Libro> estanteria;
  private Pila<Libro> librosDevueltos;
  private Cola<Libro> librosEnEspera;

  public Biblioteca() {
    catalogo = new AVL<>();
    estanteria = new ListaEnlazadaDoble<>();
    librosDevueltos = new Pila<>();
    librosEnEspera = new Cola<>(100);
  }

  /**
   * Agrega un libro al catálogo y lo coloca en la estantería.
   */
  public void agregarLibro(int id, String titulo, String autor) {
    Libro nuevoLibro = new Libro(id, titulo, autor);
    catalogo.insertar(id, nuevoLibro);
    estanteria.insertarAlFinal(nuevoLibro);
    System.out.println("Libro agregado: " + titulo);
  }

  /**
   * Presta un libro si está disponible en la estantería.
   * Si no está, lo coloca en la lista de espera.
   */
  public void prestarLibro(int id) {
    NodoAVL<Libro> nodo = catalogo.buscar(id); // Ahora sí existe este método
    if (nodo != null) {
      Libro libro = nodo.getValor();
      if (estanteria.eliminar(libro)) {
        System.out.println("Libro prestado: " + libro.getTitulo());
      } else {
        if (!librosEnEspera.contiene(libro)) {
          librosEnEspera.enqueue(libro);
          System.out.println("Libro en espera: " + libro.getTitulo());
        } else {
          System.out.println("Este libro ya está en la lista de espera.");
        }

      }
    } else {
      System.out.println("Libro no encontrado en el catálogo.");
    }
  }

  /**
   * Recibe un libro devuelto y lo coloca en la pila de devoluciones.
   */
  public void devolverLibro(int id) {
    NodoAVL<Libro> nodo = catalogo.buscar(id);
    if (nodo != null) {
      Libro libro = nodo.getValor();
      librosDevueltos.apilar(libro);
      System.out.println("Libro devuelto: " + libro.getTitulo());
    } else {
      System.out.println("Libro no encontrado en el catálogo.");
    }
  }

  /**
   * Procesa todos los libros devueltos y los coloca en la estantería.
   */
  public void procesarDevoluciones() {
    if (librosDevueltos.estaVacia()) {
      System.out.println("No hay libros devueltos por procesar.");
      return;
    }
    while (!librosDevueltos.estaVacia()) {
      Libro libro = librosDevueltos.desapilar();
      estanteria.insertarAlFinal(libro);
      System.out.println("Libro colocado en la estantería: " + libro.getTitulo());
    }
  }

  /**
   * Atiende la lista de espera, colocando el libro disponible en la estantería.
   */
  public void atenderEspera() {
    if (!librosEnEspera.estaVacia()) {
      Libro libro = librosEnEspera.dequeue();
      estanteria.insertarAlFinal(libro);
      System.out.println("Libro entregado a quien lo esperaba: " + libro.getTitulo());
    } else {
      System.out.println("No hay libros en espera.");
    }
  }

  /**
   * Método para mostrar el estado actual de la biblioteca, incluyendo
   * los libros en la estantería, los libros devueltos (pila) y
   * los libros en espera (cola).
   */
  public void mostrarEstadoBiblioteca() {
    System.out.println("\n--- Estado de la Biblioteca ---");

    // Mostrar la estantería
    System.out.println("Estantería:");
    if (estanteria.estaVacia()) {
      System.out.println("   La estantería está vacía.");
    } else {
      estanteria.recorrer(); // Asumiendo que ListaEnlazadaDoble tiene un método mostrar()
    }

    // Mostrar la pila de libros devueltos
    System.out.println("\nLibros Devueltos (Pila):");
    if (librosDevueltos.estaVacia()) {
      System.out.println("   No hay libros devueltos.");
    } else {
      System.out.println("   " + librosDevueltos.recorrer());
    }

    // Mostrar la cola de libros en espera
    System.out.println("\nLibros en Espera (Cola):");
    if (librosEnEspera.estaVacia()) {
      System.out.println("   No hay libros en espera.");
    } else {
      System.out.print("   ");
      for (int i = 0; i < librosEnEspera.getTamanio(); i++) {
        Libro libro = librosEnEspera.dequeue(); // Sacamos el libro
        System.out.print(libro.getTitulo());
        if (i < librosEnEspera.getTamanio()) {
          System.out.print(" -> ");
        }
        librosEnEspera.enqueue(libro); // Lo volvemos a insertar para mantener la cola
      }
      System.out.println();
    }
    System.out.println("\n--- ----------------- ---");
  }

}
