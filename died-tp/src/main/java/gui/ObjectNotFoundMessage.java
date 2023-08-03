package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ObjectNotFoundMessage extends JOptionPane {
	public static void showMessageDialog(JFrame frame) {
		InvalidInputMessage.showMessageDialog(
				frame,
				"El objeto seleccionado no fue encontrado.\nEs posible que alguien más lo haya eliminado. Intentalo de nuevo.",
				"Datos ingresados invalidos",
				JOptionPane.ERROR_MESSAGE);
	}
}
