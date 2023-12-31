package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import gui.orden.*;
import gui.producto.*;
import gui.sucursal.*;
import gui.ruta.*;

public class MenuPrincipal extends JPanel {
	
	private JFrame frame;
	private JTabbedPane opcionesEntidades;
	
	public MenuPrincipal(JFrame frame) {
		this.frame = frame;
		inicializarComponentes();
	}
	
	public void inicializarComponentes() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLayout(null);
		
		JLabel tituloSiglas = new JLabel("SGL");
		tituloSiglas.setHorizontalAlignment(SwingConstants.LEFT);
		tituloSiglas.setFont(new Font("Tahoma", Font.BOLD, 32));
		tituloSiglas.setBounds(10, 11, 69, 30);
		add(tituloSiglas);
		
		JLabel tituloNombre = new JLabel("(Sistema de Gestion Logistico)");
		tituloNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tituloNombre.setBounds(84, 21, 214, 20);
		add(tituloNombre);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 52, 780, 2);
		add(separadorTituloContenido);
		
		opcionesEntidades = new JTabbedPane(JTabbedPane.TOP);
		opcionesEntidades.setFont(new Font("Tahoma", Font.BOLD, 14));
		opcionesEntidades.setBounds(10, 75, 780, 380);
		add(opcionesEntidades);
		
		this.menuSucursales();
		
		this.menuRutas();
		
		this.menuProductos();
		
		this.menuOrdenes();
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(230, 0, 0));
		btnSalir.addActionListener(act -> actionSalir());
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalir.setBounds(701, 466, 89, 23);
		add(btnSalir);
	}
	
	public void menuSucursales() {
		JPanel menuSucursal = new JPanel();
		opcionesEntidades.addTab("Sucursales", null, menuSucursal, null);
		menuSucursal.setLayout(null);
		
		JButton btnAltaDeSucursales = new JButton("Alta de sucursales");
		btnAltaDeSucursales.addActionListener(act -> this.actionAltaSucursal());
		btnAltaDeSucursales.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAltaDeSucursales.setHorizontalAlignment(SwingConstants.LEFT);
		btnAltaDeSucursales.setBounds(10, 21, 755, 23);
		menuSucursal.add(btnAltaDeSucursales);
		
		JLabel lblAltaDeSucursales = new JLabel("Carga una nueva sucursal al sistema.");
		lblAltaDeSucursales.setForeground(new Color(128, 128, 128));
		lblAltaDeSucursales.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAltaDeSucursales.setBounds(10, 45, 755, 14);
		menuSucursal.add(lblAltaDeSucursales);
		
		JButton btnConsultaDeSucursales = new JButton("Consulta de sucursales");
		btnConsultaDeSucursales.addActionListener(act -> actionConsultaSucursal());
		btnConsultaDeSucursales.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultaDeSucursales.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConsultaDeSucursales.setBounds(10, 79, 755, 23);
		menuSucursal.add(btnConsultaDeSucursales);
		
		JLabel lblConsultaDeSucursales = new JLabel("Realiza una busqueda entre todas las sucursales, pudiendo luego editar, eliminar y/o consultar el stock de productos en cualquiera de ellas.");
		lblConsultaDeSucursales.setForeground(new Color(128, 128, 128));
		lblConsultaDeSucursales.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConsultaDeSucursales.setBounds(10, 103, 755, 14);
		menuSucursal.add(lblConsultaDeSucursales);
		
		JButton btnFlujoMaximo = new JButton("Flujo maximo");
		btnFlujoMaximo.addActionListener(act -> actionFlujoMaximo());
		btnFlujoMaximo.setHorizontalAlignment(SwingConstants.LEFT);
		btnFlujoMaximo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnFlujoMaximo.setBounds(10, 137, 755, 23);
		menuSucursal.add(btnFlujoMaximo);
		
		JLabel lblFlujoMaximo = new JLabel("Consulta el maximo envio (en kilos) que puede realizarse desde una sucursal fuente y una sucursal sumidero.");
		lblFlujoMaximo.setForeground(new Color(128, 128, 128));
		lblFlujoMaximo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFlujoMaximo.setBounds(10, 162, 755, 14);
		menuSucursal.add(lblFlujoMaximo);
		
		JButton btnRankingDeSucursales = new JButton("Ranking de sucursales");
		btnRankingDeSucursales.addActionListener(act -> actionPageRank());
		btnRankingDeSucursales.setHorizontalAlignment(SwingConstants.LEFT);
		btnRankingDeSucursales.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRankingDeSucursales.setBounds(10, 196, 755, 23);
		menuSucursal.add(btnRankingDeSucursales);
		
		JLabel lblRankingDeSucursales = new JLabel("Consulta el ranking de sucursales (ordenado segun cuantas rutas llegan a cada una de ellas).");
		lblRankingDeSucursales.setForeground(new Color(128, 128, 128));
		lblRankingDeSucursales.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRankingDeSucursales.setBounds(10, 221, 755, 14);
		menuSucursal.add(lblRankingDeSucursales);
	}
	
	public void menuRutas() {
		JPanel menuRutas = new JPanel();
		opcionesEntidades.addTab("Rutas", null, menuRutas, null);
		menuRutas.setLayout(null);
		
		JButton btnAltaDeRutas = new JButton("Alta de rutas");
		btnAltaDeRutas.addActionListener(act -> this.actionAltaRuta());
		btnAltaDeRutas.setHorizontalAlignment(SwingConstants.LEFT);
		btnAltaDeRutas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAltaDeRutas.setBounds(10, 21, 755, 23);
		menuRutas.add(btnAltaDeRutas);
		
		JLabel lblAltaDeRutas = new JLabel("Carga una nueva ruta al sistema. Las rutas conectan dos sucursales.");
		lblAltaDeRutas.setForeground(Color.GRAY);
		lblAltaDeRutas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAltaDeRutas.setBounds(10, 45, 755, 14);
		menuRutas.add(lblAltaDeRutas);
		
		JButton btnConsultaDeRutas = new JButton("Consulta de rutas");
		btnConsultaDeRutas.addActionListener(act -> actionConsultaRuta());
		btnConsultaDeRutas.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultaDeRutas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConsultaDeRutas.setBounds(10, 79, 755, 23);
		menuRutas.add(btnConsultaDeRutas);
		
		JLabel lblConsultaDeRutas = new JLabel("Realiza una busqueda entre todas las rutas, pudiendo luego editar y/o eliminar cualquiera de ellas.");
		lblConsultaDeRutas.setForeground(Color.GRAY);
		lblConsultaDeRutas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConsultaDeRutas.setBounds(10, 103, 755, 14);
		menuRutas.add(lblConsultaDeRutas);
	}
	
	public void menuProductos() {
		JPanel menuProductos = new JPanel();
		opcionesEntidades.addTab("Productos", null, menuProductos, null);
		menuProductos.setLayout(null);
		
		JButton btnAltaDeProductos = new JButton("Alta de productos");
		btnAltaDeProductos.addActionListener(act -> actionAltaProducto());
		btnAltaDeProductos.setHorizontalAlignment(SwingConstants.LEFT);
		btnAltaDeProductos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAltaDeProductos.setBounds(10, 21, 755, 23);
		menuProductos.add(btnAltaDeProductos);
		
		JLabel lblAltaDeProductos = new JLabel("Carga una nueva producto al sistema.");
		lblAltaDeProductos.setForeground(Color.GRAY);
		lblAltaDeProductos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAltaDeProductos.setBounds(10, 45, 755, 14);
		menuProductos.add(lblAltaDeProductos);
		
		JButton btnConsultaDeProductos = new JButton("Consulta de productos");
		btnConsultaDeProductos.addActionListener(act -> actionConsultaProducto());
		btnConsultaDeProductos.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultaDeProductos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConsultaDeProductos.setBounds(10, 79, 755, 23);
		menuProductos.add(btnConsultaDeProductos);
		
		JLabel lblConsultaDeProductos = new JLabel("Realiza una busqueda entre todos los productos a la venta, pudiendo luego editar y/o eliminar cualquiera de ellos.");
		lblConsultaDeProductos.setForeground(Color.GRAY);
		lblConsultaDeProductos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConsultaDeProductos.setBounds(10, 103, 755, 14);
		menuProductos.add(lblConsultaDeProductos);
	}
	
	public void menuOrdenes() {
		JPanel menuOrdenesDeProvision = new JPanel();
		opcionesEntidades.addTab("Ordenes de Provision", null, menuOrdenesDeProvision, null);
		menuOrdenesDeProvision.setLayout(null);
		
		JButton btnGenerarUnaOrden = new JButton("Generar una orden de provision");
		btnGenerarUnaOrden.setHorizontalAlignment(SwingConstants.LEFT);
		btnGenerarUnaOrden.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGenerarUnaOrden.addActionListener(act -> actionGenerarOrden());
		btnGenerarUnaOrden.setBounds(10, 21, 755, 23);
		menuOrdenesDeProvision.add(btnGenerarUnaOrden);
		
		JLabel lblGenerarUnaOrden = new JLabel("Genera una orden de Provision para solicitar el abastecimiento de productos de una sucursal.");
		lblGenerarUnaOrden.setForeground(Color.GRAY);
		lblGenerarUnaOrden.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGenerarUnaOrden.setBounds(10, 45, 755, 14);
		menuOrdenesDeProvision.add(lblGenerarUnaOrden);
		
		JButton btnConfirmarUnaOrden = new JButton("Consulta de ordenes de provision");
		btnConfirmarUnaOrden.addActionListener(act -> actionConfirmarOrden());
		btnConfirmarUnaOrden.setHorizontalAlignment(SwingConstants.LEFT);
		btnConfirmarUnaOrden.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConfirmarUnaOrden.setBounds(10, 79, 755, 23);
		menuOrdenesDeProvision.add(btnConfirmarUnaOrden);
		
		JLabel lblConfirmaUnaOrden = new JLabel("Visualiza todas las ordenes de provision. Si la orden esta pendiente, puedes ponerla en proceso, o bien eliminala.");
		lblConfirmaUnaOrden.setForeground(Color.GRAY);
		lblConfirmaUnaOrden.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConfirmaUnaOrden.setBounds(10, 103, 755, 14);
		menuOrdenesDeProvision.add(lblConfirmaUnaOrden);
	}
	
	public void actionSalir() {
		int result = JOptionPane.showConfirmDialog(frame,"¿Seguro que quieres salir de la aplicacion?","Salir",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION) {
			frame.dispose();
		}
	}
	
	public void actionAltaSucursal() {
		AltaSucursal altaSucursal = new AltaSucursal(frame,this);
		this.setVisible(false);
		frame.setContentPane(altaSucursal);
	}
	
	public void actionConsultaSucursal() {
		ConsultaSucursal consultaSucursal = new ConsultaSucursal(frame,this);
		this.setVisible(false);
		frame.setContentPane(consultaSucursal);
	}
	
	public void actionFlujoMaximo() {
		FlujoMaximo flujoMaximo = new FlujoMaximo(frame,this);
		this.setVisible(false);
		frame.setContentPane(flujoMaximo);
	}
	
	public void actionPageRank() {
		PageRank pageRank = new PageRank(frame,this);
		this.setVisible(false);
		frame.setContentPane(pageRank);
	}
	
	public void actionAltaRuta() {
		AltaRuta altaRuta = new AltaRuta(frame,this);
		this.setVisible(false);
		frame.setContentPane(altaRuta);
	}
	
	public void actionConsultaRuta() {
		ConsultaRuta consultaRuta = new ConsultaRuta(frame,this);
		this.setVisible(false);
		frame.setContentPane(consultaRuta);
	}
	
	public void actionAltaProducto() {
		AltaProducto altaProducto = new AltaProducto(frame,this);
		this.setVisible(false);
		frame.setContentPane(altaProducto);
	}
	
	public void actionConsultaProducto() {
		ConsultaProducto consultaProducto = new ConsultaProducto(frame,this);
		this.setVisible(false);
		frame.setContentPane(consultaProducto);
	}
	
	public void actionGenerarOrden() {
		AltaOrden generarOrden = new AltaOrden(frame,this);
		this.setVisible(false);
		frame.setContentPane(generarOrden);
	}
	
	public void actionConfirmarOrden() {
		SeleccionarOrdenPendiente confrimarOrden = new SeleccionarOrdenPendiente(frame,this);
		this.setVisible(false);
		frame.setContentPane(confrimarOrden);
	}
	
}
