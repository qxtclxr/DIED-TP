package dal;

import java.sql.*;

public final class Conexion {
	/*ver tema clonado
	 * 
	 */
	

	private String url = "jdbc:postgresql://localhost:5432/bdtpdied";
	private String username = "usertpdied";
	private String password = "user";
	private boolean estado = false;
	private Connection con;	
	private static Conexion obj;
	
	
	public synchronized static Conexion getInstance() throws SQLException, ClassNotFoundException{
		if(obj==null) {
			obj= new Conexion();
		}
		return obj;
	}
	
	
	
	public Conexion() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		this.con = DriverManager.getConnection(url, username, password);
		this.estado = true;
	}
	
	public void desconectar() throws SQLException {
		if(!(this.con==null || this.con.isClosed())){
			this.con.close();
			this.estado = false;
		}
	}
	
	public boolean getEstado() {
		return this.estado;
	}
	
	public Connection getConn() {
		return this.con;
	}
	
}
