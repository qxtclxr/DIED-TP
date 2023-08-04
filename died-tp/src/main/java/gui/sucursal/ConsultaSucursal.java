package gui.sucursal;

import datos.*;
import gui.DatabaseErrorMessage;
import gui.InvalidInputMessage;
import gui.Pantalla;
import gui.SyntaxValidator;
import gui.tabla.TablaDeDatos;
import logica.GestorSucursal;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Point;
import java.sql.SQLException;

public class ConsultaSucursal extends Pantalla {
	
	private static final String[] COL_NAMES = {"ID de sucursal","Nombre","Tipo de sucursal","Estado","Horario de apertura","Horario de cierre",""};
	private JTextField txtIDSucursal;
	private JTextField txtNombre;
	private JComboBox<TipoSucursal> cmbTipoSucursal;
	private JComboBox<Operatividad> cmbOperatividad;
	private JTextField txtHorarioApertura;
	private JTextField txtHorarioCierre;
	private TablaDeDatos tabla;
	private JScrollPane panelContenedorTabla;
	private JButton btnBuscar;
	
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
		txtIDSucursal.setBounds(10, 113, 121, 20);
		add(txtIDSucursal);
		txtIDSucursal.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(141, 113, 121, 20);
		add(txtNombre);
		
		cmbTipoSucursal = new JComboBox<TipoSucursal>();
		cmbTipoSucursal.setBounds(272, 113, 121, 20);
		add(cmbTipoSucursal);
		cmbTipoSucursal.addItem(null);
		cmbTipoSucursal.addItem(TipoSucursal.COMERCIAL);
		cmbTipoSucursal.addItem(TipoSucursal.FUENTE);
		cmbTipoSucursal.addItem(TipoSucursal.SUMIDERO);
		
		cmbOperatividad = new JComboBox<Operatividad>();
		cmbOperatividad.setBounds(403, 113, 121, 20);
		add(cmbOperatividad);
		cmbOperatividad.addItem(null);
		cmbOperatividad.addItem(Operatividad.OPERATIVA);
		cmbOperatividad.addItem(Operatividad.NO_OPERATIVA);
		
		JLabel lblIDSucursal = new JLabel("ID de sucursal");
		lblIDSucursal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIDSucursal.setVerticalAlignment(SwingConstants.TOP);
		lblIDSucursal.setBounds(10, 95, 121, 14);
		add(lblIDSucursal);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setVerticalAlignment(SwingConstants.TOP);
		lblNombre.setBounds(141, 95, 121, 14);
		add(lblNombre);
		
		JLabel lblTipoDeSucursal = new JLabel("Tipo de sucursal");
		lblTipoDeSucursal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoDeSucursal.setVerticalAlignment(SwingConstants.TOP);
		lblTipoDeSucursal.setBounds(272, 95, 121, 14);
		add(lblTipoDeSucursal);
		
		JLabel lblEstadoDeOperatividad = new JLabel("Estado");
		lblEstadoDeOperatividad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadoDeOperatividad.setVerticalAlignment(SwingConstants.TOP);
		lblEstadoDeOperatividad.setBounds(403, 95, 121, 14);
		add(lblEstadoDeOperatividad);
		
		JLabel lblHorarioDeApertura = new JLabel("Horario de apertura");
		lblHorarioDeApertura.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHorarioDeApertura.setVerticalAlignment(SwingConstants.TOP);
		lblHorarioDeApertura.setBounds(534, 95, 121, 14);
		add(lblHorarioDeApertura);
		
		JLabel lblHorarioDeCierre = new JLabel("Horario de cierre");
		lblHorarioDeCierre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHorarioDeCierre.setVerticalAlignment(SwingConstants.TOP);
		lblHorarioDeCierre.setBounds(665, 95, 121, 14);
		add(lblHorarioDeCierre);
		
		MaskFormatter horaMask = this.createFormatter("##:##");
		horaMask.setPlaceholderCharacter('-');
		
		txtHorarioApertura = new JFormattedTextField(horaMask);
		txtHorarioApertura.setBounds(534, 113, 121, 20);
		add(txtHorarioApertura);
		
		txtHorarioCierre = new JFormattedTextField(horaMask);
		txtHorarioCierre.setBounds(665, 113, 121, 20);
		add(txtHorarioCierre);
		
		JLabel lblSeparadorHorarioCierre = new JLabel(":");
		lblSeparadorHorarioCierre.setVerticalAlignment(SwingConstants.TOP);
		lblSeparadorHorarioCierre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSeparadorHorarioCierre.setBounds(701, 111, 7, 20);
		add(lblSeparadorHorarioCierre);
		
