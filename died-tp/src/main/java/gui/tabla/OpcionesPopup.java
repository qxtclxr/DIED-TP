package gui.tabla;

import javax.swing.*;

public abstract class OpcionesPopup extends JPopupMenu{
	
	protected JFrame frame;
	protected JPanel pantalla;
	protected JMenuItem editar = new JMenuItem("Editar");
	protected JMenuItem eliminar = new JMenuItem("Eliminar");
	protected Integer id;
	
	public OpcionesPopup(Integer id, JFrame frame, JPanel pantalla) {
		super();
		this.id = id;
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