package logica;


import java.sql.SQLException;
import java.util.List;

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
	public List<Producto> consultaPorAtributos(String idProducto, String nombre, String precioUDesde, String precioUHasta,String pesoDesde, String pesoHasta) throws ClassNotFoundException, SQLException{
		if(idProducto.isBlank()) {
			idProducto=null;
		}
		if(nombre.isBlank()) {
			nombre=null;
		}
		Float precioUDesdeF;
		Float precioUHastaF;
		if(precioUDesde.isBlank()) {
			precioUDesdeF=null;
		}
		else {
			precioUDesdeF=Float.parseFloat(precioUDesde);
		}
		if(precioUHasta.isBlank()) {
			precioUHastaF=null;
		}
		else {
			precioUHastaF=Float.parseFloat(precioUHasta);
		}
		
		Float pesoDesdeF;
		Float pesoHastaF;
		if(pesoDesde.isBlank()) {
			pesoDesdeF=null;
		}
		else {
			pesoDesdeF=Float.parseFloat(pesoDesde);
		}
		if(pesoHasta.isBlank()) {
			pesoHastaF=null;
		}
		else {
			pesoHastaF=Float.parseFloat(pesoHasta);
		}
		
		
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getProductoDAO().searchByAttributes(idProducto,nombre,precioUDesdeF,precioUHastaF,pesoDesdeF,pesoHastaF);
	}
	

}
