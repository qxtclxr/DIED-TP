package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcionesCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;
    private JPopupMenu popupMenu;

    public OpcionesCellEditor(JTable table, ActionListener act) {
        button = new JButton("Opciones");
        button.setFont(new Font("Tahoma", Font.BOLD, 11));
        button.addActionListener(act);
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
