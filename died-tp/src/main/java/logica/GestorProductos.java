package logica;


import java.sql.SQLException;

import dal.general.FactoryDAO;
import dal.general.SucursalDAO;
import datos.Operatividad;
import datos.Producto;

public class GestorProductos {
	/* falta gestionar responsabilidades para esta clase
	 * 
	 */

	private static GestorProductos gestor;
	
	public synchronized static GestorProductos getInstance() {
		if(gestor==null) {
			gestor=new GestorProductos();
		}
		return gestor;
	}
	
	private GestorProductos() {
		super();
	}
	
	public void altaProducto(String nombre,String descripcion, Float precioU, Float pesoKg) throws SQLException, ClassNotFoundException{
		
		FactoryDAO fact= FactoryDAO.getFactory(1);
	
	
		Producto aux= new Producto(nombre,descripcion,precioU,pesoKg);
		
		fact.getProductoDAO().insert(aux);
	}
	
	

}
