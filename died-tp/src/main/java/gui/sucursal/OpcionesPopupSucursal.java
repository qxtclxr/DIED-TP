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
			JOptionPane.showMessageDialog(
					frame,
					"Ha habido un error al interactuar con la base de datos.\nIntente de nuevo más tarde.",
					"Error de base de datos",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void actionEliminar() {
		// TODO Auto-generated method stub
		
	}
	
	
}
