import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
	private ArrayList<Arco> lista_arcos = new ArrayList<Arco>(); // 1 OE
	private ArrayList<Nodo> lista_nodos = new ArrayList<Nodo>(); // 1 OE
	private Arco arco;
	private Nodo nodo;
	private StringBuilder buffer = new StringBuilder(); // 1 OE
	private boolean d_y = false; // 1 OE
	private boolean d_x = false; // 1 OE
	private boolean d_length = false; // 1 OE
	private boolean d_nombre = false; // 1 OE
	private boolean d_arco = false; // 1 OE
	private String id_nombre = ""; // 1 OE
	private String id_y = ""; // 1 OE
	private String id_x = ""; // 1 OE
	private String id_length = ""; // 1 OE
	private String nombre_poblacion = ""; // 1 OE
	private String id_arco = ""; // 1 OE

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException { // 1 OE
		buffer.append(ch, start, length); // 1 OE
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) { // 5 OE
		case "node": // 1 OE
			break; // 1 OE
		case "data": // 5 OE
			if (d_y) { // 1 OE
				nodo.setY(Double.parseDouble(buffer.toString())); // 3 OE
				d_y = false; // 1 OE
			} else if (d_x) { // 1 OE
				nodo.setX(Double.parseDouble(buffer.toString())); // 3 OE
				d_x = false; // 1 OE
			} else if (d_length) { // 1 OE
				arco.setLength(Double.parseDouble(buffer.toString())); // 3 OE
				d_length = false; // 1 OE
			} else if (d_nombre) { // 1 OE
				nombre_poblacion = buffer.toString(); // 2 OE
				d_nombre = false; // 1 OE
			} else if (d_arco) { // 1 OE
				arco.setId(buffer.toString()); // 2 OE
				d_arco = false; // 1 OE
			}
			break;
		case "edge": // 1 OE
			break; // 1 OE
		case "key": // 1 OE
			break; // 1 OE
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException { // 16
																														// OE
		switch (qName) { // 16 OE

		case "node": // 5 OE
			nodo = new Nodo(); // 1 OE
			lista_nodos.add(nodo); // 1 OE
			nodo.setId_nodo(attributes.getValue("id")); // 2 OE
			break; // 1 OE
		case "data": // 6 OE
			buffer.delete(0, buffer.length()); // 2 OE

			if (attributes.getValue("key").equalsIgnoreCase(id_length)) { // 2 OE
				d_length = true; // 1 OE
			} else if (attributes.getValue("key").equalsIgnoreCase(id_x)) { // 2 OE
				d_x = true; // 1 OE
			} else if (attributes.getValue("key").equalsIgnoreCase(id_y)) { // 2 OE
				d_y = true; // 1 OE
			} else if (attributes.getValue("key").equalsIgnoreCase(id_nombre)) { // 2 OE
				d_nombre = true; // 1 OE
			} else if (attributes.getValue("key").equalsIgnoreCase(id_arco)) { // 2 OE
				d_arco = true; // 1 OE
			}

			break; // 1 OE
		case "edge": // 16 OE
			arco = new Arco(); // 1 OE
			lista_arcos.add(arco); // 1 OE
			arco.setNodo_origen(getNodoFromList(lista_nodos, attributes.getValue("source"))); // 3 OE
			arco.setNodo_destino(getNodoFromList(lista_nodos, attributes.getValue("target"))); // 3 OE

			// cambiarlo por excepciones
			if (arco.getNodo_origen() == null || arco.getNodo_destino() == null) { // 5 OE
				System.out.println("No se ha leido bien"); // 1 OE
				System.exit(1); // 1 OE
			}
			break; // 1 OE
		case "key": // 8 OE
			if (attributes.getValue("attr.name").equalsIgnoreCase("length")) { // 2 OE
				id_length = attributes.getValue("id"); // 2 OE
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("x")) { // 2 OE
				id_x = attributes.getValue("id"); // 2 OE
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("y")) { // 2 OE
				id_y = attributes.getValue("id"); // 2 OE
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("name")) { // 2 OE
				id_nombre = attributes.getValue("id"); // 2 OE
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("osmid")
					&& attributes.getValue("for").equalsIgnoreCase("edge")) { // 5 OE
				id_arco = attributes.getValue("id"); // 2 OE
			}
			break; // 1 OE

		}
	}

	public ArrayList<Nodo> getLista_nodos() { // 1 OE
		return this.lista_nodos; // 1 OE
	}

	public Nodo getNodoFromList(ArrayList<Nodo> lista_nodos, String id_nodo) { // 4n + 2
		Nodo nodo = null; // 1 OE
		for (Nodo n : lista_nodos) { // 4n
			if (n.getId_nodo().equalsIgnoreCase(id_nodo)) { // 2 OE
				nodo = n; // 1 OE
				return nodo; // 1 OE
			}
		}
		return nodo; // 1 OE
	}

	public ArrayList<Arco> getLista_arcos() { // 1 OE
		return this.lista_arcos; // 1 OE
	}

	public String getId_length() { // 1 OE
		return this.id_length; // 1 OE
	}

	public String getId_x() { // 1 OE
		return this.id_x; // 1 OE
	}

	public String getId_y() { // 1 OE
		return this.id_y; // 1 OE
	}

	public String getNombre_poblacion() { // 1 OE
		return this.nombre_poblacion; // 1 OE
	}
}
