package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class OpcionesCellRenderer extends DefaultTableCellRenderer {
    private JButton button;

    public OpcionesCellRenderer() {
        button = new JButton("Opciones");
        button.setFont(new Font("Tahoma", Font.BOLD, 11));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }
}