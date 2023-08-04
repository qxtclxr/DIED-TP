package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Pantalla extends JPanel{
	
	protected JFrame frame;
	protected JPanel pantallaAnterior;
	
	public Pantalla(JFrame frame, JPanel pantallaAnterior) {
		this.frame = frame;
		this.pantallaAnterior = pantallaAnterior;
		inicializarComponentes();
	}

	public Pantalla() {
		// TODO Auto-generated constructor stub
	}

	public abstract void inicializarComponentes();
}
