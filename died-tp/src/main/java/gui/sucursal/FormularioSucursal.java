package gui.sucursal;

import datos.*;
import gui.Pantalla;
import gui.SyntaxValidator;
import logica.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class FormularioSucursal extends Pantalla {
	protected JTextField txtNombre;
	protected JComboBox<TipoSucursal> cmbTipoSucursal;
	protected JComboBox<Operatividad> cmbOperatividad;
	protected JFormattedTextField txtHorarioApertura;
	protected JFormattedTextField txtHorarioCierre;
	
	public FormularioSucursal(JFrame frame, JPanel pantallaAnterior) {
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
		txtNombre.setBackground(new Color(255, 255, 255));
		txtNombre.setBounds(30, 135, 355, 20);
		txtNombre.setColumns(10);
		add(txtNombre);
		
		JLabel lblDescNombre = new JLabel("Admite hasta 265 caracteres.");
		lblDescNombre.setBounds(30, 160, 355, 14);
		lblDescNombre.setForeground(Color.GRAY);
		lblDescNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblDescNombre);
		
		JLabel lblTipoSucursal = new JLabel("Tipo de sucursal");
		lblTipoSucursal.setVerticalAlignment(SwingConstants.TOP);
		lblTipoSucursal.setBounds(30, 214, 250, 20);
		lblTipoSucursal.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblTipoSucursal);
		
		cmbTipoSucursal = new JComboBox<TipoSucursal>();
		cmbTipoSucursal.setBounds(30, 239, 355, 20);
		add(cmbTipoSucursal);
		cmbTipoSucursal.addItem(TipoSucursal.COMERCIAL);
		cmbTipoSucursal.addItem(TipoSucursal.FUENTE);
		cmbTipoSucursal.addItem(TipoSucursal.SUMIDERO);
		
		cmbOperatividad = new JComboBox<Operatividad>();
		cmbOperatividad.setBounds(415, 239, 355, 20);
		add(cmbOperatividad);
		cmbOperatividad.addItem(Operatividad.OPERATIVA);
		cmbOperatividad.addItem(Operatividad.NO_OPERATIVA);
		
		JLabel lblHorarioDeApertura = new JLabel("Horario de apertura");
		lblHorarioDeApertura.setVerticalAlignment(SwingConstants.TOP);
		lblHorarioDeApertura.setBounds(30, 317, 226, 20);
		lblHorarioDeApertura.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblHorarioDeApertura);
		
		MaskFormatter horaMask = this.createFormatter("##:##");
		horaMask.setPlaceholderCharacter('-');
		
		txtHorarioApertura = new JFormattedTextField(horaMask);
		txtHorarioApertura.setBounds(30, 342, 355, 20);
		add(txtHorarioApertura);
		
		JLabel lblDescHorarioApertura = new JLabel("Se utiliza formato de 24 horas.");
		lblDescHorarioApertura.setBounds(30, 367, 177, 14);
		lblDescHorarioApertura.setForeground(Color.GRAY);
		lblDescHorarioApertura.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblDescHorarioApertura);
		
		JLabel lblHorarioDeCierre = new JLabel("Horario de cierre");
		lblHorarioDeCierre.setVerticalAlignment(SwingConstants.TOP);
		lblHorarioDeCierre.setBounds(415, 317, 226, 20);
		lblHorarioDeCierre.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblHorarioDeCierre);
		
		txtHorarioCierre = new JFormattedTextField(horaMask);
		txtHorarioCierre.setBounds(415, 342, 355, 20);
		add(txtHorarioCierre);
		
		JLabel lblDescHorarioCierre = new JLabel("Se utiliza formato de 24 horas.");
		lblDescHorarioCierre.setBounds(415, 367, 177, 14);
		lblDescHorarioCierre.setForeground(Color.GRAY);
		lblDescHorarioCierre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblDescHorarioCierre);
		
		JLabel lblEstadoDeOperatividad = new JLabel("Estado de operatividad");
		lblEstadoDeOperatividad.setVerticalAlignment(SwingConstants.TOP);
		lblEstadoDeOperatividad.setBounds(415, 214, 250, 20);
		lblEstadoDeOperatividad.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblEstadoDeOperatividad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 159, 162));
		btnCancelar.setBounds(580, 467, 100, 23);
		btnCancelar.addActionListener(act -> this.actionCancelar());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(new Color(129, 205, 133));
		btnConfirmar.setBounds(690, 467, 100, 23);
		btnConfirmar.addActionListener(act -> this.actionConfirmar());
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnConfirmar);
		
		fieldsDefaultColor();
	}
	
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	    	//TODO
	    }
	    return formatter;
	}
	
	protected void fieldsDefaultColor() {
		txtNombre.setBackground(Color.WHITE);
		cmbTipoSucursal.setBackground(Color.WHITE);
		cmbOperatividad.setBackground(Color.WHITE);
		txtHorarioApertura.setBackground(Color.WHITE);
		txtHorarioCierre.setBackground(Color.WHITE);
	}
	
	protected boolean validateInput() {
		fieldsDefaultColor();
		
		Color colorInvalid = Color.decode("#ff8080");
		boolean validInput = true;
		
		if(!SyntaxValidator.validTextLength(txtNombre,1,256)) {
			txtNombre.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validCombobox(cmbTipoSucursal)) {
			cmbTipoSucursal.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validCombobox(cmbOperatividad)) {
			cmbOperatividad.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validHorario(txtHorarioApertura)) {
			txtHorarioApertura.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validHorario(txtHorarioCierre)) {
			txtHorarioCierre.setBackground(colorInvalid);
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
