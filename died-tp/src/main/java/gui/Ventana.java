package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import dal.postgre.Conexion;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Ventana extends JFrame {
	
	Conexion conexion;
	
	public Ventana() {
		
		abrirConexion();
		
		this.addWindowListener(new WindowAdapter() { //Cierra la conexion cuando cierra la ventana.
			public void windowClosing(WindowEvent event) {
				try {
					conexion.desconectar();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Hubo un error al desconectarse de la base de datos.","Error de conexion", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
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
			JOptionPane.showMessageDialog(null,"Hubo un error al conectarse a la base de datos, vuelva a intentarlo más tarde.","Error de conexion",JOptionPane.ERROR_MESSAGE);
			System.exit(ERROR);
		}
	}
	
}
