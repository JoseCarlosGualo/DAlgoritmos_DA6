// TODO: Auto-generated Javadoc
/**
 * The Class Arco.
 */
public class Arco {
	
	/** The nodo origen. */
	private Nodo nodo_origen;
	
	/** The nodo destino. */
	private Nodo nodo_destino;
	
	/** The length. */
	private double length;
	
	/** The id. */
	private String id;

	/**
	 * Instantiates a new arco.
	 *
	 * @param id the id
	 * @param nodo_origen the nodo origen
	 * @param nodo_destino the nodo destino
	 * @param length the length
	 */
	public Arco(String id, Nodo nodo_origen, Nodo nodo_destino, double length) {
		this.nodo_origen = nodo_origen; // 1 OE
		this.nodo_destino = nodo_destino; // 1 OE
		this.length = length; // 1 OE
		this.id = id; // 1 OE
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() { // 1 OE
		return id; // 1 OE
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) { // 1 OE
		this.id = id; // 1 OE
	}

	/**
	 * Instantiates a new arco.
	 */
	public Arco() {
	}

	/**
	 * Gets the nodo origen.
	 *
	 * @return the nodo origen
	 */
	public Nodo getNodo_origen() { // 1 OE
		return nodo_origen; // 1 OE
	}

	/**
	 * Sets the nodo origen.
	 *
	 * @param nodo_origen the new nodo origen
	 */
	public void setNodo_origen(Nodo nodo_origen) { // 1 OE
		this.nodo_origen = nodo_origen; // 1 OE
	}

	/**
	 * Gets the nodo destino.
	 *
	 * @return the nodo destino
	 */
	public Nodo getNodo_destino() { // 1 OE
		return nodo_destino; // 1 OE
	}

	/**
	 * Sets the nodo destino.
	 *
	 * @param nodo_destino the new nodo destino
	 */
	public void setNodo_destino(Nodo nodo_destino) { // 1 OE
		this.nodo_destino = nodo_destino; // 1 OE
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public double getLength() { // 1 OE
		return length; // 1 OE
	}

	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(double length) { // 1 OE
		this.length = length; // 1 OE
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() { // 10 OE
		return "Arco{ Id: " + this.id + "Length=" + this.length + ", origen=" + this.nodo_origen.toString()
				+ ", destino=" + this.nodo_destino.toString() + '}'; // 10 OE
	}

}