package gui.ruta;

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

public abstract class FormularioRuta extends Pantalla {
	protected JComboBox<Sucursal> cmbSucursalOrigen;
	protected JComboBox<Sucursal> cmbSucursalDestino;
	protected JComboBox<Operatividad> cmbOperatividad;
	protected JTextField txtDuracion;
	protected JTextField txtCapcidadMaxima;
	
	public FormularioRuta(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		
		setLayout(null);
		
		
		//TODO: Prueba
		Sucursal aux = new Sucursal(13245,"Moron",Time.valueOf("8:00:00"),Time.valueOf("16:00:00"),Operatividad.OPERATIVA,TipoSucursal.COMERCIAL);
		Sucursal[] auxArr = new Sucursal[100];
		Arrays.fill(auxArr,aux);
		ArrayList<Sucursal> todasLasSucursales = new ArrayList<Sucursal>(Arrays.asList(auxArr));
		//TODO: Prueba
		
		//List<Sucursal> todasLasSucursales = GestorSucursal.getInstance().getAll();
		
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
		
		for(Sucursal suc : todasLasSucursales)
			cmbSucursalOrigen.addItem(suc);
		
		JLabel lblSucursalDestino = new JLabel("Sucursal de destino");
		lblSucursalDestino.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalDestino.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSucursalDestino.setBounds(415, 110, 355, 20);
		add(lblSucursalDestino);
		
		cmbSucursalDestino = new JComboBox<Sucursal>();
		cmbSucursalDestino.setBounds(415, 135, 355, 20);
		add(cmbSucursalDestino);
		
		for(Sucursal suc : todasLasSucursales)
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
		
		txtCapcidadMaxima = new JTextField();
		txtCapcidadMaxima.setBounds(30, 342, 355, 20);
		add(txtCapcidadMaxima);
		
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
