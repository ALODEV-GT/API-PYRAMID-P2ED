package test;

import midik.arbol.Arbol;
import midik.arbol.Carta;
import midik.arbol.Tipo;

public class Test {

    public static void main(String[] args) {
        Carta carta1 = new Carta(23, Tipo.TREBOL);
        Carta carta2 = new Carta(8, Tipo.TREBOL);
        Carta carta3 = new Carta(30, Tipo.TREBOL);
        Carta carta4 = new Carta(5, Tipo.TREBOL);
        Carta carta5 = new Carta(17, Tipo.TREBOL);
        Carta carta6 = new Carta(25, Tipo.TREBOL);
        Carta carta7 = new Carta(50, Tipo.TREBOL);
        Carta carta8 = new Carta(4, Tipo.TREBOL);
        Carta carta9 = new Carta(7, Tipo.TREBOL);
        Carta carta10 = new Carta(16, Tipo.TREBOL);
        Carta carta11 = new Carta(19, Tipo.TREBOL);
        Carta carta12 = new Carta(26, Tipo.TREBOL);
        Carta carta13 = new Carta(18, Tipo.TREBOL);

        Arbol arbol = new Arbol();

        System.out.println("Inicio------------------------>");
        arbol.preOrden(arbol.getRaiz());
        System.out.println("Se elimino:");
        arbol.eliminar(carta10, arbol.getRaiz());
        arbol.preOrden(arbol.getRaiz());

    }
}
