package gui;

import javax.swing.*;

public abstract class OpcionesPopup<T> extends JPopupMenu{
	
	protected JFrame frame;
	protected JPanel pantalla;
	protected JMenuItem editar = new JMenuItem("Editar");
	protected JMenuItem eliminar = new JMenuItem("Eliminar");
	protected T obj;
	
	public OpcionesPopup(T obj, JFrame frame, JPanel pantalla) {
		super();
		this.obj = obj;
		this.frame = frame;
		this.pantalla = pantalla;
		editar.addActionListener(act -> actionEditar());
		eliminar.addActionListener(act -> actionEliminar());
		add(editar);
		add(eliminar);
	}
	
	public abstract void actionEditar();
	
	public abstract void actionEliminar();
}