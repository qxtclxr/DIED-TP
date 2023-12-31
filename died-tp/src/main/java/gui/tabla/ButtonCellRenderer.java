package gui.tabla;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ButtonCellRenderer extends DefaultTableCellRenderer {
    protected JButton button;

    public ButtonCellRenderer() {
        button = new JButton("Opciones"); //Label default es "Opciones"
        button.setFont(new Font("Tahoma", Font.BOLD, 11));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }
    
    public void setLabel(String label) {
    	button.setText(label);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }
}