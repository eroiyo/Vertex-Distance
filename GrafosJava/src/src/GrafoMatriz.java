import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

public class GrafoMatriz {
	int numVertices;
	boolean[][] matriz;

	public GrafoMatriz(int numVertices) {
		this.numVertices = numVertices;
		matriz = new boolean[numVertices][numVertices];
	}

	public void desconectar(int e1, int e2) {
		matriz[e1][e2] = false;
	}

	public void addVertice() {
		numVertices++;
		boolean[][] auxiliar = new boolean[numVertices][numVertices];
		for (int i = 0; i < numVertices - 1; i++) {
			auxiliar[i] = Arrays.copyOf(matriz[i], numVertices);
		}
		matriz = auxiliar;
		
	}

	public void delVertice(int ver) {
		if (ver > numVertices || ver < 0) {
			System.out.println("El seleccionado no existe.");
		} else {
			for (int i = 1; i < matriz.length; i++) {
				matriz[ver][i] = false;
			}
			for (int i = 1; i < matriz.length; i++) {
				matriz[i][ver] = false;
			}
		}
	}

	public void addArista(int e1, int e2) {
		matriz[e1][e2] = true;
	}
	public void addAristaB(int e1, int e2)
	{
		matriz[e1][e2] = true;
		matriz[e2][e1] = true;
	}
	public boolean verAristaA(int e1, int e2)
	{
	 if	(matriz[e1][e2]==  true)
	 {
		 return true;
	 }
	 return false;
	}
	public boolean verAristaB(int e1, int e2)
	{
		if(matriz[e1][e2] && matriz[e2][e1] == true)
		return true;
		else
			return false;
	}

	public int gradodeunvertice(int ver) {
		int bandera = 0;
		
		for (int i = 0; i < this.numVertices; i++) {
			if (matriz[ver][i] == true)
				bandera++;

		}
		return bandera;
	}

