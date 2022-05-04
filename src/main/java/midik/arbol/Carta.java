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
        return formatearValor(this.getValorReal()) + tipo.getSimbolo();
    }

    //Convierte en 11 a J, 12 a Q y 13 a K. 
    private String formatearValor(int valor) {
        String valorS = String.valueOf(valor);
        switch (valor) {
            case 1:
                valorS = "A";
                break;
            case 11:
                valorS = "J";
                break;
            case 12:
                valorS = "Q";
                break;
            case 13:
                valorS = "K";
                break;
        }
        return valorS;
    }
}
