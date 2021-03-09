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
		ArrayList<Grafo> lista_grafos = getGrafosFromXML(lista_archivos);
		/*
		 * for (Grafo g : lista_grafos) { g.dibujarGrafo(); g.crear_graphml(); }
		 */
		Grafo g = lista_grafos.get(0);
		g.quicksort();
		for (Arco a : g.getLista_arcos()) {
			System.out.println(a.getLength());
		}
		System.out.println("Final");
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

	public static ArrayList<Grafo> getGrafosFromXML(ArrayList<String> lista_archivos) {
		ArrayList<Grafo> lista_grafos = new ArrayList<Grafo>();
		for (String nombre_archivo : lista_archivos) {
			lista_grafos.add(new Grafo(nombre_archivo));
		}
		return lista_grafos;
	}

}
