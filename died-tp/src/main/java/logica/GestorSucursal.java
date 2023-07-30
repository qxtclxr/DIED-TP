package logica;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;

import dal.FactoryDAO;
import datos.Operatividad;
import datos.Sucursal;
import datos.TipoSucursal;
import gui.ComboItem;

public final class GestorSucursal {
	/* Responsabilidades que tendria esta clas:
	 * ALERTA: Una vez que la inicialices, no vas a poder cambiarle la conexion
	 * Realizar altas y bajas mediante la conexion a un DAO
	 */
	
	private static GestorSucursal gestor;
	
	public synchronized static GestorSucursal getInstance(Connection c) {
		if(gestor==null) {
			gestor= new GestorSucursal();
		}
		return gestor;
	}

	private GestorSucursal(){

		super();
	}

	
	public void altaSucursal(String idSuc,String nombreSuc,TipoSucursal tipo, Operatividad operatividad,String horarioAperturaHora,String horarioAperturaMinutos, String horarioCierreHora, String horarioCierreMinutos) throws SQLException, ClassNotFoundException{
		//tengo que armar el objeto de datos y luego persistirlo
		//chequear posta estos constructores
		/*
		 * 
		 * 
		 */
		Sucursal aux= new Sucursal(idSuc,nombreSuc,Time.valueOf(horarioAperturaHora+":"+horarioAperturaMinutos),Time.valueOf(horarioCierreHora+":"+horarioCierreMinutos),operatividad,tipo);
		FactoryDAO fact= FactoryDAO.getFactory(1);
		fact.getSucursalDAO().insert(aux);
	}
	
	

}
