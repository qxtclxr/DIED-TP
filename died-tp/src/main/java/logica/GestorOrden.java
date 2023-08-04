package logica;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import dal.general.FactoryDAO;
import datos.Operatividad;
import datos.OrdenDeProvision;
import datos.Sucursal;
import datos.TipoSucursal;
import excepciones.IDNotFoundException;

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
	
	public List<OrdenDeProvision> getPendientes() throws ClassNotFoundException, SQLException {
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getOrdenDeProvisionDAO().getPendientes();
	}
	
	public OrdenDeProvision getByID(Integer id) throws ClassNotFoundException, SQLException, IDNotFoundException {
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getOrdenDeProvisionDAO().getByID(id);
	}
}
