package logica;

import java.sql.Connection;
import java.sql.SQLException;

import dal.general.FactoryDAO;
import dal.general.SucursalDAO;
import datos.Ruta;
import datos.Operatividad;

public final class GestorRutas {
	/* falta gestionar responsabilidades para esta clase
	 * 
	 */
	private Connection conn;
	private static GestorRutas gestor;
	
	public synchronized static GestorRutas getInstance(Connection c) {
		if(gestor==null) {
			gestor=new GestorRutas(c);
		}
		return gestor;
	}
	
	private GestorRutas(Connection c) {
		super();
		this.conn=c;
	}
	
	public void altaRuta(Integer idRuta, Integer idSucursalOrigen, Integer idSucursalDestino ,Operatividad estado, Integer duracion, Float capacidad) throws SQLException{
		
		FactoryDAO fact= FactoryDAO.getFactory(1);
		SucursalDAO auxDAOSucursal= fact.getSucursalDAO(conn);
	
		Ruta aux= new Ruta(idRuta,auxDAOSucursal.getByID(idSucursalOrigen),auxDAOSucursal.getByID(idSucursalDestino),estado,duracion,capacidad);
		fact.getRutaDAO(conn).insert(aux);
	}
	
	

}
