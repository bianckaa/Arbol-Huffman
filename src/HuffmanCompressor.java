/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos - Seccion 31
 * Arbol de Huffman
 * Biancka Raxón (24960)
 * 
 * Clase: HuffmanCompressor.java
 * Clase que comprime un archivo de texto usando codificación Huffman.
 * 
*/

import java.io.*;
import java.util.*;

public class HuffmanCompressor {
    private Map<Character, String> mapaCodigos = new HashMap<>();
    private HuffmanNode raiz;

    /**
     * Comprime un archivo de texto y guarda el archivo comprimido.
     *
     * @param rutaEntrada Ruta del archivo original
     * @param rutaSalida  Ruta del archivo comprimido
     * @throws IOException Si ocurre error de lectura o escritura
     */
    public void comprimir(String rutaEntrada, String rutaSalida) throws IOException {
        String texto = leerArchivo(rutaEntrada);
        Map<Character, Integer> frecuencias = contarFrecuencias(texto);
        raiz = construirArbol(frecuencias);
        construirCodigos(raiz, "");

        StringBuilder textoCodificado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            textoCodificado.append(mapaCodigos.get(c));
        }

        archivoComprimido(rutaSalida, textoCodificado.toString(), frecuencias);
    }

    /**
     * Lee todo el contenido de un archivo de texto.
     *
     * @param ruta Ruta del archivo
     * @return Texto leído
     * @throws IOException Si ocurre error
     */
    private String leerArchivo(String ruta) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(ruta));
        StringBuilder contenido = new StringBuilder();
        String linea;
        while ((linea = lector.readLine()) != null) {
            contenido.append(linea).append("\n");
        }
        lector.close();
        return contenido.toString();
    }

    /**
     * Cuenta la frecuencia de cada carácter en el texto.
     *
     * @param texto Texto de entrada
     * @return Mapa de frecuencias
     */
    private Map<Character, Integer> contarFrecuencias(String texto) {
        Map<Character, Integer> mapa = new HashMap<>();
        for (char c : texto.toCharArray()) {
            mapa.put(c, mapa.getOrDefault(c, 0) + 1);
        }
        return mapa;
    }

    /**
     * Construye el árbol de Huffman a partir de las frecuencias.
     *
     * @param frecuencias Mapa de frecuencias
     * @return Nodo raíz del árbol
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
     * Construye los códigos binarios para cada carácter.
     *
     * @param nodo Nodo actual del árbol
     * @param codigo Código binario actual
     */
    private void construirCodigos(HuffmanNode nodo, String codigo) {
        if (nodo == null) return;
        if (nodo.isLeaf()) {
            mapaCodigos.put(nodo.caracter, codigo);
        }
        construirCodigos(nodo.izquierda, codigo + "0");
        construirCodigos(nodo.derecha, codigo + "1");
    }

    /**
     * Guarda el archivo comprimido con el texto codificado.
     *
     * @param rutaSalida Ruta del archivo comprimido
     * @param datosBinarios Texto codificado
     * @param frecuencias Mapa de frecuencias
     * @throws IOException Si ocurre un error de escritura
     */
    private void archivoComprimido(String ruta, String binario, Map<Character, Integer> frecuencias) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta));
        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            escritor.write((int)entrada.getKey() + ":" + entrada.getValue());
            escritor.newLine();
        }
        escritor.write("==");
        escritor.newLine();
        escritor.write(binario);
        escritor.close();
    }
}
