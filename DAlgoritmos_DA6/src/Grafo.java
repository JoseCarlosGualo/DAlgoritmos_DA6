import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import org.xml.sax.SAXException;

public class Grafo {
	String nombre_poblacion;
	private ArrayList<Arco> lista_arcos;
	private ArrayList<Nodo> lista_nodos;

	public Grafo(String xml) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser;
		try {
			saxParser = saxParserFactory.newSAXParser();
			File file = new File(xml);
			SAXHandler handler = new SAXHandler();
			saxParser.parse(file, handler);
			this.nombre_poblacion = handler.getNombre_poblacion();
			this.lista_arcos = handler.getLista_arcos();
			this.lista_nodos = handler.getLista_nodos();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Grafo() {
	}

	public String getNombre_poblacion() {
		return nombre_poblacion;
	}

	public void setNombre_poblacion(String nombre_poblacion) {
		this.nombre_poblacion = nombre_poblacion;
	}

	public ArrayList<Arco> getLista_arcos() {
		return lista_arcos;
	}

	public void setLista_arcos(ArrayList<Arco> lista_arcos) {
		this.lista_arcos = lista_arcos;
	}

	public ArrayList<Nodo> getLista_nodos() {
		return lista_nodos;
	}

	public void setLista_nodos(ArrayList<Nodo> lista_nodos) {
		this.lista_nodos = lista_nodos;
	}

	public void addNodo(Nodo nodo) {
		if (this.lista_nodos == null) {
			this.lista_nodos = new ArrayList<Nodo>();
		}
		this.lista_nodos.add(nodo);
	}

	public void addArco(Arco arco) {
		if (this.lista_arcos == null) {
			this.lista_arcos = new ArrayList<Arco>();
		}
		this.lista_arcos.add(arco);
	}

	public String toString() {
		return "Grafo{" + "Poblacion=" + this.nombre_poblacion + ", nodos=" + this.lista_nodos.toString() + ", arcos="
				+ this.lista_arcos.toString() + '}';
	}

	public double getXmaxima() {
		double max = Double.MIN_VALUE;
		for (Nodo n : this.lista_nodos) {
			if (Math.abs(n.getX()) > max) {
				max = Math.abs(n.getX());
			}
		}
		return max;
	}

	public double getYmaxima() {
		double max = Double.MIN_VALUE;
		for (Nodo n : this.lista_nodos) {
			if (Math.abs(n.getY()) > max) {
				max = Math.abs(n.getY());
			}
		}
		return max;
	}

	public double getXminima() {
		double min = Double.MAX_VALUE;
		for (Nodo n : this.lista_nodos) {
			if (Math.abs(n.getX()) < min) {
				min = Math.abs(n.getX());
			}
		}
		return min;
	}

	public double getYminima() {
		double min = Double.MAX_VALUE;
		for (Nodo n : this.lista_nodos) {
			if (Math.abs(n.getY()) < min) {
				min = Math.abs(n.getY());
			}
		}
		return min;
	}

	public void dibujarGrafo() throws IOException {
		int width = 2000;
		int height = 2000;

		double Xmaxima = this.getXmaxima();
		double Ymaxima = this.getYmaxima();
		double diffX = Xmaxima - this.getXminima();
		double diffY = Ymaxima - this.getYminima();

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();

		for (Arco a : this.lista_arcos) {
			g2d.setStroke(new BasicStroke(2));
			g2d.setColor(Color.WHITE);
			g2d.draw(new Line2D.Double(((Xmaxima - Math.abs(a.getNodo_origen().getX())) / diffX) * width,
					((Ymaxima - Math.abs(a.getNodo_origen().getY())) / diffY) * height,
					((Xmaxima - Math.abs(a.getNodo_destino().getX())) / diffX) * width,
					((Ymaxima - Math.abs(a.getNodo_destino().getY())) / diffY) * height));

		}

		for (Nodo n : this.lista_nodos) {

			if (n.getId_nodo().equalsIgnoreCase("765350888")) {
				g2d.setColor(Color.BLUE);
			} else {
				g2d.setColor(Color.RED);
			}

			g2d.setStroke(new BasicStroke(2));

			double xnodo = (((Xmaxima - Math.abs(n.getX())) / diffX) * width) - 2;
			double ynodo = (((Ymaxima - Math.abs(n.getY())) / diffY) * height) - 2;
			g2d.draw(new Ellipse2D.Double(xnodo, ynodo, 4, 4));
		}

		g2d.dispose();
		File miDir = new File("ImagenesPoblaciones");
		miDir.mkdirs();

		File file = new File(miDir.getCanonicalPath() + "\\" + this.nombre_poblacion + ".png");
		ImageIO.write(bufferedImage, "png", file);
	}

	public void crear_graphml() {

		ArrayList<String> attr_names = new ArrayList<String>();
		ArrayList<String> ids = new ArrayList<String>();
		attr_names.add("id_edge");
		attr_names.add("length");
		attr_names.add("x");
		attr_names.add("y");
		attr_names.add("name");
		ids.add("d4");
		ids.add("d3");
		ids.add("d2");
		ids.add("d1");
		ids.add("d0");
		Element key;
		Element nodo;
		Element edge;
		Element graph;
		Element data;
		Element data2;
		Attr attr_name;
		Attr attr_id;
		Attr source_node;
		Attr target_node;
		Attr attr_key;
		Attr attr_key2;
		Text text;

		try {
			// Creo una instancia de DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// Creo un documentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Creo un DOMImplementation
			DOMImplementation implementation = builder.getDOMImplementation();

			// Creo un documento con un elemento raiz
			Document documento = implementation.createDocument(null, "graphml", null);
			documento.setXmlVersion("1.0");

			graph = documento.createElement("graph");

			for (int i = 0; i < ids.size(); i++) {
				key = documento.createElement("key");
				attr_name = documento.createAttribute("attr.name");
				attr_name.setValue(attr_names.get(i));
				key.setAttributeNode(attr_name);
				attr_id = documento.createAttribute("id");
				attr_id.setValue(ids.get(i));
				key.setAttributeNode(attr_id);
				documento.getDocumentElement().appendChild(key);
			}

			for (Nodo n : this.lista_nodos) {
				nodo = documento.createElement("node");
				Attr id_nodo = documento.createAttribute("id");
				id_nodo.setValue(n.getId_nodo());
				nodo.setAttributeNode(id_nodo);
				for (int i = 0; i < 2; i++) {
					data = documento.createElement("data");
					attr_key = documento.createAttribute("key");
					if (i == 0) {
						attr_key.setValue("d1");
						data.setAttributeNode(attr_key);
						text = documento.createTextNode(String.valueOf(n.getY()));
					} else {
						attr_key.setValue("d2");
						data.setAttributeNode(attr_key);
						text = documento.createTextNode(String.valueOf(n.getX()));
					}
					data.appendChild(text);
					nodo.appendChild(data);
				}
				graph.appendChild(nodo);
			}

			for (Arco a : this.lista_arcos) {
				edge = documento.createElement("edge");
				source_node = documento.createAttribute("source");
				source_node.setValue(a.getNodo_origen().getId_nodo());
				edge.setAttributeNode(source_node);
				target_node = documento.createAttribute("target");
				target_node.setValue(a.getNodo_destino().getId_nodo());
				edge.setAttributeNode(target_node);
				data = documento.createElement("data");
				attr_key = documento.createAttribute("key");
				attr_key.setValue("d3");
				data.setAttributeNode(attr_key);
				text = documento.createTextNode(String.valueOf(a.getLength()));
				data.appendChild(text);
				edge.appendChild(data);
				data2 = documento.createElement("data");
				attr_key2 = documento.createAttribute("key");
				attr_key2.setValue("d4");
				data2.setAttributeNode(attr_key2);
				text = documento.createTextNode(String.valueOf(a.getId()));
				data2.appendChild(text);
				edge.appendChild(data2);
				graph.appendChild(edge);
			}

			data = documento.createElement("data");
			attr_key = documento.createAttribute("key");
			attr_key.setValue("d0");
			data.setAttributeNode(attr_key);
			text = documento.createTextNode(this.nombre_poblacion);
			data.appendChild(text);
			graph.appendChild(data);

			documento.getDocumentElement().appendChild(graph);

			File miDir = new File("GraphmlGenerados");
			miDir.mkdirs();
			Source source = new DOMSource(documento);
			// Creo el Result, indicado que fichero se va a crear
			Result result = new StreamResult(
					new File(miDir.getCanonicalPath() + "\\" + this.nombre_poblacion + ".graphml"));

			// Creo un transformer, se crea el fichero XML
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// metodo que devuelve el nombre de la poblacion
	public String separarNombre() {
		return this.nombre_poblacion.split(",")[0];
	}

	public int partition(ArrayList<Arco> a, int left, int right) {
		return HoarePartition(a, left, right); // Using Hoare partition
	}

	/**
	 * Method implementing Hoare's partition
	 * 
	 * @param a     The array to partition
	 * @param left  Starting index of subarray
	 * @param right Finish index of subarray
	 * @return The pivot position
	 */
	public int HoarePartition(ArrayList<Arco> a, int left, int right) {
		int l = left, r = right + 1;
		double p = a.get(left).getLength(), aux;
		while (l < r) {
			while (l < right && a.get(++l).getLength() < p)
				;
			while (r > left && p < a.get(--r).getLength())
				;
			if (l < r) {
				aux = a.get(l).getLength();
				a.get(l).setLength(a.get(r).getLength());
				a.get(r).setLength(aux);
			}
		}
		a.get(left).setLength(a.get(r).getLength());
		a.get(r).setLength(p);
		return r;
	}

	/**
	 * Wrapper method for calling the actual, divide and conquer, quicksort method.
	 * 
	 * @param array The array to sort. Ii is sorted at the end.
	 */
	public void quicksort() {
		quicksortRec(this.lista_arcos, 0, this.lista_arcos.size() - 1);
	}

	/**
	 * Quicksort.
	 * 
	 * @param array The array to sort. Ii is sorted at the end.
	 * @param left  Starting index of subarray.
	 * @param right Finish index of subarray,
	 */
	public void quicksortRec(ArrayList<Arco> array, int left, int right) {
		if (left < right) {
			int p = partition(array, left, right); // Divide
			quicksortRec(array, left, p - 1); // Conquer
			quicksortRec(array, p + 1, right);
		}
	}

	public int find(ArrayList<ArrayList<Nodo>> componentesConexas, Nodo nodo) {
		int pos = -1;
		for (int i = 0; i < componentesConexas.size() && pos == -1; i++) {
			if (componentesConexas.get(i).contains(nodo)) {
				pos = i;
			}
		}
		return pos;
	}

	public void union(ArrayList<ArrayList<Nodo>> componentesConexas, int x, int y) {
		for (int i = 0; i < componentesConexas.get(y).size(); i++) {
			componentesConexas.get(x).add(componentesConexas.get(y).get(i));
		}
		componentesConexas.remove(y);
	}

	public Grafo kruskal() {
		Grafo grafo = new Grafo();
		ArrayList<Arco> arcos = new ArrayList<Arco>();
		ArrayList<ArrayList<Nodo>> componentesConexas = new ArrayList<ArrayList<Nodo>>();
		ArrayList<Arco> arcos_resultado = new ArrayList<Arco>();
		grafo.setNombre_poblacion(this.separarNombre() + "-kruskal");
		grafo.setLista_nodos(this.lista_nodos);

		for (int i = 0; i < this.lista_nodos.size(); i++) {
			ArrayList<Nodo> componente = new ArrayList<Nodo>();
			componente.add(this.lista_nodos.get(i));
			componentesConexas.add(componente);
		}

		for (int i = 0; i < this.lista_arcos.size(); i++) {
			arcos.add(new Arco(this.lista_arcos.get(i).getId(), this.lista_arcos.get(i).getNodo_origen(),
					this.lista_arcos.get(i).getNodo_destino(), this.lista_arcos.get(i).getLength()));
		}

		do {
			Arco arco0 = arcos.get(0);
			arcos.remove(0);
			int x = find(componentesConexas, arco0.getNodo_origen());
			int y = find(componentesConexas, arco0.getNodo_destino());
			if (x != y) {
				union(componentesConexas, x, y);
				arcos_resultado.add(arco0);
			}
		} while (componentesConexas.size() > 1);

		grafo.setLista_arcos(arcos_resultado);
		return grafo;
	}

	public Grafo prim() {
		Grafo grafo = new Grafo();
		ArrayList<Arco> arcos = new ArrayList<Arco>();
		ArrayList<Arco> arcos_resultado = new ArrayList<Arco>();
		HashSet<Nodo> b = new HashSet<Nodo>();
		ArrayList<Nodo> nodos = new ArrayList<Nodo>();
		grafo.setNombre_poblacion(this.separarNombre() + "-prim");
		grafo.setLista_nodos(this.lista_nodos);

		for (int i = 0; i < this.lista_arcos.size(); i++) {
			arcos.add(new Arco(this.lista_arcos.get(i).getId(), this.lista_arcos.get(i).getNodo_origen(),
					this.lista_arcos.get(i).getNodo_destino(), this.lista_arcos.get(i).getLength()));
		}

		for (int i = 0; i < this.lista_nodos.size(); i++) {
			nodos.add(new Nodo(this.lista_nodos.get(i).getX(), this.lista_nodos.get(i).getY(),
					this.lista_nodos.get(i).getId_nodo()));
		}

		int numero = (int) (Math.random() * this.lista_arcos.size());
		b.add(this.lista_arcos.get(numero).getNodo_origen());

		while (b.size() < nodos.size()) {

			Arco arco0 = encontrar(b, arcos);
			b.add(arco0.getNodo_destino());
			b.add(arco0.getNodo_origen());
			arcos_resultado.add(arco0);
		}

		grafo.setLista_arcos(arcos_resultado);
		return grafo;
	}

	public Arco encontrar(HashSet<Nodo> b, ArrayList<Arco> arcos_ordenados) {
		Arco a = null;
		for (int i = 0; i < arcos_ordenados.size() && a == null; i++) {
			Arco aux = arcos_ordenados.get(i);

			if ((b.contains(aux.getNodo_origen()) && !b.contains(aux.getNodo_destino()))
					|| (!b.contains(aux.getNodo_origen()) && b.contains(aux.getNodo_destino()))) {
				a = aux;
			}
		}
		return a;
	}

	public void cuenta_nodos_subArbolAux(Nodo nodo) {
		ArrayList<Arco> arcos_anexos = new ArrayList<Arco>();
		arcos_anexos = nodo.getArcosIncidentes(this.lista_arcos);

		for (Arco a : arcos_anexos) {
			if (a.getNodo_origen().equals(nodo)) {
				System.out.println(cuenta_nodos_subArbol(a.getNodo_destino(), a));
			} else if (a.getNodo_destino().equals(nodo)) {
				System.out.println(cuenta_nodos_subArbol(a.getNodo_origen(), a));
			}

		}
	}

	public int cuenta_nodos_subArbol(Nodo nodo, Arco arco) {
		int total = 1;
		ArrayList<Arco> arcos_anexos = new ArrayList<Arco>();
		arcos_anexos = nodo.getArcosIncidentes(this.lista_arcos);
		if (arcos_anexos.size() == 1) {
			return total;
		}

		for (Arco a : arcos_anexos) {
			if (a != arco) {
				if (a.getNodo_origen().equals(nodo)) {
					total += cuenta_nodos_subArbol(a.getNodo_destino(), a);
				} else if (a.getNodo_destino().equals(nodo)) {
					total += cuenta_nodos_subArbol(a.getNodo_origen(), a);
				}

			}

		}

		return total;
	}

	public void prueba() {
		for (Nodo n : this.lista_nodos) {
			if (n.getId_nodo().equalsIgnoreCase("1050180142")) {
				cuenta_nodos_subArbolAux(n);
			}
		}
	}

	public int cuenta_nodos_hoja(Nodo nodo) {
		int suma = 0;
		ArrayList<Arco> arcos_anexos = new ArrayList<Arco>();
		arcos_anexos = nodo.getArcosIncidentes(this.lista_arcos);

		for (Arco a : arcos_anexos) {
			if (a.getNodo_origen().equals(nodo)) {
				suma += cuenta_nodos_hoja(a.getNodo_destino(), a);
			} else if (a.getNodo_destino().equals(nodo)) {
				suma += cuenta_nodos_hoja(a.getNodo_origen(), a);
			}

		}
		return suma;
	}

	public int cuenta_nodos_hoja(Nodo nodo, Arco arco) {
		int hojas = 0;
		for(Nodo n : lista_nodos) {
			if(n.getArcosIncidentes(lista_arcos).size() == 1) {
				hojas++;
			}
		}
		return hojas;
	}
	

	public void prueba_altura() {
		for (Nodo n : this.lista_nodos) {

			System.out.println(cuenta_nodos_hoja(n));

		}
	}

	public int cuenta_distancia_nodos_hoja(Nodo nodo, int media, double minimo, ArrayList<Arco> arcos) {

		int suma = 0;
		ArrayList<Arco> arcos_anexos = new ArrayList<Arco>();
		arcos_anexos = nodo.getArcosIncidentes(arcos);
		for (Arco a : arcos_anexos) {
			arcos.remove(a);
			if (a.getNodo_origen().equals(nodo)) {
				suma += cuenta_distancia_nodos_hoja(a.getNodo_destino(), a, 1, media, minimo, arcos);
			} else if (a.getNodo_destino().equals(nodo)) {
				suma += cuenta_distancia_nodos_hoja(a.getNodo_origen(), a, 1, media, minimo, arcos);
			}

		}
		return suma;

	}

	public int cuenta_distancia_nodos_hoja(Nodo nodo, Arco arco, int acumulado, int media, double minimo, ArrayList<Arco> arcos) {
		int total = 0;
		ArrayList<Arco> arcos_anexos = new ArrayList<Arco>();
		arcos_anexos = nodo.getArcosIncidentes(arcos);
		
		
		double check = acumulado/media;
		if (check > minimo)
			return Integer.MAX_VALUE;
		if (arcos_anexos.size() == 0) {
			return acumulado;
		} else {
			for (Arco a : arcos_anexos) {
				if (a != arco) {
					arcos.remove(a);
					if (a.getNodo_origen().equals(nodo)) {
						total += cuenta_distancia_nodos_hoja(a.getNodo_destino(), a, acumulado + 1, media, minimo,arcos);
					} else if (a.getNodo_destino().equals(nodo)) {
						total += cuenta_distancia_nodos_hoja(a.getNodo_origen(), a, acumulado + 1, media, minimo,arcos);
					}
				}
			}
		}
		
		
		return total;
	}

	public void prueba_distancia() {
		for (Nodo n : this.lista_nodos) {

			System.out.println(cuenta_distancia_nodos_hoja(n, 0, 999, lista_arcos));

		}
	}
	
	public Nodo encontrarRaiz() {
		Nodo raiz = new Nodo();
		int media = 0;
		int distancia = 0;
		double aux = Integer.MAX_VALUE;
		media = cuenta_nodos_hoja(lista_nodos.get(0));
		
		
		for(Nodo n : lista_nodos) {
			ArrayList<Arco> arcos = new ArrayList<Arco>();
			for(Arco a : lista_arcos)
				arcos.add(a);
			
			distancia = cuenta_distancia_nodos_hoja(n, media, aux, arcos);
			if((distancia/media) < aux) {
				raiz = n;
				aux = (distancia/media);
				
			}
		}
		
		return raiz;
		
	}

}
