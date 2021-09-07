import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Mesa {
	
	private int numCubiertosT1;
	
	private int numCubiertosT2;
	
	public Mesa(int totalCubiertosT1, int totalCubiertosT2) {
		numCubiertosT1 = totalCubiertosT1;
		numCubiertosT2 = totalCubiertosT2;
	}
	
	public synchronized void agregarCubierto(int tipo) {
		if(tipo == 1) {
			numCubiertosT1 ++;
		} else if(tipo == 2) {
			numCubiertosT2 ++;
		}
	}
	
	public static void main(String [] args) {
		Properties concurrencia = new Properties();
		InputStream configInput = null;	   
		 
		int numComensales = 0;
		int numPlatos = 0;
		int tamFregadero = 0;
		int totalCubiertosT1 = 0;
		int totalCubiertosT2 = 0;
		
		try{
			configInput = new FileInputStream("data/concurrencia.properties");
			concurrencia.load(configInput);
			System.out.println("Cargando datos de concurrencia");
			numComensales = Integer.parseInt(concurrencia.getProperty("concurrencia.numComensales"));
			System.out.println("Número de comensales: " + concurrencia.getProperty("concurrencia.numComensales"));
			
			totalCubiertosT1 = Integer.parseInt(concurrencia.getProperty("concurrencia.numCubiertosT1"));
			System.out.println("Número de cubiertos tipo 1: " + concurrencia.getProperty("concurrencia.numCubiertosT1"));
			
			totalCubiertosT2 = Integer.parseInt(concurrencia.getProperty("concurrencia.numCubiertosT2"));
			System.out.println("Número de cubiertos tipo 2: " + concurrencia.getProperty("concurrencia.numCubiertosT2"));
			
			numPlatos = Integer.parseInt(concurrencia.getProperty("concurrencia.numPlatos"));
			System.out.println("Número de platos: " + concurrencia.getProperty("concurrencia.numPlatos"));
			
			tamFregadero = Integer.parseInt(concurrencia.getProperty("concurrencia.tamFregadero"));
			System.out.println("Tamaño del fregadero: " + concurrencia.getProperty("concurrencia.tamFregadero"));
			
			System.out.println("Se cargaron correctamente los datos de concurrencia");
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error cargando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		Fregadero f = new Fregadero(tamFregadero);
		
		Mesa m = new Mesa(totalCubiertosT1, totalCubiertosT2);
		
		new Lavaplatos(f, m).start();
		
		for(int i=0; i<numComensales; i++) {
			new Comensal(i, numPlatos, f, m).start();
		}
	}
}
