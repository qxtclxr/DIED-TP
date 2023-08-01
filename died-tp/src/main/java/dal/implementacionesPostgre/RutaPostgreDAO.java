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
		String statement = "INSERT INTO RUTA (origen,destino,duracion,capacidadmaxima,estado) VALUES (?,?,?,?,?)";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {			
	        pstm.setInt(1, obj.getOrigen().getID());
	        pstm.setInt(2, obj.getDestino().getID());
	        pstm.setInt(3, obj.getDuracion());
	        pstm.setFloat(4, obj.getCapacidadMaxima());
	        pstm.setString(5, obj.getEstado().toString());
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
	
	public List<Ruta> searchByAttributes(Integer idRuta, Sucursal origen, Sucursal destino,
										 Operatividad estado, Integer duracionDesde, Integer duracionHasta,
										 Float capacMaxDesde, Float capacMaxHasta){
		List<Ruta> result = new ArrayList<>();
		
		try(){
			
		}
		
		return result;
	}
	
	private PreparedStatement searchStatement(Integer idRuta, Sucursal origen, Sucursal destino,
			 								  Operatividad estado, Integer duracionDesde, Integer duracionHasta,
			 								  Float capacMaxDesde, Float capacMaxHasta) throws SQLException {
		String statement = "SELECT idruta,origen,destino,duracion,capacidadmaxima,estado FROM Ruta WHERE 1=1";
		if(idRuta != null) statement += " AND idruta::TEXT LIKE '%?%'"; //puede dar un error.
		if(origen != null) statement += " AND origen = ?";
		if(destino != null) statement += " AND destino = ?";
		if(estado != null) statement += " AND estado = ?";
		if(duracionDesde != null || duracionHasta != null) statement += " (duracion BETWEEN ? AND ?) AND";
		if(capacMaxDesde != null || capacMaxHasta != null) statement += " (capacidadmaxima BETWEEN ? AND ?) AND";
		
		PreparedStatement pstm = conn.prepareStatement(statement);
		
		int paramIndex = 1;
		if(idRuta != null) pstm.setInt(paramIndex++,idRuta);
		if(origen != null) pstm.setInt(paramIndex++,origen.getID());
		if(destino != null) pstm.setInt(paramIndex++,destino.getID());
		if(estado != null) pstm.setString(paramIndex++,destino.getEstado().toString());
		
		return pstm;
	}
}