		JLabel descHorarioApertura = new JLabel("<html>Abre a las (o antes de<br/>las):</html>");
		descHorarioApertura.setForeground(Color.GRAY);
		descHorarioApertura.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descHorarioApertura.setBounds(534, 133, 121, 28);
		add(descHorarioApertura);
		
		JLabel descPrecioHasta = new JLabel("<html>Cierra a las (o despues<br/>de las):</html>");
		descPrecioHasta.setForeground(Color.GRAY);
		descPrecioHasta.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descPrecioHasta.setBounds(665, 133, 121, 28);
		add(descPrecioHasta);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(act -> actionBuscar());
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setBounds(10, 144, 121, 20);
		add(btnBuscar);
		
		JButton btnCancelar = new JButton("Volver");
		btnCancelar.setBounds(690, 466, 100, 23);
		btnCancelar.addActionListener(act -> this.actionVolver());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
		
		fieldsDefaultColor();
		generarTabla();
	}
	
	protected void fieldsDefaultColor() {
		txtNombre.setBackground(Color.WHITE);
		cmbTipoSucursal.setBackground(Color.WHITE);
		cmbOperatividad.setBackground(Color.WHITE);
		txtHorarioApertura.setBackground(Color.WHITE);
		txtHorarioCierre.setBackground(Color.WHITE);
	}
	
	private MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	    	//TODO
	    }
	    return formatter;
	}

	private void generarTabla() {
		tabla = new TablaDeDatos(COL_NAMES);
		tabla.onPressingButton(act -> actionOpcionesPopup());
		panelContenedorTabla = new JScrollPane(tabla);
		panelContenedorTabla.setBounds(10, 174, 780, 277);
		add(panelContenedorTabla);
	}
	
	protected boolean validateInput() {
		fieldsDefaultColor();
		
		Color colorInvalid = Color.decode("#ff8080");
		boolean validInput = true;
		
		if(!SyntaxValidator.validID()) {
			txtIDSucursal.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validTextLength(txtNombre,0,256)) {
			txtNombre.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validComboboxOrNull(cmbTipoSucursal)) {
			cmbTipoSucursal.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validComboboxOrNull(cmbOperatividad)) {
			cmbOperatividad.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validHorarioOrEmpty(txtHorarioApertura)) {
			txtHorarioApertura.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validHorarioOrEmpty(txtHorarioCierre)) {
			txtHorarioCierre.setBackground(colorInvalid);
			validInput = false;
		}
		return validInput;
	}
	
	public void actionBuscar() {
		
		List<Sucursal> dataList = new ArrayList<>();
		if(this.validateInput()) {
			try {				
				dataList = GestorSucursal.getInstance().consultaPorAtributos(
						txtIDSucursal.getText(),
						txtNombre.getText(),
						(TipoSucursal) cmbTipoSucursal.getSelectedItem(),
						(Operatividad) cmbOperatividad.getSelectedItem(),
						txtHorarioApertura.getText(),
						txtHorarioCierre.getText());
				DefaultTableModel model = (DefaultTableModel) tabla.getModel();
				model.setRowCount(0); //Resetea la tabla
				for(Sucursal suc : dataList) {
					Object[] fila = {
							suc.getID(),
							suc.getNombre(),
							suc.getTipo(),
							suc.getEstado(),
							suc.getHorarioApertura(),
							suc.getHorarioCierre(),
							suc.getID()
					};
					model.addRow(fila);
				}
			}catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				DatabaseErrorMessage.showMessageDialog(frame);
			}
		}else {
			InvalidInputMessage.showMessageDialog(frame);
		}
	}
	
	public void actionOpcionesPopup() {
		int row = tabla.convertRowIndexToModel(tabla.getEditingRow());
        int column = tabla.convertColumnIndexToModel(tabla.getEditingColumn());
        Rectangle cellRect = tabla.getCellRect(row, column, true);
        Point popupLocation = new Point(cellRect.x + cellRect.width, cellRect.y);
        Integer idSelected = (Integer) tabla.getModel().getValueAt(row,0);
        OpcionesPopupSucursal popupMenu = new OpcionesPopupSucursal(idSelected,frame,this);
        popupMenu.show(tabla, popupLocation.x, popupLocation.y);
	}
	
	public void actionVolver(){
		this.setVisible(false);
		pantallaAnterior.setVisible(true);
		frame.setContentPane(pantallaAnterior);
	}
}