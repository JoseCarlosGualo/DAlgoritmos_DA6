import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Principal {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		ArrayList<String> lista_archivos = getListaXML();
		ArrayList<Poblacion> lista_poblaciones = getPoblacionesFromXML(lista_archivos);
		exportarSolucion(lista_poblaciones);

	}

	public static void exportarSolucion(ArrayList<Poblacion> lista_poblaciones) {
		try {
			String ruta = "solution_poblaciones.txt";
			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(
					"<Población>,<Cantidad de nodos>,<Cantidad de arcos>,<Longitud total de los arcos>,<cantidad de coord X pares>,<cantidad de coord Y impares> \n");

			for (Poblacion p : lista_poblaciones) {
				bw.write("<" + p.getNombre() + ">,<" + p.getCantidadNodos() + ">,<" + p.getCantidadArcos() + ">,<"
						+ p.getLongitudArcos() + ">,<" + p.getCoordenadasXPares() + ">,<" + p.getCoordenadasYImpares()
						+ ">" + "\n");
			}

			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getListaXML() {
		ArrayList<String> nombre_xmls = new ArrayList<String>();
		File miDir = new File("Poblaciones");
		File[] listOfFiles = miDir.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				try {
					nombre_xmls.add(miDir.getCanonicalPath() + "\\" + file.getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return nombre_xmls;
	}

	public static ArrayList<Poblacion> getPoblacionesFromXML(ArrayList<String> lista_archivos) {
		ArrayList<Poblacion> lista_poblaciones = new ArrayList<Poblacion>();
		LeerXMLSAX leerXML = new LeerXMLSAX();
		for (String nombre_archivo : lista_archivos) {
			try {
				lista_poblaciones.add(leerXML.leerXMLSAX(nombre_archivo));
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista_poblaciones;
	}

}
