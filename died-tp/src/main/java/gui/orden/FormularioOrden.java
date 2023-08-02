package gui.orden;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import gui.DatabaseErrorMessage;
import gui.Pantalla;
import logica.GestorSucursal;

import javax.swing.JScrollPane;

public abstract class FormularioOrden extends Pantalla {
	protected JComboBox<Sucursal> cmbSucursalDestino;
	protected JTextField txtTiempoMaximo;
	
	public FormularioOrden(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
	}
	
	public void inicializarComponentes() {
		try {
			setLayout(null);
			
			List<Sucursal> posibleDestino = GestorSucursal.getInstance().getPosiblesDestinos();
			
			JSeparator separadorTituloContenido = new JSeparator();
			separadorTituloContenido.setBounds(10, 77, 780, 2);
			add(separadorTituloContenido);
			
			JLabel lblSucursalDestino = new JLabel("Sucursal de destino");
			lblSucursalDestino.setVerticalAlignment(SwingConstants.TOP);
			lblSucursalDestino.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblSucursalDestino.setBounds(222, 156, 355, 20);
			add(lblSucursalDestino);
			
			cmbSucursalDestino = new JComboBox<Sucursal>();
			cmbSucursalDestino.setBounds(222, 181, 355, 20);
			add(cmbSucursalDestino);
			
			for(Sucursal suc : posibleDestino)
				cmbSucursalDestino.addItem(suc);
			
			JLabel descSucursalDestino = new JLabel("Sucursal receptora de los productos.");
			descSucursalDestino.setForeground(Color.GRAY);
			descSucursalDestino.setFont(new Font("Tahoma", Font.PLAIN, 11));
			descSucursalDestino.setBounds(222, 206, 355, 14);
			add(descSucursalDestino);
			
			JLabel lblTiempoMaximo = new JLabel("Tiempo maximo hasta recibir la orden");
			lblTiempoMaximo.setVerticalAlignment(SwingConstants.TOP);
			lblTiempoMaximo.setBounds(222, 264, 355, 20);
			lblTiempoMaximo.setFont(new Font("Tahoma", Font.BOLD, 14));
			add(lblTiempoMaximo);
			
			txtTiempoMaximo = new JTextField();
			txtTiempoMaximo.setBounds(222, 289, 355, 20);
			add(txtTiempoMaximo);
			
			JLabel descTiempoMaximo = new JLabel("Tiempo maximo en el que se desea recibir la orden (en horas).");
			descTiempoMaximo.setForeground(Color.GRAY);
			descTiempoMaximo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			descTiempoMaximo.setBounds(222, 314, 355, 14);
			add(descTiempoMaximo);
			
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
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			DatabaseErrorMessage.showMessageDialog(frame);
			this.setVisible(false);
			pantallaAnterior.setVisible(true);
			frame.setContentPane(pantallaAnterior);
		}
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
