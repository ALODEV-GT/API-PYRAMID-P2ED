package midik.generadorImagen;

import java.io.FileWriter;
import java.io.PrintWriter;
import midik.arbol.Nodo;

public class Generador {

    public void graficar(String path, Nodo raiz) {
        FileWriter fichero = null;
        PrintWriter escritor;
        String pathRecursosServer = "/home/midik/imagenesArbol/";
        try {
            fichero = new FileWriter(pathRecursosServer + "aux_grafico.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz(raiz));
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo aux_grafico.dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo aux_grafico.dot");
            }
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o " + pathRecursosServer + path +" "+pathRecursosServer +"aux_grafico.dot");
            //Esperamos medio segundo para dar tiempo a que la imagen se genere.
            //Para que no sucedan errores en caso de que se decidan graficar varios
            //árboles sucesivamente.
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo aux_grafico.dot");
        }
        System.out.println("Termine de graficar");
    }

    /**
     * Método que retorna el código que grapviz usará para generar la imagen del
     * árbol binario de búsqueda.
     *
     * @return
     */
    private String getCodigoGraphviz(Nodo raiz) {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "node [shape = record, style=filled, fillcolor=seashell2];\n"
                + getCodigoInterno(raiz)
                + "}\n";
    }

    /**
     * Genera el código interior de graphviz, este método tiene la
     * particularidad de ser recursivo, esto porque recorrer un árbol de forma
     * recursiva es bastante sencillo y reduce el código considerablemente.
     *
     * @return
     */
    private String getCodigoInterno(Nodo raiz) {
        String etiqueta;
        if (raiz.getHijoIzquierdo() == null && raiz.getHijoDerecho() == null) {
            etiqueta = "nodo" + raiz.getId() + " [ label =\"" + raiz.getCarta() + "\"];\n";
        } else {
            etiqueta = "nodo" + raiz.getId() + " [ label =\"<C0>|" + raiz.getCarta() + "|<C1>\"];\n";
        }
        if (raiz.getHijoIzquierdo() != null) {
            etiqueta = etiqueta + getCodigoInterno(raiz.getHijoIzquierdo())
                    + "nodo" + raiz.getId() + ":C0->nodo" + raiz.getHijoIzquierdo().getId() + "\n";
        }
        if (raiz.getHijoDerecho() != null) {
            etiqueta = etiqueta + getCodigoInterno(raiz.getHijoDerecho())
                    + "nodo" + raiz.getId() + ":C1->nodo" + raiz.getHijoDerecho().getId() + "\n";
        }
        return etiqueta;
    }
}
