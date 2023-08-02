package logica;


import java.sql.SQLException;
import java.sql.Time;

import dal.general.FactoryDAO;
import datos.Operatividad;
import datos.Sucursal;
import datos.TipoSucursal;

public final class GestorSucursal {
	/* Responsabilidades que tendria esta clas:
	 * ALERTA: Una vez que la inicialices, no vas a poder cambiarle la conexion
	 * Realizar altas y bajas mediante la conexion a un DAO
	 */
	
	private static GestorSucursal gestor;
	
	public synchronized static GestorSucursal getInstance() {
		if(gestor==null) {
			gestor= new GestorSucursal();
		}
		return gestor;
	}

	private GestorSucursal(){
		super();
	}

	
	public void altaSucursal(String nombreSuc,TipoSucursal tipo, Operatividad operatividad,
							 String horarioApertura,String horarioCierre) throws SQLException, ClassNotFoundException{
		Time parseHorarioApertura = Time.valueOf(horarioApertura+":00");
		Time parseHorarioCierre = Time.valueOf(horarioCierre+":00");
		Sucursal aux= new Sucursal(nombreSuc,parseHorarioApertura,parseHorarioCierre,operatividad,tipo);
		FactoryDAO fact= FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
		fact.getSucursalDAO().insert(aux);
	}
	public void modificarSucursal(Integer idSucursal,String nombreSuc,TipoSucursal tipo,
								  Operatividad operatividad,String horarioApertura,String horarioCierre) throws ClassNotFoundException, SQLException {
		Time parseHorarioApertura = Time.valueOf(horarioApertura+":00");
		Time parseHorarioCierre = Time.valueOf(horarioCierre+":00");
		Sucursal aux= new Sucursal(idSucursal,nombreSuc,parseHorarioApertura,parseHorarioCierre,operatividad,tipo);
		FactoryDAO fact= FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
		fact.getSucursalDAO().update(aux);
	}
	

}
