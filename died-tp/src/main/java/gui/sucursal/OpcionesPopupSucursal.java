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
		int result = JOptionPane.showConfirmDialog(
				frame,
				"¿Seguro que quieres eliminar la sucursal?",
				"Pedido de confirmacion",JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION) {
			try {
				GestorSucursal gestor = GestorSucursal.getInstance();
				Sucursal target = gestor.getByID(id);
				gestor.eliminar(target);
				JOptionPane.showMessageDialog(
						frame,
						"La sucursal ha sido eliminada correctamente.",
						"Datos guardados",
						JOptionPane.INFORMATION_MESSAGE);
				((ConsultaSucursal) pantalla).actionBuscar();
			}catch(ClassNotFoundException | SQLException ex) {
				ex.printStackTrace();
				DatabaseErrorMessage.showMessageDialog(frame);
			}
		}
		
	}
}
