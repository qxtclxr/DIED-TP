package gui.ruta;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import datos.*;
import logica.*;

public class AltaRuta extends FormularioRuta{
	
	public AltaRuta(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		
		super.inicializarComponentes();
		
		JLabel lblAltaDeProductos = new JLabel("Alta de ruta");
		lblAltaDeProductos.setHorizontalAlignment(SwingConstants.LEFT);
		lblAltaDeProductos.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblAltaDeProductos.setBounds(10, 11, 780, 30);
		add(lblAltaDeProductos);
		
		JLabel lblDescripcion = new JLabel("Completa el formulario y presiona \"Confirmar\" para guardar los datos ingresados. De haber errores se pedira el reingreso de los datos.");
		lblDescripcion.setBounds(10, 50, 780, 14);
		lblDescripcion.setForeground(new Color(128, 128, 128));
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblDescripcion);
	}

	@Override
	public void actionConfirmar() {
		if(this.validateInput()) {
			try {
				GestorRuta.getInstance().altaRuta(
						(Sucursal) cmbSucursalOrigen.getSelectedItem(),
						(Sucursal) cmbSucursalDestino.getSelectedItem(),
						(Operatividad) cmbOperatividad.getSelectedItem(),
						txtDuracion.getText(),
						txtCapacidadMaxima.getText());
				JOptionPane.showMessageDialog(
						frame,
						"La ruta ha sido creada correctamente.",
						"Datos guardados",
						JOptionPane.INFORMATION_MESSAGE);
			}catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(
						frame,
						"Ha habido un error al interactuar con la base de datos, es posible que las modificaciones no se hayan realizado.\n"
						+ "Intente de nuevo más tarde.",
						"Error de base de datos",
						JOptionPane.ERROR_MESSAGE);
			}finally {
				this.setVisible(false);
				pantallaAnterior.setVisible(true);
				frame.setContentPane(pantallaAnterior);
			}
			
		}else {
			JOptionPane.showMessageDialog(
					frame,
					"Algunos de los datos ingresados son invalidos.\nRevise los campos marcados en rojo.",
					"Datos ingresados invalidos",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
