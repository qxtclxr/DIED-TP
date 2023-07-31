package gui.producto;

import datos.*;
import gui.Pantalla;
import logica.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public abstract class FormularioProducto extends Pantalla {
	protected JTextField txtNombre;
	protected JTextField txtPrecio;
	protected JTextField txtPeso;
	protected JTextArea txtDescripcion;
	
	public FormularioProducto(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		
		setLayout(null);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setVerticalAlignment(SwingConstants.TOP);
		lblNombre.setBounds(30, 110, 355, 20);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(30, 135, 355, 20);
		add(txtNombre);
		
		JLabel descNombre = new JLabel("Nombre del producto. Limite: 256 caracteres.");
		descNombre.setForeground(Color.GRAY);
		descNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descNombre.setBounds(30, 160, 355, 14);
		add(descNombre);
		
		JLabel lblPrecio = new JLabel("Precio unitario");
		lblPrecio.setVerticalAlignment(SwingConstants.TOP);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecio.setBounds(415, 110, 355, 20);
		add(lblPrecio);
		
		JLabel descPrecio = new JLabel("Precio del producto (en pesos argentinos).");
		descPrecio.setForeground(Color.GRAY);
		descPrecio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descPrecio.setBounds(415, 160, 355, 14);
		add(descPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(415, 135, 355, 20);
		add(txtPrecio);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setVerticalAlignment(SwingConstants.TOP);
		lblPeso.setBounds(30, 214, 250, 20);
		lblPeso.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblPeso);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(30, 239, 355, 20);
		add(txtPeso);
		
		JLabel descPeso = new JLabel("Peso del producto (en kilogramos).");
		descPeso.setForeground(Color.GRAY);
		descPeso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descPeso.setBounds(30, 264, 177, 14);
		add(descPeso);
		
		JLabel Descripcion = new JLabel("Descripcion");
		Descripcion.setVerticalAlignment(SwingConstants.TOP);
		Descripcion.setBounds(415, 214, 250, 20);
		Descripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(Descripcion);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setBounds(415, 239, 355, 80);
		add(txtDescripcion);
		
		JLabel descDescripcion = new JLabel("Breve descripción del producto. Limite: 1024 caracteres.");
		descDescripcion.setForeground(Color.GRAY);
		descDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descDescripcion.setBounds(415, 324, 355, 14);
		add(descDescripcion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 159, 162));
		btnCancelar.setBounds(580, 467, 100, 23);
		btnCancelar.addActionListener(act -> this.actionCancelar());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(new Color(129, 205, 133));
		btnConfirmar.setBounds(690, 467, 100, 23);
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnConfirmar);		
	}
	
	public void actionCancelar() {
		int result = JOptionPane.showConfirmDialog(frame,"¿Seguro que quieres cancelar la operacion? Los datos no seran guardados","Cancelar",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION) {
			this.setVisible(false);
			pantallaAnterior.setVisible(true);
			frame.setContentPane(pantallaAnterior);
		}
	}
	
	public abstract void actionConfirmar();
}
