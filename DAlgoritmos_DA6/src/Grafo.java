import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
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
			g2d.draw(new Line2D.Double(((Xmaxima - Math.abs(a.getNodo_origen().getX()))/diffX)*width, ((Ymaxima - Math.abs(a.getNodo_origen().getY()))/diffY)*height,
					((Xmaxima - Math.abs(a.getNodo_destino().getX()))/diffX)*width, ((Ymaxima - Math.abs(a.getNodo_destino().getY()))/diffY)*height));

		}
		
		for (Nodo n : this.lista_nodos) {
			g2d.setStroke(new BasicStroke(2));
			g2d.setColor(Color.RED);
			double xnodo = (((Xmaxima - Math.abs(n.getX()))/diffX)*width)-2;
			double ynodo = (((Ymaxima - Math.abs(n.getY()))/diffY)*height)-2;
			g2d.draw(new Ellipse2D.Double(xnodo, ynodo, 4, 4));
		}
		
		g2d.dispose();
		File miDir = new File("ImagenesPoblaciones");
		miDir.mkdirs();

		File file = new File(miDir.getCanonicalPath() + "\\" + this.nombre_poblacion + ".png");
		ImageIO.write(bufferedImage, "png", file);
	}

}
