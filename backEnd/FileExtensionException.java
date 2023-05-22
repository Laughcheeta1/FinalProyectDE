package backEnd;

import java.rmi.server.ExportException;

public class FileExtensionException extends Exception {
    public FileExtensionException()
    {
        super("The app does not support this file extension");
    }
}
