package dal.general;

import datos.OrdenDeProvision;

import java.sql.SQLException;
import java.util.*;

public interface OrdenDeProvisionDAO extends DAO<OrdenDeProvision>{

	public abstract List<OrdenDeProvision> getAll() throws SQLException;

	public abstract void setEnProceso(OrdenDeProvision obj) throws SQLException;

}
