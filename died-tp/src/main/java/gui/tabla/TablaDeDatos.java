package gui.tabla;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import datos.Sucursal;
import gui.sucursal.OpcionesPopupSucursal;

public class TablaDeDatos extends JTable{
	
	public TablaDeDatos(Object[][] data, String[] colNames) {
		super();
		DefaultTableModel modeloTabla = new DefaultTableModel(data,colNames) {
			@Override
			public boolean isCellEditable(int row, int col) {return col==this.getColumnCount()-1;}
			//Se edita solo la ultima columna que es la que tiene el boton de opciones.
		};
		this.setModel(modeloTabla);
		this.getColumnModel().getColumn(this.getColumnCount()-1).setCellRenderer(new OpcionesCellRenderer());
		
	}
	
	public void onPressingOpciones(ActionListener listener) {
		OpcionesCellEditor op = new OpcionesCellEditor(this,listener);
		this.getColumnModel().getColumn(this.getColumnCount()-1).setCellEditor(op);
	}

}
