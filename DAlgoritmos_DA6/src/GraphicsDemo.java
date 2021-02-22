import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GraphicsDemo extends JPanel {
	private int xsize;
	private int ysize;
	private Grafo grafo;

	public GraphicsDemo(int xsize, int ysize, Grafo grafo) {
		this.xsize = xsize;
		this.ysize = ysize;
		this.grafo = grafo;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		int pinicial = 20;

	}

}