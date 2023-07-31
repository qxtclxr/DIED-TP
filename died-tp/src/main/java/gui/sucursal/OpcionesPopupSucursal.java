package gui.sucursal;

import datos.*;
import gui.*;
import javax.swing.*;

public class OpcionesPopupSucursal extends OpcionesPopup<Sucursal>{
	
	private JMenuItem modifStock = new JMenuItem("Modificar stock de productos");
	
	public OpcionesPopupSucursal(Sucursal obj, JFrame frame, JPanel pantallaAnterior) {
		super(obj,frame,pantallaAnterior);
		add(modifStock);
	}

	@Override
	public void actionEditar() {
		System.out.println(obj.getID());
		EdicionSucursal edicionSucursal = new EdicionSucursal(frame,pantalla,obj);
		pantalla.setVisible(false);
		frame.setContentPane(edicionSucursal);
	}

	@Override
	public void actionEliminar() {
		// TODO Auto-generated method stub
		
	}
	
	
}