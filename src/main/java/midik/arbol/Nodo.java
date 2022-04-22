package midik.arbol;

public class Nodo {

    private static int llave = 1;
    private final int id;

    private Carta carta;
    private int factorEquilibrio;
    private Nodo hijoIzquierdo;
    private Nodo hijoDerecho;

    public Nodo(Carta carta) {
        this.carta = carta;
        this.factorEquilibrio = 0;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.id = llave++;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public int getFactorEquilibrio() {
        return factorEquilibrio;
    }

    public void setFactorEquilibrio(int factorEquilibrio) {
        this.factorEquilibrio = factorEquilibrio;
    }

    public Nodo getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public Nodo getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public int getId() {
        return id;
    }

}
