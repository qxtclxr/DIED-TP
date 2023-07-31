package dal.implementacionesPostgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import dal.general.FactoryDAO;
import dal.general.RutaDAO;
import datos.Operatividad;
import datos.Ruta;
import datos.Sucursal;
import datos.TipoSucursal;


public class RutaPostgreDAO implements RutaDAO{

	private Connection conn;
	private static int BATCH_LIMIT = 1000;
	
	public RutaPostgreDAO() throws ClassNotFoundException, SQLException {
		super();
		this.conn = Conexion.getInstance().getConn();;
	}
	
	
	@Override
	public void insert(Ruta obj) throws SQLException {
		String statement = "INSERT INTO RUTA (idruta,origen,destino,duracion,capacidadmaxima,estado) VALUES (?,?,?,?,?,?)";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {			
	        pstm.setInt(1, obj.getID());
	        pstm.setInt(2, obj.getOrigen().getID());
	        pstm.setInt(3, obj.getDestino().getID());
	        pstm.setInt(4, obj.getDuracion());
	        pstm.setFloat(5, obj.getCapacidadMaxima());
	        pstm.setString(6, obj.getEstado().toString());
	        pstm.executeUpdate();
		}
		
	}

	@Override
	public void update(Ruta obj) throws SQLException {
		String statement = "UPDATE RUTA SET idruta = ?, origen = ?, destino = ?, duracion = ?, capacidadmaxima = ?, estado = ? WHERE idruta = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {			
	        pstm.setInt(1, obj.getID());
	        pstm.setInt(2, obj.getOrigen().getID());
	        pstm.setInt(3, obj.getDestino().getID());
	        pstm.setInt(4, obj.getDuracion());
	        pstm.setFloat(5, obj.getCapacidadMaxima());
	        pstm.setString(6, obj.getEstado().toString());
	        pstm.setInt(7, obj.getID());
	        pstm.executeUpdate();
		}
		
	}

	public void delete(Ruta obj) throws SQLException {
		String statement = "DELETE FROM Ruta WHERE idruta = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,obj.getID());
		}
		
	}
	public Ruta getByID(Integer id) throws SQLException {
		String statement = "SELECT idruta,origen,destino,duracion,capacidadmaxima,estado " +
				   "FROM Ruta " +
				   "WHERE idruta = ?";
		Ruta rut = null;
		
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,id);
			try(ResultSet rs = pstm.executeQuery();){
				
				FactoryDAO f = FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
				f.getSucursalDAO().getByID(id);
				
				
				while(rs.next()) {
				

					
				}
			}
		}
		
		return null;
	}
	
	public List<Ruta> searchByAttributes(Ruta obj){
		//TODO
		return null;
	}
}