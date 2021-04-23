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
			if (a.getNodo_origen().getId_nodo().equalsIgnoreCase(this.id_nodo)
					|| a.getNodo_destino().getId_nodo().equalsIgnoreCase(this.id_nodo)) {
				arcos_incidentes.add(a);
			}
		}

		return arcos_incidentes;
	}

	public boolean pertenece(ArrayList<Nodo> lista_nodos) {
		for (Nodo n : lista_nodos) {
			if (n.getId_nodo().equalsIgnoreCase(this.getId_nodo())) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return "Nodo{" + "Id_Nodo=" + this.id_nodo + ", x=" + this.x + ", y=" + this.y + '}';
	}

}
