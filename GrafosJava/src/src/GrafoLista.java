import java.util.ArrayList;
import java.util.Iterator;

public class GrafoLista {
	private ArrayList<Vertice> lista;
	private int numvertices;

	public GrafoLista() {
		numvertices = 0;
		this.lista =new ArrayList<Vertice>();
	}

	public void addVertice(char letra) {
				Vertice nuevo = new Vertice(letra);
				lista.add(nuevo);
			}

	public Vertice Busqueda(char letra) {
		Iterator<Vertice> listaIterator = lista.iterator();
		while (listaIterator.hasNext()) {
			Vertice x = listaIterator.next();
			if (x.nombre == letra)
				return x;
		}
		return null;
	}

	public boolean Existe(char letra) {
		Iterator<Vertice> listaIterator = lista.iterator();
		while (listaIterator.hasNext()) {
			Vertice x = listaIterator.next();
			if (x.nombre == letra)
				return true;
		}
		return false;
	}
	
	public void delVertice(char nombre) {
		Iterator<Vertice> listaIterator = lista.iterator();
		while (listaIterator.hasNext()) {
			Vertice x = listaIterator.next();
			if (x.nombre == nombre) {
				x.desaparecer();
				listaIterator.remove();
				break;
			}
		}
	}

	public void AristaA(char nombredeunvertice, char nombredelotrovertice) {
		Vertice temp1 = this.Busqueda(nombredeunvertice);
		Vertice temp2 = this.Busqueda(nombredelotrovertice);
		temp1.conexiones.add(temp2);
		temp2.conexiones.add(temp1);
	}

	// public void AristaB(char nombredeltrasmisor, char nombredelreceptor)
	// {
	// }
	// muy pronto
	public int gradodeunvertice(char nombre) {
		Vertice temp1 = this.Busqueda(nombre);
		return temp1.grado;
	}

	public int numvertices() {
		return this.numvertices;
	}
	public boolean caminata(char[] ruta) 
	{
		if(ruta.length<=1)
		{
			System.out.println("los paseos deben ser mas largas que 1 paso");
			return false;
		}
		Vertice x = this.Busqueda(ruta[0]);
		int i =ruta.length;
		if(x==null)
		{
			System.out.println("Este camino tiene vertices inexistentes");
			return false;
		}
		int a=1;
		while(a<i)
		{
			x=x.Busqueda(ruta[i]);
			if(x==null)
			{
				return false;
		}
			a++;
		}
		return true;
	}
	
	public boolean camino(char[] ruta) {
		for (int i = 0; i < ruta.length; i++) {
			for (int e = 0; e < ruta.length; e++) {
				if (ruta[i] == ruta[e]) {
					System.out.println(
							"esto tiene pasos repetidos, no puede ser un camino, mas bien podria ser una caminata");
					return false;
				}
			}
		}
		return this.caminata(ruta);
	}
}