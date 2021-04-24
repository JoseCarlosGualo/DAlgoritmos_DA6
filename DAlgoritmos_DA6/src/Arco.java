public class Arco {
	private Nodo nodo_origen;
	private Nodo nodo_destino;
	private double length;
	private String id;

	public Arco(String id, Nodo nodo_origen, Nodo nodo_destino, double length) {
		this.nodo_origen = nodo_origen; // 1 OE
		this.nodo_destino = nodo_destino; // 1 OE
		this.length = length; // 1 OE
		this.id = id; // 1 OE
	}

	public String getId() { // 1 OE
		return id; // 1 OE
	}

	public void setId(String id) { // 1 OE
		this.id = id; // 1 OE
	}

	public Arco() {
	}

	public Nodo getNodo_origen() { // 1 OE
		return nodo_origen; // 1 OE
	}

	public void setNodo_origen(Nodo nodo_origen) { // 1 OE
		this.nodo_origen = nodo_origen; // 1 OE
	}

	public Nodo getNodo_destino() { // 1 OE
		return nodo_destino; // 1 OE
	}

	public void setNodo_destino(Nodo nodo_destino) { // 1 OE
		this.nodo_destino = nodo_destino; // 1 OE
	}

	public double getLength() { // 1 OE
		return length; // 1 OE
	}

	public void setLength(double length) { // 1 OE
		this.length = length; // 1 OE
	}

	public String toString() { // 10 OE
		return "Arco{ Id: " + this.id + "Length=" + this.length + ", origen=" + this.nodo_origen.toString()
				+ ", destino=" + this.nodo_destino.toString() + '}'; // 10 OE
	}

}