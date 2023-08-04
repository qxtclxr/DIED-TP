package dal.postgre;

import java.sql.SQLException;
import dal.general.FactoryDAO;
import dal.general.OrdenDeProvisionDAO;
import dal.general.ProductoDAO;
import dal.general.RutaDAO;
import dal.general.SucursalDAO;

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
