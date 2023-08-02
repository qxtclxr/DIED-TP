package gui.ruta;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.*;
import datos.*;
import gui.DatabaseErrorMessage;
import logica.GestorRuta;

public class EdicionRuta extends FormularioRuta {
	
	private Ruta ruta;
	
	public EdicionRuta(JFrame frame, JPanel pantallaAnterior, Ruta ruta) {
		super(frame, pantallaAnterior);
		this.ruta = ruta;
		setDefaultValues();
	}
	
	public void inicializarComponentes() {
		
		super.inicializarComponentes();
		
		JLabel lblAltaDeProductos = new JLabel("Edicion de ruta");
		lblAltaDeProductos.setHorizontalAlignment(SwingConstants.LEFT);
		lblAltaDeProductos.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblAltaDeProductos.setBounds(10, 11, 780, 30);
		add(lblAltaDeProductos);
		
		JLabel lblDescripcion = new JLabel("Modifica los datos que sean necesarios y presiona \"Continuar\" para guardar los datos ingresados. De haber errores se pedira el reingreso de los datos.");
		lblDescripcion.setBounds(10, 50, 780, 14);
		lblDescripcion.setForeground(new Color(128, 128, 128));
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblDescripcion);
		
	}
	
	public void setDefaultValues() {
		
		cmbSucursalOrigen.setSelectedItem(ruta.getOrigen());
		cmbSucursalDestino.setSelectedItem(ruta.getDestino());
		cmbOperatividad.setSelectedItem(ruta.getEstado());
		txtDuracion.setText(ruta.getDuracion().toString());
		txtCapacidadMaxima.setText(ruta.getCapacidadMaxima().toString());
		
	}

	@Override
	public void actionConfirmar() {
		if(this.validateInput()) {
			try {
				GestorRuta.getInstance().modificarRuta(
						ruta.getID(),
						(Sucursal) cmbSucursalOrigen.getSelectedItem(),
						(Sucursal) cmbSucursalDestino.getSelectedItem(),
						(Operatividad) cmbOperatividad.getSelectedItem(),
						txtDuracion.getText(),
						txtCapacidadMaxima.getText());
				JOptionPane.showMessageDialog(
						frame,
						"La ruta ha sido modificada correctamente.",
						"Datos guardados",
						JOptionPane.INFORMATION_MESSAGE);
			}catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				DatabaseErrorMessage.showMessageDialog(frame);
			}finally {
				this.setVisible(false);
				//Refresca la tabla para mostrar las modificaciones
				((ConsultaRuta) pantallaAnterior).actionBuscar();
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
