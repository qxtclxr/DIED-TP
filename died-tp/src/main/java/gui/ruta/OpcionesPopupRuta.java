package gui.ruta;

import datos.*;
import gui.*;
import gui.sucursal.ConsultaSucursal;
import gui.tabla.OpcionesPopup;
import logica.GestorRuta;
import logica.GestorSucursal;

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
		int result = JOptionPane.showConfirmDialog(
				frame,
				"¿Seguro que quieres eliminar la ruta?",
				"Pedido de confirmacion",JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION) {
			try {
				GestorRuta gestor = GestorRuta.getInstance();
				Ruta target = gestor.getByID(id);
				gestor.eliminar(target);
				JOptionPane.showMessageDialog(
						frame,
						"La ruta ha sido eliminada correctamente.",
						"Datos guardados",
						JOptionPane.INFORMATION_MESSAGE);
				//Refresca la tabla
				((ConsultaRuta) pantalla).actionBuscar();
			}catch(ClassNotFoundException | SQLException ex) {
				ex.printStackTrace();
				DatabaseErrorMessage.showMessageDialog(frame);
			}
		}
		
	}
	
	
}
