
public class Fregadero {
	private int tamFregadero;
	
	private int n;
	
	public Fregadero(int tamFregadero2) {
		tamFregadero = tamFregadero2;
		n = 0;
	}
	
	public synchronized void agregarCubierto() {
		try {
			if(n==tamFregadero) {
				wait();
			}
			else {
				n++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void quitarCubierto() {
		try {
			if(n==0) {
				wait();
			}
			else {
				n--;
				notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
