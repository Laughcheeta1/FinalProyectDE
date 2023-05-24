package backEnd;

import Huffman.CompressedFile;

import java.io.*;

/**
 * En esta clase es donde se leen y se escriben los archivos.
 */
public class FileManager {
    public static File readTxt(String memoryDirection)
    {
        return new File(memoryDirection);
    }

    public static void writeText(String memoryDirection, String text, String fileName) throws IOException {
        String filePath = memoryDirection + File.separator + fileName + "." + "txt";
        File f = new File(filePath);
        BufferedWriter w = new BufferedWriter(new FileWriter(f));
        w.write(text);
        w.close();
    }

    public static CompressedFile readCompressedFile(String memoryDirection) throws IOException, ClassNotFoundException {
        File f = new File(memoryDirection);
        FileInputStream in = new FileInputStream(f);
        ObjectInputStream o = new ObjectInputStream(in);
        CompressedFile compressedFile = (CompressedFile) o.readObject();
        o.close();
        in.close();
        return compressedFile;
    }

    public static void writeCompressedFile(String memoryDirection, CompressedFile compressedFile, String fileName) throws IOException {
        String filePath = memoryDirection + File.separator + fileName + "." + "comp";
        FileOutputStream f = new FileOutputStream(filePath);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(compressedFile);
        o.close();
        f.close();
    }

    public static String removeExtension(String fileName)
    {/*
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1)
        {
            throw new FileExtensionException();
        }

        StringBuilder name = new StringBuilder(fileName);
        name.replace(dotIndex, fileName.length(), "");
        return name.toString();
        */
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }
}
