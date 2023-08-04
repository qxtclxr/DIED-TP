package gui.producto;

import datos.*;
import excepciones.IDNotFoundException;
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
		}catch(IDNotFoundException ex) {
			ex.getMessage();
			//Refresca la tabla
			((ConsultaProducto) pantalla).actionBuscar();
		}
	}

	@Override
	public void actionEliminar() {
		int result = JOptionPane.showConfirmDialog(
				frame,
				"¿Seguro que quieres eliminar el producto?",
				"Pedido de confirmacion",JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION) {
			try {
				GestorProducto gestor = GestorProducto.getInstance();
				Producto target = gestor.getByID(id);
				gestor.eliminar(target);
				JOptionPane.showMessageDialog(
						frame,
						"El producto ha sido eliminado correctamente.",
						"Datos guardados",
						JOptionPane.INFORMATION_MESSAGE);
			}catch(ClassNotFoundException | SQLException ex) {
				ex.printStackTrace();
				DatabaseErrorMessage.showMessageDialog(frame);
			}catch(IDNotFoundException ex) {
				ex.getMessage();
			}finally {
				//Refresca la tabla
				((ConsultaProducto) pantalla).actionBuscar();
			}
		}
	}
	
	
}
