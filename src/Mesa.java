
public class Mesa {
	//Total cubiertos T1
	private int totalCT1;

	//Total cubiertos T2
	private int totalCT2;

	//Numero cubiertos T1 disponibles
	private int numCubiertosT1;

	//Numero cubiertos T2 disponibles
	private int numCubiertosT2;

	public Mesa(int totalCubiertosT1, int totalCubiertosT2) {
		numCubiertosT1 = totalCubiertosT1;
		numCubiertosT2 = totalCubiertosT2;
		totalCT1 = totalCubiertosT1;
		totalCT2 = totalCubiertosT2;
	}

	public synchronized void agregarCubierto(int t) {
		
		if(t == 1) {
			numCubiertosT1 ++;
			System.out.println("Se agrego cubierto T1, stock actual T1: " + numCubiertosT1);
		} else if(t == 2) {
			numCubiertosT2 ++;
			System.out.println("Se agrego cubierto T2, stock actual T2: " + numCubiertosT2);
		} else if(t == 3) {
			System.out.println("Cubierto lavado");
			int tipo = (int)(Math.random()*(2-1+1)+1);
			if(tipo == 1 && numCubiertosT1 < totalCT1) {
				numCubiertosT1 ++;
				System.out.println("Se agrego cubierto T1, stock actual T1: " + numCubiertosT1);
				notify();
			} else if(tipo == 2 && numCubiertosT2 < totalCT2) {
				numCubiertosT2 ++;
				System.out.println("Se agrego cubierto T2, stock actual T2: " + numCubiertosT2);
				notify();
			} else if(tipo == 1 && numCubiertosT1 == totalCT1 && numCubiertosT2 < totalCT2) {
				numCubiertosT2 ++;
				System.out.println("Se agrego cubierto T2, stock actual T2: " + numCubiertosT2);
				notify();
			} else if(tipo == 2 && numCubiertosT2 == totalCT2 && numCubiertosT1 < totalCT1) {
				numCubiertosT1 ++;
				System.out.println("Se agrego cubierto T1, stock actual T1: " + numCubiertosT1);
				notify();
			}System.out.println("dejarcubierto");
		}
	}

	//resp=0->Esperando cubierto ; resp=1->Cubierto recogido
	public synchronized int cogerCubiertoT1() {
		int resp = 999;
		try {
			if(numCubiertosT1 == 0) {
				resp = 0;
			}else if(numCubiertosT1>0 && numCubiertosT1<=totalCT1){
				numCubiertosT1--;
				resp = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;

	}

	//resp=0->Esperando cubierto ; resp=1->Cubierto recogido
	public synchronized int cogerCubiertoT2() {
		int resp = 999;
		try {
			if(numCubiertosT2 == 0) {
				
				resp = 0;
			}else if(numCubiertosT2>0 && numCubiertosT2<=totalCT2) {
				numCubiertosT2--;
				resp = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
