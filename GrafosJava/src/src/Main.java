

public class Main {
	
	

	public static void main(String[] args) throws Exception {
		for(int i=0; i<=2; i++)
		{
		GrafoMatriz galleta = new GrafoMatriz(10);
		//galleta.addAristaB(0, 1);
		//galleta.addAristaB(1, 2);
		//galleta.addAristaB(2,9);
		//galleta.addAristaB(2, 3);
	    //galleta.addAristaB(6, 2);
		//galleta.addArista(3, 1);
		//galleta.addAristaB(1, 6);
		//galleta.addAristaB(5, 8);
		//galleta.addAristaB(2, 3);
		galleta.fillgrafo();
		galleta.Mtablero();
		galleta.loquepides();
		}
			
	}
}