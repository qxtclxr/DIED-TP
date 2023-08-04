package gui.orden;

import java.awt.Color;
import java.awt.Font;
import datos.*;
import gui.Pantalla;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class SeleccionarCaminoOrden extends JPanel {
	
	List<Sucursal> sucursales;
	/**
	 * Create the panel.
	 */
	public SeleccionarCaminoOrden() {
		setLayout(null);
		JLabel lblTitulo = new JLabel("Seleccion de camino");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitulo.setBounds(10, 11, 780, 30);
		add(lblTitulo);
		
		JLabel descTitulo = new JLabel("Selecciona una sucursal de origen y a continuación selecciona el camino por el que llegará la mercaderia.");
		descTitulo.setForeground(Color.GRAY);
		descTitulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descTitulo.setBounds(10, 50, 780, 14);
		add(descTitulo);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		JComboBox<Sucursal> comboBox = new JComboBox<>();
		comboBox.setBounds(10, 111, 250, 22);
		add(comboBox);
		
		JLabel lblSucursalDeDestino = new JLabel("Sucursal de destino");
		lblSucursalDeDestino.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalDeDestino.setBounds(10, 90, 250, 20);
		lblSucursalDeDestino.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblSucursalDeDestino);
		
		JButton btnNewButton = new JButton("Buscar caminos");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(273, 111, 126, 23);
		add(btnNewButton);
		
		JSeparator separadorTituloContenido_1 = new JSeparator();
		separadorTituloContenido_1.setBounds(10, 150, 780, 2);
		add(separadorTituloContenido_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 159, 162));
		btnCancelar.setBounds(690, 466, 100, 23);
		btnCancelar.addActionListener(act -> this.actionCancelar());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 187, 780, 269);
		add(tabbedPane);
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
