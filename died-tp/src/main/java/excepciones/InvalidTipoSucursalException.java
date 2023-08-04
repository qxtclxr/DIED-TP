package excepciones;

import javax.swing.JOptionPane;

import datos.TipoSucursal;

public class InvalidTipoSucursalException extends Exception {
	private TipoSucursal tipo;
	public InvalidTipoSucursalException(TipoSucursal tipo) {
		this.tipo = tipo;
	}
	public String getMessage() {
		if(tipo == TipoSucursal.FUENTE) {
			JOptionPane.showMessageDialog(
					null,
					"No puede modificarse a ese tipo de sucursal\n"
					+ "La sucursal tiene rutas entrantes (una sucursal FUENTE no puede tener rutas entrantes).",
					"Error: No puede modificarse el tipo de sucursal",JOptionPane.ERROR_MESSAGE);
		}else if(tipo == TipoSucursal.SUMIDERO){
			JOptionPane.showMessageDialog(
					null,
					"No puede modificarse a ese tipo de sucursal\n"
					+ "La sucursal tiene rutas salientes (una sucursal SUMIDERO no puede tener rutas salientes).",
					"Error: No puede modificarse el tipo de sucursal",JOptionPane.ERROR_MESSAGE);
		}
		return "";
	}
}
