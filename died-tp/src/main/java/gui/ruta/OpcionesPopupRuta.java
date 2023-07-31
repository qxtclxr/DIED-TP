package gui.ruta;

import datos.*;
import gui.*;
import gui.tabla.OpcionesPopup;

import javax.swing.*;

public class OpcionesPopupRuta extends OpcionesPopup<Ruta>{
	
	public OpcionesPopupRuta(Ruta obj, JFrame frame, JPanel pantallaAnterior) {
		super(obj,frame,pantallaAnterior);
	}

	@Override
	public void actionEditar() {
		//System.out.println(obj.getID());
		EdicionRuta edicionRuta = new EdicionRuta(frame,pantalla,obj);
		pantalla.setVisible(false);
		frame.setContentPane(edicionRuta);
	}

	@Override
	public void actionEliminar() {
		// TODO Auto-generated method stub
		
	}
	
	
}
