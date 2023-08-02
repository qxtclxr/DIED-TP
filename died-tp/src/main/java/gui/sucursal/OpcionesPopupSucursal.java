package gui.sucursal;

import datos.*;
import gui.*;
import gui.tabla.OpcionesPopup;
import logica.GestorSucursal;

import java.sql.SQLException;

import javax.swing.*;

public class OpcionesPopupSucursal extends OpcionesPopup{
	
	private JMenuItem modifStock = new JMenuItem("Modificar stock de productos");
	
	public OpcionesPopupSucursal(Integer id, JFrame frame, JPanel pantallaAnterior) {
		super(id,frame,pantallaAnterior);
		add(modifStock);
	}

	@Override
	public void actionEditar() {
		try {
			Sucursal target = GestorSucursal.getInstance().getByID(id);
			EdicionSucursal edicionSucursal = new EdicionSucursal(frame,pantalla,target);
			pantalla.setVisible(false);
			frame.setContentPane(edicionSucursal);
		}catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			DatabaseErrorMessage.showMessageDialog(frame);
		}
		
	}

	@Override
	public void actionEliminar() {
		// TODO Auto-generated method stub
		try {
			Sucursal target = GestorSucursal.getInstance().getByID(id);
			EdicionSucursal edicionSucursal = new EdicionSucursal(frame,pantalla,target);
			pantalla.setVisible(false);
			frame.setContentPane(edicionSucursal);
		}catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			DatabaseErrorMessage.showMessageDialog(frame);
		}
	}
}
