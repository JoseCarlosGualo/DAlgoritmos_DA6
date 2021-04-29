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

// TODO: Auto-generated Javadoc
/**
 * The Class Grafo.
 */
public class Grafo {

	/** The nombre poblacion. */
	String nombre_poblacion;

	/** The lista arcos. */
	private ArrayList<Arco> lista_arcos;

	/** The lista nodos. */
	private ArrayList<Nodo> lista_nodos;

	/**
	 * Instantiates a new grafo.
	 *
	 * @param xml the xml
	 */
	public Grafo(String xml) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); //2 OE
		SAXParser saxParser;
		try {
			saxParser = saxParserFactory.newSAXParser(); //2 OE
			File file = new File(xml); //3 OE
			SAXHandler handler = new SAXHandler(); //2 OE
			saxParser.parse(file, handler); //3 OE
			this.nombre_poblacion = handler.getNombre_poblacion(); //2 OE
			this.lista_arcos = handler.getLista_arcos(); //2 OE
			this.lista_nodos = handler.getLista_nodos(); //2 OE
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //1 OE
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //1 OE
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //1 OE
		}

	} // T(n) = 18 OE -- O(1)

	/**
	 * Instantiates a new grafo.
	 */
	public Grafo() {
	}

	/**
	 * Gets the nombre poblacion.
	 *
	 * @return the nombre poblacion
	 */
	public String getNombre_poblacion() {
		return nombre_poblacion; //1 OE

	} // T(n) = 1 OE -- O(1)

	/**
	 * Sets the nombre poblacion.
	 *
	 * @param nombre_poblacion the new nombre poblacion
	 */
	public void setNombre_poblacion(String nombre_poblacion) {
		this.nombre_poblacion = nombre_poblacion; //1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Gets the lista arcos.
	 *
	 * @return the lista arcos
	 */
	public ArrayList<Arco> getLista_arcos() {
		return lista_arcos; //1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Sets the lista arcos.
	 *
	 * @param lista_arcos the new lista arcos
	 */
	public void setLista_arcos(ArrayList<Arco> lista_arcos) { 
		this.lista_arcos = lista_arcos; //1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Gets the lista nodos.
	 *
	 * @return the lista nodos
	 */
	public ArrayList<Nodo> getLista_nodos() {
		return lista_nodos; //1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Sets the lista nodos.
	 *
	 * @param lista_nodos the new lista nodos
	 */
	public void setLista_nodos(ArrayList<Nodo> lista_nodos) {
		this.lista_nodos = lista_nodos; //1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Adds the nodo.
	 *
	 * @param nodo the nodo
	 */
	public void addNodo(Nodo nodo) {
		if (this.lista_nodos == null) { //1 OE
			this.lista_nodos = new ArrayList<Nodo>(); //1 OE
		}
		this.lista_nodos.add(nodo); //3 OE
	} // T(n) = 5 OE -- O(1)

	/**
	 * Adds the arco.
	 *
	 * @param arco the arco
	 */
	public void addArco(Arco arco) {
		if (this.lista_arcos == null) { //1 OE
			this.lista_arcos = new ArrayList<Arco>(); //1 OE
		}
		this.lista_arcos.add(arco); //1 OE
	} // T(n) = 5 OE -- O(1)

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Grafo{" + "Poblacion=" + this.nombre_poblacion + ", nodos=" + this.lista_nodos.toString() + ", arcos="
				+ this.lista_arcos.toString() + '}'; //9 OE
	} // T(n) = 9 OE -- O(1)

	/**
	 * Gets the xmaxima.
	 *
	 * @return the xmaxima
	 */
	public double getXmaxima() {
		double max = Double.MIN_VALUE; //1 OE
		for (Nodo n : this.lista_nodos) { //10n
			if (Math.abs(n.getX()) > max) { //5 OE
				max = Math.abs(n.getX()); //5 OE
			}
		}
		return max; //1 OE
	} // T(n) = 10n + 2 -- O(n)

	/**
	 * Gets the ymaxima.
	 *
	 * @return the ymaxima
	 */
	public double getYmaxima() {
		double max = Double.MIN_VALUE; //1 OE
		for (Nodo n : this.lista_nodos) { //10n
			if (Math.abs(n.getY()) > max) { //5 OE
				max = Math.abs(n.getY()); //5 OE
			}
		}
		return max; //1 OE
	} // T(n) = 10n + 2 -- O(n)

	/**
	 * Gets the xminima.
	 *
	 * @return the xminima
	 */
	public double getXminima() {
		double min = Double.MAX_VALUE; //1 OE
		for (Nodo n : this.lista_nodos) { //10n
			if (Math.abs(n.getX()) < min) { //5 OE
				min = Math.abs(n.getX()); //5 OE
			}
		}
		return min; //1 OE
	} // T(n) = 10n + 2 -- O(n)

	/**
	 * Gets the yminima.
	 *
	 * @return the yminima
	 */
	public double getYminima() {
		double min = Double.MAX_VALUE; //1 OE
		for (Nodo n : this.lista_nodos) { //10n
			if (Math.abs(n.getY()) < min) { //5 OE
				min = Math.abs(n.getY()); //5 OE
			}
		}
		return min; //1 OE
	} // T(n) = 10n + 2 -- O(n)

	/**
	 * Dibujar grafo.
	 *
	 * @param raiz the raiz
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void dibujarGrafo(Nodo raiz) throws IOException {
		int width = 2000; //1 OE
		int height = 2000; //1 OE

		double Xmaxima = this.getXmaxima(); //10n + 4
		double Ymaxima = this.getYmaxima(); //10n + 4
		double diffX = Xmaxima - this.getXminima(); //10n + 5
		double diffY = Ymaxima - this.getYminima(); //10n + 5

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //1 OE
		Graphics2D g2d = bufferedImage.createGraphics(); //2 OE

		for (Arco a : this.lista_arcos) { //38n + 30
			g2d.setStroke(new BasicStroke(2)); //3 OE
			g2d.setColor(Color.WHITE); //2 OE
			g2d.draw(new Line2D.Double(((Xmaxima - Math.abs(a.getNodo_origen().getX())) / diffX) * width,
					((Ymaxima - Math.abs(a.getNodo_origen().getY())) / diffY) * height,
					((Xmaxima - Math.abs(a.getNodo_destino().getX())) / diffX) * width,
					((Ymaxima - Math.abs(a.getNodo_destino().getY())) / diffY) * height)); //38n + 25

		}

		for (Nodo n : this.lista_nodos) { //12n + 37

			if (n.getId_nodo().equalsIgnoreCase(raiz.getId_nodo())) { //5 OE
				g2d.setColor(Color.GREEN); //1 OE
			} else {
				g2d.setColor(Color.RED); //1 OE
			}

			g2d.setStroke(new BasicStroke(2)); //1 OE

			double xnodo = (((Xmaxima - Math.abs(n.getX())) / diffX) * width) - 2; //6n + 11
			double ynodo = (((Ymaxima - Math.abs(n.getY())) / diffY) * height) - 2; //6n + 11
			g2d.draw(new Ellipse2D.Double(xnodo, ynodo, 4, 4)); //8 OE
		}

		g2d.dispose(); //1 OE
		File miDir = new File("ImagenesPoblaciones"); //2 OE
		miDir.mkdirs(); //1 OE

		File file = new File(miDir.getCanonicalPath() + "\\" + this.nombre_poblacion + ".png"); //2 OE
		ImageIO.write(bufferedImage, "png", file); //1 OE
	}// T(n) = 42n + 99 -- O(n)

	/**
	 * Crear graphml.
	 */
	public void crear_graphml() {

		ArrayList<String> attr_names = new ArrayList<String>(); //1 OE
		ArrayList<String> ids = new ArrayList<String>(); //1 OE
		attr_names.add("id_edge"); //1 OE
		attr_names.add("length"); //1 OE
		attr_names.add("x"); //1 OE
		attr_names.add("y"); //1 OE
		attr_names.add("name"); //1 OE
		ids.add("d4"); //1 OE
		ids.add("d3"); //1 OE
		ids.add("d2"); //1 OE
		ids.add("d1"); //1 OE
		ids.add("d0"); //1 OE
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
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //1 OE
			// Creo un documentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder(); //2 OE
			// Creo un DOMImplementation
			DOMImplementation implementation = builder.getDOMImplementation(); //2 OE

			// Creo un documento con un elemento raiz
			Document documento = implementation.createDocument(null, "graphml", null); //2 OE
			documento.setXmlVersion("1.0"); //1 OE

			graph = documento.createElement("graph"); //2 OE
			
			//3
			for (int i = 0; i < ids.size(); i++) {//17n + 3
				key = documento.createElement("key"); //2 OE
				attr_name = documento.createAttribute("attr.name"); //2 OE
				attr_name.setValue(attr_names.get(i)); //2 OE
				key.setAttributeNode(attr_name); //1 OE
				attr_id = documento.createAttribute("id"); //2 OE
				attr_id.setValue(ids.get(i)); //2 OE
				key.setAttributeNode(attr_id); //1 OE
				documento.getDocumentElement().appendChild(key); //2 OE
			}

			for (Nodo n : this.lista_nodos) { //54n
				nodo = documento.createElement("node"); //3 OE
				Attr id_nodo = documento.createAttribute("id"); //3 OE
				id_nodo.setValue(n.getId_nodo()); //3 OE
				nodo.setAttributeNode(id_nodo); //2 OE
				for (int i = 0; i < 2; i++) { //41
					data = documento.createElement("data"); //2 OE
					attr_key = documento.createAttribute("key"); //2 OE
					if (i == 0) { //1 OE
						attr_key.setValue("d1"); //2 OE
						data.setAttributeNode(attr_key); //2 OE
						text = documento.createTextNode(String.valueOf(n.getY())); //6 OE
					} else { //1 OE
						attr_key.setValue("d2"); //2 OE
						data.setAttributeNode(attr_key); //2 OE
						text = documento.createTextNode(String.valueOf(n.getX())); //6 OE
					}
					data.appendChild(text); //2 OE
					nodo.appendChild(data); //2 OE
				}
				graph.appendChild(nodo); //2 OE
			}

			for (Arco a : this.lista_arcos) { //64n
				edge = documento.createElement("edge"); //3 OE
				source_node = documento.createAttribute("source"); //3 OE
				source_node.setValue(a.getNodo_origen().getId_nodo()); //5 OE
				edge.setAttributeNode(source_node); //2 OE
				target_node = documento.createAttribute("target"); //3 OE
				target_node.setValue(a.getNodo_destino().getId_nodo()); //5 OE
				edge.setAttributeNode(target_node); //2 OE
				data = documento.createElement("data"); //3 OE
				attr_key = documento.createAttribute("key"); //3 OE
				attr_key.setValue("d3"); //2 OE
				data.setAttributeNode(attr_key); //2 OE
				text = documento.createTextNode(String.valueOf(a.getLength())); //6 OE
				data.appendChild(text); //2 OE
				edge.appendChild(data); //2 OE
				data2 = documento.createElement("data"); //3 OE
				attr_key2 = documento.createAttribute("key"); //3 OE
				attr_key2.setValue("d4"); //2 OE
				data2.setAttributeNode(attr_key2); //2 OE
				text = documento.createTextNode(String.valueOf(a.getId())); //6 OE
				data2.appendChild(text); //2 OE
				edge.appendChild(data2); //2 OE
				graph.appendChild(edge); //2 OE
			}

			data = documento.createElement("data"); //3 OE
			attr_key = documento.createAttribute("key"); //3 OE
			attr_key.setValue("d0"); //2 OE
			data.setAttributeNode(attr_key); //2 OE
			text = documento.createTextNode(this.nombre_poblacion); //3 OE
			data.appendChild(text); //2 OE
			graph.appendChild(data); //2 OE

			documento.getDocumentElement().appendChild(graph); //3 OE

			File miDir = new File("GraphmlGenerados"); //3 OE
			miDir.mkdirs(); //2 OE
			Source source = new DOMSource(documento); //3 OE
			// Creo el Result, indicado que fichero se va a crear
			Result result = new StreamResult(
					new File(miDir.getCanonicalPath() + "\\" + this.nombre_poblacion + ".graphml")); //6 OE

			// Creo un transformer, se crea el fichero XML
			Transformer transformer = TransformerFactory.newInstance().newTransformer(); //3 OE
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //3 OE
			transformer.transform(source, result); //3 OE

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); //1 OE
		}
	} // T(n) = 135n + 67 -- O(n)

	/**
	 * Separar nombre. metodo que devuelve el nombre de la poblacion
	 * 
	 * @return the string
	 */
	public String separarNombre() {
		return this.nombre_poblacion.split(",")[0];// n + 1
	} // T(n)=n + 1 -- O(n)

	/**
	 * Partition.
	 *
	 * @param a     the a
	 * @param left  the left
	 * @param right the right
	 * @return the int
	 */
	public int partition(ArrayList<Arco> a, int left, int right) {
		return HoarePartition(a, left, right); // Using Hoare partition 12n^2 + 10n + 14
	}// T(n) = 14n^2 + 24n + 23 -- O(n^2)

	/**
	 * Method implementing Hoare's partition.
	 *
	 * @param a     The array to partition
	 * @param left  Starting index of subarray
	 * @param right Finish index of subarray
	 * @return The pivot position
	 */
	public int HoarePartition(ArrayList<Arco> a, int left, int right) {
		int l = left, r = right + 1; //3 OE
		double p = a.get(left).getLength(), aux; //4 OE
		while (l < r) { //14n^2 + 24n + 1
			while (l < right && a.get(++l).getLength() < p) //7n + 3
				;
			while (r > left && p < a.get(--r).getLength()) //7n + 3
				;
			if (l < r) { //1 OE
				aux = a.get(l).getLength(); //4 OE
				a.get(l).setLength(a.get(r).getLength()); //8 OE
				a.get(r).setLength(aux); //4 OE
			}
		}
		a.get(left).setLength(a.get(r).getLength()); //7 OE
		a.get(r).setLength(p); //4 OE
		return r; //1 OE
	} // T(n) = 14n^2 + 24n + 18 -- O(n^2)

	/**
	 * Metodo quickshort implementado con divide y venceras.
	 */
	public void quicksort() {
		quicksortRec(this.lista_arcos, 0, this.lista_arcos.size() - 1); // nlog2n + 6
	} // T(n) = nlog2n + 6 -- O(nlog2n)

	/**
	 * Quicksort.
	 * 
	 * @param array The array to sort. Ii is sorted at the end.
	 * @param left  Starting index of subarray.
	 * @param right Finish index of subarray,
	 */
	public void quicksortRec(ArrayList<Arco> array, int left, int right) {
		if (left < right) { //1 OE
			int p = partition(array, left, right);// 14n^2 + 24n + 28
			quicksortRec(array, left, p - 1); // nlog2n + 5
			quicksortRec(array, p + 1, right); // nlog2n + 5
		}
	} // T(n) = 2T(n/2) + n si n > 1 con T(1) = 0
	  // Se va reeemplazando. En la primera llamada obtenemos T(n) = 2T(n/2) + n 
	  // En la segunda llamada obtenemos T(n) = 4T(n/4) + 2n. Para el k-esimo llamado recursivo
	  // obtendriamos T(n) = 2^k * T(n/2^k) + kn. Se deduce que k = log2n y quedaría como T(n) = n * T(1) + nlog2n
	  // T(n) = nlog2n -- O(nlog2n)
	
	/**
	 * Find.
	 *
	 * @param componentesConexas the componentes conexas
	 * @param nodo               the nodo
	 * @return the int
	 */
	public int find(ArrayList<ArrayList<Nodo>> componentesConexas, Nodo nodo) {
		int pos = -1;// 1 OE
		for (int i = 0; i < componentesConexas.size() && pos == -1; i++) {// n^2 + n
			if (componentesConexas.get(i).contains(nodo)) {// n+1
				pos = i;// 1 OE
			}
		}
		return pos; // 1 OE
	} // T(n)=n^2 + n + 2 -- O(n^2)

	/**
	 * Union.
	 *
	 * @param componentesConexas the componentes conexas
	 * @param x                  the x
	 * @param y                  the y
	 */
	public void union(ArrayList<ArrayList<Nodo>> componentesConexas, int x, int y) {
		for (int i = 0; i < componentesConexas.get(y).size(); i++) {// 4n + 1
			componentesConexas.get(x).add(componentesConexas.get(y).get(i));// 4 OE
		}
		componentesConexas.remove(y);// n
	}// T(n)=5n + 5 -- O(n)

	/**
	 * Kruskal.
	 *
	 * @return the grafo
	 */
	public Grafo kruskal() {
		Grafo grafo = new Grafo(); // 1 OE
		ArrayList<Arco> arcos = new ArrayList<Arco>();// 1 OE
		ArrayList<ArrayList<Nodo>> componentesConexas = new ArrayList<ArrayList<Nodo>>();// 1 OE
		ArrayList<Arco> arcos_resultado = new ArrayList<Arco>();// 1 OE
		grafo.setNombre_poblacion(this.separarNombre() + "-kruskal");// n + 1 + 2
		grafo.setLista_nodos(this.lista_nodos);// 1 OE

		for (int i = 0; i < this.lista_nodos.size(); i++) { // 4n
			ArrayList<Nodo> componente = new ArrayList<Nodo>();// 1 OE
			componente.add(this.lista_nodos.get(i));// 2 OE
			componentesConexas.add(componente);// 1 OE
		}

		for (int i = 0; i < this.lista_arcos.size(); i++) {// 13n
			arcos.add(new Arco(this.lista_arcos.get(i).getId(), this.lista_arcos.get(i).getNodo_origen(),
					this.lista_arcos.get(i).getNodo_destino(), this.lista_arcos.get(i).getLength())); // 1 + 4 + 8 OE
		}

		do {// T(n)=n*log2n * (2n + 5) O(n) = n^2 *log2n peor caso, O(n) = n *log2n
			Arco arco0 = arcos.get(0);// 2 OE
			arcos.remove(0);// n
			int x = find(componentesConexas, arco0.getNodo_origen());// n^2 + 2
			int y = find(componentesConexas, arco0.getNodo_destino());// n^2 + 2
			if (x != y) {// 1 OE
				union(componentesConexas, x, y);// n
				arcos_resultado.add(arco0);// 1 OE
			}
		} while (componentesConexas.size() > 1);// 2 OE

		grafo.setLista_arcos(arcos_resultado);// 1 OE
		return grafo;// 1 OE
	} // T(n)=n*log2n * (2n + 5) + 18n + 10 -- O(n^2 *log2n), peor caso, O(n *log2n)


	/**
	 * Prim.
	 *
	 * @return the grafo
	 */
	public Grafo prim() {
		Grafo grafo = new Grafo();// 1 OE
		ArrayList<Arco> arcos = new ArrayList<Arco>();// 1 OE
		ArrayList<Arco> arcos_resultado = new ArrayList<Arco>();// 1 OE
		HashSet<Nodo> b = new HashSet<Nodo>();// 1 OE
		ArrayList<Nodo> nodos = new ArrayList<Nodo>();// 1 OE
		grafo.setNombre_poblacion(this.separarNombre() + "-prim");// n + 1 + 2
		grafo.setLista_nodos(this.lista_nodos);// 1 OE

		for (int i = 0; i < this.lista_arcos.size(); i++) {// 13n
			arcos.add(new Arco(this.lista_arcos.get(i).getId(), this.lista_arcos.get(i).getNodo_origen(),
					this.lista_arcos.get(i).getNodo_destino(), this.lista_arcos.get(i).getLength())); // 1 + 4 + 8 OE
		}

		for (int i = 0; i < this.lista_nodos.size(); i++) {// 10n
			nodos.add(new Nodo(this.lista_nodos.get(i).getX(), this.lista_nodos.get(i).getY(),
					this.lista_nodos.get(i).getId_nodo()));// 10 OE
		}

		int numero = (int) (Math.random() * this.lista_arcos.size());// 3 OE
		b.add(this.lista_arcos.get(numero).getNodo_origen());// 3 OE

		while (b.size() < nodos.size()) { // T(n) = 3 + log2n *(n+9) O(n) = n * log2n

			Arco arco0 = encontrar(b, arcos);// n + 1
			b.add(arco0.getNodo_destino());// 2 OE
			b.add(arco0.getNodo_origen());// 2 OE
			arcos_resultado.add(arco0);// 1 OE
		}

		grafo.setLista_arcos(arcos_resultado);// 1 OE
		return grafo;// 1 OE
	} // T(n) = 3 + log2n *(n+9) + 11 + 24n -- O(n * log2n)

	/**
	 * Encontrar.
	 *
	 * @param b               the b
	 * @param arcos_ordenados the arcos ordenados
	 * @return the arco
	 */
	public Arco encontrar(HashSet<Nodo> b, ArrayList<Arco> arcos_ordenados) {
		Arco a = null;// 1 OE
		for (int i = 0; i < arcos_ordenados.size() && a == null; i++) {//24n + 3
			Arco aux = arcos_ordenados.get(i);// 3 OE

			if ((b.contains(aux.getNodo_origen()) && !b.contains(aux.getNodo_destino()))
					|| (!b.contains(aux.getNodo_origen()) && b.contains(aux.getNodo_destino()))) {// 17 OE
				a = aux;// 1 OE
			}
		}
		return a;// 1 OE
	} // T(n) = 24n + 5 -- O(n)


	/**
	 * Cuenta nodos hoja.
	 *
	 * @param nodo the nodo
	 * @return the int
	 */
	public int cuenta_nodos_hoja(Nodo nodo) {
		int suma = 0; //1 OE
		ArrayList<Arco> arcos_anexos = new ArrayList<Arco>(); //1 OE
		arcos_anexos = nodo.getArcosIncidentes(this.lista_arcos); //12n + 5

		for (Arco a : arcos_anexos) {  //12n^3 + 6n^2 + 8n
			if (a.getNodo_origen().equals(nodo)) { //3 OE
				suma += cuenta_nodos_hoja(a.getNodo_destino(), a); //12n^2 + 6n + 8
			} else if (a.getNodo_destino().equals(nodo)) {  //3 OE
				suma += cuenta_nodos_hoja(a.getNodo_origen(), a); //12n^2 + 6n + 8
			}

		}
		return suma; //1 OE
	} // T(n) = 12n^3 + 6n^2 + 20n + 8 -- O(n^3)

	/**
	 * Cuenta nodos hoja.
	 *
	 * @param nodo the nodo
	 * @param arco the arco
	 * @return the int
	 */
	public int cuenta_nodos_hoja(Nodo nodo, Arco arco) {
		int hojas = 0; //1 OE
		for (Nodo n : lista_nodos) { //12n^2 + 6n
			if (n.getArcosIncidentes(lista_arcos).size() == 1) { //12n + 6
				hojas++; //1 OE
			}
		}
		return hojas; //1 OE
	} // T(n) = 12n^2 + 6n + 2 -- O(n^2)

	/**
	 * Cuenta distancia a los nodos hoja.
	 *
	 * @param nodo   the nodo
	 * @param media  the media
	 * @param minimo the minimo
	 * @param arcos  the arcos
	 * @return the int
	 */
	public int cuenta_distancia_nodos_hoja(Nodo nodo, int media, double minimo, ArrayList<Arco> arcos) {

		int suma = 0; //1 OE
		ArrayList<Arco> arcos_anexos = new ArrayList<Arco>(); //1 OE
		arcos_anexos = nodo.getArcosIncidentes(arcos); //12n + 5
		for (Arco a : arcos_anexos) { //2n^2 + 19n
			arcos.remove(a); //1 OE
			if (a.getNodo_origen().equals(nodo)) { //5 OE
				suma += cuenta_distancia_nodos_hoja(a.getNodo_destino(), a, 1, media, minimo, arcos); //2n + 13
			} else if (a.getNodo_destino().equals(nodo)) { //5 OE
				suma += cuenta_distancia_nodos_hoja(a.getNodo_origen(), a, 1, media, minimo, arcos); //2n + 13
			}

		}
		return suma; //1 OE

	} // T(n) =  2n^2 + 31n + 8 -- O(n^2)

	/**
	 * Cuenta distancia a los nodos hoja.
	 *
	 * @param nodo      the nodo
	 * @param arco      the arco
	 * @param acumulado the acumulado
	 * @param media     the media
	 * @param minimo    the minimo
	 * @param arcos     the arcos
	 * @return the int
	 */
	public int cuenta_distancia_nodos_hoja(Nodo nodo, Arco arco, int acumulado, int media, double minimo,
			ArrayList<Arco> arcos) {
		int total = 0; //1 OE
		ArrayList<Arco> arcos_anexos = new ArrayList<Arco>(); //1 OE
		arcos_anexos = nodo.getArcosIncidentes(arcos); //12n + 5

		double check = acumulado / media; //2 OE 
		if (check > minimo) //1 OE 
			return Integer.MAX_VALUE;  //1 OE 
		if (arcos_anexos.size() == 0) { //2 OE 
			return acumulado; //1 OE 
		} else { //1 OE 
			for (Arco a : arcos_anexos) {
				if (a != arco) { //1 OE 
					arcos.remove(a); //2 OE 
					if (a.getNodo_origen().equals(nodo)) { //3 OE 
						total += cuenta_distancia_nodos_hoja(a.getNodo_destino(), a, acumulado + 1, media, minimo,
								arcos); 
					} else if (a.getNodo_destino().equals(nodo)) { //3 OE 
						total += cuenta_distancia_nodos_hoja(a.getNodo_origen(), a, acumulado + 1, media, minimo,
								arcos); 
					}
				}
			}
		}

		return total; //1 OE 
	} // T(n) = T(n - 1) + 1 si n > 1, T(1) = 1 si n = 1
	  // En la primera llamada, obtenemos T(n) = T(n - 1) + 1
	  // En la segunda llamada obtenemos T(n) = T(n - 2) + 2
	  // En la k-esima llamada, obtendriamos T(n) = k * T(n - k) + k. Se deduce que k = n + 1
	  // quedaría como T(n) = n+1 * T(1) + n + 1 = 2n * T(1) + 2
	  // T(n) = 2n + 2 -- O(n)

	/**
	 * Encontrar raiz.
	 *
	 * @return the nodo
	 */
	public Nodo encontrarRaiz() {
		Nodo raiz = new Nodo(); //2 OE 
		int media = 0; //1 OE 
		int distancia = 0; //1 OE 
		double aux = Integer.MAX_VALUE; //1 OE 
		media = cuenta_nodos_hoja(lista_nodos.get(0)); //5 OE 

		for (Nodo n : lista_nodos) { //2n^3 + 33n^2 + 20n
			ArrayList<Arco> arcos = new ArrayList<Arco>(); //1 OE 
			for (Arco a : lista_arcos) //2n
				arcos.add(a); //2 OE 

			distancia = cuenta_distancia_nodos_hoja(n, media, aux, arcos); //2n^2 + 31n + 14
			if ((distancia / media) < aux) { //2 OE 
				raiz = n; //1 OE 
				aux = (distancia / media); //2 OE 

			}
		}

		return raiz; //1 OE 

	} // T(n) = 2n^3 + 33n^2 + 20n + 11 -- O(n)
}
