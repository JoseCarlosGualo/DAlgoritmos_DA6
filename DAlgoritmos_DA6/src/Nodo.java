import java.util.ArrayList;

public class Nodo {
	private double x;
	private double y;
	private String id_nodo;
	private ArrayList<Arco> lista_arcos;

	public Nodo(double x, double y, String id_nodo, ArrayList<Arco> lista_arcos) {
		this.x = x;
		this.y = y;
		this.id_nodo = id_nodo;
		this.lista_arcos = lista_arcos;
	}

	public Nodo(double x, double y, String id_nodo) {
		this.x = x;
		this.y = y;
		this.id_nodo = id_nodo;
	}

	public Nodo() {
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getId_nodo() {
		return id_nodo;
	}

	public void setId_nodo(String id_nodo) {
		this.id_nodo = id_nodo;
	}

	public ArrayList<Arco> getLista_arcos() {
		return lista_arcos;
	}

	public void setLista_arcos(ArrayList<Arco> lista_arcos) {
		this.lista_arcos = lista_arcos;
	}

	public void addArco(Arco arco) {
		if (this.lista_arcos == null) {
			this.lista_arcos = new ArrayList<Arco>();
		}
		this.lista_arcos.add(arco);
	}

	public String toString() {
		return "Nodo{" + "Id_Nodo=" + this.id_nodo + ", x=" + this.x + ", y=" + this.y + '}';
	}

	public void toStringLista() {
		for (Arco a : this.lista_arcos) {
			System.out.println("{{{Id_Nodo Origen: " + a.getNodo_origen().getId_nodo() + ", Id_Nodo destino: "
					+ a.getNodo_destino().getId_nodo() + "}}}");
		}
	}
}
