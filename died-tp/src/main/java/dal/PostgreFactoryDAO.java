package dal;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgreFactoryDAO extends FactoryDAO {
	
	public SucursalDAO getSucursalDAO() throws ClassNotFoundException, SQLException{
		return new SucursalPostgreDAO();
	}
	public RutaDAO getRutaDAO() throws SQLException, ClassNotFoundException {
		return new RutaPostgreDAO();
	}
	
	public OrdenDeProvisionDAO getOrdenDeProvisionDAO() throws SQLException,ClassNotFoundException {
		return new OrdenDeProvisionPostgreDAO();
	}

	public ProductoDAO getProductoDAO() throws SQLException,ClassNotFoundException {
		return new ProductoPostgreDAO();
	}
}
