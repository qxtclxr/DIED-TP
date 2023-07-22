package datos;
/*hola*/
import java.util.*;
import java.util.stream.*;

public class Grafo {
	private List<Sucursal> vertices;
	private List<Ruta> aristas;
	
	public Grafo() {
		this.vertices = new ArrayList<Sucursal>();
		this.aristas = new ArrayList<Ruta>();
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
