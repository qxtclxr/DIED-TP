package gui.orden;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import datos.*;
import gui.*;
import gui.grafo.*;
import logica.*;
import logica.grafo.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class SeleccionarCaminoOrden extends Pantalla {
	
	private OrdenDeProvision orden;
	private List<Sucursal> sucursales;
	private JTabbedPane selectorCaminos;
	private JComboBox<Sucursal> cmbSucursales;
	
	public SeleccionarCaminoOrden(JFrame frame, JPanel pantallaAnterior, OrdenDeProvision orden, List<Sucursal> sucursales) {
		super();
		this.frame = frame;
		this.pantallaAnterior = pantallaAnterior;
		this.orden = orden;
		this.sucursales = sucursales;
		this.inicializarComponentes();
	}
	
	@Override
	public void inicializarComponentes() {
		setLayout(null);
		JLabel lblTitulo = new JLabel("Seleccion de camino");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitulo.setBounds(10, 11, 780, 30);
		add(lblTitulo);
		
		JLabel descTitulo = new JLabel("Selecciona una sucursal de origen y a continuación selecciona el camino por el que llegará la mercaderia.");
		descTitulo.setForeground(Color.GRAY);
		descTitulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descTitulo.setBounds(10, 50, 780, 14);
		add(descTitulo);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		cmbSucursales = new JComboBox<>();
		cmbSucursales.setBounds(10, 111, 250, 22);
		for(Sucursal suc : sucursales) {
			cmbSucursales.addItem(suc);
		}
		add(cmbSucursales);
		
		JLabel lblSucursalDeOrigen = new JLabel("Sucursal de origen");
		lblSucursalDeOrigen.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalDeOrigen.setBounds(10, 92, 250, 16);
		lblSucursalDeOrigen.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(lblSucursalDeOrigen);

		JButton btnBuscarCaminos = new JButton("Buscar caminos");
		btnBuscarCaminos.addActionListener(act -> actionBuscarCaminos());
		btnBuscarCaminos.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarCaminos.setBounds(273, 111, 126, 23);
		add(btnBuscarCaminos);
		
		JSeparator separadorBuscarCaminos = new JSeparator();
		separadorBuscarCaminos.setBounds(10, 150, 780, 2);
		add(separadorBuscarCaminos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 159, 162));
		btnCancelar.setBounds(690, 466, 100, 23);
		btnCancelar.addActionListener(act -> this.actionCancelar());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
		
		selectorCaminos = new JTabbedPane(JTabbedPane.TOP);
		selectorCaminos.setBounds(10, 163, 780, 293);
		add(selectorCaminos);
		
	}
	
	public void generarGrafoGUI(Sucursal sucursalElegida) {
		try {
			List<Sucursal> allSucursales = GestorSucursal.getInstance().getAll();
			List<Ruta> allRutas = GestorRuta.getInstance().getAll();
			Grafo grafo = new Grafo(allSucursales,allRutas);
			GrafoGUI grafoGUI = new GrafoGUI(grafo);
			Map<List<Ruta>,Integer> caminos = grafo.caminosEntreDosNodos(sucursalElegida, orden.getSucursalDestino());
			Set<Entry<List<Ruta>, Integer>> caminosEntrySet = caminos.entrySet();
			
			selectorCaminos.removeAll();
			int panelIndex = 1;
			for(Entry<List<Ruta>, Integer> camino : caminosEntrySet) {
				CaminoGrafoPanel nuevoPanel = new CaminoGrafoPanel(this,grafoGUI,camino.getKey(),camino.getValue());
				selectorCaminos.addTab("Opcion " + panelIndex++,null,nuevoPanel,null);
			}
			
		} catch (ClassNotFoundException | SQLException ex) {
			DatabaseErrorMessage.showMessageDialog(frame);
			ex.printStackTrace();
		}
		
	}
	
	public void actionCancelar() {
		int result = JOptionPane.showConfirmDialog(frame,"¿Seguro que quieres cancelar la operacion? Los datos no seran guardados","Cancelar",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION) {
			this.setVisible(false);
			pantallaAnterior.setVisible(true);
			frame.setContentPane(pantallaAnterior);
		}
	}
	
	public void actionBuscarCaminos() {
		generarGrafoGUI((Sucursal) cmbSucursales.getSelectedItem());
	}
	
	public void actionElegirCamino() {
		try {
			GestorOrden.getInstance().setEnProceso(orden);
			JOptionPane.showMessageDialog(
					frame,
					"La orden ha sido puesta en proceso.\n"
					+ "Si no recibe la mercaderia en las proximas (" + orden.getTiempoMaximo() + ") horas, contactese con la sucursal elegida como origen.",
					"Orden en proceso.",
					JOptionPane.INFORMATION_MESSAGE);
			MenuPrincipal menu = new MenuPrincipal(frame);
			this.setVisible(false);
			menu.setVisible(true);
			frame.setContentPane(menu);
		} catch (ClassNotFoundException | SQLException e) {
			DatabaseErrorMessage.showMessageDialog(frame);
			e.printStackTrace();
		}
		
	}
	
}
