package Huffman;

public class FileExtensionException extends Exception{
    public FileExtensionException()
    {
        super("This kind of files are not supported");
    }
}
