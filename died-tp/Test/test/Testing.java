package test;
import logica.grafo.Grafo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.*;
import datos.*;


public class Testing{

	@Test
	@DisplayName("Devuelve el flujo maximo en una situacion comun y tras un corte de la ruta")
	public void flujoMaximoTest1() {
	     
		 Sucursal puerto = new Sucursal(1, "Puerto", null, null, Operatividad.OPERATIVA, TipoSucursal.FUENTE);
	     Sucursal centro = new Sucursal(5, "Centro", null, null, Operatividad.OPERATIVA, TipoSucursal.SUMIDERO);
		 Sucursal sucursal1 = new Sucursal(3, "Esperanza", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
	     Sucursal sucursal2 = new Sucursal(4, "Rafaela", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal3 = new Sucursal(2, "Parana", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal4 = new Sucursal(6, "Santa Fe", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 
	     Ruta ruta1 = new Ruta(1, puerto, sucursal2, Operatividad.OPERATIVA, 120, 500.0f);
	     Ruta ruta2 = new Ruta(2, sucursal1, sucursal3, Operatividad.OPERATIVA, 120, 250.0f);
	     Ruta ruta3 = new Ruta(3, sucursal2, sucursal3, Operatividad.OPERATIVA, 120, 300.0f);
	     Ruta ruta4 = new Ruta(4, sucursal2, sucursal4, Operatividad.OPERATIVA, 120, 100.0f);
	     Ruta ruta5 = new Ruta(5, sucursal3, sucursal4, Operatividad.OPERATIVA, 120, 300.0f);
	     Ruta ruta6 = new Ruta(6, sucursal4, centro, Operatividad.OPERATIVA, 120, 250.0f);
		
	     Grafo g = new Grafo();
	     
	     g.addArista(ruta1);
	     g.addArista(ruta2);
	     g.addArista(ruta3);
	     g.addArista(ruta4);
	     g.addArista(ruta5);
	     g.addArista(ruta6);
	     
	     g.addVertice(sucursal1);
	     g.addVertice(sucursal2);
	     g.addVertice(sucursal3);
	     g.addVertice(sucursal4);	
	     g.addVertice(centro);
	     g.addVertice(puerto);
	     
	     //System.out.println(g.flujoMaximo(puerto,centro));
	     
	     Grafo g2 = new Grafo();
	     Sucursal puerto2 = new Sucursal(1, "Puerto", null, null, Operatividad.OPERATIVA, TipoSucursal.FUENTE);
	     Sucursal centro2 = new Sucursal(5, "Centro", null, null, Operatividad.OPERATIVA, TipoSucursal.SUMIDERO);
		 Sucursal sucursal12 = new Sucursal(3, "Esperanza", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
	     Sucursal sucursal22 = new Sucursal(4, "Rafaela", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal32 = new Sucursal(2, "Parana", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal42 = new Sucursal(6, "Santa Fe", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 
	     Ruta ruta12 = new Ruta(1, puerto2, sucursal22, Operatividad.OPERATIVA, 120, 500.0f);
	     Ruta ruta22 = new Ruta(2, sucursal12, sucursal32, Operatividad.OPERATIVA, 120, 250.0f);
	     Ruta ruta32 = new Ruta(3, sucursal22, sucursal32, Operatividad.OPERATIVA, 120, 300.0f);
	     Ruta ruta42 = new Ruta(4, sucursal22, sucursal42, Operatividad.OPERATIVA, 120, 100.0f);
	     Ruta ruta52 = new Ruta(5, sucursal32, sucursal42, Operatividad.OPERATIVA, 120, 300.0f);
	     Ruta ruta62 = new Ruta(6, sucursal42, centro2, Operatividad.OPERATIVA, 120, 250.0f);
	     g2.addArista(ruta12);
	     g2.addArista(ruta22);
	     g2.addArista(ruta32);
	     g2.addArista(ruta42);
	     g2.addArista(ruta52);
	     g2.addArista(ruta62);
	     
	     g2.addVertice(sucursal12);
	     g2.addVertice(sucursal22);
	     g2.addVertice(sucursal32);
	     g2.addVertice(sucursal42);	
	     g2.addVertice(centro2);
	     g2.addVertice(puerto2);
	   
	     Assertions.assertEquals(250f, g.flujoMaximo(puerto,centro));
	     
	     //Se deshabilitan rutas
	     
	     ruta22.setEstado(Operatividad.NO_OPERATIVA);
	     ruta52.setEstado(Operatividad.NO_OPERATIVA);
	     // System.out.println(g2.flujoMaximo(puerto2,centro2));
	    // System.out.println(g.flujoMaximo(puerto, centro));
	     Assertions.assertEquals(100f, g2.flujoMaximo(puerto,centro));
	}
	
	
	
	@Test
	@DisplayName("camino sin ciclos entre 2 nodos y luego un corte en el camino")
	
	public void caminosEntreNodosTest1() {
		

		 Sucursal sucursal1 = new Sucursal(1, "Esperanza", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
	     Sucursal sucursal2 = new Sucursal(2, "Rafaela", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal3 = new Sucursal(3, "Parana", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal4 = new Sucursal(4, "Santa Fe", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 
	     Ruta ruta1 = new Ruta(1, sucursal1, sucursal2, Operatividad.OPERATIVA, 10, 500.0f);
	     Ruta ruta2 = new Ruta(2, sucursal2, sucursal3, Operatividad.OPERATIVA, 10, 250.0f);
	     Ruta ruta3 = new Ruta(3, sucursal3, sucursal4, Operatividad.OPERATIVA, 10, 300.0f);

		
	     Grafo g = new Grafo();
	     
	     g.addArista(ruta1);
	     g.addArista(ruta2);
	     g.addArista(ruta3);
	     
	     g.addVertice(sucursal1);
	     g.addVertice(sucursal2);
	     g.addVertice(sucursal3);
	     g.addVertice(sucursal4);	

	     
	     List<Ruta> r1 = new ArrayList<Ruta>(Arrays.asList(ruta1,ruta2,ruta3));
	     Map<List<Ruta>,Integer> esperado1 = new HashMap<List<Ruta>,Integer>();
	     
	     esperado1.put(r1, 30);
	     /*
	     
	     for(List<Ruta> r:esperado1.keySet()) {
	    	 System.out.println("Camino:"+r+" duracion:"+esperado1.get(r));
	     }
	     for(List<Ruta> r:g.caminosEntreDosNodos(sucursal1, sucursal4).keySet()) {
	    	 System.out.println("Camino:"+r+" duracion:"+g.caminosEntreDosNodos(sucursal1, sucursal4).get(r));
	     }
	     
	     */
	     
	     Assertions.assertEquals(esperado1 , g.caminosEntreDosNodos(sucursal1, sucursal4) );
	     
	     ruta2.setEstado(Operatividad.NO_OPERATIVA);
	     
	     
	     Map<List<Ruta>,Integer> esperado2 = new HashMap<>();
	     
	     
	     Assertions.assertEquals(esperado2 , g.caminosEntreDosNodos(sucursal1, sucursal4) );
	     
		
		
	}
	
	@Test
	@DisplayName("camino con ciclos normal y luego con corte de caminos")
	
	public void caminosEntreNodosTest2() {
		
		 Sucursal sucursal1 = new Sucursal(1, "Esperanza", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
	     Sucursal sucursal2 = new Sucursal(2, "Rafaela", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal3 = new Sucursal(3, "Parana", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal4 = new Sucursal(4, "Santa Fe", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 
	     Ruta ruta1 = new Ruta(1, sucursal1, sucursal2, Operatividad.OPERATIVA, 10, 500.0f);
	     Ruta ruta2 = new Ruta(2, sucursal2, sucursal3, Operatividad.OPERATIVA, 20, 250.0f);
	     Ruta ruta3 = new Ruta(3, sucursal4, sucursal3, Operatividad.OPERATIVA, 30, 300.0f);
	     Ruta ruta4 = new Ruta(4, sucursal1, sucursal3, Operatividad.OPERATIVA, 50, 300.0f);
	     Ruta ruta5 = new Ruta(5, sucursal2, sucursal4, Operatividad.OPERATIVA, 20, 300.0f);
	     Ruta ruta6 = new Ruta(6, sucursal2, sucursal1, Operatividad.OPERATIVA, 10, 300.0f);
		
	     Grafo g = new Grafo();
	     
	     g.addArista(ruta1);
	     g.addArista(ruta2);
	     g.addArista(ruta3);
	     g.addArista(ruta4);
	     g.addArista(ruta5);
	     g.addArista(ruta6);
	   
	     
	     g.addVertice(sucursal1);
	     g.addVertice(sucursal2);
	     g.addVertice(sucursal3);
	     g.addVertice(sucursal4);	

	     
	     ArrayList<Ruta> r1 = new ArrayList<>(Arrays.asList(ruta1,ruta5,ruta3));
	     ArrayList<Ruta> r2 = new ArrayList<>(Arrays.asList(ruta1,ruta2));
	     ArrayList<Ruta> r3 = new ArrayList<>(Arrays.asList(ruta4));
	     
	     Map<List<Ruta>,Integer> esperado1 = new HashMap<>();
	     
	     esperado1.put(r1, 60);
	     esperado1.put(r2, 30);
	     esperado1.put(r3, 50);
	     
	     
	     Assertions.assertEquals(esperado1 , g.caminosEntreDosNodos(sucursal1, sucursal3) );
	     
	     //Se corta ruta 2
	     ruta2.setEstado(Operatividad.NO_OPERATIVA);
	     
	     ArrayList<Ruta> r4 = new ArrayList<>(Arrays.asList(ruta1,ruta5,ruta3));
	     ArrayList<Ruta> r5 = new ArrayList<>(Arrays.asList(ruta4));
	     
	     Map<List<Ruta>,Integer> esperado2 = new HashMap<>();
	     
	     esperado2.put(r4, 60);
	     esperado2.put(r5, 50);
	     
	     
	     Assertions.assertEquals(esperado2 , g.caminosEntreDosNodos(sucursal1, sucursal3) );
		
	}

	@Test
	public void testprueba() {
		Sucursal sucursal1 = new Sucursal(1, "Esperanza", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		Assertions.assertEquals("Esperanza", sucursal1.getNombre());
	}
	
	
}