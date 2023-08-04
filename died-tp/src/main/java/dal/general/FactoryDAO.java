package dal.general;

import java.sql.SQLException;

import dal.postgre.PostgreFactoryDAO;

public abstract class FactoryDAO {
	public static final int POSTGRE_FACTORY = 1;
	
	public abstract SucursalDAO getSucursalDAO()throws SQLException,ClassNotFoundException;
	public abstract RutaDAO getRutaDAO() throws SQLException,ClassNotFoundException;
	public abstract OrdenDeProvisionDAO getOrdenDeProvisionDAO()throws SQLException,ClassNotFoundException;
	public abstract ProductoDAO getProductoDAO() throws SQLException,ClassNotFoundException;
	
	
	public static FactoryDAO getFactory(int key) {
		switch(key) {
		case POSTGRE_FACTORY:
			return new PostgreFactoryDAO();
		}
		return new PostgreFactoryDAO();
	}
}
