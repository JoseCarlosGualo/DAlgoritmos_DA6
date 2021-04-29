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
	public Nodo(double x, double y, String id_nodo) { 		
		this.x = x; // 1 OE
		this.y = y; // 1 OE
		this.id_nodo = id_nodo; // 1 OE

	} // T(n) = 3 OE -- O(1)


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
	public double getX() {
		return x; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(double x) {
		this.x = x; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY() {
		return y; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(double y) {
		this.y = y; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Gets the id nodo.
	 *
	 * @return the id nodo
	 */
	public String getId_nodo() {
		return id_nodo; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Sets the id nodo.
	 *
	 * @param id_nodo the new id nodo
	 */
	public void setId_nodo(String id_nodo) {
		this.id_nodo = id_nodo; // 1 OE
	} // T(n) = 1 OE -- O(1)

	/**
	 * Devuelve los arcos incidentes que tiene un nodo, para ello es necesario la lista de arcos
	 * del grafo en su completitud para así poder comprobar si un arco del grafo tiene como origen
	 * dicho nodo mediante la id de este.
	 *
	 * @param lista_arcos the lista arcos
	 * @return the arcos incidentes
	 */
	public ArrayList<Arco> getArcosIncidentes(ArrayList<Arco> lista_arcos) {
		ArrayList<Arco> arcos_incidentes = new ArrayList<Arco>(); // 1 OE
		for (Arco a : lista_arcos) { // 12n
			if (a.getNodo_origen().getId_nodo().equalsIgnoreCase(this.id_nodo)
					|| a.getNodo_destino().getId_nodo().equalsIgnoreCase(this.id_nodo)) { // 11 OE
				arcos_incidentes.add(a); // 1 OE
			}
		}

		return arcos_incidentes; // 1 OE
	} // T(n) = 12n + 2 -- O(n)

	/**
	 * Comprueba si un nodo pertenece a una lista de nodos introducida por parametro.
	 *
	 * @param lista_nodos the lista nodos
	 * @return true, if successful
	 */
	public boolean pertenece(ArrayList<Nodo> lista_nodos) {
		for (Nodo n : lista_nodos) { // 7n
			if (n.getId_nodo().equalsIgnoreCase(this.getId_nodo())) { // 6 OE
				return true; // 1 OE
			}
		}
		return false; // 1 OE
	} // T(n) = 7n + 1 -- O(n)

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Nodo{" + "Id_Nodo=" + this.id_nodo + ", x=" + this.x + ", y=" + this.y + '}'; // 7 OE
	} // T(n) = 7 OE -- O(1)

}