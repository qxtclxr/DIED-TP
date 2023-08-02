package gui;

import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

public class SyntaxValidator {
	
	public static boolean validHorario(JTextComponent horario) {
		return horario.getText().matches("([01][0-9]|2[0-3]):([0-5][0-9])");
	}
	
	public static boolean validCombobox(JComboBox combobox) {
		return combobox.getSelectedItem() != null;
	}
	
	public static boolean validInteger(JTextComponent field) {
		return field.getText().matches("\\d+");
	}
	
	public static boolean validTextLength(JTextComponent field, int minLen, int maxLen) {
		int len = field.getText().length();
		return len >= minLen && len <= maxLen;
	}
	
	public static boolean validFloatingPoint(JTextComponent field) {
		return field.getText().matches("[0-9]+(\\.[0-9]*)?");
	}
	
	public static boolean validID() {return true;}
	/* Por mas que ahora sea un metodo practicamente innecesario,
	 * la implementacion puede cambiar por lo que se deja declarado el metodo*/
	
	public static boolean validHorarioOrEmpty(JTextComponent horario) {
		return horario.getText().matches("([01][0-9]|2[0-3]):([0-5][0-9])") || horario.getText().equals("--:--");
	}
	
	public static boolean validComboboxOrNull(JComboBox combobox) {return true;}
	/* Por mas que ahora sea un metodo practicamente innecesario,
	 * la implementacion puede cambiar por lo que se deja declarado el metodo*/
	
	public static boolean validIntegerOrEmpty(JTextComponent field) {
		return field.getText().matches("\\d+") || field.getText().isEmpty();
	}
	
	public static boolean validFloatingPointOrEmpty(JTextComponent field) {
		return field.getText().matches("[0-9]+(\\.[0-9]*)?") || field.getText().isEmpty();
	}
}
