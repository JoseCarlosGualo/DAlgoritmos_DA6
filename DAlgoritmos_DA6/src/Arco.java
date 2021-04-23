public class Arco {
	private Nodo nodo_origen;
	private Nodo nodo_destino;
	private double length;
	private String id;

	public Arco(String id, Nodo nodo_origen, Nodo nodo_destino, double length) {
		this.nodo_origen = nodo_origen;
		this.nodo_destino = nodo_destino;
		this.length = length;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Arco() {
	}

	public Nodo getNodo_origen() {
		return nodo_origen;
	}

	public void setNodo_origen(Nodo nodo_origen) {
		this.nodo_origen = nodo_origen;
	}

	public Nodo getNodo_destino() {
		return nodo_destino;
	}

	public void setNodo_destino(Nodo nodo_destino) {
		this.nodo_destino = nodo_destino;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String toString() {
		return "Arco{ Id: "+this.id+ "Length=" + this.length + ", origen=" + this.nodo_origen.toString() + ", destino="
				+ this.nodo_destino.toString() + '}';
	}

}