package gui.ruta;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import datos.*;

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
		txtCapcidadMaxima.setText(ruta.getCapacidadMaxima().toString());
		
	}

	@Override
	public void actionConfirmar() {
		// TODO Auto-generated method stub
		
	}

}
