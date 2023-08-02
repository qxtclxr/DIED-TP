package gui.producto;

import datos.*;
import gui.*;
import logica.GestorProducto;
import gui.tabla.OpcionesPopup;

import java.sql.SQLException;

import javax.swing.*;

public class OpcionesPopupProducto extends OpcionesPopup{
	
	public OpcionesPopupProducto(Integer id, JFrame frame, JPanel pantallaAnterior) {
		super(id,frame,pantallaAnterior);
	}

	@Override
	public void actionEditar(){
		try {
			Producto target = GestorProducto.getInstance().getByID(id);
			EdicionProducto edicionProducto = new EdicionProducto(frame,pantalla,target);
			pantalla.setVisible(false);
			frame.setContentPane(edicionProducto);
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
