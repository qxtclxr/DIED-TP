package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InvalidInputMessage extends JOptionPane{
	public static void showMessageDialog(JFrame frame) {
		InvalidInputMessage.showMessageDialog(
				frame,
				"Algunos de los datos ingresados son invalidos.\nRevise los campos marcados en rojo.",
				"Datos ingresados invalidos",
				JOptionPane.ERROR_MESSAGE);
	}
}
