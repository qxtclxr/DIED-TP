package dal;

import java.sql.Connection;

public class PostgreFactoryDAO extends FactoryDAO {
	
	public SucursalDAO getSucursalDAO(Connection conn) {
		return new SucursalPostgreDAO(conn);
	}
	public RutaDAO getRutaDAO(Connection conn) {
		return new RutaPostgreDAO(conn);
	}

}
