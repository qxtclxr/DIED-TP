package logica;

import java.sql.Connection;
import java.sql.SQLException;

import dal.FactoryDAO;
import dal.SucursalDAO;
import datos.Operatividad;
import datos.Producto;

public class GestorProductos {
	/* falta gestionar responsabilidades para esta clase
	 * 
	 */
	private Connection conn;
	private static GestorProductos gestor;
	
	public synchronized static GestorProductos getInstance(Connection c) {
		if(gestor==null) {
			gestor=new GestorProductos(c);
		}
		return gestor;
	}
	
	private GestorProductos(Connection c) {
		super();
		this.conn=c;
	}
	
	public void altaProducto(String idProducto, String nombre,String descripcion, Float precioU, Float pesoKg) throws SQLException{
		
		FactoryDAO fact= FactoryDAO.getFactory(1);
	
	
		Producto aux= new Producto(idProducto,nombre,descripcion,precioU,pesoKg);
		
		fact.getProductoDAO(conn).insert(aux);
	}
	
	

}
