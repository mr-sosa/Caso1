import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Mesa {
	
	private static int totalCubiertosT1;
	
	private static int totalCubiertosT2;
	
	public Mesa() {
	}
	
	public static void main(String [] args) {
		Properties concurrencia = new Properties();
		InputStream configInput = null;	   
		 
		int numComensales = 0;
		int numPlatos = 0;
		int tamFregadero = 0;
		
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
		
		new Lavaplatos().start();
		
		for(int i=0; i<numComensales; i++) {
			new Comensal(i, numPlatos, f).start();
		}
	}
}
