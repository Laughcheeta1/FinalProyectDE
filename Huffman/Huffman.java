package Huffman;

import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Santiago Yepes Mesa, Simon Eduardo Parisca Muñoz, Santiago Augusto Toro Bonilla
 */

public class Huffman {
    /**
     * Given a .txt file, returns the compressed version of this file in the way of a CompressedFile class object.
     * @param file to compress
     * @return CompressedFile object.
     */
    public CompressedFile compressTxt(File file) throws FileExtensionException, IOException
    {
        Node treeHead = createHuffmanTree(countCharacters(file));
        HashMap<Character, Integer> frequencies = countCharacters(file);
        BitSet encode = new BitSet();
        int actual = 0;
        generateCode(treeHead);

        return new CompressedFile(treeHead, encode);
    }


    /**
     * Recursive method that given a Huffman tree and the node to search returns the character
     * binary code.
     * @param head of the tree
     * @return The encoded version of a given Character
     */
    private void generateCode(Node head){
        BitSet code = new BitSet();
        generateCode(head, code, 0);
    }
    private void generateCode(Node node, BitSet code, int actual){
        if (node instanceof LeafNode){
            ((LeafNode) node).setCode(code);
            return;
        }
        code.set(actual, false);
        generateCode(node.getLeftNode(), code, actual + 1);
        code.set(actual, true);
        generateCode(node.getRightNode(), code, actual + 1);
    }
    /*
    private BitSet generateCode(Node head, Character key){
        BitSet code = new BitSet();
        return generateCode(head, key, code, 0);
    }
    private BitSet generateCode(Node head, Character key, BitSet code, int actual){
        Node rs = head.getRightNode();
        Node ls = head.getLeftNode();
        if (rs == null && ls == null) {
            return code;
        }
        else if (ls != null && ls.getKey().contains(String.valueOf(key))) {
            code.set(actual, false);
            return generateCode(ls, key, code, actual + 1);
        }
        else {
            code.set(actual, true);
            return generateCode(rs, key, code, actual + 1);
        }

    }
*/
    /**
     * Given a compressed file, of the extension .compr, returns the .txt uncompressed version of this file
     * @param compressedFile with the .compr extension
     * @return .txt File
     */
    public File decompressFile(CompressedFile compressedFile)
    {/*
        byte[] buffer = new byte[1024];
<<<<<<< HEAD

        try {
            File carpetaDestino = new File(directorioDestino);
            if (!carpetaDestino.exists()) {
                carpetaDestino.mkdir();
            }

            ZipInputStream zis = new ZipInputStream(new FileInputStream(archivoComprimido));
            ZipEntry entrada;

            while ((entrada = zis.getNextEntry()) != null) {
                String nombreArchivo = entrada.getName();
                if (!entrada.isDirectory() && nombreArchivo.endsWith(".compr")) {
                    String archivoDescomprimido = directorioDestino + File.separator + obtenerNombreSinExtension(nombreArchivo) + ".txt";
                    File nuevoArchivo = new File(archivoDescomprimido);

                    // Crear directorios si es necesario
                    nuevoArchivo.getParentFile().mkdirs();

                    // Extraer el archivo
                    FileOutputStream fos = new FileOutputStream(nuevoArchivo);
                    int longitud;
                    while ((longitud = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, longitud);
                    }
                    fos.close();
                }
                zis.closeEntry();
            }

            zis.close();

            System.out.println("¡Archivo descomprimido exitosamente!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        return null;
    }

    /**
     * Given a file returns the count of the characters in form of a hashmap, where its key is the
     * character, and the value is the frequency of the character.
     * @param file to count the number of characters
     * @return HashMap that contains the number of chars
     */
    private HashMap<Character, Integer> countCharacters(File file) throws FileExtensionException, IOException
    {
        // Check that the file is of the correct type
        if (!file.getName().endsWith(".txt") || file.getName().isBlank())
        {
            throw new FileExtensionException();
        }

        // Create the needed resources
        HashMap<Character, Integer> count = new HashMap<>();
        FileReader fr = new FileReader(file);
        // Buffered reader is faster than file reader, that's why we use it
        BufferedReader br = new BufferedReader(fr);

        // Count the times each character appears, and save it into the HashMap
        int character; // Is of type int because BufferedReader only allows to read the int value of the char
        while ((character = br.read()) != -1)
        {
            if (count.containsKey((char) character))
                count.replace((char) character, count.get((char) character) + 1);

            else
                count.put((char) character, 1);
        }

        return count;
    }


    /**
     * Given a HashMap that stores the frequencies returns the Tree head of the Huffman tree
     * @param frequencies of the characters
     * @return Head node of the tree
     */
    private Node createHuffmanTree(HashMap<Character, Integer> frequencies)
    {
        // Creates a priority queue that will always have first the nodes that stores the lower values (because of the
            // compare to of the class Node)
        PriorityQueue<Node> list = new PriorityQueue<>();
        // Create all the base nodes of the tree
        for (Character c : frequencies.keySet())
        {
            list.add(new LeafNode(c, frequencies.get(c)));
        }

        // As the huffman code says, make a new node with the two lowest value nodes
        while (list.size() > 1)
        {
            list.add(new Node(list.poll(), list.poll()));
        }

        // The last remaining element of the queue is the head of the created tree
        return list.poll();
    }

    /**
     * Method that given the name of a file with the extension, returns the name of the file without the extension
     * @param nombreArchivo, the name of the archive with the extension
     * @return the name of the archive without the extension
     */
    private static String obtenerNombreSinExtension(String nombreArchivo) {
        int indicePunto = nombreArchivo.lastIndexOf(".");
        if (indicePunto != -1) {
            return nombreArchivo.substring(0, indicePunto);
        }
        return nombreArchivo;
    }

}
