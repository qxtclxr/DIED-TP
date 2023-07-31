package gui.grafo;

import datos.*;
import logica.*;
import logica.grafo.Grafo;

import org.jgrapht.graph.*;
import javax.swing.JPanel;
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
		
		mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);
		layout.execute(jgxAdapter.getDefaultParent());
		
		mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);
		graphComponent.setConnectable(false);
		
		return graphComponent;
	}
	
	
}
