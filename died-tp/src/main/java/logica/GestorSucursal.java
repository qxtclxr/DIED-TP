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

	
	public void altaSucursal(String nombreSuc,TipoSucursal tipo, Operatividad operatividad,String horarioAperturaHora,String horarioAperturaMinutos, String horarioCierreHora, String horarioCierreMinutos) throws SQLException, ClassNotFoundException{
		/*
		 * En esta sucursal uso null para el id porque cuando lo persista, la BDD le va a generar un id serial automaticamente
		 */
		Sucursal aux= new Sucursal(nombreSuc,Time.valueOf(horarioAperturaHora+":"+horarioAperturaMinutos),Time.valueOf(horarioCierreHora+":"+horarioCierreMinutos),operatividad,tipo);
		FactoryDAO fact= FactoryDAO.getFactory(1);
		fact.getSucursalDAO().insert(aux);
	}
	

}
