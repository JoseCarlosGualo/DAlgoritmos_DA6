import java.util.ArrayList;

public class Poblacion {
	private String nombre;
	private ArrayList<Nodo> lista_nodos;
	private ArrayList<Arco> lista_arcos;

	public Poblacion(String nombre, ArrayList<Nodo> lista_nodos, ArrayList<Arco> lista_arcos) {
		this.nombre = nombre;
		this.lista_nodos = lista_nodos;
		this.lista_arcos = lista_arcos;
	}

	public Poblacion() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Nodo> getLista_nodos() {
		return lista_nodos;
	}

	public void setLista_nodos(ArrayList<Nodo> lista_nodos) {
		this.lista_nodos = lista_nodos;
	}

	public ArrayList<Arco> getLista_arcos() {
		return lista_arcos;
	}

	public void setLista_arcos(ArrayList<Arco> list_arcos) {
		this.lista_arcos = list_arcos;
	}

	public int getCantidadNodos() {
		return this.lista_nodos.size();
	}

	public int getCantidadArcos() {
		return this.lista_arcos.size();
	}

	public double getLongitudArcos() {
		double resultado = 0;
		for (Arco a : this.lista_arcos) {
			resultado += a.getLength();
		}
		return resultado;
	}

	public int getCoordenadasXPares() {
		int resultado = 0;
		for (Nodo n : this.lista_nodos) {
			if ((n.getX() % 2.0) == 0.0) {
				resultado++;
			}
		}
		return resultado;
	}

	public int getCoordenadasYImpares() {
		int resultado = 0;
		for (Nodo n : this.lista_nodos) {
			if (((int) n.getY() % 2) != 0) {
				resultado++;
			}
		}
		return resultado;
	}

}