	public boolean caminata(char[] ruta) {
		if (ruta.length < 1) {
			System.out.println("los viajes no pueden ser mas cortos que 1");
			return false;
		}
		for (int i = 1; i < ruta.length; i++) {
			int c = interpreter(ruta[i]);
			if (matriz[c][c - 1] == false) {
				return false;
			}
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

	static public int interpreter(char z) {
		Character a = z;
		a.toLowerCase(a);
		int b = a.charValue();
		b = b - 96;

		return b;
	}
	
    public void Mtablero()
    {
	System.out.println("************************************************");
    System.out.print("  ");
    for(int e1=0; e1<numVertices; e1++)
    {
    	System.out.print(e1);
    }
    System.out.println(" ");
    
    for (int e1=0; e1<numVertices; e1++)
    {
    	System.out.print(e1+" ");
    	for(int e2=0; e2<numVertices; e2++)
    	{
                System.out.print(trueness(matriz[e1][e2]));
    		if(e2==numVertices-1)
    		{
    			System.out.println();
    		}
    	}
    	}
    
    }
	private char trueness(boolean any)
	{
		if(any)
			{return '1';}
		else
			{return '0';}
	}
	
	public ArrayList<Integer> recorridoAnchura(int nodoI) {
	     boolean[] visitiadoAnchura = new boolean[numVertices];
		//Lista donde guardo los nodos recorridos

		        ArrayList<Integer> recorridos = new ArrayList<Integer>();

		//El nodo inicial ya está visitado

		        visitiadoAnchura[nodoI] = true;

		//Cola de visitas de los nodos adyacentes

		        ArrayList<Integer> cola = new ArrayList<Integer>();

		//Se lista el nodo como ya recorrido

		        recorridos.add(nodoI);

		//Se agrega el nodo a la cola de visitas

		        cola.add(nodoI);

		//Hasta que visite todos los nodos

		        while (!cola.isEmpty()) {

		            int j = cola.remove(0); //Se saca el primero nodo de la cola

		//Se busca en la matriz que representa el grafo los nodos adyacentes

		            for (int i = 0; i < matriz.length; i++) {

		//Si es un nodo adyacente y no está visitado entonces

		                if (matriz[j][i] == true && !visitiadoAnchura[i]) {

		                    cola.add(i);//Se agrega a la cola de visitas


		                    recorridos.add(i);//Se marca como recorrido

		                    visitiadoAnchura[i] = true;//Se marca como visitado

		                }

		            }

		        }

		        return null;//Devuelvo el recorrido del grafo en anchura

		    }
ArrayList<Integer> Exentricidad(int nodoI)
{
	int distancia =0;
	ArrayList<Integer> recorrido =new ArrayList<Integer>();
	ArrayList<ArrayList<Integer>> opciones=new ArrayList<ArrayList<Integer>>(); 
	recorrido.add(nodoI);
	for(int i=0; i<numVertices; i++)
	{
		if(matriz[nodoI][i]==true && !recorrido.contains(i))
		{
			ArrayList<Integer> temp;
			if(recorrido.contains(nodoI) && nodoI!=recorrido.get(recorrido.size()-1))
				opciones.add(recorrido);
			else
			{
				temp=		ExentricidadB(recorrido,distancia,i);
				opciones.add(temp);
			}
				
		}
	}
	ArrayList<Integer> temp =new ArrayList<Integer>(recorrido);
	for(int i=0; i<opciones.size();i++)
	{
		if(opciones.get(i).size()>temp.size())
		{
			temp=opciones.get(i);
		}
	}
	return temp;
}

ArrayList<Integer> ExentricidadB(ArrayList<Integer> recorrido,int distancia,int nodoS)
{
	distancia++;
	ArrayList<ArrayList<Integer>> opciones=new ArrayList<ArrayList<Integer>>(); 
	recorrido.add(nodoS);
	ArrayList<Integer> original =new ArrayList<Integer>(recorrido);
	for(int i=0; i<numVertices; i++)
	{
		if(matriz[nodoS][i]==true && !recorrido.contains(i))
		{
			ArrayList<Integer> temp;
			if(recorrido.contains(nodoS) && nodoS!=recorrido.get(recorrido.size()-1))
				{opciones.add(recorrido);
				while(recorrido.get(recorrido.size()-1)!=nodoS)
				{
					recorrido.remove(recorrido.size()-1);
				}
				temp=ExentricidadB(recorrido,distancia,i);
				opciones.add(temp);
				}
			else
			{
				temp=		ExentricidadB(recorrido,distancia,i);
				opciones.add(temp);
			}
				
		}
	}
	ArrayList<Integer> temp =new ArrayList<Integer>(recorrido);
	for(int i=0; i<opciones.size();i++)
	{
		if(opciones.get(i).size()>temp.size())
		{
			temp=opciones.get(i);
		}
	}
	return temp;
}


	public void loquepides()
	{
		boolean eaislado=false;
		boolean aislado =false;
		ArrayList<Integer> grande=this.Exentricidad(0);
		ArrayList<Integer> pequeño=this.Exentricidad(0);
		ArrayList<Integer> naislado =new ArrayList();
		if(pequeño.size()==1)
		{aislado=true;}
		for(int i=1;i<numVertices;i++)
		{
			ArrayList<Integer> temp;
			temp=Exentricidad(i);
			if(temp.size()!=0) {
			if(temp.size()>grande.size() && temp!=null)   // no creo que requiera explicacion
			{
				grande=temp;
			}
			if(aislado==true)
			{
				if(temp.size()>1)
					{pequeño=temp;
					aislado=false;
					}
			}
			if(temp.size()<pequeño.size() && temp!=null && temp.size()>1)
			{
				pequeño=temp;
			}
			if(temp.size()==1)
			{
				naislado=temp;
			}
			}
		}
		System.out.println("El Diametro de este grafo es de "+grande.size()+"  "+grande
				);
		if(grande.size()==1)
		{
			System.out.println("Todos los nodos estan aislados");
		}
		else
		{System.out.println("El Radio  de este grafo es de "+pequeño.size());}
		if(aislado==false)
		{
			if(!naislado.isEmpty())
			System.out.println("A no ser que los aislados cuenten, en ese caso "+naislado.size());
		}
		
		System.out.println("Los Vertices Centros son "+pequeño);
		if(!naislado.isEmpty())
		System.out.println("A no ser que los aislados cuenten como los nodo centro en ese caso"+naislado);
		System.out.println("************************************************");
	}
	public void fillgrafo()
	{
		int i=0;
		while(i<numVertices-1)
		{
			int a =(int) (Math.random() * numVertices);
			int b= (int) (Math.random() * numVertices);
			while(b==a)
			{
				b= (int) (Math.random() * numVertices);
			}
			
			if(matriz[a][b]==false)
			{
			this.addAristaB(a, b);
			i++;
			}
		}
	}
	
}
