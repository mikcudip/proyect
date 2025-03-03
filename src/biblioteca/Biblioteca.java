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
    if (!librosEnEspera.isEmpty()) {
      Libro libro = librosEnEspera.dequeue();
      estanteria.insertarAlFinal(libro);
      System.out.println("Libro entregado a quien lo esperaba: " + libro.getTitulo());
    } else {
      System.out.println("No hay libros en espera.");
    }
  }

  /**
   * Muestra el catálogo en orden ascendente.
   */
  public void mostrarCatalogo() {
    if (catalogo.getRaiz() == null) {
      System.out.println("El catálogo está vacío.");
      return;
    }
    System.out.println("Catálogo de libros (InOrden):");
    catalogo.inOrden(catalogo.getRaiz());
    System.out.println();
  }
}
