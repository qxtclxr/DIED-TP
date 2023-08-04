package gui.tabla;

import java.awt.*;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;

public class SpinnerCellEditor extends DefaultCellEditor
    {
        JSpinner sp;
        DefaultEditor defaultEditor;
        JTextField text;
        
        // Inicializa el spinner
        public SpinnerCellEditor() {
            super(new JTextField());
            SpinnerNumberModel model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);  
            sp = new JSpinner(model);
            defaultEditor = (DefaultEditor) sp.getEditor();
            text = defaultEditor.getTextField();
        }
        
        // Prepara el componente y lo retorna
        public Component getTableCellEditorComponent(JTable table, Object 
        value, boolean isSelected, int row, int column) 
        {
            sp.setValue(value);
            return sp;
        }
        
        // Retorna el valor actual del spinner
        public Object getCellEditorValue() {
            return sp.getValue();
        }
    }
