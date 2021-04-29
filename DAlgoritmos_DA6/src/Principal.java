import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * The Class Principal.
 */
public class Principal {

	/**
	 * Metodo principal.
	 *
	 * @param args the arguments
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException                 the SAX exception
	 * @throws IOException                  Signals that an I/O exception has
	 *                                      occurred.
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException { //T(n) = 21n + 15 -- O(n)

		ArrayList<String> lista_archivos = getListaXML(); //7n + 9
		ArrayList<Grafo> lista_grafos = getGrafosFromXML(lista_archivos); //4n + 5
		ArrayList<Grafo> lista_kruskal = new ArrayList<Grafo>(); //1 OE
		ArrayList<Grafo> lista_prim = new ArrayList<Grafo>(); //1 OE
		ArrayList<Nodo> lista_raices = new ArrayList<Nodo>(); //1 OE

		for (int i = 0; i < lista_grafos.size(); i++) { //2n^4 + 2n^3log2n + 33n^3 + 6n^2log2n + 80n^2 + 152n + 3
			Grafo g = lista_grafos.get(i); //3 OE
			g.quicksort(); //nlog2n + 7
			Grafo kruskal = g.kruskal(); //n*log2n * (2n + 5) + 18n + 12
			lista_kruskal.add(kruskal); //2 OE
			Nodo raiz = lista_kruskal.get(i).encontrarRaiz(); //2n^3 + 33n^2 + 20n + 15
			lista_kruskal.get(i).dibujarGrafo(raiz); //42n + 103
			lista_raices.add(raiz); //2 OE
			System.out.println(lista_kruskal.get(i).nombre_poblacion + " Raiz: " + raiz.toString()); //5 OE
		}

		Grafo raices = generarGrafoRaices(lista_raices); //12n + 11
		Grafo raices_kruskal = raices.kruskal(); //n*log2n * (2n + 5) + 18n + 12
		Nodo raiz_final = raices_kruskal.encontrarRaiz(); //2n^3 + 33n^2 + 20n + 13
		raices_kruskal.dibujarGrafo(raiz_final); //42n + 101
		raices.crear_graphml(); //135n + 68
		System.out.println("RAIZ final :" + raiz_final.toString()); //3 OE

	} // T(n) = 2n^4 + 2n^3log2n + 35n^3 + 8n^2log2n + 113n^2 + 5nlog2n + 490n + 228 -- O(n^4) COMPLEJIDAD TOTAL DEL CODIGO

	/**
	 * Devuelve una lista con el nombre de todas las poblaciones dentro de la carpeta "Poblaciones".
	 *
	 * @return the lista XML
	 */
	public static ArrayList<String> getListaXML() {
		ArrayList<String> nombre_xmls = new ArrayList<String>(); //1 OE
		File miDir = new File("Poblaciones"); //3 OE
		File[] listOfFiles = miDir.listFiles();  //2 OE
		for (File file : listOfFiles) { //7n
			if (file.isFile()) { //1 OE
				try {
					nombre_xmls.add(miDir.getCanonicalPath() + "\\" + file.getName()); //6 OE
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); //1 OE
				}
			}
		}
		return nombre_xmls; //1 OE
	} // T(n) = 7n + 7 -- O(n)

	/**
	 * Con la lista de nombres de las poblaciones, obtenemos la lista de grafos.
	 *
	 * @param lista_archivos the lista archivos
	 * @return the grafos from XML
	 */
	public static ArrayList<Grafo> getGrafosFromXML(ArrayList<String> lista_archivos) {
		ArrayList<Grafo> lista_grafos = new ArrayList<Grafo>(); //1 OE
		for (String nombre_archivo : lista_archivos) { //4n
			lista_grafos.add(new Grafo(nombre_archivo)); //4 OE
		}
		return lista_grafos; //1 OE
	} // T(n) = 4n + 2 -- O(n)

	/**
	 * Se genera y devuelve el grafo de los nodos raices de todas las poblaciones.
	 *
	 * @param lista_raices the lista raices
	 * @return the grafo
	 */
	public static Grafo generarGrafoRaices(ArrayList<Nodo> lista_raices) {
		Grafo g = new Grafo(); //2 OE
		g.setNombre_poblacion("Raices poblaciones "); //2 OE
		ArrayList<Arco> arcos = get_arcos_unitarios(lista_raices); //3 OE

		for (Nodo n : lista_raices) //6n
			g.addNodo(n); //6 OE
		for (Arco a : arcos) { //6n
			g.addArco(a); //6 OE
		}

		return g; //1 OE
	} // T(n) = 12n + 8 -- O(n)

	/**
	 * Devuelve una lista de arcos los cuales son los arcos unitarios que unen los nodos raices
	 * de todas las poblaciones.
	 *
	 * @param lista_nodos_raices the lista nodos raices
	 * @return the arcos unitarios
	 */
	public static ArrayList<Arco> get_arcos_unitarios(ArrayList<Nodo> lista_nodos_raices) {
		int i = 0; //1 OE
		ArrayList<Arco> lista_arcos_unitarios = new ArrayList<Arco>(); //1 OE
		for (Nodo n : lista_nodos_raices) { //10n^3 + 10n^2
			for (Nodo n_1 : lista_nodos_raices) { //10n^2 + 10n
				if (!n.equals(n_1) && checkRepetidos(lista_arcos_unitarios, n, n_1) == false) { //10n + 10
					lista_arcos_unitarios.add(new Arco("nuevoArco_" + i++, n, n_1,
							Math.sqrt((Math.pow((n_1.getX() - n.getX()), 2) + (Math.pow((n_1.getY() - n.getY()), 2))))
									* 100000)); //27 OE
				}
			}
		}
		return lista_arcos_unitarios; //1 OE
	} // T(n) = 10n^3 + 10n^2 + 3 -- O(n^3)

	/**
	 * Comprueba si hay algun arco repetido en la lista de arcos unitarios, para ello son necesarias
	 * las ids de los nodos origen y destino.
	 *
	 * @param arcos the arcos
	 * @param n1    the n 1
	 * @param n2    the n 2
	 * @return true, if successful
	 */
	public static boolean checkRepetidos(ArrayList<Arco> arcos, Nodo n1, Nodo n2) {
		for (Arco a : arcos) { //10n
			if (a.getNodo_destino().equals(n1) && a.getNodo_origen().equals(n2)) { //9 OE
				return true; //1 OE
			} else if (a.getNodo_destino().equals(n2) && a.getNodo_origen().equals(n1)) { //9 OE
				return true; //1 OE
			}
		}
		return false; //1 OE
	} // T(n) = 10n + 1 -- O(n)

}
