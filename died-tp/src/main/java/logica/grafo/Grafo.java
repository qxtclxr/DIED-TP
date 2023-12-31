package logica.grafo;

import java.util.*;
import java.util.stream.*;
import datos.*;
import org.apache.commons.math3.linear.*;

public class Grafo {
	private List<Sucursal> vertices;
	private List<Ruta> aristas;
	private static final int ITERACIONES_PAGERANK = 2500;
	
	public Grafo() {
		this.vertices = new ArrayList<Sucursal>();
		this.aristas = new ArrayList<Ruta>();
	}
	
	public Grafo(List<Sucursal> vertices, List<Ruta> aristas) {
		super();
		this.vertices = vertices;
		this.aristas = aristas;
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
	
	public Float flujoMaximo(Sucursal inicio, Sucursal fin) {
		
		ArrayList<Ruta> copiaAristas = new ArrayList<>();
		Collections.copy(aristas,copiaAristas);
		
		Float flujoMax = 0F;
		
		HashSet<Sucursal> visitados = new HashSet<>();
		visitados.add(inicio);
		Float flujoEnCamino = flujoMaximo(inicio,fin,new ArrayList<Ruta>(),visitados,Float.MAX_VALUE);
		
		while(flujoEnCamino != null){
			flujoMax += flujoEnCamino;
			visitados = new HashSet<>();
			visitados.add(inicio);
			flujoEnCamino = flujoMaximo(inicio,fin,new ArrayList<Ruta>(),visitados,Float.MAX_VALUE);
		}
		
		return flujoMax;
	}
	
	public Float flujoMaximo(Sucursal actual, Sucursal fin, List<Ruta> caminoActual, HashSet<Sucursal> visitados, float flujoEnCamino) {
		
		if(actual.equals(fin)) {
			for (Ruta ruta : caminoActual) {
	            ruta.setCapacidadMaxima(ruta.getCapacidadMaxima() - flujoEnCamino);
	        }
			return flujoEnCamino;
		}
			
		for(Ruta rut : this.rutasSalientes(actual)) {
			Sucursal nodoNuevo = rut.getDestino();
			
			if(!(rut.getCapacidadMaxima() == 0 ||
				 rut.getEstado().equals(Operatividad.NO_OPERATIVA) ||
				 nodoNuevo.getEstado().equals(Operatividad.NO_OPERATIVA) ||
				 visitados.contains(nodoNuevo))) {
				
				//Copia de la lista caminoActual
				List<Ruta> caminoNuevo = caminoActual.stream().collect(Collectors.toList());
				caminoNuevo.add(rut);
				//Copia del set visitados
				HashSet<Sucursal> visitadosNuevo = (HashSet<Sucursal>) visitados.stream().collect(Collectors.toSet());
				visitadosNuevo.add(nodoNuevo);
				Float result = flujoMaximo(nodoNuevo,fin,caminoNuevo,visitadosNuevo,Float.min(flujoEnCamino,rut.getCapacidadMaxima()));
				if(result != null) return result;
			}
		}
		
		return null;
	}
	
	/*
	public float flujoMaximo(Sucursal origen, Sucursal destino) {
		float flujoMaximo= 0;
		List<Sucursal> copiaNodos =this.vertices.stream().collect(Collectors.toList());
		List<Ruta> copiaArista =this.aristas.stream().collect(Collectors.toList());
		flujoMaximo=flujoMaximoAux(origen,destino);
		
		this.aristas=copiaArista;
		this.vertices=copiaNodos;
		
		return flujoMaximo;
	}
	private Float flujoMaximoAux(Sucursal origen, Sucursal destino) {
		float flujoMax=0;
		while(true) {
			List<Sucursal> listaNodos=this.augmentingBFS(origen,destino);
			if(listaNodos==null)
				break;
			List<Ruta> camino=this.reconstruirCaminoNoNulo(listaNodos);
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
	*/
	
	private List<Sucursal> getNeighbourhood(Sucursal unNodo){
		List<Sucursal> salida = new ArrayList<Sucursal>();
		for(Ruta enlace : this.aristas){
			if( enlace.getOrigen().equals(unNodo)){
				salida.add(enlace.getDestino());
			}
		}
		return salida;
	}
	
	public Map<Sucursal,Double> pageRank(){
		HashMap<Sucursal,Double> pageRank = new HashMap<>();
		int cantVert = vertices.size();
		
		if(cantVert==0)
			return pageRank;
		
		double alpha = 0.15;
		double[][] dataP = new double[cantVert][cantVert];
		double[][] dataJ = new double[cantVert][cantVert];
		//J es la matriz de todos 1s
		for (double[] row: dataJ)
		    Arrays.fill(row, 1.0);
		
		//Se genera la matriz de probabilidades inicial, hay igual probabilidad de, en un nodo, salir a cualquier ruta.
		for(int i = 0 ; i < cantVert ; i++) {
			Sucursal suc = this.vertices.get(i);
			List<Sucursal> vecinas = this.getNeighbourhood(suc);
			double prob = 1.0/(vecinas.size()+1); //Puede tomar alguna de las rutas, o quedarse en su lugar. (Se agrega un bucle)
			dataP[i][i] = prob;
			for(Sucursal vecina : vecinas) {
				dataP[i][vertices.indexOf(vecina)] += prob;
			}
		}
		
		RealMatrix P = new Array2DRowRealMatrix(dataP);
		RealMatrix J = new Array2DRowRealMatrix(dataJ);
		
		P = P.scalarMultiply(1-alpha);
		J = J.scalarMultiply(alpha/cantVert);
		RealMatrix Phat = P.add(J);
		
		double[] stateData = new double[cantVert];
		Arrays.fill(stateData,1.0/cantVert);
		RealVector state = new ArrayRealVector(stateData, false);
		
		for(int i = 0 ; i < ITERACIONES_PAGERANK ; i++) {
			state = Phat.preMultiply(state);
		}
		
		for(int i = 0 ; i < cantVert ; i++) {
			pageRank.put(vertices.get(i),state.getEntry(i));
		}
		
		return pageRank;
	}
	
	/*
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
	*/
	
}
