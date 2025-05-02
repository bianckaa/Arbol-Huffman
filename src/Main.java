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
        String archivoOriginal = "green_eggs_and_ham_entrada.txt";
        String archivoComprimido = "comprimido.txt";
        String archivoDescomprimido = "green_eggs_and_ham_salida.txt";

        HuffmanCompressor compresor = new HuffmanCompressor();
        HuffmanDecompressor descompresor = new HuffmanDecompressor();

        try {
            System.out.println("El contenido original del archivo a comprimir es el siguiente ^._.^:\n");
            System.out.println(java.nio.file.Files.readString(java.nio.file.Paths.get(archivoOriginal)));

            compresor.comprimir(archivoOriginal, archivoComprimido);
            System.out.println("\n\n####################################################");
            System.out.println("# El archivo fue comprimido correctamente (^-^)/   #");
            System.out.println("####################################################");

            descompresor.descomprimir(archivoComprimido, archivoDescomprimido);
            System.out.println("\n\nEl contenido descomprimido del archivo es el siguiente ^._.^:\n");
            System.out.println(java.nio.file.Files.readString(java.nio.file.Paths.get(archivoDescomprimido)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
