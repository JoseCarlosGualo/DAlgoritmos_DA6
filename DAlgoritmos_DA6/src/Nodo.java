import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Nodo.
 */
public class Nodo {

	/** The x. */
	private double x;

	/** The y. */
	private double y;

	/** The id nodo. */
	private String id_nodo;

	/**
	 * Instantiates a new nodo.
	 *
	 * @param x       the x
	 * @param y       the y
	 * @param id_nodo the id nodo
	 */
	public Nodo(double x, double y, String id_nodo) { // 3 OE
		this.x = x; // 1 OE
		this.y = y; // 1 OE
		this.id_nodo = id_nodo; // 1 OE

	}

	/**
	 * Instantiates a new nodo.
	 */
	public Nodo() {
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public double getX() { // 1 OE
		return x; // 1 OE
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(double x) { // 1 OE
		this.x = x; // 1 OE
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY() { // 1 OE
		return y; // 1 OE
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(double y) { // 1 OE
		this.y = y; // 1 OE
	}

	/**
	 * Gets the id nodo.
	 *
	 * @return the id nodo
	 */
	public String getId_nodo() { // 1 OE
		return id_nodo; // 1 OE
	}

	/**
	 * Sets the id nodo.
	 *
	 * @param id_nodo the new id nodo
	 */
	public void setId_nodo(String id_nodo) { // 1 OE
		this.id_nodo = id_nodo; // 1 OE
	}

	/**
	 * Gets the arcos incidentes.
	 *
	 * @param lista_arcos the lista arcos
	 * @return the arcos incidentes
	 */
	public ArrayList<Arco> getArcosIncidentes(ArrayList<Arco> lista_arcos) { // 8n + 2
		ArrayList<Arco> arcos_incidentes = new ArrayList<Arco>(); // 1 OE
		for (Arco a : lista_arcos) { // 8n
			if (a.getNodo_origen().getId_nodo().equalsIgnoreCase(this.id_nodo)
					|| a.getNodo_destino().getId_nodo().equalsIgnoreCase(this.id_nodo)) { // 7 OE
				arcos_incidentes.add(a); // 1 OE
			}
		}

		return arcos_incidentes; // 1 OE
	}

	/**
	 * Pertenece.
	 *
	 * @param lista_nodos the lista nodos
	 * @return true, if successful
	 */
	public boolean pertenece(ArrayList<Nodo> lista_nodos) { // 6n + 1
		for (Nodo n : lista_nodos) { // 6n
			if (n.getId_nodo().equalsIgnoreCase(this.getId_nodo())) { // 4 OE
				return true; // 1 OE
			}
		}
		return false; // 1 OE
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() { // 7 OE
		return "Nodo{" + "Id_Nodo=" + this.id_nodo + ", x=" + this.x + ", y=" + this.y + '}'; // 7 OE
	}

}