package gui.ruta;

import datos.*;
import gui.*;
import gui.tabla.OpcionesPopup;
import logica.GestorRuta;

import java.sql.SQLException;

import javax.swing.*;

public class OpcionesPopupRuta extends OpcionesPopup{
	
	public OpcionesPopupRuta(Integer id, JFrame frame, JPanel pantallaAnterior) {
		super(id,frame,pantallaAnterior);
	}

	@Override
	public void actionEditar(){
		try {
			Ruta target = GestorRuta.getInstance().getByID(id);
			EdicionRuta edicionRuta = new EdicionRuta(frame,pantalla,target);
			pantalla.setVisible(false);
			frame.setContentPane(edicionRuta);
		}catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			DatabaseErrorMessage.showMessageDialog(frame);
		}
	}

	@Override
	public void actionEliminar() {
		// TODO Auto-generated method stub
		
	}
	
	
}
