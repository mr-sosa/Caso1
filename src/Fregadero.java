
public class Fregadero {
	private int tamFregadero;
	
	private int n;
	
	public Fregadero(int tamFregadero2) {
		tamFregadero = tamFregadero2;
		n = 0;
	}
	
	public synchronized void agregarCubierto() {
		try {
			while(n==tamFregadero) {
				wait();
			}
			n++;
			System.out.println("Se agregó un cubierto al Fregadero, stock actual: " + n);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized int quitarCubierto() {
		int resp = 0;
		try {
			if(n!=0) {
				n--;
				resp = 1;
				System.out.println("Se quitó un cubierto al Fregadero, stock actual: " + n);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
