
public class Comensal extends Thread {

	private int numPlatos;
	
	private int id;
	
	private Fregadero f;
	
	private Mesa m;
	
	public Comensal(int pID, int pPlatos, Fregadero pF, Mesa pM) {
		id = pID;
		numPlatos = pPlatos;
		f = pF;
		m = pM;
	}

	public void run(){
		
	}
}
