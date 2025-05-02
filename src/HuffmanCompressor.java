import java.io.*;
import java.util.*;

public class HuffmanCompressor {
    private Map<Character, String> mapaCodigos = new HashMap<>();
    private HuffmanNode raiz;

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

    private Map<Character, Integer> contarFrecuencias(String texto) {
        Map<Character, Integer> mapa = new HashMap<>();
        for (char c : texto.toCharArray()) {
            mapa.put(c, mapa.getOrDefault(c, 0) + 1);
        }
        return mapa;
    }

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

    private void construirCodigos(HuffmanNode nodo, String codigo) {
        if (nodo == null) return;
        if (nodo.isLeaf()) {
            mapaCodigos.put(nodo.caracter, codigo);
        }
        construirCodigos(nodo.izquierda, codigo + "0");
        construirCodigos(nodo.derecha, codigo + "1");
    }

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
