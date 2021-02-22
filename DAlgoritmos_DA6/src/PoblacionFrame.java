import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PoblacionFrame extends JFrame {
	int xsize;
	int ysize;

	public PoblacionFrame(int xsize, int ysize, Grafo grafo) {
		GraphicsDemo lienzo = new GraphicsDemo(xsize, ysize, grafo);
		this.xsize = xsize;
		this.ysize = ysize;
		this.setSize(xsize, ysize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(lienzo);
		this.setVisible(true);

		try {
			BufferedImage image = new BufferedImage(getWidth() - 40, getHeight() - 40, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = image.createGraphics();
			lienzo.paint(graphics2D);
			String path;
			path = "puzzle_loop" + grafo.getNombre_poblacion() + ".png";
			ImageIO.write(image, "PNG", new File(path));
		} catch (Exception exception) {
			System.out.println("Error al exportar la imagen");

		}
	}

}