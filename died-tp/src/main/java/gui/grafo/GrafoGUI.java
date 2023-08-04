package gui.grafo;

import datos.*;
import logica.grafo.Grafo;
import org.jgrapht.graph.*;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.SwingConstants;
import org.jgrapht.ext.JGraphXAdapter;
import com.mxgraph.layout.hierarchical.*;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;


public class GrafoGUI {
	
	private Grafo grafo;
	private DirectedMultigraph<Sucursal,LabeledRuta> jgraphtGrafo;
	public static final String FILL_NO_OPERATIVA = "#878787";
	public static final String FONT_NO_OPERATIVA = "#ffffff";
	public static final int OPACITY_NO_OPERATIVA = 60;
	public static final int OPACITY_DISABLED = 20;
	
	public GrafoGUI() {
		this.jgraphtGrafo = new DirectedMultigraph<Sucursal,LabeledRuta>(LabeledRuta.class);
	}
	
	public GrafoGUI(Grafo grafo) {
		this();
		this.grafo = grafo;
		for(Sucursal vertice : grafo.getVertices()) {
			this.jgraphtGrafo.addVertex(vertice);
		}
		
		for(Ruta arista : grafo.getAristas()) {
			this.jgraphtGrafo.addEdge(arista.getOrigen(), arista.getDestino(),new LabeledRuta(arista));
		}
	}

	public mxGraphComponent getGraphComponent() {
		JGraphXAdapter<Sucursal,LabeledRuta> jgxAdapter = new JGraphXAdapter<>(jgraphtGrafo);
				
		jgxAdapter.setCellsEditable(false);
		jgxAdapter.setCellsMovable(false);
		jgxAdapter.setCellsResizable(false);
		jgxAdapter.setCellsSelectable(false);	
		
		this.styleNoOperativa(jgxAdapter);
		
	    mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter, SwingConstants.WEST);
	    layout.execute(jgxAdapter.getDefaultParent());
		
		mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);
		graphComponent.setConnectable(false);
			
		return graphComponent;
	}
	
	public mxGraphComponent getGraphComponent(List<Ruta> highlightedPath) {
		JGraphXAdapter<Sucursal,LabeledRuta> jgxAdapter = new JGraphXAdapter<>(jgraphtGrafo);
		
		this.styleNoOperativa(jgxAdapter);
		this.styleHighlightedPath(jgxAdapter,highlightedPath);
				
		jgxAdapter.setCellsEditable(false);
		jgxAdapter.setCellsMovable(false);
		jgxAdapter.setCellsResizable(false);
		jgxAdapter.setCellsSelectable(false);
		
		mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter,SwingConstants.WEST);
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
			if (edge.getRuta().getEstado().equals(Operatividad.NO_OPERATIVA)) {
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
