package dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.Operatividad;
import datos.Ruta;
import datos.Sucursal;
import datos.TipoSucursal;

public class RutaPostgreDAO implements DAO<Ruta>,RutaDAO{

	private Connection conn;
	private static int BATCH_LIMIT = 1000;
	
	public RutaDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insert(Ruta obj) throws SQLException {
		String statement = "INSERT INTO RUTA (idruta,origen,destino,duracion,capacidadmaxima,estado) VALUES (?,?,?,?,?,?)";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {			
	        pstm.setString(1, obj.getIdRuta());
	        pstm.setString(2, obj.getOrigen().getID());
	        pstm.setString(3, obj.getDestino().getID());
	        pstm.setInt(4, obj.getDuracion());
	        pstm.setFloat(5, obj.getCapacidadMaxima());
	        pstm.setString(6, obj.getEstado());
	        pstm.executeUpdate();
		}
		
	}

	@Override
	public void update(Ruta obj) throws SQLException {
		String statement = "UPDATE RUTA SET idruta = ?, origen = ?, destino = ?, duracion = ?, capacidadmaxima = ?, estado = ? WHERE idruta = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {			
	        pstm.setString(1, obj.getIdRuta());
	        pstm.setString(2, obj.getOrigen().getID());
	        pstm.setString(3, obj.getDestino().getID());
	        pstm.setInt(4, obj.getDuracion());
	        pstm.setFloat(5, obj.getCapacidadMaxima());
	        pstm.setString(6, obj.getEstado());
	        pstm.setString(7, obj.getIdRuta());
	        pstm.executeUpdate();
		}
		
	}

	@Override
	public void delete(Ruta obj) throws SQLException {
		String statement = "DELETE FROM Ruta WHERE idruta = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setString(1,obj.getIdRuta());
		}
		
	}

	@Override
	public Ruta getByID(String id) throws SQLException {
		
		return null;
	}
	
	
	
	
	
	
	
	
	
}
