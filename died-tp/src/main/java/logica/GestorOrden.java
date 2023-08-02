package logica;

import java.sql.SQLException;
import java.sql.Time;

import dal.general.FactoryDAO;
import datos.Operatividad;
import datos.OrdenDeProvision;
import datos.Sucursal;
import datos.TipoSucursal;

public final class GestorOrden {
private static GestorOrden gestor;
	
	public synchronized static GestorOrden getInstance() {
		if(gestor==null) {
			gestor= new GestorOrden();
		}
		return gestor;
	}

	private GestorOrden(){
		super();
	}

	
	public void altaOrden(OrdenDeProvision orden) throws ClassNotFoundException, SQLException {
		FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getOrdenDeProvisionDAO().insert(orden);
	}
	
	public OrdenDeProvision getByID(Integer id) throws ClassNotFoundException, SQLException {
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getOrdenDeProvisionDAO().getByID(id);
	}
}
