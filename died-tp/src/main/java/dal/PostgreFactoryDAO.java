package dal;

import java.sql.Connection;

public class PostgreFactoryDAO extends FactoryDAO {

	@Override
	public SucursalDAO getSucursalDAO(Connection conn) {
		return new SucursalPostgreDAO(conn);
	}

}
