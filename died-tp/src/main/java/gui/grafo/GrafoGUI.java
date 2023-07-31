package gui.grafo;

import datos.*;
import logica.*;
import logica.grafo.Grafo;

import org.jgrapht.graph.*;

import java.util.*;
import java.util.stream.Collectors;

import org.jgrapht.ext.JGraphXAdapter;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;


public class GrafoGUI {
	
	private class LabeledRuta{
		private Ruta ruta;
		public LabeledRuta(Ruta ruta) {this.ruta = ruta;}
		public String toString() {return ruta.getDuracion() + "mins";}
	}
	private DirectedMultigraph<Sucursal,LabeledRuta> grafo;
	public static final String FILL_NO_OPERATIVA = "#878787";
	public static final String FONT_NO_OPERATIVA = "#ffffff";
	public static final int OPACITY_NO_OPERATIVA = 60;
	public static final int OPACITY_DISABLED = 20;
	
	public GrafoGUI() {
		this.grafo = new DirectedMultigraph<Sucursal,LabeledRuta>(LabeledRuta.class);
	}
	
	public GrafoGUI(Grafo grafo) {
		this();
		
		for(Sucursal vertice : grafo.getVertices()) {
			this.grafo.addVertex(vertice);
		}
		
		for(Ruta arista : grafo.getAristas()) {
			this.grafo.addEdge(arista.getOrigen(), arista.getDestino(),new LabeledRuta(arista));
		}
	}

	public mxGraphComponent getGraphComponent() {
		JGraphXAdapter<Sucursal,LabeledRuta> jgxAdapter = new JGraphXAdapter<>(grafo);
				
		jgxAdapter.setCellsEditable(false);
		jgxAdapter.setCellsMovable(false);
		jgxAdapter.setCellsResizable(false);
		jgxAdapter.setCellsSelectable(false);
		
		this.styleNoOperativa(jgxAdapter);
		
		mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);
		layout.execute(jgxAdapter.getDefaultParent());
			
		mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);
		graphComponent.setConnectable(false);
			
		return graphComponent;
	}
	
	public mxGraphComponent getGraphComponent(List<Ruta> highlightedPath) {
		JGraphXAdapter<Sucursal,LabeledRuta> jgxAdapter = new JGraphXAdapter<>(grafo);
		
		this.styleNoOperativa(jgxAdapter);
		this.styleHighlightedPath(jgxAdapter,highlightedPath);
				
		jgxAdapter.setCellsEditable(false);
		jgxAdapter.setCellsMovable(false);
		jgxAdapter.setCellsResizable(false);
		jgxAdapter.setCellsSelectable(false);
		
		mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);
		layout.execute(jgxAdapter.getDefaultParent());
			
		mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);
		graphComponent.setConnectable(false);
			
		return graphComponent;
	}
	
	private void styleNoOperativa(mxGraph graph) {
		String vertexStyle =
				"strokeColor=" + FILL_NO_OPERATIVA + ";" +
				"fillColor="+ FILL_NO_OPERATIVA + ";" +
				mxConstants.STYLE_FONTCOLOR + "=" + FONT_NO_OPERATIVA + ";" +
				"opacity=" + OPACITY_NO_OPERATIVA + ";" +
				mxConstants.STYLE_TEXT_OPACITY + "=" + OPACITY_NO_OPERATIVA + ";";
		String edgeStyle =
				"strokeColor=" + FILL_NO_OPERATIVA + ";" +
				mxConstants.STYLE_FONTCOLOR + "=" + FILL_NO_OPERATIVA + ";" +
				"opacity=" + OPACITY_NO_OPERATIVA + ";" +
				mxConstants.STYLE_TEXT_OPACITY + "=" + OPACITY_NO_OPERATIVA + ";";
		
		for (Object cell : graph.getChildVertices(graph.getDefaultParent())) {
			Sucursal vertex = (Sucursal) graph.getModel().getValue(cell);
			if (vertex.getEstado().equals(Operatividad.NO_OPERATIVA)) {
					graph.setCellStyle(vertexStyle, new Object[]{cell});
			}
		}
		
		for (Object cell : graph.getChildEdges(graph.getDefaultParent())) {
			LabeledRuta edge = (LabeledRuta) graph.getModel().getValue(cell);
			if (edge.ruta.getEstado().equals(Operatividad.NO_OPERATIVA)) {
					graph.setCellStyle(edgeStyle, new Object[]{cell});
			}
		}
	}
	
	private void styleHighlightedPath(mxGraph graph, List<Ruta> highlightedPath) {
		String vertexStyle =
				"opacity="+ OPACITY_DISABLED + ";" +
				mxConstants.STYLE_TEXT_OPACITY + "=" + OPACITY_DISABLED + ";";
		String edgeStyle =
				"opacity="+ OPACITY_DISABLED + ";" +
				mxConstants.STYLE_TEXT_OPACITY + "=" + OPACITY_DISABLED + ";";
		
		HashSet<Sucursal> sucursalInPath = new HashSet<>();
		sucursalInPath.add(highlightedPath.get(0).getOrigen());
		sucursalInPath.addAll(highlightedPath.stream().map(rut -> rut.getDestino()).collect(Collectors.toSet()));
		HashSet<LabeledRuta> rutaInPath = new HashSet<>();
		rutaInPath.addAll(highlightedPath.stream().map(rut -> new LabeledRuta(rut)).collect(Collectors.toSet()));
		
		for (Object cell : graph.getChildVertices(graph.getDefaultParent())) {
			Sucursal vertex = (Sucursal) graph.getModel().getValue(cell);
			if (!sucursalInPath.contains(vertex)) {
					graph.setCellStyle(vertexStyle, new Object[]{cell});
			}
		}
		
		for (Object cell : graph.getChildEdges(graph.getDefaultParent())) {
			LabeledRuta edge = (LabeledRuta) graph.getModel().getValue(cell);
			if (!rutaInPath.contains(edge)) {
					graph.setCellStyle(edgeStyle, new Object[]{cell});
			}
		}		
	}
	
}
