package gui.sucursal;

import datos.*;
import gui.Pantalla;
import logica.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class FormularioSucursal extends Pantalla {
	protected JTextField txtNombre;
	protected JComboBox<TipoSucursal> cmbTipoSucursal;
	protected JComboBox<Operatividad> cmbOperatividad;
	protected JComboBox<String> cmbHorarioAperturaHora;
	protected JComboBox<String> cmbHorarioAperturaMinutos;
	protected JComboBox<String> cmbHorarioCierreMinutos;
	protected JComboBox<String> cmbHorarioCierreHora;
	
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
		
		cmbHorarioAperturaHora = new JComboBox<String>();
		cmbHorarioAperturaHora.setModel(
				new DefaultComboBoxModel<String>(
						new String[] {"00", "01", "02", "03", "04", "05",
								"06","07", "08", "09", "10", "11",
								"12", "13", "14", "15", "16", "17",
								"18", "19", "20", "21", "22", "23"}));
		cmbHorarioAperturaHora.setBounds(30, 342, 50, 20);
		add(cmbHorarioAperturaHora);
		
		cmbHorarioAperturaMinutos = new JComboBox<String>();
		cmbHorarioAperturaMinutos.setModel(
				new DefaultComboBoxModel<String>(
						new String[] {"00", "01", "02", "03", "04", "05",
								"06", "07", "08", "09", "10", "11",
								"12", "13", "14", "15", "16", "17",
								"18", "19", "20", "21", "22", "23",
								"24", "25", "26", "27", "28", "29",
								"30", "31", "32", "33", "34", "35",
								"36", "37", "38", "39", "40", "41",
								"42", "43", "44", "45", "46", "47",
								"48", "49", "50", "51", "52", "53",
								"54", "55", "56", "57", "58", "59"}));
		cmbHorarioAperturaMinutos.setBounds(94, 342, 50, 20);
		add(cmbHorarioAperturaMinutos);
		
		JLabel lblSeparadorHorarioApertura = new JLabel(":");
		lblSeparadorHorarioApertura.setVerticalAlignment(SwingConstants.TOP);
		lblSeparadorHorarioApertura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSeparadorHorarioApertura.setBounds(84, 342, 7, 22);
		add(lblSeparadorHorarioApertura);
		
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
		
		cmbHorarioCierreHora = new JComboBox<String>();
		cmbHorarioCierreHora.setModel(
				new DefaultComboBoxModel<String>(
						new String[] {"00", "01", "02", "03", "04", "05",
								"06","07", "08", "09", "10", "11",
								"12", "13", "14", "15", "16", "17",
								"18", "19", "20", "21", "22", "23"}));
		cmbHorarioCierreHora.setBounds(415, 342, 50, 20);
		add(cmbHorarioCierreHora);
		
		cmbHorarioCierreMinutos = new JComboBox<String>();
		cmbHorarioCierreMinutos.setModel(
				new DefaultComboBoxModel<String>(
						new String[] {"00", "01", "02", "03", "04", "05",
								"06", "07", "08", "09", "10", "11",
								"12", "13", "14", "15", "16", "17",
								"18", "19", "20", "21", "22", "23",
								"24", "25", "26", "27", "28", "29",
								"30", "31", "32", "33", "34", "35",
								"36", "37", "38", "39", "40", "41",
								"42", "43", "44", "45", "46", "47",
								"48", "49", "50", "51", "52", "53",
								"54", "55", "56", "57", "58", "59"}));
		cmbHorarioCierreMinutos.setBounds(479, 342, 50, 20);
		add(cmbHorarioCierreMinutos);
		
		JLabel lblSeparadorHorarioCierre = new JLabel(":");
		lblSeparadorHorarioCierre.setVerticalAlignment(SwingConstants.TOP);
		lblSeparadorHorarioCierre.setBounds(469, 342, 7, 22);
		lblSeparadorHorarioCierre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(lblSeparadorHorarioCierre);
		
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
