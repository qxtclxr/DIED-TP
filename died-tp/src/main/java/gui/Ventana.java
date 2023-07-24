package gui;

import javax.swing.JFrame;
import java.awt.Dimension;

public class Ventana extends JFrame {

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
	
}
