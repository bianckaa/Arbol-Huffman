/**
 * Referencia:
 * - https://www.wscubetech.com/resources/dsa/huffman-code
 * 
 * Representa un nodo del árbol de Huffman.
 */

public class HuffmanNode  implements Comparable<HuffmanNode> {
    char caracter;
    int frecuencia;
    HuffmanNode izquierda, derecha;

    /**
     * Constructor del nodo de Huffman.
     *
     * @param caracter  El carácter
     * @param frecuencia La frecuencia del carácter
     */
    public HuffmanNode (char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izquierda = this.derecha = null;
    }

    /**
     * Verifica si el nodo es una hoja.
     *
     * @return true si es hoja, false si no
     */
    public boolean isLeaf() {
        return izquierda == null && derecha == null;
    }

    /**
     * Compara dos nodos por su frecuencia.
     *
     * @param otro Otro nodo de Huffman
     * @return Resultado de la comparación
     */
    public int compareTo(HuffmanNode  otro) {
        return this.frecuencia - otro.frecuencia;
    }
}