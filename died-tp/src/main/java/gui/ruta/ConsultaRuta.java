package gui.ruta;

import datos.*;
import gui.*;
import gui.tabla.TablaDeDatos;

import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Point;
import java.sql.Time;


public class ConsultaRuta extends Pantalla {

	private static final String[] COL_NAMES = {"ID de ruta","Sucursal de origen","Sucursal de destino","Estado","Duracion","Capacidad maxima",""};
	private JTextField txtIDRuta;
	private JComboBox<Sucursal> cmbSucursalOrigen;
	private JComboBox<Sucursal> cmbSucursalDestino;
	private JComboBox<Operatividad> cmbOperatividad;
	private JTextField txtDuracionDesde;
	private JTextField txtDuracionHasta;
	private TablaDeDatos tabla;
	private JScrollPane panelContenedorTabla;
	private JButton btnBuscar;
	private JTextField txtCapacidadMaximaDesde;
	private JTextField txtCapacidadMaximaHasta;
	
	/**
	 * Create the panel.
	 */
	public ConsultaRuta(JFrame frame, JPanel pantallaAnterior) {
		super(frame,pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		setLayout(null);
		
		//TODO: Prueba
		Sucursal aux = new Sucursal(13245,"Moron",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.OPERATIVA,TipoSucursal.COMERCIAL);
		Sucursal[] auxArr = new Sucursal[100];
		Arrays.fill(auxArr,aux);
		ArrayList<Sucursal> todasLasSucursales = new ArrayList<Sucursal>(Arrays.asList(auxArr));
		//TODO: Prueba
		
		//List<Sucursal> todasLasSucursales = GestorSucursal.getInstance().getAll();
		
		JLabel lblCompletaLosCampos = new JLabel("Completa los campos para filtrar la busqueda. A continuacion presiona el boton \"Buscar\".");
		lblCompletaLosCampos.setForeground(Color.GRAY);
		lblCompletaLosCampos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCompletaLosCampos.setBounds(10, 50, 425, 14);
		add(lblCompletaLosCampos);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		JLabel lblConsultaDeSucursales = new JLabel("Consulta de rutas");
		lblConsultaDeSucursales.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultaDeSucursales.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblConsultaDeSucursales.setBounds(10, 11, 780, 30);
		add(lblConsultaDeSucursales);
		
		txtIDRuta = new JTextField();
		txtIDRuta.setBounds(10, 113, 121, 20);
		add(txtIDRuta);
		txtIDRuta.setColumns(10);
		
		cmbSucursalOrigen = new JComboBox<Sucursal>();
		cmbSucursalOrigen.setBounds(141, 113, 121, 20);
		cmbSucursalOrigen.addItem(null);
		add(cmbSucursalOrigen);
		
		for(Sucursal suc : todasLasSucursales)
			cmbSucursalOrigen.addItem(suc);
		
		cmbSucursalDestino = new JComboBox<Sucursal>();
		cmbSucursalDestino.setBounds(272, 113, 121, 20);
		add(cmbSucursalDestino);
		cmbSucursalDestino.addItem(null);
		for(Sucursal suc : todasLasSucursales)
			cmbSucursalDestino.addItem(suc);
		
		cmbOperatividad = new JComboBox<Operatividad>();
		cmbOperatividad.setBounds(403, 113, 121, 20);
		add(cmbOperatividad);
		cmbOperatividad.addItem(null);
		cmbOperatividad.addItem(Operatividad.OPERATIVA);
		cmbOperatividad.addItem(Operatividad.NO_OPERATIVA);
		
		txtDuracionDesde = new JTextField();
		txtDuracionDesde.setColumns(10);
		txtDuracionDesde.setBounds(534, 113, 121, 20);
		add(txtDuracionDesde);
		
		txtDuracionHasta = new JTextField();
		txtDuracionHasta.setColumns(10);
		txtDuracionHasta.setBounds(665, 113, 121, 20);
		add(txtDuracionHasta);
		
		txtCapacidadMaximaDesde = new JTextField();
		txtCapacidadMaximaDesde.setColumns(10);
		txtCapacidadMaximaDesde.setBounds(10, 162, 121, 20);
		add(txtCapacidadMaximaDesde);
		
		txtCapacidadMaximaHasta = new JTextField();
		txtCapacidadMaximaHasta.setColumns(10);
		txtCapacidadMaximaHasta.setBounds(141, 162, 121, 20);
		add(txtCapacidadMaximaHasta);
		
		JLabel lblIDRuta = new JLabel("ID de ruta");
		lblIDRuta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIDRuta.setVerticalAlignment(SwingConstants.TOP);
		lblIDRuta.setBounds(10, 95, 121, 14);
		add(lblIDRuta);
		
		JLabel lblSucursalOrigen = new JLabel("Sucursal de origen");
		lblSucursalOrigen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSucursalOrigen.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalOrigen.setBounds(141, 95, 121, 14);
		add(lblSucursalOrigen);
		
		JLabel lblSucursalDestino = new JLabel("Sucursal de destino");
		lblSucursalDestino.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSucursalDestino.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalDestino.setBounds(272, 95, 121, 14);
		add(lblSucursalDestino);
		
		JLabel lblEstadoDeOperatividad = new JLabel("Estado");
		lblEstadoDeOperatividad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadoDeOperatividad.setVerticalAlignment(SwingConstants.TOP);
		lblEstadoDeOperatividad.setBounds(403, 95, 121, 14);
		add(lblEstadoDeOperatividad);
		
		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDuracion.setVerticalAlignment(SwingConstants.TOP);
		lblDuracion.setBounds(534, 95, 121, 14);
		add(lblDuracion);
		
		JLabel descDuracionDesde = new JLabel("desde");
		descDuracionDesde.setForeground(Color.GRAY);
		descDuracionDesde.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descDuracionDesde.setBounds(534, 133, 121, 14);
		add(descDuracionDesde);
		
		JLabel descDuracionHasta = new JLabel("hasta");
		descDuracionHasta.setForeground(Color.GRAY);
		descDuracionHasta.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descDuracionHasta.setBounds(665, 133, 121, 14);
		add(descDuracionHasta);
		
		JLabel lblCapacidadMaxima = new JLabel("Capacidad maxima");
		lblCapacidadMaxima.setVerticalAlignment(SwingConstants.TOP);
		lblCapacidadMaxima.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCapacidadMaxima.setBounds(10, 144, 121, 14);
		add(lblCapacidadMaxima);
		
		JLabel descCapacidadMaximaHasta = new JLabel("hasta");
		descCapacidadMaximaHasta.setForeground(Color.GRAY);
		descCapacidadMaximaHasta.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descCapacidadMaximaHasta.setBounds(141, 182, 121, 14);
		add(descCapacidadMaximaHasta);
		
		JLabel descCapacidadMaximaDesde = new JLabel("desde");
		descCapacidadMaximaDesde.setForeground(Color.GRAY);
		descCapacidadMaximaDesde.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descCapacidadMaximaDesde.setBounds(10, 182, 121, 14);
		add(descCapacidadMaximaDesde);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(act -> actionBuscar());
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setBounds(272, 162, 121, 20);
		add(btnBuscar);
		
		JButton btnCancelar = new JButton("Volver");
		btnCancelar.setBounds(690, 466, 100, 23);
		btnCancelar.addActionListener(act -> this.actionVolver());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);

	}

