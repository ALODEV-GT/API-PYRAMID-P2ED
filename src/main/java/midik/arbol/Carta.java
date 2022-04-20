package midik.arbol;

import midik.comunes.Comunes;

public class Carta {

    private int valor;
    private Tipo tipo;

    public Carta(int valor, Tipo tipo) {
        this.valor = Comunes.obtenerValorHash(valor, tipo);
        this.tipo = tipo;
    }

    public Carta() {
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getValorReal() {
        return Comunes.obtenerValorHashInverso(valor, tipo);
    }

    @Override
    public String toString() {
        return "Nodo{" + "Valor=" + valor + ", Tipo=" + tipo.getSimbolo() + '}';
    }

}
