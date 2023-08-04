package gui.orden;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mxgraph.swing.mxGraphComponent;

import datos.Ruta;
import gui.Pantalla;
import gui.grafo.GrafoGUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import java.awt.Color;
import javax.swing.JButton;

public class CaminoGrafoPanel extends JPanel {
	
	private GrafoGUI grafo;
	private List<Ruta> camino;
	private Integer longitud;
	private JPanel contenedor;
	
	public CaminoGrafoPanel(JPanel contenedor, GrafoGUI grafo, List<Ruta> camino, Integer longitud) {
		super();
		this.contenedor = contenedor;
		this.grafo = grafo;
		this.camino = camino;
		this.longitud = longitud;
		this.inicializarComponentes();
	}

	public void inicializarComponentes() {
		setLayout(null);
		
		mxGraphComponent graphComponent = grafo.getGraphComponent(camino);
		graphComponent.setBounds(10, 11, 760, 211);
		add(graphComponent);
		
		JLabel lblLongitud = new JLabel("Longitud total:");
		lblLongitud.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLongitud.setBounds(10, 233, 96, 25);
		add(lblLongitud);
		
		JLabel longitudText = new JLabel(longitud + "mins");
		longitudText.setForeground(new Color(64, 128, 128));
		longitudText.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		longitudText.setBounds(112, 233, 70, 25);
		add(longitudText);
		
		JButton elegirCamino = new JButton("Elegir camino");
		elegirCamino.setBounds(681, 235, 89, 23);
		add(elegirCamino);
	}
	
}
