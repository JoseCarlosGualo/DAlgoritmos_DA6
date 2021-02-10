
public class Arco {
	private Nodo nodo_origen;
	private Nodo nodo_destino;
	private double length;

	public Arco(Nodo nodo_origen, Nodo nodo_destino, double length) {
		this.nodo_origen = nodo_origen;
		this.nodo_destino = nodo_destino;
		this.length = length;
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
		return "Arco{" + "Length=" + this.length + ", origen=" + this.nodo_destino.toString() + ", destino="
				+ this.nodo_destino.toString() + '}';
	}

}