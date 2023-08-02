package gui.producto;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import datos.Operatividad;
import datos.Sucursal;
import gui.DatabaseErrorMessage;
import gui.InvalidInputMessage;
import logica.GestorProducto;
import logica.GestorRuta;

public class AltaProducto extends FormularioProducto{
	
	public AltaProducto(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		
		super.inicializarComponentes();
		
		JLabel lblAltaDeProductos = new JLabel("Alta de producto");
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
				GestorProducto.getInstance().altaProducto(
						txtNombre.getText(),
						txtDescripcion.getText(),
						txtPrecio.getText(),
						txtPeso.getText());
				JOptionPane.showMessageDialog(
						frame,
						"El producto ha sido creado correctamente.",
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
