package gui.sucursal;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import datos.Operatividad;
import datos.TipoSucursal;
import gui.DatabaseErrorMessage;
import gui.InvalidInputMessage;
import logica.GestorSucursal;

public class AltaSucursal extends FormularioSucursal{
	
	public AltaSucursal(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		
		super.inicializarComponentes();
		
		JLabel lblAltaDeProductos = new JLabel("Alta de sucursal");
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
				GestorSucursal.getInstance().altaSucursal(
						txtNombre.getText(),
						(TipoSucursal) cmbTipoSucursal.getSelectedItem(),
						(Operatividad) cmbOperatividad.getSelectedItem(),
						txtHorarioApertura.getText(),
						txtHorarioCierre.getText());
				JOptionPane.showMessageDialog(
						frame,
						"La sucursal ha sido creada correctamente.",
						"Datos guardados",
						JOptionPane.INFORMATION_MESSAGE);
			}catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				DatabaseErrorMessage.showMessageDialog(frame);
			}finally {
				this.setVisible(false);
				pantallaAnterior.setVisible(true);
				frame.setContentPane(pantallaAnterior);
			}
			
		}else {
			InvalidInputMessage.showMessageDialog(frame);
		}
	}
}
