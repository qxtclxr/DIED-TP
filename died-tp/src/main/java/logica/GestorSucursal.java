package logica;

import java.sql.Connection;

public final class GestorSucursal {
	/* responsabilidades que tendria esta clas:
	 * ALERTA: Una vez que la inicialices, no vas a poder cambiarle la conexion
	 * Realizar altas y bajas mediante la conexion a un DAO
	 */
	
	private Connection conn;
	private static GestorSucursal gestor;
	
	public synchronized static GestorSucursal getInstance(Connection c) {
		if(gestor==null) {
			gestor= new GestorSucursal(c);
		}
		return gestor;
	}
	
	private GestorSucursal(Connection c){
		super();
		this.conn=c;
	}
	
	public Boolean altaSucursal() {
		//tbd
		return false;
	};

}
