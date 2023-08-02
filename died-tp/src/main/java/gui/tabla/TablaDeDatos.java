package gui.tabla;

import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablaDeDatos extends JTable{
	
	protected OpcionesCellRenderer cellRenderer;
	protected OpcionesCellEditor cellEditor;
	
	public TablaDeDatos(String[] colNames) {
		super();
		DefaultTableModel modeloTabla = new DefaultTableModel(new Object[0][colNames.length],colNames) {
			@Override
			public boolean isCellEditable(int row, int col) {return col==this.getColumnCount()-1;}
			//Se edita solo la ultima columna que es la que tiene el boton de opciones.
		};
		this.setModel(modeloTabla);
		this.cellRenderer = new OpcionesCellRenderer();
		this.cellEditor = new OpcionesCellEditor();
		this.getColumnModel().getColumn(this.getColumnCount()-1).setCellRenderer(cellRenderer);
		this.getColumnModel().getColumn(this.getColumnCount()-1).setCellEditor(cellEditor);
	}
	
	public TablaDeDatos() {
		super();
	}

	public void setButtonLabel(String label) {
		this.cellRenderer.setLabel(label);
		this.cellEditor.setLabel(label);
	}
	
	public void onPressingButton(ActionListener listener) {
		this.cellEditor.setActionListener(listener);
	}
}
