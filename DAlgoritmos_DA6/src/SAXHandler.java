import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
	private ArrayList<Arco> lista_arcos = new ArrayList<Arco>();
	private ArrayList<Nodo> lista_nodos = new ArrayList<Nodo>();
	private Arco arco;
	private Nodo nodo;
	private StringBuilder buffer = new StringBuilder();
	private boolean d_y = false;
	private boolean d_x = false;
	private boolean d_length = false;
	private boolean d_nombre = false;
	private boolean d_arco = false;
	private String id_nombre = "";
	private String id_y = "";
	private String id_x = "";
	private String id_length = "";
	private String nombre_poblacion = "";
	private String id_arco = "";

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		buffer.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "node":
			break;
		case "data":
			if (d_y) {
				nodo.setY(Double.parseDouble(buffer.toString()));
				d_y = false;
			} else if (d_x) {
				nodo.setX(Double.parseDouble(buffer.toString()));
				d_x = false;
			} else if (d_length) {
				arco.setLength(Double.parseDouble(buffer.toString()));
				d_length = false;
			} else if (d_nombre) {
				nombre_poblacion = buffer.toString();
				d_nombre = false;
			} else if (d_arco) {
				arco.setId(buffer.toString());
				d_arco = false;
			}
			break;
		case "edge":
			break;
		case "key":
			break;
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {

		case "node":
			nodo = new Nodo();
			lista_nodos.add(nodo);
			nodo.setId_nodo(attributes.getValue("id"));
			break;
		case "data":
			buffer.delete(0, buffer.length());

			if (attributes.getValue("key").equalsIgnoreCase(id_length)) {
				d_length = true;
			} else if (attributes.getValue("key").equalsIgnoreCase(id_x)) {
				d_x = true;
			} else if (attributes.getValue("key").equalsIgnoreCase(id_y)) {
				d_y = true;
			} else if (attributes.getValue("key").equalsIgnoreCase(id_nombre)) {
				d_nombre = true;
			} else if (attributes.getValue("key").equalsIgnoreCase(id_arco)) {
				d_arco = true;
			}

			break;
		case "edge":
			arco = new Arco();
			lista_arcos.add(arco);
			arco.setNodo_origen(getNodoFromList(lista_nodos, attributes.getValue("source")));
			arco.setNodo_destino(getNodoFromList(lista_nodos, attributes.getValue("target")));
			

			// cambiarlo por excepciones
			if (arco.getNodo_origen() == null || arco.getNodo_destino() == null) {
				System.out.println("No se ha leido bien");
				System.exit(1);
			}
			break;
		case "key":
			if (attributes.getValue("attr.name").equalsIgnoreCase("length")) {
				id_length = attributes.getValue("id");
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("x")) {
				id_x = attributes.getValue("id");
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("y")) {
				id_y = attributes.getValue("id");
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("name")) {
				id_nombre = attributes.getValue("id");
			} else if (attributes.getValue("attr.name").equalsIgnoreCase("osmid") && attributes.getValue("for").equalsIgnoreCase("edge")) {
				id_arco = attributes.getValue("id");
			}
			break;

		}
	}

	public ArrayList<Nodo> getLista_nodos() {
		return this.lista_nodos;
	}

	public Nodo getNodoFromList(ArrayList<Nodo> lista_nodos, String id_nodo) {
		Nodo nodo = null;
		for (Nodo n : lista_nodos) {
			if (n.getId_nodo().equalsIgnoreCase(id_nodo)) {
				nodo = n;
				return nodo;
			}
		}
		return nodo;
	}

	public ArrayList<Arco> getLista_arcos() {
		return this.lista_arcos;
	}

	public String getId_length() {
		return this.id_length;
	}

	public String getId_x() {
		return this.id_x;
	}

	public String getId_y() {
		return this.id_y;
	}

	public String getNombre_poblacion() {
		return this.nombre_poblacion;
	}
}
