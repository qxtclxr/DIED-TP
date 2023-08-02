package dal.postgre;

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
		this.conn = Conexion.getInstance().getConn();
	}
	
	
	@Override
	public void insert(Ruta obj) throws SQLException {
		String statement = "INSERT INTO RUTA (origen,destino,duracion,capacidadmaxima,estado) VALUES (?,?,?,?,?)";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {			
	        pstm.setInt(1, obj.getOrigen().getID());
	        pstm.setInt(2, obj.getDestino().getID());
	        pstm.setInt(3, obj.getDuracion());
	        pstm.setFloat(4, obj.getCapacidadMaxima());
	        pstm.setString(5, obj.getEstado().getValueAsString());
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
	        pstm.setString(6, obj.getEstado().getValueAsString());
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
		String statement =
				"SELECT or.idsucursal,or.nombre,or.horarioapertura,or.horariocierre,or.estado,or.tipo," +
						"de.idsucursal,de.nombre,de.horarioapertura,de.horariocierre,de.estado,de.tipo," +
						"r.idruta,r.duracion,r.capacidadmaxima,r.estado " +
						"FROM Ruta r, Sucursal or, Sucursal de " +
						"WHERE r.origen = or.idsucursal AND r.destino = de.idsucursal AND r.idruta = ?";
		Ruta ruta = null;
		
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,id);
			
			try(ResultSet rs = pstm.executeQuery();){		
				
				while(rs.next()) {
					Sucursal origenAux  = new Sucursal();
					origenAux.setID(rs.getInt(1));
					origenAux.setNombre(rs.getString(2));
					origenAux.setHorarioApertura(rs.getTime(3));
					origenAux.setHorarioCierre(rs.getTime(4));
					origenAux.setEstado(Operatividad.valueOf(rs.getString(5)));
					origenAux.setTipo(TipoSucursal.valueOf(rs.getString(6)));
					Sucursal destinoAux = new Sucursal();
					destinoAux.setID(rs.getInt(7));
					destinoAux.setNombre(rs.getString(8));
					destinoAux.setHorarioApertura(rs.getTime(9));
					destinoAux.setHorarioCierre(rs.getTime(10));
					destinoAux.setEstado(Operatividad.valueOf(rs.getString(11)));
					destinoAux.setTipo(TipoSucursal.valueOf(rs.getString(12)));
					ruta = new Ruta();
					ruta.setID(rs.getInt(13));
					ruta.setDuracion(rs.getInt(14));
					ruta.setCapacidadMaxima(rs.getFloat(15));
					ruta.setEstado(Operatividad.valueOf(rs.getString(16)));
					ruta.setOrigen(origenAux);
					ruta.setOrigen(destinoAux);				
				}
				
			}
		}
		
		return ruta;
	}
	
	public List<Ruta> searchByAttributes(Integer idRuta, Sucursal origen, Sucursal destino,
										 Operatividad estado, Integer duracionDesde, Integer duracionHasta,
										 Float capacMaxDesde, Float capacMaxHasta) throws SQLException {
		List<Ruta> result = new ArrayList<>();
		
		try(PreparedStatement pstm = searchStatement(idRuta,origen,destino,estado,duracionDesde,duracionHasta,capacMaxDesde,capacMaxHasta);
			ResultSet rs = pstm.executeQuery()){
			while(rs.next()) {
				Sucursal origenAux  = new Sucursal();
				origenAux.setID(rs.getInt(1));
				origenAux.setNombre(rs.getString(2));
				origenAux.setHorarioApertura(rs.getTime(3));
				origenAux.setHorarioCierre(rs.getTime(4));
				origenAux.setEstado(Operatividad.valueOf(rs.getString(5)));
				origenAux.setTipo(TipoSucursal.valueOf(rs.getString(6)));
				Sucursal destinoAux = new Sucursal();
				destinoAux.setID(rs.getInt(7));
				destinoAux.setNombre(rs.getString(8));
				destinoAux.setHorarioApertura(rs.getTime(9));
				destinoAux.setHorarioCierre(rs.getTime(10));
				destinoAux.setEstado(Operatividad.valueOf(rs.getString(11)));
				destinoAux.setTipo(TipoSucursal.valueOf(rs.getString(12)));
				Ruta ruta = new Ruta();
				ruta.setID(rs.getInt(13));
				ruta.setDuracion(rs.getInt(14));
				ruta.setCapacidadMaxima(rs.getFloat(15));
				ruta.setEstado(Operatividad.valueOf(rs.getString(16)));
				ruta.setOrigen(origenAux);
				ruta.setDestino(destinoAux);
				result.add(ruta);
			}
		}
		
		return result;
	}
	
	private PreparedStatement searchStatement(Integer idRuta, Sucursal origen, Sucursal destino,
			 								  Operatividad estado, Integer duracionDesde, Integer duracionHasta,
			 								  Float capacMaxDesde, Float capacMaxHasta) throws SQLException {
		String statement =
				"SELECT o.idsucursal,o.nombre,o.horarioapertura,o.horariocierre,o.estado,o.tipo," +
				"d.idsucursal,d.nombre,d.horarioapertura,d.horariocierre,d.estado,d.tipo," +
				"r.idruta,r.duracion,r.capacidadmaxima,r.estado " +
				"FROM Ruta r, Sucursal o, Sucursal d " +
				"WHERE r.origen = o.idsucursal AND r.destino = d.idsucursal";
		if(idRuta != null) statement += " AND idruta::TEXT LIKE '%?%'"; //puede dar un error.
		if(origen != null) statement += " AND origen = ?";
		if(destino != null) statement += " AND destino = ?";
		if(estado != null) statement += " AND estado = ?";
		if(duracionDesde != null || duracionHasta != null) statement += " AND (duracion BETWEEN ? AND ?)";
		if(capacMaxDesde != null || capacMaxHasta != null) statement += " AND (capacidadmaxima BETWEEN ? AND ?)";
		
		statement += " ORDER BY o.nombre";
		
		PreparedStatement pstm = conn.prepareStatement(statement);
		
		int paramIndex = 1;
		if(idRuta != null) pstm.setInt(paramIndex++,idRuta);
		if(origen != null) pstm.setInt(paramIndex++,origen.getID());
		if(destino != null) pstm.setInt(paramIndex++,destino.getID());
		if(estado != null) pstm.setString(paramIndex++,destino.getEstado().getValueAsString());
		if(duracionDesde != null || duracionHasta != null) {
			if(duracionDesde != null) pstm.setInt(paramIndex++,duracionDesde);
			else pstm.setInt(paramIndex++,-1);
			if(duracionHasta != null) pstm.setInt(paramIndex++,duracionHasta);
			else pstm.setInt(paramIndex++,Integer.MAX_VALUE);
		}
		if(capacMaxDesde != null || capacMaxHasta != null) {
			if(capacMaxDesde != null) pstm.setFloat(paramIndex++,capacMaxDesde);
			else pstm.setFloat(paramIndex++,-1);
			if(capacMaxHasta != null) pstm.setFloat(paramIndex++,capacMaxHasta);
			else pstm.setString(paramIndex++,"inf");
		}
		return pstm;
	}
}