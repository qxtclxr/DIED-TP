package gui.ruta;

import datos.*;
import gui.Pantalla;
import logica.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FormularioRuta extends Pantalla {
	protected JComboBox<Sucursal> cmbSucursalOrigen;
	protected JComboBox<Sucursal> cmbSucursalDestino;
	protected JComboBox<Operatividad> cmbOperatividad;
	protected JTextField txtDuracion;
	protected JTextField txtCapacidadMaxima;
	
	public FormularioRuta(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		try {
			setLayout(null);
			
			/*TODO: Prueba
			Sucursal aux = new Sucursal(13245,"Moron",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.OPERATIVA,TipoSucursal.COMERCIAL);
			Sucursal[] auxArr = new Sucursal[100];
			Arrays.fill(auxArr,aux);
			ArrayList<Sucursal> todasLasSucursales = new ArrayList<Sucursal>(Arrays.asList(auxArr));
			*/
			
			List<Sucursal> posiblesOrigenes = GestorSucursal.getInstance().getPosiblesOrigenes();
			
			List<Sucursal> posiblesDestinos = GestorSucursal.getInstance().getPosiblesDestinos();
			
			JSeparator separadorTituloContenido = new JSeparator();
			separadorTituloContenido.setBounds(10, 77, 780, 2);
			add(separadorTituloContenido);
			
			JLabel lblSucursalOrigen = new JLabel("Sucursal de origen");
			lblSucursalOrigen.setVerticalAlignment(SwingConstants.TOP);
			lblSucursalOrigen.setBounds(30, 110, 355, 20);
			lblSucursalOrigen.setFont(new Font("Tahoma", Font.BOLD, 14));
			add(lblSucursalOrigen);
			
			cmbSucursalOrigen = new JComboBox<Sucursal>();
			cmbSucursalOrigen.setBounds(30, 135, 355, 20);
			add(cmbSucursalOrigen);
			
			for(Sucursal suc : posiblesOrigenes)
				cmbSucursalOrigen.addItem(suc);
			
			JLabel lblSucursalDestino = new JLabel("Sucursal de destino");
			lblSucursalDestino.setVerticalAlignment(SwingConstants.TOP);
			lblSucursalDestino.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblSucursalDestino.setBounds(415, 110, 355, 20);
			add(lblSucursalDestino);
			
			cmbSucursalDestino = new JComboBox<Sucursal>();
			cmbSucursalDestino.setBounds(415, 135, 355, 20);
			add(cmbSucursalDestino);
			
			for(Sucursal suc : posiblesDestinos)
				cmbSucursalDestino.addItem(suc);
			
			JLabel lblEstadoDeOperatividad = new JLabel("Estado de operatividad");
			lblEstadoDeOperatividad.setVerticalAlignment(SwingConstants.TOP);
			lblEstadoDeOperatividad.setBounds(30, 214, 250, 20);
			lblEstadoDeOperatividad.setFont(new Font("Tahoma", Font.BOLD, 14));
			add(lblEstadoDeOperatividad);
			
			cmbOperatividad = new JComboBox<Operatividad>();
			cmbOperatividad.setBounds(30, 239, 355, 20);
			add(cmbOperatividad);
			cmbOperatividad.addItem(Operatividad.OPERATIVA);
			cmbOperatividad.addItem(Operatividad.NO_OPERATIVA);
			
			JLabel lblDuracion = new JLabel("Duracion");
			lblDuracion.setVerticalAlignment(SwingConstants.TOP);
			lblDuracion.setBounds(415, 214, 250, 20);
			lblDuracion.setFont(new Font("Tahoma", Font.BOLD, 14));
			add(lblDuracion);
			
			txtDuracion = new JTextField();
			txtDuracion.setBounds(415, 239, 355, 20);
			add(txtDuracion);
			
			JLabel descDuracion = new JLabel("Tiempo de transito (en minutos).");
			descDuracion.setForeground(Color.GRAY);
			descDuracion.setFont(new Font("Tahoma", Font.PLAIN, 11));
			descDuracion.setBounds(415, 264, 177, 14);
			add(descDuracion);
			
			JLabel lblCapacidadMaxima = new JLabel("Capacidad maxima");
			lblCapacidadMaxima.setVerticalAlignment(SwingConstants.TOP);
			lblCapacidadMaxima.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCapacidadMaxima.setBounds(30, 317, 250, 20);
			add(lblCapacidadMaxima);
			
			txtCapacidadMaxima = new JTextField();
			txtCapacidadMaxima.setBounds(30, 342, 355, 20);
			add(txtCapacidadMaxima);
			
			JLabel descCapacidadMaxima = new JLabel("<html>Maxima cantidad de mercaderia (en killogramos) que puede transportarse<br>por la ruta.</html>");
			descCapacidadMaxima.setForeground(Color.GRAY);
			descCapacidadMaxima.setFont(new Font("Tahoma", Font.PLAIN, 11));
			descCapacidadMaxima.setBounds(30, 367, 355, 28);
			add(descCapacidadMaxima);
			
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(new Color(255, 159, 162));
			btnCancelar.setBounds(580, 467, 100, 23);
			btnCancelar.addActionListener(act -> this.actionCancelar());
			btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			add(btnCancelar);
			
			JButton btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(act -> actionConfirmar());
			btnConfirmar.setBackground(new Color(129, 205, 133));
			btnConfirmar.setBounds(690, 467, 100, 23);
			btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 13));
			add(btnConfirmar);
			
			fieldsDefaultColor();
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(
					frame,
					"Ha habido un error al interactuar con la base de datos.\nIntente de nuevo más tarde.",
					"Error de base de datos",
					JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			pantallaAnterior.setVisible(true);
			frame.setContentPane(pantallaAnterior);
		}
	}
	
	protected void fieldsDefaultColor() {
		cmbSucursalOrigen.setBackground(Color.WHITE);
		cmbSucursalDestino.setBackground(Color.WHITE);
		cmbOperatividad.setBackground(Color.WHITE);
		txtDuracion.setBackground(Color.WHITE);
		txtCapacidadMaxima.setBackground(Color.WHITE);
	}
	
	protected boolean validCombobox(JComboBox combobox) {
		return combobox.getSelectedItem() != null;
	}
	
	protected boolean validFloatingPoint(JTextField field) {
		return field.getText().matches("[0-9]+(\\.[0-9]*)?");
	}
	
	protected boolean validInteger(JTextField field) {
		return field.getText().matches("\\d+");
	}
	
	protected boolean validateInput() {
		fieldsDefaultColor();
		Color colorInvalid = Color.decode("#ff8080");
		boolean validInput = true;
		
		if(!validCombobox(cmbSucursalOrigen)) {
			cmbSucursalOrigen.setBackground(colorInvalid);
			validInput = false;
		}
		if(!validCombobox(cmbSucursalDestino)) {
			cmbSucursalDestino.setBackground(colorInvalid);
			validInput = false;
		}
		if(!validCombobox(cmbOperatividad)) {
			cmbOperatividad.setBackground(colorInvalid);
			validInput = false;
		}
		if(!validInteger(txtDuracion)) {
			txtDuracion.setBackground(colorInvalid);
			validInput = false;
		}
		if(!validFloatingPoint(txtCapacidadMaxima)) {
			txtCapacidadMaxima.setBackground(colorInvalid);
			validInput = false;
		}
		return validInput;
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
