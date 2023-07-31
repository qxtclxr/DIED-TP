package gui.producto;

import datos.*;
import gui.*;
import gui.tabla.OpcionesPopup;

import javax.swing.*;

public class OpcionesPopupProducto extends OpcionesPopup<Producto>{
	
	public OpcionesPopupProducto(Producto obj, JFrame frame, JPanel pantallaAnterior) {
		super(obj,frame,pantallaAnterior);
	}

	@Override
	public void actionEditar() {
		//System.out.println(obj.getID());
		EdicionProducto edicionProducto = new EdicionProducto(frame,pantalla,obj);
		pantalla.setVisible(false);
		frame.setContentPane(edicionProducto);
	}

	@Override
	public void actionEliminar() {
		// TODO Auto-generated method stub
	}
	
	
}
