package gui.tabla;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionListener;

public class OpcionesCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;

    public OpcionesCellEditor() {
        button = new JButton("Opciones"); //Label default es "Opciones"
        button.setFont(new Font("Tahoma", Font.BOLD, 11));
    }
    
    public void setActionListener(ActionListener act) {
    	button.addActionListener(act);
    }
    
    public void setLabel(String label) {
    	button.setText(label);
    }
    
    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return button;
    }
}
