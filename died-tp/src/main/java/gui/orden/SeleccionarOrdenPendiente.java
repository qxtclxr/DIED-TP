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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import datos.Producto;
import gui.DatabaseErrorMessage;
import gui.InvalidInputMessage;
import gui.Pantalla;
import gui.SyntaxValidator;
import gui.producto.OpcionesPopupProducto;
import gui.tabla.ButtonCellEditor;
import gui.tabla.ButtonCellRenderer;
import gui.tabla.TablaDeDatos;
import logica.GestorProducto;

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
	
	/**
	 * Create the panel.
	 */
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
		
		panelContenedorTabla = new JScrollPane(tabla);
		panelContenedorTabla.setBounds(10, 90, 780, 361);
		add(panelContenedorTabla);
	}
	
	public void generarData() {
		
	}
	
	public void actionEliminar() {
			
	}
	
	public void actionConfirmar() {
		
	}
	
	public void actionVolver(){
		this.setVisible(false);
		pantallaAnterior.setVisible(true);
		frame.setContentPane(pantallaAnterior);
	}



}
