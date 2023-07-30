package dal;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgreFactoryDAO extends FactoryDAO {
	
	public SucursalDAO getSucursalDAO() throws ClassNotFoundException, SQLException{
		return new SucursalPostgreDAO();
	}
	public RutaDAO getRutaDAO() {
		return new RutaPostgreDAO();
	}
	
	public OrdenDeProvisionDAO getOrdenDeProvisionDAO() {
		return new OrdenDeProvisionPostgreDAO();
	}

	public ProductoDAO getProductoDAO() {
		return new ProductoPostgreDAO();
	}
}
