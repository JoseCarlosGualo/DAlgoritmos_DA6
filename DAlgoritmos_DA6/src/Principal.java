import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Principal {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = saxParserFactory.newSAXParser();
		File file = new File("Manzanares.graphml");
		SAXHandler handler = new SAXHandler();
		saxParser.parse(file, handler);

		ArrayList<Nodo> lista_nodos = handler.getLista_nodos();
		ArrayList<Arco> lista_arcos = handler.getLista_arcos();

		for (Nodo n : lista_nodos) {
			System.out.println(n);
		}

		for (Arco a : lista_arcos) {
			System.out.println(a);
		}

		// System.out.println(handler.getId_length());

	}

}
