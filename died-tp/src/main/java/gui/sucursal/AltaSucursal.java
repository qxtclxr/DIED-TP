package gui.sucursal;

import datos.*;
import gui.ComboItem;
import gui.Pantalla;
import logica.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaSucursal extends Pantalla {

	private JTextField txtIDSucursal;
	private JTextField txtNombre;
	private JComboBox<TipoSucursal> cmbTipoSucursal;
	private JComboBox<Operatividad> cmbOperatividad;
	private JTextField txtHorarioAperturaHora;
	private JTextField txtHorarioAperturaMinutos;
	private JTextField txtHorarioCierreMinutos;
	private JTextField txtHorarioCierreHora;
	
	public AltaSucursal(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		setLayout(null);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		JLabel lblDescripcion = new JLabel("Completa el formulario y presiona \"Continuar\" para guardar los datos ingresados. Los datos obligatorios estan marcados con");
		lblDescripcion.setBounds(10, 50, 595, 14);
		lblDescripcion.setForeground(new Color(128, 128, 128));
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblDescripcion);
		
		JLabel lblDescripcionAsterisco = new JLabel("(*)");
		lblDescripcionAsterisco.setBounds(609, 52, 17, 14);
		lblDescripcionAsterisco.setForeground(new Color(255, 0, 0));
		lblDescripcionAsterisco.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblDescripcionAsterisco);
		
		JLabel lblIDSucursal = new JLabel("ID de sucursal");
		lblIDSucursal.setVerticalAlignment(SwingConstants.TOP);
		lblIDSucursal.setBounds(30, 110, 355, 20);
		lblIDSucursal.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblIDSucursal);
		
		txtIDSucursal = new JTextField();
		txtIDSucursal.setBounds(30, 135, 355, 20);
		add(txtIDSucursal);
		txtIDSucursal.setColumns(10);
		
		JLabel lblDescIDSucursal = new JLabel("Debe ser un numero de 5 (cinco) digitos.");
		lblDescIDSucursal.setBounds(30, 160, 250, 14);
		lblDescIDSucursal.setForeground(Color.GRAY);
		lblDescIDSucursal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblDescIDSucursal);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setVerticalAlignment(SwingConstants.TOP);
		lblNombre.setBounds(415, 110, 355, 20);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(415, 135, 355, 20);
		txtNombre.setColumns(10);
		add(txtNombre);
		
		JLabel lblDescNombre = new JLabel("Admite hasta 265 caracteres.");
		lblDescNombre.setBounds(415, 160, 355, 14);
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
		
		txtHorarioAperturaHora = new JTextField();
		txtHorarioAperturaHora.setBounds(30, 342, 50, 20);
		add(txtHorarioAperturaHora);
		
		txtHorarioAperturaMinutos = new JTextField();
		txtHorarioAperturaMinutos.setBounds(94, 342, 50, 20);
		add(txtHorarioAperturaMinutos);
		
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
		
		txtHorarioCierreHora = new JTextField();
		txtHorarioCierreHora.setBounds(415, 342, 50, 20);
		add(txtHorarioCierreHora);
		
		txtHorarioCierreMinutos = new JTextField();
		txtHorarioCierreMinutos.setBounds(479, 342, 50, 20);
		add(txtHorarioCierreMinutos);
		
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
		
		JLabel lblAltaDeProductos = new JLabel("Alta de sucursal");
		lblAltaDeProductos.setHorizontalAlignment(SwingConstants.LEFT);
		lblAltaDeProductos.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblAltaDeProductos.setBounds(10, 11, 780, 30);
		add(lblAltaDeProductos);
		
	}
	
	public void actionCancelar() {
		int result = JOptionPane.showConfirmDialog(frame,"¿Seguro que quieres cancelar la operacion? Los datos no seran guardados","Cancelar",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION) {
			this.setVisible(false);
			pantallaAnterior.setVisible(true);
			frame.setContentPane(pantallaAnterior);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void actionConfirmar() {
		
		ComboItem<TipoSucursal> selectTipoSucursal = (ComboItem<TipoSucursal>) cmbTipoSucursal.getSelectedItem();
		ComboItem<Operatividad> selectOperatividad = (ComboItem<Operatividad>) cmbOperatividad.getSelectedItem();
		/*
		try {
			GestorSucursal gestorSucursal = GestorSucursal.getInstance();
			gestorSucursal.altaSucursal(
					txtIDSucursal.getText(),
					txtNombre.getText(),
					selectTipoSucursal.getValue(),
					selectOperatividad.getValue(),
					txtHorarioAperturaHora.getText(),
					txtHorarioAperturaMinutos.getText(),
					txtHorarioCierreHora.getText(),
					txtHorarioCierreMinutos.getText()
			);
		}catch(DatosInvalidosException ex) {
			
		}
		*/
	}
}
