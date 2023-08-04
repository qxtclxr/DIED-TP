package app;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import datos.Operatividad;
import datos.Ruta;
import datos.Sucursal;
import datos.TipoSucursal;
import gui.*;
import logica.grafo.Grafo;

public class App {

	public static void main(String[] args) {
		try {	
			Ventana frame = new Ventana();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
