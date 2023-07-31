package logica.grafo;

import java.util.*;
import java.util.stream.*;
import datos.*;

public class Grafo {
	private List<Sucursal> vertices;
	private List<Ruta> aristas;
	
	public Grafo() {
		this.vertices = new ArrayList<Sucursal>();
		this.aristas = new ArrayList<Ruta>();
	}
	
	public List<Sucursal> getVertices() {
		return vertices;
	}
	
	public void addVertice(Sucursal vertice) {
		this.vertices.add(vertice);
	}
	
	public void addVertice(List<Sucursal> vertices) {
		for(Sucursal vertice: vertices)
			this.vertices.add(vertice);
	}

	public List<Ruta> getAristas() {
		return aristas;
	}
	
	public void addArista(Ruta arista) {
		this.aristas.add(arista);
	}
	
	public void addArista(List<Ruta> aristas) {
		for(Ruta arista: aristas)
			this.aristas.add(arista);
	}
	
	public void removeVertice(Sucursal vertice) {
		this.vertices.remove(vertice);
		this.aristas = this.aristas.stream().
				filter(a -> !(a.getOrigen().equals(vertice) || a.getDestino().equals(vertice))).
				collect(Collectors.toList());
	}
	
	public void removeArista(Ruta arista) {
		this.aristas.remove(arista);
	}


	public List<Ruta> rutasSalientes(Sucursal suc) {
		return this.aristas.stream().
			   filter(rut -> rut.getOrigen().equals(suc)).
			   collect(Collectors.toList());
	}
	
	public Integer gradoSalida(Sucursal suc) {
		Integer sol = 0;
		for(Ruta rut : this.aristas) {
			if(rut.getOrigen().equals(suc))
				sol++;
		}
		return sol;
	}
	
	public Integer gradoEntrada(Sucursal suc) {
		Integer sol = 0;
		for(Ruta rut : this.aristas) {
			if(rut.getDestino().equals(suc))
				sol++;
		}
		return sol;
	}
	
	public Map<List<Sucursal>,Integer> caminosEntreDosSucursales(Sucursal ini, Sucursal fin){
		Map<List<Sucursal>,Integer> sol = new HashMap<List<Sucursal>,Integer>();
		caminosEntreDosSucursales(ini,fin,new ArrayList<Sucursal>(),0,sol);
		return sol;
	}
	
	private void caminosEntreDosSucursales
	(Sucursal actual,Sucursal fin, List<Sucursal> caminoActual, Integer duracion, Map<List<Sucursal>,Integer> sol) {
		caminoActual.add(actual);
		if(actual.equals(fin)) {
			sol.put(caminoActual,duracion);
			return;
		}
		for(Ruta rut : this.rutasSalientes(actual)) {
			if(!caminoActual.contains(rut.getDestino())) {
				caminosEntreDosSucursales(
						rut.getDestino(),
						fin,
						caminoActual.stream().collect(Collectors.toList()),
						duracion+rut.getDuracion(),
						sol);
			}
		}
		return;
	}
}
