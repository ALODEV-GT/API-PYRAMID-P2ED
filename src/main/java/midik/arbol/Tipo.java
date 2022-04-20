package midik.arbol;

public enum Tipo {
    CORAZON("♥"),
    TREBOL("♣"),
    DIAMANTE("♦"),
    PICA("♠");

    private String simbolo = "";

    private Tipo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return this.simbolo;
    }

}
