/**
 * Referencia:
 * - https://www.wscubetech.com/resources/dsa/huffman-code
 */

public class HuffmanNode  implements Comparable<HuffmanNode> {
    char caracter;
    int frecuencia;
    HuffmanNode izquierda, derecha;

    public HuffmanNode (char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izquierda = this.derecha = null;
    }

    public boolean isLeaf() {
        return izquierda == null && derecha == null;
    }

    public int compareTo(HuffmanNode  otro) {
        return this.frecuencia - otro.frecuencia;
    }
}