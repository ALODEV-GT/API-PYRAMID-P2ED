package midik.arbol;

import javax.servlet.http.HttpServletResponse;

public class Arbol {

    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    //Buscar
    public Nodo buscar(Carta carta, Nodo raiz) {
        if (raiz == null) {
            return null;
        } else if (raiz.getCarta().getValor() == carta.getValor()) {
            return raiz;
        } else if (raiz.getCarta().getValor() < carta.getValor()) {
            return buscar(carta, raiz.getHijoDerecho());
        } else {
            return buscar(carta, raiz.getHijoIzquierdo());
        }
    }

    private Nodo eliminarNodoHoja(Carta carta, Nodo raiz, Nodo padre, boolean derecha) {
        Nodo nuevaRaiz = raiz;
        if (raiz == null) {
            //Arbol vacio
        } else if (raiz.getCarta().getValor() == carta.getValor()) {
            if (padre == null) {
                this.raiz = null;
            } else {
                if (derecha) {
                    padre.setHijoDerecho(null);
                    nuevaRaiz = null;
                } else {
                    padre.setHijoIzquierdo(null);
                    nuevaRaiz = null;
                }
            }
        } else if (raiz.getCarta().getValor() < carta.getValor()) { //Derecha
            raiz.setHijoDerecho(eliminarNodoHoja(carta, raiz.getHijoDerecho(), raiz, true));
            actualizarFe(raiz);
        } else {
            raiz.setHijoIzquierdo(eliminarNodoHoja(carta, raiz.getHijoIzquierdo(), raiz, false)); //Izquierda
            actualizarFe(raiz);
        }

        if (obtenerFactorEquilibrio(raiz.getHijoDerecho()) - obtenerFactorEquilibrio(raiz.getHijoIzquierdo()) == 2) {
            Nodo derecho = raiz.getHijoDerecho();
            if (derecho.getHijoIzquierdo() != null && derecho.getHijoDerecho() == null) {
                nuevaRaiz = rotacionDobleIzquierda(raiz);
            } else {
                nuevaRaiz = rotacionIzquierda(raiz);
            }
        } else if (obtenerFactorEquilibrio(raiz.getHijoIzquierdo()) - obtenerFactorEquilibrio(raiz.getHijoDerecho()) == 2) {
            Nodo izquierdo = raiz.getHijoIzquierdo();
            if (izquierdo.getHijoDerecho() != null && izquierdo.getHijoIzquierdo() == null) {
                nuevaRaiz = rotacionDobleDerecha(raiz);
            } else {
                nuevaRaiz = rotacionDerecha(raiz);
            }
        }
        return nuevaRaiz;
    }

    public void eliminar(Carta carta, Nodo raiz) {
        this.raiz = eliminarNodoHoja(carta, raiz, raiz, true);
    }

