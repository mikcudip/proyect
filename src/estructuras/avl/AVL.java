package estructuras.avl;

public class AVL<Tipo> {
  NodoAVL<Tipo> raiz;

  public AVL() {
    raiz = null;
  }

  public NodoAVL<Tipo> getRaiz() {
    return raiz;
  }

  public int altura(NodoAVL<Tipo> nodo) {
    if (nodo == null)
      return -1;
    return nodo.altura;
  }

  public int obtenerFE(NodoAVL<Tipo> nodo) {
    if (nodo == null)
      return 0;
    return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
  }

  public NodoAVL<Tipo> rotarDerecha(NodoAVL<Tipo> nodoRaiz) {
    NodoAVL<Tipo> nuevaRaiz = nodoRaiz.getIzquierdo();
    NodoAVL<Tipo> temp = nuevaRaiz.getDerecho();
    nuevaRaiz.setDerecho(nodoRaiz);
    nodoRaiz.setIzquierdo(temp);
    nodoRaiz.altura = 1 + Math.max(altura(nodoRaiz.getIzquierdo()), altura(nodoRaiz.getDerecho()));
    nuevaRaiz.altura = 1 + Math.max(altura(nuevaRaiz.getIzquierdo()), altura(nuevaRaiz.getDerecho()));
    return nuevaRaiz;
  }

  public NodoAVL<Tipo> rotarIzquierda(NodoAVL<Tipo> nodoRaiz) {
    NodoAVL<Tipo> nuevaRaiz = nodoRaiz.getDerecho();
    NodoAVL<Tipo> temp = nuevaRaiz.getIzquierdo();
    nuevaRaiz.setIzquierdo(nodoRaiz);
    nodoRaiz.setDerecho(temp);
    nodoRaiz.altura = 1 + Math.max(altura(nodoRaiz.getIzquierdo()), altura(nodoRaiz.getDerecho()));
    nuevaRaiz.altura = 1 + Math.max(altura(nuevaRaiz.getIzquierdo()), altura(nuevaRaiz.getDerecho()));
    return nuevaRaiz;
  }

  // Método público para insertar en el árbol
  public void insertar(int clave, Tipo valor) {
    raiz = insertar(raiz, clave, valor);
  }

  public NodoAVL<Tipo> insertar(NodoAVL<Tipo> raiz, int clave, Tipo valor) {
    if (raiz == null)
      return new NodoAVL<Tipo>(clave, valor); // Se proporciona un valor adecuado

    if (clave < raiz.getClave())
      raiz.setIzquierdo(insertar(raiz.getIzquierdo(), clave, valor));
    else if (clave > raiz.getClave())
      raiz.setDerecho(insertar(raiz.getDerecho(), clave, valor));
    else
      return raiz; // Clave duplicada, no se permite en ABB

    // Actualizar altura correctamente
    raiz.setAltura(1 + Math.max(altura(raiz.getIzquierdo()), altura(raiz.getDerecho())));

    // Obtener factor de equilibrio
    int balance = obtenerFE(raiz);

    // Casos de desbalance y rotaciones
    // Caso LL
    if (balance > 1 && clave < raiz.getIzquierdo().getClave())
      return rotarDerecha(raiz);

    // Caso RR
    if (balance < -1 && clave > raiz.getDerecho().getClave())
      return rotarIzquierda(raiz);

    // Caso LR
    if (balance > 1 && clave > raiz.getIzquierdo().getClave()) {
      raiz.setIzquierdo(rotarIzquierda(raiz.getIzquierdo()));
      return rotarDerecha(raiz);
    }

    // Caso RL
    if (balance < -1 && clave < raiz.getDerecho().getClave()) {
      raiz.setDerecho(rotarDerecha(raiz.getDerecho()));
      return rotarIzquierda(raiz);
    }

    return raiz;
  }

  public void eliminar(int clave) {
    raiz = eliminar(raiz, clave);
  }

