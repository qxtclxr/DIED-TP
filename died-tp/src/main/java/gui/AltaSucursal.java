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
		lblAltaDeSucursales.setVerticalAlignment(SwingConstants.TOP);
		lblAltaDeSucursales.setFont(new Font("Roboto Black", Font.PLAIN, 32));
		lblAltaDeSucursales.setBounds(10, 11, 780, 30);
		add(lblAltaDeSucursales);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		JLabel lblDescripcion = new JLabel("Completa el formulario y presiona \"Continuar\" para guardar los datos ingresados. Los datos obligatorios estan marcados con");
		lblDescripcion.setForeground(new Color(128, 128, 128));
		lblDescripcion.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblDescripcion.setBounds(10, 52, 626, 14);
		add(lblDescripcion);
		
		JLabel lblDescripcionAsterisco = new JLabel("(*)");
		lblDescripcionAsterisco.setForeground(new Color(255, 0, 0));
		lblDescripcionAsterisco.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblDescripcionAsterisco.setBounds(637, 52, 13, 14);
		add(lblDescripcionAsterisco);
		
		JLabel lblIDSucursal = new JLabel("ID de sucursal");
		lblIDSucursal.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		lblIDSucursal.setBounds(10, 113, 250, 14);
		add(lblIDSucursal);
		
		txtIDSucursal = new JTextField();
		txtIDSucursal.setBounds(10, 135, 250, 20);
		add(txtIDSucursal);
		txtIDSucursal.setColumns(10);
		
		JLabel lblDescIDSucursal = new JLabel("Debe ser un numero de 5 (cinco) digitos.");
		lblDescIDSucursal.setForeground(Color.GRAY);
		lblDescIDSucursal.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblDescIDSucursal.setBounds(10, 160, 250, 14);
		add(lblDescIDSucursal);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		lblNombre.setBounds(275, 113, 250, 14);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(275, 135, 250, 20);
		add(txtNombre);
		
		JLabel lblDescNombre = new JLabel("Admite hasta 265 caracteres.");
		lblDescNombre.setForeground(Color.GRAY);
		lblDescNombre.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblDescNombre.setBounds(275, 160, 250, 14);
		add(lblDescNombre);
		
		JLabel lblTipoSucursal = new JLabel("Tipo de sucursal");
		lblTipoSucursal.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		lblTipoSucursal.setBounds(540, 113, 250, 14);
		add(lblTipoSucursal);
		
		JRadioButton rdbtnComercial = new JRadioButton("Comercial");
		rdbtnComercial.setSelected(true);
		rdbtnComercial.setFont(new Font("Roboto", Font.PLAIN, 13));
		rdbtnComercial.setBounds(541, 135, 249, 14);
		add(rdbtnComercial);
		
		JRadioButton rdbtnFuente = new JRadioButton("Fuente");
		rdbtnFuente.setFont(new Font("Roboto", Font.PLAIN, 13));
		rdbtnFuente.setBounds(541, 153, 249, 14);
		add(rdbtnFuente);
		
		JRadioButton rdbtnSumidero = new JRadioButton("Sumidero");
		rdbtnSumidero.setFont(new Font("Roboto", Font.PLAIN, 13));
		rdbtnSumidero.setBounds(541, 171, 249, 14);
		add(rdbtnSumidero);
		
		ButtonGroup btnGrpTipoSucursal = new ButtonGroup();
		btnGrpTipoSucursal.add(rdbtnComercial);
		btnGrpTipoSucursal.add(rdbtnFuente);
		btnGrpTipoSucursal.add(rdbtnSumidero);
		
		JLabel lblHorarioDeApertura = new JLabel("Horario de apertura");
		lblHorarioDeApertura.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		lblHorarioDeApertura.setBounds(10, 243, 250, 14);
		add(lblHorarioDeApertura);
		
		txtHorarioAperturaHora = new JTextField();
		txtHorarioAperturaHora.setColumns(10);
		txtHorarioAperturaHora.setBounds(10, 265, 50, 20);
		add(txtHorarioAperturaHora);
		
		txtHorarioAperturaMinutos = new JTextField();
		txtHorarioAperturaMinutos.setColumns(10);
		txtHorarioAperturaMinutos.setBounds(74, 265, 50, 20);
		add(txtHorarioAperturaMinutos);
		
		JLabel lblSeparadorHorarioApertura = new JLabel(":");
		lblSeparadorHorarioApertura.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		lblSeparadorHorarioApertura.setBounds(65, 265, 5, 22);
		add(lblSeparadorHorarioApertura);
		
		JLabel lblDescHorarioApertura = new JLabel("Se utiliza formato de 24 horas.");
		lblDescHorarioApertura.setForeground(Color.GRAY);
		lblDescHorarioApertura.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblDescHorarioApertura.setBounds(10, 290, 158, 14);
		add(lblDescHorarioApertura);
		
		JLabel lblHorarioDeCierre = new JLabel("Horario de cierre");
		lblHorarioDeCierre.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		lblHorarioDeCierre.setBounds(275, 243, 250, 14);
		add(lblHorarioDeCierre);		
		
		txtHorarioCierreMinutos = new JTextField();
		txtHorarioCierreMinutos.setColumns(10);
		txtHorarioCierreMinutos.setBounds(339, 263, 50, 20);
		add(txtHorarioCierreMinutos);
		
		txtHorarioCierreHora = new JTextField();
		txtHorarioCierreHora.setColumns(10);
		txtHorarioCierreHora.setBounds(275, 263, 50, 20);
		add(txtHorarioCierreHora);
		
		JLabel lblSeparadorHorarioCierre = new JLabel(":");
		lblSeparadorHorarioCierre.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		lblSeparadorHorarioCierre.setBounds(330, 263, 5, 22);
		add(lblSeparadorHorarioCierre);
		
		JLabel lblDescHorarioCierre = new JLabel("Se utiliza formato de 24 horas.");
		lblDescHorarioCierre.setForeground(Color.GRAY);
		lblDescHorarioCierre.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblDescHorarioCierre.setBounds(275, 290, 158, 14);
		add(lblDescHorarioCierre);
		
		JLabel lblEstadoDeOperatividad = new JLabel("Estado de operatividad");
		lblEstadoDeOperatividad.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		lblEstadoDeOperatividad.setBounds(540, 243, 250, 14);
		add(lblEstadoDeOperatividad);
		
		JRadioButton rdbtnOperativa = new JRadioButton("Operativa");
		rdbtnOperativa.setSelected(true);
		rdbtnOperativa.setFont(new Font("Roboto", Font.PLAIN, 13));
		rdbtnOperativa.setBounds(541, 265, 249, 14);
		add(rdbtnOperativa);
		
		JRadioButton rdbtnNoOperativa = new JRadioButton("No Operativa");
		rdbtnNoOperativa.setFont(new Font("Roboto", Font.PLAIN, 13));
		rdbtnNoOperativa.setBounds(541, 283, 249, 14);
		add(rdbtnNoOperativa);
		
		ButtonGroup btnGrpOperatividad = new ButtonGroup();
		btnGrpOperatividad.add(rdbtnOperativa);
		btnGrpOperatividad.add(rdbtnNoOperativa);
		
		JLabel lblProductosEnStock = new JLabel("Productos en stock");
		lblProductosEnStock.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		lblProductosEnStock.setBounds(275, 367, 250, 14);
		add(lblProductosEnStock);
		
		JButton btnProductosEnStock = new JButton("Editar stock de productos");
		btnProductosEnStock.setHorizontalAlignment(SwingConstants.LEFT);
		btnProductosEnStock.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnProductosEnStock.setBounds(275, 389, 250, 23);
		add(btnProductosEnStock);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(act -> this.actionCancelar());
		btnCancelar.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnCancelar.setBounds(580, 467, 100, 23);
		add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnConfirmar.setBounds(690, 467, 100, 23);
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
