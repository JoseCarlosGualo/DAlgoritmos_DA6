import java.util.ArrayList;

public class Nodo {
	private double x;
	private double y;
	private String id_nodo;

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

	public ArrayList<Arco> getArcosIncidentes(ArrayList<Arco> lista_arcos) {
		ArrayList<Arco> arcos_incidentes = new ArrayList<Arco>();
		for (Arco a : lista_arcos) {
			if (a.getNodo_destino().getId_nodo().equalsIgnoreCase(this.id_nodo)) {
				arcos_incidentes.add(a);
			}
		}

		return arcos_incidentes;
	}

	public boolean isRelacion(ArrayList<Arco> lista_arcos, Nodo origen) {
		for (Arco a : lista_arcos) {
			if (a.getNodo_destino().id_nodo.equalsIgnoreCase(origen.getId_nodo())) {
				return true;
			}
		}

		return false;
	}

	/*
	 * public void addArco(Arco arco) { if (this.lista_arcos == null) {
	 * this.lista_arcos = new ArrayList<Arco>(); } this.lista_arcos.add(arco); }
	 */

	public String toString() {
		return "Nodo{" + "Id_Nodo=" + this.id_nodo + ", x=" + this.x + ", y=" + this.y + '}';
	}

}
