
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String [] args) {
		Properties concurrencia = new Properties();
		InputStream configInput = null;	   
		
		//Total cubiertos T1
		int totalCT1;
		List<Object> cubiertosT1;
		
		//Total cubiertos T2
		int totalCT2;
		List<Object> cubiertosT2;
		
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
		
		cubiertosT1 = new ArrayList<Object>();
		for(int i=0; i<totalCubiertosT1; i++) {
			Object x = new Object();
			cubiertosT1.add(x);
		}
		
		cubiertosT2 = new ArrayList<Object>();
		for(int i=0; i<totalCubiertosT2; i++) {
			Object x = new Object();
			cubiertosT2.add(x);
		}
		
		Mesa m = new Mesa(totalCubiertosT1, totalCubiertosT2);
		
		new Lavaplatos(f,m).start();
		
		for(int i=0; i<numComensales; i++) {
			new Comensal(i, numPlatos, f, m).start();
		}
	}
}
