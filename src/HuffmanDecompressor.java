/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos - Seccion 31
 * Arbol de Huffman
 * Biancka Raxón (24960)
 * 
 * Clase: HuffmanDecompressor.java
 * Clase que descomprime un archivo comprimido con Huffman.
 * 
*/

import java.io.*;
import java.util.*;

public class HuffmanDecompressor {
    /**
     * Descomprime el archivo codificado y recupera el texto original.
     *
     * @param rutaEntrada  Ruta del archivo comprimido
     * @param rutaSalida   Ruta del archivo descomprimido
     * @throws IOException Si ocurre un error
     */
    public void descomprimir(String rutaEntrada, String rutaSalida) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(rutaEntrada));
        Map<Character, Integer> frecuencias = new HashMap<>();
        String linea;

        while (!(linea = lector.readLine()).equals("==")) {
            String[] partes = linea.split(":");
            char c = (char) Integer.parseInt(partes[0]);
            int freq = Integer.parseInt(partes[1]);
            frecuencias.put(c, freq);
        }

        StringBuilder binario = new StringBuilder();
        while ((linea = lector.readLine()) != null) {
            binario.append(linea);
        }
        lector.close();

        HuffmanNode raiz = construirArbol(frecuencias);
        String texto = decodificarTexto(binario.toString(), raiz);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaSalida));
        escritor.write(texto);
        escritor.close();
    }

    /**
     * Construye el árbol de Huffman con las frecuencias.
     *
     * @param frecuencias Mapa de frecuencias
     * @return Nodo raíz
     */
    private HuffmanNode construirArbol(Map<Character, Integer> frecuencias) {
        PriorityQueue<HuffmanNode> cola = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            cola.add(new HuffmanNode(entrada.getKey(), entrada.getValue()));
        }

        while (cola.size() > 1) {
            HuffmanNode nodo1 = cola.poll();
            HuffmanNode nodo2 = cola.poll();
            HuffmanNode combinado = new HuffmanNode('\0', nodo1.frecuencia + nodo2.frecuencia);
            combinado.izquierda = nodo1;
            combinado.derecha = nodo2;
            cola.add(combinado);
        } return cola.poll();
    }

    /**
     * Decodifica el texto binario a texto original usando el árbol.
     *
     * @param datosBinarios Cadena de bits
     * @param raiz Árbol de Huffman
     * @return Texto original
     */
    private String decodificarTexto(String binario, HuffmanNode raiz) {
        StringBuilder texto = new StringBuilder();
        HuffmanNode actual = raiz;
        for (char bit : binario.toCharArray()) {
            actual = (bit == '0') ? actual.izquierda : actual.derecha;
            if (actual.isLeaf()) {
                texto.append(actual.caracter);
                actual = raiz;
            }
        }
        return texto.toString();
    }
}
