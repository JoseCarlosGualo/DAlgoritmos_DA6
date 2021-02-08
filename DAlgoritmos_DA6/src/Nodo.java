
public class Nodo {
	private String x;
	private String y;
	private String id_nodo;

	public Nodo(String x, String y, String id_nodo) {
		super();
		this.x = x;
		this.y = y;
		this.id_nodo = id_nodo;
	}

	public Nodo() {
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getId_nodo() {
		return id_nodo;
	}

	public void setId_nodo(String id_nodo) {
		this.id_nodo = id_nodo;
	}

	public String toString() {
		return "Nodo{" + "Id_Nodo=" + this.id_nodo + ", x=" + this.x + ", y=" + this.y + '}';
	}
}
