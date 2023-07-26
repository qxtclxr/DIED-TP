package dal;

import java.sql.*;

public class Conexion {

	private String url = "jdbc:postgresql://localhost:5432/bdtpdied";
	private String username = "usertpdied";
	private String password = "user";
	private boolean estado = false;
	private Connection con;	
	
	public Connection conectar() throws SQLException { 
		this.con = DriverManager.getConnection(url, username, password);
		this.estado = true;
		return this.con;
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
	
}
