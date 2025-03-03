package estructuras.avl;

public class NodoAVL<Tipo> {
  private int clave;
  private Tipo valor;
  private NodoAVL<Tipo> izquierdo;
  private NodoAVL<Tipo> derecho;
  int altura;

  public NodoAVL(int clave, Tipo valor) {
    this.clave = clave;
    this.izquierdo = null;
    this.derecho = null;
    this.valor = valor;
    this.altura = 1;
  }

  public int getClave() {
    return clave;
  }

  public void setClave(int clave) {
    this.clave = clave;
  }

  public Tipo getValor() {
    return valor;
  }

  public void setValor(Tipo valor) {
    this.valor = valor;
  }

  public NodoAVL<Tipo> getIzquierdo() {
    return izquierdo;
  }

  public void setIzquierdo(NodoAVL<Tipo> izquierdo) {
    this.izquierdo = izquierdo;
  }

  public NodoAVL<Tipo> getDerecho() {
    return derecho;
  }

  public void setDerecho(NodoAVL<Tipo> derecho) {
    this.derecho = derecho;
  }

  public int getAltura() {
    return altura;
  }

  public void setAltura(int altura) {
    this.altura = altura;
  }
}
