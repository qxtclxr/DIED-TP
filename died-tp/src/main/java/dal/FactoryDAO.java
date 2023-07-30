package dal;

import java.sql.Connection;

public abstract class FactoryDAO {
	public static final int POSTGRE_FACTORY = 1;
	
	public abstract SucursalDAO getSucursalDAO();
	public abstract RutaDAO getRutaDAO();
	public abstract OrdenDeProvisionDAO getOrdenDeProvisionDAO();
	public abstract ProductoDAO getProductoDAO();
	
	
	public static FactoryDAO getFactory(int key) {
		switch(key) {
		case POSTGRE_FACTORY:
			return new PostgreFactoryDAO();
		}
		return new PostgreFactoryDAO();
	}
}
