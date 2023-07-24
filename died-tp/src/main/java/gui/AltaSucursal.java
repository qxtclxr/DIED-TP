package gui;

import javax.swing.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaSucursal extends JPanel {
	
	private JFrame frame;
	private JPanel pantallaAnterior;
	private JTextField txtIDSucursal;
	private JTextField txtNombre;
	private JTextField txtHorarioAperturaHora;
	private JTextField txtHorarioAperturaMinutos;
	private JTextField txtHorarioCierreMinutos;
	private JTextField txtHorarioCierreHora;
	
	public AltaSucursal(JFrame frame, JPanel pantallaAnterior) {
		this.frame = frame;
		this.pantallaAnterior = pantallaAnterior;
		inicializarComponentes();
	}
	
	public void inicializarComponentes() {
		setLayout(null);
		
		JLabel lblAltaDeSucursales = new JLabel("Alta de sucursales");
		lblAltaDeSucursales.setBounds(10, 11, 780, 30);
		lblAltaDeSucursales.setVerticalAlignment(SwingConstants.TOP);
		lblAltaDeSucursales.setFont(new Font("Roboto Black", Font.PLAIN, 32));
		add(lblAltaDeSucursales);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		JLabel lblDescripcion = new JLabel("Completa el formulario y presiona \"Continuar\" para guardar los datos ingresados. Los datos obligatorios estan marcados con");
		lblDescripcion.setBounds(10, 52, 626, 14);
		lblDescripcion.setForeground(new Color(128, 128, 128));
		lblDescripcion.setFont(new Font("Roboto", Font.PLAIN, 11));
		add(lblDescripcion);
		
		JLabel lblDescripcionAsterisco = new JLabel("(*)");
		lblDescripcionAsterisco.setBounds(637, 52, 13, 14);
		lblDescripcionAsterisco.setForeground(new Color(255, 0, 0));
		lblDescripcionAsterisco.setFont(new Font("Roboto", Font.PLAIN, 11));
		add(lblDescripcionAsterisco);
		
		JLabel lblIDSucursal = new JLabel("ID de sucursal");
		lblIDSucursal.setBounds(10, 113, 250, 14);
		lblIDSucursal.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		add(lblIDSucursal);
		
		txtIDSucursal = new JTextField();
		txtIDSucursal.setBounds(10, 135, 250, 20);
		add(txtIDSucursal);
		txtIDSucursal.setColumns(10);
		
		JLabel lblDescIDSucursal = new JLabel("Debe ser un numero de 5 (cinco) digitos.");
		lblDescIDSucursal.setBounds(10, 160, 250, 14);
		lblDescIDSucursal.setForeground(Color.GRAY);
		lblDescIDSucursal.setFont(new Font("Roboto", Font.PLAIN, 11));
		add(lblDescIDSucursal);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(275, 113, 250, 14);
		lblNombre.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(275, 135, 250, 20);
		txtNombre.setColumns(10);
		add(txtNombre);
		
		JLabel lblDescNombre = new JLabel("Admite hasta 265 caracteres.");
		lblDescNombre.setBounds(275, 160, 250, 14);
		lblDescNombre.setForeground(Color.GRAY);
		lblDescNombre.setFont(new Font("Roboto", Font.PLAIN, 11));
		add(lblDescNombre);
		
		JLabel lblTipoSucursal = new JLabel("Tipo de sucursal");
		lblTipoSucursal.setBounds(540, 113, 250, 14);
		lblTipoSucursal.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		add(lblTipoSucursal);
		
		JRadioButton rdbtnComercial = new JRadioButton("Comercial");
		rdbtnComercial.setBounds(541, 135, 249, 14);
		rdbtnComercial.setSelected(true);
		rdbtnComercial.setFont(new Font("Roboto", Font.PLAIN, 13));
		add(rdbtnComercial);
		
		JRadioButton rdbtnFuente = new JRadioButton("Fuente");
		rdbtnFuente.setBounds(541, 153, 249, 14);
		rdbtnFuente.setFont(new Font("Roboto", Font.PLAIN, 13));
		add(rdbtnFuente);
		
		JRadioButton rdbtnSumidero = new JRadioButton("Sumidero");
		rdbtnSumidero.setBounds(541, 171, 249, 14);
		rdbtnSumidero.setFont(new Font("Roboto", Font.PLAIN, 13));
		add(rdbtnSumidero);
		
		ButtonGroup btnGrpTipoSucursal = new ButtonGroup();
		btnGrpTipoSucursal.add(rdbtnComercial);
		btnGrpTipoSucursal.add(rdbtnFuente);
		btnGrpTipoSucursal.add(rdbtnSumidero);
		
		JLabel lblHorarioDeApertura = new JLabel("Horario de apertura");
		lblHorarioDeApertura.setBounds(10, 243, 250, 14);
		lblHorarioDeApertura.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		add(lblHorarioDeApertura);
		
		txtHorarioAperturaHora = new JTextField();
		txtHorarioAperturaHora.setBounds(10, 265, 50, 20);
		txtHorarioAperturaHora.setColumns(10);
		add(txtHorarioAperturaHora);
		
		txtHorarioAperturaMinutos = new JTextField();
		txtHorarioAperturaMinutos.setBounds(74, 265, 50, 20);
		txtHorarioAperturaMinutos.setColumns(10);
		add(txtHorarioAperturaMinutos);
		
		JLabel lblSeparadorHorarioApertura = new JLabel(":");
		lblSeparadorHorarioApertura.setBounds(65, 265, 5, 22);
		lblSeparadorHorarioApertura.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		add(lblSeparadorHorarioApertura);
		
		JLabel lblDescHorarioApertura = new JLabel("Se utiliza formato de 24 horas.");
		lblDescHorarioApertura.setBounds(10, 290, 158, 14);
		lblDescHorarioApertura.setForeground(Color.GRAY);
		lblDescHorarioApertura.setFont(new Font("Roboto", Font.PLAIN, 11));
		add(lblDescHorarioApertura);
		
		JLabel lblHorarioDeCierre = new JLabel("Horario de cierre");
		lblHorarioDeCierre.setBounds(275, 243, 250, 14);
		lblHorarioDeCierre.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		add(lblHorarioDeCierre);		
		
		txtHorarioCierreMinutos = new JTextField();
		txtHorarioCierreMinutos.setBounds(339, 263, 50, 20);
		txtHorarioCierreMinutos.setColumns(10);
		add(txtHorarioCierreMinutos);
		
		txtHorarioCierreHora = new JTextField();
		txtHorarioCierreHora.setBounds(275, 263, 50, 20);
		txtHorarioCierreHora.setColumns(10);
		add(txtHorarioCierreHora);
		
		JLabel lblSeparadorHorarioCierre = new JLabel(":");
		lblSeparadorHorarioCierre.setBounds(330, 263, 5, 22);
		lblSeparadorHorarioCierre.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		add(lblSeparadorHorarioCierre);
		
		JLabel lblDescHorarioCierre = new JLabel("Se utiliza formato de 24 horas.");
		lblDescHorarioCierre.setBounds(275, 290, 158, 14);
		lblDescHorarioCierre.setForeground(Color.GRAY);
		lblDescHorarioCierre.setFont(new Font("Roboto", Font.PLAIN, 11));
		add(lblDescHorarioCierre);
		
		JLabel lblEstadoDeOperatividad = new JLabel("Estado de operatividad");
		lblEstadoDeOperatividad.setBounds(540, 243, 250, 14);
		lblEstadoDeOperatividad.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		add(lblEstadoDeOperatividad);
		
		JRadioButton rdbtnOperativa = new JRadioButton("Operativa");
		rdbtnOperativa.setBounds(541, 265, 249, 14);
		rdbtnOperativa.setSelected(true);
		rdbtnOperativa.setFont(new Font("Roboto", Font.PLAIN, 13));
		add(rdbtnOperativa);
		
		JRadioButton rdbtnNoOperativa = new JRadioButton("No Operativa");
		rdbtnNoOperativa.setBounds(541, 283, 249, 14);
		rdbtnNoOperativa.setFont(new Font("Roboto", Font.PLAIN, 13));
		add(rdbtnNoOperativa);
		
		ButtonGroup btnGrpOperatividad = new ButtonGroup();
		btnGrpOperatividad.add(rdbtnOperativa);
		btnGrpOperatividad.add(rdbtnNoOperativa);
		
		JLabel lblProductosEnStock = new JLabel("Productos en stock");
		lblProductosEnStock.setBounds(275, 367, 250, 14);
		lblProductosEnStock.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		add(lblProductosEnStock);
		
		JButton btnProductosEnStock = new JButton("Editar stock de productos");
		btnProductosEnStock.setBounds(275, 389, 250, 23);
		btnProductosEnStock.setHorizontalAlignment(SwingConstants.LEFT);
		btnProductosEnStock.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		add(btnProductosEnStock);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(580, 467, 100, 23);
		btnCancelar.addActionListener(act -> this.actionCancelar());
		btnCancelar.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(690, 467, 100, 23);
		btnConfirmar.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
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
}
