import java.util.ArrayList;

public class Nodo {
	private double x;
	private double y;
	private String id_nodo;

	public Nodo(double x, double y, String id_nodo) { // 3 OE
		this.x = x; // 1 OE
		this.y = y; // 1 OE
		this.id_nodo = id_nodo; // 1 OE

	}

	public Nodo() {
	}

	public double getX() { // 1 OE
		return x; // 1 OE
	}

	public void setX(double x) { // 1 OE
		this.x = x; // 1 OE
	}

	public double getY() { // 1 OE
		return y; // 1 OE
	}

	public void setY(double y) { // 1 OE
		this.y = y; // 1 OE
	}

	public String getId_nodo() { // 1 OE
		return id_nodo; // 1 OE
	}

	public void setId_nodo(String id_nodo) { // 1 OE
		this.id_nodo = id_nodo; // 1 OE
	}

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

	public boolean pertenece(ArrayList<Nodo> lista_nodos) { // 6n + 1
		for (Nodo n : lista_nodos) { // 6n
			if (n.getId_nodo().equalsIgnoreCase(this.getId_nodo())) { // 4 OE
				return true; // 1 OE
			}
		}
		return false; // 1 OE
	}

	public String toString() { // 7 OE
		return "Nodo{" + "Id_Nodo=" + this.id_nodo + ", x=" + this.x + ", y=" + this.y + '}'; // 7 OE
	}

}