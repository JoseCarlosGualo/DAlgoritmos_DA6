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
	private boolean d4_y = false;
	private boolean d5_x = false;
	private boolean d_length = false;
	private String id_length = "";

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
			if (d4_y) {
				nodo.setY(buffer.toString());
				d4_y = false;
			} else if (d5_x) {
				nodo.setX(buffer.toString());
				d5_x = false;
			} else if (d_length) {
				arco.setLength(buffer.toString());
				d_length = false;
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
			switch (attributes.getValue("key")) {
			case "d4":
				d4_y = true;
				break;
			case "d5":
				d5_x = true;
				break;
			}

			if (attributes.getValue("key").equalsIgnoreCase(id_length)) {
				d_length = true;
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
}
