
public class Comensal extends Thread{
	private int id;
	
	private int numPlatos;
	
	private Fregadero f;
	
	private Mesa m;
	
	private Object t1;
	
	private Object t2;
	
	public Comensal(int pID,  int pPlatos, Fregadero pF, Mesa pM) {
		this.id = pID;
		this.numPlatos = pPlatos;
		this.f = pF;
		this.m = pM;
	}
	
	private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }
	
	public void run() {
		try {
			while (true) {
				int resultadoT1 = m.cogerCubiertoT1();
				doAction(System.nanoTime() + ": Coger cubierto T1");
				int resultadoT2 = m.cogerCubiertoT2();
				doAction(System.nanoTime() + ": Coger cubierto T2");
				
				if(resultadoT1==1 && resultadoT2==1) {
					//comer();
					doAction(System.nanoTime() + ": Comer");
					f.agregarCubierto();
					doAction(System.nanoTime() + ": Dejar cubierto T1");
					f.agregarCubierto();
					doAction(System.nanoTime() + ": Dejar cubierto T2");
				}
				if(resultadoT1==1 && resultadoT2==0) {
					m.agregarCubierto();
					doAction(System.nanoTime() + ": Devolver cubierto T1");
				}
				if(resultadoT1==0 && resultadoT2==1) {
					m.agregarCubierto();
					doAction(System.nanoTime() + ": Devolver cubierto T2");
				}
			}
		} catch (Exception e) {
		}
	}
}
