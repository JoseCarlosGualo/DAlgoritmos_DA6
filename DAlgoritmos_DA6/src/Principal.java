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
		ArrayList<Grafo> lista_kruskal = new ArrayList<Grafo>();
		ArrayList<Grafo> lista_prim = new ArrayList<Grafo>();
		ArrayList<Nodo> lista_raices = new ArrayList<Nodo>();

		for (int i = 0; i < lista_grafos.size(); i++) {
			Grafo g = lista_grafos.get(i);
			g.quicksort();
			Grafo kruskal = g.kruskal();
			lista_kruskal.add(kruskal);
			Nodo raiz = lista_kruskal.get(i).encontrarRaiz();
			lista_kruskal.get(i).dibujarGrafo(raiz);
			lista_raices.add(raiz);
			System.out.println(lista_kruskal.get(i).nombre_poblacion + " Raiz: " + raiz.toString());
		}

		Grafo raices = generarGrafoRaices(lista_raices);

		Grafo raices_kruskal = raices.kruskal();
		Nodo raiz_final = raices_kruskal.encontrarRaiz();
		raices_kruskal.dibujarGrafo(raiz_final);
		System.out.println("RAIZ final :" + raiz_final.toString());

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

	public static Grafo generarGrafoRaices(ArrayList<Nodo> lista_raices) {
		Grafo g = new Grafo();
		g.setNombre_poblacion("Raices poblaciones ");
		ArrayList<Arco> arcos = get_arcos_unitarios(lista_raices);

		for (Nodo n : lista_raices)
			g.addNodo(n);
		for (Arco a : arcos) {
			g.addArco(a);
		}

		return g;
	}

	public static ArrayList<Arco> get_arcos_unitarios(ArrayList<Nodo> lista_nodos_raices) {
		int i = 0;
		ArrayList<Arco> lista_arcos_unitarios = new ArrayList<Arco>();
		for (Nodo n : lista_nodos_raices) {
			for (Nodo n_1 : lista_nodos_raices) {
				if (!n.equals(n_1) && checkRepetidos(lista_arcos_unitarios, n, n_1) == false) {
					lista_arcos_unitarios.add(new Arco("nuevoArco_" + i++, n, n_1,
							Math.sqrt((Math.pow((n_1.getX() - n.getX()), 2) + (Math.pow((n_1.getY() - n.getY()), 2))))
									* 100000));
				}
			}
		}
		return lista_arcos_unitarios;
	}

	public static boolean checkRepetidos(ArrayList<Arco> arcos, Nodo n1, Nodo n2) {
		for (Arco a : arcos) {
			if (a.getNodo_destino().equals(n1) && a.getNodo_origen().equals(n2)) {
				return true;
			} else if (a.getNodo_destino().equals(n2) && a.getNodo_origen().equals(n1)) {
				return true;
			}
		}
		return false;
	}

}
