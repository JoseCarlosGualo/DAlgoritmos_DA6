import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

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

	public void dibujarGrafo() throws IOException {
		int width = 900;
		int height = 900;

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setBackground(Color.WHITE);
		
		for (Arco a : this.lista_arcos) {
			
		}
		
		g2d.dispose();
		File file = new File(this.nombre_poblacion + ".png");
		ImageIO.write(bufferedImage, "png", file);
	}

}
