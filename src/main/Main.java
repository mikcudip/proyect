package main;

import biblioteca.Biblioteca;

public class Main {
  public static void main(String[] args) {
    Biblioteca biblioteca = new Biblioteca();

    // Agregar varios libros al catálogo y a la estantería
    biblioteca.agregarLibro(1, "1984", "George Orwell"); // Libro agregado: 1984
    biblioteca.agregarLibro(2, "Cien años de soledad", "Gabriel García Márquez"); // Libro agregado: Cien años de
                                                                                  // soledad
    biblioteca.agregarLibro(3, "El principito", "Antoine de Saint-Exupéry"); // Libro agregado: El principito
    biblioteca.agregarLibro(4, "Don Quijote de la Mancha", "Miguel de Cervantes"); // Libro agregado: Don Quijote de la
                                                                                   // Mancha
    biblioteca.agregarLibro(5, "Matar a un ruiseñor", "Harper Lee"); // Libro agregado: Matar a un ruiseñor
    biblioteca.agregarLibro(6, "Orgullo y prejuicio", "Jane Austen"); // Libro agregado: Orgullo y prejuicio
    biblioteca.agregarLibro(7, "Fahrenheit 451", "Ray Bradbury"); // Libro agregado: Fahrenheit 451

    // Mostrar catálogo de libros
    System.out.println("Catalogo de Libros:");
    biblioteca.mostrarCatalogo();
    // Catálogo de libros (InOrden):
    // 1 - 1984
    // 2 - Cien años de soledad
    // 3 - El principito
    // 4 - Don Quijote de la Mancha
    // 5 - Matar a un ruiseñor
    // 6 - Orgullo y prejuicio
    // 7 - Fahrenheit 451

    // Prestar varios libros
    biblioteca.prestarLibro(2); // Libro prestado: Cien años de soledad
    biblioteca.prestarLibro(4); // Libro prestado: Don Quijote de la Mancha
    biblioteca.prestarLibro(6); // Libro prestado: Orgullo y prejuicio

    // Intentar prestar un libro ya prestado (se pone en espera)
    System.out.println("Libros en espera debido a que ya fueron prestados:");
    biblioteca.prestarLibro(2); // Libro en espera: Cien años de soledad
    biblioteca.prestarLibro(6); // Libro en espera: Orgullo y prejuicio

    // Devolver algunos libros
    biblioteca.devolverLibro(2); // Libro devuelto: Cien años de soledad
    biblioteca.devolverLibro(6); // Libro devuelto: Orgullo y prejuicio

    // Procesar devoluciones (deben volver a la estantería)
    biblioteca.procesarDevoluciones();
    // Libro colocado en la estantería: Orgullo y prejuicio
    // Libro colocado en la estantería: Cien años de soledad

    // Atender la lista de espera (los libros en espera se entregan)
    biblioteca.atenderEspera(); // Libro entregado a quien lo esperaba: Cien años de soledad
    biblioteca.atenderEspera(); // Libro entregado a quien lo esperaba: Orgullo y prejuicio

    // Intentar atender cuando no hay más libros en espera
    biblioteca.atenderEspera(); // No hay libros en espera.

    // Prestar todos los libros disponibles
    biblioteca.prestarLibro(1); // Libro prestado: 1984
    biblioteca.prestarLibro(3); // Libro prestado: El principito
    biblioteca.prestarLibro(5); // Libro prestado: Matar a un ruiseñor
    biblioteca.prestarLibro(7); // Libro prestado: Fahrenheit 451

    // Intentar prestar un libro que no existe
    biblioteca.prestarLibro(10); // Libro no encontrado en el catálogo.

    // Intentar devolver un libro que no existe
    biblioteca.devolverLibro(10); // Libro no encontrado en el catálogo.

    // Mostrar catálogo nuevamente
    System.out.println("Catalogo de Libros:");
    biblioteca.mostrarCatalogo();
    // Catálogo de libros (InOrden):
    // 1 - 1984
    // 2 - Cien años de soledad
    // 3 - El principito
    // 4 - Don Quijote de la Mancha
    // 5 - Matar a un ruiseñor
    // 6 - Orgullo y prejuicio
    // 7 - Fahrenheit 451
  }
}
