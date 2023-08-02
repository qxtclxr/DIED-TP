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

	
	public void altaOrden(String nombreSuc,TipoSucursal tipo, Operatividad operatividad,String horarioAperturaHora,String horarioAperturaMinutos, String horarioCierreHora, String horarioCierreMinutos) throws SQLException, ClassNotFoundException{
		/*
		 * En esta sucursal uso null para el id porque cuando lo persista, la BDD le va a generar un id serial automaticamente
		 */
		/*
		Sucursal aux= new Sucursal(nombreSuc,Time.valueOf(horarioAperturaHora+":"+horarioAperturaMinutos),Time.valueOf(horarioCierreHora+":"+horarioCierreMinutos),operatividad,tipo);
		FactoryDAO fact= FactoryDAO.getFactory(1);
		fact.getSucursalDAO().insert(aux);
		*/
	}
	public OrdenDeProvision getByID(Integer id) throws ClassNotFoundException, SQLException {
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getOrdenDeProvisionDAO().getByID(id);
	}
}