	private void generarTabla(List<Ruta> data) {
		
		tabla = new TablaDeDatos(datosTabla(data),COL_NAMES);
		tabla.onPressingButton(act -> actionOpcionesPopup(data));
		panelContenedorTabla = new JScrollPane(tabla);
		panelContenedorTabla.setBounds(10, 206, 780, 245);
		add(panelContenedorTabla);
	}
	
	private Object[][] datosTabla(List<Ruta> data){
		Object[][] contenido = new Object[data.size()][COL_NAMES.length];
		for(int i = 0 ; i < data.size() ; i++) {
			Object[] fila = {
				data.get(i).getID(),
				data.get(i).getOrigen(),
				data.get(i).getDestino(),
				data.get(i).getEstado(),
				data.get(i).getDuracion(),
				data.get(i).getCapacidadMaxima(),
				data.get(i).getID()
			};
			contenido[i] = fila;
		}
		return contenido;
	}
	
	public void actionBuscar() {
		
		//TODO: Prueba
		Sucursal auxSuc = new Sucursal(13245,"Moron",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.OPERATIVA,TipoSucursal.COMERCIAL);
		Ruta aux = new Ruta(12345,auxSuc,auxSuc,Operatividad.OPERATIVA,120,6000F);
		Ruta[] auxArr = new Ruta[100];
		Arrays.fill(auxArr,aux);
		ArrayList<Ruta> auxList = new ArrayList<Ruta>(Arrays.asList(auxArr));
		//TODO: Prueba
		
		generarTabla(auxList);
	}
	
	public void actionOpcionesPopup(List<Ruta> data) {
		int row = tabla.convertRowIndexToModel(tabla.getEditingRow());
        int column = tabla.convertColumnIndexToModel(tabla.getEditingColumn());
        Rectangle cellRect = tabla.getCellRect(row, column, true);
        Point popupLocation = new Point(cellRect.x + cellRect.width, cellRect.y);
        Ruta selected = data.stream().
        					filter(ruta -> ruta.getID().equals(tabla.getModel().getValueAt(row,0))).
        					findFirst().
        					orElse(null);
        OpcionesPopupRuta popupMenu = new OpcionesPopupRuta(selected,frame,this);
        popupMenu.show(tabla, popupLocation.x, popupLocation.y);
	}
	
	public void actionVolver(){
		this.setVisible(false);
		pantallaAnterior.setVisible(true);
		frame.setContentPane(pantallaAnterior);
	}

}