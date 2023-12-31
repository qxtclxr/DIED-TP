package logica;


import java.sql.SQLException;
import java.util.List;

import dal.general.FactoryDAO;
import dal.general.SucursalDAO;
import datos.Operatividad;
import datos.Producto;
import datos.Sucursal;
import excepciones.IDNotFoundException;

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
	
	public void altaProducto(String nombre,String descripcion, String precioU, String pesoKg) throws SQLException, ClassNotFoundException{
		
		FactoryDAO fact= FactoryDAO.getFactory(1);
		
		Float parsePrecio = Float.parseFloat(precioU);
		Float parsePeso = Float.parseFloat(pesoKg);
	
		Producto aux= new Producto(nombre,descripcion,parsePrecio,parsePeso);
		
		fact.getProductoDAO().insert(aux);
	}
	public void modificarProducto(Integer idProducto, String nombre,String descripcion, String precioU, String pesoKg) throws SQLException, ClassNotFoundException{
			
			FactoryDAO fact= FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
			
			Float parsePrecio = Float.parseFloat(precioU);
			Float parsePeso = Float.parseFloat(pesoKg);
		
			Producto aux= new Producto(idProducto,nombre,descripcion,parsePrecio,parsePeso);
			
			fact.getProductoDAO().update(aux);
		}
	public Producto getByID(Integer id) throws ClassNotFoundException, SQLException, IDNotFoundException {
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
	public void eliminar(Producto p) throws ClassNotFoundException, SQLException {
		FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getProductoDAO().delete(p);
	}
	

}
