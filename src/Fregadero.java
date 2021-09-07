
public class Fregadero {

	private int tamFregadero;
	
	private int n;
	
	public Fregadero(int tamFregadero2) {
		tamFregadero = tamFregadero2;
		n = 0;
	}
	
	public synchronized void agregarCubierto() {
		while(n == tamFregadero) {
			Thread.yield();
		}
		n++;
	}
	
	public synchronized void quitarCubierto() {
		while(n == 0) {
			Thread.yield();
		}
		n--;
	}
}
