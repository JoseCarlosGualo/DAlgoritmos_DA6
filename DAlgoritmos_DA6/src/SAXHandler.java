import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class SAXHandler.
 */
public class SAXHandler extends DefaultHandler {

	/** The lista arcos. */
	private ArrayList<Arco> lista_arcos = new ArrayList<Arco>(); // 1 OE

	/** The lista nodos. */
	private ArrayList<Nodo> lista_nodos = new ArrayList<Nodo>(); // 1 OE

	/** The arco. */
	private Arco arco;

	/** The nodo. */
	private Nodo nodo;

	/** The buffer. */
	private StringBuilder buffer = new StringBuilder(); // 1 OE

	/** The d y. */
	private boolean d_y = false; // 1 OE

	/** The d x. */
	private boolean d_x = false; // 1 OE

	/** The d length. */
	private boolean d_length = false; // 1 OE

	/** The d nombre. */
	private boolean d_nombre = false; // 1 OE

	/** The d arco. */
	private boolean d_arco = false; // 1 OE

	/** The id nombre. */
	private String id_nombre = ""; // 1 OE

	/** The id y. */
	private String id_y = ""; // 1 OE

	/** The id x. */
	private String id_x = ""; // 1 OE

	/** The id length. */
	private String id_length = ""; // 1 OE

	/** The nombre poblacion. */
	private String nombre_poblacion = ""; // 1 OE

	/** The id arco. */
	private String id_arco = ""; // 1 OE

	/**
	 * Characters.
	 *
	 * @param ch     the ch
	 * @param start  the start
	 * @param length the length
	 * @throws SAXException the SAX exception
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		buffer.append(ch, start, length); // 4 OE
	} // T(n) = 4 OE -- O(1)

	/**
	 * Se produce al finalizar el procesado de una etiqueta xml.
	 *
	 * @param uri       the uri
	 * @param localName the local name
	 * @param qName     the q name
	 * @throws SAXException the SAX exception
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) { // 5 OE
		case "node": // 1 OE
			break; // 1 OE
		case "data": // 7 OE
			if (d_y) { // 1 OE
				nodo.setY(Double.parseDouble(buffer.toString())); // 5 OE
				d_y = false; // 1 OE
			} else if (d_x) { // 1 OE
				nodo.setX(Double.parseDouble(buffer.toString())); // 5 OE
				d_x = false; // 1 OE
			} else if (d_length) { // 1 OE
				arco.setLength(Double.parseDouble(buffer.toString())); // 5 OE
				d_length = false; // 1 OE
			} else if (d_nombre) { // 1 OE
				nombre_poblacion = buffer.toString(); // 2 OE
				d_nombre = false; // 1 OE
			} else if (d_arco) { // 1 OE
				arco.setId(buffer.toString()); // 3 OE
				d_arco = false; // 1 OE
			}
			break;
		case "edge": // 1 OE
			break; // 1 OE
		case "key": // 1 OE
			break; // 1 OE
		}
	} // T(n) = 7 OE -- O(1)

	/**
	 * Se produce al comenzar el procesado de una etiqueta xml, es donde se leen los atributos de las etiquetas.
	 *
	 * @param uri        the uri
	 * @param localName  the local name
	 * @param qName      the q name
	 * @param attributes the attributes
	 * @throws SAXException the SAX exception
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) { // 20 OE

		case "node": // 8 OE
			nodo = new Nodo(); // 1 OE
			lista_nodos.add(nodo); // 2 OE
			nodo.setId_nodo(attributes.getValue("id")); // 4 OE
			break; // 1 OE
		case "data": // 9 OE
			buffer.delete(0, buffer.length()); // 4 OE

			if (attributes.getValue("key").equalsIgnoreCase(id_length)) { // 4 OE
				d_length = true; // 1 OE
			} else if (attributes.getValue("key").equalsIgnoreCase(id_x)) { // 4 OE
				d_x = true; // 1 OE
			} else if (attributes.getValue("key").equalsIgnoreCase(id_y)) { // 4 OE
				d_y = true; // 1 OE
			} else if (attributes.getValue("key").equalsIgnoreCase(id_nombre)) { // 4 OE
				d_nombre = true; // 1 OE
			} else if (attributes.getValue("key").equalsIgnoreCase(id_arco)) { // 4 OE
				d_arco = true; // 1 OE
			}

			break; // 1 OE
		case "edge": // 20 OE
			arco = new Arco(); // 1 OE
			lista_arcos.add(arco); // 1 OE
			arco.setNodo_origen(getNodoFromList(lista_nodos, attributes.getValue("source"))); // 3 OE
			arco.setNodo_destino(getNodoFromList(lista_nodos, attributes.getValue("target"))); // 3 OE

			// cambiarlo por excepciones
			if (arco.getNodo_origen() == null || arco.getNodo_destino() == null) { // 9 OE
				System.out.println("No se ha leido bien"); // 1 OE
				System.exit(1); // 1 OE
			}
			break; // 1 OE
		case "key": // 12 OE
			if (attributes.getValue("attr.name").equalsIgnoreCase("length")) { // 5 OE
				id_length = attributes.getValue("id"); // 3 OE
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("x")) { // 5 OE
				id_x = attributes.getValue("id"); // 3 OE
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("y")) { // 5 OE
				id_y = attributes.getValue("id"); // 3 OE
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("name")) { // 5 OE
				id_nombre = attributes.getValue("id"); // 3 OE
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("osmid")
					&& attributes.getValue("for").equalsIgnoreCase("edge")) { // 8 OE
				id_arco = attributes.getValue("id"); // 3 OE
			}
			break; // 1 OE

		}
	} // T(n) = 20 OE -- O(1)

	/**
	 * Gets the lista nodos.
	 *
	 * @return the lista nodos
	 */
	public ArrayList<Nodo> getLista_nodos() {
		return this.lista_nodos; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Devuelve el nodo de una lista si los ids coinciden.
	 *
	 * @param lista_nodos the lista nodos
	 * @param id_nodo     the id nodo
	 * @return the nodo from list
	 */
	public Nodo getNodoFromList(ArrayList<Nodo> lista_nodos, String id_nodo) {
		Nodo nodo = null; // 1 OE
		for (Nodo n : lista_nodos) { // 6n
			if (n.getId_nodo().equalsIgnoreCase(id_nodo)) { // 4 OE
				nodo = n; // 1 OE
				return nodo; // 1 OE
			}
		}
		return nodo; // 1 OE
	} // T(n) = 6n + 2 -- O(n)

	/**
	 * Gets the lista arcos.
	 *
	 * @return the lista arcos
	 */
	public ArrayList<Arco> getLista_arcos() {
		return this.lista_arcos; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Gets the id length.
	 *
	 * @return the id length
	 */
	public String getId_length() {
		return this.id_length; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Gets the id x.
	 *
	 * @return the id x
	 */
	public String getId_x() {
		return this.id_x; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Gets the id y.
	 *
	 * @return the id y
	 */
	public String getId_y() {
		return this.id_y; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Gets the nombre poblacion.
	 *
	 * @return the nombre poblacion
	 */
	public String getNombre_poblacion() {
		return this.nombre_poblacion; // 1 OE
	} // T(n) = 1 OE -- O(1)
}
