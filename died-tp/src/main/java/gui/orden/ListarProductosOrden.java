package gui.orden;

import datos.*;
import gui.*;
import gui.tabla.TablaCarrito;
import gui.tabla.TablaDeDatos;
import logica.GestorOrden;
import logica.GestorProducto;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ListarProductosOrden extends Pantalla {
	
	private OrdenDeProvision orden;
	private static final String[] COL_NAMES = {"ID del producto","Nombre","Descripcion","Precio unitario","Peso (en kg.)",""};
	private static final String[] COL_NAMES_SELECT = {"ID del producto","Nombre","Descripcion","Precio unitario","Peso (en kg.)","Cantidad",""};
	private JTextField txtIDProducto;
	private JTextField txtNombre;
	private JTextField txtPrecioDesde;
	private JTextField txtPrecioHasta;
	private JTextField txtPesoDesde;
	private JTextField txtPesoHasta;
	private TablaDeDatos tablaDatos;
	private TablaCarrito tablaSeleccionados;
	private JScrollPane panelContenedorTabla;
	
	/**
	 * Create the panel.
	 */
	public ListarProductosOrden(JFrame frame, JPanel pantallaAnterior, OrdenDeProvision orden) {
		super(frame,pantallaAnterior);
		this.orden = orden;
	}
	
	public void inicializarComponentes() {
		setLayout(null);
		
		JLabel lblCompletaLosCampos = new JLabel("Completa los campos para filtrar la busqueda. A continuacion presiona el boton \"Buscar\".");
		lblCompletaLosCampos.setForeground(Color.GRAY);
		lblCompletaLosCampos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCompletaLosCampos.setBounds(10, 50, 425, 14);
		add(lblCompletaLosCampos);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		JLabel lblConsultaDeProductos = new JLabel("Consulta de productos");
		lblConsultaDeProductos.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultaDeProductos.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblConsultaDeProductos.setBounds(10, 11, 780, 30);
		add(lblConsultaDeProductos);
		
		txtIDProducto = new JTextField();
		txtIDProducto.setBounds(10, 113, 121, 20);
		add(txtIDProducto);
		txtIDProducto.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(141, 113, 121, 20);
		add(txtNombre);
		
		txtPrecioDesde = new JTextField();
		txtPrecioDesde.setBounds(272, 113, 121, 20);
		add(txtPrecioDesde);
		
		txtPrecioHasta = new JTextField();
		txtPrecioHasta.setBounds(403, 113, 121, 20);
		add(txtPrecioHasta);
		
		JLabel lblIDProducto = new JLabel("ID del producto");
		lblIDProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIDProducto.setVerticalAlignment(SwingConstants.TOP);
		lblIDProducto.setBounds(10, 95, 121, 14);
		add(lblIDProducto);
		
		JLabel lblSucursalOrigen = new JLabel("Nombre del producto");
		lblSucursalOrigen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSucursalOrigen.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalOrigen.setBounds(141, 95, 121, 14);
		add(lblSucursalOrigen);
		
		JLabel lblSucursalDestino = new JLabel("Precio unitario");
		lblSucursalDestino.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSucursalDestino.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalDestino.setBounds(272, 95, 121, 14);
		add(lblSucursalDestino);
		
		JLabel lblDuracion = new JLabel("Peso (en kg.)");
		lblDuracion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDuracion.setVerticalAlignment(SwingConstants.TOP);
		lblDuracion.setBounds(534, 95, 121, 14);
		add(lblDuracion);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(act -> actionBuscar());
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setBounds(10, 144, 121, 20);
		add(btnBuscar);
		
		JButton btnCancelar = new JButton("Volver");
		btnCancelar.setBounds(580, 467, 100, 23);
		btnCancelar.addActionListener(act -> this.actionVolver());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
		
		txtPesoDesde = new JTextField();
		txtPesoDesde.setColumns(10);
		txtPesoDesde.setBounds(534, 113, 121, 20);
		add(txtPesoDesde);
		
		txtPesoHasta = new JTextField();
		txtPesoHasta.setColumns(10);
		txtPesoHasta.setBounds(665, 113, 121, 20);
		add(txtPesoHasta);
		
		JLabel descPrecioDesde = new JLabel("desde");
		descPrecioDesde.setForeground(Color.GRAY);
		descPrecioDesde.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descPrecioDesde.setBounds(272, 133, 121, 14);
		add(descPrecioDesde);
		
		JLabel descPesoDesde = new JLabel("desde");
		descPesoDesde.setForeground(Color.GRAY);
		descPesoDesde.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descPesoDesde.setBounds(534, 133, 121, 14);
		add(descPesoDesde);
		
		JLabel descPrecioHasta = new JLabel("hasta");
		descPrecioHasta.setForeground(Color.GRAY);
		descPrecioHasta.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descPrecioHasta.setBounds(403, 133, 121, 14);
		add(descPrecioHasta);
		
		JLabel descPesoHasta = new JLabel("hasta");
		descPesoHasta.setForeground(Color.GRAY);
		descPesoHasta.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descPesoHasta.setBounds(665, 133, 121, 14);
		add(descPesoHasta);
		
		JLabel lblProductosSeleccionados = new JLabel("Productos seleccionados");
		lblProductosSeleccionados.setHorizontalAlignment(SwingConstants.LEFT);
		lblProductosSeleccionados.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProductosSeleccionados.setBounds(10, 326, 780, 20);
		add(lblProductosSeleccionados);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(act -> actionConfirmar());
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConfirmar.setBounds(690, 467, 100, 23);
		add(btnConfirmar);
		
		generarTablaDatos();
		generarTablaSeleccionados();
		fieldsDefaultColor();
	}
	
	protected void fieldsDefaultColor() {
		txtIDProducto.setBackground(Color.WHITE);
		txtNombre.setBackground(Color.WHITE);
		txtPrecioDesde.setBackground(Color.WHITE);
		txtPrecioHasta.setBackground(Color.WHITE);
		txtPesoDesde.setBackground(Color.WHITE);
		txtPesoHasta.setBackground(Color.WHITE);
	}
	
	protected boolean validateInput() {
		fieldsDefaultColor();
		Color colorInvalid = Color.decode("#ff8080");
		boolean validInput = true;
		if(!SyntaxValidator.validID()) {
			txtIDProducto.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validTextLength(txtNombre,0,256)) {
			txtNombre.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validFloatingPointOrEmpty(txtPrecioDesde)) {
			txtPrecioDesde.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validFloatingPointOrEmpty(txtPrecioHasta)) {
			txtPrecioHasta.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validFloatingPointOrEmpty(txtPesoDesde)) {
			txtPesoDesde.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validFloatingPointOrEmpty(txtPesoHasta)) {
			txtPesoHasta.setBackground(colorInvalid);
			validInput = false;
		}
		return validInput;
	}
	
	public void actionBuscar() {
		List<Producto> dataList = new ArrayList<>();
		if(this.validateInput()) {
			try {				
				dataList = GestorProducto.getInstance().consultaPorAtributos(
						txtIDProducto.getText(),
						txtNombre.getText(),
						txtPrecioDesde.getText(),
						txtPrecioHasta.getText(),
						txtPesoDesde.getText(),
						txtPesoHasta.getText());
				DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
				model.setRowCount(0); //Resetea la tabla
				for(Producto prod : dataList) {
					Object[] fila = {
							prod.getID(),
							prod.getNombre(),
							prod.getDescripcion(),
							prod.getPrecioUnitario(),
							prod.getPesoKg(),
							prod.getID()
					};
					model.addRow(fila);
				}
			}catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				DatabaseErrorMessage.showMessageDialog(frame);
			}
		} else {
			InvalidInputMessage.showMessageDialog(frame);
		}		
	}
	
	private void generarTablaDatos() {
		tablaDatos = new TablaDeDatos(COL_NAMES);
		tablaDatos.onPressingButton(act -> actionAgregar());
		tablaDatos.setButtonLabel("Agregar");
		panelContenedorTabla = new JScrollPane(tablaDatos);
		panelContenedorTabla.setBounds(10, 175, 780, 140);
		add(panelContenedorTabla);
	}
	
	public void actionAgregar() {
		int row = tablaDatos.convertRowIndexToModel(tablaDatos.getEditingRow());
		Integer selectedProdID = (Integer) tablaDatos.getModel().getValueAt(row,0);
		if(!tablaSeleccionados.getCarrito().contains(selectedProdID)) {
			int dataSize = tablaDatos.getColumnCount()-1;
	        Object[] data = new Object[dataSize+1]; //Un lugar mas para el valor default del JSpinner
	        for(int i = 0 ; i < dataSize ; i++) {
	        	data[i] = tablaDatos.getModel().getValueAt(row,i);
	        }
	        data[dataSize] = 1; //Este es el valor inicial del JSpinner con el que se setea la cantidad de producto.
	        DefaultTableModel model = (DefaultTableModel) tablaSeleccionados.getModel();
	        model.addRow(data);
	        tablaSeleccionados.getCarrito().add(selectedProdID);
		}
		
	}
	
	public void generarTablaSeleccionados() {
		tablaSeleccionados = new TablaCarrito(COL_NAMES_SELECT);
		JScrollPane panelProductosSeleccionados = new JScrollPane(tablaSeleccionados);
		panelProductosSeleccionados.setBounds(10, 350, 780, 105);
		add(panelProductosSeleccionados);
	}
	
	public void actionConfirmar() {
		HashMap<Integer,Integer> enOrden = new HashMap<>();
		int cantidadSeleccionados = tablaSeleccionados.getRowCount();
		for(int i = 0 ; i < cantidadSeleccionados; i++) {
			Integer idProd = (Integer) tablaSeleccionados.getModel().getValueAt(i,0);
			Integer cantidad = (Integer) tablaSeleccionados.getModel().getValueAt(i,tablaSeleccionados.getColumnCount()-2);
			enOrden.put(idProd,cantidad);
		}
		orden.setProductos(enOrden);
		try {
			GestorOrden.getInstance().altaOrden(orden);
			JOptionPane.showMessageDialog(
					frame,
					"La orden de provision fue generada con exito\n"
					+ "Su orden ahora se encuentra en estado \"Pendiente\"\n"
					+ "Puede ponerla \"En proceso\" volviendo al menu principal.",
					"Generacion de orden exitosa",
					JOptionPane.INFORMATION_MESSAGE);
		}catch(SQLException | ClassNotFoundException ex) {
			DatabaseErrorMessage.showMessageDialog(frame);
			ex.printStackTrace();
		}finally {
			MenuPrincipal menu = new MenuPrincipal(frame);
			this.setVisible(false);
			menu.setVisible(true);
			frame.setContentPane(menu);
		}
	}
	
	public void actionVolver(){
		this.setVisible(false);
		pantallaAnterior.setVisible(true);
		frame.setContentPane(pantallaAnterior);
	}
}