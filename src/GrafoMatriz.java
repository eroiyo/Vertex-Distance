import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GrafoMatriz {
	int numVertexs;
	boolean[][] matrix;
	HashMap<String,Integer> listav = new HashMap<String,Integer>();
	HashMap<String,Integer> noactors = new HashMap<String,Integer>();
	
	
	public GrafoMatriz(File f) {
		   HashMap<String,Integer> vertex_list = new HashMap<String,Integer>();
			try {
			Scanner s = new Scanner(f);
			int x=0;
			while(s.hasNextLine())
			{
			String line = s.nextLine();
			String[] words = line.split("/");
			this.noactors.put(words[0],x);
			for (String word : words)
			{
				if(!(vertex_list.containsKey(word)))
				{
					vertex_list.put(word,x);
					x++;
				}
			}
			this.listav=vertex_list;
			}
			s.close();
			
			}catch(FileNotFoundException e){
				System.out.println("Archive does not exist...");
			}
		this.numVertexs = vertex_list.size();
		matrix = new boolean[vertex_list.size()][vertex_list.size()];
		try {
		Scanner s = new Scanner(f);
		while(s.hasNextLine())
		{
		String line = s.nextLine();
		String[] words = line.split("/");
		String movie = words[0];
		int movie_id=this.listav.get(movie);
		for(int i=1; i<words.length; i++)
		{
			this.addEdgesB(this.listav.get(words[i]), movie_id);
		}
		}
		s.close();
		}catch(FileNotFoundException e){
			System.out.println("Archive does not exist...");
		}
	}

	public void desconectar(int e1, int e2) {
		matrix[e1][e2] = false;
	}

	public void addVertice() {
		numVertexs++;
		boolean[][] auxiliar = new boolean[numVertexs][numVertexs];
		for (int i = 0; i < numVertexs - 1; i++) {
			auxiliar[i] = Arrays.copyOf(matrix[i], numVertexs);
		}
		matrix = auxiliar;
		
	}

	public void delVertice(int ver) {
		if (ver > numVertexs || ver < 0) {
			System.out.println("The selected does not exist.");
		} else {
			for (int i = 1; i < matrix.length; i++) {
				matrix[ver][i] = false;
			}
			for (int i = 1; i < matrix.length; i++) {
				matrix[i][ver] = false;
			}
		}
	}

	public void addEdgesA(int e1, int e2) {
		matrix[e1][e2] = true;
	}
	public void addEdgesB(int e1, int e2)
	{
		matrix[e1][e2] = true;
		matrix[e2][e1] = true;
	}
	public boolean getEdgesA(int e1, int e2)
	{
	 if	(matrix[e1][e2]==  true)
	 {
		 return true;
	 }
	 return false;
	}
	public boolean getEdgesB(int e1, int e2)
	{
		if(matrix[e1][e2] && matrix[e2][e1] == true)
		return true;
		else
			return false;
	}

	public int vertex_grade(int ver) {
		int flag = 0;
		
		for (int i = 0; i < this.numVertexs; i++) {
			if (matrix[ver][i] == true)
				flag++;

		}
		return flag;
	}

	public boolean walk(char[] ruta) {
		if (ruta.length < 1) {
			System.out.println("the travels cant be shorten than 1");
			return false;
		}
		for (int i = 1; i < ruta.length; i++) {
			int c = interpreter(ruta[i]);
			if (matrix[c][c - 1] == false) {
				return false;
			}
		}
		return true;

	}

	public boolean road(char[] route) {
		for (int i = 0; i < route.length; i++) {
			for (int e = 0; e < route.length; e++) {
				if (route[i] == route[e]) {
					System.out.println(
							"this road have repeated steps, its mean its not a road maybe a hike");
					return false;
				}
			}
		}
		return this.walk(route);
	}

	static public int interpreter(char z) {
		Character a = z;
		a.toLowerCase(a);
		int b = a.charValue();
		b = b - 96;

		return b;
	}
	
    public void show()
    {
	System.out.println("************************************************");
    System.out.print("  ");
    for(int e1=0; e1<numVertexs; e1++)
    {
    	System.out.print(e1);
    }
    System.out.println(" ");
    
    for (int e1=0; e1<numVertexs; e1++)
    {
    	System.out.print(e1+" ");
    	for(int e2=0; e2<numVertexs; e2++)
    	{
                System.out.print(trueness(matrix[e1][e2]));
    		if(e2==numVertexs-1)
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
	
	public ArrayList<Integer> width_travel(int nodeI) {
	     boolean[] visited_width = new boolean[numVertexs];

		        ArrayList<Integer> recorridos = new ArrayList<Integer>();


		        visited_width[nodeI] = true;


		        ArrayList<Integer> queue = new ArrayList<Integer>();


		        recorridos.add(nodeI);


		        queue.add(nodeI);


		        while (!queue.isEmpty()) {

		            int j = queue.remove(0); 


		            for (int i = 0; i < matrix.length; i++) {


		                if (matrix[j][i] == true && !visited_width[i]) {

		                    queue.add(i);


		                    recorridos.add(i);

		                    visited_width[i] = true;

		                }

		            }

		        }

		        return null;
		    }
	
	
public int distance(int nodeI, int nodeO) {                                      
    boolean[] visited_width = new boolean[this.numVertexs];
    int e=nodeI;  
    int distance=0;
    boolean e_reached=false;
    boolean inserted=false;
    System.out.println("Loading...");
    if(nodeI==nodeO) 
    {
    	return distance;
    }
	        
	        visited_width[nodeI] = true; 
	        ArrayList<Integer> queue = new ArrayList<Integer>();
	        
	        
	        queue.add(nodeI); 
	        while (!queue.isEmpty()) {
	        	if(queue.get(0)==e)   
	        	{
	        		e_reached=true;
	        	}
	            int j = queue.remove(0); 
	            if(j==nodeO)
	            {
	            	return (distance/2); 
	            }
	            for (int i = 0; i < this.matrix.length; i++) {
	                if (this.matrix[j][i] == true && !visited_width[i]) {
	                    queue.add(i);
	                    visited_width[i] = true;
	                    inserted=true;
	                }
	            }
	            if(e_reached==true && inserted==true)
	            {
	            e=queue.get(queue.size()-1);
	            e_reached=false;
        		distance++;
        		inserted=false;
	            }
	        }
	        return -1; 	    }
public int eccentricity(int nodeI) {                                     
    boolean[] visited_width = new boolean[this.numVertexs];
    int e=nodeI; 
    int distance=0;
    boolean inserted=false;
    System.out.println("Loading...");
	        visited_width[nodeI] = true;
	        ArrayList<Integer> queue = new ArrayList<Integer>(); 
	        
	        
	        queue.add(nodeI); 
	        while (!queue.isEmpty()) {
	            int j = queue.remove(0); 
	            for (int i = 0; i < this.matrix.length; i++) {
	                if (this.matrix[j][i] == true && !visited_width[i]) {
	                    queue.add(i);
	                    visited_width[i] = true;
	                    inserted=true;
	                }
	            }
	            if(e==j && inserted==true)
	            {
	            e=queue.get(queue.size()-1);
        		distance++;
        		inserted=false;
	            }
	        }
	        return distance; 
	    }
int actor_distance(String actor_a, String actor_b)
{
	if(noactors.containsKey(actor_a) || (!this.listav.containsKey(actor_a)))
	{
		return -2;
	}
	if(noactors.containsKey(actor_b) || (!this.listav.containsKey(actor_b)))
	{
		return -3;
	}
	return distance(this.listav.get(actor_a),this.listav.get(actor_b));
}
	public void result()
	{
		System.out.println("Be Patient");
		int temp=0;
		int grande=0;
		for(int i=1;i<numVertexs;i++)
		{
			if(!this.noactors.containsValue(i))
			temp=eccentricity(i);
			if(temp!=0) {
		    if(temp>grande)
			{
				grande=temp;
			}
			}
		}
		System.out.println("Diameter is equal to: "+(grande/2));
		System.out.println("************************************************");
}
}
