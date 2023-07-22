package datos;

import java.util.*;

public class Grafo {
	private List<Sucursal> vertices;
	private List<Ruta> aristas;
	
	public Grafo() {
		this.vertices = new ArrayList<Sucursal>();
		this.aristas = new ArrayList<Ruta>();
	}
	
	public List<Sucursal> getAdyacentes(Sucursal suc) {
		ArrayList<Sucursal> sol = new ArrayList<Sucursal>();
		for(Ruta rut : this.aristas) {
			if(rut.getOrigen().equals(suc))
				sol.add(rut.getDestino());
		}
		return sol;
	}
}
