package gui.producto;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import datos.*;

public class EdicionProducto extends FormularioProducto {
	
	private Producto prod;
	
	public EdicionProducto(JFrame frame, JPanel pantallaAnterior, Producto prod) {
		super(frame, pantallaAnterior);
		this.prod = prod;
		setDefaultValues();
	}
	
	public void inicializarComponentes() {
		
		super.inicializarComponentes();
		
		JLabel lblAltaDeProductos = new JLabel("Edicion de producto");
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
		txtNombre.setText(prod.getNombre());
		txtPrecio.setText(prod.getPrecioUnitario().toString());
		txtPeso.setText(prod.getPesoKg().toString());
		txtDescripcion.setText(prod.getDescripcion());
	}

	@Override
	public void actionConfirmar() {
		// TODO Auto-generated method stub
		
	}

}
