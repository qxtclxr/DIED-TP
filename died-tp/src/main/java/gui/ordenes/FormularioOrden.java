package gui.ordenes;

import java.awt.Color;
import java.awt.Font;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import datos.Operatividad;
import datos.Sucursal;
import datos.TipoSucursal;
import gui.Pantalla;
import javax.swing.JScrollPane;

public abstract class FormularioOrden extends Pantalla {
	protected JComboBox<Sucursal> cmbSucursalDestino;
	protected JTextField txtDuracion;
	
	public FormularioOrden(JFrame frame, JPanel pantallaAnterior) {
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
		
		for(Sucursal suc : todasLasSucursales)
			cmbSucursalOrigen.addItem(suc);
		
		JLabel lblSucursalDestino = new JLabel("Sucursal de destino");
		lblSucursalDestino.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalDestino.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSucursalDestino.setBounds(222, 156, 355, 20);
		add(lblSucursalDestino);
		
		cmbSucursalDestino = new JComboBox<Sucursal>();
		cmbSucursalDestino.setBounds(222, 181, 355, 20);
		add(cmbSucursalDestino);
		
		for(Sucursal suc : todasLasSucursales)
			cmbSucursalDestino.addItem(suc);
		
		JLabel lblDuracion = new JLabel("Tiempo maximo hasta recibir la orden");
		lblDuracion.setVerticalAlignment(SwingConstants.TOP);
		lblDuracion.setBounds(222, 264, 355, 20);
		lblDuracion.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblDuracion);
		
		txtDuracion = new JTextField();
		txtDuracion.setBounds(222, 289, 355, 20);
		add(txtDuracion);
		
		JLabel descDuracion = new JLabel("Tiempo maximo en el que se desea recibir la orden (en horas).");
		descDuracion.setForeground(Color.GRAY);
		descDuracion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descDuracion.setBounds(222, 314, 355, 14);
		add(descDuracion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 159, 162));
		btnCancelar.setBounds(580, 467, 100, 23);
		btnCancelar.addActionListener(act -> this.actionCancelar());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Continuar");
		btnConfirmar.setBackground(new Color(129, 205, 133));
		btnConfirmar.setBounds(690, 467, 100, 23);
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnConfirmar);		
		
		JLabel lblSucursalReceptoraDe = new JLabel("Sucursal receptora de los productos.");
		lblSucursalReceptoraDe.setForeground(Color.GRAY);
		lblSucursalReceptoraDe.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSucursalReceptoraDe.setBounds(222, 206, 355, 14);
		add(lblSucursalReceptoraDe);
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
