package logica;


import java.sql.SQLException;

import dal.general.FactoryDAO;
import dal.general.SucursalDAO;
import datos.Operatividad;
import datos.Producto;

public class GestorProducto {
	/* falta gestionar responsabilidades para esta clase
	 * 
	 */

	private static GestorProducto gestor;
	
	public synchronized static GestorProducto getInstance() {
		if(gestor==null) {
			gestor=new GestorProducto();
		}
		return gestor;
	}
	
	private GestorProducto() {
		super();
	}
	
	public void altaProducto(String nombre,String descripcion, Float precioU, Float pesoKg) throws SQLException, ClassNotFoundException{
		
		FactoryDAO fact= FactoryDAO.getFactory(1);
	
	
		Producto aux= new Producto(nombre,descripcion,precioU,pesoKg);
		
		fact.getProductoDAO().insert(aux);
	}
	public void modificarProducto(Integer idProducto, String nombre,String descripcion, Float precioU, Float pesoKg) throws SQLException, ClassNotFoundException{
			
			FactoryDAO fact= FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
		
		
			Producto aux= new Producto(idProducto,nombre,descripcion,precioU,pesoKg);
			
			fact.getProductoDAO().update(aux);
		}
	public Producto getByID(Integer id) throws ClassNotFoundException, SQLException {
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getProductoDAO().getByID(id);
	}
	

}
