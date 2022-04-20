package midik.singleton;

import midik.arbol.Arbol;

public class SingletonArbol {

    private static Arbol arbol = null;

    private SingletonArbol() {
        arbol = new Arbol();
    }

    public static Arbol getArbol() {
        if (arbol == null) {
            new SingletonArbol();
        }
        return arbol;
    }
}
