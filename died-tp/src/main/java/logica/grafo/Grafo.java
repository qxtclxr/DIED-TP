package logica.grafo;

import java.util.*;
import java.util.stream.*;
import datos.*;

public class Grafo {
	private List<Sucursal> vertices;
	private List<Ruta> aristas;
	private static final Double ERROR_CUADRATICO=1*Math.pow(10, -6);
	
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
	
	public Map<List<Ruta>,Integer> caminosEntreDosNodos(Sucursal ini, Sucursal fin){
		Map<List<Ruta>,Integer> sol = new HashMap<>();
		HashSet<Sucursal> visitados = new HashSet<>();
		visitados.add(ini);
		caminosEntreDosNodos(ini,fin,new ArrayList<Ruta>(),visitados,sol);
		return sol;
	}
	
	private void caminosEntreDosNodos(Sucursal actual,Sucursal fin, List<Ruta> caminoActual,
									  HashSet<Sucursal> visitados, Map<List<Ruta>,Integer> sol) {
		if(actual.equals(fin)) {
			Integer duracionTotal = caminoActual.stream().
					mapToInt(rut -> rut.getDuracion()).
					sum();
			sol.put(caminoActual,duracionTotal);
			return;
		}
		for(Ruta rut : this.rutasSalientes(actual)) {
			Sucursal nodoNuevo = rut.getDestino();
			if(!(rut.getEstado().equals(Operatividad.NO_OPERATIVA) ||
				 nodoNuevo.getEstado().equals(Operatividad.NO_OPERATIVA) ||
				 visitados.contains(nodoNuevo))) {
				//Copia de la lista caminoActual
				List<Ruta> caminoNuevo = caminoActual.stream().collect(Collectors.toList());
				caminoNuevo.add(rut);
				//Copia del set visitados
				HashSet<Sucursal> visitadosNuevo = (HashSet<Sucursal>) visitados.stream().collect(Collectors.toSet());
				visitadosNuevo.add(nodoNuevo);
				caminosEntreDosNodos(nodoNuevo,fin,caminoNuevo,visitadosNuevo,sol);
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
	
	public Map<Sucursal,Double> pageRank(){
		Map<Sucursal,Double> actual=new HashMap<Sucursal,Double>();
		Double d=(double) 0.5;
		for(Sucursal s:this.vertices)
			actual.put(s, (double)1);
		
		Map<Sucursal,Double> siguiente=this.iteracionPageRank(actual,d);
		double e=0;
		for(Sucursal s:this.vertices) {
			e+=Math.pow(siguiente.get(s)-actual.get(s),2);
		}
		e=Math.sqrt(e);
		while(e>Grafo.ERROR_CUADRATICO) {
			actual=siguiente;
			siguiente=this.iteracionPageRank(actual,d);
			e=0;
			for(Sucursal s:this.vertices) {
				e+=Math.pow(siguiente.get(s)-actual.get(s),2);
			}
			e=Math.sqrt(e);
			
		}
		return actual;
	}
	
	private Map<Sucursal,Double> iteracionPageRank(Map<Sucursal,Double>res,Double d) {
		Map<Sucursal,Double> result=new HashMap<Sucursal,Double>();
		 for(Sucursal s:this.vertices)
			 result.put(s, Double.valueOf(res.get(s)));
		
		 for(Sucursal s:this.vertices) {
			 double m=d;
			 for(Sucursal ady:this.getEntrantes(s)) {
				 m+=(1-d)*(res.get(ady)/this.gradoSalida(ady));
			 }
			 result.put(s,m);
		 }
		 return result;
			 
	}
	private List<Sucursal> getEntrantes(Sucursal s){
		List<Sucursal> res=new ArrayList<>();
		for(Ruta rut : this.aristas) {
			if(rut.getDestino().equals(s))
				res.add(rut.getOrigen());
		}
		
		return res;
	}
	
	
}
