package app;

import java.sql.Time;
import java.util.ArrayList;

import datos.Operatividad;
import datos.Ruta;
import datos.Sucursal;
import datos.TipoSucursal;
import gui.*;
import logica.grafo.Grafo;

public class App {
	public static void main(String[] args) {

		probarGrafos();
		try {				

			Ventana frame = new Ventana();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void probarGrafos() {
		ArrayList<Sucursal> suc = new ArrayList<Sucursal>();
		suc.add(new Sucursal(13245,"Moron",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.OPERATIVA,TipoSucursal.COMERCIAL));
		suc.add(new Sucursal(12345,"Santo Tome",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.OPERATIVA,TipoSucursal.COMERCIAL));
		suc.add(new Sucursal(13425,"Parana",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.OPERATIVA,TipoSucursal.COMERCIAL));
		suc.add(new Sucursal(14235,"Santa Fe",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.OPERATIVA,TipoSucursal.COMERCIAL));
		
		ArrayList<Ruta> ruta = new ArrayList<Ruta>();
		ruta.add(new Ruta(12345,suc.get(0),suc.get(1),Operatividad.OPERATIVA,120,6000F));
		ruta.add(new Ruta(12345,suc.get(1),suc.get(2),Operatividad.OPERATIVA,120,6000F));
		ruta.add(new Ruta(12345,suc.get(2),suc.get(0),Operatividad.OPERATIVA,120,6000F));
		ruta.add(new Ruta(12345,suc.get(0),suc.get(3),Operatividad.OPERATIVA,120,6000F));
		ruta.add(new Ruta(12345,suc.get(1),suc.get(3),Operatividad.OPERATIVA,120,6000F));
		ruta.add(new Ruta(12345,suc.get(2),suc.get(3),Operatividad.OPERATIVA,120,6000F));
		
		Grafo grafo = new Grafo();
		grafo.addVertice(suc);
		grafo.addArista(ruta);
		
		System.out.println(grafo.caminosEntreDosNodos(suc.get(0),suc.get(3)));
	}
}
