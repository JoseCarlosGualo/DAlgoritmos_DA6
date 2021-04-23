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
		ArrayList<Nodo> nodos_raiz = new ArrayList<Nodo>();
		Grafo grafo_principal = new Grafo();
		int nodos_totales = 0;

		for (int i = 0; i < lista_grafos.size(); i++) {
			Grafo g = lista_grafos.get(i);
			g.quicksort();
			Grafo kruskal = g.kruskal();
			lista_kruskal.add(kruskal);
			Nodo nodo = lista_kruskal.get(i).getRootNode();
			nodos_raiz.add(nodo);
			grafo_principal.addListaArco(kruskal.getLista_arcos());
			grafo_principal.addListaNodo(kruskal.getLista_nodos());
			nodos_totales += kruskal.getLista_nodos().size();
			// System.out.println(lista_kruskal.get(i).nombre_poblacion + " Raiz: " +
			// nodo.toString());
		}

		grafo_principal.addListaArco(get_arcos_unitarios(nodos_raiz));
		System.out.println("En el problema: " + nodos_totales);
		System.out.println("Nodos grafo principal: " + grafo_principal.getLista_nodos().size());
		System.out.println("Arcos en principal: " + grafo_principal.getLista_arcos().size());
		System.out.println("Nodos raiz: " + nodos_raiz.size());
		System.out.println("poblaciones: " + lista_grafos.size());
		grafo_principal.setNombre_poblacion("Provincia");
		grafo_principal.dibujarGrafo();
		grafo_principal.quicksort();
		System.out.println("hola");
		Grafo arbol_minimo_principal = grafo_principal.kruskal();
		System.out.println(arbol_minimo_principal.getLista_arcos().size());
		System.out.println(arbol_minimo_principal.getLista_nodos().size());
		System.out.println("hace kruskal");
		// Grafo arbol_minimo_principal = grafo_principal.prim();
		arbol_minimo_principal.setNombre_poblacion("Provincia_kruskal");
		// arbol_minimo_principal.dibujarGrafo();
		Nodo nodo_raiz_principal = arbol_minimo_principal.getRootNodeCiu();
		System.out.println("coge nodo raiz");

		
		System.out.println(arbol_minimo_principal.nombre_poblacion + " Raiz: " + nodo_raiz_principal.toString());

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

	public static ArrayList<Arco> get_arcos_unitarios(ArrayList<Nodo> lista_nodos_raices) {
		int i = 0;
		ArrayList<Arco> lista_arcos_unitarios = new ArrayList<Arco>();
		for (Nodo n : lista_nodos_raices) {
			for (Nodo n_1 : lista_nodos_raices) {
				if (!n.equals(n_1) && checkRepetidos(lista_arcos_unitarios, n, n_1) == false) {
					lista_arcos_unitarios.add(new Arco("nuevoArco_" + i++, n, n_1,
							Math.sqrt((Math.pow((n_1.getX() - n.getX()), 2) + (Math.pow((n_1.getY() - n.getY()), 2))))
									* 100000));
					// System.out.println(Math
					// .sqrt((Math.pow((n_1.getX() - n.getX()), 2) + (Math.pow((n_1.getY() -
					// n.getY()), 2))))*100000);
				}
			}
		}
		return lista_arcos_unitarios;
	}

	public static boolean checkRepetidos(ArrayList<Arco> arcos, Nodo n1, Nodo n2) {
		for (Arco a : arcos) {
			if (a.getNodo_destino().equals(n1) && a.getNodo_origen().equals(n2)) {
				return true;
			}
		}
		return false;
	}

}
