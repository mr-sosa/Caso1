import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.swing.JOptionPane;

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
	
	public synchronized void agregarCubierto() {
		int tipo = (int)(Math.random()*(2-1+1)+1);
		if(tipo == 1 && numCubiertosT1 < totalCT1) {
			numCubiertosT1 ++;
			notify();
		} else if(tipo == 2 && numCubiertosT2 < totalCT2) {
			numCubiertosT2 ++;
			notify();
		} else if(tipo == 1 && numCubiertosT1 == totalCT1 && numCubiertosT2 < totalCT2) {
			numCubiertosT2 ++;
			notify();
		} else if(tipo == 2 && numCubiertosT2 == totalCT2 && numCubiertosT1 < totalCT1) {
			numCubiertosT1 ++;
			notify();
		}
	}
	
	//resp=0->Esperando cubierto ; resp=1->Cubierto recogido
	public synchronized int cogerCubiertoT1() {
		int resp = 999;
		try {
			if(numCubiertosT1 == 0) {
				wait();
				resp = 0;
			}else {
				numCubiertosT1--;
				resp = 1;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return resp;
		
	}
	
	//resp=0->Esperando cubierto ; resp=1->Cubierto recogido
	public synchronized int cogerCubiertoT2() {
		int resp = 999;
		try {
			if(numCubiertosT2 == 0) {
				wait();
				resp = 0;
			}else {
				numCubiertosT2--;
				resp = 1;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return resp;
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
