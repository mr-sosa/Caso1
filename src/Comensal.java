
public class Comensal extends Thread {

	private int numPlatos;
	
	private int id;
	
	private Fregadero f;
	
	public Comensal(int pID, int pPlatos, Fregadero pF) {
		id = pID;
		numPlatos = pPlatos;
		f = pF;
	}

	public void run(){
		
	}
}
