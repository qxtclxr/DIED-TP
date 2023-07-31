package gui;

import java.awt.BorderLayout;
import java.sql.Time;
import javax.swing.JFrame;
import javax.swing.JPanel;
import datos.*;
import gui.grafo.*;
import logica.grafo.Grafo;

import java.util.*;
import com.mxgraph.swing.mxGraphComponent;


public class GrafoPrueba extends Pantalla {

	public GrafoPrueba(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
	}

	@Override
	public void inicializarComponentes() {
		
		setLayout(new BorderLayout());
		
		ArrayList<Sucursal> suc = new ArrayList<Sucursal>();
		suc.add(new Sucursal(13245,"Moron",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.OPERATIVA,TipoSucursal.COMERCIAL));
		suc.add(new Sucursal(12345,"Santo Tome",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.NO_OPERATIVA,TipoSucursal.COMERCIAL));
		suc.add(new Sucursal(13425,"Parana",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.OPERATIVA,TipoSucursal.COMERCIAL));
		suc.add(new Sucursal(14235,"Santa Fe",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.NO_OPERATIVA,TipoSucursal.COMERCIAL));
		
		ArrayList<Ruta> ruta = new ArrayList<Ruta>();
		ruta.add(new Ruta(12345,suc.get(0),suc.get(1),Operatividad.OPERATIVA,120,6000F));
		ruta.add(new Ruta(12345,suc.get(1),suc.get(2),Operatividad.NO_OPERATIVA,120,6000F));
		ruta.add(new Ruta(12345,suc.get(2),suc.get(0),Operatividad.OPERATIVA,120,6000F));
		ruta.add(new Ruta(12345,suc.get(0),suc.get(3),Operatividad.NO_OPERATIVA,120,6000F));
		ruta.add(new Ruta(12345,suc.get(1),suc.get(3),Operatividad.OPERATIVA,120,6000F));
		ruta.add(new Ruta(12345,suc.get(2),suc.get(3),Operatividad.OPERATIVA,120,6000F));
		
		Grafo grafo = new Grafo();
		grafo.addVertice(suc);
		grafo.addArista(ruta);
		
		GrafoGUI grafoGUI = new GrafoGUI(grafo);		
		mxGraphComponent graphComponent = grafoGUI.getGraphComponent();
		
		add(graphComponent,BorderLayout.CENTER);
		
	}

}
