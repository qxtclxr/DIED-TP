package gui;

import datos.*;
import javax.swing.*;

public class OpcionesSucursalPopup extends JPopupMenu{
	
	private JMenuItem editar = new JMenuItem("Editar");
	private JMenuItem eliminar = new JMenuItem("Eliminar");
	private JMenuItem modifStock = new JMenuItem("Modificar stock de productos");
	private Sucursal obj;
	
	public OpcionesSucursalPopup(Sucursal obj) {
		super();
		this.obj = obj;
		
		editar.addActionListener(act -> actionEditar());
		
		add(editar);
		add(eliminar);
		add(modifStock);
	}
	
	public void actionEditar() {
		//TODO
		System.out.println(obj.getID());
	}
}
