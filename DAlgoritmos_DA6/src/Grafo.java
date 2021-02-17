import java.util.ArrayList;

public class Grafo {
	private ArrayList<Arco> lista_arcos;
	private ArrayList<Nodo> lista_nodos;

	public Grafo(ArrayList<Arco> lista_arcos, ArrayList<Nodo> lista_nodos) {
		this.lista_arcos = lista_arcos;
		this.lista_nodos = lista_nodos;
	}

	public ArrayList<Arco> getLista_arcos() {
		return lista_arcos;
	}

	public void setLista_arcos(ArrayList<Arco> lista_arcos) {
		this.lista_arcos = lista_arcos;
	}

	public ArrayList<Nodo> getLista_nodos() {
		return lista_nodos;
	}

	public void setLista_nodos(ArrayList<Nodo> lista_nodos) {
		this.lista_nodos = lista_nodos;
	}

	public void addNodo(Nodo nodo) {
		if (this.lista_nodos == null) {
			this.lista_nodos = new ArrayList<Nodo>();
		}
		this.lista_nodos.add(nodo);
	}

	public void relacionaNodoArco() {
		for (Nodo n : this.lista_nodos) {
			for (Arco a : this.lista_arcos) {
				if (n.getId_nodo().equalsIgnoreCase(a.getNodo_origen().getId_nodo())) {
					n.addArco(a);
				}
			}
		}
	}

	public void toStringGrafo() {
		for (Nodo n : this.lista_nodos) {
			System.out.println("Grafo{" + "Id_Nodo=" + n.getId_nodo() + ", x=" + n.getX() + ", y=" + n.getY() + '}');
			if (!n.getLista_arcos().isEmpty()) {
				n.toStringLista();
			}
			
		}
	}
}
