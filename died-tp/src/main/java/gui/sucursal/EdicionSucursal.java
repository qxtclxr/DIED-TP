package gui.sucursal;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import datos.*;

public class EdicionSucursal extends FormularioSucursal {
	
	private Sucursal suc;
	
	public EdicionSucursal(JFrame frame, JPanel pantallaAnterior, Sucursal suc) {
		super(frame, pantallaAnterior);
		this.suc = suc;
		setDefaultValues();
	}
	
	public void inicializarComponentes() {
		
		super.inicializarComponentes();
		
		JLabel lblAltaDeProductos = new JLabel("Edicion de sucursal");
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
		String horarioApertura =
				String.format("%02d", suc.getHorarioApertura().toLocalTime().getHour()) +
				":" +
				String.format("%02d", suc.getHorarioApertura().toLocalTime().getMinute());
		String horarioCierre =
				String.format("%02d", suc.getHorarioCierre().toLocalTime().getHour()) +
				":" +
				String.format("%02d", suc.getHorarioCierre().toLocalTime().getMinute());
		txtNombre.setText(suc.getNombre());
		cmbTipoSucursal.setSelectedItem(suc.getTipo());
		cmbOperatividad.setSelectedItem(suc.getEstado());
		txtHorarioApertura.setValue(horarioApertura);
		txtHorarioCierre.setValue(horarioCierre);
	}

	@Override
	public void actionConfirmar() {
		// TODO Auto-generated method stub
		
	}

}
