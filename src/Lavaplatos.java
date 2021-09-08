
public class Lavaplatos extends Thread{

	private Fregadero f;
	
	private Mesa m;
	
	public Lavaplatos(Fregadero pF, Mesa pM) {
		f = pF;
		m = pM;
		
	}

	public void run(){
		boolean loop = true;
		while(loop) {
			try {
				
				int x = f.quitarCubierto();
				if (x == 1) {
					int numero = (int)(Math.random()*(2000-1000+1)+1000);
					sleep(numero);
					System.out.println("2");
					m.agregarCubierto(3);
					System.out.println("3");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
