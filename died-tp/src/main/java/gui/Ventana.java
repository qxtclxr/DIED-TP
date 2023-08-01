package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dal.postgre.Conexion;

import java.awt.Dimension;
import java.sql.SQLException;

public class Ventana extends JFrame {
	
	Conexion conexion;
	
	public Ventana() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(800, 500));
		pack();
		
		MenuPrincipal menu = new MenuPrincipal(this);
		this.setContentPane(menu);
		
		setTitle("SGL (Sistema de Gestion Logistico)");
		setLocationRelativeTo(null);
		setResizable(false);		
	}
	
	public void abrirConexion() {
		try {
			conexion = Conexion.getInstance();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cerrarConexion() {
		
	}
	
}
