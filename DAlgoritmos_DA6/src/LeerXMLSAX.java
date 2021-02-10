import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class LeerXMLSAX {

	public LeerXMLSAX() {
	}

	public Poblacion leerXMLSAX(String xml) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = saxParserFactory.newSAXParser();
		File file = new File(xml);
		SAXHandler handler = new SAXHandler();
		saxParser.parse(file, handler);

		Poblacion poblacion = new Poblacion(handler.getNombre_poblacion(), handler.getLista_nodos(),
				handler.getLista_arcos());
		return poblacion;
	}
}
