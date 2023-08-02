package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DatabaseErrorMessage extends JOptionPane {
	public static void showMessageDialog(JFrame frame) {
		DatabaseErrorMessage.showMessageDialog(
				frame,
				"Ha habido un error al interactuar con la base de datos, es posible que las modificaciones no se hayan realizado.\n"
				+ "Intente de nuevo más tarde.",
				"Error de base de datos",
				JOptionPane.ERROR_MESSAGE);
	}
}
