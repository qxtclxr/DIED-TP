package datos;

import java.util.*;
import java.util.stream.*;

public class Grafo {
	private List<Sucursal> vertices;
	private List<Ruta> aristas;
	
	public Grafo() {
		this.vertices = new ArrayList<Sucursal>();
		this.aristas = new ArrayList<Ruta>();
	}
	
	public List<Sucursal> getAdyacentes(Sucursal suc) {
		return this.aristas.stream().
							filter(rut -> rut.getOrigen().equals(suc)).
							map(rut -> rut.getDestino()).
							collect(Collectors.toList());
	}
	
	public long gradoSalida(Sucursal suc) {
		return this.aristas.stream().
							filter(rut -> rut.getOrigen().equals(suc)).
							count();
	}
}
