import java.util.ArrayList;
import java.util.Iterator;

public class Vertice

{
	char nombre;
	int grado;
	ArrayList<Vertice> conexiones;

	public Vertice(char character) {
		this.nombre = character;
		this.grado = 0;
		this.conexiones = new ArrayList<Vertice>();
	}

	public char nombre() {
		return this.nombre;
	}

	public int grado() {
		return this.grado;
	}

	public Vertice Busqueda(char letra) {
		Iterator<Vertice> conexionesIterator = conexiones.iterator();
		while (conexionesIterator.hasNext()) {
			Vertice x = conexionesIterator.next();
			if (x.nombre == letra)
				return x;
		}
		return null;
	}

	public void delArista(char letra) {
		Iterator<Vertice> conexionesIterator = conexiones.iterator();
		while (conexionesIterator.hasNext()) {
			Vertice x = conexionesIterator.next();
			if (x.nombre == letra) {
				conexionesIterator.remove();
				this.grado--;
				break;
			}
		}
	}

	public void addArista(Vertice direccion) {

		this.conexiones.add(direccion);
		this.grado++;
	}

	public void desaparecer() {
		Iterator<Vertice> conexionesIterator = conexiones.iterator();
		while (conexionesIterator.hasNext()) {
			Vertice x = conexionesIterator.next();
			x.conexiones.remove(this);
			conexionesIterator.remove();
			this.grado--;

		}
	}

}