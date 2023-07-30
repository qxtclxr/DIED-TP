package dal;

import java.sql.Connection;

public abstract class FactoryDAO {
	public static final int POSTGRE_FACTORY = 1;
	
	public abstract SucursalDAO getSucursalDAO(Connection conn);
	public abstract RutaDAO getRutaDAO(Connection conn);
	public abstract OrdenDeProvisionDAO getOrdenDeProvisionDAO(Connection conn);
	public abstract ProductoDAO getProductoDAO(Connection conn);
	
	
	public static FactoryDAO getFactory(int key) {
		switch(key) {
		case POSTGRE_FACTORY:
			return new PostgreFactoryDAO();
		}
		return new PostgreFactoryDAO();
	}
}
