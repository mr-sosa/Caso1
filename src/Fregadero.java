
public class Fregadero {
	private int tamFregadero;
	
	private int nT1;
	private int nT2;
	
	public Fregadero(int tamFregadero2) {
		tamFregadero = tamFregadero2;
		nT1 = 0;
		nT2 = 0;
	}
	
	public synchronized void agregarCubierto(int t) {
		try {
			while((nT1 + nT2) ==tamFregadero) {
				wait();
			}
			if(t == 1) {
				nT1++;
			} else if (t == 2) {
				nT2++;
			}
			System.out.println("Se agregó un cubierto al Fregadero, stock actual T1: " + nT1+ " T2:"+ nT2);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized int quitarCubierto() {
		int resp = 0;
		try {
			if(nT1!=0) {
				nT1--;
				resp = 1;
				System.out.println("Se quitó un cubierto al Fregadero, stock actual: T1: " + nT1+ " T2:"+ nT2);
			}
			else if(nT1!=0) {
				nT2--;
				resp = 2;
				System.out.println("Se quitó un cubierto al Fregadero, stock actual: T1: " + nT1+ " T2:"+ nT2);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
