package gui.orden;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import datos.OrdenDeProvision;
import datos.Producto;
import datos.Sucursal;
import excepciones.IDNotFoundException;
import gui.DatabaseErrorMessage;
import gui.InvalidInputMessage;
import gui.Pantalla;
import gui.SyntaxValidator;
import gui.producto.ConsultaProducto;
import gui.producto.OpcionesPopupProducto;
import gui.tabla.ButtonCellEditor;
import gui.tabla.ButtonCellRenderer;
import gui.tabla.TablaDeDatos;
import logica.GestorOrden;
import logica.GestorProducto;
import logica.GestorSucursal;

public class SeleccionarOrdenPendiente extends Pantalla {
	
	protected static final String[] COL_NAMES = {"ID de la orden","Fecha","Sucursal de destino",
												 "Tiempo max. de entrega","Estado","Cant. de productos","",""};
	protected JLabel lblTitulo;
	protected JLabel descTitulo;
	protected JTable tabla;
	protected JScrollPane panelContenedorTabla;

	public SeleccionarOrdenPendiente(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
		// TODO Auto-generated constructor stub
	}
	
	public void inicializarComponentes() {
		setLayout(null);
		
		lblTitulo = new JLabel("Modificar ordenes de provision pendientes");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitulo.setBounds(10, 11, 780, 30);
		add(lblTitulo);
		
		descTitulo = new JLabel("A continuacion se encuentran todas las ordenes de provision pendientes. Puedes confirmarlas o eliminarlas.");
		descTitulo.setForeground(Color.GRAY);
		descTitulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descTitulo.setBounds(10, 50, 780, 14);
		add(descTitulo);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		JButton btnCancelar = new JButton("Volver");
		btnCancelar.setBounds(690, 466, 100, 23);
		btnCancelar.addActionListener(act -> this.actionVolver());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
	
		generarTabla();
	}
	
	protected void generarTabla() {
		tabla = new JTable();
		DefaultTableModel modeloTabla = new DefaultTableModel(new Object[0][COL_NAMES.length],COL_NAMES) {
			@Override
			public boolean isCellEditable(int row, int col) {return col==this.getColumnCount()-1 || col==this.getColumnCount()-2;}
		};
		tabla.setModel(modeloTabla);
		
		//Setear renderers y editors para columna eliminar.
		ButtonCellRenderer eliminarRenderer = new ButtonCellRenderer();
		ButtonCellEditor eliminarEditor = new ButtonCellEditor();
		eliminarRenderer.setLabel("Eliminar");
		eliminarEditor.setLabel("Eliminar");
		eliminarEditor.setActionListener(act -> actionEliminar());
		tabla.getColumnModel().getColumn(tabla.getColumnCount()-1).setCellRenderer(eliminarRenderer);
		tabla.getColumnModel().getColumn(tabla.getColumnCount()-1).setCellEditor(eliminarEditor);
		
		//Setear renderers y editors para columna confirmar
		ButtonCellRenderer confirmarRenderer = new ButtonCellRenderer();
		ButtonCellEditor confirmarEditor = new ButtonCellEditor();
		confirmarRenderer.setLabel("Confirmar");
		confirmarEditor.setLabel("Confirmar");
		confirmarEditor.setActionListener(act -> actionConfirmar());
		tabla.getColumnModel().getColumn(tabla.getColumnCount()-2).setCellRenderer(confirmarRenderer);
		tabla.getColumnModel().getColumn(tabla.getColumnCount()-2).setCellEditor(confirmarEditor);
		
		generarData();
		
		panelContenedorTabla = new JScrollPane(tabla);
		panelContenedorTabla.setBounds(10, 90, 780, 361);
		add(panelContenedorTabla);
	}
	
	public void generarData() {
		try {
			List<OrdenDeProvision> ordenesPend = GestorOrden.getInstance().getPendientes();
			DefaultTableModel model = (DefaultTableModel) tabla.getModel();
			model.setRowCount(0); //Resetea la tabla
			for(OrdenDeProvision orden : ordenesPend) {
				Object[] fila = {
						orden.getID(),
						orden.getFecha(),
						orden.getSucursalDestino(),
						orden.getTiempoMaximo(),
						orden.getEstado(),
						orden.getProductos().size()
				};
				model.addRow(fila);
			}
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			DatabaseErrorMessage.showMessageDialog(frame);
		}
	}
	
	public void actionEliminar() {
		int row = tabla.convertRowIndexToModel(tabla.getEditingRow());
		Integer id = (Integer) tabla.getModel().getValueAt(row,0);
		int result = JOptionPane.showConfirmDialog(
				frame,
				"¿Seguro que quieres eliminar la orden de provision?",
				"Pedido de confirmacion",JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION) {
			try {
				GestorOrden gestor = GestorOrden.getInstance();
				OrdenDeProvision target = gestor.getByID(id);
				gestor.eliminar(target);
				JOptionPane.showMessageDialog(
						frame,
						"La orden de provision ha sido eliminado correctamente.",
						"Datos guardados",
						JOptionPane.INFORMATION_MESSAGE);
			}catch(ClassNotFoundException | SQLException ex) {
				ex.printStackTrace();
				DatabaseErrorMessage.showMessageDialog(frame);
			}catch(IDNotFoundException ex) {
				ex.getMessage();
			}finally {
				//Refresca la tabla
				this.generarData();
			}
		}
	}
	
	public void actionConfirmar() {
		try {
			int row = tabla.convertRowIndexToModel(tabla.getEditingRow());
			Integer id = (Integer) tabla.getModel().getValueAt(row,0);
			GestorOrden gestor = GestorOrden.getInstance();
			OrdenDeProvision target = gestor.getByID(id);
			List<Sucursal> tienenStock = GestorSucursal.getInstance().hasStock(target);
			if(!tienenStock.isEmpty()) {
				SeleccionarCaminoOrden selectCamino = new SeleccionarCaminoOrden(frame,this,target,tienenStock);
				this.setVisible(false);
				selectCamino.setVisible(true);
				frame.setContentPane(selectCamino);
			}else {
				JOptionPane.showMessageDialog(
						frame,
						"En este momento no existen sucursales con los productos que se solicitan\n"
						+ "Intente de nuevo más tarde.",
						"Error: No existen sucursales con stock",
						JOptionPane.ERROR_MESSAGE);
			}
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			DatabaseErrorMessage.showMessageDialog(frame);
			//Refresca la tabla
			this.generarData();
		}catch(IDNotFoundException ex) {
			ex.getMessage();
			//Refresca la tabla
			this.generarData();
		}
		
	}
	
	public void actionVolver(){
		this.setVisible(false);
		pantallaAnterior.setVisible(true);
		frame.setContentPane(pantallaAnterior);
	}

}
