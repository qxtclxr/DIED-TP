package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;

public class ConsultaSucursal extends Pantalla {
	
	private JTextField txtIDSucursal;
	private JTextField txtNombre;
	private JComboBox cmbTipoDeSucursal;
	private JComboBox cmbEstado;
	private JTextField txtHorarioAperturaHora;
	private JTextField txtHorarioAperturaMinutos;
	private JLabel lblIDSucursal;
	private JLabel lblNombre;
	private JLabel lblTipoDeSucursal;
	private JLabel lblEstadoDeOperatividad;
	private JLabel lblHorarioDeApertura;
	private JLabel lblHorarioDeCierre;
	private JLabel lblSeparadorHorarioApertura;
	private JTextField txtHorarioCierreHora;
	private JLabel lblSeparadorHorarioCierre;
	private JTextField txtHorarioCierreMinutos;
	private JButton btnNewButton;
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public ConsultaSucursal(JFrame frame, JPanel pantallaAnterior) {
		super(frame,pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		setLayout(null);
		
		JLabel lblCompletaLosCampos = new JLabel("Completa los campos para filtrar la busqueda. A continuacion presiona el boton \"Buscar\".");
		lblCompletaLosCampos.setForeground(Color.GRAY);
		lblCompletaLosCampos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCompletaLosCampos.setBounds(10, 50, 425, 14);
		add(lblCompletaLosCampos);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		JLabel lblConsultaDeSucursales = new JLabel("Consulta de sucursales");
		lblConsultaDeSucursales.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultaDeSucursales.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblConsultaDeSucursales.setBounds(10, 11, 780, 30);
		add(lblConsultaDeSucursales);
		
		txtIDSucursal = new JTextField();
		txtIDSucursal.setBounds(10, 105, 121, 20);
		add(txtIDSucursal);
		txtIDSucursal.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(141, 105, 121, 20);
		add(txtNombre);
		
		cmbTipoDeSucursal = new JComboBox();
		cmbTipoDeSucursal.setBounds(272, 105, 121, 20);
		add(cmbTipoDeSucursal);
		
		cmbEstado = new JComboBox();
		cmbEstado.setBounds(403, 105, 121, 20);
		add(cmbEstado);
		
		lblIDSucursal = new JLabel("ID de sucursal");
		lblIDSucursal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIDSucursal.setVerticalAlignment(SwingConstants.TOP);
		lblIDSucursal.setBounds(10, 90, 121, 14);
		add(lblIDSucursal);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setVerticalAlignment(SwingConstants.TOP);
		lblNombre.setBounds(141, 90, 121, 14);
		add(lblNombre);
		
		lblTipoDeSucursal = new JLabel("Tipo de sucursal");
		lblTipoDeSucursal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoDeSucursal.setVerticalAlignment(SwingConstants.TOP);
		lblTipoDeSucursal.setBounds(272, 90, 121, 14);
		add(lblTipoDeSucursal);
		
		lblEstadoDeOperatividad = new JLabel("Estado\r\n");
		lblEstadoDeOperatividad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadoDeOperatividad.setVerticalAlignment(SwingConstants.TOP);
		lblEstadoDeOperatividad.setBounds(403, 90, 121, 14);
		add(lblEstadoDeOperatividad);
		
		lblHorarioDeApertura = new JLabel("Horario de apertura");
		lblHorarioDeApertura.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHorarioDeApertura.setVerticalAlignment(SwingConstants.TOP);
		lblHorarioDeApertura.setBounds(534, 90, 121, 14);
		add(lblHorarioDeApertura);
		
		lblHorarioDeCierre = new JLabel("Horario de cierre");
		lblHorarioDeCierre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHorarioDeCierre.setVerticalAlignment(SwingConstants.TOP);
		lblHorarioDeCierre.setBounds(665, 90, 121, 14);
		add(lblHorarioDeCierre);
		
		txtHorarioAperturaHora = new JTextField();
		txtHorarioAperturaHora.setBounds(534, 105, 35, 20);
		add(txtHorarioAperturaHora);
		
		lblSeparadorHorarioApertura = new JLabel(":");
		lblSeparadorHorarioApertura.setVerticalAlignment(SwingConstants.TOP);
		lblSeparadorHorarioApertura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSeparadorHorarioApertura.setBounds(570, 104, 7, 22);
		add(lblSeparadorHorarioApertura);
		
		txtHorarioAperturaMinutos = new JTextField();
		txtHorarioAperturaMinutos.setBounds(577, 105, 35, 20);
		add(txtHorarioAperturaMinutos);
		
		txtHorarioCierreHora = new JTextField();
		txtHorarioCierreHora.setBounds(665, 104, 35, 20);
		add(txtHorarioCierreHora);
		
		lblSeparadorHorarioCierre = new JLabel(":");
		lblSeparadorHorarioCierre.setVerticalAlignment(SwingConstants.TOP);
		lblSeparadorHorarioCierre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSeparadorHorarioCierre.setBounds(701, 103, 7, 22);
		add(lblSeparadorHorarioCierre);
		
		txtHorarioCierreMinutos = new JTextField();
		txtHorarioCierreMinutos.setBounds(708, 104, 35, 20);
		add(txtHorarioCierreMinutos);
		
		btnNewButton = new JButton("Buscar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 136, 121, 20);
		add(btnNewButton);
		
	}
}
