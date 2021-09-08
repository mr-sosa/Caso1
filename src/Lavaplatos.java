
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
					System.out.println("Lavando T1");
					sleep(numero);
					System.out.println("Se terminó de lavar T1");
					m.agregarCubierto(1);
					
				}else if (x == 2) {
					int numero = (int)(Math.random()*(2000-1000+1)+1000);
					System.out.println("Lavando T1");
					sleep(numero);
					System.out.println("Se terminó de lavar T2");
					m.agregarCubierto(2);					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