  public NodoAVL<Tipo> eliminar(NodoAVL<Tipo> nodoActual, int clave) {
    if (nodoActual == null)
      return nodoActual; // caso base, condicion terminacion
    if (clave < nodoActual.getClave())
      nodoActual.setIzquierdo(eliminar(nodoActual.getIzquierdo(), clave));
    else if (clave > nodoActual.getClave())
      nodoActual.setDerecho(eliminar(nodoActual.getDerecho(), clave));
    else {
      if (nodoActual.getIzquierdo() == null || nodoActual.getDerecho() == null) {// 0 o un hijo
        NodoAVL<Tipo> temp = null;
        if (temp == nodoActual.getIzquierdo())
          temp = nodoActual.getDerecho();
        else
          temp = nodoActual.getIzquierdo();
        if (temp == null) {
          temp = nodoActual;
          nodoActual = null;
        } else
          nodoActual = temp;

      } else {// dos hijos, hare sucesor inorden (menor en subarbol derecho)
        NodoAVL<Tipo> temp = this.encontrarMenor(nodoActual.getDerecho());
        nodoActual.setClave(temp.getClave());
        nodoActual.setDerecho(this.eliminar(nodoActual.getDerecho(), temp.getClave()));
      }
    }
    // arbol tenia un solo nodo
    if (nodoActual == null)
      return nodoActual;
    nodoActual.altura = 1 + Math.max(altura(nodoActual.getIzquierdo()), altura(nodoActual.getDerecho()));

    int balance = obtenerFE(nodoActual);

    if (balance > 1 && obtenerFE(nodoActual.getIzquierdo()) >= 0) {
      return rotarDerecha(nodoActual); // Caso LL
    }
    if (balance < -1 && obtenerFE(nodoActual.getDerecho()) <= 0) {
      return rotarIzquierda(nodoActual); // Caso RR
    }
    if (balance > 1 && obtenerFE(nodoActual.getIzquierdo()) < 0) {
      nodoActual.setIzquierdo(rotarIzquierda(nodoActual.getIzquierdo())); // Caso LR
      return rotarDerecha(nodoActual);
    }
    if (balance < -1 && obtenerFE(nodoActual.getDerecho()) > 0) {
      nodoActual.setDerecho(rotarDerecha(nodoActual.getDerecho())); // Caso RL
      return rotarIzquierda(nodoActual);
    }
    return nodoActual;
  }

  public NodoAVL<Tipo> encontrarMenor(NodoAVL<Tipo> nodo) {
    NodoAVL<Tipo> actual = nodo;
    while (actual.getIzquierdo() != null)
      actual = actual.getIzquierdo();
    return actual;
  }

  public void inOrden(NodoAVL<Tipo> nodo) {
    if (nodo == null)
      return;
    inOrden(nodo.getIzquierdo());
    System.out.print(nodo.getClave() + " - " + nodo.getValor().toString());
    inOrden(nodo.getDerecho());
  }

  public void preOrden(NodoAVL<Tipo> nodo) {
    if (nodo == null)
      return;
    System.out.print(nodo.getClave() + " ");
    preOrden(nodo.getIzquierdo());
    preOrden(nodo.getDerecho());
  }

  public void postOrden(NodoAVL<Tipo> nodo) {
    if (nodo == null)
      return;
    postOrden(nodo.getIzquierdo());
    postOrden(nodo.getDerecho());
    System.out.print(nodo.getClave() + " ");
  }

  public NodoAVL<Tipo> buscar(int clave) {
    return buscar(raiz, clave);
  }

  private NodoAVL<Tipo> buscar(NodoAVL<Tipo> nodo, int clave) {
    if (nodo == null || nodo.getClave() == clave) {
      return nodo;
    }
    if (clave < nodo.getClave()) {
      return buscar(nodo.getIzquierdo(), clave);
    } else {
      return buscar(nodo.getDerecho(), clave);
    }
  }
}