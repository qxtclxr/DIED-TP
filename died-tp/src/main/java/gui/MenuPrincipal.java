package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPrincipal extends JFrame {

	private JPanel panelPrincipal;

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inicializarComponentes();
		
		setTitle("SGL (Sistema de Gestion Logistico)");
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public void inicializarComponentes() {
		setBounds(100, 100, 800, 500);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel tituloSiglas = new JLabel("SGL");
		tituloSiglas.setVerticalAlignment(SwingConstants.TOP);
		tituloSiglas.setFont(new Font("Roboto Black", Font.PLAIN, 32));
		tituloSiglas.setBounds(10, 11, 59, 30);
		panelPrincipal.add(tituloSiglas);
		
		JLabel tituloNombre = new JLabel("(Sistema de Gestion Logistico)");
		tituloNombre.setVerticalAlignment(SwingConstants.BOTTOM);
		tituloNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		tituloNombre.setBounds(79, 11, 224, 31);
		panelPrincipal.add(tituloNombre);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 52, 764, 2);
		panelPrincipal.add(separadorTituloContenido);
		
		JTabbedPane opcionesEntidades = new JTabbedPane(JTabbedPane.TOP);
		opcionesEntidades.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		opcionesEntidades.setBounds(10, 75, 764, 284);
		panelPrincipal.add(opcionesEntidades);
		
		JPanel menuSucursal = new JPanel();
		opcionesEntidades.addTab("Sucursales", null, menuSucursal, null);
		menuSucursal.setLayout(null);
		
		JButton btnAltaDeSucursales = new JButton("Alta de sucursales");
		btnAltaDeSucursales.addActionListener(action -> altaDeSucursales());
		btnAltaDeSucursales.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnAltaDeSucursales.setHorizontalAlignment(SwingConstants.LEFT);
		btnAltaDeSucursales.setBounds(10, 21, 739, 23);
		menuSucursal.add(btnAltaDeSucursales);
		
		JLabel lblAltaDeSucursales = new JLabel("Carga una nueva sucursal al sistema.");
		lblAltaDeSucursales.setForeground(new Color(128, 128, 128));
		lblAltaDeSucursales.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblAltaDeSucursales.setBounds(10, 45, 715, 14);
		menuSucursal.add(lblAltaDeSucursales);
		
		JButton btnConsultaDeSucursales = new JButton("Consulta de sucursales");
		btnConsultaDeSucursales.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultaDeSucursales.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnConsultaDeSucursales.setBounds(10, 79, 739, 23);
		menuSucursal.add(btnConsultaDeSucursales);
		
		JLabel lblRealizaUnaBusqueda = new JLabel("Realiza una busqueda entre todas las sucursales, pudiendo luego editar, eliminar y/o consultar el stock de productos en cualquiera de ellas.");
		lblRealizaUnaBusqueda.setForeground(new Color(128, 128, 128));
		lblRealizaUnaBusqueda.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblRealizaUnaBusqueda.setBounds(10, 103, 715, 14);
		menuSucursal.add(lblRealizaUnaBusqueda);
		
		JButton btnFlujoMaximo = new JButton("Flujo maximo");
		btnFlujoMaximo.setHorizontalAlignment(SwingConstants.LEFT);
		btnFlujoMaximo.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnFlujoMaximo.setBounds(10, 137, 739, 23);
		menuSucursal.add(btnFlujoMaximo);
		
		JLabel lblConsultaElMaximo = new JLabel("Consulta el maximo envio (en kilos) que puede realizarse desde una sucursal fuente y una sucursal sumidero.");
		lblConsultaElMaximo.setForeground(new Color(128, 128, 128));
		lblConsultaElMaximo.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblConsultaElMaximo.setBounds(10, 162, 715, 14);
		menuSucursal.add(lblConsultaElMaximo);
		
		JButton btnRankingDeSucursales = new JButton("Ranking de sucursales");
		btnRankingDeSucursales.setHorizontalAlignment(SwingConstants.LEFT);
		btnRankingDeSucursales.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnRankingDeSucursales.setBounds(10, 196, 739, 23);
		menuSucursal.add(btnRankingDeSucursales);
		
		JLabel lblConsultaElRanking = new JLabel("Consulta el ranking de sucursales (ordenado segun cuantas rutas llegan a cada una de ellas).");
		lblConsultaElRanking.setForeground(new Color(128, 128, 128));
		lblConsultaElRanking.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblConsultaElRanking.setBounds(10, 221, 715, 14);
		menuSucursal.add(lblConsultaElRanking);
		
		JPanel menuRutas = new JPanel();
		opcionesEntidades.addTab("Rutas", null, menuRutas, null);
		
		JPanel menuProductos = new JPanel();
		opcionesEntidades.addTab("Productos", null, menuProductos, null);
		
		JPanel menuOrdenesDeProvision = new JPanel();
		opcionesEntidades.addTab("Ordenes de provision", null, menuOrdenesDeProvision, null);
	}
	
	private void altaDeSucursales() {
		this.setVisible(false);
		this.setVisible(true);
	}
}
