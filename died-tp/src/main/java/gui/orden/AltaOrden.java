package gui.orden;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.*;
import datos.*;
import gui.InvalidInputMessage;

public class AltaOrden extends FormularioOrden{
	
	public AltaOrden(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		
		super.inicializarComponentes();
		
		JLabel lblAltaDeProductos = new JLabel("Generar orden de provision");
		lblAltaDeProductos.setHorizontalAlignment(SwingConstants.LEFT);
		lblAltaDeProductos.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblAltaDeProductos.setBounds(10, 11, 780, 30);
		add(lblAltaDeProductos);
		
		JLabel lblDescripcion = new JLabel("Completa el formulario y presiona \"Continuar\" para guardar los datos ingresados. De haber errores se pedira el reingreso de los datos.");
		lblDescripcion.setBounds(10, 50, 780, 14);
		lblDescripcion.setForeground(new Color(128, 128, 128));
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblDescripcion);
	}

	@Override
	public void actionContinuar() {
		if(validateInput()) {
			OrdenDeProvision orden = new OrdenDeProvision();
			orden.setSucursalDestino((Sucursal) cmbSucursalDestino.getSelectedItem());
			orden.setTiempoMaximo(Integer.parseInt(txtTiempoMaximo.getText()));
			orden.setFecha(Date.valueOf(LocalDate.now()));
			orden.setEstado(EstadoOrden.PENDIENTE);
			ListarProductosOrden listar = new ListarProductosOrden(frame,this,orden);
			this.setVisible(false);
			listar.setVisible(true);
			frame.setContentPane(listar);
		}else {
			InvalidInputMessage.showMessageDialog(frame);
		}
	}
}