    private void actualizarFe(Nodo subArbol) {
        //Actualizando factor de equilibrio
        if (subArbol.getHijoIzquierdo() == null && subArbol.getHijoDerecho() != null) {
            subArbol.setFactorEquilibrio(subArbol.getHijoDerecho().getFactorEquilibrio() + 1);
        } else if (subArbol.getHijoDerecho() == null && subArbol.getHijoIzquierdo() != null) {
            subArbol.setFactorEquilibrio(subArbol.getHijoIzquierdo().getFactorEquilibrio() + 1);
        } else {
            subArbol.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(subArbol.getHijoIzquierdo()), obtenerFactorEquilibrio(subArbol.getHijoDerecho())) + 1);
        }
    }

    public boolean tieneHijos(Carta carta, Nodo raiz) {
        Nodo aux = buscar(carta, raiz);
        if (aux.getHijoDerecho() == null && aux.getHijoIzquierdo() == null) {
            return false;
        }
        return true;
    }

    private int obtenerFactorEquilibrio(Nodo nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.getFactorEquilibrio();
        }
    }

    private Nodo rotacionDerecha(Nodo nodo) {
        Nodo aux = nodo.getHijoIzquierdo();
        nodo.setHijoIzquierdo(aux.getHijoDerecho());
        aux.setHijoDerecho(nodo);
        nodo.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(nodo.getHijoIzquierdo()), obtenerFactorEquilibrio(nodo.getHijoDerecho())) + 1);
        aux.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(aux.getHijoIzquierdo()), obtenerFactorEquilibrio(aux.getHijoDerecho())) + 1);
        return aux;
    }

    private Nodo rotacionIzquierda(Nodo nodo) {
        Nodo aux = nodo.getHijoDerecho();
        nodo.setHijoDerecho(aux.getHijoIzquierdo());
        aux.setHijoIzquierdo(nodo);
        nodo.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(nodo.getHijoIzquierdo()), obtenerFactorEquilibrio(nodo.getHijoDerecho())) + 1);
        aux.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(aux.getHijoIzquierdo()), obtenerFactorEquilibrio(aux.getHijoDerecho())) + 1);
        return aux;
    }

    private Nodo rotacionDobleDerecha(Nodo nodo) {
        Nodo aux;
        nodo.setHijoIzquierdo(rotacionIzquierda(nodo.getHijoIzquierdo()));
        aux = rotacionDerecha(nodo);
        return aux;
    }

    private Nodo rotacionDobleIzquierda(Nodo nodo) {
        Nodo aux;
        nodo.setHijoDerecho(rotacionDerecha(nodo.getHijoDerecho()));
        aux = rotacionIzquierda(nodo);
        return aux;
    }

    private Nodo insertarAVL(Nodo nuevo, Nodo subArbol, HttpServletResponse response) {
        Nodo nuevaRaiz = subArbol;
        if (nuevo.getCarta().getValor() < subArbol.getCarta().getValor()) {
            if (subArbol.getHijoIzquierdo() == null) {
                subArbol.setHijoIzquierdo(nuevo);
            } else {
                subArbol.setHijoIzquierdo(insertarAVL(nuevo, subArbol.getHijoIzquierdo(), response));
                if (obtenerFactorEquilibrio(subArbol.getHijoIzquierdo()) - obtenerFactorEquilibrio(subArbol.getHijoDerecho()) == 2) {
                    if (nuevo.getCarta().getValor() < subArbol.getHijoIzquierdo().getCarta().getValor()) {
                        nuevaRaiz = rotacionDerecha(subArbol);
                    } else {
                        nuevaRaiz = rotacionDobleDerecha(subArbol);
                    }
                }
            }
        } else if (nuevo.getCarta().getValor() > subArbol.getCarta().getValor()) {
            if (subArbol.getHijoDerecho() == null) {
                subArbol.setHijoDerecho(nuevo);
            } else {
                subArbol.setHijoDerecho(insertarAVL(nuevo, subArbol.getHijoDerecho(), response));
                if (obtenerFactorEquilibrio(subArbol.getHijoDerecho()) - obtenerFactorEquilibrio(subArbol.getHijoIzquierdo()) == 2) {
                    if (nuevo.getCarta().getValor() > subArbol.getHijoDerecho().getCarta().getValor()) {
                        nuevaRaiz = rotacionIzquierda(subArbol);
                    } else {
                        nuevaRaiz = rotacionDobleIzquierda(subArbol);
                    }
                }
            }
        } else {
            response.setStatus(406);
            System.out.println("Carta duplicada");
        }
        //Actualizando factor de equilibrio
        actualizarFe(subArbol);
        return nuevaRaiz;
    }

    //Insertar
    public void insertar(Carta carta, HttpServletResponse response) {
        Nodo nuevo = new Nodo(carta);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            raiz = insertarAVL(nuevo, raiz, response);
        }
    }

    //Recorridos
    public void inOrden(Nodo nodo) {
        if (nodo != null) {
            inOrden(nodo.getHijoIzquierdo());
            System.out.println(nodo.getCarta().getValorReal() + nodo.getCarta().getTipo().getSimbolo() + ", ");
            inOrden(nodo.getHijoDerecho());
        }
    }

    public void preOrden(Nodo nodo) {
        if (nodo != null) {
            System.out.println(nodo.getCarta().getValorReal() + nodo.getCarta().getTipo().getSimbolo() + ", ");
            preOrden(nodo.getHijoIzquierdo());
            preOrden(nodo.getHijoDerecho());
        }
    }

    public void postOrden(Nodo nodo) {
        if (nodo != null) {
            postOrden(nodo.getHijoIzquierdo());
            postOrden(nodo.getHijoDerecho());
            System.out.println(nodo.getCarta().getValorReal() + nodo.getCarta().getTipo().getSimbolo() + ", ");
        }
    }

    public Nodo getRaiz() {
        return raiz;
    }

}
