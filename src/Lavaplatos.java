
public class Lavaplatos extends Thread{

	private Fregadero f;
	
	private Mesa m;
	
	public Lavaplatos(Fregadero pF, Mesa pM) {
		f = pF;
		m = pM;
		
	}

	public void run(){
		while(true) {
			f.quitarCubierto();
			try {
				int numero = (int)(Math.random()*(2000-1000+1)+1000);
				Thread.sleep(numero);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//m.agregarCubierto();
		}
	}
}
