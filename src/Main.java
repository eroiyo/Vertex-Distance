import java.io.File;
import java.util.Scanner;


public class Main {
	
	

	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);
        System.out.print("Insert File");
        String direction = keyboard.nextLine();
		File f= new File(direction);
		GrafoMatriz Graph = new GrafoMatriz(f);
		if (f.isFile()) {
        System.out.print("select frist actor, it will be the origin");
        String actor_a = keyboard.nextLine();
        System.out.print("select the second actor, it will be the destiny");
        String actor_b = keyboard.nextLine();
        keyboard.close();
        int result =Graph.actor_distance(actor_a, actor_b);
        if(result==-1)
        {
        	System.out.println("The actors dont have any relations");
        }
        else if(result==-2)
        {
        	System.out.println("Frist actor does not exist");
        }
        else if(result==-3)
        {
        	System.out.println("The Second actor does not exist");
        }
        else
        {
        	System.out.println("the distance is "+result);
        }
		Graph.result();
		}
	else
	{
		System.out.println("file not found");
	}
}
			
}