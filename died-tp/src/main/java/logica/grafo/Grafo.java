package logica.grafo;

import java.util.*;
import java.util.stream.*;
import datos.*;
import died.estructuras.Edge;
import died.estructuras.Vertex;

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
	
	public float flujoMaximo(Sucursal origen, Sucursal destino, List<List<Ruta>> result) {
		float flujoMaximo= 0;
		List<Sucursal> copiaNodos =this.vertices.stream().collect(Collectors.toList());
		List<Ruta> copiaArista =this.aristas.stream().collect(Collectors.toList());
		flujoMaximo=flujoMaximoAux(origen,destino,result);
		
		this.aristas=copiaArista;
		this.vertices=copiaNodos;
		
		
		
		return flujoMaximo;
	}
	private Float flujoMaximoAux(Sucursal origen, Sucursal destino, List<List<Ruta>> res) {
		float flujoMax=0;
		while(true) {
			List<Sucursal> listaNodos=this.augmentingBFS(origen,destino);
			if(listaNodos==null)
				break;
			List<Ruta> camino=this.reconstruirCaminoNoNulo(listaNodos);
			res.add(camino);
			Float capMin=Float.MAX_VALUE;
			for(Ruta r:camino) {
				capMin=Math.min(capMin, r.getCapacidadMaxima());
			}
			for(Ruta r:camino) {
				r.setCapacidadMaxima(r.getCapacidadMaxima()-capMin);
			}
			flujoMax+=capMin;
		
			
		}
		return flujoMax;
	}
	
	
	public List<Sucursal> augmentingBFS(Sucursal inicio,Sucursal fin){
		List<Sucursal> resultado = new ArrayList<Sucursal>();
		//estructuras auxiliares
		Queue<Sucursal> pendientes = new LinkedList<Sucursal>();
		HashSet<Sucursal> marcados = new HashSet<Sucursal>();
		marcados.add(inicio);
		pendientes.add(inicio);
		
		while(!pendientes.isEmpty()){
			Sucursal actual = pendientes.poll();
			if(actual.equals(fin)) {
				resultado.add(actual);
				return resultado;
			}
			List<Ruta> salientes = this.rutasSalientes(actual);
			resultado.add(actual);
			for(Ruta r : salientes){
				if(!marcados.contains(r.getDestino()) && r.getCapacidadMaxima()>0){ 
					pendientes.add(r.getDestino());
					marcados.add(r.getDestino());
				}
			}
		}		
		//System.out.println(resultado);
		return null;
 	}
	private List<Sucursal> getNeighbourhood(Sucursal unNodo){ 
		List<Sucursal> salida = new ArrayList<Sucursal>();
		for(Ruta enlace : this.aristas){
			if( enlace.getOrigen().equals(unNodo)){
				salida.add(enlace.getDestino());
			}
		}
		return salida;
	}
	
	private List<Ruta> reconstruirCaminoNoNulo(List<Sucursal> listaNodos){
		List<Ruta> result=new ArrayList<Ruta>();
		int i=0; 
		int max=listaNodos.size();
		for(i=0;i<max-1;i++) {
			Sucursal actual=listaNodos.get(i);
			List<Ruta> salientes=this.rutasSalientes(actual);
			int m=0;
			while(!(salientes.get(m).getDestino().equals(listaNodos.get(i+1)) && salientes.get(m).getCapacidadMaxima()>0) &&  m<salientes.size())
				m++;
			result.add(salientes.get(m));
			
		}
		return result;
	}
	
	
}
