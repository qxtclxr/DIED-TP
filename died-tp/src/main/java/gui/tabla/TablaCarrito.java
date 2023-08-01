package gui.tabla;

import javax.swing.table.DefaultTableModel;
import java.util.HashSet;

public class TablaCarrito extends TablaDeDatos{
	
	private HashSet<Integer> idEnCarrito;
	private SpinnerCellEditor spinnerEditor;
	
	public TablaCarrito(String[] colNames) {
		super();
		this.idEnCarrito = new HashSet<>();
		DefaultTableModel modeloTabla = new DefaultTableModel(new Object[0][colNames.length],colNames) {
			@Override
			public boolean isCellEditable(int row, int col) {return col==this.getColumnCount()-1 || col==this.getColumnCount()-2;}
			//Se edita solo la ultima columna que es la que tiene el boton de opciones y la que tiene el spinner
		};
		this.setModel(modeloTabla);
		this.setModel(modeloTabla);
		this.cellRenderer = new OpcionesCellRenderer();
		this.cellEditor = new OpcionesCellEditor();
		this.spinnerEditor = new SpinnerCellEditor();
		this.getColumnModel().getColumn(this.getColumnCount()-1).setCellRenderer(cellRenderer);
		this.getColumnModel().getColumn(this.getColumnCount()-1).setCellEditor(cellEditor);
		this.getColumnModel().getColumn(this.getColumnCount()-2).setCellEditor(spinnerEditor);
		this.setButtonLabel("Eliminar");
		this.onPressingButton(act -> this.actionEliminar());
	}
	
	public void actionEliminar() {
		int row = this.convertRowIndexToModel(this.getEditingRow());
		Integer selectedProdID = (Integer) this.getModel().getValueAt(row,0);
		DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.removeRow(row);
        idEnCarrito.remove(selectedProdID);
	}
	
	public HashSet<Integer> getCarrito() {
		return idEnCarrito;
	}
	
}
