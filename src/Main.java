/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos - Seccion 31
 * Arbol de Huffman
 * Biancka Raxón (24960)
 * 
 * Clase: Main.java
 * Clase principal para probar la compresión y descompresión Huffman.
 * 
*/

public class Main {
    /**
     * Lee un archivo, lo comprime, lo descomprime y muestra los resultados.
     *
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        String archivoOriginal = "entrada.txt";
        String archivoComprimido = "comprimido.txt";
        String archivoDescomprimido = "salida.txt";

        HuffmanCompressor compresor = new HuffmanCompressor();
        HuffmanDecompressor descompresor = new HuffmanDecompressor();

        try {
            System.out.println("Contenido original:");
            System.out.println(java.nio.file.Files.readString(java.nio.file.Paths.get(archivoOriginal)));

            compresor.comprimir(archivoOriginal, archivoComprimido);
            System.out.println("\nArchivo comprimido correctamente.");

            descompresor.descomprimir(archivoComprimido, archivoDescomprimido);
            System.out.println("\nContenido descomprimido:");
            System.out.println(java.nio.file.Files.readString(java.nio.file.Paths.get(archivoDescomprimido)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
