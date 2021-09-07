
public class Comensal extends Thread {

//	private Object t1;
//	
//	private Object t2;
	
	private int numPlatos;
	
	private int id;
	
	private Fregadero f;
	
	private Mesa m;
	
	public Comensal(int pID,  int pPlatos, Fregadero pF, Mesa pM) {
		id = pID;
//		this.t1 = t1;
//		this.t2 = t2;
		numPlatos = pPlatos;
		f = pF;
		m = pM;
	}
	
	private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        int numero = (int)(Math.random()*(3000-1000+1)+1000);
        Thread.sleep(numero);
    }
	
	private synchronized void comer() {
		numPlatos--;
    }
	
	public void run(){
		try {
			while (true) {
				int resultadoT1 = m.cogerCubiertoT1();
				doAction(System.nanoTime() + ": Coger cubierto T1");
				int resultadoT2 = m.cogerCubiertoT2();
				doAction(System.nanoTime() + ": Coger cubierto T2");
				
				if(resultadoT1==1 && resultadoT2==1) {
					comer();
					doAction(System.nanoTime() + ": Comer");
					f.agregarCubierto();
					doAction(System.nanoTime() + ": Dejar cubierto T1");
					f.agregarCubierto();
					doAction(System.nanoTime() + ": Dejar cubierto T2");
				}
				else if(resultadoT1==1 && resultadoT2==0 || resultadoT1==0 && resultadoT2==1) {
					m.agregarCubierto();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
