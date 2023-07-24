package logica.bdd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	private String url = "jdbc:postgresql://localhost:5432/bdtpdied";
	private String username = "usertpdied";
	private String password = "user";
	private boolean estado = false;
	private Connection con;
	
	
	
		public Connection conectar() { 
		
			try {
				 this.con = DriverManager.getConnection(url, username, password);
				
				System.out.println("Conexion Exitosa ! ");
				
				this.estado = true;
				
				return this.con;
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
	
	}
	
	public void desconectar() {
		try {
			
			if(!this.con.isClosed()) {
				this.con.close();
				this.estado = false; 
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getEstado() {
		return this.estado;
	}
	
	
}
