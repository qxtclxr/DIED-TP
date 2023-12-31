package gui.producto;

import datos.*;
import gui.*;
import gui.ruta.OpcionesPopupRuta;
import gui.tabla.TablaDeDatos;
import logica.GestorProducto;
import logica.GestorSucursal;

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Point;
import java.sql.SQLException;
import java.sql.Time;


public class ConsultaProducto extends Pantalla {

	protected static final String[] COL_NAMES = {"ID del producto","Nombre","Descripcion","Precio unitario","Peso (en kg.)",""};
	protected JLabel lblTitulo;
	protected JLabel descTitulo;
	protected JTextField txtIDProducto;
	protected JTextField txtNombre;
	protected JTextField txtPrecioDesde;
	protected JTextField txtPrecioHasta;
	protected JTextField txtPesoDesde;
	protected JTextField txtPesoHasta;
	protected TablaDeDatos tabla;
	protected JScrollPane panelContenedorTabla;
	protected JButton btnBuscar;
	
	/**
	 * Create the panel.
	 */
	public ConsultaProducto(JFrame frame, JPanel pantallaAnterior) {
		super(frame,pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		setLayout(null);
		
		lblTitulo = new JLabel("Consulta de productos");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitulo.setBounds(10, 11, 780, 30);
		add(lblTitulo);
		
		descTitulo = new JLabel("Completa los campos para filtrar la busqueda. A continuacion presiona el boton \"Buscar\".");
		descTitulo.setForeground(Color.GRAY);
		descTitulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descTitulo.setBounds(10, 50, 780, 14);
		add(descTitulo);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
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
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(act -> actionBuscar());
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setBounds(10, 144, 121, 20);
		add(btnBuscar);
		
		JButton btnCancelar = new JButton("Volver");
		btnCancelar.setBounds(690, 466, 100, 23);
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
		
		fieldsDefaultColor();
		generarTabla();
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
	
	protected void generarTabla() {
		tabla = new TablaDeDatos(COL_NAMES);
		tabla.onPressingButton(act -> actionOpcionesPopup());
		panelContenedorTabla = new JScrollPane(tabla);
		panelContenedorTabla.setBounds(10, 174, 780, 277);
		add(panelContenedorTabla);
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
				DefaultTableModel model = (DefaultTableModel) tabla.getModel();
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
	
	public void actionOpcionesPopup() {
		int row = tabla.convertRowIndexToModel(tabla.getEditingRow());
        int column = tabla.convertColumnIndexToModel(tabla.getEditingColumn());
        Rectangle cellRect = tabla.getCellRect(row, column, true);
        Point popupLocation = new Point(cellRect.x + cellRect.width, cellRect.y);
        Integer idSelected = (Integer) tabla.getModel().getValueAt(row,0);
        OpcionesPopupProducto popupMenu = new OpcionesPopupProducto(idSelected,frame,this);
        popupMenu.show(tabla, popupLocation.x, popupLocation.y);
	}
	
	public void actionVolver(){
		this.setVisible(false);
		pantallaAnterior.setVisible(true);
		frame.setContentPane(pantallaAnterior);
	}

}