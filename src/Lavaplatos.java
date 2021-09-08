
public class Lavaplatos extends Thread{

	private Fregadero f;
	
	private Mesa m;
	
	public Lavaplatos(Fregadero pF, Mesa pM) {
		f = pF;
		m = pM;
		
	}

	private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
    }
	
	public void run(){
		while(true) {
			try {
				f.quitarCubierto();
				doAction(System.nanoTime() + ": Sacar del fregadero");
				int numero = (int)(Math.random()*(2000-1000+1)+1000);
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			m.agregarCubierto();
			notify();
		}
		
	}
}
