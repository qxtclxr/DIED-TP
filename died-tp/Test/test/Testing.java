package test;
import logica.*;
import logica.grafo.Grafo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import datos.*;


@ExtendWith(MockitoExtension.class)
public class Testing{

	@Test
	@DisplayName("Devuelve el flujo maximo en un camino operativo")
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
	     
	
	     Assertions.assertEquals(250f, g.flujoMaximo(centro, puerto, null));
	     
	}
	
	@Test
	@DisplayName("Devuelve el flujo maximo en un camino totalmente deshabilitado")
	public void flujoMaximoTest2() {
	     
		 Sucursal puerto = new Sucursal(1, "Puerto", null, null, Operatividad.OPERATIVA, TipoSucursal.FUENTE);
	     Sucursal centro = new Sucursal(5, "Centro", null, null, Operatividad.OPERATIVA, TipoSucursal.SUMIDERO);
		 Sucursal sucursal1 = new Sucursal(3, "Esperanza", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
	     Sucursal sucursal2 = new Sucursal(4, "Rafaela", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal3 = new Sucursal(2, "Parana", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal4 = new Sucursal(6, "Santa Fe", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 
	     Ruta ruta1 = new Ruta(1, puerto, sucursal2, Operatividad.OPERATIVA, 120, 500.0f);
	     Ruta ruta2 = new Ruta(2, sucursal1, sucursal3, Operatividad.NO_OPERATIVA, 120, 250.0f);
	     Ruta ruta3 = new Ruta(3, sucursal2, sucursal3, Operatividad.NO_OPERATIVA, 120, 300.0f);
	     Ruta ruta4 = new Ruta(4, sucursal2, sucursal4, Operatividad.NO_OPERATIVA, 120, 100.0f);
	     Ruta ruta5 = new Ruta(5, sucursal3, sucursal4, Operatividad.NO_OPERATIVA, 120, 300.0f);
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
	     
	  
	     Assertions.assertEquals(0f, g.flujoMaximo(centro, puerto, null));
	     
	}
	
	@Test
	@DisplayName("Devuelve el flujo maximo en un camino parcialmente deshabilitado")
	public void flujoMaximoTest3() {
	     
		 Sucursal puerto = new Sucursal(1, "Puerto", null, null, Operatividad.OPERATIVA, TipoSucursal.FUENTE);
	     Sucursal centro = new Sucursal(5, "Centro", null, null, Operatividad.OPERATIVA, TipoSucursal.SUMIDERO);
		 Sucursal sucursal1 = new Sucursal(3, "Esperanza", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
	     Sucursal sucursal2 = new Sucursal(4, "Rafaela", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal3 = new Sucursal(2, "Parana", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal4 = new Sucursal(6, "Santa Fe", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 
	     Ruta ruta1 = new Ruta(1, puerto, sucursal2, Operatividad.OPERATIVA, 120, 500.0f);
	     Ruta ruta2 = new Ruta(2, sucursal1, sucursal3, Operatividad.NO_OPERATIVA, 120, 250.0f);
	     Ruta ruta3 = new Ruta(3, sucursal2, sucursal3, Operatividad.NO_OPERATIVA, 120, 300.0f);
	     Ruta ruta4 = new Ruta(4, sucursal2, sucursal4, Operatividad.OPERATIVA, 120, 100.0f);
	     Ruta ruta5 = new Ruta(5, sucursal3, sucursal4, Operatividad.NO_OPERATIVA, 120, 300.0f);
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
	     
	  
	     Assertions.assertEquals(100f, g.flujoMaximo(centro, puerto, null));
	     
	}
	
	@Test
	@DisplayName("camino lineal entre 2 nodos")
	
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

	     
	     ArrayList<Ruta> r1 = new ArrayList<>(Arrays.asList(ruta1,ruta2,ruta3));
	     Map<List<Ruta>,Integer> esperado = new HashMap<>();
	     esperado.put(r1, 30);
	     
	     
	     Assertions.assertEquals(esperado , g.caminosEntreDosNodos(sucursal1, sucursal4) );
	     
		
		
	}
	
	@Test
	@DisplayName("varios caminos entre 2 nodos")
	
	public void caminosEntreNodosTest2() {
		

		 Sucursal sucursal1 = new Sucursal(1, "Esperanza", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
	     Sucursal sucursal2 = new Sucursal(2, "Rafaela", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal3 = new Sucursal(3, "Parana", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 Sucursal sucursal4 = new Sucursal(4, "Santa Fe", null, null, Operatividad.OPERATIVA, TipoSucursal.COMERCIAL);
		 
	     Ruta ruta1 = new Ruta(1, sucursal1, sucursal2, Operatividad.OPERATIVA, 10, 500.0f);
	     Ruta ruta2 = new Ruta(2, sucursal2, sucursal3, Operatividad.OPERATIVA, 10, 250.0f);
	     Ruta ruta3 = new Ruta(3, sucursal3, sucursal4, Operatividad.OPERATIVA, 10, 300.0f);
	     Ruta ruta4 = new Ruta(4, sucursal1, sucursal3, Operatividad.OPERATIVA, 15, 300.0f);
	     Ruta ruta5 = new Ruta(5, sucursal1, sucursal4, Operatividad.OPERATIVA, 17, 300.0f);
	     Ruta ruta6 = new Ruta(6, sucursal4, sucursal3, Operatividad.OPERATIVA, 10, 300.0f);
		
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

	     
	     ArrayList<Ruta> r1 = new ArrayList<>(Arrays.asList(ruta1,ruta2,ruta3));
	     ArrayList<Ruta> r2 = new ArrayList<>(Arrays.asList(ruta1,ruta3));
	     ArrayList<Ruta> r3 = new ArrayList<>(Arrays.asList(ruta1,ruta4,ruta3));
	     
	     Map<List<Ruta>,Integer> esperado = new HashMap<>();
	     esperado.put(r1, 20);
	     esperado.put(r2, 15);
	     esperado.put(r3, 40);
	     
	     
	     Assertions.assertEquals(esperado , g.caminosEntreDosNodos(sucursal1, sucursal4) );
	     
		
		
	}
	
	
	
}