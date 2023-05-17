package backEnd;
/**
 * Creator: 
 * This class is the back end of the TextViewer program.
 */

public class FileViewerBackEnd {
    // Retorna la ruta de la carpeta de descargas · Susana Uribe
    public static Path downloadsPath() {
		Path downloadsPath = Paths.get(System.getProperty("user.home"), "Downloads");
		return downloadsPath;
	}

    // Verifica si la ruta existe · Susana Uribe
    public static void checkPath(String path) {
		File file = new File(path);
	    if (file.exists()) {
	        return true;
	    } else {
	        return false;
        }
	}

    // Retorna el contenido del archivo como String · Susana Uribe
    public static String readFile(String archivo) throws IOException {
		File file=new File(archivo);
		FileReader fr= new FileReader(file);
		BufferedReader b= new BufferedReader(fr);
		String linea;
		String lectura = "";
		while ((linea=b.readLine())!=null) {
			lectura=lectura+linea+"\n";
		}
		b.close();
		fr.close();
		return lectura;
	}
    // Guarda el archivo · Susana Uribe
    public static void save() {
        File file = new File(downloadsPath().toString()); //Se crea el archivo en Descargas
        //método para leer archivo
		Desktop.getDesktop().open(file); //Se abre el archivo
	}

    // main method  · Susana Uribe
    public static void main(String[] args) throws IOException {
		String textForPath=downloadsPath().toString(); //We use this automatically in the space for the path
        
	}
}
