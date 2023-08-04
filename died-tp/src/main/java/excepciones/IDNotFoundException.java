package excepciones;

import javax.swing.JOptionPane;

public class IDNotFoundException extends Exception{
	@Override
	public String getMessage() {
		JOptionPane.showMessageDialog(
				null,
				"Algunos de los datos ingresados son invalidos.\nRevise los campos marcados en rojo.",
				"Datos ingresados invalidos",
				JOptionPane.ERROR_MESSAGE);
		return "";
	}
}
