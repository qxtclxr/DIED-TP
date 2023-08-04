package gui.orden;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import datos.*;
import excepciones.IDNotFoundException;
import gui.*;
import gui.tabla.*;
import logica.*;

public class SeleccionarOrdenPendiente extends Pantalla {
	
	protected static final String[] COL_NAMES = {"ID de la orden","Fecha","Sucursal de destino",
												 "Tiempo max. de entrega","Estado","Cant. de productos","",""};
	protected JLabel lblTitulo;
	protected JLabel descTitulo;
	protected JTable tabla;
	protected JScrollPane panelContenedorTabla;
	
	private class SetEnableButtonCellEditor extends ButtonCellEditor{
	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	    	//Checkea el valor de la columna "estado" para saber si es modificable (todavia esta pendiente) o no
	    	boolean isEnabled = table.getValueAt(row,4) == EstadoOrden.PENDIENTE;
	        button.setEnabled(isEnabled);
	        return button;
	    }
	}
	
	private class SetEnableButtonCellRenderer extends ButtonCellRenderer{
		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			//Checkea el valor de la columna "estado" para saber si es modificable (todavia esta pendiente) o no
	    	 boolean isEnabled = table.getValueAt(row,4) == EstadoOrden.PENDIENTE;
	         button.setEnabled(isEnabled);
	         return button;
	    }
	}
	
	public SeleccionarOrdenPendiente(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
		// TODO Auto-generated constructor stub
	}
	
	public void inicializarComponentes() {
		setLayout(null);
		
		lblTitulo = new JLabel("Consulta de ordenes de provision");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitulo.setBounds(10, 11, 780, 30);
		add(lblTitulo);
		
		descTitulo = new JLabel("A continuacion se encuentran todas las ordenes de provision. Si la orden todavia esta pendiente puedes confirmarlas o eliminarlas.");
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
		SetEnableButtonCellRenderer eliminarRenderer = new SetEnableButtonCellRenderer();
		SetEnableButtonCellEditor eliminarEditor = new SetEnableButtonCellEditor();
		eliminarRenderer.setLabel("Eliminar");
		eliminarEditor.setLabel("Eliminar");
		eliminarEditor.setActionListener(act -> actionEliminar());
		tabla.getColumnModel().getColumn(tabla.getColumnCount()-1).setCellRenderer(eliminarRenderer);
		tabla.getColumnModel().getColumn(tabla.getColumnCount()-1).setCellEditor(eliminarEditor);
		
		//Setear renderers y editors para columna confirmar
		SetEnableButtonCellRenderer confirmarRenderer = new SetEnableButtonCellRenderer();
		SetEnableButtonCellEditor confirmarEditor = new SetEnableButtonCellEditor();
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
			java.util.List<OrdenDeProvision> ordenes = GestorOrden.getInstance().getAll();
			DefaultTableModel model = (DefaultTableModel) tabla.getModel();
			model.setRowCount(0); //Resetea la tabla
			for(OrdenDeProvision orden : ordenes) {
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
			java.util.List<Sucursal> tienenStock = GestorSucursal.getInstance().hasStock(target);
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
